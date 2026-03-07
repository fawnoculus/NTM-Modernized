package net.fawnoculus.ntm.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.custom.*;
import net.fawnoculus.ntm.blocks.custom.container.energy.SimpleEnergyStorageBlock;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.fawnoculus.ntm.util.NtmJavaUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class NtmBlocks {
    public static final RegistrySupplier<Block> GEOTHERMAL_VENT = register("geothermal_vent", Block::new,
      Properties.ofFullCopy(Blocks.BEDROCK).strength(Float.MAX_VALUE)
    );
    public static final RegistrySupplier<Block> BEDROCK_OIL_DEPOSIT = register("bedrock_oil_deposit", Block::new,
      Properties.ofFullCopy(Blocks.BEDROCK).strength(Float.MAX_VALUE)
    );
    public static final RegistrySupplier<Block> BEDROCK_ORE = register("bedrock_ore", Block::new,
      Properties.ofFullCopy(Blocks.BEDROCK).strength(Float.MAX_VALUE)
    );
    public static final RegistrySupplier<Block> ALLOY_FURNACE = register("alloy_furnace", AlloyFurnaceBlock::new,
      machineSettings(Properties.of())
        .strength(1.5F, 6.0F)
        .sound(SoundType.STONE)
        .mapColor(MapColor.TERRACOTTA_ORANGE));
    public static final RegistrySupplier<Block> ALLOY_FURNACE_EXTENSION = register("alloy_furnace_extension", AlloyFurnaceExtensionBlock::new,
      advancedModel(machineSettings(Properties.of()))
        .strength(1.5F, 6.0F)
        .sound(SoundType.STONE)
        .mapColor(MapColor.TERRACOTTA_ORANGE)
    );
    public static final RegistrySupplier<Block> ELECTRIC_FURNACE = register("electric_furnace", ElectricFurnaceBlock::new, machineSettings(Properties.of()));
    // This one is currently only here for the item group icon
    public static final RegistrySupplier<Block> PWR_CONTROLLER = register("pwr_controller", PWRControllerBlock::new, machineSettings(Properties.of()));

    // Energy Stuff
    public static final RegistrySupplier<Block> TEMP_CABLE = register("temp_cable", TempCableBlock::new, Properties.of());
    public static final RegistrySupplier<Block> POTATO_BATTERY_BLOCK = register("potato_battery_block",
      properties -> new SimpleEnergyStorageBlock(properties, 10_000L), machineSettings(Properties.of()));
    public static final RegistrySupplier<Block> ENERGY_STORAGE_BLOCK = register("energy_storage_block",
      properties -> new SimpleEnergyStorageBlock(properties, 1_000_000L), machineSettings(Properties.of()));
    public static final RegistrySupplier<Block> LITHIUM_ION_ENERGY_STORAGE_BLOCK = register("lithium_ion_energy_storage_block",
      properties -> new SimpleEnergyStorageBlock(properties, 50_000_000L), machineSettings(Properties.of()));
    public static final RegistrySupplier<Block> SCHRABIDIUM_ENERGY_STORAGE_BLOCK = register("schrabidium_energy_storage_block",
      properties -> new SimpleEnergyStorageBlock(properties, 25_000_000_000L), machineSettings(Properties.of()));
    public static final RegistrySupplier<Block> SPARK_ENERGY_STORAGE_BLOCK = register("spark_energy_storage_block",
      properties -> new SimpleEnergyStorageBlock(properties, 1_000_000_000_000L), machineSettings(Properties.of()));
    // Ore Blocks
    public static final RegistrySupplier<Block> URANIUM_ORE = register("uranium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> SCORCHED_URANIUM_ORE = register("scorched_uranium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> TITANIUM_ORE = register("titanium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> SULFUR_ORE = register("sulfur_ore", Block::new, stone());
    public static final RegistrySupplier<Block> THORIUM_ORE = register("thorium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> NITER_ORE = register("niter_ore", Block::new, stone());
    public static final RegistrySupplier<Block> TUNGSTEN_ORE = register("tungsten_ore", Block::new, stone());
    public static final RegistrySupplier<Block> ALUMINIUM_BEARING_ORE = register("aluminium_bearing_ore", Block::new, stone());
    public static final RegistrySupplier<Block> FLUORITE_ORE = register("fluorite_ore", Block::new, stone());
    public static final RegistrySupplier<Block> LEAD_ORE = register("lead_ore", Block::new, stone());
    public static final RegistrySupplier<Block> SCHRABIDIUM_ORE = register("schrabidium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> BERYLLIUM_ORE = register("beryllium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> AUSTRALIUM_ORE = register("australium_ore", Block::new, stone());
    public static final RegistrySupplier<Block> RARE_EARTH_ORE = register("rare_earth_ore", Block::new, stone());
    public static final RegistrySupplier<Block> COBALT_ORE = register("cobalt_ore", Block::new, stone());
    public static final RegistrySupplier<Block> CINNEBAR_ORE = register("cinnebar_ore", Block::new, stone());
    public static final RegistrySupplier<Block> COLTAN_ORE = register("coltan_ore", Block::new, stone());
    public static final RegistrySupplier<Block> LIGNITE_ORE = register("lignite_ore", Block::new, stone());
    public static final RegistrySupplier<Block> ASBESTOS_ORE = register("asbestos_ore", Block::new, stone());
    public static final RegistrySupplier<Block> OIL_DEPOSIT = register("oil_deposit", Block::new, stone());
    public static final RegistrySupplier<Block> EMPTY_OIL_DEPOSIT = register("empty_oil_deposit", Block::new, stone());
    public static final RegistrySupplier<Block> ALUMINIUM_ORE_CLUSTER = register("aluminium_ore_cluster", Block::new, stone());
    public static final RegistrySupplier<Block> COPPER_ORE_CLUSTER = register("copper_ore_cluster", Block::new, stone());
    public static final RegistrySupplier<Block> IRON_ORE_CLUSTER = register("iron_ore_cluster", Block::new, stone());
    public static final RegistrySupplier<Block> TITANIUM_ORE_CLUSTER = register("titanium_ore_cluster", Block::new, stone());
    public static final RegistrySupplier<Block> BAUXITE = register("bauxite", Block::new, stone());
    public static final RegistrySupplier<Block> CHRYSOTILE = register("chrysotile", Block::new, stone());
    public static final RegistrySupplier<Block> HEMATITE = register("hematite", Block::new, stone());
    public static final RegistrySupplier<Block> LIMESTONE = register("limestone", Block::new, stone());
    public static final RegistrySupplier<Block> MALACHITE = register("malachite", Block::new, stone());
    public static final RegistrySupplier<Block> SULFUROUS_STONE = register("sulfurous_stone", Block::new, stone());
    public static final RegistrySupplier<Block> TEKTITE = register("tektite", Block::new, stone());
    public static final RegistrySupplier<Block> OSMIRIDIUM_INFUSED_TEKTITE = register("osmiridium_infused_tektite", Block::new, stone());
    public static final RegistrySupplier<Block> TRIXITE_ORE = register("trixite_ore", Block::new, stone());
    // Resource Blocks
    public static final RegistrySupplier<Block> ACTINIUM_227_BLOCK = register("actinium_227_block", Block::new, stone());
    public static final RegistrySupplier<Block> ADVANCED_ALLOY_BLOCK = register("advanced_alloy_block", Block::new, stone());
    public static final RegistrySupplier<Block> ALUMINIUM_BLOCK = register("aluminium_block", Block::new, stone());
    public static final RegistrySupplier<Block> ASBESTOS_BLOCK = register("asbestos_block", Block::new, stone());
    public static final RegistrySupplier<Block> AUSTRALIUM_BLOCK = register("australium_block", Block::new, stone());
    public static final RegistrySupplier<Block> BAKELITE_BLOCK = register("bakelite_block", Block::new, stone());
    public static final RegistrySupplier<Block> BERYLLIUM_BLOCK = register("beryllium_block", Block::new, stone());
    public static final RegistrySupplier<Block> BISMUTH_BLOCK = register("bismuth_block", Block::new, stone());
    public static final RegistrySupplier<Block> BORON_BLOCK = register("boron_block", Block::new, stone());
    public static final RegistrySupplier<Block> CADMIUM_BLOCK = register("cadmium_block", Block::new, stone());
    public static final RegistrySupplier<Block> CADMIUM_STEEL_BLOCK = register("cadmium_steel_block", Block::new, stone());
    public static final RegistrySupplier<Block> CMB_STEEL_BLOCK = register("cmb_steel_block", Block::new, stone());
    public static final RegistrySupplier<Block> COAL_COKE_BLOCK = register("coal_coke_block", Block::new, stone());
    public static final RegistrySupplier<Block> COBALT_BLOCK = register("cobalt_block", Block::new, stone());
    public static final RegistrySupplier<Block> COLTAN_BLOCK = register("coltan_block", Block::new, stone());
    public static final RegistrySupplier<Block> DESH_BLOCK = register("desh_block", Block::new, stone());
    public static final RegistrySupplier<Block> DINEUTRONIUM_BLOCK = register("dineutronium_block", Block::new, stone());
    public static final RegistrySupplier<Block> EUPHEMIUM_BLOCK = register("euphemium_block", Block::new, stone());
    public static final RegistrySupplier<Block> FERRIC_SCHRABIDATE_BLOCK = register("ferric_schrabidate_block", Block::new, stone());
    public static final RegistrySupplier<Block> FLUORITE_BLOCK = register("fluorite_block", Block::new, stone());
    public static final RegistrySupplier<Block> GRAPHITE_BLOCK = register("graphite_block", Block::new, stone());
    public static final RegistrySupplier<Block> HIGH_SPEED_STEEL_BLOCK = register("high_speed_steel_block", Block::new, stone());
    public static final RegistrySupplier<Block> LIGNITE_COKE_BLOCK = register("lignite_coke_block", Block::new, stone());
    public static final RegistrySupplier<Block> LEAD_BLOCK = register("lead_block", Block::new, stone());
    public static final RegistrySupplier<Block> LITHIUM_BLOCK = register("lithium_block", Block::new, stone());
    public static final RegistrySupplier<Block> MAGNETIZED_TUNGSTEN_BLOCK = register("magnetized_tungsten_block", Block::new, stone());
    public static final RegistrySupplier<Block> MOX_FUEL_BLOCK = register("mox_fuel_block", Block::new, stone());
    public static final RegistrySupplier<Block> NEPTUNIUM_BLOCK = register("neptunium_block", Block::new, stone());
    public static final RegistrySupplier<Block> NIOBIUM_BLOCK = register("niobium_block", Block::new, stone());
    public static final RegistrySupplier<Block> NITER_BLOCK = register("niter_block", Block::new, stone());
    public static final RegistrySupplier<Block> PETROLEUM_COKE_BLOCK = register("petroleum_coke_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_BLOCK = register("plutonium_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_FUEL_BLOCK = register("plutonium_fuel_block", Block::new, stone());
    public static final RegistrySupplier<Block> REACTOR_GRADE_PLUTONIUM_BLOCK = register("reactor_grade_plutonium_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_238_BLOCK = register("plutonium_238_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_239_BLOCK = register("plutonium_239_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_240_BLOCK = register("plutonium_240_block", Block::new, stone());
    public static final RegistrySupplier<Block> PLUTONIUM_241_BLOCK = register("plutonium_241_block", Block::new, stone());
    public static final RegistrySupplier<Block> POLONIUM_210_BLOCK = register("polonium_210_block", Block::new, stone());
    public static final RegistrySupplier<Block> POLYMER_BLOCK = register("polymer_block", Block::new, stone());
    public static final RegistrySupplier<Block> RADIUM_226_BLOCK = register("radium_266_block", Block::new, stone());
    public static final RegistrySupplier<Block> RED_COPPER_BLOCK = register("red_copper_block", Block::new, stone());
    public static final RegistrySupplier<Block> RED_PHOSPHORUS_BLOCK = register("red_phosphorus_block", Block::new, stone());
    public static final RegistrySupplier<Block> RUBBER_BLOCK = register("rubber_block", Block::new, stone());
    public static final RegistrySupplier<Block> SCHRABIDIUM_BLOCK = register("schrabidium_block", Block::new, stone());
    public static final RegistrySupplier<Block> SCHRABIDIUM_FUEL_BLOCK = register("schrabidium_fuel_block", Block::new, stone());
    public static final RegistrySupplier<Block> SCHRARANIUM_BLOCK = register("schraranium_block", Block::new, stone());
    public static final RegistrySupplier<Block> SEMTEX_BLOCK = register("semtex_block", Block::new, stone());
    public static final RegistrySupplier<Block> SOLINIUM_BLOCK = register("solinium_block", Block::new, stone());
    public static final RegistrySupplier<Block> STARMETAL_BLOCK = register("starmetal_block", Block::new, stone());
    public static final RegistrySupplier<Block> STEEL_BLOCK = register("steel_block", Block::new, stone());
    public static final RegistrySupplier<Block> SULFUR_BLOCK = register("sulfur_block", Block::new, stone());
    public static final RegistrySupplier<Block> TANTALUM_BLOCK = register("tantalum_block", Block::new, stone());
    public static final RegistrySupplier<Block> TECHNETIUM_STEEL_BLOCK = register("technetium_steel_block", Block::new, stone());
    public static final RegistrySupplier<Block> THORIUM_232_BLOCK = register("thorium_232_block", Block::new, stone());
    public static final RegistrySupplier<Block> THORIUM_FUEL_BLOCK = register("thorium_fuel_block", Block::new, stone());
    public static final RegistrySupplier<Block> TITANIUM_BLOCK = register("titanium_block", Block::new, stone());
    public static final RegistrySupplier<Block> TUNGSTEN_BLOCK = register("tungsten_block", Block::new, stone());
    public static final RegistrySupplier<Block> URANIUM_BLOCK = register("uranium_block", Block::new, stone());
    public static final RegistrySupplier<Block> URANIUM_FUEL_BLOCK = register("uranium_fuel_block", Block::new, stone());
    public static final RegistrySupplier<Block> URANIUM_233_BLOCK = register("uranium_233_block", Block::new, stone());
    public static final RegistrySupplier<Block> URANIUM_235_BLOCK = register("uranium_235_block", Block::new, stone());
    public static final RegistrySupplier<Block> URANIUM_238_BLOCK = register("uranium_238_block", Block::new, stone());
    public static final RegistrySupplier<Block> WHITE_PHOSPHORUS_BLOCK = register("white_phosphorus_block", Block::new, stone());
    public static final RegistrySupplier<Block> YELLOWCAKE_BLOCK = register("yellowcake_block", Block::new, stone());
    public static final RegistrySupplier<Block> ZIRCONIUM_BLOCK = register("zirconium_block", Block::new, stone());
    public static final RegistrySupplier<Block> GRAPHITIC_SCHIST = register("graphitic_schist", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_IRON_ORE = register("schist_iron_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_GOLD_ORE = register("schist_gold_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_URANIUM_ORE = register("schist_uranium_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCORCHED_SCHIST_URANIUM_ORE = register("scorched_schist_uranium_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_COPPER_ORE = register("schist_copper_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_ASBESTOS_ORE = register("schist_asbestos_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_LITHIUM_ORE = register("schist_lithium_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_SCHRABIDIUM_ORE = register("schist_schrabidium_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> SCHIST_RARE_EARTH_ORE = register("schist_rare_earth_ore", Block::new, schistOre());
    public static final RegistrySupplier<Block> GAS_SHALE = register("gas_shale", Block::new, schistOre());
    public static final RegistrySupplier<Block> DEEPSLATE_URANIUM_ORE = register("deepslate_uranium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_SCORCHED_URANIUM_ORE = register("deepslate_scorched_uranium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_TITANIUM_ORE = register("deepslate_titanium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_SULFUR_ORE = register("deepslate_sulfur_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_THORIUM_ORE = register("deepslate_thorium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_NITER_ORE = register("deepslate_niter_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_TUNGSTEN_ORE = register("deepslate_tungsten_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_ALUMINIUM_BEARING_ORE = register("deepslate_aluminium_bearing_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_FLUORITE_ORE = register("deepslate_fluorite_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_LEAD_ORE = register("deepslate_lead_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_SCHRABIDIUM_ORE = register("deepslate_schrabidium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_BERYLLIUM_ORE = register("deepslate_beryllium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_AUSTRALIUM_ORE = register("deepslate_australium_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_RARE_EARTH_ORE = register("deepslate_rare_earth_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_COBALT_ORE = register("deepslate_cobalt_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_CINNEBAR_ORE = register("deepslate_cinnebar_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_COLTAN_ORE = register("deepslate_coltan_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_LIGNITE_ORE = register("deepslate_lignite_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_ASBESTOS_ORE = register("deepslate_asbestos_ore", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_OIL_DEPOSIT = register("deepslate_oil_deposit", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_EMPTY_OIL_DEPOSIT = register("deepslate_empty_oil_deposit", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_ALUMINIUM_ORE_CLUSTER = register("deepslate_aluminium_ore_cluster", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_COPPER_ORE_CLUSTER = register("deepslate_copper_ore_cluster", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_IRON_ORE_CLUSTER = register("deepslate_iron_ore_cluster", Block::new, deepslate());
    public static final RegistrySupplier<Block> DEEPSLATE_TITANIUM_ORE_CLUSTER = register("deepslate_titanium_ore_cluster", Block::new, deepslate());
    public static final RegistrySupplier<Block> VOLCANIC_BASALT = register("volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> SULFUR_RICH_VOLCANIC_BASALT = register("sulfur_rich_volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> FLUORITE_RICH_VOLCANIC_BASALT = register("fluorite_rich_volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> ASBESTOS_RICH_VOLCANIC_BASALT = register("asbestos_rich_volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> GEM_RICH_VOLCANIC_BASALT = register("gem_rich_volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> MOLYSITE_RICH_VOLCANIC_BASALT = register("molysite_rich_volcanic_basalt", Block::new, volcanicBasalt());
    public static final RegistrySupplier<Block> NETHER_URANIUM_ORE = register("nether_uranium_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> SCORCHED_NETHER_URANIUM_ORE = register("scorched_nether_uranium_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_PLUTONIUM_ORE = register("nether_plutonium_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_TUNGSTEN_ORE = register("nether_tungsten_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_SULFUR_ORE = register("nether_sulfur_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_PHOSPHORUS_ORE = register("nether_phosphorus_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_COBALT_ORE = register("nether_cobalt_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> NETHER_SCHRABIDIUM_ORE = register("nether_schrabidium_ore", Block::new, netherOre());
    public static final RegistrySupplier<Block> SMOLDERING_NETHERRACK = register("smoldering_netherrack", Block::new, glowingNetherOre());
    public static final RegistrySupplier<Block> NETHER_COAL_ORE = register("nether_coal_ore", Block::new, glowingNetherOre());
    public static final RegistrySupplier<Block> METEORITE_BLOCK = register("meteorite_block", Block::new, meteorStone());
    public static final RegistrySupplier<Block> BROKEN_METEORITE_BLOCK = register("broken_meteorite_block", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEORITE_COBBLESTONE = register("meteorite_cobblestone", Block::new, meteorStone());
    public static final RegistrySupplier<Block> HOT_METEORITE_COBBLESTONE = register("hot_meteorite_cobblestone", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEORITE_TREASURE_BLOCK = register("meteorite_treasure_block", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEOR_IRON_ORE = register("meteor_iron_ore", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEOR_COPPER_ORE = register("meteor_copper_ore", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEOR_ALUMINIUM_ORE = register("meteor_aluminium_ore", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEOR_RARE_EARTH_ORE = register("meteor_rare_earth_ore", Block::new, meteorStone());
    public static final RegistrySupplier<Block> METEOR_COBALT_ORE = register("meteor_cobalt_ore", Block::new, meteorStone());
    public static final RegistrySupplier<Block> DEPTH_ROCK = register("depth_rock", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_CINNABAR_ORE = register("depth_cinnabar_ore", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_ZIRCONIUM_ORE = register("depth_zirconium", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_BORAX_ORE = register("depth_borax_ore", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_IRON_ORE_CLUSTER = register("depth_iron_ore_cluster", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_TITANIUM_ORE_CLUSTER = register("depth_titanium_ore_cluster", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEPTH_TUNGSTEN_ORE_CLUSTER = register("depth_tungsten_ore_cluster", Block::new, depthStone());
    public static final RegistrySupplier<Block> ALEXANDRITE_ORE = register("alexandrite_ore", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEEPSLATE_ALEXANDRITE_ORE = register("deepslate_alexandrite_ore", Block::new, depthStone());
    public static final RegistrySupplier<Block> NETHER_DEPTH_ROCK = register("nether_depth_rock", Block::new, depthStone());
    public static final RegistrySupplier<Block> NETHER_DEPTH_NEODYMIUM_ORE = register("nether_depth_neodymium_ore", Block::new, depthStone());
    public static final RegistrySupplier<Block> DEAD_DIRT = register("dead_dirt", Block::new,
      Properties.ofFullCopy(Blocks.DIRT)
        .mapColor(MapColor.TERRACOTTA_BROWN)
        .destroyTime(2f)
    );
    public static final RegistrySupplier<Block> OILY_DIRT = register("oily_dirt", Block::new,
      Properties.ofFullCopy(Blocks.DIRT)
        .mapColor(MapColor.TERRACOTTA_BLACK)
        .destroyTime(2f)
    );
    public static final RegistrySupplier<Block> OILY_SAND = register("oily_sand", Block::new,
      Properties.ofFullCopy(Blocks.SAND)
        .mapColor(MapColor.TERRACOTTA_BLACK)
        .destroyTime(2f)
    );

    // Special Effect Barrels
    /* FIXME
    public static final RegistrySupplier<Block> EXPLOSIVE_BARREL = register("explosive_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> IMP_RESIDUE_BARREL = register("imp_residue_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> KEROSENE_BARREL = register("kerosene_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> LOX_BARREL = register("lox_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> RADIOACTIVE_BARREL = register("radioactive_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> VITRIFIED_NUCLEAR_WASTE_DRUM = register("vitrified_nuclear_waste_drum", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    */

    // FluidNetworkType Stuff
    /* FIXME
    public static final RegistrySupplier<Block> CORRODED_BARREL = register("corroded_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> SAFE_BARREL = register("safe_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> IRON_BARREL = register("iron_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> STEEL_BARREL = register("steel_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> TECHNETIUM_STEEL_BARREL = register("technetium_steel_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    public static final RegistrySupplier<Block> MAGNETIC_BARREL = register("magnetic_barrel", properties -> new SimpleFluidStorageBlock(properties, 12_000), AbstractBlock.Settings.create());
    */


    private static Properties stone() {
        return Properties.ofFullCopy(Blocks.STONE);
    }

    private static Properties deepslate() {
        return Properties.ofFullCopy(Blocks.DEEPSLATE);
    }

    private static Properties volcanicBasalt() {
        return Properties.ofFullCopy(Blocks.BASALT)
          .mapColor(MapColor.COLOR_BLACK)
          .strength(2f, 2f)
          .requiresCorrectToolForDrops();
    }

    private static Properties netherOre() {
        return Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)
          .mapColor(MapColor.COLOR_RED)
          .strength(2f, 2f)
          .requiresCorrectToolForDrops();
    }

    private static Properties glowingNetherOre() {
        return Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)
          .mapColor(MapColor.TERRACOTTA_RED)
          .lightLevel(NtmJavaUtil.valueFunc(15)::apply)
          .strength(2f, 2f)
          .requiresCorrectToolForDrops();
    }

    private static Properties depthStone() {
        return Properties.ofFullCopy(Blocks.DEEPSLATE)
          .sound(SoundType.STONE)
          .mapColor(MapColor.COLOR_GRAY)
          .strength(Float.MAX_VALUE, 40f)
          .requiresCorrectToolForDrops();
    }

    private static Properties meteorStone() {
        return Properties.ofFullCopy(Blocks.BLACKSTONE)
          .sound(SoundType.STONE)
          .mapColor(MapColor.COLOR_BLACK)
          .strength(2f, 2f)
          .requiresCorrectToolForDrops();
    }

    private static Properties schistOre() {
        return Properties.ofFullCopy(Blocks.STONE)
          .sound(SoundType.STONE)
          .mapColor(MapColor.CLAY)
          .strength(2f, 2f)
          .requiresCorrectToolForDrops();
    }

    private static Properties advancedModel(@NotNull Properties properties) {
        return properties
          .noOcclusion()
          .isValidSpawn(NtmJavaUtil.valueFunc(false)::apply)
          .isRedstoneConductor(NtmJavaUtil.valueFunc(false)::apply)
          .isSuffocating(NtmJavaUtil.valueFunc(false)::apply)
          .isViewBlocking(NtmJavaUtil.valueFunc(false)::apply);
    }

    private static Properties machineSettings(@NotNull Properties properties) {
        return properties.sound(SoundType.STONE).mapColor(MapColor.COLOR_GRAY).strength(2f, 6.0f).requiresCorrectToolForDrops();
    }

    private static RegistrySupplier<Block> register(String name, Function<Properties, Block> blockFactory, Properties properties) {
        return register(name, blockFactory, properties, true);
    }

    private static RegistrySupplier<Block> register(String name, Function<Properties, Block> blockFactory, Properties properties, boolean registerItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        RegistrySupplier<Block> block = NtmDeferredRegistries.BLOCKS.register(blockKey.identifier(), () -> blockFactory.apply(properties.setId(blockKey)));

        if (registerItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            NtmDeferredRegistries.ITEMS.register(itemKey.identifier(), () -> new BlockItem(block.get(), new Item.Properties().setId(itemKey)));
        }

        return block;
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Ntm.id(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Ntm.id(name));
    }

    public static void init() {
    }
}
