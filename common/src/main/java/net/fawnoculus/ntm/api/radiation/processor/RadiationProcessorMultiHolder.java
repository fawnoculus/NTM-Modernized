package net.fawnoculus.ntm.api.radiation.processor;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

import java.util.Collection;

public interface RadiationProcessorMultiHolder {
    static RadiationProcessorMultiHolder from(ServerLevel world) {
        return (RadiationProcessorMultiHolder) world;
    }

    Collection<RadiationProcessor> ntm$getRadiationProcessors();

    RadiationProcessor ntm$getRadiationProcessor(ChunkPos chunkPos);

    void ntm$addRadiationProcessor(RadiationProcessor processor, ChunkPos chunkPos);

    void ntm$removeRadiationProcessor(ChunkPos chunkPos);
}
