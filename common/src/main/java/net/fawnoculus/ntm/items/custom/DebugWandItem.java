package net.fawnoculus.ntm.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
    public @NonNull InteractionResult use(Level world, @NonNull Player user, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        BlockHitResult hitResult = (BlockHitResult) user.pick(256, 0, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            // TODO: simple explosion type testing
            //NTMExplosionTypes.SIMPLE.explode((ServerLevel) world, hitResult.getBlockPos(), 10f);
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable("tooltip.ntm.creative_only").withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.debug_wand").withStyle(ChatFormatting.GRAY));
    }
}
