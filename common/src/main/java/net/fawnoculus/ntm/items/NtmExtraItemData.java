package net.fawnoculus.ntm.items;

import net.fawnoculus.ntm.api.radiation.RadiationRegistry;

public class NtmExtraItemData {
    public static void init() {
        RadiationRegistry.register(NtmItems.URANIUM_INGOT, 350);
    }
}
