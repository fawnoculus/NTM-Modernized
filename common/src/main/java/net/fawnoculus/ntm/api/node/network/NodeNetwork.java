package net.fawnoculus.ntm.api.node.network;

import com.google.common.collect.ImmutableList;
import io.netty.util.collection.LongObjectHashMap;
import io.netty.util.collection.LongObjectMap;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.api.node.Node;
import net.fawnoculus.ntm.api.node.NodeValueContainer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NodeNetwork {
    public final UUID ID;
    public final NetworkType TYPE;
    public final Set<Node> LOADED_NODES = new HashSet<>();
    public final Set<Long> REVERSED_CONSUMER_PRIORITIES = new TreeSet<Long>().reversed();
    public final LongObjectMap<List<NodeValueContainer>> PRIORITISED_CONSUMERS = new LongObjectHashMap<>();
    public final Set<Long> REVERSED_PROVIDER_PRIORITIES = new TreeSet<Long>().reversed();
    public final LongObjectMap<List<NodeValueContainer>> PRIORITISED_PROVIDERS = new LongObjectHashMap<>();

    public NodeNetwork(UUID uuid, NetworkType type) {
        this.ID = uuid;
        this.TYPE = type;

        NodeNetworkManager.addNetwork(this);
    }

    public void addNode(@NotNull Node node) {
        if (node.getLevel() != null && node.getLevel().isClientSide()) {
            return;
        }

        if (!LOADED_NODES.add(node) && NtmConfig.DEV_MODE.getValue()) {
            Ntm.LOGGER.warn("Added Node {} twice to Network {}", node, this.ID);
        }

        for (NodeValueContainer container : node.getContainers()) {
            this.addToSorted(container, container.getPriority(), container.provides(), container.consumes());
        }
    }

    /**
     * Removes a Node from the Network, if the node is permanently removed &AMP; not just unloaded, please use disconnect Node instead!
     *
     * @param node the Node to be removed
     */
    public void removeNode(@NotNull Node node) {
        if (node.getLevel() != null && node.getLevel().isClientSide()) {
            return;
        }

        LOADED_NODES.remove(node);

        for (NodeValueContainer container : node.getContainers()) {
            this.removeFromSorted(container, container.getPriority(), container.provides(), container.consumes());
        }
    }

    /**
     * Removes all data from a network, this will cause the network to get removed if no containers are added back to the network before the next tick
     */
    public void clearNetwork() {
        this.LOADED_NODES.clear();
        this.PRIORITISED_CONSUMERS.clear();
        this.PRIORITISED_PROVIDERS.clear();
        this.REVERSED_PROVIDER_PRIORITIES.clear();
        this.REVERSED_CONSUMER_PRIORITIES.clear();
    }

    /**
     * checks if a network is empty, if this is true the network will be removed in the next tick
     *
     * @return if the network is empty or not
     */
    public boolean isEmpty() {
        return this.LOADED_NODES.isEmpty();
    }

    /**
     * Checks if the network contains a specific node
     *
     * @param node the node to be checked
     * @return whether the network contains the node
     */
    public boolean containsNode(@NotNull Node node) {
        return this.LOADED_NODES.contains(node);
    }

    /**
     * removes all connections from a node &AMP; removes the node from the network
     *
     * @param originNode the Node to me removed
     */
    public void disconnectNode(@NotNull Node originNode) {
        this.clearNetwork();

        final ImmutableList<Node> disconnectedNodeList = ImmutableList.copyOf(originNode.getConnectedNodes());
        Stack<Node> disconnectedNodes = new Stack<>();
        for (Node disconectedNode : disconnectedNodeList) {
            disconnectedNodes.push(disconectedNode);
        }

        HashSet<Node> alreadyScannedNodes = new HashSet<>();
        alreadyScannedNodes.add(originNode);

        boolean isFirst = true;

        while (!disconnectedNodes.isEmpty()) {
            Node disconectedNode = disconnectedNodes.pop();

            NodeNetwork assignedNetwork = isFirst ? this : this.TYPE.makeNewNetwork();
            isFirst = false;

            Stack<Node> toBeScannedNodes = new Stack<>();
            toBeScannedNodes.push(disconectedNode);

            alreadyScannedNodes.add(disconectedNode);
            disconectedNode.setNetwork(assignedNetwork);
            assignedNetwork.addNode(disconectedNode);

            while (!toBeScannedNodes.isEmpty()) {
                Node scannedNode = toBeScannedNodes.pop();

                for (Node node : scannedNode.getConnectedNodes()) {
                    if (alreadyScannedNodes.contains(node)) continue;
                    if (disconnectedNodeList.contains(node)) {
                        disconnectedNodes.remove(node);
                    }

                    alreadyScannedNodes.add(node);
                    node.setNetwork(assignedNetwork);
                    assignedNetwork.addNode(node);

                    if (toBeScannedNodes.size() > NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() && NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() > 0) {
                        Ntm.LOGGER.warn("Exceeded Max Node Scan Depth of {} at {} in {} while Removing Node at {} from Network {}",
                          NtmConfig.MAX_NODE_SCAN_DEPTH.getValue(),
                          node.getBlockPos().toShortString(),
                          node.getLevel().dimension(),
                          originNode.getBlockPos().toShortString(),
                          this.ID
                        );
                        continue;
                    }
                    toBeScannedNodes.push(node);
                }
            }
        }
    }

    /**
     * removes the connection between multiple containers, will only remove them from the network if necessary
     *
     * @param providedNodes the containers whose connection with each other will be severed
     */
    public void disconnectNodes(Collection<Node> providedNodes) {
        this.clearNetwork();

        final ImmutableList<Node> disconnectedNodeList = ImmutableList.copyOf(providedNodes);
        Stack<Node> disconnectedNodes = new Stack<>();
        for (Node disconectedNode : disconnectedNodeList) {
            disconnectedNodes.push(disconectedNode);
        }

        HashSet<Node> alreadyScannedNodes = new HashSet<>();

        boolean isFirst = true;

        while (!disconnectedNodes.isEmpty()) {
            Node disconectedNode = disconnectedNodes.pop();

            NodeNetwork assignedNetwork = isFirst ? this : this.TYPE.makeNewNetwork();
            isFirst = false;

            Stack<Node> toBeScannedNodes = new Stack<>();
            toBeScannedNodes.push(disconectedNode);

            alreadyScannedNodes.add(disconectedNode);
            disconectedNode.setNetwork(assignedNetwork);
            assignedNetwork.addNode(disconectedNode);

            while (!toBeScannedNodes.isEmpty()) {
                Node scannedNode = toBeScannedNodes.pop();

                for (Node node : scannedNode.getConnectedNodes()) {
                    if (alreadyScannedNodes.contains(node)) continue;
                    if (disconnectedNodeList.contains(node)) {
                        disconnectedNodes.remove(node);
                    }

                    alreadyScannedNodes.add(node);
                    node.setNetwork(assignedNetwork);
                    assignedNetwork.addNode(node);

                    if (toBeScannedNodes.size() > NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() && NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() > 0) {
                        Ntm.LOGGER.warn("Exceeded Max Node Scan Depth of {} at {} in {} while severing Connections Between {} Nodes in Network {}",
                          NtmConfig.MAX_NODE_SCAN_DEPTH.getValue(),
                          node.getBlockPos().toShortString(),
                          node.getLevel().dimension(),
                          providedNodes.size(),
                          this.ID
                        );
                        continue;
                    }
                    toBeScannedNodes.push(node);
                }
            }
        }
    }

    /**
     * Reconstructs a network starting form a specified node.
     * Used when multiple networks are connected together in order to merge them
     *
     * @param node the Node at which the reconstruction will start
     */
    public void mergeAt(@NotNull Node node) {
        Stack<Node> toBeScanned = new Stack<>();
        Set<Node> scannedNodes = new HashSet<>();

        toBeScanned.push(node);

        while (!toBeScanned.isEmpty()) {
            Node scaningNode = toBeScanned.pop();

            for (Node connectedNode : scaningNode.getConnectedNodes()) {
                if (scannedNodes.contains(connectedNode)) continue;
                if (connectedNode.getNetwork() == null) continue;
                if (connectedNode.getNetwork().equals(this)) continue;

                connectedNode.setNetwork(this);
                this.addNode(connectedNode);

                if (toBeScanned.size() > NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() && NtmConfig.MAX_NODE_SCAN_DEPTH.getValue() > 0) {
                    Ntm.LOGGER.warn("Exceeded Max Node Scan Depth of {} while Connecting Network {} from {} to {} in {}",
                      NtmConfig.MAX_NODE_SCAN_DEPTH.getValue(),
                      this.ID,
                      connectedNode.getBlockPos().toShortString(),
                      node.getBlockPos().toShortString(),
                      node.getLevel().dimension()
                    );
                    continue;
                }
                toBeScanned.push(connectedNode);
            }

            scannedNodes.add(scaningNode);
        }
    }

    public void addToSorted(NodeValueContainer container, long priority, boolean provides, boolean consumes) {
        if (provides) {
            this.REVERSED_PROVIDER_PRIORITIES.add(priority);
            this.PRIORITISED_PROVIDERS.putIfAbsent(priority, new ArrayList<>());
            this.PRIORITISED_PROVIDERS.get(priority).add(container);
        }
        if (consumes) {
            this.REVERSED_CONSUMER_PRIORITIES.add(priority);
            this.PRIORITISED_CONSUMERS.putIfAbsent(priority, new ArrayList<>());
            this.PRIORITISED_CONSUMERS.get(priority).add(container);
        }
    }

    public void removeFromSorted(NodeValueContainer container, long priority, boolean provides, boolean consumes) {
        if (provides) {
            List<NodeValueContainer> providers = this.PRIORITISED_PROVIDERS.getOrDefault(priority, new ArrayList<>());
            providers.remove(container);
            if (providers.isEmpty()) {
                this.REVERSED_PROVIDER_PRIORITIES.remove(priority);
                this.PRIORITISED_PROVIDERS.remove(priority);
            }
        }
        if (consumes) {
            List<NodeValueContainer> providers = this.PRIORITISED_CONSUMERS.getOrDefault(priority, new ArrayList<>());
            providers.remove(container);
            if (providers.isEmpty()) {
                this.REVERSED_CONSUMER_PRIORITIES.remove(priority);
                this.PRIORITISED_CONSUMERS.remove(priority);
            }
        }
    }

    /**
     * Get the amount of  available ... (whatever this network contains ig)
     *
     * @return the amount contained in all Provider Storages
     */
    public long getAvailable() {
        long totalEnergy = 0;
        for (long priority : REVERSED_PROVIDER_PRIORITIES) {
            for (NodeValueContainer node : PRIORITISED_PROVIDERS.getOrDefault(priority, new ArrayList<>())) {
                long newTotal = totalEnergy + node.getValue();
                if (newTotal < totalEnergy && node.getValue() > 0) {
                    // Use Long.MAX_VALUE if we overflow
                    totalEnergy = Long.MAX_VALUE;
                    break;
                }
                totalEnergy = newTotal;
            }
        }
        return totalEnergy;
    }

    /**
     * Spread an amount to all Consumers sorted by Priority
     *
     * @param toSpread the amount to be spread
     * @return the amount that could not be spread (0 if everything was used)
     */
    public long spread(long toSpread) {
        for (long priority : REVERSED_CONSUMER_PRIORITIES) {
            List<NodeValueContainer> containers = new ArrayList<>(PRIORITISED_CONSUMERS.getOrDefault(priority, new ArrayList<>()));

            while (toSpread > containers.size() && !containers.isEmpty()) {
                long energyPerNode = toSpread / containers.size();
                // The amount that can not be equally divided
                toSpread %= containers.size();

                List<NodeValueContainer> fullContainers = new ArrayList<>();
                for (NodeValueContainer container : containers) {
                    // Add the amount that could not be consumed back to the total
                    toSpread += container.add(energyPerNode);

                    if (container.isFull()) {
                        fullContainers.add(container);
                    }
                }

                containers.removeAll(fullContainers);
            }

            // Take the amount that could not be evenly spread & just put it somewhere
            if (toSpread > 0 && !containers.isEmpty()) {
                for (NodeValueContainer container : containers) {
                    toSpread = container.add(toSpread);
                }
            }

            // break the loop if there is nothing more to spread
            if (toSpread == 0) break;
        }
        return toSpread;
    }

    /**
     * Remove a certain amount from the Networks Providers
     *
     * @param toRemove the amount to be spread
     */
    public void removeUsed(long toRemove) {
        for (long priority : REVERSED_PROVIDER_PRIORITIES) {
            List<NodeValueContainer> containers = new ArrayList<>(PRIORITISED_PROVIDERS.getOrDefault(priority, new ArrayList<>()));

            while (toRemove > containers.size() && !containers.isEmpty()) {
                long energyPerNode = toRemove / containers.size();
                // The amount that can not be equally divided
                toRemove %= containers.size();

                List<NodeValueContainer> fullContainers = new ArrayList<>();
                for (NodeValueContainer container : containers) {
                    // Add the amount that could not be removed back to the total
                    toRemove += container.remove(energyPerNode);

                    if (container.isFull()) {
                        fullContainers.add(container);
                    }
                }

                containers.removeAll(fullContainers);
            }

            // Take the amount that could not be evenly removed & just take it from somewhere
            if (toRemove > 0 && !containers.isEmpty()) {
                for (NodeValueContainer container : containers) {
                    toRemove = container.add(toRemove);
                }
            }

            // break the loop if there is nothing more to remove
            if (toRemove == 0) break;
        }
    }

    public void tickNetwork() {
        long available = this.getAvailable();
        long remaining = this.spread(available);
        this.removeUsed(available - remaining);
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) return false;
        NodeNetwork that = (NodeNetwork) object;
        return this.ID.equals(that.ID);
    }

    @Override
    public String toString() {
        return String.format("NodeNetwork{UUID:%1$s, %2$d Loaded Nodes(s)}", this.ID, this.LOADED_NODES.size());
    }
}
