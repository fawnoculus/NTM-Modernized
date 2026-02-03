package net.fawnoculus.ntm.items.custom.consumable;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class CoffeeItem extends Item {
    public CoffeeItem(Properties settings) {
        super(settings.component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, LivingEntity user) {
        user.heal(10);
        user.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 2, false, false, true));
        return super.finishUsingItem(stack, world, user);
    }
}
