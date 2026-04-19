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

public class GeigerCounterItem extends Item {
    public GeigerCounterItem(Properties settings) {
        super(settings);
    }

    private Component getRadsText(double milliRads) {
        if (milliRads > 1_000_000) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.DARK_GRAY);
        }
        if (milliRads > 100_000) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.DARK_RED);
        }
        if (milliRads > 10_000) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.RED);
        }
        if (milliRads > 1_000) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 0) {
            return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.literal("%.1f ".formatted(milliRads / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.GREEN);
    }

    private Component getRadText(double milliRad) {
        if (milliRad > 900_000) {
            return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.DARK_GRAY);
        }
        if (milliRad > 800_000) {
            return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.DARK_RED);
        }
        if (milliRad > 600_000) {
            return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.RED);
        }
        if (milliRad > 400_000) {
            return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRad > 200_000) {
            return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.literal("%.1f ".formatted(milliRad / 1000)).append(Component.translatable(NtmTranslations.GENERIC_RAD)).withStyle(ChatFormatting.GREEN);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, @NonNull Player user, @NonNull InteractionHand hand) {
        if (!(world instanceof ServerLevel serverWorld)) {
            return InteractionResult.SUCCESS;
        }

        double chunkRadiation = RadiationManager.getChunkRadiation(serverWorld, user.position());
        double totalRadiation = RadiationManager.getTotalRadiation(user);
        double playerContamination = RadiationManager.getRadiationExposure(user);
        double playerResistance = RadiationManager.getRadiationResistance(user);
        double playerResistancePercentage = RadiationManager.getRadiationResistancePercentage(user);

        Component player_resistance = Component.literal(String.format("%.1f%% (%.1f)", playerResistancePercentage, playerResistance)).withStyle(ChatFormatting.WHITE);

        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_GEIGER_COUNTER).withStyle(ChatFormatting.GOLD), false);
        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_CHUNK_RADIATION).append(getRadsText(chunkRadiation)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_ENVIRONMENTAL_RADIATION).append(getRadsText(totalRadiation)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_PLAYER_CONTAMINATION).append(getRadText(playerContamination)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_PLAYER_RESISTANCE).append(player_resistance).withStyle(ChatFormatting.YELLOW), false);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        // TODO: make it make noise
        super.inventoryTick(stack, world, entity, slot);
    }
}
