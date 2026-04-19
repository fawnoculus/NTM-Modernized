package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.fawnoculus.ntm.misc.NtmTranslations;
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
            return Component.literal(">3.6").append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 1_000) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 0) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.GREEN);
    }

    @Override
    public @NonNull InteractionResult use(Level world, @NonNull Player user, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        double totalRadiation = RadiationManager.getTotalRadiation(user);

        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_DOSIMETER).withStyle(ChatFormatting.GOLD), false);
        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_ENVIRONMENTAL_RADIATION).append(getRadsText(totalRadiation)).withStyle(ChatFormatting.YELLOW), false);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        // TODO: make it make noise
        super.inventoryTick(stack, world, entity, slot);
    }
}
