package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.misc.data.CustomDataHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.PalettedContainerFactory;
import net.minecraft.world.level.chunk.storage.SerializableChunkData;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SerializableChunkData.class)
public class SerializableChunkDataMixin implements CustomDataHolder {
    @Unique
    CompoundTag ntm$customData = new CompoundTag();

    @Inject(method = "parse", at = @At(value = "RETURN"))
    private static void earlyJoin(
      LevelHeightAccessor levelHeightAccessor,
      PalettedContainerFactory palettedContainerFactory,
      CompoundTag tag,
      CallbackInfoReturnable<SerializableChunkData> cir
    ) {
        CustomDataHolder.from(cir.getReturnValue()).ntm$setCustomData(tag.getCompoundOrEmpty(CustomDataHolder.KEY));
    }

    @Override
    public @NotNull CompoundTag ntm$getCustomData() {
        return this.ntm$customData;
    }

    @Override
    public void ntm$setCustomData(@NonNull CompoundTag customData) {
        this.ntm$customData = customData;
    }

    @Inject(method = "write", at = @At(value = "RETURN"))
    private void writeCustomData(CallbackInfoReturnable<CompoundTag> cir) {
        cir.getReturnValue().put(CustomDataHolder.KEY, ntm$customData);
    }
}
