package net.fawnoculus.ntm.client.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.protocol.login.ClientboundLoginFinishedPacket;

public interface ConnectToServerEvent {
    Event<ConnectToServerEvent> EVENT = EventFactory.createLoop();

    void onJoin(ClientHandshakePacketListenerImpl listener, ClientboundLoginFinishedPacket packet);
}
