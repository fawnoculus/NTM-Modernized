package net.fawnoculus.ntm.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.NtmBlockProperties;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;


public class AlloyFurnaceBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty EXTENSION = NtmBlockProperties.EXTENSION;

    public AlloyFurnaceBlock(Properties settings) {
        super(settings);
        registerDefaultState(this.defaultBlockState()
          .setValue(LIT, false)
          .setValue(EXTENSION, false)
          .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(AlloyFurnaceBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new AlloyFurnaceBE(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        if (world.isClientSide()) return null;
        return createTickerHelper(type, NtmBlockEntities.ALLOY_FURNACE_BE.get(), AlloyFurnaceBE::tick);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof AlloyFurnaceBE alloyFurnaceBE)) {
            return InteractionResult.FAIL;
        }
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        player.openMenu(alloyFurnaceBE);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(LIT);
        builder.add(EXTENSION);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() == null) return this.defaultBlockState();
        if (context.getLevel().getBlockState(context.getClickedPos().above()).getBlock() instanceof AlloyFurnaceExtensionBlock) {
            return this.defaultBlockState()
              .setValue(FACING, context.getPlayer().getDirection().getOpposite())
              .setValue(EXTENSION, true);
        }
        return this.defaultBlockState().setValue(FACING, context.getPlayer().getDirection().getOpposite());
    }

    @Override
    public void animateTick(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!world.isClientSide()) {
            return;
        }

        if (!state.getValue(LIT)) {
            return;
        }

        Vec3 centerPos = pos.getCenter();
        double y = centerPos.y() + 0.5;
        double x = centerPos.x();
        double z = centerPos.z();
        if (state.getValue(EXTENSION)) y += 1;
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);

        x = centerPos.x();
        y = centerPos.y() - 0.2 + random.nextDouble() * 0.4;
        z = centerPos.z();
        switch (state.getValue(FACING)) {
            case Direction.NORTH -> {
                x += 0.2;
                x -= random.nextDouble() * 0.4;
                z -= 0.6;
            }
            case Direction.SOUTH -> {
                x -= 0.2;
                x += random.nextDouble() * 0.4;
                z += 0.6;
            }
            case Direction.EAST -> {
                z -= 0.2;
                z += random.nextDouble() * 0.4;
                x += 0.6;
            }
            case Direction.WEST -> {
                z += 0.2;
                z -= random.nextDouble() * 0.4;
                x -= 0.6;
            }
        }
        world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
    }
}
