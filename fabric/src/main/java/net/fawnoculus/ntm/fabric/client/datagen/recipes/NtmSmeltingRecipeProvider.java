package net.fawnoculus.ntm.fabric.client.datagen.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.items.NtmItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class NtmSmeltingRecipeProvider extends FabricRecipeProvider {
    public NtmSmeltingRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                float xp = 1.0f;
                int time = 300;
                ore(xp, time, "uranium", NtmItems.URANIUM_INGOT, itemList(NtmItems.RAW_URANIUM, NtmBlocks.URANIUM_ORE, NtmBlocks.DEEPSLATE_URANIUM_ORE, NtmBlocks.NETHER_URANIUM_ORE, NtmBlocks.SCHIST_URANIUM_ORE));
                ore(xp, time, "scorched_uranium", NtmItems.URANIUM_INGOT, itemList(NtmItems.RAW_SCORCHED_URANIUM, NtmBlocks.SCORCHED_URANIUM_ORE, NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE, NtmBlocks.SCORCHED_NETHER_URANIUM_ORE, NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE));
                ore(xp, time, "australium", NtmItems.AUSTRALIUM_INGOT, itemList(NtmItems.RAW_AUSTRALIUM, NtmBlocks.AUSTRALIUM_ORE, NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE));
                ore(xp, time, "lead", NtmItems.LEAD_INGOT, itemList(NtmItems.RAW_LEAD, NtmBlocks.LEAD_ORE, NtmBlocks.DEEPSLATE_LEAD_ORE));
                ore(xp, time, "plutonium", NtmItems.PLUTONIUM_INGOT, itemList(NtmItems.RAW_PLUTONIUM, NtmBlocks.NETHER_PLUTONIUM_ORE));
                ore(xp, time, "thorium", NtmItems.THORIUM_232_INGOT, itemList(NtmItems.RAW_THORIUM, NtmBlocks.THORIUM_ORE, NtmBlocks.DEEPSLATE_THORIUM_ORE));
            }

            @SafeVarargs
            private List<ItemLike> itemList(Supplier<? extends ItemLike>... items) {
                List<ItemLike> list = new ArrayList<>(items.length);
                for (Supplier<? extends ItemLike> item : items) {
                    list.add(item.get());
                }
                return list;
            }

            private void ore(float xp, int time, String group, Supplier<? extends ItemLike> result, List<ItemLike> ores) {
                this.oreSmelting(
                  ores,
                  RecipeCategory.MISC,
                  result.get(),
                  xp,
                  time,
                  group
                );
                this.oreBlasting(
                  ores,
                  RecipeCategory.MISC,
                  result.get(),
                  xp,
                  time / 2,
                  group
                );
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Smelting-Recipe Provider";
    }
}
