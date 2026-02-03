package net.fawnoculus.ntm.blocks.custom;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.menu.MenuRegistry;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.entities.ElectricFurnaceBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ElectricFurnaceBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public ElectricFurnaceBlock(Properties settings) {
        super(settings);
        registerDefaultState(this.defaultBlockState()
          .setValue(LIT, false)
          .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ElectricFurnaceBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new ElectricFurnaceBE(pos, state);
    }

    @Override
    protected void affectNeighborsAfterRemoval(@NotNull BlockState state, ServerLevel world, @NonNull BlockPos pos, boolean moved) {
        world.updateNeighbourForOutputSignal(pos, this);
        super.affectNeighborsAfterRemoval(state, world, pos, moved);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        if (world.isClientSide()) return null;
        return createTickerHelper(type, NtmBlockEntities.ELECTRIC_FURNACE_BE.get(), ElectricFurnaceBE::tick);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, @NotNull Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof ElectricFurnaceBE electricFurnaceBE)) {
            return InteractionResult.FAIL;
        }
        if (!(player instanceof ServerPlayer serverPlayer) || world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        MenuRegistry.openExtendedMenu(serverPlayer, electricFurnaceBE);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null) {
            return this.defaultBlockState().setValue(FACING, context.getPlayer().getDirection().getOpposite());
        }
        return this.defaultBlockState();
    }

    @Override
    public void animateTick(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull RandomSource random) {
        if (!world.isClientSide()) {
            return;
        }

        if (!state.getValue(LIT)) {
            return;
        }


        double x = pos.getX();
        double y = pos.getY() + 0.1 + random.nextDouble() * 0.4;
        double z = pos.getZ();
        switch (state.getValue(AlloyFurnaceBlock.FACING)) {
            case Direction.NORTH -> {
                x += 0.3 + random.nextDouble() * 0.4;
                z -= 0.1;
            }
            case Direction.EAST -> {
                x += 1.1;
                z += 0.3 + random.nextDouble() * 0.4;
            }
            case Direction.WEST -> {
                x -= 0.1;
                z += 0.3 + random.nextDouble() * 0.4;
            }
            case Direction.SOUTH -> {
                x += 0.3 + random.nextDouble() * 0.4;
                z += 1.1;
            }
        }
        world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
    }
}
