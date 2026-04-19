package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.api.explosion.custom.NtmBalefireExplosion;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class DebugWandItem extends Item {
    public DebugWandItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player user, @NonNull InteractionHand hand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockHitResult hitResult = (BlockHitResult) user.pick(256, 0, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            NtmBalefireExplosion.addExplosion(serverLevel, hitResult.getBlockPos(), 300);
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable(NtmTranslations.TOOLTIP_CREATIVE_ONLY).withStyle(ChatFormatting.GRAY));
        tooltip.accept(NtmTextUtil.tooltip(this).withStyle(ChatFormatting.GRAY));
    }
}
