package net.fawnoculus.ntm.blocks.entities.container.energy;

import net.fawnoculus.ntm.api.node.Node;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public abstract class EnergyBE extends BlockEntity implements Node {
    private boolean shouldAssignNetwork = false;
    private NodeNetwork network;

    public EnergyBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public NetworkType getNetworkType() {
        return NetworkTypes.ENERGY;
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
        super.loadAdditional(input);
        this.readNodeData(input);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        this.writeNodeData(output);
    }
}
