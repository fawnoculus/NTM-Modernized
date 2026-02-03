package net.fawnoculus.ntm.client.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;

public interface ResourceLoadingEvent {
    Event<ResourceLoadingEvent> LOAD_WAVEFRONT_MODELS = EventFactory.createLoop();

    Event<ResourceLoadingEvent> LOAD_WAVEFRONT_MODEL_TEXTURES = EventFactory.createLoop();

    void load();
}
