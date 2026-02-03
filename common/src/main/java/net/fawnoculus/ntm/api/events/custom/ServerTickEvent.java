package net.fawnoculus.ntm.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.server.MinecraftServer;

import java.util.function.BooleanSupplier;

@FunctionalInterface
public interface ServerTickEvent {
    Event<ServerTickEvent> PRE_TICK = EventFactory.of(
      (events) -> (server, shouldKeepTicking, tickStartNanoTime) -> {
          for (ServerTickEvent event : events) {
              event.onTick(server, shouldKeepTicking, tickStartNanoTime);
          }
      }
    );

    Event<ServerTickEvent> POST_TICK = EventFactory.of(
      (events) -> (server, shouldKeepTicking, tickStartNanoTime) -> {
          for (ServerTickEvent event : events) {
              event.onTick(server, shouldKeepTicking, tickStartNanoTime);
          }
      }
    );


    void onTick(MinecraftServer server, BooleanSupplier shouldKeepTicking, long tickStartNanoTime);
}
