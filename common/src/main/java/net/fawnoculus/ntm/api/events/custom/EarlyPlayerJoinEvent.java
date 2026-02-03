package net.fawnoculus.ntm.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;

@FunctionalInterface
public interface EarlyPlayerJoinEvent {
    Event<EarlyPlayerJoinEvent> EVENT = EventFactory.createLoop();

    void onJoin(Connection connection, ServerPlayer player, CommonListenerCookie clientData);
}
