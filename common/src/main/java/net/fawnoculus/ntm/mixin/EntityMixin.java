package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.misc.data.CustomDataHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin implements CustomDataHolder {
    @Unique
    CompoundTag ntm$customData = new CompoundTag();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V"), method = "load")
    protected void readCustomData(ValueInput input, CallbackInfo ci) {
        Tag data = input.read(CustomDataHolder.KEY, CompoundTag.CODEC).orElse(new CompoundTag());
        if (data instanceof CompoundTag nbtCompound) {
            ntm$customData = nbtCompound;
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueOutput;)V"), method = "saveWithoutId")
    protected void writeCustomData(ValueOutput output, CallbackInfo ci) {
        output.store(CustomDataHolder.KEY, CompoundTag.CODEC, ntm$customData);
    }

    @Override
    public @NotNull CompoundTag ntm$getCustomData() {
        return ntm$customData;
    }

    @Override
    public void ntm$setCustomData(@NonNull CompoundTag customData) {
        ntm$customData = customData;
    }
}
