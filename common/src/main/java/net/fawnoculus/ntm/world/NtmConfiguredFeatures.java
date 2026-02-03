package net.fawnoculus.ntm.world;

import net.fawnoculus.ntm.world.configured.NtmOreConfiguredFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NtmConfiguredFeatures {
    public static void init(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        NtmOreConfiguredFeatures.init(context);
    }
}
