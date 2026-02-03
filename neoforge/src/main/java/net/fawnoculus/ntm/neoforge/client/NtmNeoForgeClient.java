package net.fawnoculus.ntm.neoforge.client;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.client.NtmClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Ntm.MOD_ID, dist = Dist.CLIENT)
public final class NtmNeoForgeClient {
    public NtmNeoForgeClient(IEventBus modBus) {
        NtmClient.init();
    }
}
