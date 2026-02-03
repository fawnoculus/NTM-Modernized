package net.fawnoculus.ntm.neoforge;

import net.fawnoculus.ntm.Ntm;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Ntm.MOD_ID)
public final class NtmNeoForge {
    public NtmNeoForge(IEventBus modBus) {
        Ntm.init();
    }
}
