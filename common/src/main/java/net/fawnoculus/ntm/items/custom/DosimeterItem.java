package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class DosimeterItem extends Item {
    public DosimeterItem(Properties settings) {
        super(settings);
    }

    private Component getRadsText(double milliRads) {
        if (milliRads > 3_600) {
            return Component.translatable("generic.ntm.radiation.rad_s", ">3.6").withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 1_000) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 0) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.GREEN);
    }

    @Override
    public @NonNull InteractionResult use(Level world, @NonNull Player user, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        double totalRadiation = RadiationManager.getTotalRadiation(user);

        user.displayClientMessage(Component.translatable("message.ntm.dosimeter").withStyle(ChatFormatting.GOLD), false);
        user.displayClientMessage(Component.translatable("message.ntm.radiation.environmental_radiation").append(getRadsText(totalRadiation)).withStyle(ChatFormatting.YELLOW), false);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        // TODO: make it make noise
        super.inventoryTick(stack, world, entity, slot);
    }
}
