package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.items.components.NtmFoodComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class TemFlakesItem extends Item {
    public TemFlakesItem(Properties settings) {
        super(settings.food(NtmFoodComponents.ALWAYS_EDIBLE));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, LivingEntity user) {
        user.heal(2);
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5)).withStyle(ChatFormatting.GRAY));
    }
}
