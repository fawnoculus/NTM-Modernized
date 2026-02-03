package net.fawnoculus.ntm.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceExtensionBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;


public class AlloyFurnaceExtensionBlock extends Block implements EntityBlock {
    public AlloyFurnaceExtensionBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new AlloyFurnaceExtensionBE(pos, state);
    }

    @Override
    protected @NonNull MapCodec<AlloyFurnaceExtensionBlock> codec() {
        return simpleCodec(AlloyFurnaceExtensionBlock::new);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, @NonNull BlockState state, @Nullable LivingEntity placer, @NonNull ItemStack itemStack) {
        BlockState bellowState = world.getBlockState(pos.below());
        if (bellowState.getBlock() != NtmBlocks.ALLOY_FURNACE) return;

        bellowState = bellowState.setValue(AlloyFurnaceBlock.EXTENSION, true);
        world.setBlockAndUpdate(pos.below(), bellowState);
    }

    @Override
    public @NonNull BlockState playerWillDestroy(Level world, BlockPos pos, @NonNull BlockState state, @NonNull Player player) {
        BlockState bellowState = world.getBlockState(pos.below());
        if (bellowState.getBlock() != NtmBlocks.ALLOY_FURNACE)
            return super.playerWillDestroy(world, pos, state, player);

        bellowState = bellowState.setValue(AlloyFurnaceBlock.EXTENSION, false);
        world.setBlockAndUpdate(pos.below(), bellowState);
        return super.playerWillDestroy(world, pos, state, player);
    }
}
