package net.fawnoculus.ntm.api.events.custom;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;

public interface LevelChunkSaveEvent {
    Event<LevelChunkSaveEvent> SAVE = EventFactory.createLoop();
    Event<LevelChunkSaveEvent> LOAD = EventFactory.createLoop();

    void onSaveEvent(ServerLevel level, LevelChunk levelChunk, CompoundTag compoundTag);
}
