package net.fawnoculus.ntm.misc.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.storage.SerializableChunkData;
import org.jetbrains.annotations.NotNull;

public interface CustomDataHolder {
    String KEY = "ntm.custom_data";

    static CustomDataHolder from(ChunkAccess chunk) {
        return (CustomDataHolder) chunk;
    }

    static CustomDataHolder from(Entity entity) {
        return (CustomDataHolder) entity;
    }

    static CustomDataHolder from(SerializableChunkData data) {
        return (CustomDataHolder) (Object) data;
    }

    @NotNull CompoundTag ntm$getCustomData();

    void ntm$setCustomData(@NotNull CompoundTag customData);
}
