package net.fawnoculus.ntm.world;

import net.fawnoculus.ntm.world.placed.NtmPOrePlacedFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;

public class NtmPlacedFeatures {
    public static void init(@NotNull BootstrapContext<PlacedFeature> context) {
        NtmPOrePlacedFeatures.init(context);
    }
}
