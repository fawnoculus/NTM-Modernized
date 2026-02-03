package net.fawnoculus.ntm.api.node;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.node.network.NetworkType;
import net.fawnoculus.ntm.api.node.network.NodeNetwork;
import net.fawnoculus.ntm.util.ExceptionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A Node serves as the base Part of a Node-Network.
 */
public interface Node {
    NetworkType getNetworkType();

    void setShouldAssignNetwork(boolean value);

    boolean shouldAssignNetwork();

    Collection<NodeValueContainer> getContainers();

    @Nullable NodeNetwork getNetwork();

    void setNetwork(NodeNetwork network);

    BlockPos getBlockPos();

    Level getLevel();

    /**
     * this can be overwritten to allow a node to connect to things that are not directly next to itself
     *
     * @return a List of all Nodes this Node is connected to
     */
    default List<Node> getConnectedNodes() {
        Level world = this.getLevel();
        assert world != null;
        BlockPos pos = this.getBlockPos();
        List<Node> nodes = new ArrayList<>();
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.above())));
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.below())));
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.north())));
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.east())));
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.south())));
        nodes.addAll(this.checkForNode(world.getBlockEntity(pos.west())));
        return nodes;
    }

    default void assignNetwork() {
        if (!this.shouldAssignNetwork()) return;
        try {
            NodeNetwork detectedNetwork = null;
            for (Node connectedNode : this.getConnectedNodes()) {
                detectedNetwork = this.findNetwork(connectedNode, detectedNetwork);
            }
            if (detectedNetwork == null) {
                detectedNetwork = this.getNetworkType().makeNewNetwork();
            }
            if (!detectedNetwork.LOADED_NODES.contains(this)) {
                detectedNetwork.addNode(this);
            }

            this.setNetwork(detectedNetwork);
        } catch (Throwable throwable) {
            Ntm.LOGGER.error("Failed assigning Network to Node at {}\nException: {}", this.getBlockPos().toShortString(), ExceptionUtil.makePretty(throwable));
        }
    }

    default NodeNetwork findNetwork(Node node, NodeNetwork detectedNetwork) {
        try {
            NodeNetwork foundNetwork = Objects.requireNonNull(node.getNetwork());

            if (detectedNetwork != null && !foundNetwork.equals(detectedNetwork)) {
                detectedNetwork.mergeAt(this);
                return detectedNetwork;
            }

            return foundNetwork;
        } catch (Exception ignored) {
            return detectedNetwork;
        }
    }

    default List<Node> checkForNode(Object object) {
        List<Object> toBeChecked = new ArrayList<>();
        toBeChecked.add(object);
        if (object instanceof MultiNode multiNode) {
            toBeChecked.addAll(multiNode.getNodes());
        }

        List<Node> nodes = new ArrayList<>();
        for (Object o : toBeChecked) {
            try {
                Node node = (Node) o;
                if (this.canConnectTo(node)) {
                    nodes.add(node);
                }
            } catch (ClassCastException ignored) {
            }
        }
        return nodes;
    }

    default boolean canConnectTo(@Nullable Node node) {
        if (node == null) return false;
        return node.getNetworkType() == this.getNetworkType();
    }

    default void writeNodeData(ValueOutput output) {
        if (this.getNetwork() != null) {
            output.putString("network", this.getNetwork().ID.toString());
        }
    }

    default void readNodeData(ValueInput input) {
        if (this.getLevel() != null && this.getLevel().isClientSide()) {
            return;
        }

        try {
            UUID uuid = UUID.fromString(input.getString("network").orElseThrow());
            NodeNetwork network = this.getNetworkType().getNetwork(uuid);
            this.setNetwork(network);
            if (!network.containsNode(this)) {
                network.addNode(this);
            }
        } catch (NoSuchElementException | IllegalArgumentException | NullPointerException ignored) {
        }
    }

    default void onSetWorld() {
        this.setShouldAssignNetwork(true);
        if (this.getLevel().isClientSide()) {
            return;
        }
        if (this.getNetwork() == null) {
            this.assignNetwork();
        }
    }

    default void onBreak() {
        if (this.getConnectedNodes().size() > 1 && this.getNetwork() != null) {
            this.getNetwork().disconnectNode(this);
        }
    }

    default void onUnload() {
        setShouldAssignNetwork(false);

        if (this.getNetwork() != null) {
            this.getNetwork().removeNode(this);
        }

        this.setNetwork(null);
    }
}
