package net.fawnoculus.ntm.client.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.MouseButtonEvent;
import org.jetbrains.annotations.NotNull;

public interface OnMouseClickEvent {
    Event<@NotNull OnMouseClickEvent> EVENT = EventFactory.of(
      (events) -> (client, click) -> {
          for (OnMouseClickEvent event : events) {
              if (event.onClick(client, click)) {
                  return true;
              }
          }
          return false;
      }
    );

    /**
     * @return if the following actions should be canceled or not
     */
    boolean onClick(Minecraft client, MouseButtonEvent click);
}
