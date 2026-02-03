package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.items.components.NtmConsumableComponents;
import net.fawnoculus.ntm.items.components.NtmFoodComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class VeganSchnitzelItem extends Item {
    public VeganSchnitzelItem(Properties settings) {
        super(settings.food(NtmFoodComponents.ALWAYS_EDIBLE, NtmConsumableComponents.VEGAN_SCHNITZEL));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, LivingEntity user) {
        user.setRemainingFireTicks(1200);
        return super.finishUsingItem(stack, world, user);
    }
}
