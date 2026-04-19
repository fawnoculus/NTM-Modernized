package net.fawnoculus.ntm.client.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent.ClientCommandSourceStack;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.api.messages.MessageSystem;
import net.fawnoculus.ntm.client.render.hud.FlashBangRender;
import net.fawnoculus.ntm.client.render.hud.HudWigglerRender;
import net.fawnoculus.ntm.client.util.NtmClientUtil;
import net.fawnoculus.ntm.commands.NtmCommands;
import net.fawnoculus.ntm.misc.NtmTranslations;
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
              .then(NtmConfig.WORLD_CONFIG.defaultConfig().getClientCommand("level-default"))
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
        context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_VERSION_CLIENT, Ntm.MOD_VERSION), false);
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
        NtmClientUtil.getClient().stop();
        return 0;
    }

    @SuppressWarnings("resource")
    private static int forceDisconnect(CommandContext<ClientCommandSourceStack> context) {
        context.getSource().arch$getLevel().disconnect(Component.translatable(NtmTranslations.MESSAGE_FORCE_DISCONNECT));
        NtmClientUtil.getClient().disconnectWithSavingScreen();
        NtmClientUtil.getClient().setScreen(new TitleScreen());
        return 0;
    }

    private static int clearMessages(CommandContext<ClientCommandSourceStack> context) {
        context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_IS_DISABLED, MessageSystem.getAllMessages().size()), false);
        MessageSystem.removeAllMessages();
        return 0;
    }

    private static int removeMessage(CommandContext<ClientCommandSourceStack> context, Identifier identifier) {
        context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_REMOVED, identifier.toString()), false);
        MessageSystem.removeAllMessages();
        return 0;
    }

    private static int addMessage(CommandContext<ClientCommandSourceStack> context, Component text, float millis, Identifier identifier) {
        MessageSystem.addMessage(new AdvancedMessage(identifier, text, millis));
        context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_ADDED), false);
        return 0;
    }

    private static int getMessagesEnabled(CommandContext<ClientCommandSourceStack> context) {
        boolean enabled = MessageSystem.getIsEnabled();
        if (enabled) {
            context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_IS_ENABLED), false);
        } else {
            context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_IS_DISABLED), false);
        }
        return 0;
    }

    private static int setMessagesEnabled(CommandContext<ClientCommandSourceStack> context, Boolean enabled) {
        MessageSystem.setIsEnabled(enabled);
        if (enabled) {
            context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_DET_ENABLED), false);
        } else {
            context.getSource().arch$sendSuccess(() -> Component.translatable(NtmTranslations.MESSAGE_MESSAGE_CLIENT_SET_DISABLED), false);
        }
        return 0;
    }
}
