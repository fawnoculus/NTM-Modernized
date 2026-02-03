package net.fawnoculus.ntm.api.multiblock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class MultiBlockItem extends BlockItem {
    private final MultiBlock MULTI_BLOCK;

    public MultiBlockItem(Block block, Properties settings) {
        super(block, settings);

        if (block instanceof MultiBlockOrigin origin) {
            this.MULTI_BLOCK = origin.getMultiBlock();
        } else {
            throw new IllegalArgumentException("Block must be a multi Block Origin");
        }
    }

    public MultiBlockItem(Block block, Properties settings, MultiBlock multiBlock) {
        super(block, settings);

        this.MULTI_BLOCK = multiBlock;
    }

    @Override
    protected boolean canPlace(@NonNull BlockPlaceContext context, @NonNull BlockState state) {
        return super.canPlace(context, state)
          && MULTI_BLOCK.canPlaceAt(context.getLevel(), context.getClickedPos(), context.getHorizontalDirection());
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, @NonNull BlockState state) {
        MULTI_BLOCK.placeAt(context.getLevel(), context.getClickedPos(), context.getHorizontalDirection());
        return super.placeBlock(context, state);
    }
}
