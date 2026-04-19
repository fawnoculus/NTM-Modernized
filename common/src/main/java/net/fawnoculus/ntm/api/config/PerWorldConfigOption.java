package net.fawnoculus.ntm.api.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class PerWorldConfigOption<T> {
    private final @NotNull String NAME;
    private final @Nullable String COMMENT;
    private final @NotNull Codec<T> CODEC;
    private final @NotNull ConfigOption<T> DEFAULT;
    private @Nullable ConfigOption<T> PER_WORLD = null;

    public PerWorldConfigOption(@NotNull String name, @NotNull Codec<T> codec, @NotNull ConfigOption<T> defaultOption, @Nullable String comment) {
        this.NAME = name;
        this.COMMENT = comment;
        this.CODEC = codec;
        this.DEFAULT = defaultOption;
    }

    public void setWorldConfigFile(ConfigFile worldConfigFile) {
        this.PER_WORLD = worldConfigFile.newOption(this.NAME, this.CODEC, this.DEFAULT.getValue(), this.COMMENT);
    }

    public void unsetWorldConfigFile() {
        this.PER_WORLD = null;
    }

    public T getValue() {
        return Objects.requireNonNullElse(this.PER_WORLD, this.DEFAULT).getValue();
    }

    /// Anything Below is used for Commands ///

    public ArgumentBuilder<CommandSourceStack, ?> getGetCommand() {
        return Commands.literal(NAME).executes(
          context -> getOptionInfo(component -> context.getSource().sendSuccess(() -> component, false))
        );
    }

    public ArgumentBuilder<CommandSourceStack, ?> getSetDefaultCommand(PerWorldConfigFile file) {
        return Commands.literal(NAME)
          .then(Commands.argument("value", StringArgumentType.greedyString())
            .executes(context -> trySetDefaultOption(
              component -> context.getSource().sendSuccess(() -> component, true),
              context.getSource()::sendFailure,
              file.defaultConfig(),
              StringArgumentType.getString(context, "value"))
            )
          );
    }

    public ArgumentBuilder<CommandSourceStack, ?> getSetCommand(PerWorldConfigFile file) {
        return Commands.literal(NAME)
          .then(Commands.argument("value", StringArgumentType.greedyString())
            .executes(context -> trySetPerWorldOption(
              component -> context.getSource().sendSuccess(() -> component, true),
              context.getSource()::sendFailure,
              file.worldConfig(),
              StringArgumentType.getString(context, "value"))
            )
          );
    }

    private int getOptionInfo(Consumer<Component> consumer) {
        Component nameText = Component.translatable(NtmTranslations.COMMAND_GET_OPTION_INFO_NAME).withStyle(ChatFormatting.BLUE)
          .append(Component.literal(this.NAME).withStyle(ChatFormatting.WHITE));
        consumer.accept(nameText);

        if (this.COMMENT != null) {
            Component commentText = Component.translatable(NtmTranslations.COMMAND_GET_OPTION_INFO_COMMENT).withStyle(ChatFormatting.YELLOW)
              .append(Component.literal(this.COMMENT).withStyle(ChatFormatting.WHITE));
            consumer.accept(commentText);
        }

        Component defaultValueText = Component.translatable(NtmTranslations.COMMAND_GET_OPTION_INFO_DEFAULT).withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(this.CODEC.encodeStart(JsonOps.INSTANCE, this.DEFAULT.getValue()).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        consumer.accept(defaultValueText);

        Component worldDefaultValueText = Component.translatable(NtmTranslations.COMMAND_GET_OPTION_INFO_WORLD_DEFAULT).withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(this.CODEC.encodeStart(JsonOps.INSTANCE, this.DEFAULT.getValue()).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        consumer.accept(worldDefaultValueText);

        Component currentValueText = Component.translatable(NtmTranslations.COMMAND_GET_OPTION_INFO_VALUE).withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(this.CODEC.encodeStart(JsonOps.INSTANCE, this.getValue()).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        consumer.accept(currentValueText);

        return 0;
    }

    private int trySetDefaultOption(Consumer<Component> onSuccess, Consumer<Component> onFailure, ConfigFile file, String value) {
        JsonElement element;
        try {
            element = JsonParser.parseString(value);
        } catch (JsonSyntaxException exception) {
            onFailure.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_INVALID_JSON, value));
            return -2;
        }

        if (this.DEFAULT.setValueFrom(element, JsonOps.INSTANCE)) {
            onSuccess.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_WORLD_DEFAULT, this.NAME, value));
            file.writeFile();
            return 0;
        }

        onFailure.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_FAILED_WORLD_DEFAULT, this.NAME, value));
        return -1;
    }

    private int trySetPerWorldOption(Consumer<Component> onSuccess, Consumer<Component> onFailure, ConfigFile file, String value) {
        JsonElement element;
        try {
            element = JsonParser.parseString(value);
        } catch (JsonSyntaxException exception) {
            onFailure.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_INVALID_JSON, value));
            return -2;
        }

        if (this.PER_WORLD == null) {
            onFailure.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_PER_WORLD_NOT_EXIST, value));
            return -3;
        }

        if (this.PER_WORLD.setValueFrom(element, JsonOps.INSTANCE)) {
            onSuccess.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG, this.NAME, value));
            file.writeFile();
            return 0;
        }

        onFailure.accept(Component.translatable(NtmTranslations.COMMAND_SET_CONFIG_FAILED, this.NAME, value));
        return -1;
    }
}
