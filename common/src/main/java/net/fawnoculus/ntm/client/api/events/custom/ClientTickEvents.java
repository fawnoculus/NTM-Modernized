package net.fawnoculus.ntm.client.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.client.Minecraft;

/**
 * This Event is called every time the Client draws a new Frame
 */
@FunctionalInterface
public interface ClientTickEvents {
    Event<ClientTickEvents> EVENT = EventFactory.createLoop();


    void onTick(Minecraft client);
}
