package net.fawnoculus.ntm.client.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent.ClientCommandSourceStack;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.api.config.ConfigFile;
import net.fawnoculus.ntm.api.config.ConfigOption;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.api.messages.MessageSystem;
import net.fawnoculus.ntm.client.render.hud.FlashBangRender;
import net.fawnoculus.ntm.client.render.hud.HudWigglerRender;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.fawnoculus.ntm.commands.NtmCommands;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ComponentArgument;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class NtmClientCommands {

    public static void init() {
        CommandRegistrationEvent.EVENT.register((dispatcher, buildContext, commandSelection) -> dispatcher.register(
            Commands.literal("ntm")
              .then(Commands.literal("config")
                .requires(NtmCommands::allowCommands)
                .then(NtmClientConfig.CLIENT_CONFIG_FILE.getCommand("client"))
              )
          )
        );

        ClientCommandRegistrationEvent.EVENT.register((dispatcher, buildContext) -> dispatcher.register(
          ClientCommandRegistrationEvent.literal("ntm_client")
            .then(ClientCommandRegistrationEvent.literal("config")
              .then(NtmClientConfig.CLIENT_CONFIG_FILE.getClientCommand("client"))
              .then(NtmConfig.COMMON_CONFIG_FILE.getClientCommand("common"))
              .then(NtmConfig.WORLD_CONFIG.defaultConfig().getClientCommand("world-default"))
            )
            .then(ClientCommandRegistrationEvent.literal("version")
              .executes(NtmClientCommands::version)
            )
            .then(ClientCommandRegistrationEvent.literal("messages")
              .then(ClientCommandRegistrationEvent.literal("add_message")
                .then(ClientCommandRegistrationEvent.argument("text", ComponentArgument.textComponent(buildContext))
                  .executes(context -> addMessage(
                      context,
                      context.getArgument("text", Component.class),
                      2000.0f,
                      Ntm.id("command_client")
                    )
                  )
                  .then(ClientCommandRegistrationEvent.argument("millis", FloatArgumentType.floatArg(0.0f, 1000000.0f))
                    .executes(context -> addMessage(
                        context,
                        context.getArgument("text", Component.class),
                        context.getArgument("millis", Float.class),
                        Ntm.id("command_client")
                      )
                    )
                    .then(ClientCommandRegistrationEvent.argument("identifier", IdentifierArgument.id())
                      .executes(context -> addMessage(
                          context,
                          context.getArgument("text", Component.class),
                          context.getArgument("millis", Float.class),
                          context.getArgument("identifier", Identifier.class)
                        )
                      )
                    )
                  )
                )
              )
              .then(ClientCommandRegistrationEvent.literal("remove_message")
                .then(ClientCommandRegistrationEvent.argument("identifier", IdentifierArgument.id())
                  .executes(context -> removeMessage(context, context.getArgument("identifier", Identifier.class)))
                )
              )
              .then(ClientCommandRegistrationEvent.literal("remove_all_messages")
                .executes(NtmClientCommands::clearMessages)
              )
              .then(ClientCommandRegistrationEvent.literal("enabled")
                .executes(NtmClientCommands::getMessagesEnabled)
                .then(ClientCommandRegistrationEvent.argument("enabled", BoolArgumentType.bool())
                  .executes(context -> setMessagesEnabled(context, context.getArgument("enabled", Boolean.class)))
                )
              )
            )
        ));

        if (NtmConfig.DEV_MODE.getValue()) {
            ClientCommandRegistrationEvent.EVENT.register((dispatcher, context) -> dispatcher.register(
              ClientCommandRegistrationEvent.literal("ntm_client")
                .then(ClientCommandRegistrationEvent.literal("dev")
                  .then(ClientCommandRegistrationEvent.literal("test")
                    .executes(NtmClientCommands::test)
                  )
                  .then(ClientCommandRegistrationEvent.literal("force_disconnect")
                    .executes(NtmClientCommands::forceDisconnect)
                  )
                  .then(ClientCommandRegistrationEvent.literal("force_quit")
                    .executes(NtmClientCommands::forceQuit)
                  )
                )
            ));
        }
    }

    private static int version(CommandContext<ClientCommandSourceStack> context) {
        context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.version.client", Ntm.MOD_VERSION), false);
        return 0;
    }

    private static int test(CommandContext<ClientCommandSourceStack> context) {
        final double wiggleAngle = (float) (context.getSource().arch$getPlayer().getRandom().nextDouble() * Math.PI * 2);
        final double wiggleIntensity = 20;

        HudWigglerRender.addVelocity(
          (float) (wiggleIntensity * Math.cos(wiggleAngle)),
          (float) (wiggleIntensity * Math.sin(wiggleAngle))
        );

        FlashBangRender.addFlash(1.5f, 0.01f);
        return 0;
    }

    private static int forceQuit(CommandContext<ClientCommandSourceStack> context) {
        ClientUtil.getClient().stop();
        return 0;
    }

    @SuppressWarnings("resource")
    private static int forceDisconnect(CommandContext<ClientCommandSourceStack> context) {
        context.getSource().arch$getLevel().disconnect(Component.translatable("message.ntm.force_disconnect"));
        ClientUtil.getClient().disconnectWithSavingScreen();
        ClientUtil.getClient().setScreen(new TitleScreen());
        return 0;
    }

    private static int clearMessages(CommandContext<ClientCommandSourceStack> context) {
        context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.cleared", MessageSystem.getAllMessages().size()), false);
        MessageSystem.removeAllMessages();
        return 0;
    }

    private static int removeMessage(CommandContext<ClientCommandSourceStack> context, Identifier identifier) {
        context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.removed", identifier.toString()), false);
        MessageSystem.removeAllMessages();
        return 0;
    }

    private static int addMessage(CommandContext<ClientCommandSourceStack> context, Component text, float millis, Identifier identifier) {
        MessageSystem.addMessage(new AdvancedMessage(identifier, text, millis));
        context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.added"), false);
        return 0;
    }

    private static int getMessagesEnabled(CommandContext<ClientCommandSourceStack> context) {
        boolean enabled = MessageSystem.getIsEnabled();
        if (enabled) {
            context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.is_enabled"), false);
        } else {
            context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.is_disabled"), false);
        }
        return 0;
    }

    private static int setMessagesEnabled(CommandContext<ClientCommandSourceStack> context, Boolean enabled) {
        MessageSystem.setIsEnabled(enabled);
        if (enabled) {
            context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.set_enabled"), false);
        } else {
            context.getSource().arch$sendSuccess(() -> Component.translatable("message.ntm.message_client.set_disabled"), false);
        }
        return 0;
    }


    private static int reloadConfig(CommandContext<ClientCommandSourceStack> context, ConfigFile file) {
        context.getSource().arch$sendSuccess(() -> Component.translatable("command.ntm.reload_configs", file.getSubPath()), false);
        file.readFile();
        return 0;
    }

    protected static <T> int getOptionInfo(CommandContext<ClientCommandSourceStack> context, ConfigOption<T> option) {
        Codec<T> codec = option.getCodec();
        T defaultValue = option.getDefaultValue();
        T currentValue = option.getValue();

        Component nameText = Component.translatable("command.ntm.get_option_info.name").withStyle(ChatFormatting.BLUE)
          .append(Component.literal(option.getName()).withStyle(ChatFormatting.WHITE));
        context.getSource().arch$sendSuccess(() -> nameText, false);

        if (option.getComment() != null) {
            Component commentText = Component.translatable("command.ntm.get_option_info.comment").withStyle(ChatFormatting.YELLOW)
              .append(Component.literal(option.getComment()).withStyle(ChatFormatting.WHITE));
            context.getSource().arch$sendSuccess(() -> commentText, false);
        }

        Component defaultValueText = Component.translatable("command.ntm.get_option_info.default").withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(codec.encodeStart(JsonOps.INSTANCE, defaultValue).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        context.getSource().arch$sendSuccess(() -> defaultValueText, false);

        Component currentValueText = Component.translatable("command.ntm.get_option_info.current_value").withStyle(ChatFormatting.YELLOW)
          .append(Component.literal(codec.encodeStart(JsonOps.INSTANCE, currentValue).getOrThrow().toString()).withStyle(ChatFormatting.WHITE));
        context.getSource().arch$sendSuccess(() -> currentValueText, false);
        return 0;
    }

    private static int trySetOption(CommandContext<ClientCommandSourceStack> context, ConfigFile file, ConfigOption<?> option, String value) {
        JsonElement element;
        try {
            element = JsonParser.parseString(value);
        } catch (JsonSyntaxException exception) {
            context.getSource().arch$sendFailure(Component.translatable("command.ntm.set_config_value.invalid_json", value));
            return -2;
        }

        if (option.setValueFrom(element, JsonOps.INSTANCE)) {
            context.getSource().arch$sendSuccess(() -> Component.translatable("command.ntm.set_config_value", option.getName(), value), false);
            file.writeFile();
            return 0;
        }

        context.getSource().arch$sendFailure(Component.translatable("command.ntm.set_config_value.failed", option.getName(), value));
        return -1;
    }
}
