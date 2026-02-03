package net.fawnoculus.ntm.blocks;

import net.fawnoculus.ntm.api.radiation.RadiationRegistry;

public class NtmExtraBlockData {
    public static void init() {
        RadiationRegistry.register(NtmBlocks.URANIUM_BLOCK, 3500);
    }
}
