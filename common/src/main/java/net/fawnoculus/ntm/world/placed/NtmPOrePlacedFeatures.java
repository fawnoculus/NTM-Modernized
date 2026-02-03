package net.fawnoculus.ntm.world.placed;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.world.configured.NtmOreConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class NtmPOrePlacedFeatures {
    public static final ResourceKey<PlacedFeature> URANIUM_ORE_PLACED_KEY = registryKey("uranium_ore_placed");
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY = registryKey("titanium_ore_placed");
    public static final ResourceKey<PlacedFeature> SULFUR_ORE_PLACED_KEY = registryKey("sulfur_ore_placed");
    public static final ResourceKey<PlacedFeature> THORIUM_ORE_PLACED_KEY = registryKey("thorium_ore_placed");
    public static final ResourceKey<PlacedFeature> NITER_ORE_PLACED_KEY = registryKey("niter_ore_placed");
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = registryKey("tungsten_ore_placed");
    public static final ResourceKey<PlacedFeature> ALUMINIUM_BEARING_ORE_PLACED_KEY = registryKey("aluminium_bearing_ore_placed");
    public static final ResourceKey<PlacedFeature> FLUORITE_ORE_PLACED_KEY = registryKey("fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registryKey("lead_ore_placed");
    public static final ResourceKey<PlacedFeature> SCHRABIDIUM_ORE_PLACED_KEY = registryKey("schrabidium_ore_placed");
    public static final ResourceKey<PlacedFeature> BERYLLIUM_ORE_PLACED_KEY = registryKey("beryllium_ore_placed");
    public static final ResourceKey<PlacedFeature> AUSTRALIUM_ORE_PLACED_KEY = registryKey("australium_ore_placed");
    public static final ResourceKey<PlacedFeature> RARE_EARTH_ORE_PLACED_KEY = registryKey("rare_earth_ore_placed");
    public static final ResourceKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registryKey("cobalt_ore_placed");
    public static final ResourceKey<PlacedFeature> CINNEBAR_ORE_PLACED_KEY = registryKey("cinnebar_ore_placed");
    public static final ResourceKey<PlacedFeature> COLTAN_ORE_PLACED_KEY = registryKey("coltan_ore_placed");
    public static final ResourceKey<PlacedFeature> LIGNITE_ORE_PLACED_KEY = registryKey("lignite_ore_placed");
    public static final ResourceKey<PlacedFeature> ASBESTOS_ORE_PLACED_KEY = registryKey("asbestos_ore_placed");
    public static final ResourceKey<PlacedFeature> ALUMINIUM_ORE_CLUSTER_PLACED_KEY = registryKey("aluminium_ore_cluster_placed");
    public static final ResourceKey<PlacedFeature> COPPER_ORE_CLUSTER_PLACED_KEY = registryKey("copper_ore_cluster_placed");
    public static final ResourceKey<PlacedFeature> IRON_ORE_CLUSTER_PLACED_KEY = registryKey("iron_ore_cluster_placed");
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_CLUSTER_PLACED_KEY = registryKey("titanium_ore_cluster_placed");

    public static final ResourceKey<PlacedFeature> SMOLDERING_NETHERRACK_PLACED_KEY = registryKey("smoldering_netherrack_placed");
    public static final ResourceKey<PlacedFeature> NETHER_COAL_ORE_PLACED_KEY = registryKey("nether_coal_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_URANIUM_ORE_PLACED_KEY = registryKey("nether_uranium_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_PLUTONIUM_ORE_PLACED_KEY = registryKey("nether_plutonium_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_TUNGSTEN_ORE_PLACED_KEY = registryKey("nether_tungsten_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_SULFUR_ORE_PLACED_KEY = registryKey("nether_sulfur_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_PHOSPHORUS_ORE_PLACED_KEY = registryKey("nether_phosphorus_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_COBALT_ORE_PLACED_KEY = registryKey("nether_cobalt_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_SCHRABIDIUM_ORE_PLACED_KEY = registryKey("nether_schrabidium_ore_placed");

    public static final ResourceKey<PlacedFeature> TRIXITE_ORE_PLACED_KEY = registryKey("trixite_ore_placed");


    public static void init(@NotNull BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // TODO: extract propper counts from original
        register(context, URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.URANIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, TITANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, SULFUR_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.SULFUR_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, THORIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.THORIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NITER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.NITER_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, TUNGSTEN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TUNGSTEN_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, ALUMINIUM_BEARING_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.ALUMINIUM_BEARING_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.FLUORITE_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, LEAD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.LEAD_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, SCHRABIDIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.SCHRABIDIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, BERYLLIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.BERYLLIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, AUSTRALIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.AUSTRALIUM_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, RARE_EARTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.RARE_EARTH_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, COBALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.COBALT_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, CINNEBAR_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.CINNEBAR_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, COLTAN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.COLTAN_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, LIGNITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.LIGNITE_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, ASBESTOS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.ASBESTOS_ORE_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, ALUMINIUM_ORE_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.ALUMINIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, COPPER_ORE_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.COPPER_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, IRON_ORE_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.IRON_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, TITANIUM_ORE_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );

        register(context, SMOLDERING_NETHERRACK_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_PLUTONIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_TUNGSTEN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_SULFUR_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_PHOSPHORUS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_COBALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
        register(context, NETHER_SCHRABIDIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );

        register(context, TRIXITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(NtmOreConfiguredFeatures.TITANIUM_ORE_CLUSTER_KEY),
          modifiersWithCount(14,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))

          )
        );
    }


    public static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Ntm.id(name));
    }

    private static void register(@NotNull BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, PlacementModifier... modifiers) {
        register(context, key, config, List.of(modifiers));
    }

    // Methods below are directly from OrePlacedFeatures, just with some Annotations
    @Contract("_, _ -> new")
    public static @NotNull @Unmodifiable List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    @Contract("_, _ -> new") // Technically it's not exactly veins per Chunk, but it's pretty close
    public static @NotNull @Unmodifiable List<PlacementModifier> modifiersWithCount(int veinsPerChunk, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(veinsPerChunk), heightModifier);
    }

    @Contract("_, _ -> new")
    public static @NotNull @Unmodifiable List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }
}
