package net.fawnoculus.ntm.api;

import net.fawnoculus.ntm.api.events.NtmEvents;
import net.fawnoculus.ntm.api.explosion.NtmExplosionTypes;
import net.fawnoculus.ntm.api.node.network.NetworkTypes;
import net.fawnoculus.ntm.api.node.network.NodeNetworkManager;
import net.fawnoculus.ntm.api.radiation.HazmatRegistry;
import net.fawnoculus.ntm.api.radiation.RadiationRegistry;

public class NtmApi {
    public static void init() {
        NtmEvents.init();
        RadiationRegistry.init();
        HazmatRegistry.init();
        NtmExplosionTypes.init();

        NodeNetworkManager.registerType(NetworkTypes.ENERGY);
        NodeNetworkManager.registerType(NetworkTypes.FLUID);
    }
}
