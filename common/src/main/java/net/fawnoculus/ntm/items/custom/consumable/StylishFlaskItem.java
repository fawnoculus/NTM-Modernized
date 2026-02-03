package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class StylishFlaskItem extends Item {
    private static final int COOLDOWN_PER_USAGE = 3600;

    public StylishFlaskItem(Properties settings) {
        super(settings
          .component(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0)
          .component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK)
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        int cooldown = stack.getOrDefault(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0);
        if (cooldown > 0) {
            stack.set(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), cooldown - 1);
        }
    }

    @Override
    public @NonNull InteractionResult use(Level world, @NonNull Player user, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return super.use(world, user, hand);
        }
        ItemStack stack = user.getItemInHand(hand);
        int cooldown = stack.getOrDefault(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0);
        if (cooldown > 0) {
            return InteractionResult.FAIL;
        }
        return super.use(world, user, hand);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, @NonNull Level world, LivingEntity user) {
        user.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0, false, false, true));
        user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 600, 2, false, false, true));
        stack.set(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), COOLDOWN_PER_USAGE);
        ItemStack copy = stack.copy();
        super.finishUsingItem(stack, world, user);
        return copy;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getOrDefault(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int cooldown = stack.getOrDefault(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0);
        return Mth.clamp(Math.round(13.0F - cooldown * 13.0F / COOLDOWN_PER_USAGE), 0, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        int cooldown = stack.getOrDefault(NtmDataComponentTypes.COOLDOWN_COMPONENT_TYPE.get(), 0);
        float f = Math.max(0.0F, ((float) COOLDOWN_PER_USAGE - cooldown) / (float) COOLDOWN_PER_USAGE);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5) + 1).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5) + 2).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5) + 3).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.literal(""));
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5) + 4).withStyle(ChatFormatting.GRAY));
    }
}
