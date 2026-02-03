package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.misc.data.CustomDataHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChunkAccess.class)
public class ChunkAccessMixin implements CustomDataHolder {
    @Unique
    CompoundTag ntm$customData = new CompoundTag();

    @Override
    public @NotNull CompoundTag ntm$getCustomData() {
        return this.ntm$customData;
    }

    @Override
    public void ntm$setCustomData(@NonNull CompoundTag customData) {
        this.ntm$customData = customData;
    }
}
