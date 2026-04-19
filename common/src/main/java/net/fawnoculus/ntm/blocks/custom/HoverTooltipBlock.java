package net.fawnoculus.ntm.blocks.custom;

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
        this.appendHoverTooltip(world, pos, state, tooltip::add);
        return tooltip;
    }

    void appendHoverTooltip(Level world, BlockPos pos, BlockState state, Consumer<Component> tooltip);
}
