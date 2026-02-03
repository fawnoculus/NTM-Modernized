package net.fawnoculus.ntm.fabric.client.datagen.tags;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tags.NtmItemTags;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.items.NtmItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmItemTagProvider extends FabricTagProvider<Item> {
    public NtmItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    private static Identifier id(RegistrySupplier<? extends ItemLike> itemConvertible) {
        return BuiltInRegistries.ITEM.getKey(itemConvertible.get().asItem());
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        // Tool Materials
        getOrCreateRawBuilder(NtmItemTags.STEEL_TOOL_MATERIALS)
          .addElement(id(NtmItems.STEEL_INGOT));
        getOrCreateRawBuilder(NtmItemTags.TITANIUM_TOOL_MATERIALS)
          .addElement(id(NtmItems.TANTALUM_INGOT));
        getOrCreateRawBuilder(NtmItemTags.ADVANCED_ALLOY_TOOL_MATERIALS)
          .addElement(id(NtmItems.ADVANCED_ALLOY_INGOT));
        getOrCreateRawBuilder(NtmItemTags.CMB_STEEL_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.DESH_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.COBALT_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.DECORATED_COBALT_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.STARMETAL_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.SCHRABIDIUM_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.BISMUTH_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.MOLTEN_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.CHLOROPHYTE_TOOL_MATERIALS);
        getOrCreateRawBuilder(NtmItemTags.MESE_TOOL_MATERIALS);

        getOrCreateRawBuilder(NtmItemTags.URANIUM_ORES)
          .addElement(id(NtmBlocks.URANIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_URANIUM_ORE))
          .addElement(id(NtmBlocks.SCHIST_URANIUM_ORE))
          .addElement(id(NtmBlocks.NETHER_URANIUM_ORE))
          .addElement(id(NtmItems.RAW_URANIUM));

        getOrCreateRawBuilder(NtmItemTags.SCORCHED_URANIUM_ORES)
          .addElement(id(NtmBlocks.SCORCHED_URANIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE))
          .addElement(id(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE))
          .addElement(id(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE))
          .addElement(id(NtmItems.RAW_SCORCHED_URANIUM));

        getOrCreateRawBuilder(NtmItemTags.THORIUM_ORES)
          .addElement(id(NtmBlocks.THORIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_THORIUM_ORE))
          .addElement(id(NtmItems.RAW_THORIUM));

        getOrCreateRawBuilder(NtmItemTags.TITANIUM_ORES)
          .addElement(id(NtmBlocks.TITANIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_TITANIUM_ORE))
          .addElement(id(NtmItems.RAW_TITANIUM))
        ;
        getOrCreateRawBuilder(NtmItemTags.NITER_ORES)
          .addElement(id(NtmBlocks.NITER_ORE))
          .addElement(id(NtmBlocks.NITER_ORE));

        getOrCreateRawBuilder(NtmItemTags.SULFUR_ORES)
          .addElement(id(NtmBlocks.SULFUR_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_SULFUR_ORE))
          .addElement(id(NtmBlocks.NETHER_SULFUR_ORE))
          .addElement(id(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT));

        getOrCreateRawBuilder(NtmItemTags.TUNGSTEN_ORES)
          .addElement(id(NtmBlocks.TUNGSTEN_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_TUNGSTEN_ORE))
          .addElement(id(NtmBlocks.NETHER_TUNGSTEN_ORE));

        getOrCreateRawBuilder(NtmItemTags.CRYOLITE_ORES)
          .addElement(id(NtmBlocks.ALUMINIUM_BEARING_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_ALUMINIUM_BEARING_ORE))
          .addElement(id(NtmItems.RAW_CRYOLITE));

        getOrCreateRawBuilder(NtmItemTags.FLUORITE_ORES)
          .addElement(id(NtmBlocks.FLUORITE_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_FLUORITE_ORE))
          .addElement(id(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT));

        getOrCreateRawBuilder(NtmItemTags.BERYLLIUM_ORES)
          .addElement(id(NtmBlocks.BERYLLIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_BERYLLIUM_ORE))
          .addElement(id(NtmItems.RAW_BERYLLIUM));

        getOrCreateRawBuilder(NtmItemTags.LEAD_ORES)
          .addElement(id(NtmBlocks.LEAD_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_LEAD_ORE))
          .addElement(id(NtmItems.RAW_LEAD));

        getOrCreateRawBuilder(NtmItemTags.LIGNITE_ORES)
          .addElement(id(NtmBlocks.LIGNITE_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_LIGNITE_ORE));

        getOrCreateRawBuilder(NtmItemTags.ASBESTOS_ORES)
          .addElement(id(NtmBlocks.ASBESTOS_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_ASBESTOS_ORE))
          .addElement(id(NtmBlocks.SCHIST_ASBESTOS_ORE))
          .addElement(id(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT));

        getOrCreateRawBuilder(NtmItemTags.SCHRABIDIUM_ORES)
          .addElement(id(NtmBlocks.SCHRABIDIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_SCHRABIDIUM_ORE))
          .addElement(id(NtmBlocks.SCHIST_SCHRABIDIUM_ORE))
          .addElement(id(NtmBlocks.NETHER_SCHRABIDIUM_ORE))
          .addElement(id(NtmItems.RAW_SCHRABIDIUM));

        getOrCreateRawBuilder(NtmItemTags.AUSTRALIUM_ORES)
          .addElement(id(NtmBlocks.AUSTRALIUM_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE));

        getOrCreateRawBuilder(NtmItemTags.RARE_EARTH_ORES)
          .addElement(id(NtmBlocks.RARE_EARTH_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_RARE_EARTH_ORE))
          .addElement(id(NtmBlocks.SCHIST_RARE_EARTH_ORE));

        getOrCreateRawBuilder(NtmItemTags.COBALT_ORES)
          .addElement(id(NtmBlocks.COBALT_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_COBALT_ORE));

        getOrCreateRawBuilder(NtmItemTags.CINNABAR_ORES)
          .addElement(id(NtmBlocks.CINNEBAR_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_CINNEBAR_ORE));

        getOrCreateRawBuilder(NtmItemTags.COLTAN_ORES)
          .addElement(id(NtmBlocks.COLTAN_ORE))
          .addElement(id(NtmBlocks.DEEPSLATE_COLTAN_ORE));

        getOrCreateRawBuilder(NtmItemTags.PLUTONIUM_ORES)
          .addElement(id(NtmBlocks.NETHER_PLUTONIUM_ORE))
          .addElement(id(NtmItems.RAW_PLUTONIUM));

        getOrCreateRawBuilder(NtmItemTags.PHOSPHORUS_ORES)
          .addElement(id(NtmBlocks.NETHER_PHOSPHORUS_ORE));

        getOrCreateRawBuilder(NtmItemTags.TRIXITE_ORES)
          .addElement(id(NtmBlocks.TRIXITE_ORE))
          .addElement(id(NtmItems.RAW_TRIXITE));

        getOrCreateRawBuilder(NtmItemTags.OSMIRIDIUM_ORES)
          .addElement(id(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE))
          .addElement(id(NtmItems.RAW_METEORIC_IRON));

        getOrCreateRawBuilder(NtmItemTags.METEORIC_IRON_ORES)
          .addElement(id(NtmBlocks.METEOR_IRON_ORE))
          .addElement(id(NtmItems.RAW_METEORIC_IRON));

        getOrCreateRawBuilder(NtmItemTags.METEORIC_COPPER_ORES)
          .addElement(id(NtmBlocks.METEOR_COPPER_ORE))
          .addElement(id(NtmItems.RAW_METEORIC_COPPER));

        getOrCreateRawBuilder(NtmItemTags.METEORIC_ALUMINIUM_ORES)
          .addElement(id(NtmBlocks.METEOR_ALUMINIUM_ORE))
          .addElement(id(NtmItems.RAW_METEORIC_ALUMINIUM));

        getOrCreateRawBuilder(NtmItemTags.METEORIC_RARE_EARTH_ORES)
          .addElement(id(NtmBlocks.METEOR_RARE_EARTH_ORE))
          .addElement(id(NtmItems.RAW_METEORIC_RARE_EARTH));

        getOrCreateRawBuilder(NtmItemTags.METEORIC_COBALT_ORES)
          .addElement(id(NtmBlocks.METEOR_COBALT_ORE))
          .addElement(id(NtmItems.RAW_METEORIC_COBALT));

        // Ore Items
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Item-Tag Provider";
    }
}
