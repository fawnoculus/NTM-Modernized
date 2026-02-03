package net.fawnoculus.ntm.fabric.client.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.items.NtmItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmBlockLootProvider extends FabricBlockLootTableProvider {
    public NtmBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        add(NtmBlocks.URANIUM_ORE.get(), createOreDrop(NtmBlocks.URANIUM_ORE.get(), NtmItems.RAW_URANIUM.get()));
        add(NtmBlocks.DEEPSLATE_URANIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_URANIUM_ORE.get(), NtmItems.RAW_URANIUM.get()));
        add(NtmBlocks.SCORCHED_URANIUM_ORE.get(), createOreDrop(NtmBlocks.SCORCHED_URANIUM_ORE.get(), NtmItems.RAW_SCORCHED_URANIUM.get()));
        add(NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE.get(), NtmItems.RAW_SCORCHED_URANIUM.get()));
        add(NtmBlocks.TITANIUM_ORE.get(), createOreDrop(NtmBlocks.TITANIUM_ORE.get(), NtmItems.RAW_TITANIUM.get()));
        add(NtmBlocks.DEEPSLATE_TITANIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_TITANIUM_ORE.get(), NtmItems.RAW_TITANIUM.get()));
        add(NtmBlocks.SULFUR_ORE.get(), createOreDrop(NtmBlocks.SULFUR_ORE.get(), NtmItems.SULFUR.get()));
        add(NtmBlocks.DEEPSLATE_SULFUR_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_SULFUR_ORE.get(), NtmItems.SULFUR.get()));
        add(NtmBlocks.THORIUM_ORE.get(), createOreDrop(NtmBlocks.THORIUM_ORE.get(), NtmItems.RAW_THORIUM.get()));
        add(NtmBlocks.DEEPSLATE_THORIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_THORIUM_ORE.get(), NtmItems.RAW_THORIUM.get()));
        add(NtmBlocks.NITER_ORE.get(), createOreDrop(NtmBlocks.NITER_ORE.get(), NtmItems.NITER.get()));
        add(NtmBlocks.DEEPSLATE_NITER_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_NITER_ORE.get(), NtmItems.NITER.get()));
        add(NtmBlocks.TUNGSTEN_ORE.get(), createOreDrop(NtmBlocks.TUNGSTEN_ORE.get(), NtmItems.RAW_TUNGSTEN.get()));
        add(NtmBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), NtmItems.RAW_TUNGSTEN.get()));
        add(NtmBlocks.ALUMINIUM_BEARING_ORE.get(), createOreDrop(NtmBlocks.ALUMINIUM_BEARING_ORE.get(), NtmItems.RAW_CRYOLITE.get()));
        add(NtmBlocks.DEEPSLATE_ALUMINIUM_BEARING_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_ALUMINIUM_BEARING_ORE.get(), NtmItems.RAW_CRYOLITE.get()));
        add(NtmBlocks.FLUORITE_ORE.get(), createOreDrop(NtmBlocks.FLUORITE_ORE.get(), NtmItems.FLUORITE.get()));
        add(NtmBlocks.DEEPSLATE_FLUORITE_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_FLUORITE_ORE.get(), NtmItems.FLUORITE.get()));
        add(NtmBlocks.LEAD_ORE.get(), createOreDrop(NtmBlocks.LEAD_ORE.get(), NtmItems.RAW_LEAD.get()));
        add(NtmBlocks.DEEPSLATE_LEAD_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_LEAD_ORE.get(), NtmItems.RAW_LEAD.get()));
        add(NtmBlocks.SCHRABIDIUM_ORE.get(), createOreDrop(NtmBlocks.SCHRABIDIUM_ORE.get(), NtmItems.RAW_SCHRABIDIUM.get()));
        add(NtmBlocks.DEEPSLATE_SCHRABIDIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_SCHRABIDIUM_ORE.get(), NtmItems.RAW_SCHRABIDIUM.get()));
        add(NtmBlocks.BERYLLIUM_ORE.get(), createOreDrop(NtmBlocks.BERYLLIUM_ORE.get(), NtmItems.RAW_BERYLLIUM.get()));
        add(NtmBlocks.DEEPSLATE_BERYLLIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_BERYLLIUM_ORE.get(), NtmItems.RAW_BERYLLIUM.get()));
        add(NtmBlocks.AUSTRALIUM_ORE.get(), createOreDrop(NtmBlocks.AUSTRALIUM_ORE.get(), NtmItems.RAW_AUSTRALIUM.get()));
        add(NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE.get(), NtmItems.RAW_AUSTRALIUM.get()));
        add(NtmBlocks.RARE_EARTH_ORE.get(), createOreDrop(NtmBlocks.RARE_EARTH_ORE.get(), NtmItems.RARE_EARTH_ORE_CHUNK.get()));
        add(NtmBlocks.DEEPSLATE_RARE_EARTH_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_RARE_EARTH_ORE.get(), NtmItems.RARE_EARTH_ORE_CHUNK.get()));
        add(NtmBlocks.COBALT_ORE.get(), multiDrops(NtmBlocks.COBALT_ORE.get(), NtmItems.COBALT_FRAGMENT.get(), 4, 9));
        add(NtmBlocks.DEEPSLATE_COBALT_ORE.get(), multiDrops(NtmBlocks.DEEPSLATE_COBALT_ORE.get(), NtmItems.COBALT_FRAGMENT.get(), 4, 9));
        add(NtmBlocks.CINNEBAR_ORE.get(), createOreDrop(NtmBlocks.CINNEBAR_ORE.get(), NtmItems.CINNABAR.get()));
        add(NtmBlocks.DEEPSLATE_CINNEBAR_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_CINNEBAR_ORE.get(), NtmItems.CINNABAR.get()));
        add(NtmBlocks.COLTAN_ORE.get(), createOreDrop(NtmBlocks.COLTAN_ORE.get(), NtmItems.COLTAN.get()));
        add(NtmBlocks.DEEPSLATE_COLTAN_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_COLTAN_ORE.get(), NtmItems.COLTAN.get()));
        add(NtmBlocks.LIGNITE_ORE.get(), createOreDrop(NtmBlocks.LIGNITE_ORE.get(), NtmItems.LIGNITE.get()));
        add(NtmBlocks.DEEPSLATE_LIGNITE_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_LIGNITE_ORE.get(), NtmItems.LIGNITE.get()));
        add(NtmBlocks.ASBESTOS_ORE.get(), createOreDrop(NtmBlocks.ASBESTOS_ORE.get(), NtmItems.ASBESTOS_SHEET.get()));
        add(NtmBlocks.DEEPSLATE_ASBESTOS_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_ASBESTOS_ORE.get(), NtmItems.ASBESTOS_SHEET.get()));
        dropSelf(NtmBlocks.OIL_DEPOSIT.get());
        dropSelf(NtmBlocks.EMPTY_OIL_DEPOSIT.get());
        add(NtmBlocks.ALUMINIUM_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.ALUMINIUM_ORE_CLUSTER.get(), NtmItems.ALUMINIUM_CRYSTALS.get()));
        add(NtmBlocks.DEEPSLATE_ALUMINIUM_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEEPSLATE_ALUMINIUM_ORE_CLUSTER.get(), NtmItems.ALUMINIUM_CRYSTALS.get()));
        add(NtmBlocks.COPPER_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.COPPER_ORE_CLUSTER.get(), NtmItems.COPPER_CRYSTALS.get()));
        add(NtmBlocks.DEEPSLATE_COPPER_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEEPSLATE_COPPER_ORE_CLUSTER.get(), NtmItems.COPPER_CRYSTALS.get()));
        add(NtmBlocks.IRON_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.IRON_ORE_CLUSTER.get(), NtmItems.IRON_CRYSTALS.get()));
        add(NtmBlocks.DEEPSLATE_IRON_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEEPSLATE_IRON_ORE_CLUSTER.get(), NtmItems.IRON_CRYSTALS.get()));
        add(NtmBlocks.TITANIUM_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.TITANIUM_ORE_CLUSTER.get(), NtmItems.TITANIUM_CRYSTALS.get()));
        add(NtmBlocks.DEEPSLATE_TITANIUM_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEEPSLATE_TITANIUM_ORE_CLUSTER.get(), NtmItems.TITANIUM_CRYSTALS.get()));

        dropSelf(NtmBlocks.DEAD_DIRT.get());
        dropSelf(NtmBlocks.OILY_DIRT.get());
        dropSelf(NtmBlocks.OILY_SAND.get());

        dropSelf(NtmBlocks.DEPTH_ROCK.get());
        add(NtmBlocks.DEPTH_CINNABAR_ORE.get(), createOreDrop(NtmBlocks.DEPTH_CINNABAR_ORE.get(), NtmItems.CINNABAR.get()));
        add(NtmBlocks.DEPTH_ZIRCONIUM_ORE.get(), multiDrops(NtmBlocks.DEPTH_CINNABAR_ORE.get(), NtmItems.CINNABAR.get(), 2, 3));
        add(NtmBlocks.DEPTH_BORAX_ORE.get(), createOreDrop(NtmBlocks.DEPTH_BORAX_ORE.get(), NtmItems.BORAX_POWDER.get()));
        add(NtmBlocks.DEPTH_IRON_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEPTH_IRON_ORE_CLUSTER.get(), NtmItems.IRON_CRYSTALS.get()));
        add(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER.get(), NtmItems.TITANIUM_CRYSTALS.get()));
        add(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER.get(), createOreDrop(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER.get(), NtmItems.TUNGSTEN_CRYSTALS.get()));
        add(NtmBlocks.ALEXANDRITE_ORE.get(), createOreDrop(NtmBlocks.ALEXANDRITE_ORE.get(), NtmItems.ALEXANDRITE.get()));
        add(NtmBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), createOreDrop(NtmBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), NtmItems.ALEXANDRITE.get()));

        dropSelf(NtmBlocks.VOLCANIC_BASALT.get());
        add(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT.get(), createOreDrop(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT.get(), NtmItems.SULFUR.get()));
        add(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT.get(), createOreDrop(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT.get(), NtmItems.FLUORITE.get()));
        add(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT.get(), createOreDrop(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT.get(), NtmItems.ASBESTOS_SHEET.get()));
        add(NtmBlocks.GEM_RICH_VOLCANIC_BASALT.get(), createOreDrop(NtmBlocks.GEM_RICH_VOLCANIC_BASALT.get(), NtmItems.VOLCANIC_GEM.get()));
        add(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT.get(), createOreDrop(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT.get(), NtmItems.MOLYSITE.get()));

        add(NtmBlocks.SMOLDERING_NETHERRACK.get(), createOreDrop(NtmBlocks.SMOLDERING_NETHERRACK.get(), NtmItems.RED_PHOSPHORUS.get()));
        add(NtmBlocks.NETHER_COAL_ORE.get(), createOreDrop(NtmBlocks.NETHER_COAL_ORE.get(), NtmItems.INFERNAL_COAL.get()));
        add(NtmBlocks.NETHER_URANIUM_ORE.get(), createOreDrop(NtmBlocks.NETHER_URANIUM_ORE.get(), NtmItems.RAW_URANIUM.get()));
        add(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE.get(), createOreDrop(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE.get(), NtmItems.RAW_SCORCHED_URANIUM.get()));
        add(NtmBlocks.NETHER_PLUTONIUM_ORE.get(), createOreDrop(NtmBlocks.NETHER_PLUTONIUM_ORE.get(), NtmItems.RAW_PLUTONIUM.get()));
        add(NtmBlocks.NETHER_TUNGSTEN_ORE.get(), createOreDrop(NtmBlocks.NETHER_TUNGSTEN_ORE.get(), NtmItems.RAW_TUNGSTEN.get()));
        add(NtmBlocks.NETHER_SULFUR_ORE.get(), multiDrops(NtmBlocks.NETHER_SULFUR_ORE.get(), NtmItems.SULFUR.get(), 2, 4));
        add(NtmBlocks.NETHER_PHOSPHORUS_ORE.get(), chancedDrops(NtmBlocks.NETHER_PHOSPHORUS_ORE.get(), NtmItems.RED_PHOSPHORUS.get(), 4, NtmItems.WHITE_PHOSPHORUS_BAR.get(), 1));
        add(NtmBlocks.NETHER_COBALT_ORE.get(), multiDrops(NtmBlocks.NETHER_COBALT_ORE.get(), NtmItems.COBALT_FRAGMENT.get(), 4, 9));
        add(NtmBlocks.NETHER_SCHRABIDIUM_ORE.get(), createOreDrop(NtmBlocks.NETHER_SCHRABIDIUM_ORE.get(), NtmItems.RAW_SCHRABIDIUM.get()));

        dropSelf(NtmBlocks.NETHER_DEPTH_ROCK.get());
        add(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE.get(), multiDrops(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE.get(), NtmItems.NEODYMIUM_FRAGMENT.get(), 2, 3));

        // TODO: this
        dropOther(NtmBlocks.METEORITE_BLOCK.get(), NtmItems.NULL.get());
        dropOther(NtmBlocks.BROKEN_METEORITE_BLOCK.get(), NtmItems.NULL.get());
        dropOther(NtmBlocks.METEORITE_COBBLESTONE.get(), NtmItems.NULL.get());
        dropOther(NtmBlocks.METEORITE_TREASURE_BLOCK.get(), NtmItems.NULL.get());

        add(NtmBlocks.METEOR_IRON_ORE.get(), createOreDrop(NtmBlocks.METEOR_IRON_ORE.get(), NtmItems.RAW_METEORIC_IRON.get()));
        add(NtmBlocks.METEOR_COPPER_ORE.get(), createOreDrop(NtmBlocks.METEOR_COPPER_ORE.get(), NtmItems.RAW_METEORIC_COPPER.get()));
        add(NtmBlocks.METEOR_ALUMINIUM_ORE.get(), createOreDrop(NtmBlocks.METEOR_ALUMINIUM_ORE.get(), NtmItems.RAW_METEORIC_ALUMINIUM.get()));
        add(NtmBlocks.METEOR_RARE_EARTH_ORE.get(), createOreDrop(NtmBlocks.METEOR_RARE_EARTH_ORE.get(), NtmItems.RAW_METEORIC_RARE_EARTH.get()));
        add(NtmBlocks.METEOR_COBALT_ORE.get(), createOreDrop(NtmBlocks.METEOR_COBALT_ORE.get(), NtmItems.RAW_METEORIC_COBALT.get()));

        dropSelf(NtmBlocks.GRAPHITIC_SCHIST.get());
        add(NtmBlocks.SCHIST_IRON_ORE.get(), createOreDrop(NtmBlocks.SCHIST_IRON_ORE.get(), Items.RAW_IRON));
        add(NtmBlocks.SCHIST_GOLD_ORE.get(), createOreDrop(NtmBlocks.SCHIST_GOLD_ORE.get(), Items.RAW_GOLD));
        add(NtmBlocks.SCHIST_URANIUM_ORE.get(), createOreDrop(NtmBlocks.SCHIST_URANIUM_ORE.get(), NtmItems.RAW_URANIUM.get()));
        add(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE.get(), createOreDrop(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE.get(), NtmItems.RAW_SCORCHED_URANIUM.get()));
        add(NtmBlocks.SCHIST_COPPER_ORE.get(), createCopperOreDrops(NtmBlocks.SCHIST_COPPER_ORE.get()));
        add(NtmBlocks.SCHIST_ASBESTOS_ORE.get(), createOreDrop(NtmBlocks.SCHIST_ASBESTOS_ORE.get(), NtmItems.ASBESTOS_SHEET.get()));
        add(NtmBlocks.SCHIST_LITHIUM_ORE.get(), createOreDrop(NtmBlocks.SCHIST_LITHIUM_ORE.get(), NtmItems.NULL.get())); // TODO: think of something for this (maybe raw lithium?)
        add(NtmBlocks.SCHIST_SCHRABIDIUM_ORE.get(), createOreDrop(NtmBlocks.SCHIST_SCHRABIDIUM_ORE.get(), NtmItems.RAW_METEORIC_COBALT.get()));
        add(NtmBlocks.SCHIST_RARE_EARTH_ORE.get(), createOreDrop(NtmBlocks.SCHIST_RARE_EARTH_ORE.get(), NtmItems.RARE_EARTH_ORE_CHUNK.get()));
        dropSelf(NtmBlocks.GAS_SHALE.get());

        dropSelf(NtmBlocks.BAUXITE.get());
        dropSelf(NtmBlocks.CHRYSOTILE.get());
        dropSelf(NtmBlocks.HEMATITE.get());
        dropSelf(NtmBlocks.LIMESTONE.get());
        dropSelf(NtmBlocks.MALACHITE.get());
        dropSelf(NtmBlocks.SULFUROUS_STONE.get());

        dropSelf(NtmBlocks.TEKTITE.get());
        add(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE.get(), createOreDrop(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE.get(), NtmItems.RAW_OSMIRIDIUM_INFUSED_TEKTITE.get()));
        add(NtmBlocks.TRIXITE_ORE.get(), createOreDrop(NtmBlocks.TRIXITE_ORE.get(), NtmItems.RAW_TRIXITE.get()));


        dropSelf(NtmBlocks.ALLOY_FURNACE.get());
        dropSelf(NtmBlocks.ALLOY_FURNACE_EXTENSION.get());
        dropSelf(NtmBlocks.ELECTRIC_FURNACE.get());

        dropSelf(NtmBlocks.POTATO_BATTERY_BLOCK.get());
        dropSelf(NtmBlocks.ENERGY_STORAGE_BLOCK.get());
        dropSelf(NtmBlocks.LITHIUM_ION_ENERGY_STORAGE_BLOCK.get());
        dropSelf(NtmBlocks.SCHRABIDIUM_ENERGY_STORAGE_BLOCK.get());
        dropSelf(NtmBlocks.SPARK_ENERGY_STORAGE_BLOCK.get());
    }

    public LootTable.Builder multiDrops(Block block, ItemLike item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> impl = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(
          block,
          applyExplosionDecay(
            block,
            LootItem.lootTableItem(item)
              .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
          )
            .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
        );
    }

    public LootTable.Builder chancedDrops(Block block, ItemLike item, int chance, ItemLike item2, int chance2) {
        HolderLookup.RegistryLookup<Enchantment> impl = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(
          block,
          new EntryGroup.Builder(
            applyExplosionDecay(
              block,
              LootItem.lootTableItem(item).setWeight(chance)
            ).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE))),
            applyExplosionDecay(
              block,
              LootItem.lootTableItem(item2).setWeight(chance2)
            ).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
          )
        );
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Block-Loot Provider";
    }
}
