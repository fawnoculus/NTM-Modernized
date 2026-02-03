package net.fawnoculus.ntm.blocks.entities.container.fluid;

import net.fawnoculus.ntm.api.node.Node;
import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.api.node.network.NetworkType;
import net.fawnoculus.ntm.api.node.network.NetworkTypes;
import net.fawnoculus.ntm.api.node.network.NodeNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;

public abstract class FluidBE extends BlockEntity implements Node {
    private boolean shouldAssignNetwork = false;
    private NodeNetwork network;

    public FluidBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static @NotNull Fluid getNetworkFluid(@NotNull NodeNetwork network) {
        for (Node node : network.LOADED_NODES) {
            if (node instanceof FluidBE fluidNode
              && fluidNode.getNodeFluid() != null
            ) {
                return fluidNode.getNodeFluid();
            }
        }

        return Fluids.EMPTY;
    }

    /**
     * May return Null if the Node Contains Multiple Fluid Types
     *
     * @return Which type of Fluid this node transports / Contains
     */
    public abstract @Nullable Fluid getNodeFluid();

    /**
     * Get the containers in this Node based on the type of Fluid it is trying to get
     *
     * @param fluid the type of fluid
     * @return the containers in this node based on the fluid
     */
    public abstract Collection<NodeValueContainer> getContainers(Fluid fluid);


    @Override
    public Collection<NodeValueContainer> getContainers() {
        if (this.getNetwork() == null) {
            return List.of();
        }

        return this.getContainers(getNetworkFluid(this.getNetwork()));
    }

    @Override
    public NetworkType getNetworkType() {
        return NetworkTypes.FLUID;
    }

    @Override
    public void setShouldAssignNetwork(boolean value) {
        this.shouldAssignNetwork = value;
    }

    @Override
    public boolean shouldAssignNetwork() {
        return this.shouldAssignNetwork;
    }

    @Override
    public @Nullable NodeNetwork getNetwork() {
        return this.network;
    }

    @Override
    public void setNetwork(NodeNetwork network) {
        this.network = network;
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setLevel(@NonNull Level world) {
        super.setLevel(world);
        this.onSetWorld();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        this.onUnload();
    }

    @Override
    public void preRemoveSideEffects(@NonNull BlockPos pos, @NonNull BlockState oldState) {
        super.preRemoveSideEffects(pos, oldState);
        this.onBreak();
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        this.readNodeData(input);
        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        this.writeNodeData(output);
    }
}
