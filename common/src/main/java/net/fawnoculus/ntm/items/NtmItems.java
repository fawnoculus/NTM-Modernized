package net.fawnoculus.ntm.items;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.Abilities;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.fawnoculus.ntm.api.tool.ModifierHandler;
import net.fawnoculus.ntm.api.tool.Modifiers;
import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.fawnoculus.ntm.entity.NtmStatusEffects;
import net.fawnoculus.ntm.items.components.NtmConsumableComponents;
import net.fawnoculus.ntm.items.components.NtmFoodComponents;
import net.fawnoculus.ntm.items.custom.*;
import net.fawnoculus.ntm.items.custom.consumable.*;
import net.fawnoculus.ntm.items.custom.container.energy.*;
import net.fawnoculus.ntm.items.custom.tools.*;
import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.fawnoculus.ntm.misc.NtmSounds;
import net.fawnoculus.ntm.util.EntityUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class NtmItems {
    // Basic Items
    public static final RegistrySupplier<Item> NULL = register("null", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_238_RTG_PELLET = register("plutonium_238_rtg_pellet", Item::new); // This is only here for the ItemGroup Icon, it will be fully added Later
    public static final RegistrySupplier<Item> TUNGSTEN_REACHER = register("tungsten_reacher", Item::new);

    // Basic Resources split by Type sorted Alphabetically because I felt like it
    public static final RegistrySupplier<Item> ACTINIUM_227_INGOT = register("actinium_227_ingot", Item::new);
    public static final RegistrySupplier<Item> ACTINIUM_227_BILLET = register("actinium_227_billet", Item::new);
    public static final RegistrySupplier<Item> ACTINIUM_227_POWDER = register("actinium_227_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_ACTINIUM_227_POWDER = register("tiny_pile_of_actinium_227_powder", Item::new);
    public static final RegistrySupplier<Item> ACTINIUM_227_NUGGET = register("actinium_227_nugget", Item::new);
    public static final RegistrySupplier<Item> ACTINIUM_227_FRAGMENT = register("actinium_227_fragment", Item::new);

    public static final RegistrySupplier<Item> ADVANCED_ALLOY_INGOT = register("advanced_alloy_ingot", Item::new);
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_POWDER = register("advanced_alloy_powder", Item::new);
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_PLATE = register("advanced_alloy_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_ADVANCED_ALLOY_PLATE = register("cast_advanced_alloy_plate", Item::new);
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_WIRE = register("advanced_alloy_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_ADVANCED_ALLOY_WIRE = register("dense_advanced_alloy_wire", Item::new);

    public static final RegistrySupplier<Item> ALEXANDRITE = register("alexandrite", Item::new);

    public static final RegistrySupplier<Item> RAW_METEORIC_ALUMINIUM = register("raw_meteoric_aluminium", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_INGOT = register("aluminium_ingot", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_POWDER = register("aluminium_powder", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_PLATE = register("aluminium_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_ALUMINIUM_PLATE = register("cast_aluminium_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_ALUMINIUM_PLATE = register("welded_aluminium_plate", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_SHELL = register("aluminium_shell", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_PIPE = register("aluminium_pipe", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_WIRE = register("aluminium_wire", Item::new);
    public static final RegistrySupplier<Item> ALUMINIUM_CRYSTALS = register("aluminium_crystals", Item::new);

    public static final RegistrySupplier<Item> AMERICIUM_241_INGOT = register("americium_241_ingot", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_241_BILLET = register("americium_241_billet", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_241_NUGGET = register("americium_241_nugget", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_242_INGOT = register("americium_242_ingot", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_242_BILLET = register("americium_242_billet", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_242_NUGGET = register("americium_242_nugget", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_FUEL_INGOT = register("americium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_FUEL_BILLET = register("americium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> AMERICIUM_FUEL_NUGGET = register("americium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_AMERICIUM_INGOT = register("reactor_grade_americium_ingot", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_AMERICIUM_BILLET = register("reactor_grade_americium_billet", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_AMERICIUM_ZFB_BILLET = register("reactor_grade_americium_zfb_billet", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_AMERICIUM_NUGGET = register("reactor_grade_americium_nugget", Item::new);

    public static final RegistrySupplier<Item> ARSENIC_INGOT = register("arsenic_ingot", Item::new);
    public static final RegistrySupplier<Item> ARSENIC_NUGGET = register("arsenic_nugget", Item::new);
    public static final RegistrySupplier<Item> ARSENIC_BRONZE_INGOT = register("arsenic_bronze_ingot", Item::new);
    public static final RegistrySupplier<Item> CAST_ARSENIC_BRONZE_PLATE = register("cast_arsenic_bronze_plate", Item::new);

    public static final RegistrySupplier<Item> ASBESTOS_SHEET = register("asbestos_sheet", TooltipItem::new);
    public static final RegistrySupplier<Item> ASBESTOS_POWDER = register("asbestos_powder", TooltipItem::new);

    public static final RegistrySupplier<Item> ASTATINE_POWDER = register("astatine_powder", Item::new);
    public static final RegistrySupplier<Item> ASTATINE_209_POWDER = register("astatine_209_powder", Item::new);

    public static final RegistrySupplier<Item> ASH = register("ash", Item::new);
    public static final RegistrySupplier<Item> WOOD_ASH = register("wood_ash", Item::new);
    public static final RegistrySupplier<Item> COAL_ASH = register("coal_ash", Item::new);
    public static final RegistrySupplier<Item> FLY_ASH = register("fly_ash", Item::new);
    public static final RegistrySupplier<Item> FINE_SOOT = register("fine_soot", Item::new);

    public static final RegistrySupplier<Item> RAW_AUSTRALIUM = register("raw_australium", Item::new);
    public static final RegistrySupplier<Item> AUSTRALIUM_INGOT = register("australium_ingot", Item::new);
    public static final RegistrySupplier<Item> AUSTRALIUM_BILLET = register("australium_billet", Item::new);
    public static final RegistrySupplier<Item> AUSTRALIUM_NUGGET = register("australium_nugget", Item::new);
    public static final RegistrySupplier<Item> LESSER_AUSTRALIUM_BILLET = register("lesser_australium_billet", Item::new);
    public static final RegistrySupplier<Item> LESSER_AUSTRALIUM_NUGGET = register("lesser_australium_nugget", Item::new);
    public static final RegistrySupplier<Item> GREATER_AUSTRALIUM_BILLET = register("greater_australium_billet", Item::new);
    public static final RegistrySupplier<Item> GREATER_AUSTRALIUM_NUGGET = register("greater_australium_nugget", Item::new);
    public static final RegistrySupplier<Item> AUSTRALIUM_POWDER = register("australium_powder", Item::new);

    public static final RegistrySupplier<Item> BAKELITE_BAR = register("bakelite_bar", Item::new);
    public static final RegistrySupplier<Item> BAKELITE_POWDER = register("bakelite_powder", Item::new);

    public static final RegistrySupplier<Item> BALEFIRE_EGG = register("balefire_egg", Item::new);
    public static final RegistrySupplier<Item> BALEFIRE_SHARD = register("balefire_shard", Item::new);
    public static final RegistrySupplier<Item> THERMONUCLEAR_ASHES = register("thermonuclear_ashes", Item::new);

    public static final RegistrySupplier<Item> RAW_BERYLLIUM = register("raw_beryllium", Item::new);
    public static final RegistrySupplier<Item> BERYLLIUM_INGOT = register("beryllium_ingot", Item::new);
    public static final RegistrySupplier<Item> BERYLLIUM_BILLET = register("beryllium_billet", Item::new);
    public static final RegistrySupplier<Item> BERYLLIUM_NUGGET = register("beryllium_nugget", Item::new);
    public static final RegistrySupplier<Item> BERYLLIUM_POWDER = register("beryllium_powder", Item::new);
    public static final RegistrySupplier<Item> BERYLLIUM_CRYSTALS = register("beryllium_crystals", Item::new);

    public static final RegistrySupplier<Item> BISMUTH_INGOT = register("bismuth_ingot", Item::new);
    public static final RegistrySupplier<Item> BISMUTH_BILLET = register("bismuth_billet", Item::new);
    public static final RegistrySupplier<Item> BISMUTH_ZFB_BILLET = register("bismuth_zfb_billet", Item::new);
    public static final RegistrySupplier<Item> BISMUTH_POWDER = register("bismuth_powder", Item::new);
    public static final RegistrySupplier<Item> BISMUTH_NUGGET = register("bismuth_nugget", Item::new);

    public static final RegistrySupplier<Item> BISMUTH_BRONZE_INGOT = register("bismuth_bronze_ingot", Item::new);
    public static final RegistrySupplier<Item> CAST_BISMUTH_BRONZE_PLATE = register("cast_bismuth_bronze_plate", Item::new);

    public static final RegistrySupplier<Item> BORAX_POWDER = register("borax_powder", Item::new);

    public static final RegistrySupplier<Item> BORON_INGOT = register("boron_ingot", Item::new);
    public static final RegistrySupplier<Item> BORON_POWDER = register("boron_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_BORON_POWDER = register("tiny_pile_of_boron_powder", Item::new);
    public static final RegistrySupplier<Item> BORON_FRAGMENT = register("boron_fragment", Item::new);

    public static final RegistrySupplier<Item> BROMINE_POWDER = register("bromine_powder", Item::new);

    public static final RegistrySupplier<Item> BSCCO_INGOT = register("bscco_ingot", Item::new);
    public static final RegistrySupplier<Item> DENSE_BSCCO_WIRE = register("dense_bscco_wire", Item::new);

    public static final RegistrySupplier<Item> CADMIUM_INGOT = register("cadmium_ingot", Item::new);
    public static final RegistrySupplier<Item> CADMIUM_POWDER = register("cadmium_powder", Item::new);

    public static final RegistrySupplier<Item> CAESIUM_POWDER = register("caesium_powder", Item::new);
    public static final RegistrySupplier<Item> CAESIUM_137_POWDER = register("caesium_137_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_CAESIUM_137_POWDER = register("tiny_pile_of_caesium_137_powder", Item::new);

    public static final RegistrySupplier<Item> CALCIUM_INGOT = register("calcium_ingot", Item::new);
    public static final RegistrySupplier<Item> CALCIUM_POWDER = register("calcium_powder", Item::new);

    public static final RegistrySupplier<Item> CADMIUM_STEEL_INGOT = register("cadmium_steel_ingot", Item::new);
    public static final RegistrySupplier<Item> CAST_CADMIUM_STEEL_PLATE = register("cast_cadmium_steel_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_CADMIUM_STEEL_PLATE = register("welded_cadmium_steel_plate", Item::new);

    public static final RegistrySupplier<Item> CEMENT = register("cement", Item::new);

    public static final RegistrySupplier<Item> CERIUM_POWDER = register("cerium_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_CERIUM_POWDER = register("tiny_pile_of_cerium_powder", Item::new);
    public static final RegistrySupplier<Item> CERIUM_FRAGMENT = register("cerium_fragment", Item::new);

    public static final RegistrySupplier<Item> CHLOROCALCITE = register("chlorocalcite", Item::new);

    public static final RegistrySupplier<Item> CHLOROPHYTE_POWDER = register("chlorophyte_powder", Item::new);

    public static final RegistrySupplier<Item> CINNABAR = register("cinnabar", Item::new);
    public static final RegistrySupplier<Item> CINNABAR_CRYSTALS = register("cinnabar_crystals", Item::new);

    public static final RegistrySupplier<Item> CMB_STEEL_INGOT = register("cmb_steel_ingot", TooltipItem::new);
    public static final RegistrySupplier<Item> CMB_STEEL_POWDER = register("cmb_steel_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_CMB_STEEL_PLATE = register("cast_cmb_steel_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_CMB_STEEL_PLATE = register("welded_cmb_steel_plate", Item::new);
    public static final RegistrySupplier<Item> CMB_STEEL_PLATE = register("cmb_steel_plate", Item::new);

    public static final RegistrySupplier<Item> COAL_POWDER = register("coal_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_COAL_POWDER = register("tiny_pile_of_coal_powder", Item::new);
    public static final RegistrySupplier<Item> CARBON_WIRE = register("carbon_wire", Item::new);
    public static final RegistrySupplier<Item> COAL_BRIQUETTE = register("coal_briquette", Item::new);
    public static final RegistrySupplier<Item> COAL_COKE = register("coal_coke", Item::new);

    public static final RegistrySupplier<Item> COBALT_INGOT = register("cobalt_ingot", Item::new);
    public static final RegistrySupplier<Item> COBALT_BILLET = register("cobalt_billet", Item::new);
    public static final RegistrySupplier<Item> COBALT_POWDER = register("cobalt_powder", Item::new);
    public static final RegistrySupplier<Item> COBALT_NUGGET = register("cobalt_nugget", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_COBALT_POWDER = register("tiny_pile_of_cobalt_powder", Item::new);
    public static final RegistrySupplier<Item> COBALT_60_INGOT = register("cobalt_60_ingot", Item::new);
    public static final RegistrySupplier<Item> COBALT_60_BILLET = register("cobalt_60_billet", Item::new);
    public static final RegistrySupplier<Item> COBALT_60_POWDER = register("cobalt_60_powder", Item::new);
    public static final RegistrySupplier<Item> COBALT_60_NUGGET = register("cobalt_60_nugget", Item::new);
    public static final RegistrySupplier<Item> COBALT_FRAGMENT = register("cobalt_fragment", Item::new);
    public static final RegistrySupplier<Item> COBALT_CRYSTALS = register("cobalt_crystals", Item::new);
    public static final RegistrySupplier<Item> RAW_METEORIC_COBALT = register("raw_meteoric_cobalt", Item::new);

    public static final RegistrySupplier<Item> COLTAN = register("coltan", Item::new);
    public static final RegistrySupplier<Item> CRUSHED_COLTAN = register("crushed_coltan", Item::new); // Turns into TANTALUM

    public static final RegistrySupplier<Item> COPPER_POWDER = register("copper_powder", Item::new);
    public static final RegistrySupplier<Item> COPPER_PLATE = register("copper_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_COPPER_PLATE = register("cast_copper_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_COPPER_PLATE = register("welded_copper_plate", Item::new);
    public static final RegistrySupplier<Item> COPPER_PIPE = register("copper_pipe", Item::new);
    public static final RegistrySupplier<Item> COPPER_SHELL = register("copper_shell", Item::new);
    public static final RegistrySupplier<Item> COPPER_WIRE = register("copper_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_COPPER_WIRE = register("dense_copper_wire", Item::new);
    public static final RegistrySupplier<Item> COPPER_CRYSTALS = register("copper_crystals", Item::new);
    public static final RegistrySupplier<Item> RAW_METEORIC_COPPER = register("raw_meteoric_copper", Item::new);

    public static final RegistrySupplier<Item> CRYO_POWDER = register("cryo_powder", Item::new);

    public static final RegistrySupplier<Item> RAW_CRYOLITE = register("raw_cryolite", Item::new);
    public static final RegistrySupplier<Item> CRYOLITE_CHUNK = register("cryolite_chunk", Item::new);

    public static final RegistrySupplier<Item> DESH_INGOT = register("desh_ingot", Item::new);
    public static final RegistrySupplier<Item> DESH_BLEND = register("desh_blend", Item::new);
    public static final RegistrySupplier<Item> DESHREADY_BLEND = register("deshready_blend", Item::new);
    public static final RegistrySupplier<Item> DESH_POWDER = register("desh_powder", Item::new);
    public static final RegistrySupplier<Item> DESH_NUGGET = register("desh_nugget", Item::new);
    public static final RegistrySupplier<Item> CAST_DESH_PLATE = register("cast_desh_plate", Item::new);

    public static final RegistrySupplier<Item> DIAMOND_POWDER = register("diamond_powder", Item::new);
    public static final RegistrySupplier<Item> DIAMOND_CRYSTALS = register("diamond_crystals", Item::new);

    public static final RegistrySupplier<Item> DINEUTRONIUM_INGOT = register("dineutronium_ingot", Item::new);
    public static final RegistrySupplier<Item> DINEUTRONIUM_POWDER = register("dineutronium_powder", Item::new);
    public static final RegistrySupplier<Item> DINEUTRONIUM_NUGGET = register("dineutronium_nugget", Item::new);
    public static final RegistrySupplier<Item> DENSE_DINEUTRONIUM_WIRE = register("dense_dineutronium_wire", Item::new);

    public static final RegistrySupplier<Item> ELECTRONIUM_INGOT = register("electronium_ingot", Item::new);

    public static final RegistrySupplier<Item> EMERALD_POWDER = register("emerald_powder", Item::new);

    public static final RegistrySupplier<Item> ENERGY_POWDER = register("energy_powder", Item::new);

    public static final RegistrySupplier<Item> EUPHEMIUM_INGOT = register("euphemium_ingot", Item::new);
    public static final RegistrySupplier<Item> EUPHEMIUM_POWDER = register("euphemium_powder", Item::new);
    public static final RegistrySupplier<Item> EUPHEMIUM_NUGGET = register("euphemium_nugget", Item::new);

    public static final RegistrySupplier<Item> FERRIC_SCHARBIDATE_INGOT = register("ferric_schrabidate_ingot", Item::new);
    public static final RegistrySupplier<Item> FERRIC_SCHARBIDATE_POWDER = register("ferric_schrabidate_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_FERRIC_SCHARBIDATE_PLATE = register("cast_ferric_schrabidate_plate", Item::new);
    public static final RegistrySupplier<Item> DENSE_FERRIC_SCHARBIDATE_WIRE = register("dense_ferric_schrabidate_wire", Item::new);

    public static final RegistrySupplier<Item> FERROURANIUM_INGOT = register("ferrouranium_ingot", Item::new);
    public static final RegistrySupplier<Item> CAST_FERROURANIUM_PLATE = register("cast_ferrouranium_plate", Item::new);

    public static final RegistrySupplier<Item> FLASH_GOLD = register("flash_gold", Item::new);

    public static final RegistrySupplier<Item> FLASH_LEAD = register("flash_lead", Item::new);

    public static final RegistrySupplier<Item> FLUORITE = register("fluorite", Item::new);
    public static final RegistrySupplier<Item> FLUORITE_CRYSTALS = register("fluorite_crystals", Item::new);

    public static final RegistrySupplier<Item> FLUX = register("flux", Item::new);

    public static final RegistrySupplier<Item> FULLERENE = register("fullerene", Item::new);
    public static final RegistrySupplier<Item> CRYSTALLINE_FULLERENE = register("crystalline_fullerene", Item::new);

    public static final RegistrySupplier<Item> GHIORSIUM_336_INGOT = register("ghiorsium_336_ingot", Item::new);
    public static final RegistrySupplier<Item> GHIORSIUM_336_BILLET = register("ghiorsium_336_billet", Item::new);
    public static final RegistrySupplier<Item> GHIORSIUM_336_NUGGET = register("ghiorsium_336_nugget", Item::new);

    public static final RegistrySupplier<Item> GOLD_POWDER = register("gold_powder", Item::new);
    public static final RegistrySupplier<Item> GOLD_PLATE = register("gold_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_GOLD_PLATE = register("cast_gold_plate", Item::new);
    public static final RegistrySupplier<Item> GOLD_WIRE = register("gold_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_GOLD_WIRE = register("dense_gold_wire", Item::new);
    public static final RegistrySupplier<Item> GOLD_CRYSTALS = register("gold_crystals", Item::new);
    public static final RegistrySupplier<Item> GOLD_198_INGOT = register("gold_198_ingot", Item::new);
    public static final RegistrySupplier<Item> GOLD_198_BILLET = register("gold_198_billet", Item::new);
    public static final RegistrySupplier<Item> GOLD_198_POWDER = register("gold_198_powder", Item::new);
    public static final RegistrySupplier<Item> GOLD_198_NUGGET = register("gold_198_nugget", Item::new);

    public static final RegistrySupplier<Item> GRAPHITE_INGOT = register("graphite_ingot", Item::new);

    public static final RegistrySupplier<Item> GUNMETAL_INGOT = register("gunmetal_ingot", Item::new);
    public static final RegistrySupplier<Item> GUNMETAL_PLATE = register("gunmetal_plate", Item::new);

    public static final RegistrySupplier<Item> HARD_PLASTIC_BAR = register("hard_plastic_bar", Item::new);

    public static final RegistrySupplier<Item> HIGH_SPEED_STEEL_INGOT = register("high_speed_steel_ingot", Item::new);
    public static final RegistrySupplier<Item> HIGH_SPEED_STEEL_POWDER = register("high_speed_steel_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_HIGH_SPEED_STEEL_PLATE = register("cast_high_speed_steel_plate", Item::new);
    public static final RegistrySupplier<Item> HIGH_SPEED_STEEL_PLATE = register("high_speed_steel_plate", Item::new);
    public static final RegistrySupplier<Item> HIGH_SPEED_STEEL_BOLT = register("high_speed_steel_bolt", Item::new);
    public static final RegistrySupplier<Item> HIGH_SPEED_STEEL_PIPE = register("high_speed_steel_pipe", Item::new);

    public static final RegistrySupplier<Item> IODINE_POWDER = register("iodine_powder", Item::new);
    public static final RegistrySupplier<Item> IODINE_131_POWDER = register("iodine_131_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_IODINE_131_POWDER = register("tiny_pile_of_iodine_131_powder", Item::new);

    public static final RegistrySupplier<Item> IRON_POWDER = register("iron_powder", Item::new);
    public static final RegistrySupplier<Item> IRON_PLATE = register("iron_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_IRON_PLATE = register("cast_iron_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_IRON_PLATE = register("welded_iron_plate", Item::new);
    public static final RegistrySupplier<Item> IRON_PIPE = register("iron_pipe", Item::new);
    public static final RegistrySupplier<Item> IRON_CRYSTALS = register("iron_crystals", Item::new);
    public static final RegistrySupplier<Item> RAW_METEORIC_IRON = register("raw_meteoric_iron", Item::new);

    public static final RegistrySupplier<Item> INDUSTRIAL_FERTILIZER = register("industrial_fertilizer", Item::new);

    public static final RegistrySupplier<Item> INFERNAL_COAL = register("infernal_coal", Item::new);

    public static final RegistrySupplier<Item> SEMI_STABLE_LANTHANUM_INGOT = register("semi_stable_lanthanum_ingot", Item::new);
    public static final RegistrySupplier<Item> LANTHANUM_POWDER = register("lanthanum_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_LANTHANUM_POWDER = register("tiny_pile_of_lanthanum_powder", Item::new);
    public static final RegistrySupplier<Item> LANTHANUM_FRAGMENT = register("lanthanum_fragment", Item::new);

    public static final RegistrySupplier<Item> LAPIS_POWDER = register("lapis_powder", Item::new);
    public static final RegistrySupplier<Item> LAPIS_CRYSTALS = register("lapis_crystals", Item::new);

    public static final RegistrySupplier<Item> LATEX = register("latex", Item::new);
    public static final RegistrySupplier<Item> LATEX_BAR = register("latex_bar", Item::new);

    public static final RegistrySupplier<Item> RAW_LEAD = register("raw_lead", Item::new);
    public static final RegistrySupplier<Item> LEAD_INGOT = register("lead_ingot", Item::new);
    public static final RegistrySupplier<Item> LEAD_NUGGET = register("lead_nugget", Item::new);
    public static final RegistrySupplier<Item> LEAD_209_INGOT = register("lead_209_ingot", Item::new);
    public static final RegistrySupplier<Item> LEAD_209_BILLET = register("lead_209_billet", Item::new);
    public static final RegistrySupplier<Item> LEAD_209_NUGGET = register("lead_209_nugget", Item::new);
    public static final RegistrySupplier<Item> LEAD_POWDER = register("lead_powder", Item::new);
    public static final RegistrySupplier<Item> LEAD_PLATE = register("lead_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_LEAD_PLATE = register("cast_lead_plate", Item::new);
    public static final RegistrySupplier<Item> LEAD_PIPE = register("lead_pipe", Item::new);
    public static final RegistrySupplier<Item> LEAD_BOLT = register("lead_bolt", Item::new);
    public static final RegistrySupplier<Item> LEAD_WIRE = register("lead_wire", Item::new);
    public static final RegistrySupplier<Item> LEAD_CRYSTALS = register("lead_crystals", Item::new);

    public static final RegistrySupplier<Item> LIGNITE = register("lignite", Item::new);
    public static final RegistrySupplier<Item> LIGNITE_POWDER = register("lignite_powder", Item::new);
    public static final RegistrySupplier<Item> LIGNITE_COKE = register("lignite_coke", Item::new);
    public static final RegistrySupplier<Item> LIGNITE_BRIQUETTE = register("lignite_briquette", Item::new);

    public static final RegistrySupplier<Item> LIMESTONE_POWDER = register("limestone_powder", Item::new);

    public static final RegistrySupplier<Item> LITHIUM_CUBE = register("lithium_cube", Item::new);
    public static final RegistrySupplier<Item> LITHIUM_POWDER = register("lithium_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_LITHIUM_POWDER = register("tiny_pile_of_lithium_powder", Item::new);
    public static final RegistrySupplier<Item> LITHIUM_CRYSTALS = register("lithium_crystals", Item::new);

    public static final RegistrySupplier<Item> MAGNETIZED_TUNGSTEN_INGOT = register("magnetized_tungsten_ingot", Item::new);
    public static final RegistrySupplier<Item> MAGNETIZED_TUNGSTEN_POWDER = register("magnetized_tungsten_powder", Item::new);
    public static final RegistrySupplier<Item> MAGNETIZED_TUNGSTEN_WIRE = register("magnetized_tungsten_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_MAGNETIZED_TUNGSTEN_WIRE = register("dense_magnetized_tungsten_wire", Item::new);

    public static final RegistrySupplier<Item> METEORITE_INGOT = register("meteorite_ingot", Item::new);
    public static final RegistrySupplier<Item> METEORITE_POWDER = register("meteorite_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_METEORITE_POWDER = register("tiny_pile_of_meteorite_powder", Item::new);
    public static final RegistrySupplier<Item> METEORITE_FRAGMENT = register("meteorite_fragment", Item::new);

    public static final RegistrySupplier<Item> MOLYSITE = register("molysite", Item::new);

    public static final RegistrySupplier<Item> MOX_FUEL_INGOT = register("mox_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> MOX_FUEL_BILLET = register("mox_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> MOX_FUEL_NUGGET = register("mox_fuel_nugget", Item::new);

    public static final RegistrySupplier<Item> NEODYMIUM_POWDER = register("neodymium_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_NEODYMIUM_POWDER = register("tiny_pile_of_neodymium_powder", Item::new);
    public static final RegistrySupplier<Item> DENSE_NEODYMIUM_WIRE = register("dense_neodymium_wire", Item::new);
    public static final RegistrySupplier<Item> NEODYMIUM_FRAGMENT = register("neodymium_fragment", Item::new);

    public static final RegistrySupplier<Item> NEPTUNIUM_INGOT = register("neptunium_ingot", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_POWDER = register("neptunium_powder", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_BILLET = register("neptunium_billet", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_NUGGET = register("neptunium_nugget", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_FUEL_INGOT = register("neptunium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_FUEL_BILLET = register("neptunium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> NEPTUNIUM_FUEL_NUGGET = register("neptunium_fuel_nugget", Item::new);

    public static final RegistrySupplier<Item> NIOBIUM_INGOT = register("niobium_ingot", Item::new);
    public static final RegistrySupplier<Item> NIOBIUM_POWDER = register("niobium_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_NIOBIUM_POWDER = register("tiny_pile_of_niobium_powder", Item::new);
    public static final RegistrySupplier<Item> NIOBIUM_NUGGET = register("niobium_nugget", Item::new);
    public static final RegistrySupplier<Item> DENSE_NIOBIUM_WIRE = register("dense_niobium_wire", Item::new);
    public static final RegistrySupplier<Item> NIOBIUM_FRAGMENT = register("niobium_fragment", Item::new);

    public static final RegistrySupplier<Item> NITAN_BLEND = register("nitan_blend", Item::new);

    public static final RegistrySupplier<Item> NITER = register("niter", Item::new);
    public static final RegistrySupplier<Item> NITER_CRYSTALS = register("niter_crystals", Item::new);

    public static final RegistrySupplier<Item> NITRA = register("nitra", Item::new);
    public static final RegistrySupplier<Item> SMALL_PILE_OF_NITRA = register("small_pile_of_nitra", Item::new);

    public static final RegistrySupplier<Item> RAW_OSMIRIDIUM_INFUSED_TEKTITE = register("raw_osmiridium_infused_tektite", Item::new);
    public static final RegistrySupplier<Item> OSMIRIDIUM_INGOT = register("osmiridium_ingot", Item::new);
    public static final RegistrySupplier<Item> OSMIRIDIUM_NUGGET = register("osmiridium_nugget", Item::new);
    public static final RegistrySupplier<Item> IMPURE_OSMIRIDIUM_POWDER = register("impure_osmiridium_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_OSMIRIDIUM_PLATE = register("cast_osmiridium_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_OSMIRIDIUM_PLATE = register("welded_osmiridium_plate", Item::new);
    public static final RegistrySupplier<Item> OSMIRIDIUM_CRYSTALS = register("osmiridium_crystals", Item::new);

    public static final RegistrySupplier<Item> PALEOGENITE_POWDER = register("paleogenite_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_PALEOGENITE_POWDER = register("tiny_pile_of_paleogenite_powder", Item::new);

    public static final RegistrySupplier<Item> RED_PHOSPHORUS = register("red_phosphorus", settings -> new TooltipItem(settings, 2));
    public static final RegistrySupplier<Item> WHITE_PHOSPHORUS_BAR = register("white_phosphorus_bar", Item::new);
    public static final RegistrySupplier<Item> PHOSPHORUS_CRYSTALS = register("phosphorus_crystals", Item::new);

    public static final RegistrySupplier<Item> PETROLEUM_COKE = register("petroleum_coke", Item::new);

    public static final RegistrySupplier<Item> RAW_PLUTONIUM = register("raw_plutonium", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_INGOT = register("plutonium_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_BILLET = register("plutonium_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_NUGGET = register("plutonium_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_FUEL_INGOT = register("plutonium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_FUEL_BILLET = register("plutonium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_FUEL_NUGGET = register("plutonium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_PLUTONIUM_INGOT = register("reactor_grade_plutonium_ingot", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_PLUTONIUM_BILLET = register("reactor_grade_plutonium_billet", Item::new);
    public static final RegistrySupplier<Item> REACTOR_GRADE_PLUTONIUM_NUGGET = register("reactor_grade_plutonium_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_238_INGOT = register("plutonium_238_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_238_BILLET = register("plutonium_238_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_238_BE_BILLET = register("plutonium_238_be_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_238_NUGGET = register("plutonium_238_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_239_INGOT = register("plutonium_239_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_239_BILLET = register("plutonium_239_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_239_NUGGET = register("plutonium_239_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_240_INGOT = register("plutonium_240_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_240_BILLET = register("plutonium_240_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_240_NUGGET = register("plutonium_240_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_241_INGOT = register("plutonium_241_ingot", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_241_BILLET = register("plutonium_241_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_241_ZFB_BILLET = register("plutonium_241_zfb_billet", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_241_NUGGET = register("plutonium_241_nugget", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_POWDER = register("plutonium_powder", Item::new);
    public static final RegistrySupplier<Item> PLUTONIUM_CRYSTALS = register("plutonium_crystals", Item::new);

    public static final RegistrySupplier<Item> POISON_POWDER = register("poison_powder", settings -> new TooltipItem(settings, 2));

    public static final RegistrySupplier<Item> POLONIUM_210_INGOT = register("polonium_210_ingot", Item::new);
    public static final RegistrySupplier<Item> POLONIUM_210_BILLET = register("polonium_210_billet", Item::new);
    public static final RegistrySupplier<Item> POLONIUM_210_BE_BILLET = register("polonium_210_be_billet", Item::new);
    public static final RegistrySupplier<Item> POLONIUM_210_NUGGET = register("polonium_210_nugget", Item::new);
    public static final RegistrySupplier<Item> POLONIUM_210_POWDER = register("polonium_210_powder", Item::new);

    public static final RegistrySupplier<Item> POLYMER_BAR = register("polymer_bar", Item::new);
    public static final RegistrySupplier<Item> POLYMER_POWDER = register("polymer_powder", Item::new);

    public static final RegistrySupplier<Item> PULVERIZED_ENCHANTMENT = register("pulverized_enchantment", Item::new);

    public static final RegistrySupplier<Item> PVC_BAR = register("pvc_bar", Item::new);

    public static final RegistrySupplier<Item> QUARTZ_POWDER = register("quartz_powder", Item::new);

    public static final RegistrySupplier<Item> RADIUM_226_INGOT = register("radium_266_ingot", Item::new);
    public static final RegistrySupplier<Item> RADIUM_226_BILLET = register("radium_266_billet", Item::new);
    public static final RegistrySupplier<Item> RADIUM_226_BE_BILLET = register("radium_266_be_billet", Item::new);
    public static final RegistrySupplier<Item> RADIUM_226_POWDER = register("radium_266_powder", Item::new);
    public static final RegistrySupplier<Item> RADIUM_226_NUGGET = register("radium_266_nugget", Item::new);

    public static final RegistrySupplier<Item> RARE_EARTH_ORE_CHUNK = register("rare_earth_ore_chunk", Item::new);
    public static final RegistrySupplier<Item> RARE_EARTH_CRYSTALS = register("rare_earth_crystals", Item::new);
    public static final RegistrySupplier<Item> RAW_METEORIC_RARE_EARTH = register("raw_meteoric_rare_earth", Item::new);

    public static final RegistrySupplier<Item> RED_COPPER_INGOT = register("red_copper_ingot", Item::new);
    public static final RegistrySupplier<Item> RED_COPPER_POWDER = register("red_copper_powder", Item::new);
    public static final RegistrySupplier<Item> RED_COPPER_WIRE = register("red_copper_wire", Item::new);

    public static final RegistrySupplier<Item> REDSTONE_CRYSTALS = register("redstone_crystals", Item::new);

    public static final RegistrySupplier<Item> RUBBER_BAR = register("rubber_bar", Item::new);
    public static final RegistrySupplier<Item> RUBBER_PIPE = register("rubber_pipe", Item::new);

    public static final RegistrySupplier<Item> SATURNITE_INGOT = register("saturnite_ingot", Item::new);
    public static final RegistrySupplier<Item> SATURNITE_PLATE = register("saturnite_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_SATURNITE_PLATE = register("cast_saturnite_plate", Item::new);
    public static final RegistrySupplier<Item> SATURNITE_SHELL = register("saturnite_shell", Item::new);

    public static final RegistrySupplier<Item> SAWDUST_POWDER = register("sawdust_powder", Item::new);
    public static final RegistrySupplier<Item> SAWDUST_BRIQUETTE = register("sawdust_briquette", Item::new);

    public static final RegistrySupplier<Item> RAW_SCHRABIDIUM = register("raw_schrabidium", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_INGOT = register("schrabidium_ingot", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_BILLET = register("schrabidium_billet", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_NUGGET = register("schrabidium_nugget", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_FUEL_INGOT = register("schrabidium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_FUEL_BILLET = register("schrabidium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_FUEL_NUGGET = register("schrabidium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> LOW_ENRICHED_SCHRABIDIUM_FUEL_INGOT = register("low_enriched_schrabidium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> LOW_ENRICHED_SCHRABIDIUM_FUEL_BILLET = register("low_enriched_schrabidium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> LOW_ENRICHED_SCHRABIDIUM_FUEL_NUGGET = register("low_enriched_schrabidium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_INGOT = register("highly_enriched_schrabidium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_BILLET = register("highly_enriched_schrabidium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_NUGGET = register("highly_enriched_schrabidium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_POWDER = register("schrabidium_powder", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_PLATE = register("schrabidium_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_SCHRABIDIUM_PLATE = register("cast_schrabidium_plate", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_WIRE = register("schrabidium_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_SCHRABIDIUM_WIRE = register("dense_schrabidium_wire", Item::new);
    public static final RegistrySupplier<Item> SCHRABIDIUM_CRYSTALS = register("schrabidium_crystals", Item::new);

    public static final RegistrySupplier<Item> SCHRARANIUM_INGOT = register("schraranium_ingot", Item::new);
    public static final RegistrySupplier<Item> SCHRARANIUM_CRYSTALS = register("schraranium_crystals", Item::new);

    public static final RegistrySupplier<Item> SEMTEX_BLEND = register("semtex_blend", Item::new);
    public static final RegistrySupplier<Item> SEMTEX_BAR = register("semtex_bar", settings -> new TooltipItem(settings, 3));

    public static final RegistrySupplier<Item> SILICON_BOULE = register("silicon_boule", Item::new);
    public static final RegistrySupplier<Item> SILICON_WAFER = register("silicon_wafer", Item::new);
    public static final RegistrySupplier<Item> PRINTED_SILICON_WAFER = register("printed_silicon_wafer", Item::new);
    public static final RegistrySupplier<Item> SILICON_NUGGET = register("silicon_nugget", Item::new);

    public static final RegistrySupplier<Item> SODIUM_POWDER = register("sodium_powder", Item::new);

    public static final RegistrySupplier<Item> SOLINIUM_INGOT = register("solinium_ingot", Item::new);
    public static final RegistrySupplier<Item> SOLINIUM_BILLET = register("solinium_billet", Item::new);
    public static final RegistrySupplier<Item> SOLINIUM_NUGGET = register("solinium_nugget", Item::new);

    public static final RegistrySupplier<Item> SPARK_BLEND = register("spark_blend", Item::new);

    public static final RegistrySupplier<Item> STARMETAL_INGOT = register("starmetal_ingot", Item::new);
    public static final RegistrySupplier<Item> DENSE_STARMETAL_WIRE = register("dense_starmetal_wire", Item::new);
    public static final RegistrySupplier<Item> STARMETAL_RING = register("starmetal_ring", Item::new);
    public static final RegistrySupplier<Item> STARMETAL_CRYSTALS = register("starmetal_crystals", Item::new);

    public static final RegistrySupplier<Item> STRONTIUM_POWDER = register("strontium_powder", Item::new);
    public static final RegistrySupplier<Item> STRONTIUM_90_INGOT = register("strontium_90_ingot", Item::new);
    public static final RegistrySupplier<Item> STRONTIUM_90_BILLET = register("strontium_90_billet", Item::new);
    public static final RegistrySupplier<Item> STRONTIUM_90_POWDER = register("strontium_90_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_STRONTIUM_90_POWDER = register("tiny_pile_of_strontium_90_powder", Item::new);
    public static final RegistrySupplier<Item> STRONTIUM_90_NUGGET = register("strontium_90_nugget", Item::new);

    public static final RegistrySupplier<Item> STEEL_INGOT = register("steel_ingot", Item::new);
    public static final RegistrySupplier<Item> STEEL_POWDER = register("steel_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_STEEL_POWDER = register("tiny_pile_of_steel_powder", Item::new);
    public static final RegistrySupplier<Item> STEEL_PLATE = register("steel_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_STEEL_PLATE = register("cast_steel_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_STEEL_PLATE = register("welded_steel_plate", Item::new);
    public static final RegistrySupplier<Item> STEEL_BOLT = register("steel_bolt", Item::new);
    public static final RegistrySupplier<Item> STEEL_PIPE = register("steel_pipe", Item::new);
    public static final RegistrySupplier<Item> STEEL_SHELL = register("steel_shell", Item::new);
    public static final RegistrySupplier<Item> STEEL_WIRE = register("steel_wire", Item::new);

    public static final RegistrySupplier<Item> SULFUR = register("sulfur", Item::new);
    public static final RegistrySupplier<Item> SULFUR_CRYSTALS = register("sulfur_crystals", Item::new);

    public static final RegistrySupplier<Item> PURIFIED_TANTALITE = register("purified_tantalite", Item::new); // Comes from Coltan
    public static final RegistrySupplier<Item> TANTALUM_INGOT = register("tantalum_ingot", Item::new);
    public static final RegistrySupplier<Item> TANTALUM_POWDER = register("tantalum_powder", Item::new);
    public static final RegistrySupplier<Item> TANTALUM_NUGGET = register("tantalum_nugget", Item::new);
    public static final RegistrySupplier<Item> TANTALUM_POLYCRYSTAL = register("tantalum_polycrystal", Item::new);

    public static final RegistrySupplier<Item> TECHNETIUM_99_INGOT = register("technetium_99_ingot", Item::new);
    public static final RegistrySupplier<Item> TECHNETIUM_99_BILLET = register("technetium_99_billet", Item::new);
    public static final RegistrySupplier<Item> TECHNETIUM_99_NUGGET = register("technetium_99_nugget", Item::new);

    public static final RegistrySupplier<Item> TECHNETIUM_STEEL_INGOT = register("technetium_steel_ingot", Item::new);
    public static final RegistrySupplier<Item> TECHNETIUM_STEEL_POWDER = register("technetium_steel_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_TECHNETIUM_STEEL_PLATE = register("cast_technetium_steel_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_TECHNETIUM_STEEL_PLATE = register("welded_technetium_steel_plate", Item::new);

    public static final RegistrySupplier<Item> TEKTITE_POWDER = register("tektite_powder", Item::new);

    public static final RegistrySupplier<Item> TENNESSINE_POWDER = register("tennessine_powder", Item::new);

    public static final RegistrySupplier<Item> THERMITE = register("thermite", Item::new);

    public static final RegistrySupplier<Item> RAW_THORIUM = register("raw_thorium", Item::new);
    public static final RegistrySupplier<Item> THORIUM_232_INGOT = register("thorium_232_ingot", Item::new);
    public static final RegistrySupplier<Item> THORIUM_232_BILLET = register("thorium_232_billet", Item::new);
    public static final RegistrySupplier<Item> THORIUM_232_NUGGET = register("thorium_232_nugget", Item::new);
    public static final RegistrySupplier<Item> THORIUM_FUEL_INGOT = register("thorium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> THORIUM_FUEL_BILLET = register("thorium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> THORIUM_FUEL_NUGGET = register("thorium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> THORIUM_POWDER = register("thorium_powder", Item::new);
    public static final RegistrySupplier<Item> THORIUM_CRYSTALS = register("thorium_crystals", Item::new);

    public static final RegistrySupplier<Item> RAW_TITANIUM = register("raw_titanium", Item::new);
    public static final RegistrySupplier<Item> TITANIUM_INGOT = register("titanium_ingot", Item::new);
    public static final RegistrySupplier<Item> TITANIUM_PLATE = register("titanium_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_TITANIUM_PLATE = register("cast_titanium_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_TITANIUM_PLATE = register("welded_titanium_plate", Item::new);
    public static final RegistrySupplier<Item> TITANIUM_POWDER = register("titanium_powder", Item::new);
    public static final RegistrySupplier<Item> TITANIUM_SHELL = register("titanium_shell", Item::new);
    public static final RegistrySupplier<Item> DENSE_TITANIUM_WIRE = register("dense_titanium_wire", Item::new);
    public static final RegistrySupplier<Item> TITANIUM_CRYSTALS = register("titanium_crystals", Item::new);

    public static final RegistrySupplier<Item> RAW_TRIXITE = register("raw_trixite", Item::new);
    public static final RegistrySupplier<Item> TRIXITE_CRYSTALS = register("trixite_crystals", Item::new);

    public static final RegistrySupplier<Item> RAW_TUNGSTEN = register("raw_tungsten", Item::new);
    public static final RegistrySupplier<Item> TUNGSTEN_INGOT = register("tungsten_ingot", Item::new);
    public static final RegistrySupplier<Item> TUNGSTEN_POWDER = register("tungsten_powder", Item::new);
    public static final RegistrySupplier<Item> TUNGSTEN_BOLT = register("tungsten_bolt", Item::new);
    public static final RegistrySupplier<Item> TUNGSTEN_WIRE = register("tungsten_wire", Item::new);
    public static final RegistrySupplier<Item> DENSE_TUNGSTEN_WIRE = register("dense_tungsten_wire", Item::new);
    public static final RegistrySupplier<Item> TUNGSTEN_CRYSTALS = register("tungsten_crystals", Item::new);

    public static final RegistrySupplier<Item> RAW_URANIUM = register("raw_uranium", Item::new);
    public static final RegistrySupplier<Item> RAW_SCORCHED_URANIUM = register("raw_scorched_uranium", Item::new);
    public static final RegistrySupplier<Item> URANIUM_INGOT = register("uranium_ingot", Item::new);
    public static final RegistrySupplier<Item> URANIUM_BILLET = register("uranium_billet", Item::new);
    public static final RegistrySupplier<Item> URANIUM_NUGGET = register("uranium_nugget", Item::new);
    public static final RegistrySupplier<Item> URANIUM_FUEL_INGOT = register("uranium_fuel_ingot", Item::new);
    public static final RegistrySupplier<Item> URANIUM_FUEL_BILLET = register("uranium_fuel_billet", Item::new);
    public static final RegistrySupplier<Item> URANIUM_FUEL_NUGGET = register("uranium_fuel_nugget", Item::new);
    public static final RegistrySupplier<Item> URANIUM_233_INGOT = register("uranium_233_ingot", Item::new);
    public static final RegistrySupplier<Item> URANIUM_233_BILLET = register("uranium_233_billet", Item::new);
    public static final RegistrySupplier<Item> URANIUM_233_NUGGET = register("uranium_233_nugget", Item::new);
    public static final RegistrySupplier<Item> URANIUM_235_INGOT = register("uranium_235_ingot", Item::new);
    public static final RegistrySupplier<Item> URANIUM_235_BILLET = register("uranium_235_billet", Item::new);
    public static final RegistrySupplier<Item> URANIUM_235_NUGGET = register("uranium_235_nugget", Item::new);
    public static final RegistrySupplier<Item> URANIUM_238_INGOT = register("uranium_238_ingot", Item::new);
    public static final RegistrySupplier<Item> URANIUM_238_BILLET = register("uranium_238_billet", Item::new);
    public static final RegistrySupplier<Item> URANIUM_238_NUGGET = register("uranium_238_nugget", Item::new);
    public static final RegistrySupplier<Item> URANIUM_POWDER = register("uranium_powder", Item::new);
    public static final RegistrySupplier<Item> URANIUM_CRYSTALS = register("uranium_crystals", Item::new);

    public static final RegistrySupplier<Item> VOLCANIC_GEM = register("volcanic_gem", Item::new);

    public static final RegistrySupplier<Item> WEAPON_STEEL_INGOT = register("weapon_steel_ingot", Item::new);
    public static final RegistrySupplier<Item> WEAPON_STEEL_PLATE = register("weapon_steel_plate", Item::new);
    public static final RegistrySupplier<Item> CAST_WEAPON_STEEL_PLATE = register("cast_weapon_steel_plate", Item::new);
    public static final RegistrySupplier<Item> WEAPON_STEEL_SHELL = register("weapon_steel_shell", Item::new);

    public static final RegistrySupplier<Item> XENON_135_POWDER = register("xenon_135_powder", Item::new);
    public static final RegistrySupplier<Item> TINY_PILE_OF_XENON_135_POWDER = register("tiny_pile_of_xenon_135_powder", Item::new);

    public static final RegistrySupplier<Item> YHARONITE_BILLET = register("yharonite_billet", Item::new);

    public static final RegistrySupplier<Item> YELLOWCAKE = register("yellowcake", Item::new);

    public static final RegistrySupplier<Item> ZIRCONIUM_SPLINTER = register("zirconium_splinter", Item::new);
    public static final RegistrySupplier<Item> ZIRCONIUM_CUBE = register("zirconium_cube", Item::new);
    public static final RegistrySupplier<Item> ZIRCONIUM_BILLET = register("zirconium_billet", Item::new);
    public static final RegistrySupplier<Item> ZIRCONIUM_POWDER = register("zirconium_powder", Item::new);
    public static final RegistrySupplier<Item> CAST_ZIRCONIUM_PLATE = register("cast_zirconium_plate", Item::new);
    public static final RegistrySupplier<Item> WELDED_ZIRCONIUM_PLATE = register("welded_zirconium_plate", Item::new);
    public static final RegistrySupplier<Item> ZIRCONIUM_WIRE = register("zirconium_wire", Item::new);

    // TODO: Heatable/Forgeable Ingots
    // public static final RegistrySupplier<Item>Option DUSTED_STEEL_INGOT = register("dusted_steel_ingot", ItemOption::new, new ItemOption.Settings());
    // public static final RegistrySupplier<Item>Option HEAVY_CHAINSTEEL_INGOT = register("heavy_chainsteel_ingot", ItemOption::new, new ItemOption.Settings());
    // public static final RegistrySupplier<Item>Option FORGED_METEORITE_INGOT = register("forged_meteorite_ingot", ItemOption::new, new ItemOption.Settings());


    // Usable Items
    public static final RegistrySupplier<Item> DEBUG_WAND = register("debug_wand", DebugWandItem::new);
    public static final RegistrySupplier<Item> CONSTRUCTION_WAND = register("construction_wand", ConstructionWandItem::new);
    public static final RegistrySupplier<Item> NETWORK_DEBUG_TOOL = register("network_debug_tool", NetworkDebuggingToolItem::new);
    public static final RegistrySupplier<Item> GEIGER_COUNTER = register("geiger_counter", GeigerCounterItem::new);
    public static final RegistrySupplier<Item> DOSIMETER = register("dosimeter", DosimeterItem::new);

    // Batteries
    public static final RegistrySupplier<Item> BATTERY = register("battery",
      settings -> new SimpleBatteryItem(settings, 5_000L, 100L));
    public static final RegistrySupplier<Item> REDSTONE_POWER_CELL = register("redstone_power_cell",
      settings -> new SimpleBatteryItem(settings, 15_000L, 100L));
    public static final RegistrySupplier<Item> SIXFOLD_REDSTONE_POWER_CELL = register("sixfold_redstone_power_cell",
      settings -> new SimpleBatteryItem(settings, 90_000L, 100L));
    public static final RegistrySupplier<Item> TWENTY_FOUR_FOLD_REDSTONE_POWER_CELL = register("twenty_four_fold_redstone_power_cell",
      settings -> new SimpleBatteryItem(settings, 360_000L, 100L));
    public static final RegistrySupplier<Item> ADVANCED_BATTERY = register("advanced_battery",
      settings -> new SimpleBatteryItem(settings, 20_000L, 500L));
    public static final RegistrySupplier<Item> ADVANCED_POWER_CELL = register("advanced_power_cell",
      settings -> new SimpleBatteryItem(settings, 60_000L, 500L));
    public static final RegistrySupplier<Item> QUADRUPLE_ADVANCED_POWER_CELL = register("quadruple_advanced_power_cell",
      settings -> new SimpleBatteryItem(settings, 240_000L, 500L));
    public static final RegistrySupplier<Item> TWELVEFOLD_ADVANCED_POWER_CELL = register("twelvefold_advanced_power_cell",
      settings -> new SimpleBatteryItem(settings, 720_000L, 500L));
    public static final RegistrySupplier<Item> LITHIUM_ION_BATTERY = register("lithium_ion_battery",
      settings -> new SimpleBatteryItem(settings, 250_000L, 1_000L));
    public static final RegistrySupplier<Item> LITHIUM_ION_POWER_CELL = register("lithium_ion_power_cell",
      settings -> new SimpleBatteryItem(settings, 750_000L, 1_000L));
    // Triple TRIPLE_LITHIUM_ION_POWER_CELL has one extra comma digit because its max energy would be rounded otherwise, that's why it has an extra class
    public static final RegistrySupplier<Item> TRIPLE_LITHIUM_ION_POWER_CELL = register("triple_lithium_ion_power_cell", TripleLithiumIonBatteryItem::new);
    public static final RegistrySupplier<Item> SIXFOLD_LITHIUM_ION_POWER_CELL = register("sixfold_lithium_ion_power_cell",
      settings -> new SimpleBatteryItem(settings, 4_500_000L, 1_000L));
    public static final RegistrySupplier<Item> SCHRABIDIUM_BATTERY = register("schrabidium_battery",
      settings -> new SimpleBatteryItem(settings, 1_000_000L, 5_000L));
    public static final RegistrySupplier<Item> SCHRABIDIUM_POWER_CELL = register("schrabidium_power_cell",
      settings -> new SimpleBatteryItem(settings, 3_000_000L, 5_000L));
    public static final RegistrySupplier<Item> DOUBLE_SCHRABIDIUM_POWER_CELL = register("double_schrabidium_power_cell",
      settings -> new SimpleBatteryItem(settings, 6_000_000L, 5_000L));
    public static final RegistrySupplier<Item> QUADRUPLE_SCHRABIDIUM_POWER_CELL = register("quadruple_schrabidium_power_cell",
      settings -> new SimpleBatteryItem(settings, 12_000_000L, 5_000L));
    public static final RegistrySupplier<Item> SPARK_BATTERY = register("spark_battery",
      settings -> new SimpleBatteryItem(settings, 100_000_000L, 2_000_000L));
    public static final RegistrySupplier<Item> OFF_BRAND_SPARK_BATTERY = register("off_brand_spark_battery",
      settings -> new SimpleBatteryItem(settings, 5_000_000L, 40_000L, 200_000L));
    public static final RegistrySupplier<Item> SPARK_POWER_CELL = register("spark_power_cell",
      settings -> new SimpleBatteryItem(settings, 600_000_000L, 2_000_000L));
    public static final RegistrySupplier<Item> SPARK_ARCANE_CAR_BATTERY = register("spark_arcane_car_battery",
      settings -> new SimpleBatteryItem(settings, 2_500_000_000L, 2_000_000L));
    public static final RegistrySupplier<Item> SPARK_ARCANE_ENERGY_STORAGE_ARRAY = register("spark_arcane_energy_storage_array",
      settings -> new SimpleBatteryItem(settings, 10_000_000_000L, 2_000_000L));
    public static final RegistrySupplier<Item> SPARK_ARCANE_MASS_ENERGY_VOID = register("spark_arcane_mass_energy_void",
      settings -> new SimpleBatteryItem(settings, 100_000_000_000L, 20_000_000L));
    public static final RegistrySupplier<Item> SPARK_ARCANE_DIRAC_SEA = register("spark_arcane_dirac_sea",
      settings -> new SimpleBatteryItem(settings, 250_000_000_000L, 20_000_000L));
    public static final RegistrySupplier<Item> SPARK_SOLID_SPACE_TIME_CRYSTAL = register("spark_solid_space_time_crystal",
      settings -> new SimpleBatteryItem(settings, 1_000_000_000_000L, 200_000_000L));
    public static final RegistrySupplier<Item> SPARK_LUDICROUS_ENERGY_STORAGE_UNIT = register("spark_ludicrous_energy_storage_unit",
      settings -> new SimpleBatteryItem(settings, 100_000_000_000_000L, 200_000_000L));
    public static final RegistrySupplier<Item> ELECTRONIUM_CUBE = register("electronium_cube",
      settings -> new SimpleBatteryItem(settings, 1_000_000_000_000_000_000L, 1_000_000_000_000_000L));
    public static final RegistrySupplier<Item> INFINITE_BATTERY = register("infinite_battery", InfiniteBatteryItem::new);
    public static final RegistrySupplier<Item> POTATO_BATTERY = register("potato_battery", settings -> new SimpleBatteryItem(settings, 1_000L, 0L, 100L),
      () -> new Item.Properties().component(NtmDataComponentTypes.ENERGY_COMPONENT_TYPE.get(), 1_000L));
    public static final RegistrySupplier<Item> POTATOS = register("potatos", settings -> new PotatOSItem(settings, 500_000L, 0L, 100L),
      () -> new Item.Properties().component(NtmDataComponentTypes.ENERGY_COMPONENT_TYPE.get(), 500_000L));
    public static final RegistrySupplier<Item> SELF_CHARGING_URANIUM_238_BATTERY = register("self_charging_uranium_238_battery", settings -> new SelfChargingBatteryItem(settings, 5L));
    public static final RegistrySupplier<Item> SELF_CHARGING_TECHNETIUM_99_BATTERY = register("self_charging_technetium_99_battery", settings -> new SelfChargingBatteryItem(settings, 25L));
    public static final RegistrySupplier<Item> SELF_CHARGING_PLUTONIUM_238_BATTERY = register("self_charging_plutonium_238_battery", settings -> new SelfChargingBatteryItem(settings, 100L));
    public static final RegistrySupplier<Item> SELF_CHARGING_POLONIUM_210_BATTERY = register("self_charging_polonium_210_battery", settings -> new SelfChargingBatteryItem(settings, 500L));
    public static final RegistrySupplier<Item> SELF_CHARGING_GOLD_198_BATTERY = register("self_charging_gold_198_battery", settings -> new SelfChargingBatteryItem(settings, 2_500L));
    public static final RegistrySupplier<Item> SELF_CHARGING_LEAD_209_BATTERY = register("self_charging_lead_209_battery", settings -> new SelfChargingBatteryItem(settings, 5_000L));
    public static final RegistrySupplier<Item> SELF_CHARGING_AMERICIUM_241_BATTERY = register("self_charging_americium_241_battery", settings -> new SelfChargingBatteryItem(settings, 10_000L));

    // Consumables
    public static final RegistrySupplier<Item> EMPTY_SYRINGE = register("empty_syringe", Item::new);
    public static final RegistrySupplier<Item> POISONOUS_INJECTION = register("poisonous_injection", settings ->
      new InjectionItem(settings, NtmSounds.SYRINGE_INJECTS, EMPTY_SYRINGE,
        (serverWorld, entity) -> {
            if (!entity.hasInfiniteMaterials()) {
                EntityUtil.applyDamage(entity, serverWorld, NtmDamageTypes.EUTHANIZED, 30f);
            } else {
                EntityUtil.applyDamage(entity, serverWorld, NtmDamageTypes.EUTHANIZED, 2f);
            }
        })
    );
    public static final RegistrySupplier<Item> ANTIDOTE = register("antidote", settings ->
      new InjectionItem(settings, NtmSounds.SYRINGE_INJECTS, EMPTY_SYRINGE, (serverWorld, entity) -> entity.removeAllEffects())
    );
    public static final RegistrySupplier<Item> AWESOME = register("awesome", settings ->
      new InjectionItem(settings, NtmSounds.SYRINGE_INJECTS, EMPTY_SYRINGE,
        (serverWorld, entity) -> {
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 150, 4, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.SPEED, 700, 6, false, false, true));
            entity.addEffect(new MobEffectInstance(NtmStatusEffects.RAD_X, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.HASTE, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 700, 24, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 700, 4, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 700, 9, false, false, true));
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 700, 0, false, false, true));
        }), () -> new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
    );
    public static final RegistrySupplier<Item> METAL_SYRINGE = register("metal_syringe", Item::new);
    public static final RegistrySupplier<Item> STIMPAK = register("stimpak", settings -> new InjectionWithTooltipItem(settings, NtmSounds.SYRINGE_INJECTS, METAL_SYRINGE,
      (serverWorld, entity) -> entity.heal(5F)
    ));
    public static final RegistrySupplier<Item> MED_X = register("med_x", settings -> new InjectionWithTooltipItem(settings, NtmSounds.SYRINGE_INJECTS, METAL_SYRINGE,
      (serverWorld, entity) -> entity.addEffect(
        new MobEffectInstance(MobEffects.RESISTANCE, 4800, 2, false, false, true)
      )
    ));
    public static final RegistrySupplier<Item> PSYCHO = register("psycho", settings -> new InjectionWithTooltipItem(settings, 2, NtmSounds.SYRINGE_INJECTS, METAL_SYRINGE,
      (serverWorld, entity) -> {
          entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 2400, 0, false, false, true));
          entity.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 2400, 0, false, false, true));
      }));
    public static final RegistrySupplier<Item> SUPER_STIMPAK = register("super_stimpak", settings -> new InjectionWithTooltipItem(settings, 2, NtmSounds.SYRINGE_INJECTS, METAL_SYRINGE,
      (serverWorld, entity) -> {
          entity.heal(50F);
          entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 0, false, false, true));
      }));
    public static final RegistrySupplier<Item> FIRST_AID_KIT = register("first_aid_kit", settings -> new InjectionWithTooltipItem(settings, 2, null, List.of(),
      (serverWorld, entity) -> {
          entity.setHealth(entity.getMaxHealth());
          EntityUtil.removeNegativeEffects(entity);
      }));

    public static final RegistrySupplier<Item> IV_BAG = register("iv_bag", IvBagItem::new);
    public static final RegistrySupplier<Item> BLOOD_BAG = register("blood_bag", settings -> new InjectionItem(settings, NtmSounds.IV_BAG_INJECTS, IV_BAG,
      (serverWorld, entity) -> entity.heal(5F)
    ));
    public static final RegistrySupplier<Item> EMPTY_EXPERIENCE_BAG = register("empty_experience_bag", EmptyExperienceBagItem::new);
    public static final RegistrySupplier<Item> EXPERIENCE_BAG = register("experience_bag", ExperienceBagItem::new);
    public static final RegistrySupplier<Item> RAD_AWAY = register("rad_away", settings -> new InjectionItem(settings, NtmSounds.IV_BAG_INJECTS, IV_BAG,
      (serverWorld, entity) -> EntityUtil.addEffectDuration(entity, NtmStatusEffects.RAD_AWAY, 140)
    ));
    public static final RegistrySupplier<Item> STRONG_RAD_AWAY = register("strong_rad_away", settings -> new InjectionItem(settings, NtmSounds.IV_BAG_INJECTS, IV_BAG,
      (serverWorld, entity) -> EntityUtil.addEffectDuration(entity, NtmStatusEffects.RAD_AWAY, 350)
    ));
    public static final RegistrySupplier<Item> ELITE_RAD_AWAY = register("elite_rad_away", settings -> new InjectionItem(settings, NtmSounds.IV_BAG_INJECTS, IV_BAG,
      (serverWorld, entity) -> EntityUtil.addEffectDuration(entity, NtmStatusEffects.RAD_AWAY, 500)
    ));
    public static final RegistrySupplier<Item> RAD_X = register("rad_x", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.ALWAYS_EDIBLE, NtmConsumableComponents.RAD_X)
    );
    public static final RegistrySupplier<Item> IODINE_PILL = register("iodine_pill", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.ALWAYS_EDIBLE, NtmConsumableComponents.IODINE_PILL)
    );
    public static final RegistrySupplier<Item> PLAN_C = register("plan_c", PlanC::new, () -> new Item.Properties()
      .food(NtmFoodComponents.ALWAYS_EDIBLE)
    );
    public static final RegistrySupplier<Item> EMPTY_CAN = register("empty_can", Item::new);
    public static final RegistrySupplier<Item> RING_PULL = register("ring_pull", Item::new);
    public static final RegistrySupplier<Item> SMART_ENERGY_DRINK = register("smart_energy_drink", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 3, false, false, true)
      )));
    public static final RegistrySupplier<Item> CREATURE_ENERGY_DRINK = register("creature_energy_drink", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 3, false, false, true)
      )));
    public static final RegistrySupplier<Item> RED_BOMB_ENERGY_DRINK = register("red_bomb_energy_drink", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.ABSORPTION, 600, 2, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 1, false, false, true)
      )));
    public static final RegistrySupplier<Item> DR_SUGAR_SOFT_DRINK = register("dr_sugar_soft_drink", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 2, false, false, true)
      )));
    public static final RegistrySupplier<Item> OVERCHARGE_DELIRIUM_XT = register("overcharge_delirium_xt", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 2, false, false, true)
      )));
    public static final RegistrySupplier<Item> BLACK_MESA_LUNA_DARK_COLA = register("black_mesa_luna_dark_cola", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.REGENERATION, 600, 2, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 2, false, false, true)
      )));
    public static final RegistrySupplier<Item> BEPIS = register("bepis", settings -> new DrinkCanItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 3, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 3, false, false, true)
      )));
    public static final RegistrySupplier<Item> DR_BREENS_PRIVATE_RESERVE = register("dr_breens_private_reserve", settings -> new DrinkCanItem(settings, 2,
      List.of(
        new MobEffectInstance(MobEffects.NAUSEA, 600, 0, false, false, true)
      )));
    public static final RegistrySupplier<Item> MUG_ROOT_BEER = register("mug_root_beer", settings -> new DrinkCanItem(settings, 0,
      List.of(
        new MobEffectInstance(MobEffects.REGENERATION, 1200, 2, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 3600, 2, false, false, true)
      )));

    public static final RegistrySupplier<Item> WAFFLE_OF_MASS_DESTRUCTION = register("waffle_of_mass_destruction", DestructiveWaffleItem::new);
    public static final RegistrySupplier<Item> VEGAN_SCHNITZEL = register("vegan_schnitzel", VeganSchnitzelItem::new);
    public static final RegistrySupplier<Item> RADIOACTIVE_COTTON_CANDY = register("radioactive_cotton_candy", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.RADIOACTIVE_COTTON_CANDY, NtmConsumableComponents.RADIOACTIVE_COTTON_CANDY));

    public static final RegistrySupplier<Item> BASIC_LEAD_APPLE = register("basic_lead_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.LEAD_APPLE, NtmConsumableComponents.BASIC_LEAD_APPLE).rarity(Rarity.UNCOMMON));
    public static final RegistrySupplier<Item> GOOD_LEAD_APPLE = register("good_lead_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.LEAD_APPLE, NtmConsumableComponents.GOOD_LEAD_APPLE).rarity(Rarity.RARE));
    public static final RegistrySupplier<Item> EPIC_LEAD_APPLE = register("epic_lead_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.LEAD_APPLE, NtmConsumableComponents.EPIC_LEAD_APPLE).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final RegistrySupplier<Item> BASIC_SCHRABIDIUM_APPLE = register("basic_schrabidium_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.SCHRABIDIUM_APPLE, NtmConsumableComponents.BASIC_SCHRABIDIUM_APPLE).rarity(Rarity.UNCOMMON));
    public static final RegistrySupplier<Item> GOOD_SCHRABIDIUM_APPLE = register("good_schrabidium_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.SCHRABIDIUM_APPLE, NtmConsumableComponents.GOOD_SCHRABIDIUM_APPLE).rarity(Rarity.RARE));
    public static final RegistrySupplier<Item> EPIC_SCHRABIDIUM_APPLE = register("epic_schrabidium_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.SCHRABIDIUM_APPLE, NtmConsumableComponents.EPIC_SCHRABIDIUM_APPLE).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final RegistrySupplier<Item> EUPHEMIUM_APPLE = register("euphemium_apple", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.SCHRABIDIUM_APPLE, NtmConsumableComponents.EUPHEMIUM_APPLE).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    public static final RegistrySupplier<Item> CHEAP_TEM_FLAKES = register("cheap_tem_flakes", TemFlakesItem::new);
    public static final RegistrySupplier<Item> TEM_FLAKES = register("tem_flakes", TemFlakesItem::new);
    public static final RegistrySupplier<Item> EXPENSIVE_TEM_FLAKES = register("expensive_tem_flakes", TemFlakesItem::new);

    public static final RegistrySupplier<Item> GLOWING_MUSHROOM_STEW = register("glowing_mushroom_stew", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.GLOWING_MUSHROOM_STEW).usingConvertsTo(Items.BOWL));
    public static final RegistrySupplier<Item> SCRAMBLED_BALEFIRE_EGG = register("scrambled_balefire_egg", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.GLOWING_MUSHROOM_STEW).usingConvertsTo(Items.BOWL));
    public static final RegistrySupplier<Item> SCRAMBLED_BALEFIRE_EGG_AND_HAM = register("scrambled_balefire_egg_and_ham", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.GLOWING_MUSHROOM_STEW).usingConvertsTo(Items.BOWL));
    public static final RegistrySupplier<Item> LEMON = register("lemon", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.LEMON));
    public static final RegistrySupplier<Item> MRE = register("mre", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.MRE));
    public static final RegistrySupplier<Item> LOOPS = register("loops", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.LOOPS));
    public static final RegistrySupplier<Item> IT_BREAKFAST = register("it_breakfast", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.IT_BREAKFAST).usingConvertsTo(Items.BOWL));
    public static final RegistrySupplier<Item> SPONGEBOB_MACARONI = register("spongebob_macaroni", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.SPONGEBOB_MACARONI));
    public static final RegistrySupplier<Item> FOOD_ITEM = register("food_item", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.FOOD_ITEM));
    public static final RegistrySupplier<Item> TWINKIE = register("twinkie", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.TWINKIE));
    public static final RegistrySupplier<Item> TV_STATIC_SANDWICH = register("tv_static_sandwich", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.TV_STATIC_SANDWICH));
    public static final RegistrySupplier<Item> PUDDING = register("pudding", settings -> new TooltipItem(settings, 3), () -> new Item.Properties()
      .food(NtmFoodComponents.PUDDING));
    public static final RegistrySupplier<Item> SCRAP_PANCAKE = register("scrap_pancake", ScrapPancakeItem::new);
    public static final RegistrySupplier<Item> CHICKEN_NUGGET = register("chicken_nugget", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.CHICKEN_NUGGET));
    public static final RegistrySupplier<Item> PEAS = register("peas", PeasItem::new);
    public static final RegistrySupplier<Item> MARSHMALLOW_ON_A_STICK = register("marshmallow_on_a_stick", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.MARSHMALLOW_ON_A_STICK));
    public static final RegistrySupplier<Item> ROASTED_MARSHMALLOW_ON_A_STICK = register("roasted_marshmallow_on_a_stick", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.ROASTED_MARSHMALLOW_ON_A_STICK));
    public static final RegistrySupplier<Item> CHEESE = register("cheese", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.CHEESE));
    public static final RegistrySupplier<Item> CHEESE_QUESADILLA = register("cheese_quesadilla", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.CHEESE_QUESADILLA));
    public static final RegistrySupplier<Item> GLYPHID_MEAT = register("glyphid_meat", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.GLYPHID_MEAT));
    public static final RegistrySupplier<Item> GRILLED_GLYPHID_MEAT = register("grilled_glyphid_meat", Item::new, () -> new Item.Properties()
      .food(NtmFoodComponents.GRILLED_GLYPHID_MEAT));
    public static final RegistrySupplier<Item> GLYPHID_EGG = register("glyphid_egg", Item::new);
    public static final RegistrySupplier<Item> IPECAC_SYRUP = register("ipecac_syrup", settings -> new TooltipItem(settings, 2), () -> new Item.Properties()
      .food(NtmFoodComponents.ALWAYS_EDIBLE, NtmConsumableComponents.IPECAC_SYRUP)
    );
    public static final RegistrySupplier<Item> PTSD_MEDICATION = register("ptsd_medication", settings -> new TooltipItem(settings, 2), () -> new Item.Properties()
      .food(NtmFoodComponents.ALWAYS_EDIBLE, NtmConsumableComponents.IPECAC_SYRUP)
    );
    public static final RegistrySupplier<Item> STYLISH_FLASK = register("stylish_flask", StylishFlaskItem::new);
    public static final RegistrySupplier<Item> ARIZONA_MUCHO_MANGO = register("arizona_mucho_mango", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.ARIZONA_MUCHO_MANGO, NtmConsumableComponents.ARIZONA_MUCHO_MANGO));
    public static final RegistrySupplier<Item> RADIUM_CHOCOLATE = register("radium_chocolate", TooltipItem::new, () -> new Item.Properties()
      .food(NtmFoodComponents.RADIUM_CHOCOLATE, NtmConsumableComponents.RADIUM_CHOCOLATE));
    public static final RegistrySupplier<Item> COFFEE = register("coffee", CoffeeItem::new);
    public static final RegistrySupplier<Item> RADIUM_COFFEE = register("radium_coffee", RadiumCoffeeItem::new);
    public static final RegistrySupplier<Item> BOTTLE_OPENER = register("bottle_opener", settings -> new TooltipItem(settings, 2));
    public static final RegistrySupplier<Item> EMPTY_BOTTLE = register("empty_bottle", Item::new);
    public static final RegistrySupplier<Item> EMPTY_BOMB_BOTTLE = register("empty_bomb_bottle", Item::new);
    public static final RegistrySupplier<Item> NUKA_COLA_BOTTLE_CAP = register("nuka_cola_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> NUKA_COLA_QUANTUM_BOTTLE_CAP = register("nuka_cola_quantum_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> S_COLA_BOTTLE_CAP = register("s_cola_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> S_COLA_RAD_BOTTLE_CAP = register("s_cola_rad_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> KAROL_BOTTLE_CAP = register("karol_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> FRITZ_COLA_BOTTLE_CAP = register("fritz_cola_bottle_cap", Item::new);
    public static final RegistrySupplier<Item> BOTTLE_OF_NUKA_COLA = register("bottle_of_nuka_cola", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 600, 1, false, false, true)
      ), List.of(
      EMPTY_BOMB_BOTTLE,
      NUKA_COLA_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_NUKA_CHERRY = register("bottle_of_nuka_cherry", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 2, false, false, true)
      ), List.of(
      EMPTY_BOMB_BOTTLE,
      NUKA_COLA_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_NUKA_COLA_QUANTUM = register("bottle_of_nuka_cola_quantum", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 2, false, false, true)
      ), List.of(
      EMPTY_BOMB_BOTTLE,
      NUKA_COLA_QUANTUM_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_S_COLA = register("bottle_of_s_cola", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 2400, 2, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 2400, 2, false, false, true)
      ), List.of(
      EMPTY_BOMB_BOTTLE,
      S_COLA_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_S_COLA_RAD = register("bottle_of_s_cola_rad", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 2400, 4, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 2400, 2, false, false, true),
        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 0, false, false, true)
      ), List.of(
      EMPTY_BOMB_BOTTLE,
      S_COLA_RAD_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_KAROL = register("bottle_of_karol", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 600, 2, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 600, 2, false, false, true)
      ), List.of(
      EMPTY_BOTTLE,
      KAROL_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> FIRST_BOTTLE_OF_KAROL = register("first_bottle_of_karol", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 2400, 2, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 2400, 2, false, false, true)
      ), List.of(
      EMPTY_BOTTLE,
      KAROL_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> BOTTLE_OF_FRITZ_COLA = register("bottle_of_fritz_cola", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 600, 1, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 2, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 600, 2, false, false, true)
      ), List.of(
      EMPTY_BOTTLE,
      FRITZ_COLA_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> FIRST_BOTTLE_OF_FRITZ_COLA = register("first_bottle_of_fritz_cola", settings -> new BottleItem(settings,
      List.of(
        new MobEffectInstance(MobEffects.SPEED, 2400, 1, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 2400, 2, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 2400, 2, false, false, true)
      ), List.of(
      EMPTY_BOTTLE,
      FRITZ_COLA_BOTTLE_CAP
    )));
    public static final RegistrySupplier<Item> WATERY_TAINT_INJECTION = register("watery_taint_injection", settings ->
      new InjectionWithTooltipItem(settings, 3, NtmSounds.SYRINGE_INJECTS,
        List.of(METAL_SYRINGE, EMPTY_BOTTLE),
        (serverWorld, entity) -> {
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 0, false, false, true));
            entity.addEffect(new MobEffectInstance(NtmStatusEffects.TAINT, 1200, 0, false, false, true));
        }
      ));

    // Tools
    public static final RegistrySupplier<Item> STEEL_SWORD = register("steel_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.STEEL_TOOL_MATERIAL, 3.5f, 1f));
    public static final RegistrySupplier<Item> STEEL_PICKAXE = register("steel_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.STEEL_TOOL_MATERIAL, 1.5f, 1f));
    public static final RegistrySupplier<Item> STEEL_AXE = register("steel_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.STEEL_TOOL_MATERIAL, 2.5f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> STEEL_SHOVEL = register("steel_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.STEEL_TOOL_MATERIAL, 0.5f, 1f));
    public static final RegistrySupplier<Item> STEEL_HOE = register("steel_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.STEEL_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> TITANIUM_SWORD = register("titanium_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.TITANIUM_TOOL_MATERIAL, 3.5f, 1f));
    public static final RegistrySupplier<Item> TITANIUM_PICKAXE = register("titanium_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.TITANIUM_TOOL_MATERIAL, 1.5f, 1f));
    public static final RegistrySupplier<Item> TITANIUM_AXE = register("titanium_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.TITANIUM_TOOL_MATERIAL, 2.5f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> TITANIUM_SHOVEL = register("titanium_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.TITANIUM_TOOL_MATERIAL, 0.5f, 1f));
    public static final RegistrySupplier<Item> TITANIUM_HOE = register("titanium_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.TITANIUM_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> ADVANCED_ALLOY_SWORD = register("advanced_alloy_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.ADVANCED_ALLOY_TOOL_MATERIAL, 5f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder().addModifier(Modifiers.STUNNING, 2).build(),
        false
      ));
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_PICKAXE = register("advanced_alloy_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.ADVANCED_ALLOY_TOOL_MATERIAL, 2f, 1f,
        AbilityHandler.builder().addAbility(Abilities.VEIN_MINER, 1).build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_AXE = register("advanced_alloy_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.ADVANCED_ALLOY_TOOL_MATERIAL, 4f, 1f,
        AbilityHandler.builder().addAbility(Abilities.VEIN_MINER, 1).build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_SHOVEL = register("advanced_alloy_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.ADVANCED_ALLOY_TOOL_MATERIAL, 1f, 1f,
        AbilityHandler.builder().addAbility(Abilities.VEIN_MINER, 1).build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> ADVANCED_ALLOY_HOE = register("advanced_alloy_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.ADVANCED_ALLOY_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> CMB_STEEL_SWORD = register("cmb_steel_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.CMB_STEEL_TOOL_MATERIAL, 28f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.STUNNING, 2)
          .addModifier(Modifiers.VAMPIRE, 2)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> CMB_STEEL_PICKAXE = register("cmb_steel_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.CMB_STEEL_TOOL_MATERIAL, 3f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> CMB_STEEL_AXE = register("cmb_steel_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.CMB_STEEL_TOOL_MATERIAL, 23f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> CMB_STEEL_SHOVEL = register("cmb_steel_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.CMB_STEEL_TOOL_MATERIAL, 1f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> CMB_STEEL_HOE = register("cmb_steel_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.CMB_STEEL_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> COBALT_SWORD = register("cobalt_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.COBALT_TOOL_MATERIAL, 11.5f, 1f));
    public static final RegistrySupplier<Item> COBALT_PICKAXE = register("cobalt_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.COBALT_TOOL_MATERIAL, 1.5f, 1f));
    public static final RegistrySupplier<Item> COBALT_AXE = register("cobalt_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.COBALT_TOOL_MATERIAL, 4.5f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> COBALT_SHOVEL = register("cobalt_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.COBALT_TOOL_MATERIAL, 1f, 1f));
    public static final RegistrySupplier<Item> COBALT_HOE = register("cobalt_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.COBALT_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> DECORATED_COBALT_SWORD = register("decorated_cobalt_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.DECORATED_COBALT_TOOL_MATERIAL, 11f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder().addModifier(Modifiers.LUCK_OF_THE_COLLECTOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> DECORATED_COBALT_PICKAXE = register("decorated_cobalt_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.DECORATED_COBALT_TOOL_MATERIAL, 2f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 2)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> DECORATED_COBALT_AXE = register("decorated_cobalt_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.DECORATED_COBALT_TOOL_MATERIAL, 4f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 2)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> DECORATED_COBALT_SHOVEL = register("decorated_cobalt_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.DECORATED_COBALT_TOOL_MATERIAL, 1f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 2)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> DECORATED_COBALT_HOE = register("decorated_cobalt_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.DECORATED_COBALT_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> STARMETAL_SWORD = register("starmetal_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.STARMETAL_TOOL_MATERIAL, 19f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.DECAPITATOR)
          .addModifier(Modifiers.STUNNING, 3)
          .addModifier(Modifiers.LUCK_OF_THE_COLLECTOR)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> STARMETAL_PICKAXE = register("starmetal_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.STARMETAL_TOOL_MATERIAL, 2f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.STUNNING, 3).build(),
        false
      ));
    public static final RegistrySupplier<Item> STARMETAL_AXE = register("starmetal_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.STARMETAL_TOOL_MATERIAL, 6f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.STUNNING, 3)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> STARMETAL_SHOVEL = register("starmetal_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.STARMETAL_TOOL_MATERIAL, 1f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.STUNNING, 3).build(),
        false
      ));
    public static final RegistrySupplier<Item> STARMETAL_HOE = register("starmetal_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.STARMETAL_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> DESH_SWORD = register("desh_sword", settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.DESH_TOOL_MATERIAL, 9.5f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.STUNNING, 2)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> DESH_PICKAXE = register("desh_pickaxe", settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.DESH_TOOL_MATERIAL, 2f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 2)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> DESH_AXE = register("desh_axe", settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.DESH_TOOL_MATERIAL, 4.5f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 2)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.DECAPITATOR).build(),
        false
      ));
    public static final RegistrySupplier<Item> DESH_SHOVEL = register("desh_shovel", settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.DESH_TOOL_MATERIAL, 1f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 3)
          .addAbility(Abilities.AOE, 1)
          .addAbility(Abilities.FLAT_AOE, 1)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 2)
          .build(),
        ModifierHandler.builder().build(),
        false
      ));
    public static final RegistrySupplier<Item> DESH_HOE = register("desh_hoe", settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.DESH_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> SCHRABIDIUM_SWORD = register("schrabidium_sword", () -> new Item.Properties().rarity(Rarity.RARE), settings ->
      new SpecialSwordItem(settings, NtmToolMaterials.SCHRABIDIUM_TOOL_MATERIAL, 65f, 1f,
        AbilityHandler.builder().build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.RADIOACTIVE_BLADE, 50)
          .addModifier(Modifiers.VAMPIRE, 2)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> SCHRABIDIUM_PICKAXE = register("schrabidium_pickaxe", () -> new Item.Properties().rarity(Rarity.RARE), settings ->
      new SpecialPickaxeItem(settings, NtmToolMaterials.SCHRABIDIUM_TOOL_MATERIAL, 20f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 8)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.AUTO_SHREADER)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.RADIOACTIVE_BLADE, 15).build(),
        false
      ));
    public static final RegistrySupplier<Item> SCHRABIDIUM_AXE = register("schrabidium_axe", () -> new Item.Properties().rarity(Rarity.RARE), settings ->
      new SpecialAxeItem(settings, NtmToolMaterials.SCHRABIDIUM_TOOL_MATERIAL, 15f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 8)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.AUTO_SHREADER)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.RADIOACTIVE_BLADE, 15)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        false
      ));
    public static final RegistrySupplier<Item> SCHRABIDIUM_SHOVEL = register("schrabidium_shovel", () -> new Item.Properties().rarity(Rarity.RARE), settings ->
      new SpecialShovelItem(settings, NtmToolMaterials.SCHRABIDIUM_TOOL_MATERIAL, 5f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 8)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 5)
          .addAbility(Abilities.AUTO_SMELT)
          .addAbility(Abilities.AUTO_SHREADER)
          .build(),
        ModifierHandler.builder().addModifier(Modifiers.RADIOACTIVE_BLADE, 15).build(),
        false
      ));
    public static final RegistrySupplier<Item> SCHRABIDIUM_HOE = register("schrabidium_hoe", () -> new Item.Properties().rarity(Rarity.RARE), settings ->
      new SpecialHoeItem(settings, NtmToolMaterials.SCHRABIDIUM_TOOL_MATERIAL, 0f, 1f));

    public static final RegistrySupplier<Item> BISMUTH_PICKAXE = register("bismuth_pickaxe", settings ->
      new SpecialBigPickaxeItem(settings, NtmToolMaterials.BISMUTH_TOOL_MATERIAL, 0f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 2)
          .addAbility(Abilities.AUTO_SHREADER)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 2)
          .addModifier(Modifiers.STUNNING, 5)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        true
      ));
    public static final RegistrySupplier<Item> BISMUTH_AXE = register("bismuth_axe", settings ->
      new SpecialBigAxeItem(settings, NtmToolMaterials.BISMUTH_TOOL_MATERIAL, 10f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 2)
          .addAbility(Abilities.AUTO_SHREADER)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 3)
          .addModifier(Modifiers.STUNNING, 10)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        false
      ));

    public static final RegistrySupplier<Item> MOLTEN_PICKAXE = register("molten_pickaxe", settings ->
      new SpecialBigPickaxeItem(settings, NtmToolMaterials.MOLTEN_TOOL_MATERIAL, 0f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .addAbility(Abilities.AUTO_SMELT)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 2)
          .addModifier(Modifiers.FLAMING, 5)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        true
      ));
    public static final RegistrySupplier<Item> MOLTEN_AXE = register("molten_axe", settings ->
      new SpecialBigAxeItem(settings, NtmToolMaterials.MOLTEN_TOOL_MATERIAL, 10f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 3)
          .addAbility(Abilities.AUTO_SMELT)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 3)
          .addModifier(Modifiers.FLAMING, 10)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        false
      ));

    public static final RegistrySupplier<Item> CHLOROPHYTE_PICKAXE = register("chlorophyte_pickaxe", settings ->
      new SpecialBigPickaxeItem(settings, NtmToolMaterials.CHLOTOPHYTE_TOOL_MATERIAL, 0f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.FORTUNE, 4)
          .addAbility(Abilities.AUTO_CENTRIFUGE)
          .addAbility(Abilities.MERCURY_TOUCH)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 5)
          .addModifier(Modifiers.STUNNING, 10)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        true
      ));
    public static final RegistrySupplier<Item> CHLOROPHYTE_AXE = register("chlorophyte_axe", settings ->
      new SpecialBigAxeItem(settings, NtmToolMaterials.CHLOTOPHYTE_TOOL_MATERIAL, 30f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 4)
          .addAbility(Abilities.AOE, 2)
          .addAbility(Abilities.FLAT_AOE, 2)
          .addAbility(Abilities.FORTUNE, 4)
          .addAbility(Abilities.AUTO_CENTRIFUGE)
          .addAbility(Abilities.MERCURY_TOUCH)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.VAMPIRE, 10)
          .addModifier(Modifiers.STUNNING, 15)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        false
      ));

    public static final RegistrySupplier<Item> MESE_PICKAXE = register("mese_pickaxe", settings ->
      new SpecialBigPickaxeItem(settings, NtmToolMaterials.MESE_TOOL_MATERIAL, 0f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 5)
          .addAbility(Abilities.AOE, 3)
          .addAbility(Abilities.FLAT_AOE, 3)
          .addAbility(Abilities.EXPLOSION, 4)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 9)
          .addAbility(Abilities.AUTO_CRYSTALLIZER)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.STUNNING, 10)
          .addModifier(Modifiers.PHOSPHORUS_TIP, 60)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        true
      ));
    public static final RegistrySupplier<Item> MESE_AXE = register("mese_axe", settings ->
      new SpecialBigAxeItem(settings, NtmToolMaterials.MESE_TOOL_MATERIAL, 40f, 1f,
        AbilityHandler.builder()
          .addAbility(Abilities.VEIN_MINER, 5)
          .addAbility(Abilities.AOE, 3)
          .addAbility(Abilities.FLAT_AOE, 3)
          .addAbility(Abilities.EXPLOSION, 4)
          .addAbility(Abilities.SILK_TOUCH)
          .addAbility(Abilities.FORTUNE, 9)
          .build(),
        ModifierHandler.builder()
          .addModifier(Modifiers.STUNNING, 15)
          .addModifier(Modifiers.PHOSPHORUS_TIP, 90)
          .addModifier(Modifiers.DECAPITATOR)
          .build(),
        true
      ));

    public static RegistrySupplier<Item> register(@NotNull String name, @NotNull Function<Item.Properties, Item> itemFactory) {
        return register(name, Item.Properties::new, itemFactory);
    }

    public static RegistrySupplier<Item> register(
      @NotNull String name,
      @NotNull Function<Item.Properties, Item> itemFactory,
      @NotNull Supplier<Item.Properties> properties
    ) {
        return register(name, properties, itemFactory);
    }

    public static RegistrySupplier<Item> register(
      @NotNull String name,
      @NotNull Supplier<Item.Properties> properties,
      @NotNull Function<Item.Properties, Item> itemFactory
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Ntm.id(name));
        return NtmDeferredRegistries.ITEMS.register(itemKey.identifier(), () -> itemFactory.apply(properties.get().setId(itemKey)));
    }

    public static void init() {
    }
}
