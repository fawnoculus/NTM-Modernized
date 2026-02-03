package net.fawnoculus.ntm.client.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;

public interface DisconnectFromServerEvent {
    Event<DisconnectFromServerEvent> EVENT = EventFactory.createLoop();

    void onJoin(ClientHandshakePacketListenerImpl listener);
}
