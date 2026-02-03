package net.fawnoculus.ntm.api.node.network;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkType {
    private final HashMap<UUID, NodeNetwork> NETWORKS = new HashMap<>();
    private final Identifier ID;

    public NetworkType(Identifier id) {
        this.ID = id;
    }

    public Identifier getId() {
        return this.ID;
    }

    public Map<UUID, NodeNetwork> networks() {
        return NETWORKS;
    }

    public void setNetwork(UUID uuid, NodeNetwork network) {
        this.networks().put(uuid, network);
    }

    public void removeNetwork(UUID uuid) {
        this.networks().remove(uuid);
    }

    public NodeNetwork getNetwork(UUID uuid) {
        NodeNetwork network = this.networks().get(uuid);
        if (network == null) {
            network = this.makeNewNetwork(uuid);
        }
        return network;
    }

    public Collection<NodeNetwork> getAllNetworks() {
        return this.networks().values();
    }

    public MutableComponent getName() {
        return Component.translatable("network_type." + this.getId().getNamespace() + "." + this.getId().getPath());
    }

    public NodeNetwork makeNewNetwork() {
        return new NodeNetwork(UUID.randomUUID(), this);
    }

    public NodeNetwork makeNewNetwork(UUID uuid) {
        return new NodeNetwork(uuid, this);
    }
}
