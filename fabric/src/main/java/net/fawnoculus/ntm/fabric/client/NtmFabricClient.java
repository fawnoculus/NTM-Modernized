package net.fawnoculus.ntm.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fawnoculus.ntm.client.NtmClient;

@Environment(EnvType.CLIENT)
public final class NtmFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NtmClient.init();
    }
}
