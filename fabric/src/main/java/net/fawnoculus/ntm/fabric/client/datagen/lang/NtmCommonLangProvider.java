package net.fawnoculus.ntm.fabric.client.datagen.lang;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.node.network.NetworkType;
import net.fawnoculus.ntm.api.tool.Abilities;
import net.fawnoculus.ntm.api.tool.ItemAbility;
import net.fawnoculus.ntm.api.tool.ItemModifier;
import net.fawnoculus.ntm.api.tool.Modifiers;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.entity.NtmMobEffects;
import net.fawnoculus.ntm.items.NtmItemGroups;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.io.*;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import static net.fawnoculus.ntm.fabric.client.NtmDataGenerator.DOWNLOAD_COMMIT;

public abstract class NtmCommonLangProvider extends FabricLanguageProvider {

    protected NtmCommonLangProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, languageCode, registryLookup);
    }

    @SuppressWarnings("DataFlowIssue")
    public static void getTranslations(@NonNull Properties translations, @NonNull TranslationBuilder builder) {
        builder.add(NtmMobEffects.ASTOLFIZATION.get(), translations.getProperty("potion.hbm_death"));
        builder.add(NtmMobEffects.CONTAMINATED.get(), translations.getProperty("potion.hbm_radiation"));
        builder.add(NtmMobEffects.EXPLOSION.get(), translations.getProperty("potion.hbm_bang"));
        builder.add(NtmMobEffects.LEAD_POISONING.get(), translations.getProperty("potion.hbm_lead"));
        builder.add(NtmMobEffects.PHOSPHORUS_BURNS.get(), translations.getProperty("potion.hbm_phosphorus"));
        builder.add(NtmMobEffects.POTION_SICKNESS.get(), translations.getProperty("potion.hbm_potionsickness"));
        builder.add(NtmMobEffects.RAD_AWAY.get(), translations.getProperty("potion.hbm_radaway"));
        builder.add(NtmMobEffects.RAD_X.get(), translations.getProperty("potion.hbm_radx"));
        builder.add(NtmMobEffects.STABILITY.get(), translations.getProperty("potion.hbm_stability"));
        builder.add(NtmMobEffects.TAINT.get(), translations.getProperty("potion.hbm_taint"));
        builder.add(NtmMobEffects.TAINTED_HEART.get(), translations.getProperty("potion.hbm_mutation"));

        builder.add(NtmItemGroups.BOMBS_KEY, translations.getProperty("itemGroup.tabNuke"));
        builder.add(NtmItemGroups.CONSUMABLES_AND_GEAR_KEY, translations.getProperty("itemGroup.tabConsumable"));
        builder.add(NtmItemGroups.MACHINE_ITEMS_AND_FUEL_KEY, translations.getProperty("itemGroup.tabControl"));
        builder.add(NtmItemGroups.MACHINES_KEY, translations.getProperty("itemGroup.tabMachine"));
        builder.add(NtmItemGroups.MISSILES_AND_SATELLITES_KEY, translations.getProperty("itemGroup.tabMissile"));
        builder.add(NtmItemGroups.ORES_AND_BLOCKS_KEY, translations.getProperty("itemGroup.tabBlocks"));
        builder.add(NtmItemGroups.RESOURCES_AND_PARTS_KEY, translations.getProperty("itemGroup.tabParts"));
        builder.add(NtmItemGroups.TEMPLATES_KEY, translations.getProperty("itemGroup.tabTemplate"));
        builder.add(NtmItemGroups.WEAPONS_AND_TURRETS_KEY, translations.getProperty("itemGroup.tabWeapon"));

        builder.add(of(Abilities.AOE), translations.getProperty("tool.ability.hammer"));
        builder.add(of(Abilities.AUTO_CENTRIFUGE), translations.getProperty("tool.ability.centrifuge"));
        builder.add(of(Abilities.AUTO_CRYSTALLIZER), translations.getProperty("tool.ability.crystallizer"));
        builder.add(of(Abilities.AUTO_SHREADER), translations.getProperty("tool.ability.shredder"));
        builder.add(of(Abilities.AUTO_SMELT), translations.getProperty("tool.ability.smelter"));
        builder.add(of(Abilities.EXPLOSION), translations.getProperty("tool.ability.explosion"));
        builder.add(of(Abilities.FLAT_AOE), translations.getProperty("tool.ability.hammer_flat"));
        builder.add(of(Abilities.FORTUNE), translations.getProperty("tool.ability.luck"));
        builder.add(of(Abilities.MERCURY_TOUCH), translations.getProperty("tool.ability.mercury"));
        builder.add(of(Abilities.SILK_TOUCH), translations.getProperty("tool.ability.silktouch"));
        builder.add(of(Abilities.VEIN_MINER), translations.getProperty("tool.ability.recursion"));

        builder.add(of(Modifiers.DECAPITATOR), translations.getProperty("weapon.ability.beheader"));
        builder.add(of(Modifiers.FLAMING), translations.getProperty("weapon.ability.fire"));
        builder.add(of(Modifiers.LUCK_OF_THE_COLLECTOR), translations.getProperty("weapon.ability.bobble"));
        builder.add(of(Modifiers.PAIN_SAW), translations.getProperty("weapon.ability.chainsaw"));
        builder.add(of(Modifiers.PHOSPHORUS_TIP), translations.getProperty("weapon.ability.phosphorus"));
        builder.add(of(Modifiers.RADIOACTIVE_BLADE), translations.getProperty("weapon.ability.radiation"));
        builder.add(of(Modifiers.STUNNING), translations.getProperty("weapon.ability.stun"));
        builder.add(of(Modifiers.VAMPIRE), translations.getProperty("weapon.ability.vampire"));

        builder.add(of(NtmKeybinds.NTM_CATEGORY), translations.getProperty("hbm.key"));
        builder.add(of(NtmKeybinds.OPEN_QMAW_PAGE), translations.getProperty("hbm.key.qmaw"));
        builder.add(of(NtmKeybinds.OPEN_TOOL_ABILITY_GUI), translations.getProperty("hbm.key.abilityAlt"));

        builder.add(NtmItems.PLUTONIUM_238_RTG_PELLET.get(), translations.getProperty("item.pellet_rtg.name"));
        builder.add(tooltip(NtmItems.PLUTONIUM_238_RTG_PELLET), translations.getProperty("item.pellet_rtg.desc"));
        builder.add(NtmItems.TUNGSTEN_REACHER.get(), translations.getProperty("item.reacher.name"));
        builder.add(NtmItems.ROD_OF_DISCORD.get(), translations.getProperty("item.rod_of_discord.name"));

        builder.add(NtmItems.DEBUG_WAND.get(), translations.getProperty("item.wand_d.name"));
        builder.add(NtmItems.CONSTRUCTION_WAND.get(), translations.getProperty("item.wand_k.name"));
        builder.add(NtmItems.NETWORK_DEBUG_TOOL.get(), translations.getProperty("item.power_net_tool.name"));
        builder.add(NtmItems.GEIGER_COUNTER.get(), translations.getProperty("item.geiger_counter.name"));
        builder.add(NtmItems.DOSIMETER.get(), translations.getProperty("item.dosimeter.name"));

        builder.add(NtmItems.EMPTY_SYRINGE.get(), translations.getProperty("item.syringe_empty.name"));
        builder.add(NtmItems.POISONOUS_INJECTION.get(), translations.getProperty("item.syringe_poison.name"));
        builder.add(NtmItems.ANTIDOTE.get(), translations.getProperty("item.syringe_antidote.name"));
        builder.add(NtmItems.AWESOME.get(), translations.getProperty("item.syringe_awesome.name"));
        builder.add(NtmItems.METAL_SYRINGE.get(), translations.getProperty("item.syringe_metal_empty.name"));
        builder.add(NtmItems.STIMPAK.get(), translations.getProperty("item.syringe_metal_stimpak.name"));
        builder.add(NtmItems.MED_X.get(), translations.getProperty("item.syringe_metal_medx.name"));
        builder.add(NtmItems.PSYCHO.get(), translations.getProperty("item.syringe_metal_psycho.name"));
        builder.add(NtmItems.SUPER_STIMPAK.get(), translations.getProperty("item.syringe_metal_super.name"));
        builder.add(NtmItems.FIRST_AID_KIT.get(), translations.getProperty("item.med_bag.name"));
        builder.add(NtmItems.IV_BAG.get(), translations.getProperty("item.iv_empty.name"));
        builder.add(NtmItems.BLOOD_BAG.get(), translations.getProperty("item.iv_blood.name"));
        builder.add(NtmItems.EMPTY_EXPERIENCE_BAG.get(), translations.getProperty("item.iv_xp_empty.name"));
        builder.add(NtmItems.EXPERIENCE_BAG.get(), translations.getProperty("item.iv_xp.name"));
        builder.add(NtmItems.RAD_AWAY.get(), translations.getProperty("item.radaway.name"));
        builder.add(NtmItems.STRONG_RAD_AWAY.get(), translations.getProperty("item.radaway_strong.name"));
        builder.add(NtmItems.ELITE_RAD_AWAY.get(), translations.getProperty("item.radaway_flush.name"));
        builder.add(NtmItems.RAD_X.get(), translations.getProperty("item.radx.name"));
        builder.add(tooltip(NtmItems.RAD_X), translations.getProperty("item.radx.desc"));
        builder.add(NtmItems.IODINE_PILL.get(), translations.getProperty("item.pill_iodine.name"));
        builder.add(tooltip(NtmItems.IODINE_PILL), translations.getProperty("item.pill_iodine.desc"));
        builder.add(NtmItems.PLAN_C.get(), translations.getProperty("item.plan_c.name"));
        builder.add(tooltip(NtmItems.PLAN_C), translations.getProperty("item.plan_c.desc"));
        builder.add(NtmItems.EMPTY_CAN.get(), translations.getProperty("item.can_empty.name"));
        builder.add(NtmItems.RING_PULL.get(), translations.getProperty("item.ring_pull.name"));
        builder.add(NtmItems.SMART_ENERGY_DRINK.get(), translations.getProperty("item.can_smart.name"));
        builder.add(NtmItems.CREATURE_ENERGY_DRINK.get(), translations.getProperty("item.can_creature.name"));
        builder.add(NtmItems.RED_BOMB_ENERGY_DRINK.get(), translations.getProperty("item.can_redbomb.name"));
        builder.add(NtmItems.DR_SUGAR_SOFT_DRINK.get(), translations.getProperty("item.can_mrsugar.name"));
        builder.add(NtmItems.OVERCHARGE_DELIRIUM_XT.get(), translations.getProperty("item.can_overcharge.name"));
        builder.add(NtmItems.BLACK_MESA_LUNA_DARK_COLA.get(), translations.getProperty("item.can_luna.name"));
        builder.add(NtmItems.BEPIS.get(), translations.getProperty("item.can_bepis.name"));
        builder.add(NtmItems.DR_BREENS_PRIVATE_RESERVE.get(), translations.getProperty("item.can_breen.name"));
        builder.add(NtmItems.MUG_ROOT_BEER.get(), translations.getProperty("item.can_mug.name"));
        builder.add(NtmItems.WAFFLE_OF_MASS_DESTRUCTION.get(), translations.getProperty("item.bomb_waffle.name"));
        builder.add(NtmItems.VEGAN_SCHNITZEL.get(), translations.getProperty("item.schnitzel_vegan.name"));
        builder.add(NtmItems.RADIOACTIVE_COTTON_CANDY.get(), translations.getProperty("item.cotton_candy.name"));
        builder.add(NtmItems.BASIC_LEAD_APPLE.get(), translations.getProperty("item.apple_lead.name"));
        builder.add(NtmItems.GOOD_LEAD_APPLE.get(), translations.getProperty("item.apple_lead.name"));
        builder.add(NtmItems.EPIC_LEAD_APPLE.get(), translations.getProperty("item.apple_lead.name"));
        builder.add(NtmItems.BASIC_SCHRABIDIUM_APPLE.get(), translations.getProperty("item.apple_schrabidium.name"));
        builder.add(NtmItems.GOOD_SCHRABIDIUM_APPLE.get(), translations.getProperty("item.apple_schrabidium.name"));
        builder.add(NtmItems.EPIC_SCHRABIDIUM_APPLE.get(), translations.getProperty("item.apple_schrabidium.name"));
        builder.add(NtmItems.EUPHEMIUM_APPLE.get(), translations.getProperty("item.apple_euphemium.name"));
        builder.add(NtmItems.CHEAP_TEM_FLAKES.get(), translations.getProperty("item.tem_flakes.name"));
        builder.add(NtmItems.TEM_FLAKES.get(), translations.getProperty("item.tem_flakes.name"));
        builder.add(NtmItems.EXPENSIVE_TEM_FLAKES.get(), translations.getProperty("item.tem_flakes.name"));
        builder.add(NtmItems.GLOWING_MUSHROOM_STEW.get(), translations.getProperty("item.glowing_stew.name"));
        builder.add(NtmItems.SCRAMBLED_BALEFIRE_EGG.get(), translations.getProperty("item.balefire_scrambled.name"));
        builder.add(NtmItems.HAM_AND_BALEFIRE_EGGS.get(), translations.getProperty("item.balefire_and_ham.name"));
        builder.add(NtmItems.LEMON.get(), translations.getProperty("item.lemon.name"));
        builder.add(tooltip(NtmItems.LEMON), translations.getProperty("item.lemon.desc"));
        builder.add(NtmItems.MRE.get(), translations.getProperty("item.definitelyfood.name"));
        builder.add(NtmItems.LOOPS.get(), translations.getProperty("item.loops.name"));
        builder.add(tooltip(NtmItems.LOOPS), translations.getProperty("item.loops.desc"));
        builder.add(NtmItems.IT_BREAKFAST.get(), translations.getProperty("item.loop_stew.name"));
        builder.add(tooltip(NtmItems.IT_BREAKFAST), translations.getProperty("item.loop_stew.desc"));
        builder.add(NtmItems.SPONGEBOB_MACARONI.get(), translations.getProperty("item.spongebob_macaroni.name"));
        builder.add(NtmItems.FOOD_ITEM.get(), translations.getProperty("item.fooditem.name"));
        builder.add(NtmItems.TWINKIE.get(), translations.getProperty("item.twinkie.name"));
        builder.add(tooltip(NtmItems.TWINKIE), translations.getProperty("item.twinkie.desc"));
        builder.add(NtmItems.TV_STATIC_SANDWICH.get(), translations.getProperty("item.static_sandwich.name"));
        builder.add(NtmItems.PUDDING.get(), translations.getProperty("item.pudding.name"));
        String[] puddingTooltips = translations.getProperty("item.pudding.desc", "").split("\\$");
        if (puddingTooltips.length == 3) {
            builder.add(tooltip(NtmItems.PUDDING, 1), puddingTooltips[0]);
            builder.add(tooltip(NtmItems.PUDDING, 2), puddingTooltips[1]);
            builder.add(tooltip(NtmItems.PUDDING, 3), puddingTooltips[2]);
        }
        builder.add(NtmItems.SCRAP_PANCAKE.get(), translations.getProperty("item.pancake.name"));
        builder.add(NtmItems.CHICKEN_NUGGET.get(), translations.getProperty("item.nugget.name"));
        builder.add(NtmItems.PEAS.get(), translations.getProperty("item.peas.name"));
        builder.add(tooltip(NtmItems.PEAS), translations.getProperty("item.peas.desc"));
        builder.add(NtmItems.MARSHMALLOW_ON_A_STICK.get(), translations.getProperty("item.marshmallow.name"));
        builder.add(NtmItems.ROASTED_MARSHMALLOW_ON_A_STICK.get(), translations.getProperty("item.marshmallow.name"));
        builder.add(NtmItems.CHEESE.get(), translations.getProperty("item.cheese.name"));
        builder.add(NtmItems.CHEESE_QUESADILLA.get(), translations.getProperty("item.cheese_quesadilla.name"));
        builder.add(tooltip(NtmItems.CHEESE_QUESADILLA), translations.getProperty("item.cheese_quesadilla.desc"));
        builder.add(NtmItems.GLYPHID_MEAT.get(), translations.getProperty("item.glyphid_meat.name"));
        builder.add(NtmItems.GRILLED_GLYPHID_MEAT.get(), translations.getProperty("item.glyphid_meat_grilled.name"));
        builder.add(NtmItems.GLYPHID_EGG.get(), translations.getProperty("item.egg_glyphid.name"));
        builder.add(NtmItems.IPECAC_SYRUP.get(), translations.getProperty("item.med_ipecac.name"));
        String[] ipecacTooltips = translations.getProperty("item.med_ipecac.desс", "").split("\\$");
        if (ipecacTooltips.length == 2) {
            builder.add(tooltip(NtmItems.IPECAC_SYRUP, 1), ipecacTooltips[0]);
            builder.add(tooltip(NtmItems.IPECAC_SYRUP, 2), ipecacTooltips[1]);
        }
        builder.add(NtmItems.PTSD_MEDICATION.get(), translations.getProperty("item.med_ptsd.name"));
        String[] ptsdMedTooltips = translations.getProperty("item.med_ptsd.desc", "").split("\\$");
        if (ptsdMedTooltips.length == 2) {
            builder.add(tooltip(NtmItems.PTSD_MEDICATION, 1), ptsdMedTooltips[0]);
            builder.add(tooltip(NtmItems.PTSD_MEDICATION, 2), ptsdMedTooltips[1]);
        }
        builder.add(NtmItems.STYLISH_FLASK.get(), translations.getProperty("item.canteen_vodka.name"));
        builder.add(NtmItems.ARIZONA_MUCHO_MANGO.get(), translations.getProperty("item.mucho_mango.name"));
        builder.add(NtmItems.RADIUM_CHOCOLATE.get(), translations.getProperty("item.chocolate.name"));
        builder.add(tooltip(NtmItems.RADIUM_CHOCOLATE), translations.getProperty("item.chocolate.desc"));
        builder.add(NtmItems.COFFEE.get(), translations.getProperty("item.coffee.name"));
        builder.add(NtmItems.RADIUM_COFFEE.get(), translations.getProperty("item.coffee_radium.name"));
        builder.add(NtmItems.BOTTLE_OPENER.get(), translations.getProperty("item.bottle_opener.name"));
        builder.add(NtmItems.EMPTY_BOTTLE.get(), translations.getProperty("item.bottle2_empty.name"));
        builder.add(NtmItems.EMPTY_BOMB_BOTTLE.get(), translations.getProperty("item.bottle_empty.name"));
        builder.add(NtmItems.NUKA_COLA_BOTTLE_CAP.get(), translations.getProperty("item.cap_nuka.name"));
        builder.add(NtmItems.NUKA_COLA_QUANTUM_BOTTLE_CAP.get(), translations.getProperty("item.cap_quantum.name"));
        builder.add(NtmItems.S_COLA_BOTTLE_CAP.get(), translations.getProperty("item.cap_sparkle.name"));
        builder.add(NtmItems.S_COLA_RAD_BOTTLE_CAP.get(), translations.getProperty("item.cap_rad.name"));
        builder.add(NtmItems.KAROL_BOTTLE_CAP.get(), translations.getProperty("item.cap_korl.name"));
        builder.add(NtmItems.FRITZ_COLA_BOTTLE_CAP.get(), translations.getProperty("item.cap_fritz.name"));
        builder.add(NtmItems.BOTTLE_OF_NUKA_COLA.get(), translations.getProperty("item.bottle_nuka.name"));
        builder.add(NtmItems.BOTTLE_OF_NUKA_CHERRY.get(), translations.getProperty("item.bottle_cherry.name"));
        builder.add(NtmItems.BOTTLE_OF_NUKA_COLA_QUANTUM.get(), translations.getProperty("item.bottle_quantum.name"));
        builder.add(NtmItems.BOTTLE_OF_S_COLA.get(), translations.getProperty("item.bottle_sparkle.name"));
        builder.add(NtmItems.BOTTLE_OF_S_COLA_RAD.get(), translations.getProperty("item.bottle_rad.name"));
        builder.add(NtmItems.BOTTLE_OF_KAROL.get(), translations.getProperty("item.bottle2_korl.name"));
        builder.add(NtmItems.FIRST_BOTTLE_OF_KAROL.get(), translations.getProperty("item.bottle2_korl_special.name"));
        builder.add(NtmItems.BOTTLE_OF_FRITZ_COLA.get(), translations.getProperty("item.bottle2_fritz.name"));
        builder.add(NtmItems.FIRST_BOTTLE_OF_FRITZ_COLA.get(), translations.getProperty("item.bottle2_fritz_special.name"));
        builder.add(NtmItems.WATERY_TAINT_INJECTION.get(), translations.getProperty("item.syringe_taint.name"));

        builder.add(NtmItems.STEEL_SWORD.get(), translations.getProperty("item.steel_sword.name"));
        builder.add(NtmItems.STEEL_PICKAXE.get(), translations.getProperty("item.steel_pickaxe.name"));
        builder.add(NtmItems.STEEL_AXE.get(), translations.getProperty("item.steel_axe.name"));
        builder.add(NtmItems.STEEL_SHOVEL.get(), translations.getProperty("item.steel_shovel.name"));
        builder.add(NtmItems.STEEL_HOE.get(), translations.getProperty("item.steel_hoe.name"));
        builder.add(NtmItems.TITANIUM_SWORD.get(), translations.getProperty("item.titanium_sword.name"));
        builder.add(NtmItems.TITANIUM_PICKAXE.get(), translations.getProperty("item.titanium_pickaxe.name"));
        builder.add(NtmItems.TITANIUM_AXE.get(), translations.getProperty("item.titanium_axe.name"));
        builder.add(NtmItems.TITANIUM_SHOVEL.get(), translations.getProperty("item.titanium_shovel.name"));
        builder.add(NtmItems.TITANIUM_HOE.get(), translations.getProperty("item.titanium_hoe.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_SWORD.get(), translations.getProperty("item.alloy_sword.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_PICKAXE.get(), translations.getProperty("item.alloy_pickaxe.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_AXE.get(), translations.getProperty("item.alloy_axe.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_SHOVEL.get(), translations.getProperty("item.alloy_shovel.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_HOE.get(), translations.getProperty("item.alloy_hoe.name"));
        builder.add(NtmItems.CMB_STEEL_SWORD.get(), translations.getProperty("item.cmb_sword.name"));
        builder.add(NtmItems.CMB_STEEL_PICKAXE.get(), translations.getProperty("item.cmb_pickaxe.name"));
        builder.add(NtmItems.CMB_STEEL_AXE.get(), translations.getProperty("item.cmb_axe.name"));
        builder.add(NtmItems.CMB_STEEL_SHOVEL.get(), translations.getProperty("item.cmb_shovel.name"));
        builder.add(NtmItems.CMB_STEEL_HOE.get(), translations.getProperty("item.cmb_hoe.name"));
        builder.add(NtmItems.COBALT_SWORD.get(), translations.getProperty("item.cobalt_sword.name"));
        builder.add(NtmItems.COBALT_PICKAXE.get(), translations.getProperty("item.cobalt_pickaxe.name"));
        builder.add(NtmItems.COBALT_AXE.get(), translations.getProperty("item.cobalt_axe.name"));
        builder.add(NtmItems.COBALT_SHOVEL.get(), translations.getProperty("item.cobalt_shovel.name"));
        builder.add(NtmItems.COBALT_HOE.get(), translations.getProperty("item.cobalt_hoe.name"));
        builder.add(NtmItems.DECORATED_COBALT_SWORD.get(), translations.getProperty("item.cobalt_decorated_sword.name"));
        builder.add(NtmItems.DECORATED_COBALT_PICKAXE.get(), translations.getProperty("item.cobalt_decorated_pickaxe.name"));
        builder.add(NtmItems.DECORATED_COBALT_AXE.get(), translations.getProperty("item.cobalt_decorated_axe.name"));
        builder.add(NtmItems.DECORATED_COBALT_SHOVEL.get(), translations.getProperty("item.cobalt_decorated_shovel.name"));
        builder.add(NtmItems.DECORATED_COBALT_HOE.get(), translations.getProperty("item.cobalt_decorated_hoe.name"));
        builder.add(NtmItems.STARMETAL_SWORD.get(), translations.getProperty("item.starmetal_sword.name"));
        builder.add(NtmItems.STARMETAL_PICKAXE.get(), translations.getProperty("item.starmetal_pickaxe.name"));
        builder.add(NtmItems.STARMETAL_AXE.get(), translations.getProperty("item.starmetal_axe.name"));
        builder.add(NtmItems.STARMETAL_SHOVEL.get(), translations.getProperty("item.starmetal_shovel.name"));
        builder.add(NtmItems.STARMETAL_HOE.get(), translations.getProperty("item.starmetal_hoe.name"));
        builder.add(NtmItems.DESH_SWORD.get(), translations.getProperty("item.desh_sword.name"));
        builder.add(NtmItems.DESH_PICKAXE.get(), translations.getProperty("item.desh_pickaxe.name"));
        builder.add(NtmItems.DESH_AXE.get(), translations.getProperty("item.desh_axe.name"));
        builder.add(NtmItems.DESH_SHOVEL.get(), translations.getProperty("item.desh_shovel.name"));
        builder.add(NtmItems.DESH_HOE.get(), translations.getProperty("item.desh_hoe.name"));
        builder.add(NtmItems.SCHRABIDIUM_SWORD.get(), translations.getProperty("item.schrabidium_sword.name"));
        builder.add(NtmItems.SCHRABIDIUM_PICKAXE.get(), translations.getProperty("item.schrabidium_pickaxe.name"));
        builder.add(NtmItems.SCHRABIDIUM_AXE.get(), translations.getProperty("item.schrabidium_axe.name"));
        builder.add(NtmItems.SCHRABIDIUM_SHOVEL.get(), translations.getProperty("item.schrabidium_shovel.name"));
        builder.add(NtmItems.SCHRABIDIUM_HOE.get(), translations.getProperty("item.schrabidium_hoe.name"));
        builder.add(NtmItems.BISMUTH_PICKAXE.get(), translations.getProperty("item.bismuth_pickaxe.name"));
        builder.add(NtmItems.BISMUTH_AXE.get(), translations.getProperty("item.bismuth_axe.name"));
        builder.add(NtmItems.MOLTEN_PICKAXE.get(), translations.getProperty("item.volcanic_pickaxe.name"));
        builder.add(NtmItems.MOLTEN_AXE.get(), translations.getProperty("item.volcanic_axe.name"));
        builder.add(NtmItems.CHLOROPHYTE_PICKAXE.get(), translations.getProperty("item.chlorophyte_pickaxe.name"));
        builder.add(NtmItems.CHLOROPHYTE_AXE.get(), translations.getProperty("item.chlorophyte_axe.name"));
        builder.add(NtmItems.MESE_PICKAXE.get(), translations.getProperty("item.mese_pickaxe.name"));
        builder.add(NtmItems.MESE_AXE.get(), translations.getProperty("item.mese_axe.name"));

        builder.add(NtmItems.ACTINIUM_227_INGOT.get(), translations.getProperty("item.ingot_actinium.name"));
        builder.add(NtmItems.ACTINIUM_227_BILLET.get(), translations.getProperty("item.billet_actinium.name"));
        builder.add(NtmItems.ACTINIUM_227_POWDER.get(), translations.getProperty("item.powder_actinium.name"));
        builder.add(NtmItems.TINY_PILE_OF_ACTINIUM_227_POWDER.get(), translations.getProperty("item.powder_actinium_tiny.name"));
        builder.add(NtmItems.ACTINIUM_227_NUGGET.get(), translations.getProperty("item.nugget_actinium.name"));
        builder.add(NtmItems.ACTINIUM_227_FRAGMENT.get(), translations.getProperty("item.fragment_actinium.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_INGOT.get(), translations.getProperty("item.ingot_advanced_alloy.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_POWDER.get(), translations.getProperty("item.powder_advanced_alloy.name"));
        builder.add(NtmItems.ADVANCED_ALLOY_PLATE.get(), translations.getProperty("item.plate_advanced_alloy.name"));
        builder.add(NtmItems.CAST_ADVANCED_ALLOY_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.advancedalloy"));
        builder.add(NtmItems.ADVANCED_ALLOY_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.advancedalloy"));
        builder.add(NtmItems.DENSE_ADVANCED_ALLOY_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.advancedalloy"));
        builder.add(NtmItems.ALEXANDRITE.get(), translations.getProperty("item.gem_alexandrite.name"));
        builder.add(NtmItems.ALUMINIUM_INGOT.get(), translations.getProperty("item.ingot_aluminium.name"));
        builder.add(NtmItems.ALUMINIUM_POWDER.get(), translations.getProperty("item.powder_aluminium.name"));
        builder.add(NtmItems.ALUMINIUM_PLATE.get(), translations.getProperty("item.plate_aluminium.name"));
        builder.add(NtmItems.CAST_ALUMINIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.aluminum"));
        builder.add(NtmItems.WELDED_ALUMINIUM_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.aluminum"));
        builder.add(NtmItems.ALUMINIUM_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.aluminum"));
        builder.add(NtmItems.ALUMINIUM_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.aluminum"));
        builder.add(NtmItems.ALUMINIUM_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.aluminum"));
        builder.add(NtmItems.ALUMINIUM_CRYSTALS.get(), translations.getProperty("item.crystal_aluminium.name"));
        builder.add(NtmItems.AMERICIUM_241_INGOT.get(), translations.getProperty("item.ingot_am241.name"));
        builder.add(NtmItems.AMERICIUM_241_BILLET.get(), translations.getProperty("item.billet_am241.name"));
        builder.add(NtmItems.AMERICIUM_241_NUGGET.get(), translations.getProperty("item.nugget_am241.name"));
        builder.add(NtmItems.AMERICIUM_242_INGOT.get(), translations.getProperty("item.ingot_am242.name"));
        builder.add(NtmItems.AMERICIUM_242_BILLET.get(), translations.getProperty("item.billet_am242.name"));
        builder.add(NtmItems.AMERICIUM_242_NUGGET.get(), translations.getProperty("item.nugget_am242.name"));
        builder.add(NtmItems.AMERICIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_americium_fuel.name"));
        builder.add(NtmItems.AMERICIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_americium_fuel.name"));
        builder.add(NtmItems.AMERICIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_americium_fuel.name"));
        builder.add(NtmItems.REACTOR_GRADE_AMERICIUM_INGOT.get(), translations.getProperty("item.ingot_am_mix.name"));
        builder.add(NtmItems.REACTOR_GRADE_AMERICIUM_BILLET.get(), translations.getProperty("item.billet_am_mix.name"));
        builder.add(NtmItems.REACTOR_GRADE_AMERICIUM_ZFB_BILLET.get(), translations.getProperty("item.billet_zfb_am_mix.name"));
        builder.add(NtmItems.REACTOR_GRADE_AMERICIUM_NUGGET.get(), translations.getProperty("item.nugget_am_mix.name"));
        builder.add(NtmItems.ARSENIC_INGOT.get(), translations.getProperty("item.ingot_arsenic.name"));
        builder.add(NtmItems.ARSENIC_NUGGET.get(), translations.getProperty("item.nugget_arsenic.name"));
        builder.add(NtmItems.ARSENIC_BRONZE_INGOT.get(), translations.getProperty("item.ingot_arsenic_bronze.name"));
        builder.add(NtmItems.CAST_ARSENIC_BRONZE_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.arsenicbronze"));
        builder.add(NtmItems.ASBESTOS_SHEET.get(), translations.getProperty("item.ingot_asbestos.name"));
        builder.add(tooltip(NtmItems.ASBESTOS_SHEET), translations.getProperty("item.ingot_asbestos.desc"));
        builder.add(NtmItems.ASBESTOS_POWDER.get(), translations.getProperty("item.powder_asbestos.name"));
        builder.add(tooltip(NtmItems.ASBESTOS_POWDER), translations.getProperty("item.powder_asbestos.desc"));
        builder.add(NtmItems.ASTATINE_POWDER.get(), translations.getProperty("item.powder_astatine.name"));
        builder.add(NtmItems.ASTATINE_209_POWDER.get(), translations.getProperty("item.powder_at209.name"));
        builder.add(NtmItems.ASH.get(), translations.getProperty("item.powder_ash.misc.name"));
        builder.add(NtmItems.WOOD_ASH.get(), translations.getProperty("item.powder_ash.wood.name"));
        builder.add(NtmItems.COAL_ASH.get(), translations.getProperty("item.powder_ash.coal.name"));
        builder.add(NtmItems.FLY_ASH.get(), translations.getProperty("item.powder_ash.fly.name"));
        builder.add(NtmItems.FINE_SOOT.get(), translations.getProperty("item.powder_ash.soot.name"));
        builder.add(NtmItems.AUSTRALIUM_INGOT.get(), translations.getProperty("item.ingot_australium.name"));
        builder.add(NtmItems.AUSTRALIUM_BILLET.get(), translations.getProperty("item.billet_australium.name"));
        builder.add(NtmItems.AUSTRALIUM_NUGGET.get(), translations.getProperty("item.nugget_australium.name"));
        builder.add(NtmItems.GREATER_AUSTRALIUM_BILLET.get(), translations.getProperty("item.billet_australium_greater.name"));
        builder.add(NtmItems.GREATER_AUSTRALIUM_NUGGET.get(), translations.getProperty("item.nugget_australium_greater.name"));
        builder.add(NtmItems.LESSER_AUSTRALIUM_BILLET.get(), translations.getProperty("item.billet_australium_lesser.name"));
        builder.add(NtmItems.LESSER_AUSTRALIUM_NUGGET.get(), translations.getProperty("item.nugget_australium_lesser.name"));
        builder.add(NtmItems.AUSTRALIUM_POWDER.get(), translations.getProperty("item.powder_australium.name"));
        builder.add(NtmItems.BAKELITE_BAR.get(), translations.getProperty("item.ingot_bakelite.name"));
        builder.add(NtmItems.BAKELITE_POWDER.get(), translations.getProperty("item.powder_bakelite.name"));
        builder.add(NtmItems.BALEFIRE_EGG.get(), translations.getProperty("item.egg_balefire.name"));
        builder.add(tooltip(NtmItems.BALEFIRE_EGG), translations.getProperty("item.egg_balefire.desc"));
        builder.add(NtmItems.BALEFIRE_SHARD.get(), translations.getProperty("item.egg_balefire_shard.name"));
        builder.add(NtmItems.THERMONUCLEAR_ASHES.get(), translations.getProperty("item.powder_balefire.name"));
        builder.add(NtmItems.BERYLLIUM_INGOT.get(), translations.getProperty("item.ingot_beryllium.name"));
        builder.add(NtmItems.BERYLLIUM_BILLET.get(), translations.getProperty("item.billet_beryllium.name"));
        builder.add(NtmItems.BERYLLIUM_NUGGET.get(), translations.getProperty("item.nugget_beryllium.name"));
        builder.add(NtmItems.BERYLLIUM_POWDER.get(), translations.getProperty("item.powder_beryllium.name"));
        builder.add(NtmItems.BERYLLIUM_CRYSTALS.get(), translations.getProperty("item.crystal_beryllium.name"));
        builder.add(NtmItems.BISMUTH_INGOT.get(), translations.getProperty("item.ingot_bismuth.name"));
        builder.add(NtmItems.BISMUTH_BILLET.get(), translations.getProperty("item.billet_bismuth.name"));
        builder.add(NtmItems.BISMUTH_ZFB_BILLET.get(), translations.getProperty("item.billet_zfb_bismuth.name"));
        builder.add(NtmItems.BISMUTH_POWDER.get(), translations.getProperty("item.powder_bismuth.name"));
        builder.add(NtmItems.BISMUTH_NUGGET.get(), translations.getProperty("item.nugget_bismuth.name"));
        builder.add(NtmItems.BISMUTH_BRONZE_INGOT.get(), translations.getProperty("item.ingot_bismuth_bronze.name"));
        builder.add(NtmItems.CAST_BISMUTH_BRONZE_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.bismuthbronze"));
        builder.add(NtmItems.BORAX.get(), translations.getProperty("item.powder_borax.name"));
        builder.add(NtmItems.BORON_INGOT.get(), translations.getProperty("item.ingot_boron.name"));
        builder.add(NtmItems.BORON_POWDER.get(), translations.getProperty("item.powder_boron.name"));
        builder.add(NtmItems.TINY_PILE_OF_BORON_POWDER.get(), translations.getProperty("item.powder_boron_tiny.name"));
        builder.add(NtmItems.BORON_FRAGMENT.get(), translations.getProperty("item.fragment_boron.name"));
        builder.add(NtmItems.BROMINE_POWDER.get(), translations.getProperty("item.powder_bromine.name"));
        builder.add(NtmItems.BSCCO_INGOT.get(), translations.getProperty("item.ingot_bscco.name"));
        builder.add(NtmItems.DENSE_BSCCO_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.bscco"));
        builder.add(NtmItems.CADMIUM_INGOT.get(), translations.getProperty("item.ingot_cadmium.name"));
        builder.add(NtmItems.CADMIUM_POWDER.get(), translations.getProperty("item.powder_cadmium.name"));
        builder.add(NtmItems.CAESIUM_POWDER.get(), translations.getProperty("item.powder_caesium.name"));
        builder.add(NtmItems.CAESIUM_137_POWDER.get(), translations.getProperty("item.powder_cs137.name"));
        builder.add(NtmItems.TINY_PILE_OF_CAESIUM_137_POWDER.get(), translations.getProperty("item.powder_cs137_tiny.name"));
        builder.add(NtmItems.CALCIUM_INGOT.get(), translations.getProperty("item.ingot_calcium.name"));
        builder.add(NtmItems.CALCIUM_POWDER.get(), translations.getProperty("item.powder_calcium.name"));
        builder.add(NtmItems.CADMIUM_STEEL_INGOT.get(), translations.getProperty("item.ingot_cdalloy.name"));
        builder.add(NtmItems.CAST_CADMIUM_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.cdalloy"));
        builder.add(NtmItems.WELDED_CADMIUM_STEEL_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.cdalloy"));
        builder.add(NtmItems.CEMENT.get(), translations.getProperty("item.powder_cement.name"));
        builder.add(NtmItems.CERIUM_POWDER.get(), translations.getProperty("item.powder_cerium.name"));
        builder.add(NtmItems.TINY_PILE_OF_CERIUM_POWDER.get(), translations.getProperty("item.powder_cerium_tiny.name"));
        builder.add(NtmItems.CERIUM_FRAGMENT.get(), translations.getProperty("item.fragment_cerium.name"));
        builder.add(NtmItems.CHLOROCALCITE.get(), translations.getProperty("item.powder_chlorocalcite.name"));
        builder.add(NtmItems.CHLOROPHYTE_POWDER.get(), translations.getProperty("item.powder_chlorophyte.name"));
        builder.add(NtmItems.CINNABAR.get(), translations.getProperty("item.cinnebar.name"));
        builder.add(NtmItems.CINNABAR_CRYSTALS.get(), translations.getProperty("item.crystal_cinnebar.name"));
        builder.add(NtmItems.CMB_STEEL_INGOT.get(), translations.getProperty("item.ingot_combine_steel.name"));
        builder.add(tooltip(NtmItems.CMB_STEEL_INGOT), translations.getProperty("item.ingot_combine_steel.desc"));
        builder.add(NtmItems.CMB_STEEL_POWDER.get(), translations.getProperty("item.powder_combine_steel.name"));
        builder.add(NtmItems.CAST_CMB_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.cmbsteel"));
        builder.add(NtmItems.WELDED_CMB_STEEL_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.cmbsteel"));
        builder.add(NtmItems.CMB_STEEL_PLATE.get(), translations.getProperty("item.plate_combine_steel.name"));
        builder.add(NtmItems.COAL_POWDER.get(), translations.getProperty("item.powder_coal.name"));
        builder.add(NtmItems.TINY_PILE_OF_COAL_POWDER.get(), translations.getProperty("item.powder_coal_tiny.name"));
        builder.add(NtmItems.CARBON_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.carbon"));
        builder.add(NtmItems.COAL_BRIQUETTE.get(), translations.getProperty("item.briquette.coal.name"));
        builder.add(NtmItems.COAL_COKE.get(), translations.getProperty("item.coke.coal.name"));
        builder.add(NtmItems.COAL_CRYSTALS.get(), translations.getProperty("item.crystal_coal.name"));
        builder.add(NtmItems.COBALT_INGOT.get(), translations.getProperty("item.ingot_cobalt.name"));
        builder.add(NtmItems.COBALT_BILLET.get(), translations.getProperty("item.billet_cobalt.name"));
        builder.add(NtmItems.COBALT_POWDER.get(), translations.getProperty("item.powder_cobalt.name"));
        builder.add(NtmItems.COBALT_NUGGET.get(), translations.getProperty("item.nugget_cobalt.name"));
        builder.add(NtmItems.TINY_PILE_OF_COBALT_POWDER.get(), translations.getProperty("item.powder_cobalt_tiny.name"));
        builder.add(NtmItems.COBALT_60_INGOT.get(), translations.getProperty("item.ingot_co60.name"));
        builder.add(NtmItems.COBALT_60_BILLET.get(), translations.getProperty("item.billet_co60.name"));
        builder.add(NtmItems.COBALT_60_POWDER.get(), translations.getProperty("item.powder_co60.name"));
        builder.add(NtmItems.COBALT_60_NUGGET.get(), translations.getProperty("item.nugget_co60.name"));
        builder.add(NtmItems.COBALT_FRAGMENT.get(), translations.getProperty("item.fragment_cobalt.name"));
        builder.add(NtmItems.COBALT_CRYSTALS.get(), translations.getProperty("item.crystal_cobalt.name"));
        builder.add(NtmItems.COLTAN.get(), translations.getProperty("item.fragment_coltan.name"));
        builder.add(NtmItems.CRUSHED_COLTAN.get(), translations.getProperty("item.powder_coltan_ore.name"));
        builder.add(NtmItems.COPPER_POWDER.get(), translations.getProperty("item.powder_copper.name"));
        builder.add(NtmItems.COPPER_PLATE.get(), translations.getProperty("item.plate_copper.name"));
        builder.add(NtmItems.CAST_COPPER_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.copper"));
        builder.add(NtmItems.WELDED_COPPER_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.copper"));
        builder.add(NtmItems.COPPER_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.copper"));
        builder.add(NtmItems.COPPER_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.copper"));
        builder.add(NtmItems.COPPER_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.copper"));
        builder.add(NtmItems.DENSE_COPPER_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.copper"));
        builder.add(NtmItems.COPPER_CRYSTALS.get(), translations.getProperty("item.crystal_copper.name"));
        builder.add(NtmItems.CRYO_POWDER.get(), translations.getProperty("item.powder_ice.name"));
        builder.add(NtmItems.CRYOLITE_CHUNK.get(), translations.getProperty("item.chunk_ore.cryolite.name"));
        builder.add(NtmItems.CRYSTAL_HORN.get(), translations.getProperty("item.crystal_horn.name"));
        builder.add(tooltip(NtmItems.CRYSTAL_HORN), translations.getProperty("item.crystal_horn.desc"));
        builder.add(tooltip11(NtmItems.CRYSTAL_HORN), translations.getProperty("item.crystal_horn.desc.P11"));
        builder.add(NtmItems.CRYSTAL_CHARRED.get(), translations.getProperty("item.crystal_charred.name"));
        builder.add(tooltip(NtmItems.CRYSTAL_CHARRED), translations.getProperty("item.crystal_charred.desc"));
        builder.add(tooltip11(NtmItems.CRYSTAL_CHARRED), translations.getProperty("item.crystal_charred.desc.P11"));
        builder.add(NtmItems.DESH_INGOT.get(), translations.getProperty("item.ingot_desh.name"));
        builder.add(NtmItems.DESH_BLEND.get(), translations.getProperty("item.powder_desh_mix.name"));
        builder.add(NtmItems.DESHREADY_BLEND.get(), translations.getProperty("item.powder_desh_ready.name"));
        builder.add(NtmItems.DESH_POWDER.get(), translations.getProperty("item.powder_desh.name"));
        builder.add(NtmItems.DESH_NUGGET.get(), translations.getProperty("item.nugget_desh.name"));
        builder.add(NtmItems.CAST_DESH_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.desh"));
        builder.add(NtmItems.DIAMOND_POWDER.get(), translations.getProperty("item.powder_diamond.name"));
        builder.add(NtmItems.DIAMOND_CRYSTALS.get(), translations.getProperty("item.crystal_diamond.name"));
        builder.add(NtmItems.DINEUTRONIUM_INGOT.get(), translations.getProperty("item.ingot_dineutronium.name"));
        builder.add(NtmItems.DINEUTRONIUM_POWDER.get(), translations.getProperty("item.powder_dineutronium.name"));
        builder.add(NtmItems.DINEUTRONIUM_NUGGET.get(), translations.getProperty("item.nugget_dineutronium.name"));
        builder.add(NtmItems.DENSE_DINEUTRONIUM_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.dineutronium"));
        builder.add(NtmItems.ELECTRONIUM_INGOT.get(), translations.getProperty("item.ingot_electronium.name"));
        builder.add(NtmItems.EMERALD_POWDER.get(), translations.getProperty("item.powder_emerald.name"));
        builder.add(NtmItems.ENERGY_POWDER.get(), translations.getProperty("item.powder_power.name"));
        builder.add(NtmItems.EUPHEMIUM_INGOT.get(), translations.getProperty("item.ingot_euphemium.name"));
        builder.add(tooltip(NtmItems.EUPHEMIUM_INGOT), translations.getProperty("item.ingot_euphemium.desc"));
        builder.add(NtmItems.EUPHEMIUM_POWDER.get(), translations.getProperty("item.powder_euphemium.name"));
        String[] euphemiumPowderTooltips = translations.getProperty("item.powder_euphemium.desc", "").split("\\$");
        if (euphemiumPowderTooltips.length == 2) {
            builder.add(tooltip(NtmItems.EUPHEMIUM_POWDER, 1), euphemiumPowderTooltips[0]);
            builder.add(tooltip(NtmItems.EUPHEMIUM_POWDER, 2), euphemiumPowderTooltips[1]);
        }
        builder.add(NtmItems.EUPHEMIUM_NUGGET.get(), translations.getProperty("item.nugget_euphemium.name"));
        String[] euphemiumNuggetTooltips = translations.getProperty("item.nugget_euphemium.desc", "").split("\\$");
        if (euphemiumNuggetTooltips.length == 3) {
            builder.add(tooltip(NtmItems.EUPHEMIUM_NUGGET, 1), euphemiumNuggetTooltips[0]);
            builder.add(tooltip(NtmItems.EUPHEMIUM_NUGGET, 2), euphemiumNuggetTooltips[1]);
            builder.add(tooltip(NtmItems.EUPHEMIUM_NUGGET, 3), euphemiumNuggetTooltips[2]);
        }
        builder.add(NtmItems.FERRIC_SCHRABIDATE_INGOT.get(), translations.getProperty("item.ingot_schrabidate.name"));
        builder.add(NtmItems.FERRIC_SCHRABIDATE_POWDER.get(), translations.getProperty("item.powder_schrabidate.name"));
        builder.add(NtmItems.CAST_FERRIC_SCHRABIDATE_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.schrabidate"));
        builder.add(NtmItems.DENSE_FERRIC_SCHRABIDATE_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.schrabidate"));
        builder.add(NtmItems.FERROURANIUM_INGOT.get(), translations.getProperty("item.ingot_ferrouranium.name"));
        builder.add(NtmItems.CAST_FERROURANIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.ferrouranium"));
        builder.add(NtmItems.FLASH_GOLD_BILLET.get(), translations.getProperty("item.billet_balefire_gold.name"));
        builder.add(NtmItems.FLASH_LEAD_BILLET.get(), translations.getProperty("item.billet_flashlead.name"));
        String[] flashLeadTooltips = translations.getProperty("item.billet_flashlead.desc", "").split("\\$");
        if (flashLeadTooltips.length == 5) {
            builder.add(tooltip(NtmItems.FLASH_LEAD_BILLET, 1), flashLeadTooltips[0]);
            builder.add(tooltip(NtmItems.FLASH_LEAD_BILLET, 2), flashLeadTooltips[1]);
            builder.add(tooltip(NtmItems.FLASH_LEAD_BILLET, 3), flashLeadTooltips[2]);
            builder.add(tooltip(NtmItems.FLASH_LEAD_BILLET, 4), flashLeadTooltips[3]);
            builder.add(tooltip(NtmItems.FLASH_LEAD_BILLET, 5), flashLeadTooltips[4]);
        }
        builder.add(NtmItems.FLUORITE.get(), translations.getProperty("item.fluorite.name"));
        builder.add(NtmItems.FLUORITE_CRYSTALS.get(), translations.getProperty("item.crystal_fluorite.name"));
        builder.add(NtmItems.FLUX.get(), translations.getProperty("item.powder_flux.name"));
        builder.add(NtmItems.FULLERENE.get(), translations.getProperty("item.powder_ash.fullerene.name"));
        builder.add(NtmItems.CRYSTALLINE_FULLERITE.get(), translations.getProperty("item.ingot_cft.name"));
        builder.add(NtmItems.GHIORSIUM_336_INGOT.get(), translations.getProperty("item.ingot_gh336.name"));
        builder.add(tooltip(NtmItems.GHIORSIUM_336_INGOT), translations.getProperty("item.ingot_gh336.desc"));
        builder.add(NtmItems.GHIORSIUM_336_BILLET.get(), translations.getProperty("item.billet_gh336.name"));
        builder.add(tooltip(NtmItems.GHIORSIUM_336_BILLET), translations.getProperty("item.billet_gh336.desc"));
        builder.add(NtmItems.GHIORSIUM_336_NUGGET.get(), translations.getProperty("item.nugget_gh336.name"));
        builder.add(tooltip(NtmItems.GHIORSIUM_336_NUGGET), translations.getProperty("item.nugget_gh336.desc"));
        builder.add(NtmItems.GOLD_POWDER.get(), translations.getProperty("item.powder_gold.name"));
        builder.add(NtmItems.GOLD_PLATE.get(), translations.getProperty("item.plate_gold.name"));
        builder.add(NtmItems.CAST_GOLD_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.gold"));
        builder.add(NtmItems.GOLD_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.gold"));
        builder.add(NtmItems.DENSE_GOLD_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.gold"));
        builder.add(NtmItems.GOLD_CRYSTALS.get(), translations.getProperty("item.crystal_gold.name"));
        builder.add(NtmItems.GOLD_198_INGOT.get(), translations.getProperty("item.ingot_au198.name"));
        builder.add(NtmItems.GOLD_198_BILLET.get(), translations.getProperty("item.billet_au198.name"));
        builder.add(NtmItems.GOLD_198_POWDER.get(), translations.getProperty("item.powder_au198.name"));
        builder.add(NtmItems.GOLD_198_NUGGET.get(), translations.getProperty("item.nugget_au198.name"));
        builder.add(NtmItems.GRAPHITE_INGOT.get(), translations.getProperty("item.ingot_graphite.name"));
        builder.add(NtmItems.GUNMETAL_INGOT.get(), translations.getProperty("item.ingot_gunmetal.name"));
        builder.add(NtmItems.GUNMETAL_PLATE.get(), translations.getProperty("item.plate_gunmetal.name"));
        builder.add(NtmItems.HARD_PLASTIC_BAR.get(), translations.getProperty("item.ingot_pc.name"));
        builder.add(NtmItems.HIGH_SPEED_STEEL_INGOT.get(), translations.getProperty("item.ingot_dura_steel.name"));
        builder.add(NtmItems.HIGH_SPEED_STEEL_POWDER.get(), translations.getProperty("item.powder_dura_steel.name"));
        builder.add(NtmItems.CAST_HIGH_SPEED_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.durasteel"));
        builder.add(NtmItems.HIGH_SPEED_STEEL_PLATE.get(), translations.getProperty("item.plate_dura_steel.name"));
        builder.add(NtmItems.HIGH_SPEED_STEEL_BOLT.get(), getFormated(translations, "item.boltntm.name", "hbmmat.durasteel"));
        builder.add(NtmItems.HIGH_SPEED_STEEL_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.durasteel"));
        builder.add(NtmItems.IODINE_POWDER.get(), translations.getProperty("item.powder_iodine.name"));
        builder.add(NtmItems.IODINE_131_POWDER.get(), translations.getProperty("item.powder_i131.name"));
        builder.add(NtmItems.TINY_PILE_OF_IODINE_131_POWDER.get(), translations.getProperty("item.powder_i131_tiny.name"));
        builder.add(NtmItems.IRON_POWDER.get(), translations.getProperty("item.powder_iron.name"));
        builder.add(NtmItems.IRON_PLATE.get(), translations.getProperty("item.plate_iron.name"));
        builder.add(NtmItems.CAST_IRON_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.iron"));
        builder.add(NtmItems.WELDED_IRON_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.iron"));
        builder.add(NtmItems.IRON_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.iron"));
        builder.add(NtmItems.IRON_CRYSTALS.get(), translations.getProperty("item.crystal_iron.name"));
        builder.add(NtmItems.INDUSTRIAL_FERTILIZER.get(), translations.getProperty("item.powder_fertilizer.name"));
        builder.add(NtmItems.INFERNAL_COAL.get(), translations.getProperty("item.coal_infernal.name"));
        builder.add(NtmItems.SEMI_STABLE_LANTHANUM_INGOT.get(), translations.getProperty("item.ingot_lanthanium.name"));
        builder.add(NtmItems.LANTHANUM_POWDER.get(), translations.getProperty("item.powder_lanthanium.name"));
        builder.add(NtmItems.TINY_PILE_OF_LANTHANUM_POWDER.get(), translations.getProperty("item.powder_lanthanium_tiny.name"));
        builder.add(NtmItems.LANTHANUM_FRAGMENT.get(), translations.getProperty("item.fragment_lanthanium.name"));
        builder.add(NtmItems.LAPIS_POWDER.get(), translations.getProperty("item.powder_lapis.name"));
        builder.add(NtmItems.LAPIS_CRYSTALS.get(), translations.getProperty("item.crystal_lapis.name"));
        builder.add(NtmItems.LATEX.get(), translations.getProperty("item.ball_resin.name"));
        builder.add(NtmItems.LATEX_BAR.get(), translations.getProperty("item.ingot_biorubber.name"));
        builder.add(NtmItems.LEAD_INGOT.get(), translations.getProperty("item.ingot_lead.name"));
        builder.add(NtmItems.LEAD_NUGGET.get(), translations.getProperty("item.nugget_lead.name"));
        builder.add(NtmItems.LEAD_209_INGOT.get(), translations.getProperty("item.ingot_pb209.name"));
        builder.add(NtmItems.LEAD_209_BILLET.get(), translations.getProperty("item.billet_pb209.name"));
        builder.add(NtmItems.LEAD_209_NUGGET.get(), translations.getProperty("item.nugget_pb209.name"));
        builder.add(NtmItems.LEAD_POWDER.get(), translations.getProperty("item.powder_lead.name"));
        builder.add(NtmItems.LEAD_PLATE.get(), translations.getProperty("item.plate_lead.name"));
        builder.add(NtmItems.CAST_LEAD_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.lead"));
        builder.add(NtmItems.LEAD_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.lead"));
        builder.add(NtmItems.LEAD_BOLT.get(), getFormated(translations, "item.boltntm.name", "hbmmat.lead"));
        builder.add(NtmItems.LEAD_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.lead"));
        builder.add(NtmItems.LEAD_CRYSTALS.get(), translations.getProperty("item.crystal_lead.name"));
        builder.add(NtmItems.LIGNITE.get(), translations.getProperty("item.lignite.name"));
        builder.add(NtmItems.LIGNITE_POWDER.get(), translations.getProperty("item.powder_lignite.name"));
        builder.add(NtmItems.LIGNITE_COKE.get(), translations.getProperty("item.coke.lignite.name"));
        builder.add(NtmItems.LIGNITE_BRIQUETTE.get(), translations.getProperty("item.briquette.lignite.name"));
        builder.add(NtmItems.LIMESTONE_POWDER.get(), translations.getProperty("item.powder_limestone.name"));
        builder.add(NtmItems.LITHIUM_CUBE.get(), translations.getProperty("item.lithium.name"));
        builder.add(NtmItems.LITHIUM_POWDER.get(), translations.getProperty("item.powder_lithium.name"));
        builder.add(NtmItems.TINY_PILE_OF_LITHIUM_POWDER.get(), translations.getProperty("item.powder_lithium_tiny.name"));
        builder.add(NtmItems.LITHIUM_CRYSTALS.get(), translations.getProperty("item.crystal_lithium.name"));
        builder.add(NtmItems.MAGNETIZED_TUNGSTEN_INGOT.get(), translations.getProperty("item.ingot_magnetized_tungsten.name"));
        builder.add(NtmItems.MAGNETIZED_TUNGSTEN_POWDER.get(), translations.getProperty("item.powder_magnetized_tungsten.name"));
        builder.add(NtmItems.MAGNETIZED_TUNGSTEN_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.magnetizedtungsten"));
        builder.add(NtmItems.DENSE_MAGNETIZED_TUNGSTEN_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.magnetizedtungsten"));
        builder.add(NtmItems.METEORITE_INGOT.get(), translations.getProperty("item.ingot_meteorite.name"));
        builder.add(NtmItems.METEORITE_POWDER.get(), translations.getProperty("item.powder_meteorite.name"));
        builder.add(NtmItems.TINY_PILE_OF_METEORITE_POWDER.get(), translations.getProperty("item.powder_meteorite_tiny.name"));
        builder.add(NtmItems.METEORITE_FRAGMENT.get(), translations.getProperty("item.fragment_meteorite.name"));
        builder.add(NtmItems.MOLYSITE.get(), translations.getProperty("item.powder_molysite.name"));
        builder.add(NtmItems.MOX_FUEL_INGOT.get(), translations.getProperty("item.ingot_mox_fuel.name"));
        builder.add(NtmItems.MOX_FUEL_BILLET.get(), translations.getProperty("item.billet_mox_fuel.name"));
        builder.add(tooltip(NtmItems.MOX_FUEL_BILLET), translations.getProperty("item.billet_mox_fuel.desc"));
        builder.add(NtmItems.MOX_FUEL_NUGGET.get(), translations.getProperty("item.nugget_mox_fuel.name"));
        builder.add(tooltip(NtmItems.MOX_FUEL_NUGGET), translations.getProperty("item.nugget_mox_fuel.desc"));
        builder.add(NtmItems.NEODYMIUM_POWDER.get(), translations.getProperty("item.powder_neodymium.name"));
        builder.add(NtmItems.TINY_PILE_OF_NEODYMIUM_POWDER.get(), translations.getProperty("item.powder_neodymium_tiny.name"));
        builder.add(NtmItems.DENSE_NEODYMIUM_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.neodymium"));
        builder.add(NtmItems.NEODYMIUM_FRAGMENT.get(), translations.getProperty("item.fragment_neodymium.name"));
        builder.add(NtmItems.NEPTUNIUM_INGOT.get(), translations.getProperty("item.ingot_neptunium.name"));
        builder.add(tooltip(NtmItems.NEPTUNIUM_INGOT), translations.getProperty("item.ingot_neptunium.desc"));
        builder.add(tooltip11(NtmItems.NEPTUNIUM_INGOT), translations.getProperty("item.ingot_neptunium.desc.P11"));
        builder.add(NtmItems.NEPTUNIUM_POWDER.get(), translations.getProperty("item.powder_neptunium.name"));
        builder.add(NtmItems.NEPTUNIUM_BILLET.get(), translations.getProperty("item.billet_neptunium.name"));
        builder.add(NtmItems.NEPTUNIUM_NUGGET.get(), translations.getProperty("item.nugget_neptunium.name"));
        builder.add(NtmItems.NEPTUNIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_neptunium_fuel.name"));
        builder.add(NtmItems.NEPTUNIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_neptunium_fuel.name"));
        builder.add(NtmItems.NEPTUNIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_neptunium_fuel.name"));
        builder.add(NtmItems.NIOBIUM_INGOT.get(), translations.getProperty("item.ingot_niobium.name"));
        builder.add(NtmItems.NIOBIUM_POWDER.get(), translations.getProperty("item.powder_niobium.name"));
        builder.add(NtmItems.TINY_PILE_OF_NIOBIUM_POWDER.get(), translations.getProperty("item.powder_niobium_tiny.name"));
        builder.add(NtmItems.NIOBIUM_NUGGET.get(), translations.getProperty("item.nugget_niobium.name"));
        builder.add(NtmItems.DENSE_NIOBIUM_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.niobium"));
        builder.add(NtmItems.NIOBIUM_FRAGMENT.get(), translations.getProperty("item.fragment_niobium.name"));
        builder.add(NtmItems.NITANIUM_BLEND.get(), translations.getProperty("item.powder_nitan_mix.name"));
        builder.add(NtmItems.NITER.get(), translations.getProperty("item.niter.name"));
        builder.add(NtmItems.NITER_CRYSTALS.get(), translations.getProperty("item.crystal_niter.name"));
        builder.add(NtmItems.NITRA.get(), translations.getProperty("item.nitra.name"));
        builder.add(NtmItems.SMALL_PILE_OF_NITRA.get(), translations.getProperty("item.nitra_small.name"));
        builder.add(NtmItems.OSMIRIDIUM_INGOT.get(), translations.getProperty("item.ingot_osmiridium.name"));
        builder.add(NtmItems.OSMIRIDIUM_NUGGET.get(), translations.getProperty("item.nugget_osmiridium.name"));
        builder.add(NtmItems.IMPURE_OSMIRIDIUM_POWDER.get(), translations.getProperty("item.powder_impure_osmiridium.name"));
        builder.add(NtmItems.CAST_OSMIRIDIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.osmiridium"));
        builder.add(NtmItems.WELDED_OSMIRIDIUM_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.osmiridium"));
        builder.add(NtmItems.OSMIRIDIUM_CRYSTALS.get(), translations.getProperty("item.crystal_osmiridium.name"));
        builder.add(NtmItems.PALEOGENITE_POWDER.get(), translations.getProperty("item.powder_paleogenite.name"));
        builder.add(NtmItems.TINY_PILE_OF_PALEOGENITE_POWDER.get(), translations.getProperty("item.powder_paleogenite_tiny.name"));
        builder.add(NtmItems.RED_PHOSPHORUS.get(), translations.getProperty("item.powder_fire.name"));
        String[] redPhosphorousTooltips = translations.getProperty("item.powder_fire.desc", "").split("\\$");
        if (redPhosphorousTooltips.length == 2) {
            builder.add(tooltip(NtmItems.RED_PHOSPHORUS, 1), redPhosphorousTooltips[0]);
            builder.add(tooltip(NtmItems.RED_PHOSPHORUS, 2), redPhosphorousTooltips[1]);
        }
        builder.add(NtmItems.WHITE_PHOSPHORUS_BAR.get(), translations.getProperty("item.ingot_phosphorus.name"));
        builder.add(NtmItems.PHOSPHORUS_CRYSTALS.get(), translations.getProperty("item.crystal_phosphorus.name"));
        builder.add(NtmItems.PETROLEUM_COKE.get(), translations.getProperty("item.coke.petroleum.name"));
        builder.add(NtmItems.PLUTONIUM_INGOT.get(), translations.getProperty("item.ingot_plutonium.name"));
        builder.add(NtmItems.PLUTONIUM_BILLET.get(), translations.getProperty("item.billet_plutonium.name"));
        builder.add(NtmItems.PLUTONIUM_NUGGET.get(), translations.getProperty("item.nugget_plutonium.name"));
        builder.add(NtmItems.PLUTONIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_plutonium_fuel.name"));
        builder.add(NtmItems.PLUTONIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_plutonium_fuel.name"));
        builder.add(NtmItems.PLUTONIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_plutonium_fuel.name"));
        builder.add(NtmItems.REACTOR_GRADE_PLUTONIUM_INGOT.get(), translations.getProperty("item.ingot_pu_mix.name"));
        builder.add(NtmItems.REACTOR_GRADE_PLUTONIUM_BILLET.get(), translations.getProperty("item.billet_pu_mix.name"));
        builder.add(NtmItems.REACTOR_GRADE_PLUTONIUM_NUGGET.get(), translations.getProperty("item.nugget_pu_mix.name"));
        builder.add(NtmItems.PLUTONIUM_238_INGOT.get(), translations.getProperty("item.ingot_pu238.name"));
        builder.add(NtmItems.PLUTONIUM_238_BILLET.get(), translations.getProperty("item.billet_pu238.name"));
        builder.add(NtmItems.PLUTONIUM_238_BE_BILLET.get(), translations.getProperty("item.billet_pu238be.name"));
        builder.add(NtmItems.PLUTONIUM_238_NUGGET.get(), translations.getProperty("item.nugget_pu238.name"));
        builder.add(NtmItems.PLUTONIUM_239_INGOT.get(), translations.getProperty("item.ingot_pu239.name"));
        builder.add(NtmItems.PLUTONIUM_239_BILLET.get(), translations.getProperty("item.billet_pu239.name"));
        builder.add(NtmItems.PLUTONIUM_239_NUGGET.get(), translations.getProperty("item.nugget_pu239.name"));
        builder.add(NtmItems.PLUTONIUM_240_INGOT.get(), translations.getProperty("item.ingot_pu240.name"));
        builder.add(NtmItems.PLUTONIUM_240_BILLET.get(), translations.getProperty("item.billet_pu240.name"));
        builder.add(NtmItems.PLUTONIUM_240_NUGGET.get(), translations.getProperty("item.nugget_pu240.name"));
        builder.add(NtmItems.PLUTONIUM_241_INGOT.get(), translations.getProperty("item.ingot_pu241.name"));
        builder.add(NtmItems.PLUTONIUM_241_BILLET.get(), translations.getProperty("item.billet_pu241.name"));
        builder.add(NtmItems.PLUTONIUM_241_ZFB_BILLET.get(), translations.getProperty("item.billet_zfb_pu241.name"));
        builder.add(NtmItems.PLUTONIUM_241_NUGGET.get(), translations.getProperty("item.nugget_pu241.name"));
        builder.add(NtmItems.PLUTONIUM_POWDER.get(), translations.getProperty("item.powder_plutonium.name"));
        builder.add(NtmItems.PLUTONIUM_CRYSTALS.get(), translations.getProperty("item.crystal_plutonium.name"));
        builder.add(NtmItems.POISON_POWDER.get(), translations.getProperty("item.powder_poison.name"));
        String[] poisonPowderTooltips = translations.getProperty("item.powder_poison.desc", "").split("\\$");
        if (poisonPowderTooltips.length == 2) {
            builder.add(tooltip(NtmItems.POISON_POWDER, 1), poisonPowderTooltips[0]);
            builder.add(tooltip(NtmItems.POISON_POWDER, 2), poisonPowderTooltips[1]);
        }
        builder.add(NtmItems.POLONIUM_210_INGOT.get(), translations.getProperty("item.ingot_polonium.name"));
        builder.add(NtmItems.POLONIUM_210_BILLET.get(), translations.getProperty("item.billet_polonium.name"));
        builder.add(NtmItems.POLONIUM_210_BE_BILLET.get(), translations.getProperty("item.billet_po210be.name"));
        builder.add(NtmItems.POLONIUM_210_NUGGET.get(), translations.getProperty("item.nugget_polonium.name"));
        builder.add(NtmItems.POLONIUM_210_POWDER.get(), translations.getProperty("item.powder_polonium.name"));
        builder.add(NtmItems.POLYMER_BAR.get(), translations.getProperty("item.ingot_polymer.name"));
        builder.add(NtmItems.POLYMER_POWDER.get(), translations.getProperty("item.powder_polymer.name"));
        builder.add(NtmItems.PULVERIZED_ENCHANTMENT.get(), translations.getProperty("item.powder_magic.name"));
        builder.add(NtmItems.PVC_BAR.get(), translations.getProperty("item.ingot_pvc.name"));
        builder.add(NtmItems.QUARTZ_POWDER.get(), translations.getProperty("item.powder_quartz.name"));
        builder.add(NtmItems.RADIUM_226_INGOT.get(), translations.getProperty("item.ingot_ra226.name"));
        builder.add(NtmItems.RADIUM_226_BILLET.get(), translations.getProperty("item.billet_ra226.name"));
        builder.add(NtmItems.RADIUM_226_BE_BILLET.get(), translations.getProperty("item.billet_ra226be.name"));
        builder.add(NtmItems.RADIUM_226_POWDER.get(), translations.getProperty("item.powder_ra226.name"));
        builder.add(NtmItems.RADIUM_226_NUGGET.get(), translations.getProperty("item.nugget_ra226.name"));
        builder.add(NtmItems.RARE_EARTH_ORE_CHUNK.get(), translations.getProperty("item.chunk_ore.rare.name"));
        builder.add(NtmItems.RARE_EARTH_CRYSTALS.get(), translations.getProperty("item.crystal_rare.name"));
        builder.add(NtmItems.RED_COPPER_INGOT.get(), translations.getProperty("item.ingot_red_copper.name"));
        builder.add(NtmItems.RED_COPPER_POWDER.get(), translations.getProperty("item.powder_red_copper.name"));
        builder.add(NtmItems.RED_COPPER_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.mingrade"));
        builder.add(NtmItems.REDSTONE_CRYSTALS.get(), translations.getProperty("item.crystal_redstone.name"));
        builder.add(NtmItems.RUBBER_BAR.get(), translations.getProperty("item.ingot_rubber.name"));
        builder.add(NtmItems.RUBBER_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.rubber"));
        builder.add(NtmItems.SATURNITE_INGOT.get(), translations.getProperty("item.ingot_saturnite.name"));
        builder.add(NtmItems.SATURNITE_PLATE.get(), translations.getProperty("item.plate_saturnite.name"));
        builder.add(NtmItems.CAST_SATURNITE_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.saturnite"));
        builder.add(NtmItems.SATURNITE_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.saturnite"));
        builder.add(NtmItems.SAWDUST.get(), translations.getProperty("item.powder_sawdust.name"));
        builder.add(NtmItems.SAWDUST_BRIQUETTE.get(), translations.getProperty("item.briquette.wood.name"));
        builder.add(NtmItems.SCHRABIDIUM_INGOT.get(), translations.getProperty("item.ingot_schrabidium.name"));
        builder.add(NtmItems.SCHRABIDIUM_BILLET.get(), translations.getProperty("item.billet_schrabidium.name"));
        builder.add(NtmItems.SCHRABIDIUM_NUGGET.get(), translations.getProperty("item.nugget_schrabidium.name"));
        builder.add(NtmItems.SCHRABIDIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_schrabidium_fuel.name"));
        builder.add(NtmItems.SCHRABIDIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_schrabidium_fuel.name"));
        builder.add(NtmItems.SCHRABIDIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_schrabidium_fuel.name"));
        builder.add(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_les.name"));
        builder.add(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_les.name"));
        builder.add(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_les.name"));
        builder.add(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_hes.name"));
        builder.add(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_hes.name"));
        builder.add(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_hes.name"));
        builder.add(NtmItems.SCHRABIDIUM_POWDER.get(), translations.getProperty("item.powder_schrabidium.name"));
        builder.add(NtmItems.SCHRABIDIUM_PLATE.get(), translations.getProperty("item.plate_schrabidium.name"));
        builder.add(NtmItems.CAST_SCHRABIDIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.schrabidium"));
        builder.add(NtmItems.SCHRABIDIUM_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.schrabidium"));
        builder.add(NtmItems.DENSE_SCHRABIDIUM_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.schrabidium"));
        builder.add(NtmItems.SCHRABIDIUM_CRYSTALS.get(), translations.getProperty("item.crystal_schrabidium.name"));
        builder.add(NtmItems.SCHRARANIUM_INGOT.get(), translations.getProperty("item.ingot_schraranium.name"));
        builder.add(tooltip(NtmItems.SCHRARANIUM_INGOT), translations.getProperty("item.ingot_schraranium.desc"));
        builder.add(NtmItems.SCHRARANIUM_CRYSTALS.get(), translations.getProperty("item.crystal_schraranium.name"));
        builder.add(NtmItems.SEMTEX_BLEND.get(), translations.getProperty("item.powder_semtex_mix.name"));
        builder.add(NtmItems.SEMTEX_BAR.get(), translations.getProperty("item.ingot_semtex.name"));
        String[] semtexBarTooltips = translations.getProperty("item.ingot_semtex.desc", "").split("\\$");
        if (semtexBarTooltips.length == 3) {
            builder.add(tooltip(NtmItems.SEMTEX_BAR, 1), semtexBarTooltips[0]);
            builder.add(tooltip(NtmItems.SEMTEX_BAR, 2), semtexBarTooltips[1]);
            builder.add(tooltip(NtmItems.SEMTEX_BAR, 3), semtexBarTooltips[2]);
        }
        builder.add(NtmItems.SILICON_BOULE.get(), translations.getProperty("item.ingot_silicon.name"));
        builder.add(NtmItems.SILICON_WAFER.get(), translations.getProperty("item.billet_silicon.name"));
        builder.add(NtmItems.PRINTED_SILICON_WAFER.get(), translations.getProperty("item.circuit.silicon.name"));
        builder.add(NtmItems.SILICON_NUGGET.get(), translations.getProperty("item.nugget_silicon.name"));
        builder.add(NtmItems.SODIUM.get(), translations.getProperty("item.powder_sodium.name"));
        builder.add(NtmItems.SOLINIUM_INGOT.get(), translations.getProperty("item.ingot_solinium.name"));
        builder.add(NtmItems.SOLINIUM_BILLET.get(), translations.getProperty("item.billet_solinium.name"));
        builder.add(NtmItems.SOLINIUM_NUGGET.get(), translations.getProperty("item.nugget_solinium.name"));
        builder.add(NtmItems.SPARK_BLEND.get(), translations.getProperty("item.powder_spark_mix.name"));
        builder.add(NtmItems.STARMETAL_INGOT.get(), translations.getProperty("item.ingot_starmetal.name"));
        builder.add(NtmItems.DENSE_STARMETAL_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.starmetal"));
        builder.add(NtmItems.STARMETAL_RING.get(), translations.getProperty("item.ring_starmetal.name"));
        builder.add(NtmItems.STARMETAL_CRYSTALS.get(), translations.getProperty("item.crystal_starmetal.name"));
        builder.add(NtmItems.STRONTIUM_POWDER.get(), translations.getProperty("item.powder_strontium.name"));
        builder.add(NtmItems.STRONTIUM_90_INGOT.get(), translations.getProperty("item.ingot_sr90.name"));
        builder.add(NtmItems.STRONTIUM_90_BILLET.get(), translations.getProperty("item.billet_sr90.name"));
        builder.add(NtmItems.STRONTIUM_90_POWDER.get(), translations.getProperty("item.powder_sr90.name"));
        builder.add(NtmItems.TINY_PILE_OF_STRONTIUM_90_POWDER.get(), translations.getProperty("item.powder_sr90_tiny.name"));
        builder.add(NtmItems.STRONTIUM_90_NUGGET.get(), translations.getProperty("item.nugget_sr90.name"));
        builder.add(NtmItems.STEEL_INGOT.get(), translations.getProperty("item.ingot_steel.name"));
        builder.add(NtmItems.STEEL_POWDER.get(), translations.getProperty("item.powder_steel.name"));
        builder.add(NtmItems.TINY_PILE_OF_STEEL_POWDER.get(), translations.getProperty("item.powder_steel_tiny.name"));
        builder.add(NtmItems.STEEL_PLATE.get(), translations.getProperty("item.plate_steel.name"));
        builder.add(NtmItems.CAST_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.steel"));
        builder.add(NtmItems.WELDED_STEEL_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.steel"));
        builder.add(NtmItems.STEEL_BOLT.get(), getFormated(translations, "item.boltntm.name", "hbmmat.steel"));
        builder.add(NtmItems.STEEL_PIPE.get(), getFormated(translations, "item.pipentm.name", "hbmmat.steel"));
        builder.add(NtmItems.STEEL_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.steel"));
        builder.add(NtmItems.STEEL_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.steel"));
        builder.add(NtmItems.SULFUR.get(), translations.getProperty("item.sulfur.name"));
        builder.add(NtmItems.SULFUR_CRYSTALS.get(), translations.getProperty("item.crystal_sulfur.name"));
        builder.add(NtmItems.PURIFIED_TANTALITE.get(), translations.getProperty("item.powder_coltan.name"));
        builder.add(NtmItems.TANTALUM_INGOT.get(), translations.getProperty("item.ingot_tantalium.name"));
        builder.add(NtmItems.TANTALUM_POWDER.get(), translations.getProperty("item.powder_tantalium.name"));
        builder.add(NtmItems.TANTALUM_NUGGET.get(), translations.getProperty("item.nugget_tantalium.name"));
        builder.add(NtmItems.TANTALUM_POLYCRYSTAL.get(), translations.getProperty("item.gem_tantalium.name"));
        builder.add(NtmItems.TECHNETIUM_99_INGOT.get(), translations.getProperty("item.ingot_technetium.name"));
        builder.add(NtmItems.TECHNETIUM_99_BILLET.get(), translations.getProperty("item.billet_technetium.name"));
        builder.add(NtmItems.TECHNETIUM_99_NUGGET.get(), translations.getProperty("item.nugget_technetium.name"));
        builder.add(NtmItems.TECHNETIUM_STEEL_INGOT.get(), translations.getProperty("item.ingot_tcalloy.name"));
        builder.add(NtmItems.TECHNETIUM_STEEL_POWDER.get(), translations.getProperty("item.powder_tcalloy.name"));
        builder.add(NtmItems.CAST_TECHNETIUM_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.tcalloy"));
        builder.add(NtmItems.WELDED_TECHNETIUM_STEEL_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.tcalloy"));
        builder.add(NtmItems.TEKTITE_POWDER.get(), translations.getProperty("item.powder_tektite.name"));
        builder.add(NtmItems.TENNESSINE_POWDER.get(), translations.getProperty("item.powder_tennessine.name"));
        builder.add(NtmItems.THERMITE.get(), translations.getProperty("item.powder_thermite.name"));
        builder.add(NtmItems.THORIUM_232_INGOT.get(), translations.getProperty("item.ingot_th232.name"));
        builder.add(NtmItems.THORIUM_232_BILLET.get(), translations.getProperty("item.billet_th232.name"));
        builder.add(NtmItems.THORIUM_232_NUGGET.get(), translations.getProperty("item.nugget_th232.name"));
        builder.add(NtmItems.THORIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_thorium_fuel.name"));
        builder.add(NtmItems.THORIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_thorium_fuel.name"));
        builder.add(NtmItems.THORIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_thorium_fuel.name"));
        builder.add(NtmItems.THORIUM_POWDER.get(), translations.getProperty("item.powder_thorium.name"));
        builder.add(NtmItems.THORIUM_CRYSTALS.get(), translations.getProperty("item.crystal_thorium.name"));
        builder.add(NtmItems.TITANIUM_INGOT.get(), translations.getProperty("item.ingot_titanium.name"));
        builder.add(NtmItems.TITANIUM_PLATE.get(), translations.getProperty("item.plate_titanium.name"));
        builder.add(NtmItems.CAST_TITANIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.titanium"));
        builder.add(NtmItems.WELDED_TITANIUM_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.titanium"));
        builder.add(NtmItems.TITANIUM_POWDER.get(), translations.getProperty("item.powder_titanium.name"));
        builder.add(NtmItems.TITANIUM_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.titanium"));
        builder.add(NtmItems.DENSE_TITANIUM_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.titanium"));
        builder.add(NtmItems.TITANIUM_CRYSTALS.get(), translations.getProperty("item.crystal_titanium.name"));
        builder.add(NtmItems.TRIXITE_CRYSTALS.get(), translations.getProperty("item.crystal_trixite.name"));
        builder.add(NtmItems.TUNGSTEN_INGOT.get(), translations.getProperty("item.ingot_tungsten.name"));
        builder.add(NtmItems.TUNGSTEN_POWDER.get(), translations.getProperty("item.powder_tungsten.name"));
        builder.add(NtmItems.TUNGSTEN_BOLT.get(), getFormated(translations, "item.boltntm.name", "hbmmat.tungsten"));
        builder.add(NtmItems.TUNGSTEN_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.tungsten"));
        builder.add(NtmItems.DENSE_TUNGSTEN_WIRE.get(), getFormated(translations, "item.wire_dense.name", "hbmmat.tungsten"));
        builder.add(NtmItems.TUNGSTEN_CRYSTALS.get(), translations.getProperty("item.crystal_tungsten.name"));
        builder.add(NtmItems.URANIUM_INGOT.get(), translations.getProperty("item.ingot_uranium.name"));
        builder.add(NtmItems.URANIUM_BILLET.get(), translations.getProperty("item.billet_uranium.name"));
        builder.add(NtmItems.URANIUM_NUGGET.get(), translations.getProperty("item.nugget_uranium.name"));
        builder.add(NtmItems.URANIUM_FUEL_INGOT.get(), translations.getProperty("item.ingot_uranium_fuel.name"));
        builder.add(NtmItems.URANIUM_FUEL_BILLET.get(), translations.getProperty("item.billet_uranium_fuel.name"));
        builder.add(NtmItems.URANIUM_FUEL_NUGGET.get(), translations.getProperty("item.nugget_uranium_fuel.name"));
        builder.add(NtmItems.URANIUM_233_INGOT.get(), translations.getProperty("item.ingot_u233.name"));
        builder.add(NtmItems.URANIUM_233_BILLET.get(), translations.getProperty("item.billet_u233.name"));
        builder.add(NtmItems.URANIUM_233_NUGGET.get(), translations.getProperty("item.nugget_u233.name"));
        builder.add(NtmItems.URANIUM_235_INGOT.get(), translations.getProperty("item.ingot_u235.name"));
        builder.add(NtmItems.URANIUM_235_BILLET.get(), translations.getProperty("item.billet_u235.name"));
        builder.add(NtmItems.URANIUM_235_NUGGET.get(), translations.getProperty("item.nugget_u235.name"));
        builder.add(NtmItems.URANIUM_238_INGOT.get(), translations.getProperty("item.ingot_u238.name"));
        builder.add(NtmItems.URANIUM_238_BILLET.get(), translations.getProperty("item.billet_u238.name"));
        builder.add(NtmItems.URANIUM_238_NUGGET.get(), translations.getProperty("item.nugget_u238.name"));
        builder.add(NtmItems.URANIUM_ZIRCONIUM_HYDRIDE_BILLET.get(), translations.getProperty("item.billet_uzh.name"));
        builder.add(NtmItems.URANIUM_POWDER.get(), translations.getProperty("item.powder_uranium.name"));
        builder.add(NtmItems.URANIUM_CRYSTALS.get(), translations.getProperty("item.crystal_uranium.name"));
        builder.add(NtmItems.VOLCANIC_GEM.get(), translations.getProperty("item.gem_volcanic.name"));
        builder.add(NtmItems.WEAPON_STEEL_INGOT.get(), translations.getProperty("item.ingot_weaponsteel.name"));
        builder.add(NtmItems.WEAPON_STEEL_PLATE.get(), translations.getProperty("item.plate_weaponsteel.name"));
        builder.add(NtmItems.CAST_WEAPON_STEEL_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.weaponsteel"));
        builder.add(NtmItems.WEAPON_STEEL_SHELL.get(), getFormated(translations, "item.shellntm.name", "hbmmat.weaponsteel"));
        builder.add(NtmItems.XENON_135_POWDER.get(), translations.getProperty("item.powder_xe135.name"));
        builder.add(NtmItems.TINY_PILE_OF_XENON_135_POWDER.get(), translations.getProperty("item.powder_xe135_tiny.name"));
        builder.add(NtmItems.YHARONITE_BILLET.get(), translations.getProperty("item.billet_yharonite.name"));
        builder.add(NtmItems.YELLOWCAKE.get(), translations.getProperty("item.powder_yellowcake.name"));
        builder.add(NtmItems.ZIRCONIUM_SPLINTER.get(), translations.getProperty("item.nugget_zirconium.name"));
        builder.add(NtmItems.ZIRCONIUM_CUBE.get(), translations.getProperty("item.ingot_zirconium.name"));
        builder.add(NtmItems.ZIRCONIUM_BILLET.get(), translations.getProperty("item.billet_zirconium.name"));
        builder.add(NtmItems.ZIRCONIUM_POWDER.get(), translations.getProperty("item.powder_zirconium.name"));
        builder.add(NtmItems.CAST_ZIRCONIUM_PLATE.get(), getFormated(translations, "item.plate_cast.name", "hbmmat.zirconium"));
        builder.add(NtmItems.WELDED_ZIRCONIUM_PLATE.get(), getFormated(translations, "item.plate_welded.name", "hbmmat.zirconium"));
        builder.add(NtmItems.ZIRCONIUM_WIRE.get(), getFormated(translations, "item.wire_fine.name", "hbmmat.zirconium"));

        builder.add(NtmBlocks.GEOTHERMAL_VENT.get(), translations.getProperty("tile.ore_volcano.name"));
        builder.add(NtmBlocks.GEOTHERMAL_VENT.get().asItem(), translations.getProperty("tile.ore_volcano.name"));
        builder.add(NtmBlocks.BEDROCK_OIL_DEPOSIT.get(), translations.getProperty("tile.ore_bedrock_oil.name"));
        builder.add(NtmBlocks.BEDROCK_OIL_DEPOSIT.get().asItem(), translations.getProperty("tile.ore_bedrock_oil.name"));
        builder.add(NtmBlocks.BEDROCK_ORE.get(), translations.getProperty("tile.ore_bedrock.name"));
        builder.add(NtmBlocks.BEDROCK_ORE.get().asItem(), translations.getProperty("tile.ore_bedrock.name"));
        builder.add(NtmBlocks.ALLOY_FURNACE.get(), translations.getProperty("tile.machine_difurnace_on.name"));
        builder.add(NtmBlocks.ALLOY_FURNACE.get().asItem(), translations.getProperty("tile.machine_difurnace_on.name"));
        builder.add(NtmBlocks.ALLOY_FURNACE_EXTENSION.get(), translations.getProperty("tile.machine_difurnace_extension.name"));
        builder.add(NtmBlocks.ALLOY_FURNACE_EXTENSION.get().asItem(), translations.getProperty("tile.machine_difurnace_extension.name"));
        builder.add(NtmBlocks.ELECTRIC_FURNACE.get(), translations.getProperty("tile.machine_electric_furnace_on.name"));
        builder.add(NtmBlocks.ELECTRIC_FURNACE.get().asItem(), translations.getProperty("tile.machine_electric_furnace_on.name"));
        builder.add(NtmBlocks.PWR_CONTROLLER.get(), translations.getProperty("tile.pwr_controller.name"));
        builder.add(NtmBlocks.PWR_CONTROLLER.get().asItem(), translations.getProperty("tile.pwr_controller.name"));
        builder.add(NtmBlocks.URANIUM_ORE.get(), translations.getProperty("tile.ore_uranium.name"));
        builder.add(NtmBlocks.URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_uranium.name"));
        builder.add(NtmBlocks.SCORCHED_URANIUM_ORE.get(), translations.getProperty("tile.ore_uranium_scorched.name"));
        builder.add(NtmBlocks.SCORCHED_URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_uranium_scorched.name"));
        builder.add(NtmBlocks.TITANIUM_ORE.get(), translations.getProperty("tile.ore_titanium.name"));
        builder.add(NtmBlocks.TITANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_titanium.name"));
        builder.add(NtmBlocks.SULFUR_ORE.get(), translations.getProperty("tile.ore_sulfur.name"));
        builder.add(NtmBlocks.SULFUR_ORE.get().asItem(), translations.getProperty("tile.ore_sulfur.name"));
        builder.add(NtmBlocks.THORIUM_ORE.get(), translations.getProperty("tile.ore_thorium.name"));
        builder.add(NtmBlocks.THORIUM_ORE.get().asItem(), translations.getProperty("tile.ore_thorium.name"));
        builder.add(NtmBlocks.NITER_ORE.get(), translations.getProperty("tile.ore_niter.name"));
        builder.add(NtmBlocks.NITER_ORE.get().asItem(), translations.getProperty("tile.ore_niter.name"));
        builder.add(NtmBlocks.TUNGSTEN_ORE.get(), translations.getProperty("tile.ore_tungsten.name"));
        builder.add(NtmBlocks.TUNGSTEN_ORE.get().asItem(), translations.getProperty("tile.ore_tungsten.name"));
        builder.add(NtmBlocks.ALUMINIUM_BEARING_ORE.get(), translations.getProperty("tile.ore_aluminium.name"));
        builder.add(NtmBlocks.ALUMINIUM_BEARING_ORE.get().asItem(), translations.getProperty("tile.ore_aluminium.name"));
        builder.add(NtmBlocks.FLUORITE_ORE.get(), translations.getProperty("tile.ore_fluorite.name"));
        builder.add(NtmBlocks.FLUORITE_ORE.get().asItem(), translations.getProperty("tile.ore_fluorite.name"));
        builder.add(NtmBlocks.LEAD_ORE.get(), translations.getProperty("tile.ore_lead.name"));
        builder.add(NtmBlocks.LEAD_ORE.get().asItem(), translations.getProperty("tile.ore_lead.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_ORE.get(), translations.getProperty("tile.ore_schrabidium.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_ORE.get().asItem(), translations.getProperty("tile.ore_schrabidium.name"));
        builder.add(NtmBlocks.BERYLLIUM_ORE.get(), translations.getProperty("tile.ore_beryllium.name"));
        builder.add(NtmBlocks.BERYLLIUM_ORE.get().asItem(), translations.getProperty("tile.ore_beryllium.name"));
        builder.add(NtmBlocks.AUSTRALIUM_ORE.get(), translations.getProperty("tile.ore_australium.name"));
        builder.add(NtmBlocks.AUSTRALIUM_ORE.get().asItem(), translations.getProperty("tile.ore_australium.name"));
        builder.add(NtmBlocks.RARE_EARTH_ORE.get(), translations.getProperty("tile.ore_rare.name"));
        builder.add(NtmBlocks.RARE_EARTH_ORE.get().asItem(), translations.getProperty("tile.ore_rare.name"));
        builder.add(NtmBlocks.COBALT_ORE.get(), translations.getProperty("tile.ore_cobalt.name"));
        builder.add(NtmBlocks.COBALT_ORE.get().asItem(), translations.getProperty("tile.ore_cobalt.name"));
        builder.add(NtmBlocks.CINNEBAR_ORE.get(), translations.getProperty("tile.ore_cinnebar.name"));
        builder.add(NtmBlocks.CINNEBAR_ORE.get().asItem(), translations.getProperty("tile.ore_cinnebar.name"));
        builder.add(NtmBlocks.COLTAN_ORE.get(), translations.getProperty("tile.ore_coltan.name"));
        builder.add(NtmBlocks.COLTAN_ORE.get().asItem(), translations.getProperty("tile.ore_coltan.name"));
        builder.add(NtmBlocks.LIGNITE_ORE.get(), translations.getProperty("tile.ore_lignite.name"));
        builder.add(NtmBlocks.LIGNITE_ORE.get().asItem(), translations.getProperty("tile.ore_lignite.name"));
        builder.add(NtmBlocks.ASBESTOS_ORE.get(), translations.getProperty("tile.ore_asbestos.name"));
        builder.add(NtmBlocks.ASBESTOS_ORE.get().asItem(), translations.getProperty("tile.ore_asbestos.name"));
        builder.add(NtmBlocks.OIL_DEPOSIT.get(), translations.getProperty("tile.ore_oil.name"));
        builder.add(NtmBlocks.OIL_DEPOSIT.get().asItem(), translations.getProperty("tile.ore_oil.name"));
        builder.add(NtmBlocks.EMPTY_OIL_DEPOSIT.get(), translations.getProperty("tile.ore_oil_empty.name"));
        builder.add(NtmBlocks.EMPTY_OIL_DEPOSIT.get().asItem(), translations.getProperty("tile.ore_oil_empty.name"));
        builder.add(NtmBlocks.ALUMINIUM_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_aluminium.name"));
        builder.add(NtmBlocks.ALUMINIUM_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_aluminium.name"));
        builder.add(NtmBlocks.COPPER_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_copper.name"));
        builder.add(NtmBlocks.COPPER_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_copper.name"));
        builder.add(NtmBlocks.IRON_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_iron.name"));
        builder.add(NtmBlocks.IRON_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_iron.name"));
        builder.add(NtmBlocks.TITANIUM_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_titanium.name"));
        builder.add(NtmBlocks.TITANIUM_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_titanium.name"));
        builder.add(NtmBlocks.BAUXITE.get(), translations.getProperty("tile.stone_resource.bauxite.name"));
        builder.add(NtmBlocks.BAUXITE.get().asItem(), translations.getProperty("tile.stone_resource.bauxite.name"));
        builder.add(NtmBlocks.CHRYSOTILE.get(), translations.getProperty("tile.stone_resource.asbestos.name"));
        builder.add(NtmBlocks.CHRYSOTILE.get().asItem(), translations.getProperty("tile.stone_resource.asbestos.name"));
        builder.add(NtmBlocks.HEMATITE.get(), translations.getProperty("tile.stone_resource.hematite.name"));
        builder.add(NtmBlocks.HEMATITE.get().asItem(), translations.getProperty("tile.stone_resource.hematite.name"));
        builder.add(NtmBlocks.LIMESTONE.get(), translations.getProperty("tile.stone_resource.limestone.name"));
        builder.add(NtmBlocks.LIMESTONE.get().asItem(), translations.getProperty("tile.stone_resource.limestone.name"));
        builder.add(NtmBlocks.MALACHITE.get(), translations.getProperty("tile.stone_resource.malachite.name"));
        builder.add(NtmBlocks.MALACHITE.get().asItem(), translations.getProperty("tile.stone_resource.malachite.name"));
        builder.add(NtmBlocks.SULFUROUS_STONE.get(), translations.getProperty("tile.stone_resource.sulfur.name"));
        builder.add(NtmBlocks.SULFUROUS_STONE.get().asItem(), translations.getProperty("tile.stone_resource.sulfur.name"));
        builder.add(NtmBlocks.TEKTITE.get(), translations.getProperty("tile.tektite.name"));
        builder.add(NtmBlocks.TEKTITE.get().asItem(), translations.getProperty("tile.tektite.name"));
        builder.add(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE.get(), translations.getProperty("tile.ore_tektite_osmiridium.name"));
        builder.add(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE.get().asItem(), translations.getProperty("tile.ore_tektite_osmiridium.name"));
        builder.add(NtmBlocks.TRIXITE_ORE.get(), translations.getProperty("tile.ore_tikite.name"));
        builder.add(NtmBlocks.TRIXITE_ORE.get().asItem(), translations.getProperty("tile.ore_tikite.name"));
        builder.add(NtmBlocks.ACTINIUM_227_BLOCK.get(), translations.getProperty("tile.block_actinium.name"));
        builder.add(NtmBlocks.ACTINIUM_227_BLOCK.get().asItem(), translations.getProperty("tile.block_actinium.name"));
        builder.add(NtmBlocks.ADVANCED_ALLOY_BLOCK.get(), translations.getProperty("tile.block_advanced_alloy.name"));
        builder.add(NtmBlocks.ADVANCED_ALLOY_BLOCK.get().asItem(), translations.getProperty("tile.block_advanced_alloy.name"));
        builder.add(NtmBlocks.ALUMINIUM_BLOCK.get(), translations.getProperty("tile.block_aluminium.name"));
        builder.add(NtmBlocks.ALUMINIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_aluminium.name"));
        builder.add(NtmBlocks.ASBESTOS_BLOCK.get(), translations.getProperty("tile.block_asbestos.name"));
        builder.add(NtmBlocks.ASBESTOS_BLOCK.get().asItem(), translations.getProperty("tile.block_asbestos.name"));
        builder.add(NtmBlocks.AUSTRALIUM_BLOCK.get(), translations.getProperty("tile.block_australium.name"));
        builder.add(NtmBlocks.AUSTRALIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_australium.name"));
        builder.add(NtmBlocks.BAKELITE_BLOCK.get(), translations.getProperty("tile.block_bakelite.name"));
        builder.add(NtmBlocks.BAKELITE_BLOCK.get().asItem(), translations.getProperty("tile.block_bakelite.name"));
        builder.add(NtmBlocks.BERYLLIUM_BLOCK.get(), translations.getProperty("tile.block_beryllium.name"));
        builder.add(NtmBlocks.BERYLLIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_beryllium.name"));
        builder.add(NtmBlocks.BISMUTH_BLOCK.get(), translations.getProperty("tile.block_bismuth.name"));
        builder.add(NtmBlocks.BISMUTH_BLOCK.get().asItem(), translations.getProperty("tile.block_bismuth.name"));
        builder.add(NtmBlocks.BORON_BLOCK.get(), translations.getProperty("tile.block_boron.name"));
        builder.add(NtmBlocks.BORON_BLOCK.get().asItem(), translations.getProperty("tile.block_boron.name"));
        builder.add(NtmBlocks.CADMIUM_BLOCK.get(), translations.getProperty("tile.block_cadmium.name"));
        builder.add(NtmBlocks.CADMIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_cadmium.name"));
        builder.add(NtmBlocks.CADMIUM_STEEL_BLOCK.get(), translations.getProperty("tile.block_cdalloy.name"));
        builder.add(NtmBlocks.CADMIUM_STEEL_BLOCK.get().asItem(), translations.getProperty("tile.block_cdalloy.name"));
        builder.add(NtmBlocks.CMB_STEEL_BLOCK.get(), translations.getProperty("tile.block_combine_steel.name"));
        builder.add(NtmBlocks.CMB_STEEL_BLOCK.get().asItem(), translations.getProperty("tile.block_combine_steel.name"));
        builder.add(NtmBlocks.COAL_COKE_BLOCK.get(), translations.getProperty("tile.block_coke.coal.name"));
        builder.add(NtmBlocks.COAL_COKE_BLOCK.get().asItem(), translations.getProperty("tile.block_coke.coal.name"));
        builder.add(NtmBlocks.COBALT_BLOCK.get(), translations.getProperty("tile.block_cobalt.name"));
        builder.add(NtmBlocks.COBALT_BLOCK.get().asItem(), translations.getProperty("tile.block_cobalt.name"));
        builder.add(NtmBlocks.COLTAN_BLOCK.get(), translations.getProperty("tile.block_coltan.name"));
        builder.add(NtmBlocks.COLTAN_BLOCK.get().asItem(), translations.getProperty("tile.block_coltan.name"));
        builder.add(NtmBlocks.DESH_BLOCK.get(), translations.getProperty("tile.block_desh.name"));
        builder.add(NtmBlocks.DESH_BLOCK.get().asItem(), translations.getProperty("tile.block_desh.name"));
        builder.add(NtmBlocks.DINEUTRONIUM_BLOCK.get(), translations.getProperty("tile.block_dineutronium.name"));
        builder.add(NtmBlocks.DINEUTRONIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_dineutronium.name"));
        builder.add(NtmBlocks.EUPHEMIUM_BLOCK.get(), translations.getProperty("tile.block_euphemium.name"));
        builder.add(NtmBlocks.EUPHEMIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_euphemium.name"));
        builder.add(NtmBlocks.FERRIC_SCHRABIDATE_BLOCK.get(), translations.getProperty("tile.block_schrabidate.name"));
        builder.add(NtmBlocks.FERRIC_SCHRABIDATE_BLOCK.get().asItem(), translations.getProperty("tile.block_schrabidate.name"));
        builder.add(NtmBlocks.FLUORITE_BLOCK.get(), translations.getProperty("tile.block_fluorite.name"));
        builder.add(NtmBlocks.FLUORITE_BLOCK.get().asItem(), translations.getProperty("tile.block_fluorite.name"));
        builder.add(NtmBlocks.GRAPHITE_BLOCK.get(), translations.getProperty("tile.block_graphite.name"));
        builder.add(NtmBlocks.GRAPHITE_BLOCK.get().asItem(), translations.getProperty("tile.block_graphite.name"));
        builder.add(NtmBlocks.HIGH_SPEED_STEEL_BLOCK.get(), translations.getProperty("tile.block_dura_steel.name"));
        builder.add(NtmBlocks.HIGH_SPEED_STEEL_BLOCK.get().asItem(), translations.getProperty("tile.block_dura_steel.name"));
        builder.add(NtmBlocks.LIGNITE_COKE_BLOCK.get(), translations.getProperty("tile.block_coke.lignite.name"));
        builder.add(NtmBlocks.LIGNITE_COKE_BLOCK.get().asItem(), translations.getProperty("tile.block_coke.lignite.name"));
        builder.add(NtmBlocks.LEAD_BLOCK.get(), translations.getProperty("tile.block_lead.name"));
        builder.add(NtmBlocks.LEAD_BLOCK.get().asItem(), translations.getProperty("tile.block_lead.name"));
        builder.add(NtmBlocks.LITHIUM_BLOCK.get(), translations.getProperty("tile.block_lithium.name"));
        builder.add(NtmBlocks.LITHIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_lithium.name"));
        builder.add(NtmBlocks.MAGNETIZED_TUNGSTEN_BLOCK.get(), translations.getProperty("tile.block_magnetized_tungsten.name"));
        builder.add(NtmBlocks.MAGNETIZED_TUNGSTEN_BLOCK.get().asItem(), translations.getProperty("tile.block_magnetized_tungsten.name"));
        builder.add(NtmBlocks.MOX_FUEL_BLOCK.get(), translations.getProperty("tile.block_mox_fuel.name"));
        builder.add(NtmBlocks.MOX_FUEL_BLOCK.get().asItem(), translations.getProperty("tile.block_mox_fuel.name"));
        builder.add(NtmBlocks.NEPTUNIUM_BLOCK.get(), translations.getProperty("tile.block_neptunium.name"));
        builder.add(NtmBlocks.NEPTUNIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_neptunium.name"));
        builder.add(NtmBlocks.NIOBIUM_BLOCK.get(), translations.getProperty("tile.block_niobium.name"));
        builder.add(NtmBlocks.NIOBIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_niobium.name"));
        builder.add(NtmBlocks.NITER_BLOCK.get(), translations.getProperty("tile.block_niter.name"));
        builder.add(NtmBlocks.NITER_BLOCK.get().asItem(), translations.getProperty("tile.block_niter.name"));
        builder.add(NtmBlocks.PETROLEUM_COKE_BLOCK.get(), translations.getProperty("tile.block_coke.petroleum.name"));
        builder.add(NtmBlocks.PETROLEUM_COKE_BLOCK.get().asItem(), translations.getProperty("tile.block_coke.petroleum.name"));
        builder.add(NtmBlocks.PLUTONIUM_BLOCK.get(), translations.getProperty("tile.block_plutonium.name"));
        builder.add(NtmBlocks.PLUTONIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_plutonium.name"));
        builder.add(NtmBlocks.PLUTONIUM_FUEL_BLOCK.get(), translations.getProperty("tile.block_plutonium_fuel.name"));
        builder.add(NtmBlocks.PLUTONIUM_FUEL_BLOCK.get().asItem(), translations.getProperty("tile.block_plutonium_fuel.name"));
        builder.add(NtmBlocks.REACTOR_GRADE_PLUTONIUM_BLOCK.get(), translations.getProperty("tile.block_pu_mix.name"));
        builder.add(NtmBlocks.REACTOR_GRADE_PLUTONIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_pu_mix.name"));
        builder.add(NtmBlocks.PLUTONIUM_238_BLOCK.get(), translations.getProperty("tile.block_pu238.name"));
        builder.add(NtmBlocks.PLUTONIUM_238_BLOCK.get().asItem(), translations.getProperty("tile.block_pu238.name"));
        builder.add(NtmBlocks.PLUTONIUM_239_BLOCK.get(), translations.getProperty("tile.block_pu239.name"));
        builder.add(NtmBlocks.PLUTONIUM_239_BLOCK.get().asItem(), translations.getProperty("tile.block_pu239.name"));
        builder.add(NtmBlocks.PLUTONIUM_240_BLOCK.get(), translations.getProperty("tile.block_pu240.name"));
        builder.add(NtmBlocks.PLUTONIUM_240_BLOCK.get().asItem(), translations.getProperty("tile.block_pu240.name"));
        builder.add(NtmBlocks.POLONIUM_210_BLOCK.get(), translations.getProperty("tile.block_polonium.name"));
        builder.add(NtmBlocks.POLONIUM_210_BLOCK.get().asItem(), translations.getProperty("tile.block_polonium.name"));
        builder.add(NtmBlocks.POLYMER_BLOCK.get(), translations.getProperty("tile.block_polymer.name"));
        builder.add(NtmBlocks.POLYMER_BLOCK.get().asItem(), translations.getProperty("tile.block_polymer.name"));
        builder.add(NtmBlocks.RADIUM_226_BLOCK.get(), translations.getProperty("tile.block_ra226.name"));
        builder.add(NtmBlocks.RADIUM_226_BLOCK.get().asItem(), translations.getProperty("tile.block_ra226.name"));
        builder.add(NtmBlocks.RED_COPPER_BLOCK.get(), translations.getProperty("tile.block_red_copper.name"));
        builder.add(NtmBlocks.RED_COPPER_BLOCK.get().asItem(), translations.getProperty("tile.block_red_copper.name"));
        builder.add(NtmBlocks.RED_PHOSPHORUS_BLOCK.get(), translations.getProperty("tile.block_red_phosphorus.name"));
        builder.add(NtmBlocks.RED_PHOSPHORUS_BLOCK.get().asItem(), translations.getProperty("tile.block_red_phosphorus.name"));
        builder.add(NtmBlocks.RUBBER_BLOCK.get(), translations.getProperty("tile.block_rubber.name"));
        builder.add(NtmBlocks.RUBBER_BLOCK.get().asItem(), translations.getProperty("tile.block_rubber.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_BLOCK.get(), translations.getProperty("tile.block_schrabidium.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_schrabidium.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_FUEL_BLOCK.get(), translations.getProperty("tile.block_schrabidium_fuel.name"));
        builder.add(NtmBlocks.SCHRABIDIUM_FUEL_BLOCK.get().asItem(), translations.getProperty("tile.block_schrabidium_fuel.name"));
        builder.add(NtmBlocks.SCHRARANIUM_BLOCK.get(), translations.getProperty("tile.block_schraranium.name"));
        builder.add(NtmBlocks.SCHRARANIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_schraranium.name"));
        builder.add(NtmBlocks.SEMTEX_BLOCK.get(), translations.getProperty("tile.block_semtex.name"));
        builder.add(NtmBlocks.SEMTEX_BLOCK.get().asItem(), translations.getProperty("tile.block_semtex.name"));
        builder.add(NtmBlocks.SOLINIUM_BLOCK.get(), translations.getProperty("tile.block_solinium.name"));
        builder.add(NtmBlocks.SOLINIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_solinium.name"));
        builder.add(NtmBlocks.STARMETAL_BLOCK.get(), translations.getProperty("tile.block_starmetal.name"));
        builder.add(NtmBlocks.STARMETAL_BLOCK.get().asItem(), translations.getProperty("tile.block_starmetal.name"));
        builder.add(NtmBlocks.STEEL_BLOCK.get(), translations.getProperty("tile.block_steel.name"));
        builder.add(NtmBlocks.STEEL_BLOCK.get().asItem(), translations.getProperty("tile.block_steel.name"));
        builder.add(NtmBlocks.SULFUR_BLOCK.get(), translations.getProperty("tile.block_sulfur.name"));
        builder.add(NtmBlocks.SULFUR_BLOCK.get().asItem(), translations.getProperty("tile.block_sulfur.name"));
        builder.add(NtmBlocks.TANTALUM_BLOCK.get(), translations.getProperty("tile.block_tantalium.name"));
        builder.add(NtmBlocks.TANTALUM_BLOCK.get().asItem(), translations.getProperty("tile.block_tantalium.name"));
        builder.add(NtmBlocks.TECHNETIUM_STEEL_BLOCK.get(), translations.getProperty("tile.block_tcalloy.name"));
        builder.add(NtmBlocks.TECHNETIUM_STEEL_BLOCK.get().asItem(), translations.getProperty("tile.block_tcalloy.name"));
        builder.add(NtmBlocks.THORIUM_232_BLOCK.get(), translations.getProperty("tile.block_thorium.name"));
        builder.add(NtmBlocks.THORIUM_232_BLOCK.get().asItem(), translations.getProperty("tile.block_thorium.name"));
        builder.add(NtmBlocks.THORIUM_FUEL_BLOCK.get(), translations.getProperty("tile.block_thorium_fuel.name"));
        builder.add(NtmBlocks.THORIUM_FUEL_BLOCK.get().asItem(), translations.getProperty("tile.block_thorium_fuel.name"));
        builder.add(NtmBlocks.TITANIUM_BLOCK.get(), translations.getProperty("tile.block_titanium.name"));
        builder.add(NtmBlocks.TITANIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_titanium.name"));
        builder.add(NtmBlocks.TUNGSTEN_BLOCK.get(), translations.getProperty("tile.block_tungsten.name"));
        builder.add(NtmBlocks.TUNGSTEN_BLOCK.get().asItem(), translations.getProperty("tile.block_tungsten.name"));
        builder.add(NtmBlocks.URANIUM_BLOCK.get(), translations.getProperty("tile.block_uranium.name"));
        builder.add(NtmBlocks.URANIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_uranium.name"));
        builder.add(NtmBlocks.URANIUM_FUEL_BLOCK.get(), translations.getProperty("tile.block_uranium_fuel.name"));
        builder.add(NtmBlocks.URANIUM_FUEL_BLOCK.get().asItem(), translations.getProperty("tile.block_uranium_fuel.name"));
        builder.add(NtmBlocks.URANIUM_233_BLOCK.get(), translations.getProperty("tile.block_u233.name"));
        builder.add(NtmBlocks.URANIUM_233_BLOCK.get().asItem(), translations.getProperty("tile.block_u233.name"));
        builder.add(NtmBlocks.URANIUM_235_BLOCK.get(), translations.getProperty("tile.block_u235.name"));
        builder.add(NtmBlocks.URANIUM_235_BLOCK.get().asItem(), translations.getProperty("tile.block_u235.name"));
        builder.add(NtmBlocks.URANIUM_238_BLOCK.get(), translations.getProperty("tile.block_u238.name"));
        builder.add(NtmBlocks.URANIUM_238_BLOCK.get().asItem(), translations.getProperty("tile.block_u238.name"));
        builder.add(NtmBlocks.WHITE_PHOSPHORUS_BLOCK.get(), translations.getProperty("tile.block_white_phosphorus.name"));
        builder.add(NtmBlocks.WHITE_PHOSPHORUS_BLOCK.get().asItem(), translations.getProperty("tile.block_white_phosphorus.name"));
        builder.add(NtmBlocks.YELLOWCAKE_BLOCK.get(), translations.getProperty("tile.block_yellowcake.name"));
        builder.add(NtmBlocks.YELLOWCAKE_BLOCK.get().asItem(), translations.getProperty("tile.block_yellowcake.name"));
        builder.add(NtmBlocks.ZIRCONIUM_BLOCK.get(), translations.getProperty("tile.block_zirconium.name"));
        builder.add(NtmBlocks.ZIRCONIUM_BLOCK.get().asItem(), translations.getProperty("tile.block_zirconium.name"));
        builder.add(NtmBlocks.GRAPHITIC_SCHIST.get(), translations.getProperty("tile.stone_gneiss.name"));
        builder.add(NtmBlocks.GRAPHITIC_SCHIST.get().asItem(), translations.getProperty("tile.stone_gneiss.name"));
        builder.add(NtmBlocks.SCHIST_IRON_ORE.get(), translations.getProperty("tile.ore_gneiss_iron.name"));
        builder.add(NtmBlocks.SCHIST_IRON_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_iron.name"));
        builder.add(NtmBlocks.SCHIST_GOLD_ORE.get(), translations.getProperty("tile.ore_gneiss_gold.name"));
        builder.add(NtmBlocks.SCHIST_GOLD_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_gold.name"));
        builder.add(NtmBlocks.SCHIST_URANIUM_ORE.get(), translations.getProperty("tile.ore_gneiss_uranium.name"));
        builder.add(NtmBlocks.SCHIST_URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_uranium.name"));
        builder.add(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE.get(), translations.getProperty("tile.ore_gneiss_uranium_scorched.name"));
        builder.add(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_uranium_scorched.name"));
        builder.add(NtmBlocks.SCHIST_COPPER_ORE.get(), translations.getProperty("tile.ore_gneiss_copper.name"));
        builder.add(NtmBlocks.SCHIST_COPPER_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_copper.name"));
        builder.add(NtmBlocks.SCHIST_ASBESTOS_ORE.get(), translations.getProperty("tile.ore_gneiss_asbestos.name"));
        builder.add(NtmBlocks.SCHIST_ASBESTOS_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_asbestos.name"));
        builder.add(NtmBlocks.SCHIST_LITHIUM_ORE.get(), translations.getProperty("tile.ore_gneiss_lithium.name"));
        builder.add(NtmBlocks.SCHIST_LITHIUM_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_lithium.name"));
        builder.add(NtmBlocks.SCHIST_SCHRABIDIUM_ORE.get(), translations.getProperty("tile.ore_gneiss_schrabidium.name"));
        builder.add(NtmBlocks.SCHIST_SCHRABIDIUM_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_schrabidium.name"));
        builder.add(NtmBlocks.SCHIST_RARE_EARTH_ORE.get(), translations.getProperty("tile.ore_gneiss_rare.name"));
        builder.add(NtmBlocks.SCHIST_RARE_EARTH_ORE.get().asItem(), translations.getProperty("tile.ore_gneiss_rare.name"));
        builder.add(NtmBlocks.GAS_SHALE.get(), translations.getProperty("tile.ore_gneiss_gas.name"));
        builder.add(NtmBlocks.GAS_SHALE.get().asItem(), translations.getProperty("tile.ore_gneiss_gas.name"));
        builder.add(NtmBlocks.VOLCANIC_BASALT.get(), translations.getProperty("tile.basalt.name"));
        builder.add(NtmBlocks.VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.basalt.name"));
        builder.add(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT.get(), translations.getProperty("tile.ore_basalt_sulfur.name"));
        builder.add(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.ore_basalt_sulfur.name"));
        builder.add(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT.get(), translations.getProperty("tile.ore_basalt_fluorite.name"));
        builder.add(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.ore_basalt_fluorite.name"));
        builder.add(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT.get(), translations.getProperty("tile.ore_basalt_asbestos.name"));
        builder.add(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.ore_basalt_asbestos.name"));
        builder.add(NtmBlocks.GEM_RICH_VOLCANIC_BASALT.get(), translations.getProperty("tile.ore_basalt_gem.name"));
        builder.add(NtmBlocks.GEM_RICH_VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.ore_basalt_gem.name"));
        builder.add(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT.get(), translations.getProperty("tile.ore_basalt_molysite.name"));
        builder.add(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT.get().asItem(), translations.getProperty("tile.ore_basalt_molysite.name"));
        builder.add(NtmBlocks.NETHER_URANIUM_ORE.get(), translations.getProperty("tile.ore_nether_uranium.name"));
        builder.add(NtmBlocks.NETHER_URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_nether_uranium.name"));
        builder.add(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE.get(), translations.getProperty("tile.ore_nether_uranium_scorched.name"));
        builder.add(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE.get().asItem(), translations.getProperty("tile.ore_nether_uranium_scorched.name"));
        builder.add(NtmBlocks.NETHER_PLUTONIUM_ORE.get(), translations.getProperty("tile.ore_nether_plutonium.name"));
        builder.add(NtmBlocks.NETHER_PLUTONIUM_ORE.get().asItem(), translations.getProperty("tile.ore_nether_plutonium.name"));
        builder.add(NtmBlocks.NETHER_TUNGSTEN_ORE.get(), translations.getProperty("tile.ore_nether_tungsten.name"));
        builder.add(NtmBlocks.NETHER_TUNGSTEN_ORE.get().asItem(), translations.getProperty("tile.ore_nether_tungsten.name"));
        builder.add(NtmBlocks.NETHER_SULFUR_ORE.get(), translations.getProperty("tile.ore_nether_sulfur.name"));
        builder.add(NtmBlocks.NETHER_SULFUR_ORE.get().asItem(), translations.getProperty("tile.ore_nether_sulfur.name"));
        builder.add(NtmBlocks.NETHER_PHOSPHORUS_ORE.get(), translations.getProperty("tile.ore_nether_fire.name"));
        builder.add(NtmBlocks.NETHER_PHOSPHORUS_ORE.get().asItem(), translations.getProperty("tile.ore_nether_fire.name"));
        builder.add(NtmBlocks.NETHER_COBALT_ORE.get(), translations.getProperty("tile.ore_nether_cobalt.name"));
        builder.add(NtmBlocks.NETHER_COBALT_ORE.get().asItem(), translations.getProperty("tile.ore_nether_cobalt.name"));
        builder.add(NtmBlocks.NETHER_SCHRABIDIUM_ORE.get(), translations.getProperty("tile.ore_nether_schrabidium.name"));
        builder.add(NtmBlocks.NETHER_SCHRABIDIUM_ORE.get().asItem(), translations.getProperty("tile.ore_nether_schrabidium.name"));
        builder.add(NtmBlocks.SMOLDERING_NETHERRACK.get(), translations.getProperty("tile.ore_nether_smoldering.name"));
        builder.add(NtmBlocks.SMOLDERING_NETHERRACK.get().asItem(), translations.getProperty("tile.ore_nether_smoldering.name"));
        builder.add(NtmBlocks.NETHER_COAL_ORE.get(), translations.getProperty("tile.ore_nether_cobalt.name"));
        builder.add(NtmBlocks.NETHER_COAL_ORE.get().asItem(), translations.getProperty("tile.ore_nether_cobalt.name"));
        builder.add(NtmBlocks.METEORITE_BLOCK.get(), translations.getProperty("tile.block_meteor.name"));
        builder.add(NtmBlocks.METEORITE_BLOCK.get().asItem(), translations.getProperty("tile.block_meteor.name"));
        builder.add(NtmBlocks.BROKEN_METEORITE_BLOCK.get(), translations.getProperty("tile.block_meteor_broken.name"));
        builder.add(NtmBlocks.BROKEN_METEORITE_BLOCK.get().asItem(), translations.getProperty("tile.block_meteor_broken.name"));
        builder.add(NtmBlocks.METEORITE_COBBLESTONE.get(), translations.getProperty("tile.block_meteor_cobble.name"));
        builder.add(NtmBlocks.METEORITE_COBBLESTONE.get().asItem(), translations.getProperty("tile.block_meteor_cobble.name"));
        builder.add(NtmBlocks.HOT_METEORITE_COBBLESTONE.get(), translations.getProperty("tile.block_meteor_molten.name"));
        builder.add(NtmBlocks.HOT_METEORITE_COBBLESTONE.get().asItem(), translations.getProperty("tile.block_meteor_molten.name"));
        builder.add(NtmBlocks.METEORITE_TREASURE_BLOCK.get(), translations.getProperty("tile.block_meteor_treasure.name"));
        builder.add(NtmBlocks.METEORITE_TREASURE_BLOCK.get().asItem(), translations.getProperty("tile.block_meteor_treasure.name"));
        builder.add(NtmBlocks.METEOR_IRON_ORE.get(), translations.getProperty("tile.ore_meteor.iron.name"));
        builder.add(NtmBlocks.METEOR_IRON_ORE.get().asItem(), translations.getProperty("tile.ore_meteor.iron.name"));
        builder.add(NtmBlocks.METEOR_COPPER_ORE.get(), translations.getProperty("tile.ore_meteor.copper.name"));
        builder.add(NtmBlocks.METEOR_COPPER_ORE.get().asItem(), translations.getProperty("tile.ore_meteor.copper.name"));
        builder.add(NtmBlocks.METEOR_ALUMINIUM_ORE.get(), translations.getProperty("tile.ore_meteor.aluminium.name"));
        builder.add(NtmBlocks.METEOR_ALUMINIUM_ORE.get().asItem(), translations.getProperty("tile.ore_meteor.aluminium.name"));
        builder.add(NtmBlocks.METEOR_RARE_EARTH_ORE.get(), translations.getProperty("tile.ore_meteor.rareearth.name"));
        builder.add(NtmBlocks.METEOR_RARE_EARTH_ORE.get().asItem(), translations.getProperty("tile.ore_meteor.rareearth.name"));
        builder.add(NtmBlocks.METEOR_COBALT_ORE.get(), translations.getProperty("tile.ore_meteor.cobalt.name"));
        builder.add(NtmBlocks.METEOR_COBALT_ORE.get().asItem(), translations.getProperty("tile.ore_meteor.cobalt.name"));
        builder.add(NtmBlocks.DEPTH_ROCK.get(), translations.getProperty("tile.stone_depth.name"));
        builder.add(NtmBlocks.DEPTH_ROCK.get().asItem(), translations.getProperty("tile.stone_depth.name"));
        builder.add(NtmBlocks.DEPTH_CINNABAR_ORE.get(), translations.getProperty("tile.ore_depth_cinnebar.name"));
        builder.add(NtmBlocks.DEPTH_CINNABAR_ORE.get().asItem(), translations.getProperty("tile.ore_depth_cinnebar.name"));
        builder.add(NtmBlocks.DEPTH_ZIRCONIUM_ORE.get(), translations.getProperty("tile.ore_depth_zirconium.name"));
        builder.add(NtmBlocks.DEPTH_ZIRCONIUM_ORE.get().asItem(), translations.getProperty("tile.ore_depth_zirconium.name"));
        builder.add(NtmBlocks.DEPTH_BORAX_ORE.get(), translations.getProperty("tile.ore_depth_borax.name"));
        builder.add(NtmBlocks.DEPTH_BORAX_ORE.get().asItem(), translations.getProperty("tile.ore_depth_borax.name"));
        builder.add(NtmBlocks.DEPTH_IRON_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_depth_iron.name"));
        builder.add(NtmBlocks.DEPTH_IRON_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_depth_iron.name"));
        builder.add(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_depth_titanium.name"));
        builder.add(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_depth_titanium.name"));
        builder.add(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER.get(), translations.getProperty("tile.cluster_depth_tungsten.name"));
        builder.add(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER.get().asItem(), translations.getProperty("tile.cluster_depth_tungsten.name"));
        builder.add(NtmBlocks.ALEXANDRITE_ORE.get(), translations.getProperty("tile.ore_alexandrite.name"));
        builder.add(NtmBlocks.ALEXANDRITE_ORE.get().asItem(), translations.getProperty("tile.ore_alexandrite.name"));
        builder.add(NtmBlocks.NETHER_DEPTH_ROCK.get(), translations.getProperty("tile.stone_depth_nether.name"));
        builder.add(NtmBlocks.NETHER_DEPTH_ROCK.get().asItem(), translations.getProperty("tile.stone_depth_nether.name"));
        builder.add(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE.get(), translations.getProperty("tile.ore_depth_nether_neodymium.name"));
        builder.add(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE.get().asItem(), translations.getProperty("tile.ore_depth_nether_neodymium.name"));
        builder.add(NtmBlocks.OILY_SAND.get(), translations.getProperty("tile.sand_dirty.name"));
        builder.add(NtmBlocks.OILY_SAND.get().asItem(), translations.getProperty("tile.sand_dirty.name"));
        builder.add(NtmBlocks.RED_OILY_SAND.get(), translations.getProperty("tile.sand_dirty_red.name"));
        builder.add(NtmBlocks.RED_OILY_SAND.get().asItem(), translations.getProperty("tile.sand_dirty_red.name"));
        builder.add(NtmBlocks.OILY_DIRT.get(), translations.getProperty("tile.dirt_oily.name"));
        builder.add(NtmBlocks.OILY_DIRT.get().asItem(), translations.getProperty("tile.dirt_oily.name"));
        builder.add(NtmBlocks.DEAD_DIRT.get(), translations.getProperty("tile.dirt_dead.name"));
        builder.add(NtmBlocks.DEAD_DIRT.get().asItem(), translations.getProperty("tile.dirt_dead.name"));
    }

    private static String getFormated(@NonNull Properties translations, String formatKey, String... partsKey) {
        String format = translations.getProperty(formatKey);
        if (format == null) {
            return null;
        }

        String[] args = new String[partsKey.length];
        for (int i = 0; i < partsKey.length; i++) {
            String arg = translations.getProperty(partsKey[i]);
            if (arg == null) {
                return null;
            }
            args[i] = arg;
        }

        return format.formatted((Object[]) args);
    }

    public static String death(ResourceKey<DamageType> damageType) {
        return damageType.identifier().toLanguageKey("death.attack");
    }

    public static String deathPlayer(ResourceKey<DamageType> damageType) {
        return damageType.identifier().toLanguageKey("death.attack", "player");
    }

    public static String of(KeyMapping key) {
        return key.getName();
    }

    public static String of(ItemAbility ability) {
        return ability.getTranslationKey();
    }

    public static String of(ItemModifier modifier) {
        return modifier.getTranslationKey();
    }

    public static String of(NetworkType networkType) {
        return networkType.getTranslationKey();
    }

    public static String of(KeyMapping.Category category) {
        return category.id().toLanguageKey("key.category");
    }

    public static String tooltip11(RegistrySupplier<Item> item) {
        return NtmTranslations.tooltipKey11(item.get());
    }

    public static String tooltip11(RegistrySupplier<Item> item, int line) {
        return NtmTranslations.tooltipKey11(item.get(), line);
    }

    public static String tooltip(RegistrySupplier<Item> item) {
        return NtmTranslations.tooltipKey(item.get());
    }

    public static String tooltip(RegistrySupplier<Item> item, int line) {
        return NtmTranslations.tooltipKey(item.get(), line);
    }

    public void addDownloadedTranslations(@NonNull String languageCode, @NonNull TranslationBuilder builder) {
        Path cachePath = Path.of("lang-cache/" + languageCode + ".lang");
        File cacheFile = cachePath.toFile();

        if (cacheFile.exists()) {
            Ntm.LOGGER.info("found cache for translations ('{}')", languageCode);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
                String line = reader.readLine();
                if (line != null && line.startsWith("# From Commit: " + DOWNLOAD_COMMIT)) {

                    Properties translations = new Properties(7_000); // there are ~6500 translations in the original
                    translations.load(reader);

                    getTranslations(translations, (translationKey, value) -> {
                        try {
                            // This try catch takes care of fabric throwing exceptions when adding a translation key that already exits,
                            // and when trying to use a value of null when the original is missing a certain translation
                            builder.add(translationKey, value);
                        } catch (RuntimeException ignored) {
                        }
                    });
                    return;
                } else {
                    Ntm.LOGGER.info("cache for translations ('{}') is outdated, updating", languageCode);
                }
            } catch (IOException ignored) {
            }
        }

        URI uri = URI.create("https://raw.githubusercontent.com/HbmMods/Hbm-s-Nuclear-Tech-GIT/" + DOWNLOAD_COMMIT + "/src/main/resources/assets/hbm/lang/" + languageCode + ".lang");
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(uri.toURL().openStream())) {
            if (cacheFile.exists()) {
                boolean wasDeleted = cacheFile.delete();
                if (!wasDeleted) {
                    Ntm.LOGGER.error("Failed to delete download translations cache file at {}", cachePath.toAbsolutePath());
                    throw new RuntimeException("Failed to delete download translations cache file " + cachePath.toAbsolutePath());
                }
            }

            boolean madeDirs = cacheFile.getParentFile().mkdirs() || cacheFile.getParentFile().exists();
            boolean madeFile = cacheFile.createNewFile();
            if (!madeDirs || !madeFile) {
                Ntm.LOGGER.error("Failed to create download translations cache file at {}", cachePath.toAbsolutePath());
                throw new RuntimeException("Failed to create download translations cache file " + cachePath.toAbsolutePath());
            }

            try (FileOutputStream outputStream = new FileOutputStream(cacheFile)) {
                byte[] commitIdentifier = ("# From Commit: " + DOWNLOAD_COMMIT + "\n").getBytes();
                outputStream.write(commitIdentifier);
                long bytesTransferred = outputStream.getChannel().transferFrom(readableByteChannel, commitIdentifier.length, Long.MAX_VALUE);

                if (bytesTransferred < 50_000) {
                    // The smallest language file is currently fr_FR.lang, which as of the time of writing this is 69.541 bytes in size
                    Ntm.LOGGER.warn("Downloaded translations ('{}') are only {} bytes in size, something is probably gone wrong during download", languageCode, bytesTransferred);
                }
            }

            Properties translations = new Properties(7_000); // there are ~6500 translations in the original
            translations.load(new BufferedInputStream(new FileInputStream(cacheFile)));
            if (translations.isEmpty()) {
                Ntm.LOGGER.error("Downloaded translations ('{}') are empty, something has gone seriously wrong", languageCode);
                throw new RuntimeException("Downloaded translations ('" + languageCode + "') are empty");
            }

            getTranslations(translations, (translationKey, value) -> {
                try {
                    builder.add(translationKey, value);
                } catch (RuntimeException ignored) {
                }
            });

        } catch (IOException e) {
            Ntm.LOGGER.error("Failed to download translations ('{}'), please make sure your computer is connected to the internet and that github.com isn't down right now", languageCode);
            Ntm.LOGGER.error("Datagen will be canceled to prevent writing incomplete language files");
            throw new RuntimeException("Failed to download translations ('" + languageCode + "') from " + uri, e);
        }

    }
}
