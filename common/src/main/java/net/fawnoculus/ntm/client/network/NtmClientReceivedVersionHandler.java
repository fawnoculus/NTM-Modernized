package net.fawnoculus.ntm.client.network;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.client.NtmClient;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.network.s2c.NtmVersionPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.login.ClientboundLoginFinishedPacket;

public class NtmClientReceivedVersionHandler {
    public static final String clientVersion = Ntm.MOD_VERSION;
    public static boolean hasReceivedVersion = false;
    public static String serverVersion = null;

    public static void handlePacket(NtmVersionPayload payload, NetworkManager.PacketContext ignored) {
        hasReceivedVersion = true;
        serverVersion = payload.version();
    }

    public static void onJoin(ClientHandshakePacketListenerImpl ignored, ClientboundLoginFinishedPacket ignored2) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        if (!hasReceivedVersion) {
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_NOT_INSTALLED_1), false);
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_NOT_INSTALLED_2), false);
            NtmClient.LOGGER.info("Connected Server doesn't of {} installed", Ntm.MOD_NAME);
            return;
        }

        NtmClient.LOGGER.info("Connected Server has Version {} of {} installed", serverVersion, Ntm.MOD_NAME);
        if (!serverVersion.equals(clientVersion)) {
            NtmClient.LOGGER.warn("Version Mismatch, Client has Version {} of {}", clientVersion, Ntm.MOD_NAME);
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_MISMATCH_1), false);
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_MISMATCH_2), false);
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_SERVER, serverVersion), false);
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_VERSION_CLIENT, clientVersion), false);
        }
    }

    public static void onDisconnect(ClientHandshakePacketListenerImpl ignored) {
        hasReceivedVersion = false;
        serverVersion = null;
    }
}
