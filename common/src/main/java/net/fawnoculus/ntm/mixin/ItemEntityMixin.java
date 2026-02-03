package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.items.custom.DangerousDrop;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow
    private int age;

    public ItemEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getItem();

    @Inject(method = "tick", at = @At("HEAD"))
    private void removeWorldSpecificConfig(CallbackInfo ci) {
        if (this.getItem().getItem() instanceof DangerousDrop dangerousDrop
          && this.level() instanceof ServerLevel serverWorld
          && this.level().getEntity(this.uuid) instanceof ItemEntity itemEntity
        ) {
            if (this.age == 0) {
                dangerousDrop.onDropped(serverWorld, this.position(), itemEntity);
            }
            if (this.onGround()) {
                dangerousDrop.onTouchBlock(serverWorld, this.position(), itemEntity);
            }
        }
    }
}
