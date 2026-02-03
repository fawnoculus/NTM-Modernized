package net.fawnoculus.ntm.client.api.events;

import net.fawnoculus.ntm.client.api.events.custom.ClientTickEvents;
import net.fawnoculus.ntm.client.api.events.custom.ConnectToServerEvent;
import net.fawnoculus.ntm.client.api.events.custom.DisconnectFromServerEvent;
import net.fawnoculus.ntm.client.api.events.custom.ResourceLoadingEvent;
import net.fawnoculus.ntm.client.api.messages.MessageSystem;
import net.fawnoculus.ntm.client.network.ClientReceivedVersionHandler;
import net.fawnoculus.ntm.client.render.NtmWavefrontModels;

public class NtmClientEvents {
    public static void init() {
        DisconnectFromServerEvent.EVENT.register(ClientReceivedVersionHandler::onDisconnect);
        DisconnectFromServerEvent.EVENT.register(ignored -> MessageSystem.removeAllMessages());

        ConnectToServerEvent.EVENT.register(ClientReceivedVersionHandler::onJoin);

        ClientTickEvents.EVENT.register(MessageSystem::onClientTick);

        ResourceLoadingEvent.LOAD_WAVEFRONT_MODELS.register(NtmWavefrontModels::loadModels);
        ResourceLoadingEvent.LOAD_WAVEFRONT_MODEL_TEXTURES.register(NtmWavefrontModels::loadModelTextures);
    }
}
