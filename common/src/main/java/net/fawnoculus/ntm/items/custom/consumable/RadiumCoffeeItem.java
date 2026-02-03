package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class RadiumCoffeeItem extends CoffeeItem {
    public RadiumCoffeeItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide() && !user.isInvulnerable() && !user.hasInfiniteMaterials()) {
            RadiationManager.increaseRadiationExposure(user, 500_000);
        }
        return super.finishUsingItem(stack, world, user);
    }
}
