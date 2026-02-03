package net.fawnoculus.ntm.items;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;


public class NtmItemGroups {
    public static final ResourceKey<CreativeModeTab> RESOURCES_AND_PARTS_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("resources_and_parts"));
    public static final RegistrySupplier<CreativeModeTab> RESOURCES_AND_PARTS = register(RESOURCES_AND_PARTS_KEY, NtmItems.URANIUM_INGOT);
    public static final ResourceKey<CreativeModeTab> MACHINE_ITEMS_AND_FUEL_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("machine_items_and_fuel"));
    public static final RegistrySupplier<CreativeModeTab> PLUTONIUM_238_RTG_PELLET = register(MACHINE_ITEMS_AND_FUEL_KEY, NtmItems.PLUTONIUM_238_RTG_PELLET);
    public static final ResourceKey<CreativeModeTab> TEMPLATES_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("templates"));
    public static final RegistrySupplier<CreativeModeTab> TEMPLATES = register(TEMPLATES_KEY, NtmItems.NULL);
    public static final ResourceKey<CreativeModeTab> ORES_AND_BLOCKS_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("ores_and_blocks"));
    public static final RegistrySupplier<CreativeModeTab> ORES_AND_BLOCKS = register(ORES_AND_BLOCKS_KEY, NtmBlocks.URANIUM_ORE);
    public static final ResourceKey<CreativeModeTab> MACHINES_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("machines"));
    public static final RegistrySupplier<CreativeModeTab> MACHINES = register(MACHINES_KEY, NtmBlocks.PWR_CONTROLLER);
    public static final ResourceKey<CreativeModeTab> BOMBS_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("bombs"));
    // TODO: somehow set the background texture of BOMBS to Ntm.id("textures/gui/creative_inventory/tab_nuke.png")
    public static final RegistrySupplier<CreativeModeTab> BOMBS = register(BOMBS_KEY, NtmItems.NULL);
    public static final ResourceKey<CreativeModeTab> MISSILES_AND_SATELLITES_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("missiles_and_satellites"));
    public static final RegistrySupplier<CreativeModeTab> MISSILES_AND_SATELLITES = register(MISSILES_AND_SATELLITES_KEY, NtmItems.NULL);
    public static final ResourceKey<CreativeModeTab> WEAPONS_AND_TURRETS_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("weapons_and_turrets"));
    public static final RegistrySupplier<CreativeModeTab> WEAPONS_AND_TURRETS = register(WEAPONS_AND_TURRETS_KEY, NtmItems.NULL);
    public static final ResourceKey<CreativeModeTab> CONSUMABLES_AND_GEAR_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("consumables_and_gear"));
    public static final RegistrySupplier<CreativeModeTab> CONSUMABLES_AND_GEAR = register(CONSUMABLES_AND_GEAR_KEY, NtmItems.BOTTLE_OF_NUKA_COLA);
    public static final ResourceKey<CreativeModeTab> TOOLS_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Ntm.id("tools"));
    public static final RegistrySupplier<CreativeModeTab> TOOLS = register(TOOLS_KEY, NtmItems.ADVANCED_ALLOY_PICKAXE);

    private static RegistrySupplier<CreativeModeTab> register(ResourceKey<CreativeModeTab> resourceKey, RegistrySupplier<? extends ItemLike> icon) {
        return NtmDeferredRegistries.ITEM_TABS.register(resourceKey.identifier(),
          () -> CreativeTabRegistry.create(
            Component.translatable(resourceKey.identifier().toLanguageKey("category")),
            () -> new ItemStack(icon.get())
          )
        );
    }

    public static void init() {
        NtmPlatform.modifyCreativeTab(RESOURCES_AND_PARTS_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.ACTINIUM_227_INGOT);
            tabModifier.accept(NtmItems.ACTINIUM_227_BILLET);
            tabModifier.accept(NtmItems.ACTINIUM_227_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_ACTINIUM_227_POWDER);
            tabModifier.accept(NtmItems.ACTINIUM_227_NUGGET);
            tabModifier.accept(NtmItems.ACTINIUM_227_FRAGMENT);

            tabModifier.accept(NtmItems.ADVANCED_ALLOY_INGOT);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_POWDER);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_PLATE);
            tabModifier.accept(NtmItems.CAST_ADVANCED_ALLOY_PLATE);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_WIRE);
            tabModifier.accept(NtmItems.DENSE_ADVANCED_ALLOY_WIRE);

            tabModifier.accept(NtmItems.ALEXANDRITE);

            tabModifier.accept(NtmItems.ALUMINIUM_INGOT);
            tabModifier.accept(NtmItems.ALUMINIUM_POWDER);
            tabModifier.accept(NtmItems.ALUMINIUM_PLATE);
            tabModifier.accept(NtmItems.CAST_ALUMINIUM_PLATE);
            tabModifier.accept(NtmItems.WELDED_ALUMINIUM_PLATE);
            tabModifier.accept(NtmItems.ALUMINIUM_SHELL);
            tabModifier.accept(NtmItems.ALUMINIUM_PIPE);
            tabModifier.accept(NtmItems.ALUMINIUM_WIRE);
            tabModifier.accept(NtmItems.ALUMINIUM_CRYSTALS);

            tabModifier.accept(NtmItems.AMERICIUM_241_INGOT);
            tabModifier.accept(NtmItems.AMERICIUM_241_BILLET);
            tabModifier.accept(NtmItems.AMERICIUM_241_NUGGET);
            tabModifier.accept(NtmItems.AMERICIUM_242_INGOT);
            tabModifier.accept(NtmItems.AMERICIUM_242_BILLET);
            tabModifier.accept(NtmItems.AMERICIUM_242_NUGGET);
            tabModifier.accept(NtmItems.AMERICIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.AMERICIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.AMERICIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.REACTOR_GRADE_AMERICIUM_INGOT);
            tabModifier.accept(NtmItems.REACTOR_GRADE_AMERICIUM_BILLET);
            tabModifier.accept(NtmItems.REACTOR_GRADE_AMERICIUM_ZFB_BILLET);
            tabModifier.accept(NtmItems.REACTOR_GRADE_AMERICIUM_NUGGET);

            tabModifier.accept(NtmItems.ARSENIC_INGOT);
            tabModifier.accept(NtmItems.ARSENIC_NUGGET);
            tabModifier.accept(NtmItems.ARSENIC_BRONZE_INGOT);
            tabModifier.accept(NtmItems.CAST_ARSENIC_BRONZE_PLATE);

            tabModifier.accept(NtmItems.ASBESTOS_SHEET);
            tabModifier.accept(NtmItems.ASBESTOS_POWDER);

            tabModifier.accept(NtmItems.ASH);
            tabModifier.accept(NtmItems.WOOD_ASH);
            tabModifier.accept(NtmItems.COAL_ASH);
            tabModifier.accept(NtmItems.FLY_ASH);
            tabModifier.accept(NtmItems.FINE_SOOT);

            tabModifier.accept(NtmItems.AUSTRALIUM_INGOT);
            tabModifier.accept(NtmItems.AUSTRALIUM_BILLET);
            tabModifier.accept(NtmItems.AUSTRALIUM_NUGGET);
            tabModifier.accept(NtmItems.LESSER_AUSTRALIUM_BILLET);
            tabModifier.accept(NtmItems.LESSER_AUSTRALIUM_NUGGET);
            tabModifier.accept(NtmItems.GREATER_AUSTRALIUM_BILLET);
            tabModifier.accept(NtmItems.GREATER_AUSTRALIUM_NUGGET);
            tabModifier.accept(NtmItems.AUSTRALIUM_POWDER);

            tabModifier.accept(NtmItems.BAKELITE_BAR);
            tabModifier.accept(NtmItems.BAKELITE_POWDER);

            tabModifier.accept(NtmItems.BALEFIRE_EGG);
            tabModifier.accept(NtmItems.BALEFIRE_SHARD);
            tabModifier.accept(NtmItems.THERMONUCLEAR_ASHES);

            tabModifier.accept(NtmItems.BERYLLIUM_INGOT);
            tabModifier.accept(NtmItems.BERYLLIUM_BILLET);
            tabModifier.accept(NtmItems.BERYLLIUM_NUGGET);
            tabModifier.accept(NtmItems.BERYLLIUM_POWDER);
            tabModifier.accept(NtmItems.BERYLLIUM_CRYSTALS);

            tabModifier.accept(NtmItems.BISMUTH_INGOT);
            tabModifier.accept(NtmItems.BISMUTH_BILLET);
            tabModifier.accept(NtmItems.BISMUTH_ZFB_BILLET);
            tabModifier.accept(NtmItems.BISMUTH_POWDER);
            tabModifier.accept(NtmItems.BISMUTH_NUGGET);
            tabModifier.accept(NtmItems.BISMUTH_BRONZE_INGOT);
            tabModifier.accept(NtmItems.CAST_BISMUTH_BRONZE_PLATE);

            tabModifier.accept(NtmItems.BORAX_POWDER);

            tabModifier.accept(NtmItems.BORON_INGOT);
            tabModifier.accept(NtmItems.BORON_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_BORON_POWDER);
            tabModifier.accept(NtmItems.BORON_FRAGMENT);

            tabModifier.accept(NtmItems.BROMINE_POWDER);

            tabModifier.accept(NtmItems.BSCCO_INGOT);
            tabModifier.accept(NtmItems.DENSE_BSCCO_WIRE);

            tabModifier.accept(NtmItems.CADMIUM_INGOT);
            tabModifier.accept(NtmItems.CADMIUM_POWDER);

            tabModifier.accept(NtmItems.CAESIUM_POWDER);
            tabModifier.accept(NtmItems.CAESIUM_137_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_CAESIUM_137_POWDER);

            tabModifier.accept(NtmItems.CALCIUM_INGOT);
            tabModifier.accept(NtmItems.CALCIUM_POWDER);

            tabModifier.accept(NtmItems.CADMIUM_STEEL_INGOT);
            tabModifier.accept(NtmItems.CAST_CADMIUM_STEEL_PLATE);
            tabModifier.accept(NtmItems.WELDED_CADMIUM_STEEL_PLATE);

            tabModifier.accept(NtmItems.CEMENT);

            tabModifier.accept(NtmItems.CERIUM_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_CERIUM_POWDER);
            tabModifier.accept(NtmItems.CERIUM_FRAGMENT);

            tabModifier.accept(NtmItems.CHLOROCALCITE);

            tabModifier.accept(NtmItems.CHLOROPHYTE_POWDER);

            tabModifier.accept(NtmItems.CINNABAR);
            tabModifier.accept(NtmItems.CINNABAR_CRYSTALS);

            tabModifier.accept(NtmItems.CMB_STEEL_INGOT);
            tabModifier.accept(NtmItems.CMB_STEEL_POWDER);
            tabModifier.accept(NtmItems.CAST_CMB_STEEL_PLATE);
            tabModifier.accept(NtmItems.WELDED_CMB_STEEL_PLATE);
            tabModifier.accept(NtmItems.CMB_STEEL_PLATE);

            tabModifier.accept(NtmItems.COAL_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_COAL_POWDER);
            tabModifier.accept(NtmItems.CARBON_WIRE);
            tabModifier.accept(NtmItems.COAL_BRIQUETTE);
            tabModifier.accept(NtmItems.COAL_COKE);

            tabModifier.accept(NtmItems.COBALT_INGOT);
            tabModifier.accept(NtmItems.COBALT_BILLET);
            tabModifier.accept(NtmItems.COBALT_POWDER);
            tabModifier.accept(NtmItems.COBALT_NUGGET);
            tabModifier.accept(NtmItems.TINY_PILE_OF_COBALT_POWDER);
            tabModifier.accept(NtmItems.COBALT_60_INGOT);
            tabModifier.accept(NtmItems.COBALT_60_BILLET);
            tabModifier.accept(NtmItems.COBALT_60_POWDER);
            tabModifier.accept(NtmItems.COBALT_60_NUGGET);
            tabModifier.accept(NtmItems.COBALT_FRAGMENT);
            tabModifier.accept(NtmItems.COBALT_CRYSTALS);

            tabModifier.accept(NtmItems.COLTAN);
            tabModifier.accept(NtmItems.CRUSHED_COLTAN);

            tabModifier.accept(NtmItems.COPPER_POWDER);
            tabModifier.accept(NtmItems.COPPER_PLATE);
            tabModifier.accept(NtmItems.CAST_COPPER_PLATE);
            tabModifier.accept(NtmItems.WELDED_COPPER_PLATE);
            tabModifier.accept(NtmItems.COPPER_PIPE);
            tabModifier.accept(NtmItems.COPPER_SHELL);
            tabModifier.accept(NtmItems.COPPER_WIRE);
            tabModifier.accept(NtmItems.DENSE_COPPER_WIRE);
            tabModifier.accept(NtmItems.COPPER_CRYSTALS);

            tabModifier.accept(NtmItems.CRYO_POWDER);

            tabModifier.accept(NtmItems.CRYOLITE_CHUNK);

            tabModifier.accept(NtmItems.DESH_INGOT);
            tabModifier.accept(NtmItems.DESH_BLEND);
            tabModifier.accept(NtmItems.DESHREADY_BLEND);
            tabModifier.accept(NtmItems.DESH_POWDER);
            tabModifier.accept(NtmItems.DESH_NUGGET);
            tabModifier.accept(NtmItems.CAST_DESH_PLATE);

            tabModifier.accept(NtmItems.DIAMOND_POWDER);
            tabModifier.accept(NtmItems.DIAMOND_CRYSTALS);

            tabModifier.accept(NtmItems.DINEUTRONIUM_INGOT);
            tabModifier.accept(NtmItems.DINEUTRONIUM_POWDER);
            tabModifier.accept(NtmItems.DINEUTRONIUM_NUGGET);
            tabModifier.accept(NtmItems.DENSE_DINEUTRONIUM_WIRE);

            tabModifier.accept(NtmItems.ELECTRONIUM_INGOT);

            tabModifier.accept(NtmItems.EMERALD_POWDER);

            tabModifier.accept(NtmItems.ENERGY_POWDER);

            tabModifier.accept(NtmItems.EUPHEMIUM_INGOT);
            tabModifier.accept(NtmItems.EUPHEMIUM_POWDER);
            tabModifier.accept(NtmItems.EUPHEMIUM_NUGGET);

            tabModifier.accept(NtmItems.FERRIC_SCHARBIDATE_INGOT);
            tabModifier.accept(NtmItems.FERRIC_SCHARBIDATE_POWDER);
            tabModifier.accept(NtmItems.CAST_FERRIC_SCHARBIDATE_PLATE);
            tabModifier.accept(NtmItems.DENSE_FERRIC_SCHARBIDATE_WIRE);

            tabModifier.accept(NtmItems.FERROURANIUM_INGOT);
            tabModifier.accept(NtmItems.CAST_FERROURANIUM_PLATE);

            tabModifier.accept(NtmItems.FLASH_GOLD);

            tabModifier.accept(NtmItems.FLASH_LEAD);

            tabModifier.accept(NtmItems.FLUORITE);
            tabModifier.accept(NtmItems.FLUORITE_CRYSTALS);

            tabModifier.accept(NtmItems.FLUX);

            tabModifier.accept(NtmItems.FULLERENE);
            tabModifier.accept(NtmItems.CRYSTALLINE_FULLERENE);

            tabModifier.accept(NtmItems.GHIORSIUM_336_INGOT);
            tabModifier.accept(NtmItems.GHIORSIUM_336_BILLET);
            tabModifier.accept(NtmItems.GHIORSIUM_336_NUGGET);

            tabModifier.accept(NtmItems.GOLD_POWDER);
            tabModifier.accept(NtmItems.GOLD_PLATE);
            tabModifier.accept(NtmItems.CAST_GOLD_PLATE);
            tabModifier.accept(NtmItems.GOLD_WIRE);
            tabModifier.accept(NtmItems.DENSE_GOLD_WIRE);
            tabModifier.accept(NtmItems.GOLD_CRYSTALS);
            tabModifier.accept(NtmItems.GOLD_198_INGOT);
            tabModifier.accept(NtmItems.GOLD_198_BILLET);
            tabModifier.accept(NtmItems.GOLD_198_POWDER);
            tabModifier.accept(NtmItems.GOLD_198_NUGGET);

            tabModifier.accept(NtmItems.GRAPHITE_INGOT);

            tabModifier.accept(NtmItems.GUNMETAL_INGOT);
            tabModifier.accept(NtmItems.GUNMETAL_PLATE);

            tabModifier.accept(NtmItems.HARD_PLASTIC_BAR);

            tabModifier.accept(NtmItems.HIGH_SPEED_STEEL_INGOT);
            tabModifier.accept(NtmItems.HIGH_SPEED_STEEL_POWDER);
            tabModifier.accept(NtmItems.CAST_HIGH_SPEED_STEEL_PLATE);
            tabModifier.accept(NtmItems.HIGH_SPEED_STEEL_PLATE);
            tabModifier.accept(NtmItems.HIGH_SPEED_STEEL_BOLT);
            tabModifier.accept(NtmItems.HIGH_SPEED_STEEL_PIPE);

            tabModifier.accept(NtmItems.IODINE_POWDER);
            tabModifier.accept(NtmItems.IODINE_131_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_IODINE_131_POWDER);

            tabModifier.accept(NtmItems.IRON_POWDER);
            tabModifier.accept(NtmItems.IRON_PLATE);
            tabModifier.accept(NtmItems.CAST_IRON_PLATE);
            tabModifier.accept(NtmItems.WELDED_IRON_PLATE);
            tabModifier.accept(NtmItems.IRON_PIPE);
            tabModifier.accept(NtmItems.IRON_CRYSTALS);

            tabModifier.accept(NtmItems.INDUSTRIAL_FERTILIZER);

            tabModifier.accept(NtmItems.INFERNAL_COAL);

            tabModifier.accept(NtmItems.SEMI_STABLE_LANTHANUM_INGOT);
            tabModifier.accept(NtmItems.LANTHANUM_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_LANTHANUM_POWDER);
            tabModifier.accept(NtmItems.LANTHANUM_FRAGMENT);

            tabModifier.accept(NtmItems.LAPIS_POWDER);
            tabModifier.accept(NtmItems.LAPIS_CRYSTALS);

            tabModifier.accept(NtmItems.LATEX);
            tabModifier.accept(NtmItems.LATEX_BAR);

            tabModifier.accept(NtmItems.LEAD_INGOT);
            tabModifier.accept(NtmItems.LEAD_NUGGET);
            tabModifier.accept(NtmItems.LEAD_209_INGOT);
            tabModifier.accept(NtmItems.LEAD_209_BILLET);
            tabModifier.accept(NtmItems.LEAD_209_NUGGET);
            tabModifier.accept(NtmItems.LEAD_POWDER);
            tabModifier.accept(NtmItems.LEAD_PLATE);
            tabModifier.accept(NtmItems.CAST_LEAD_PLATE);
            tabModifier.accept(NtmItems.LEAD_PIPE);
            tabModifier.accept(NtmItems.LEAD_BOLT);
            tabModifier.accept(NtmItems.LEAD_WIRE);
            tabModifier.accept(NtmItems.LEAD_CRYSTALS);

            tabModifier.accept(NtmItems.LIGNITE);
            tabModifier.accept(NtmItems.LIGNITE_POWDER);
            tabModifier.accept(NtmItems.LIGNITE_COKE);
            tabModifier.accept(NtmItems.LIGNITE_BRIQUETTE);

            tabModifier.accept(NtmItems.LIMESTONE_POWDER);

            tabModifier.accept(NtmItems.LITHIUM_CUBE);
            tabModifier.accept(NtmItems.LITHIUM_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_LITHIUM_POWDER);
            tabModifier.accept(NtmItems.LITHIUM_CRYSTALS);

            tabModifier.accept(NtmItems.MAGNETIZED_TUNGSTEN_INGOT);
            tabModifier.accept(NtmItems.MAGNETIZED_TUNGSTEN_POWDER);
            tabModifier.accept(NtmItems.MAGNETIZED_TUNGSTEN_WIRE);
            tabModifier.accept(NtmItems.DENSE_MAGNETIZED_TUNGSTEN_WIRE);

            tabModifier.accept(NtmItems.METEORITE_INGOT);
            tabModifier.accept(NtmItems.METEORITE_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_METEORITE_POWDER);
            tabModifier.accept(NtmItems.METEORITE_FRAGMENT);

            tabModifier.accept(NtmItems.MOLYSITE);

            tabModifier.accept(NtmItems.MOX_FUEL_INGOT);
            tabModifier.accept(NtmItems.MOX_FUEL_BILLET);
            tabModifier.accept(NtmItems.MOX_FUEL_NUGGET);

            tabModifier.accept(NtmItems.NEODYMIUM_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_NEODYMIUM_POWDER);
            tabModifier.accept(NtmItems.DENSE_NEODYMIUM_WIRE);
            tabModifier.accept(NtmItems.NEODYMIUM_FRAGMENT);

            tabModifier.accept(NtmItems.NEPTUNIUM_INGOT);
            tabModifier.accept(NtmItems.NEPTUNIUM_BILLET);
            tabModifier.accept(NtmItems.NEPTUNIUM_POWDER);
            tabModifier.accept(NtmItems.NEPTUNIUM_NUGGET);
            tabModifier.accept(NtmItems.NEPTUNIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.NEPTUNIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.NEPTUNIUM_FUEL_NUGGET);

            tabModifier.accept(NtmItems.NIOBIUM_INGOT);
            tabModifier.accept(NtmItems.NIOBIUM_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_NIOBIUM_POWDER);
            tabModifier.accept(NtmItems.NIOBIUM_NUGGET);
            tabModifier.accept(NtmItems.DENSE_NIOBIUM_WIRE);
            tabModifier.accept(NtmItems.NIOBIUM_FRAGMENT);

            tabModifier.accept(NtmItems.NITAN_BLEND);

            tabModifier.accept(NtmItems.NITER);
            tabModifier.accept(NtmItems.NITER_CRYSTALS);

            tabModifier.accept(NtmItems.NITRA);
            tabModifier.accept(NtmItems.SMALL_PILE_OF_NITRA);

            tabModifier.accept(NtmItems.OSMIRIDIUM_INGOT);
            tabModifier.accept(NtmItems.OSMIRIDIUM_NUGGET);
            tabModifier.accept(NtmItems.IMPURE_OSMIRIDIUM_POWDER);
            tabModifier.accept(NtmItems.CAST_OSMIRIDIUM_PLATE);
            tabModifier.accept(NtmItems.WELDED_OSMIRIDIUM_PLATE);
            tabModifier.accept(NtmItems.OSMIRIDIUM_CRYSTALS);

            tabModifier.accept(NtmItems.PALEOGENITE_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_PALEOGENITE_POWDER);

            tabModifier.accept(NtmItems.RED_PHOSPHORUS);
            tabModifier.accept(NtmItems.WHITE_PHOSPHORUS_BAR);
            tabModifier.accept(NtmItems.PHOSPHORUS_CRYSTALS);

            tabModifier.accept(NtmItems.PETROLEUM_COKE);

            tabModifier.accept(NtmItems.PLUTONIUM_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.REACTOR_GRADE_PLUTONIUM_INGOT);
            tabModifier.accept(NtmItems.REACTOR_GRADE_PLUTONIUM_BILLET);
            tabModifier.accept(NtmItems.REACTOR_GRADE_PLUTONIUM_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_238_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_238_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_238_BE_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_238_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_239_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_239_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_239_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_240_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_240_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_240_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_241_INGOT);
            tabModifier.accept(NtmItems.PLUTONIUM_241_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_241_ZFB_BILLET);
            tabModifier.accept(NtmItems.PLUTONIUM_241_NUGGET);
            tabModifier.accept(NtmItems.PLUTONIUM_POWDER);
            tabModifier.accept(NtmItems.PLUTONIUM_CRYSTALS);

            tabModifier.accept(NtmItems.POISON_POWDER);

            tabModifier.accept(NtmItems.POLONIUM_210_INGOT);
            tabModifier.accept(NtmItems.POLONIUM_210_BILLET);
            tabModifier.accept(NtmItems.POLONIUM_210_BE_BILLET);
            tabModifier.accept(NtmItems.POLONIUM_210_NUGGET);
            tabModifier.accept(NtmItems.POLONIUM_210_POWDER);

            tabModifier.accept(NtmItems.POLYMER_BAR);
            tabModifier.accept(NtmItems.POLYMER_POWDER);

            tabModifier.accept(NtmItems.PULVERIZED_ENCHANTMENT);

            tabModifier.accept(NtmItems.PVC_BAR);

            tabModifier.accept(NtmItems.QUARTZ_POWDER);

            tabModifier.accept(NtmItems.RADIUM_226_INGOT);
            tabModifier.accept(NtmItems.RADIUM_226_BILLET);
            tabModifier.accept(NtmItems.RADIUM_226_BE_BILLET);
            tabModifier.accept(NtmItems.RADIUM_226_POWDER);
            tabModifier.accept(NtmItems.RADIUM_226_NUGGET);

            tabModifier.accept(NtmItems.RARE_EARTH_ORE_CHUNK);
            tabModifier.accept(NtmItems.RARE_EARTH_CRYSTALS);

            tabModifier.accept(NtmItems.RED_COPPER_INGOT);
            tabModifier.accept(NtmItems.RED_COPPER_POWDER);
            tabModifier.accept(NtmItems.RED_COPPER_WIRE);

            tabModifier.accept(NtmItems.REDSTONE_CRYSTALS);

            tabModifier.accept(NtmItems.RUBBER_BAR);
            tabModifier.accept(NtmItems.RUBBER_PIPE);

            tabModifier.accept(NtmItems.SATURNITE_INGOT);
            tabModifier.accept(NtmItems.SATURNITE_PLATE);
            tabModifier.accept(NtmItems.CAST_SATURNITE_PLATE);
            tabModifier.accept(NtmItems.SATURNITE_SHELL);

            tabModifier.accept(NtmItems.SAWDUST_POWDER);
            tabModifier.accept(NtmItems.SAWDUST_BRIQUETTE);

            tabModifier.accept(NtmItems.SCHRABIDIUM_INGOT);
            tabModifier.accept(NtmItems.SCHRABIDIUM_BILLET);
            tabModifier.accept(NtmItems.SCHRABIDIUM_NUGGET);
            tabModifier.accept(NtmItems.SCHRABIDIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.SCHRABIDIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.SCHRABIDIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.SCHRABIDIUM_POWDER);
            tabModifier.accept(NtmItems.SCHRABIDIUM_PLATE);
            tabModifier.accept(NtmItems.CAST_SCHRABIDIUM_PLATE);
            tabModifier.accept(NtmItems.SCHRABIDIUM_WIRE);
            tabModifier.accept(NtmItems.DENSE_SCHRABIDIUM_WIRE);
            tabModifier.accept(NtmItems.SCHRABIDIUM_CRYSTALS);

            tabModifier.accept(NtmItems.SCHRARANIUM_INGOT);
            tabModifier.accept(NtmItems.SCHRARANIUM_CRYSTALS);

            tabModifier.accept(NtmItems.SEMTEX_BLEND);
            tabModifier.accept(NtmItems.SEMTEX_BAR);

            tabModifier.accept(NtmItems.SILICON_BOULE);
            tabModifier.accept(NtmItems.SILICON_WAFER);
            tabModifier.accept(NtmItems.PRINTED_SILICON_WAFER);
            tabModifier.accept(NtmItems.SILICON_NUGGET);

            tabModifier.accept(NtmItems.SODIUM_POWDER);

            tabModifier.accept(NtmItems.SOLINIUM_INGOT);
            tabModifier.accept(NtmItems.SOLINIUM_BILLET);
            tabModifier.accept(NtmItems.SOLINIUM_NUGGET);

            tabModifier.accept(NtmItems.SPARK_BLEND);

            tabModifier.accept(NtmItems.STARMETAL_INGOT);
            tabModifier.accept(NtmItems.DENSE_STARMETAL_WIRE);
            tabModifier.accept(NtmItems.STARMETAL_RING);
            tabModifier.accept(NtmItems.STARMETAL_CRYSTALS);

            tabModifier.accept(NtmItems.STRONTIUM_POWDER);
            tabModifier.accept(NtmItems.STRONTIUM_90_INGOT);
            tabModifier.accept(NtmItems.STRONTIUM_90_BILLET);
            tabModifier.accept(NtmItems.STRONTIUM_90_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_STRONTIUM_90_POWDER);
            tabModifier.accept(NtmItems.STRONTIUM_90_NUGGET);

            tabModifier.accept(NtmItems.STEEL_INGOT);
            tabModifier.accept(NtmItems.STEEL_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_STEEL_POWDER);
            tabModifier.accept(NtmItems.STEEL_PLATE);
            tabModifier.accept(NtmItems.CAST_STEEL_PLATE);
            tabModifier.accept(NtmItems.WELDED_STEEL_PLATE);
            tabModifier.accept(NtmItems.STEEL_BOLT);
            tabModifier.accept(NtmItems.STEEL_PIPE);
            tabModifier.accept(NtmItems.STEEL_SHELL);
            tabModifier.accept(NtmItems.STEEL_WIRE);

            tabModifier.accept(NtmItems.SULFUR);
            tabModifier.accept(NtmItems.SULFUR_CRYSTALS);

            tabModifier.accept(NtmItems.PURIFIED_TANTALITE);
            tabModifier.accept(NtmItems.TANTALUM_INGOT);
            tabModifier.accept(NtmItems.TANTALUM_POWDER);
            tabModifier.accept(NtmItems.TANTALUM_NUGGET);
            tabModifier.accept(NtmItems.TANTALUM_POLYCRYSTAL);

            tabModifier.accept(NtmItems.TECHNETIUM_99_INGOT);
            tabModifier.accept(NtmItems.TECHNETIUM_99_BILLET);
            tabModifier.accept(NtmItems.TECHNETIUM_99_NUGGET);

            tabModifier.accept(NtmItems.TECHNETIUM_STEEL_INGOT);
            tabModifier.accept(NtmItems.TECHNETIUM_STEEL_POWDER);
            tabModifier.accept(NtmItems.CAST_TECHNETIUM_STEEL_PLATE);
            tabModifier.accept(NtmItems.WELDED_TECHNETIUM_STEEL_PLATE);

            tabModifier.accept(NtmItems.TEKTITE_POWDER);

            tabModifier.accept(NtmItems.TENNESSINE_POWDER);

            tabModifier.accept(NtmItems.THERMITE);

            tabModifier.accept(NtmItems.THORIUM_232_INGOT);
            tabModifier.accept(NtmItems.THORIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.THORIUM_232_BILLET);
            tabModifier.accept(NtmItems.THORIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.THORIUM_232_NUGGET);
            tabModifier.accept(NtmItems.THORIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.THORIUM_POWDER);
            tabModifier.accept(NtmItems.THORIUM_CRYSTALS);

            tabModifier.accept(NtmItems.TITANIUM_INGOT);
            tabModifier.accept(NtmItems.TITANIUM_PLATE);
            tabModifier.accept(NtmItems.CAST_TITANIUM_PLATE);
            tabModifier.accept(NtmItems.WELDED_TITANIUM_PLATE);
            tabModifier.accept(NtmItems.TITANIUM_POWDER);
            tabModifier.accept(NtmItems.TITANIUM_SHELL);
            tabModifier.accept(NtmItems.DENSE_TITANIUM_WIRE);
            tabModifier.accept(NtmItems.TITANIUM_CRYSTALS);

            tabModifier.accept(NtmItems.TRIXITE_CRYSTALS);

            tabModifier.accept(NtmItems.TUNGSTEN_INGOT);
            tabModifier.accept(NtmItems.TUNGSTEN_POWDER);
            tabModifier.accept(NtmItems.TUNGSTEN_BOLT);
            tabModifier.accept(NtmItems.TUNGSTEN_WIRE);
            tabModifier.accept(NtmItems.DENSE_TUNGSTEN_WIRE);
            tabModifier.accept(NtmItems.TUNGSTEN_CRYSTALS);

            tabModifier.accept(NtmItems.URANIUM_INGOT);
            tabModifier.accept(NtmItems.URANIUM_BILLET);
            tabModifier.accept(NtmItems.URANIUM_NUGGET);
            tabModifier.accept(NtmItems.URANIUM_FUEL_INGOT);
            tabModifier.accept(NtmItems.URANIUM_FUEL_BILLET);
            tabModifier.accept(NtmItems.URANIUM_FUEL_NUGGET);
            tabModifier.accept(NtmItems.URANIUM_233_INGOT);
            tabModifier.accept(NtmItems.URANIUM_233_BILLET);
            tabModifier.accept(NtmItems.URANIUM_233_NUGGET);
            tabModifier.accept(NtmItems.URANIUM_235_INGOT);
            tabModifier.accept(NtmItems.URANIUM_235_BILLET);
            tabModifier.accept(NtmItems.URANIUM_235_NUGGET);
            tabModifier.accept(NtmItems.URANIUM_238_INGOT);
            tabModifier.accept(NtmItems.URANIUM_238_BILLET);
            tabModifier.accept(NtmItems.URANIUM_238_NUGGET);
            tabModifier.accept(NtmItems.URANIUM_POWDER);
            tabModifier.accept(NtmItems.URANIUM_CRYSTALS);

            tabModifier.accept(NtmItems.VOLCANIC_GEM);

            tabModifier.accept(NtmItems.WEAPON_STEEL_INGOT);
            tabModifier.accept(NtmItems.WEAPON_STEEL_PLATE);
            tabModifier.accept(NtmItems.CAST_WEAPON_STEEL_PLATE);
            tabModifier.accept(NtmItems.WEAPON_STEEL_SHELL);

            tabModifier.accept(NtmItems.XENON_135_POWDER);
            tabModifier.accept(NtmItems.TINY_PILE_OF_XENON_135_POWDER);

            tabModifier.accept(NtmItems.YHARONITE_BILLET);

            tabModifier.accept(NtmItems.YELLOWCAKE);

            tabModifier.accept(NtmItems.ZIRCONIUM_SPLINTER);
            tabModifier.accept(NtmItems.ZIRCONIUM_CUBE);
            tabModifier.accept(NtmItems.ZIRCONIUM_BILLET);
            tabModifier.accept(NtmItems.ZIRCONIUM_POWDER);
            tabModifier.accept(NtmItems.CAST_ZIRCONIUM_PLATE);
            tabModifier.accept(NtmItems.WELDED_ZIRCONIUM_PLATE);
            tabModifier.accept(NtmItems.ZIRCONIUM_WIRE);
        });
        NtmPlatform.modifyCreativeTab(MACHINE_ITEMS_AND_FUEL_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.BATTERY);
            tabModifier.accept(charged(NtmItems.BATTERY, 5_000L));
            tabModifier.accept(NtmItems.REDSTONE_POWER_CELL);
            tabModifier.accept(charged(NtmItems.REDSTONE_POWER_CELL, 15_000L));
            tabModifier.accept(NtmItems.SIXFOLD_REDSTONE_POWER_CELL);
            tabModifier.accept(charged(NtmItems.SIXFOLD_REDSTONE_POWER_CELL, 90_000L));
            tabModifier.accept(NtmItems.TWENTY_FOUR_FOLD_REDSTONE_POWER_CELL);
            tabModifier.accept(charged(NtmItems.TWENTY_FOUR_FOLD_REDSTONE_POWER_CELL, 360_000L));
            tabModifier.accept(NtmItems.ADVANCED_BATTERY);
            tabModifier.accept(charged(NtmItems.ADVANCED_BATTERY, 20_000L));
            tabModifier.accept(NtmItems.ADVANCED_POWER_CELL);
            tabModifier.accept(charged(NtmItems.ADVANCED_POWER_CELL, 60_000L));
            tabModifier.accept(NtmItems.QUADRUPLE_ADVANCED_POWER_CELL);
            tabModifier.accept(charged(NtmItems.QUADRUPLE_ADVANCED_POWER_CELL, 240_000L));
            tabModifier.accept(NtmItems.TWELVEFOLD_ADVANCED_POWER_CELL);
            tabModifier.accept(charged(NtmItems.TWELVEFOLD_ADVANCED_POWER_CELL, 720_000L));
            tabModifier.accept(NtmItems.LITHIUM_ION_BATTERY);
            tabModifier.accept(charged(NtmItems.LITHIUM_ION_BATTERY, 250_000L));
            tabModifier.accept(NtmItems.LITHIUM_ION_POWER_CELL);
            tabModifier.accept(charged(NtmItems.LITHIUM_ION_POWER_CELL, 750_000L));
            tabModifier.accept(NtmItems.TRIPLE_LITHIUM_ION_POWER_CELL);
            tabModifier.accept(charged(NtmItems.TRIPLE_LITHIUM_ION_POWER_CELL, 2_250_000L));
            tabModifier.accept(NtmItems.SIXFOLD_LITHIUM_ION_POWER_CELL);
            tabModifier.accept(charged(NtmItems.SIXFOLD_LITHIUM_ION_POWER_CELL, 4_500_000L));
            tabModifier.accept(NtmItems.SCHRABIDIUM_BATTERY);
            tabModifier.accept(charged(NtmItems.SCHRABIDIUM_BATTERY, 1_000_000L));
            tabModifier.accept(NtmItems.SCHRABIDIUM_POWER_CELL);
            tabModifier.accept(charged(NtmItems.SCHRABIDIUM_POWER_CELL, 3_000_000L));
            tabModifier.accept(NtmItems.DOUBLE_SCHRABIDIUM_POWER_CELL);
            tabModifier.accept(charged(NtmItems.DOUBLE_SCHRABIDIUM_POWER_CELL, 6_000_000L));
            tabModifier.accept(NtmItems.QUADRUPLE_SCHRABIDIUM_POWER_CELL);
            tabModifier.accept(charged(NtmItems.QUADRUPLE_SCHRABIDIUM_POWER_CELL, 12_000_000L));
            tabModifier.accept(NtmItems.SPARK_BATTERY);
            tabModifier.accept(charged(NtmItems.SPARK_BATTERY, 100_000_000L));
            tabModifier.accept(NtmItems.OFF_BRAND_SPARK_BATTERY);
            tabModifier.accept(charged(NtmItems.OFF_BRAND_SPARK_BATTERY, 5_000_000L));
            tabModifier.accept(NtmItems.SPARK_POWER_CELL);
            tabModifier.accept(charged(NtmItems.SPARK_POWER_CELL, 600_000_000L));
            tabModifier.accept(NtmItems.SPARK_ARCANE_CAR_BATTERY);
            tabModifier.accept(charged(NtmItems.SPARK_ARCANE_CAR_BATTERY, 2_500_000_000L));
            tabModifier.accept(NtmItems.SPARK_ARCANE_ENERGY_STORAGE_ARRAY);
            tabModifier.accept(charged(NtmItems.SPARK_ARCANE_ENERGY_STORAGE_ARRAY, 10_000_000_000L));
            tabModifier.accept(NtmItems.SPARK_ARCANE_MASS_ENERGY_VOID);
            tabModifier.accept(charged(NtmItems.SPARK_ARCANE_MASS_ENERGY_VOID, 100_000_000_000L));
            tabModifier.accept(NtmItems.SPARK_ARCANE_DIRAC_SEA);
            tabModifier.accept(charged(NtmItems.SPARK_ARCANE_DIRAC_SEA, 250_000_000_000L));
            tabModifier.accept(NtmItems.SPARK_SOLID_SPACE_TIME_CRYSTAL);
            tabModifier.accept(charged(NtmItems.SPARK_SOLID_SPACE_TIME_CRYSTAL, 1_000_000_000_000L));
            tabModifier.accept(NtmItems.SPARK_LUDICROUS_ENERGY_STORAGE_UNIT);
            tabModifier.accept(charged(NtmItems.SPARK_LUDICROUS_ENERGY_STORAGE_UNIT, 100_000_000_000_000L));
            tabModifier.accept(NtmItems.ELECTRONIUM_CUBE);
            tabModifier.accept(charged(NtmItems.ELECTRONIUM_CUBE, 1_000_000_000_000_000_000L));
            tabModifier.accept(NtmItems.INFINITE_BATTERY);
            tabModifier.accept(NtmItems.POTATO_BATTERY);
            tabModifier.accept(NtmItems.POTATOS);
            tabModifier.accept(NtmItems.SELF_CHARGING_URANIUM_238_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_TECHNETIUM_99_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_PLUTONIUM_238_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_POLONIUM_210_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_GOLD_198_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_LEAD_209_BATTERY);
            tabModifier.accept(NtmItems.SELF_CHARGING_AMERICIUM_241_BATTERY);
        });
        NtmPlatform.modifyCreativeTab(TEMPLATES_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.NULL);
        });
        NtmPlatform.modifyCreativeTab(ORES_AND_BLOCKS_KEY, tabModifier -> {
            tabModifier.accept(NtmBlocks.URANIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.SCORCHED_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.TITANIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_TITANIUM_ORE);
            tabModifier.accept(NtmBlocks.SULFUR_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_SULFUR_ORE);
            tabModifier.accept(NtmBlocks.THORIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_THORIUM_ORE);
            tabModifier.accept(NtmBlocks.NITER_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_NITER_ORE);
            tabModifier.accept(NtmBlocks.TUNGSTEN_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_TUNGSTEN_ORE);
            tabModifier.accept(NtmBlocks.ALUMINIUM_BEARING_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_ALUMINIUM_BEARING_ORE);
            tabModifier.accept(NtmBlocks.FLUORITE_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_FLUORITE_ORE);
            tabModifier.accept(NtmBlocks.LEAD_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_LEAD_ORE);
            tabModifier.accept(NtmBlocks.SCHRABIDIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_SCHRABIDIUM_ORE);
            tabModifier.accept(NtmBlocks.BERYLLIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_BERYLLIUM_ORE);
            tabModifier.accept(NtmBlocks.AUSTRALIUM_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE);
            tabModifier.accept(NtmBlocks.RARE_EARTH_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_RARE_EARTH_ORE);
            tabModifier.accept(NtmBlocks.COBALT_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_COBALT_ORE);
            tabModifier.accept(NtmBlocks.CINNEBAR_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_CINNEBAR_ORE);
            tabModifier.accept(NtmBlocks.COLTAN_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_COLTAN_ORE);
            tabModifier.accept(NtmBlocks.LIGNITE_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_LIGNITE_ORE);
            tabModifier.accept(NtmBlocks.ASBESTOS_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_ASBESTOS_ORE);
            tabModifier.accept(NtmBlocks.OIL_DEPOSIT);
            tabModifier.accept(NtmBlocks.DEEPSLATE_OIL_DEPOSIT);
            tabModifier.accept(NtmBlocks.EMPTY_OIL_DEPOSIT);
            tabModifier.accept(NtmBlocks.DEEPSLATE_EMPTY_OIL_DEPOSIT);
            tabModifier.accept(NtmBlocks.ALUMINIUM_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEEPSLATE_ALUMINIUM_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.COPPER_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEEPSLATE_COPPER_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.IRON_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEEPSLATE_IRON_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.TITANIUM_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEEPSLATE_TITANIUM_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEAD_DIRT);
            tabModifier.accept(NtmBlocks.OILY_DIRT);
            tabModifier.accept(NtmBlocks.OILY_SAND);
            tabModifier.accept(NtmBlocks.DEPTH_ROCK);
            tabModifier.accept(NtmBlocks.DEPTH_CINNABAR_ORE);
            tabModifier.accept(NtmBlocks.DEPTH_ZIRCONIUM_ORE);
            tabModifier.accept(NtmBlocks.DEPTH_BORAX_ORE);
            tabModifier.accept(NtmBlocks.DEPTH_IRON_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER);
            tabModifier.accept(NtmBlocks.ALEXANDRITE_ORE);
            tabModifier.accept(NtmBlocks.DEEPSLATE_ALEXANDRITE_ORE);
            tabModifier.accept(NtmBlocks.VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.GEM_RICH_VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT);
            tabModifier.accept(NtmBlocks.SMOLDERING_NETHERRACK);
            tabModifier.accept(NtmBlocks.NETHER_COAL_ORE);
            tabModifier.accept(NtmBlocks.NETHER_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.NETHER_PLUTONIUM_ORE);
            tabModifier.accept(NtmBlocks.NETHER_TUNGSTEN_ORE);
            tabModifier.accept(NtmBlocks.NETHER_SULFUR_ORE);
            tabModifier.accept(NtmBlocks.NETHER_COBALT_ORE);
            tabModifier.accept(NtmBlocks.NETHER_SCHRABIDIUM_ORE);
            tabModifier.accept(NtmBlocks.NETHER_DEPTH_ROCK);
            tabModifier.accept(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE);
            tabModifier.accept(NtmBlocks.METEORITE_BLOCK);
            tabModifier.accept(NtmBlocks.BROKEN_METEORITE_BLOCK);
            tabModifier.accept(NtmBlocks.METEORITE_COBBLESTONE);
            tabModifier.accept(NtmBlocks.HOT_METEORITE_COBBLESTONE);
            tabModifier.accept(NtmBlocks.METEORITE_TREASURE_BLOCK);
            tabModifier.accept(NtmBlocks.METEOR_IRON_ORE);
            tabModifier.accept(NtmBlocks.METEOR_COPPER_ORE);
            tabModifier.accept(NtmBlocks.METEOR_ALUMINIUM_ORE);
            tabModifier.accept(NtmBlocks.METEOR_RARE_EARTH_ORE);
            tabModifier.accept(NtmBlocks.METEOR_COBALT_ORE);
            tabModifier.accept(NtmBlocks.GRAPHITIC_SCHIST);
            tabModifier.accept(NtmBlocks.SCHIST_IRON_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_GOLD_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_COPPER_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_ASBESTOS_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_LITHIUM_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_SCHRABIDIUM_ORE);
            tabModifier.accept(NtmBlocks.SCHIST_RARE_EARTH_ORE);
            tabModifier.accept(NtmBlocks.GAS_SHALE);
            tabModifier.accept(NtmBlocks.BAUXITE);
            tabModifier.accept(NtmBlocks.CHRYSOTILE);
            tabModifier.accept(NtmBlocks.HEMATITE);
            tabModifier.accept(NtmBlocks.LIMESTONE);
            tabModifier.accept(NtmBlocks.MALACHITE);
            tabModifier.accept(NtmBlocks.SULFUROUS_STONE);
            tabModifier.accept(NtmBlocks.TEKTITE);
            tabModifier.accept(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE);
            tabModifier.accept(NtmBlocks.TRIXITE_ORE);
            tabModifier.accept(NtmBlocks.GEOTHERMAL_VENT);
            tabModifier.accept(NtmBlocks.BEDROCK_OIL_DEPOSIT);
            tabModifier.accept(NtmBlocks.BEDROCK_ORE);
            tabModifier.accept(NtmItems.RAW_URANIUM);
            tabModifier.accept(NtmItems.RAW_SCORCHED_URANIUM);
            tabModifier.accept(NtmItems.RAW_PLUTONIUM);
            tabModifier.accept(NtmItems.RAW_THORIUM);
            tabModifier.accept(NtmItems.RAW_TITANIUM);
            tabModifier.accept(NtmItems.RAW_TUNGSTEN);
            tabModifier.accept(NtmItems.RAW_CRYOLITE);
            tabModifier.accept(NtmItems.RAW_BERYLLIUM);
            tabModifier.accept(NtmItems.RAW_LEAD);
            tabModifier.accept(NtmItems.RAW_SCHRABIDIUM);
            tabModifier.accept(NtmItems.RAW_AUSTRALIUM);
            tabModifier.accept(NtmItems.RAW_METEORIC_IRON);
            tabModifier.accept(NtmItems.RAW_METEORIC_COPPER);
            tabModifier.accept(NtmItems.RAW_METEORIC_ALUMINIUM);
            tabModifier.accept(NtmItems.RAW_METEORIC_RARE_EARTH);
            tabModifier.accept(NtmItems.RAW_METEORIC_COBALT);
            tabModifier.accept(NtmItems.RAW_TRIXITE);
            tabModifier.accept(NtmItems.RAW_OSMIRIDIUM_INFUSED_TEKTITE);

            tabModifier.accept(NtmBlocks.ACTINIUM_227_BLOCK);
            tabModifier.accept(NtmBlocks.ADVANCED_ALLOY_BLOCK);
            tabModifier.accept(NtmBlocks.ALUMINIUM_BLOCK);
            tabModifier.accept(NtmBlocks.ASBESTOS_BLOCK);
            tabModifier.accept(NtmBlocks.AUSTRALIUM_BLOCK);
            tabModifier.accept(NtmBlocks.BAKELITE_BLOCK);
            tabModifier.accept(NtmBlocks.BERYLLIUM_BLOCK);
            tabModifier.accept(NtmBlocks.BISMUTH_BLOCK);
            tabModifier.accept(NtmBlocks.BORON_BLOCK);
            tabModifier.accept(NtmBlocks.CADMIUM_BLOCK);
            tabModifier.accept(NtmBlocks.CADMIUM_STEEL_BLOCK);
            tabModifier.accept(NtmBlocks.CMB_STEEL_BLOCK);
            tabModifier.accept(NtmBlocks.COAL_COKE_BLOCK);
            tabModifier.accept(NtmBlocks.COBALT_BLOCK);
            tabModifier.accept(NtmBlocks.COLTAN_BLOCK);
            tabModifier.accept(NtmBlocks.DESH_BLOCK);
            tabModifier.accept(NtmBlocks.DINEUTRONIUM_BLOCK);
            tabModifier.accept(NtmBlocks.EUPHEMIUM_BLOCK);
            tabModifier.accept(NtmBlocks.FERRIC_SCHRABIDATE_BLOCK);
            tabModifier.accept(NtmBlocks.FLUORITE_BLOCK);
            tabModifier.accept(NtmBlocks.GRAPHITE_BLOCK);
            tabModifier.accept(NtmBlocks.HIGH_SPEED_STEEL_BLOCK);
            tabModifier.accept(NtmBlocks.LIGNITE_COKE_BLOCK);
            tabModifier.accept(NtmBlocks.LEAD_BLOCK);
            tabModifier.accept(NtmBlocks.LITHIUM_BLOCK);
            tabModifier.accept(NtmBlocks.MAGNETIZED_TUNGSTEN_BLOCK);
            tabModifier.accept(NtmBlocks.MOX_FUEL_BLOCK);
            tabModifier.accept(NtmBlocks.NEPTUNIUM_BLOCK);
            tabModifier.accept(NtmBlocks.NIOBIUM_BLOCK);
            tabModifier.accept(NtmBlocks.NITER_BLOCK);
            tabModifier.accept(NtmBlocks.PETROLEUM_COKE_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_FUEL_BLOCK);
            tabModifier.accept(NtmBlocks.REACTOR_GRADE_PLUTONIUM_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_238_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_239_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_240_BLOCK);
            tabModifier.accept(NtmBlocks.PLUTONIUM_241_BLOCK);
            tabModifier.accept(NtmBlocks.POLONIUM_210_BLOCK);
            tabModifier.accept(NtmBlocks.POLYMER_BLOCK);
            tabModifier.accept(NtmBlocks.RADIUM_226_BLOCK);
            tabModifier.accept(NtmBlocks.RED_COPPER_BLOCK);
            tabModifier.accept(NtmBlocks.RED_PHOSPHORUS_BLOCK);
            tabModifier.accept(NtmBlocks.RUBBER_BLOCK);
            tabModifier.accept(NtmBlocks.SCHRABIDIUM_BLOCK);
            tabModifier.accept(NtmBlocks.SCHRABIDIUM_FUEL_BLOCK);
            tabModifier.accept(NtmBlocks.SCHRARANIUM_BLOCK);
            tabModifier.accept(NtmBlocks.SEMTEX_BLOCK);
            tabModifier.accept(NtmBlocks.SOLINIUM_BLOCK);
            tabModifier.accept(NtmBlocks.STARMETAL_BLOCK);
            tabModifier.accept(NtmBlocks.STEEL_BLOCK);
            tabModifier.accept(NtmBlocks.SULFUR_BLOCK);
            tabModifier.accept(NtmBlocks.TANTALUM_BLOCK);
            tabModifier.accept(NtmBlocks.TECHNETIUM_STEEL_BLOCK);
            tabModifier.accept(NtmBlocks.THORIUM_232_BLOCK);
            tabModifier.accept(NtmBlocks.THORIUM_FUEL_BLOCK);
            tabModifier.accept(NtmBlocks.TITANIUM_BLOCK);
            tabModifier.accept(NtmBlocks.TUNGSTEN_BLOCK);
            tabModifier.accept(NtmBlocks.URANIUM_BLOCK);
            tabModifier.accept(NtmBlocks.URANIUM_FUEL_BLOCK);
            tabModifier.accept(NtmBlocks.URANIUM_233_BLOCK);
            tabModifier.accept(NtmBlocks.URANIUM_235_BLOCK);
            tabModifier.accept(NtmBlocks.URANIUM_238_BLOCK);
            tabModifier.accept(NtmBlocks.WHITE_PHOSPHORUS_BLOCK);
            tabModifier.accept(NtmBlocks.YELLOWCAKE_BLOCK);
            tabModifier.accept(NtmBlocks.ZIRCONIUM_BLOCK);
        });
        NtmPlatform.modifyCreativeTab(MACHINES_KEY, tabModifier -> {
            tabModifier.accept(NtmBlocks.TEMP_CABLE);
            tabModifier.accept(NtmBlocks.POTATO_BATTERY_BLOCK);
            tabModifier.accept(NtmBlocks.ENERGY_STORAGE_BLOCK);
            tabModifier.accept(NtmBlocks.LITHIUM_ION_ENERGY_STORAGE_BLOCK);
            tabModifier.accept(NtmBlocks.SCHRABIDIUM_ENERGY_STORAGE_BLOCK);
            tabModifier.accept(NtmBlocks.SPARK_ENERGY_STORAGE_BLOCK);
            tabModifier.accept(NtmBlocks.ALLOY_FURNACE);
            tabModifier.accept(NtmBlocks.ALLOY_FURNACE_EXTENSION);
            tabModifier.accept(NtmBlocks.ELECTRIC_FURNACE);
        });
        NtmPlatform.modifyCreativeTab(BOMBS_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.NULL);
        });
        NtmPlatform.modifyCreativeTab(MISSILES_AND_SATELLITES_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.NULL);
        });
        NtmPlatform.modifyCreativeTab(WEAPONS_AND_TURRETS_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.NULL);
        });
        NtmPlatform.modifyCreativeTab(CONSUMABLES_AND_GEAR_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.DOSIMETER);
            tabModifier.accept(NtmItems.GEIGER_COUNTER);

            tabModifier.accept(NtmItems.EMPTY_SYRINGE);
            tabModifier.accept(NtmItems.POISONOUS_INJECTION);
            tabModifier.accept(NtmItems.ANTIDOTE);
            tabModifier.accept(NtmItems.AWESOME);
            tabModifier.accept(NtmItems.METAL_SYRINGE);
            tabModifier.accept(NtmItems.STIMPAK);
            tabModifier.accept(NtmItems.MED_X);
            tabModifier.accept(NtmItems.PSYCHO);
            tabModifier.accept(NtmItems.SUPER_STIMPAK);
            tabModifier.accept(NtmItems.WATERY_TAINT_INJECTION);
            tabModifier.accept(NtmItems.FIRST_AID_KIT);
            tabModifier.accept(NtmItems.IV_BAG);
            tabModifier.accept(NtmItems.BLOOD_BAG);
            tabModifier.accept(NtmItems.EMPTY_EXPERIENCE_BAG);
            tabModifier.accept(NtmItems.EXPERIENCE_BAG);
            tabModifier.accept(NtmItems.RAD_AWAY);
            tabModifier.accept(NtmItems.STRONG_RAD_AWAY);
            tabModifier.accept(NtmItems.ELITE_RAD_AWAY);
            tabModifier.accept(NtmItems.RAD_X);
            tabModifier.accept(NtmItems.IODINE_PILL);

            tabModifier.accept(NtmItems.PLAN_C);

            tabModifier.accept(NtmItems.WAFFLE_OF_MASS_DESTRUCTION);
            tabModifier.accept(NtmItems.VEGAN_SCHNITZEL);
            tabModifier.accept(NtmItems.RADIOACTIVE_COTTON_CANDY);
            tabModifier.accept(NtmItems.BASIC_LEAD_APPLE);
            tabModifier.accept(NtmItems.GOOD_LEAD_APPLE);
            tabModifier.accept(NtmItems.EPIC_LEAD_APPLE);
            tabModifier.accept(NtmItems.BASIC_SCHRABIDIUM_APPLE);
            tabModifier.accept(NtmItems.GOOD_SCHRABIDIUM_APPLE);
            tabModifier.accept(NtmItems.EPIC_SCHRABIDIUM_APPLE);
            tabModifier.accept(NtmItems.EUPHEMIUM_APPLE);
            tabModifier.accept(NtmItems.CHEAP_TEM_FLAKES);
            tabModifier.accept(NtmItems.TEM_FLAKES);
            tabModifier.accept(NtmItems.EXPENSIVE_TEM_FLAKES);
            tabModifier.accept(NtmItems.GLOWING_MUSHROOM_STEW);
            tabModifier.accept(NtmItems.SCRAMBLED_BALEFIRE_EGG);
            tabModifier.accept(NtmItems.SCRAMBLED_BALEFIRE_EGG_AND_HAM);
            tabModifier.accept(NtmItems.LEMON);
            tabModifier.accept(NtmItems.MRE);
            tabModifier.accept(NtmItems.LOOPS);
            tabModifier.accept(NtmItems.IT_BREAKFAST);
            tabModifier.accept(NtmItems.SPONGEBOB_MACARONI);
            tabModifier.accept(NtmItems.FOOD_ITEM);
            tabModifier.accept(NtmItems.TWINKIE);
            tabModifier.accept(NtmItems.TV_STATIC_SANDWICH);
            tabModifier.accept(NtmItems.PUDDING);
            tabModifier.accept(NtmItems.SCRAP_PANCAKE);
            tabModifier.accept(NtmItems.CHICKEN_NUGGET);
            tabModifier.accept(NtmItems.PEAS);
            tabModifier.accept(NtmItems.MARSHMALLOW_ON_A_STICK);
            tabModifier.accept(NtmItems.ROASTED_MARSHMALLOW_ON_A_STICK);
            tabModifier.accept(NtmItems.CHEESE);
            tabModifier.accept(NtmItems.CHEESE_QUESADILLA);
            tabModifier.accept(NtmItems.GLYPHID_MEAT);
            tabModifier.accept(NtmItems.GRILLED_GLYPHID_MEAT);
            tabModifier.accept(NtmItems.GLYPHID_EGG);
            tabModifier.accept(NtmItems.IPECAC_SYRUP);
            tabModifier.accept(NtmItems.PTSD_MEDICATION);
            tabModifier.accept(NtmItems.STYLISH_FLASK);
            tabModifier.accept(NtmItems.ARIZONA_MUCHO_MANGO);
            tabModifier.accept(NtmItems.RADIUM_CHOCOLATE);
            tabModifier.accept(NtmItems.EMPTY_CAN);
            tabModifier.accept(NtmItems.RING_PULL);
            tabModifier.accept(NtmItems.SMART_ENERGY_DRINK);
            tabModifier.accept(NtmItems.CREATURE_ENERGY_DRINK);
            tabModifier.accept(NtmItems.RED_BOMB_ENERGY_DRINK);
            tabModifier.accept(NtmItems.DR_SUGAR_SOFT_DRINK);
            tabModifier.accept(NtmItems.OVERCHARGE_DELIRIUM_XT);
            tabModifier.accept(NtmItems.BLACK_MESA_LUNA_DARK_COLA);
            tabModifier.accept(NtmItems.BEPIS);
            tabModifier.accept(NtmItems.DR_BREENS_PRIVATE_RESERVE);
            tabModifier.accept(NtmItems.MUG_ROOT_BEER);
            tabModifier.accept(NtmItems.COFFEE);
            tabModifier.accept(NtmItems.RADIUM_COFFEE);
            tabModifier.accept(NtmItems.EMPTY_BOTTLE);
            tabModifier.accept(NtmItems.EMPTY_BOMB_BOTTLE);
            tabModifier.accept(NtmItems.NUKA_COLA_BOTTLE_CAP);
            tabModifier.accept(NtmItems.NUKA_COLA_QUANTUM_BOTTLE_CAP);
            tabModifier.accept(NtmItems.S_COLA_BOTTLE_CAP);
            tabModifier.accept(NtmItems.S_COLA_RAD_BOTTLE_CAP);
            tabModifier.accept(NtmItems.KAROL_BOTTLE_CAP);
            tabModifier.accept(NtmItems.FRITZ_COLA_BOTTLE_CAP);
            tabModifier.accept(NtmItems.BOTTLE_OF_NUKA_COLA);
            tabModifier.accept(NtmItems.BOTTLE_OF_NUKA_CHERRY);
            tabModifier.accept(NtmItems.BOTTLE_OF_NUKA_COLA_QUANTUM);
            tabModifier.accept(NtmItems.BOTTLE_OF_S_COLA);
            tabModifier.accept(NtmItems.BOTTLE_OF_S_COLA_RAD);
            tabModifier.accept(NtmItems.BOTTLE_OF_KAROL);
            tabModifier.accept(NtmItems.FIRST_BOTTLE_OF_KAROL);
            tabModifier.accept(NtmItems.BOTTLE_OF_FRITZ_COLA);
            tabModifier.accept(NtmItems.FIRST_BOTTLE_OF_FRITZ_COLA);
            tabModifier.accept(NtmItems.BOTTLE_OPENER);

            tabModifier.accept(NtmItems.CONSTRUCTION_WAND);
            tabModifier.accept(NtmItems.DEBUG_WAND);
            tabModifier.accept(NtmItems.NETWORK_DEBUG_TOOL);
        });
        NtmPlatform.modifyCreativeTab(TOOLS_KEY, tabModifier -> {
            tabModifier.accept(NtmItems.STEEL_SWORD);
            tabModifier.accept(NtmItems.STEEL_PICKAXE);
            tabModifier.accept(NtmItems.STEEL_AXE);
            tabModifier.accept(NtmItems.STEEL_SHOVEL);
            tabModifier.accept(NtmItems.STEEL_HOE);
            tabModifier.accept(NtmItems.TITANIUM_SWORD);
            tabModifier.accept(NtmItems.TITANIUM_PICKAXE);
            tabModifier.accept(NtmItems.TITANIUM_AXE);
            tabModifier.accept(NtmItems.TITANIUM_SHOVEL);
            tabModifier.accept(NtmItems.TITANIUM_HOE);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_SWORD);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_PICKAXE);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_AXE);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_SHOVEL);
            tabModifier.accept(NtmItems.ADVANCED_ALLOY_HOE);
            tabModifier.accept(NtmItems.CMB_STEEL_SWORD);
            tabModifier.accept(NtmItems.CMB_STEEL_PICKAXE);
            tabModifier.accept(NtmItems.CMB_STEEL_AXE);
            tabModifier.accept(NtmItems.CMB_STEEL_SHOVEL);
            tabModifier.accept(NtmItems.CMB_STEEL_HOE);
            tabModifier.accept(NtmItems.COBALT_SWORD);
            tabModifier.accept(NtmItems.COBALT_PICKAXE);
            tabModifier.accept(NtmItems.COBALT_AXE);
            tabModifier.accept(NtmItems.COBALT_SHOVEL);
            tabModifier.accept(NtmItems.COBALT_HOE);
            tabModifier.accept(NtmItems.DECORATED_COBALT_SWORD);
            tabModifier.accept(NtmItems.DECORATED_COBALT_PICKAXE);
            tabModifier.accept(NtmItems.DECORATED_COBALT_AXE);
            tabModifier.accept(NtmItems.DECORATED_COBALT_SHOVEL);
            tabModifier.accept(NtmItems.DECORATED_COBALT_HOE);
            tabModifier.accept(NtmItems.STARMETAL_SWORD);
            tabModifier.accept(NtmItems.STARMETAL_PICKAXE);
            tabModifier.accept(NtmItems.STARMETAL_AXE);
            tabModifier.accept(NtmItems.STARMETAL_SHOVEL);
            tabModifier.accept(NtmItems.STARMETAL_HOE);
            tabModifier.accept(NtmItems.DESH_SWORD);
            tabModifier.accept(NtmItems.DESH_PICKAXE);
            tabModifier.accept(NtmItems.DESH_AXE);
            tabModifier.accept(NtmItems.DESH_SHOVEL);
            tabModifier.accept(NtmItems.DESH_HOE);
            tabModifier.accept(NtmItems.SCHRABIDIUM_SWORD);
            tabModifier.accept(NtmItems.SCHRABIDIUM_PICKAXE);
            tabModifier.accept(NtmItems.SCHRABIDIUM_AXE);
            tabModifier.accept(NtmItems.SCHRABIDIUM_SHOVEL);
            tabModifier.accept(NtmItems.SCHRABIDIUM_HOE);
            tabModifier.accept(NtmItems.BISMUTH_PICKAXE);
            tabModifier.accept(NtmItems.BISMUTH_AXE);
            tabModifier.accept(NtmItems.MOLTEN_PICKAXE);
            tabModifier.accept(NtmItems.MOLTEN_AXE);
            tabModifier.accept(NtmItems.CHLOROPHYTE_PICKAXE);
            tabModifier.accept(NtmItems.CHLOROPHYTE_AXE);
            tabModifier.accept(NtmItems.MESE_PICKAXE);
            tabModifier.accept(NtmItems.MESE_AXE);
        });
    }

    private static ItemStack charged(RegistrySupplier<Item> item, long charge) {
        return component(item, NtmDataComponentTypes.ENERGY_COMPONENT_TYPE.get(), charge);
    }

    private static <T> ItemStack component(RegistrySupplier<Item> item, DataComponentType<T> componentType, T value) {
        ItemStack stack = new ItemStack(item);
        stack.set(componentType, value);
        return stack;
    }
}
