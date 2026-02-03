package net.fawnoculus.ntm.api.node.network;

import net.fawnoculus.ntm.Ntm;

public class NetworkTypes {
    public static final NetworkType ENERGY = new NetworkType(Ntm.id("energy"));
    public static final NetworkType FLUID = new NetworkType(Ntm.id("fluid"));
}
