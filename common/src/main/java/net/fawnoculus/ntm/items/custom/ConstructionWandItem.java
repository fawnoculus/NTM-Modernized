package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class ConstructionWandItem extends Item {
    public ConstructionWandItem(Properties settings) {
        super(settings.component(NtmDataComponentTypes.BLOCK_STATE_COMPONENT_TYPE.get(), Blocks.AIR.defaultBlockState()));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        assert context.getPlayer() != null;
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        ServerLevel world = (ServerLevel) context.getLevel();

        if (player.isShiftKeyDown()) {
            stack.set(NtmDataComponentTypes.BLOCK_STATE_COMPONENT_TYPE.get(), world.getBlockState(context.getClickedPos()));
            player.displayClientMessage(Component.translatable(
              "message.ntm.construction_wand.set_block",
              world.getBlockState(context.getClickedPos()).getBlock().getName()
            ), false);
            return InteractionResult.SUCCESS_SERVER;
        }

        BlockPos selectedPos = getBlockPos(stack);
        if (selectedPos == null) {
            stack.set(NtmDataComponentTypes.BLOCK_POS_COMPONENT_TYPE.get(), context.getClickedPos());
            player.displayClientMessage(Component.translatable(
              "message.ntm.construction_wand.set_pos",
              context.getClickedPos().getX(),
              context.getClickedPos().getY(),
              context.getClickedPos().getZ()
            ), false);
            return InteractionResult.SUCCESS_SERVER;
        }

        int greaterX = Math.max(selectedPos.getX(), context.getClickedPos().getX());
        int lesserX = Math.min(selectedPos.getX(), context.getClickedPos().getX());
        int greaterY = Math.max(selectedPos.getY(), context.getClickedPos().getY());
        int lesserY = Math.min(selectedPos.getY(), context.getClickedPos().getY());
        int greaterZ = Math.max(selectedPos.getZ(), context.getClickedPos().getZ());
        int lesserZ = Math.min(selectedPos.getZ(), context.getClickedPos().getZ());
        int blocksToFill = (greaterX - lesserX) * (greaterY - lesserY) * (greaterZ - lesserZ);

        player.displayClientMessage(Component.translatable("message.ntm.construction_wand.filling", blocksToFill), false);

        for (int x = lesserX; x <= greaterX; x++) {
            for (int y = lesserY; y <= greaterY; y++) {
                for (int z = lesserZ; z <= greaterZ; z++) {
                    world.setBlockAndUpdate(new BlockPos(x, y, z), getBlockState(stack));
                }
            }
        }
        stack.set(NtmDataComponentTypes.BLOCK_POS_COMPONENT_TYPE.get(), null);
        player.displayClientMessage(Component.translatable("message.ntm.construction_wand.filled", blocksToFill), false);
        return InteractionResult.SUCCESS_SERVER;
    }

    private BlockState getBlockState(ItemStack stack) {
        return stack.getOrDefault(NtmDataComponentTypes.BLOCK_STATE_COMPONENT_TYPE.get(), Blocks.AIR.defaultBlockState());
    }

    @Nullable
    private BlockPos getBlockPos(ItemStack stack) {
        return stack.get(NtmDataComponentTypes.BLOCK_POS_COMPONENT_TYPE.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable("tooltip.ntm.creative_only").withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.construction_wand1").withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.construction_wand2").withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.construction_wand3").withStyle(ChatFormatting.GRAY));
        BlockPos pos = getBlockPos(stack);
        if (pos != null) {
            tooltip.accept(Component.translatable("tooltip.ntm.construction_wand.pos", pos.getX(), pos.getY(), pos.getZ()).withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.accept(Component.translatable("tooltip.ntm.construction_wand.no_pos").withStyle(ChatFormatting.GRAY));
        }
        String blockIdentifier = BuiltInRegistries.BLOCK.wrapAsHolder(getBlockState(stack).getBlock()).getRegisteredName();
        tooltip.accept(Component.translatable("tooltip.ntm.construction_wand.block", blockIdentifier).withStyle(ChatFormatting.GRAY));
    }
}
