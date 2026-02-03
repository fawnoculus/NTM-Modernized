package net.fawnoculus.ntm.api.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ConfigOption<T> {
    private final @NotNull String NAME;
    private final @Nullable String COMMENT;
    private final @NotNull Codec<T> CODEC;
    private final @NotNull T DEFAULT_VALUE;
    private @Nullable T currentValue = null;


    public ConfigOption(@NotNull String name, @NotNull Codec<T> codec, @NotNull T defaultValue, @Nullable String comment) {
        this.NAME = name;
        this.CODEC = codec;
        this.DEFAULT_VALUE = defaultValue;
        this.COMMENT = comment;
    }

    public String getName() {
        return this.NAME;
    }

    public @NotNull T getDefaultValue() {
        return this.DEFAULT_VALUE;
    }

    public Codec<T> getCodec() {
        return this.CODEC;
    }

    /**
     * @return The value this option is currently set to, or the default one if no value was set
     */
    public @NotNull T getValue() {
        if (currentValue == null) {
            return this.DEFAULT_VALUE;
        }
        return this.currentValue;
    }

    /**
     * @param value the Value the option will be set to
     */
    public void setValue(T value) {
        this.currentValue = value;
    }

    public @Nullable String getComment() {
        return this.COMMENT;
    }

    /**
     * @return if the Value was successfully set
     */
    public <U> boolean setValueFrom(U value, DynamicOps<U> ops) {
        if (this.CODEC.decode(ops, value) instanceof DataResult.Success<Pair<T, U>> success) {
            this.currentValue = success.value().getFirst();
            return true;
        }
        return false;
    }

    public <U> @Nullable U getEncodedValue(DynamicOps<U> ops) {
        DataResult<U> result = this.CODEC.encodeStart(ops, this.getValue());
        if (result instanceof DataResult.Success<U> success) {
            return success.value();
        }
        return null;
    }

    /// Anything Below is used for Commands ///

    public ArgumentBuilder<CommandSourceStack, ?> getGetCommand() {
        return Commands.literal(NAME).executes(
          context -> getOptionInfo(component -> context.getSource().sendSuccess(() -> component, false))
        );
    }

    public ArgumentBuilder<CommandSourceStack, ?> getSetCommand(ConfigFile file) {
        return Commands.literal(NAME)
          .then(Commands.argument("value", StringArgumentType.greedyString())
            .executes(context -> trySetOption(
              component -> context.getSource().sendSuccess(() -> component, true),
              context.getSource()::sendFailure,
              file,
              StringArgumentType.getString(context, "value"))
            )
          );
    }

    public ArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack, ?> getGetClientCommand() {
        return ClientCommandRegistrationEvent.literal(NAME).executes(
          context -> getOptionInfo(component -> context.getSource().arch$sendSuccess(() -> component, false))
        );
    }

    public ArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack, ?> getSetClientCommand(ConfigFile file) {
        return ClientCommandRegistrationEvent.literal(NAME)
          .then(ClientCommandRegistrationEvent.argument("value", StringArgumentType.greedyString())
            .executes(context -> trySetOption(
              component -> context.getSource().arch$sendSuccess(() -> component, true),
              context.getSource()::arch$sendFailure,
              file,
              StringArgumentType.getString(context, "value"))
            )
          );
    }

    private int getOptionInfo(Consumer<Component> consumer) {
        Component nameText = Component.translatable("command.ntm.get_option_info.name").withStyle(ChatFormatting.BLUE)
          .append(Component.literal(this.NAME).withStyle(ChatFormatting.WHITE));
        consumer.accept(nameText);

        if (this.COMMENT != null) {
            Component commentText = Component.translatable("command.ntm.get_option_info.comment").withStyle(ChatFormatting.YELLOW)
              .append(Component.literal(this.COMMENT).withStyle(ChatFormatting.WHITE));
            consumer.accept(commentText);
        }

        Component defaultValueText = Component.translatable("command.ntm.get_option_info.default").withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(this.CODEC.encodeStart(JsonOps.INSTANCE, this.DEFAULT_VALUE).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        consumer.accept(defaultValueText);

        Component currentValueText = Component.translatable("command.ntm.get_option_info.current_value").withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(this.CODEC.encodeStart(JsonOps.INSTANCE, this.getValue()).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        consumer.accept(currentValueText);

        return 0;
    }

    private int trySetOption(Consumer<Component> onSuccess, Consumer<Component> onFailure, ConfigFile file, String value) {
        JsonElement element;
        try {
            element = JsonParser.parseString(value);
        } catch (JsonSyntaxException exception) {
            onFailure.accept(Component.translatable("command.ntm.set_config_value.invalid_json", value));
            return -2;
        }

        if (this.setValueFrom(element, JsonOps.INSTANCE)) {
            onSuccess.accept(Component.translatable("command.ntm.set_config_value", this.NAME, value));
            file.writeFile();
            return 1;
        }

        onFailure.accept(Component.translatable("command.ntm.set_config_value.failed", this.NAME, value));
        return -1;
    }
}
