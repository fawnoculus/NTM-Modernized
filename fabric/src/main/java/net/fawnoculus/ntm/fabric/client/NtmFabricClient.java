package net.fawnoculus.ntm.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fawnoculus.ntm.api.annotations.ClientOnly;
import net.fawnoculus.ntm.client.NtmClient;

@ClientOnly
public final class NtmFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NtmClient.init();
    }
}
