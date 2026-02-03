package net.fawnoculus.ntm.blocks.custom.container.energy;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.menu.MenuRegistry;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.custom.HoverTooltipBlock;
import net.fawnoculus.ntm.blocks.entities.container.energy.SimpleEnergyStorageBE;
import net.fawnoculus.ntm.util.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.Consumer;

public class SimpleEnergyStorageBlock extends BaseEntityBlock implements HoverTooltipBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private final long MAX_ENERGY;

    public SimpleEnergyStorageBlock(Properties settings, long MaxEnergy) {
        super(settings);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
        this.MAX_ENERGY = MaxEnergy;
    }

    // Extra Constructor for the Codec
    private SimpleEnergyStorageBlock(Properties settings) {
        this(settings, 0);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(SimpleEnergyStorageBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        SimpleEnergyStorageBE energyStorageBE = new SimpleEnergyStorageBE(pos, state);
        energyStorageBE.energy.setMaxValue(this.MAX_ENERGY);
        return energyStorageBE;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        if (world.isClientSide()) return null;
        return createTickerHelper(type, NtmBlockEntities.SIMPLE_ENERGY_STORAGE_BE.get(), SimpleEnergyStorageBE::tick);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof SimpleEnergyStorageBE energyStorageBE)) {
            return InteractionResult.FAIL;
        }
        if (!(player instanceof ServerPlayer serverPlayer) || world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        MenuRegistry.openExtendedMenu(serverPlayer, energyStorageBE);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, @NonNull Level world, @NonNull BlockPos pos, @NonNull Block sourceBlock, @Nullable Orientation wireOrientation, boolean notify) {
        super.neighborChanged(state, world, pos, sourceBlock, wireOrientation, notify);

        if (world.getBlockEntity(pos) instanceof SimpleEnergyStorageBE simpleEnergyStorageBE) {
            simpleEnergyStorageBE.onBlockUpdate();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Player player = Objects.requireNonNull(context.getPlayer());
        context.getLevel().getBlockState(context.getClickedPos().above()).getBlock();
        return this.defaultBlockState().setValue(FACING, player.getDirection().getOpposite());
    }

    @Override
    public boolean shouldDisplayTooltip(Level world, BlockPos pos, BlockState state) {
        return world.getBlockEntity(pos) instanceof SimpleEnergyStorageBE;
    }

    @Override
    public void appendHoverTooltip(Level world, BlockPos pos, BlockState state, Consumer<Component> tooltip) {
        SimpleEnergyStorageBE energyStorageBE = (SimpleEnergyStorageBE) world.getBlockEntity(pos);
        assert energyStorageBE != null;
        long value = energyStorageBE.energy.getValue();
        Component valueText = TextUtil.unit(value);
        long maxValue = energyStorageBE.energy.getMaxValue();
        Component maxValueText = TextUtil.unit(maxValue, "generic.ntm.energy");
        int color = Mth.hsvToRgb(Math.max(0.0F, (float) value / (float) maxValue) / 3.0F, 1.0F, 1.0F);

        tooltip.accept(this.getName().withStyle(ChatFormatting.YELLOW));
        tooltip.accept(Component.translatable("generic.ntm.spaced_amount_stored", valueText, maxValueText).withStyle(ChatFormatting.WHITE));
        tooltip.accept(Component.literal(String.format("%1$,3.1f%%", 100.0 * value / maxValue)).withColor(color));
    }
}
