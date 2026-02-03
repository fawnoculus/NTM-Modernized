package net.fawnoculus.ntm.api.radiation.processor;

import net.minecraft.world.level.chunk.LevelChunk;

public interface RadiationProcessorHolder {
    static RadiationProcessorHolder from(LevelChunk chunk) {
        return (RadiationProcessorHolder) chunk;
    }

    RadiationProcessor ntm$getRadiationProcessor();

    void ntm$setRadiationProcessor(RadiationProcessor processor);
}
