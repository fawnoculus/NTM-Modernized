package net.fawnoculus.ntm.world.gen;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.fawnoculus.ntm.world.placed.NtmPOrePlacedFeatures;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.function.Predicate;

public class NtmOreGeneration {
    private static final Predicate<BiomeModifications.BiomeContext> IS_OVERWORLD = context -> context.hasTag(BiomeTags.IS_OVERWORLD);
    private static final Predicate<BiomeModifications.BiomeContext> IS_NETHER = context -> context.hasTag(BiomeTags.IS_NETHER);
    private static final Predicate<BiomeModifications.BiomeContext> IS_END = context -> context.hasTag(BiomeTags.IS_END);

    @SuppressWarnings("UnstableApiUsage") // Adding a feature from it's ResourceKey is unstable API apparently
    public static void init() {
        BiomeModifications.addProperties(IS_OVERWORLD, (ignored, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.URANIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.URANIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.TITANIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.SULFUR_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.THORIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NITER_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.TUNGSTEN_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.ALUMINIUM_BEARING_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.FLUORITE_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.LEAD_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.SCHRABIDIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.BERYLLIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.AUSTRALIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.RARE_EARTH_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.COBALT_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.CINNEBAR_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.COLTAN_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.LIGNITE_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.ASBESTOS_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.ALUMINIUM_ORE_CLUSTER_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.COPPER_ORE_CLUSTER_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.IRON_ORE_CLUSTER_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.TITANIUM_ORE_CLUSTER_PLACED_KEY);
        });

        BiomeModifications.addProperties(IS_NETHER, (ignored, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.SMOLDERING_NETHERRACK_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_COAL_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_URANIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_PLUTONIUM_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_TUNGSTEN_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_SULFUR_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_PHOSPHORUS_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_COBALT_ORE_PLACED_KEY);
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.NETHER_SCHRABIDIUM_ORE_PLACED_KEY);
        });

        BiomeModifications.addProperties(IS_END, (ignored, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NtmPOrePlacedFeatures.TRIXITE_ORE_PLACED_KEY);
        });
    }
}
