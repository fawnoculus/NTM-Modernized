package net.fawnoculus.ntm.fabric;

import net.fabricmc.api.ModInitializer;
import net.fawnoculus.ntm.Ntm;

public final class NtmFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Ntm.init();
    }
}
