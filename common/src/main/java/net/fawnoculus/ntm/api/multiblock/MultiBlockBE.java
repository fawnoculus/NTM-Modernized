package net.fawnoculus.ntm.api.multiblock;

import net.minecraft.core.BlockPos;

public interface MultiBlockBE {
    BlockPos getMultiblockOrigin();

    void setMultiblockOrigin(BlockPos multiblockOrigin);
}
