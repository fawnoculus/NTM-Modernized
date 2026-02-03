package net.fawnoculus.ntm.api.radiation.processor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public interface RadiationProcessor {
    void tick();

    double getPassiveRadiation(Vec3 pos);

    double getActiveRadiation(Vec3 pos);

    default double getPassiveRadiation(Vec3i pos) {
        return getPassiveRadiation(new Vec3(pos));
    }

    default double getActiveRadiation(Vec3i pos) {
        return getPassiveRadiation(new Vec3(pos));
    }

    void onChangeBlock(BlockState newState, BlockState previousState, BlockPos pos);

    void writeData(CompoundTag data);

    void readData(CompoundTag data);
}
