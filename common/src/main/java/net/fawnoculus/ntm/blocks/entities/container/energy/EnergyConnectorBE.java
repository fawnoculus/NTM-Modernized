package net.fawnoculus.ntm.blocks.entities.container.energy;

import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.List;

public class EnergyConnectorBE extends EnergyBE {
    public EnergyConnectorBE(BlockPos pos, BlockState state) {
        super(NtmBlockEntities.SIMPLE_ENERGY_CONNECTOR_BE.get(), pos, state);
    }

    @Override
    public Collection<NodeValueContainer> getContainers() {
        return List.of();
    }
}
