package net.fawnoculus.ntm.api.radiation.processor;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * A Radiation Processor that does absolutely nothing.
 */
public class EmptyRadiationProcessor implements RadiationProcessor {
    @Override
    public void tick() {
    }

    @Override
    public double getPassiveRadiation(Vec3 pos) {
        return 0;
    }

    @Override
    public double getActiveRadiation(Vec3 pos) {
        return 0;
    }

    @Override
    public void onChangeBlock(BlockState newState, BlockState previousState, BlockPos pos) {
    }

    @Override
    public void writeData(CompoundTag data) {
    }

    @Override
    public void readData(CompoundTag data) {
    }
}
