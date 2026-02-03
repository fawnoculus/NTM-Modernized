package net.fawnoculus.ntm.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.fawnoculus.ntm.blocks.entities.container.energy.EnergyConnectorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class TempCableBlock extends BaseEntityBlock {
    public TempCableBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(TempCableBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new EnergyConnectorBE(pos, state);
    }
}
