package net.fawnoculus.ntm.blocks.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface HoverTooltipBlock {
    default boolean shouldDisplayTooltip(Level world, BlockPos pos, BlockState state) {
        return true;
    }

    default List<Component> getTooltip(Level world, BlockPos pos, BlockState state) {
        List<Component> tooltip = new ArrayList<>();
        try {
            this.appendHoverTooltip(world, pos, state, tooltip::add);
        } catch (Throwable throwable) {
            tooltip.add(Component.translatable("error.ntm.while.block_hover_tooltip").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("error.ntm.exception", Component.literal(throwable.toString()).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED));
        }

        return tooltip;
    }

    /**
     * You can throw Exceptions in here, it will be handled without crashing the game
     */
    void appendHoverTooltip(Level world, BlockPos pos, BlockState state, Consumer<Component> tooltip);
}
