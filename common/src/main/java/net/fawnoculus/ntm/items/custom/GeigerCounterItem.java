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

public class GeigerCounterItem extends Item {
    public GeigerCounterItem(Properties settings) {
        super(settings);
    }

    private Component getRadsText(double milliRads) {
        if (milliRads > 1_000_000) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.DARK_GRAY);
        }
        if (milliRads > 100_000) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.DARK_RED);
        }
        if (milliRads > 10_000) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.RED);
        }
        if (milliRads > 1_000) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRads > 0) {
            return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.translatable("generic.ntm.radiation.rad_s", String.format("%.1f", milliRads / 1000)).withStyle(ChatFormatting.GREEN);
    }

    private Component getRadText(double milliRad) {
        if (milliRad > 900_000) {
            return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.DARK_GRAY);
        }
        if (milliRad > 800_000) {
            return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.DARK_RED);
        }
        if (milliRad > 600_000) {
            return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.RED);
        }
        if (milliRad > 400_000) {
            return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.GOLD);
        }
        if (milliRad > 200_000) {
            return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.YELLOW);
        }
        return Component.translatable("generic.ntm.radiation.rad", String.format("%.1f", milliRad / 1000)).withStyle(ChatFormatting.GREEN);
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

        user.displayClientMessage(Component.translatable("message.ntm.geiger_counter").withStyle(ChatFormatting.GOLD), false);
        user.displayClientMessage(Component.translatable("message.ntm.radiation.chunk_radiation").append(getRadsText(chunkRadiation)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable("message.ntm.radiation.environmental_radiation").append(getRadsText(totalRadiation)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable("message.ntm.radiation.player_contamination").append(getRadText(playerContamination)).withStyle(ChatFormatting.YELLOW), false);
        user.displayClientMessage(Component.translatable("message.ntm.radiation.player_resistance").append(player_resistance).withStyle(ChatFormatting.YELLOW), false);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        // TODO: make it make noise
        super.inventoryTick(stack, world, entity, slot);
    }
}
