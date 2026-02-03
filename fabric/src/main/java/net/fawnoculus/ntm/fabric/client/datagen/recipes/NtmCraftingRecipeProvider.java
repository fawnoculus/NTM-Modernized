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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class NtmCraftingRecipeProvider extends FabricRecipeProvider {
    public NtmCraftingRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                makeTools(NtmItems.STEEL_INGOT, NtmItems.STEEL_SWORD, NtmItems.STEEL_PICKAXE, NtmItems.STEEL_AXE, NtmItems.STEEL_SHOVEL, NtmItems.STEEL_HOE);
                makeTools(NtmItems.ADVANCED_ALLOY_INGOT, NtmItems.ADVANCED_ALLOY_SWORD, NtmItems.ADVANCED_ALLOY_PICKAXE, NtmItems.ADVANCED_ALLOY_AXE, NtmItems.ADVANCED_ALLOY_SHOVEL, NtmItems.ADVANCED_ALLOY_HOE);
                makeTools(NtmItems.TITANIUM_INGOT, NtmItems.TITANIUM_SWORD, NtmItems.TITANIUM_PICKAXE, NtmItems.TITANIUM_AXE, NtmItems.TITANIUM_SHOVEL, NtmItems.TITANIUM_HOE);
                makeTools(NtmItems.DESH_INGOT, NtmItems.DESH_SWORD, NtmItems.DESH_PICKAXE, NtmItems.DESH_AXE, NtmItems.DESH_SHOVEL, NtmItems.DESH_HOE);
                makeTools(NtmItems.COBALT_INGOT, NtmItems.COBALT_SWORD, NtmItems.COBALT_PICKAXE, NtmItems.COBALT_AXE, NtmItems.COBALT_SHOVEL, NtmItems.COBALT_HOE);
                makeTools(NtmItems.CMB_STEEL_INGOT, NtmItems.CMB_STEEL_SWORD, NtmItems.CMB_STEEL_PICKAXE, NtmItems.CMB_STEEL_AXE, NtmItems.CMB_STEEL_SHOVEL, NtmItems.CMB_STEEL_HOE);

                make3x3Reversible(NtmItems.ACTINIUM_227_INGOT, NtmBlocks.ACTINIUM_227_BLOCK);
                make3x3Reversible(NtmItems.ADVANCED_ALLOY_INGOT, NtmBlocks.ADVANCED_ALLOY_BLOCK);
                make3x3Reversible(NtmItems.ALUMINIUM_INGOT, NtmBlocks.ALUMINIUM_BLOCK);
                make3x3Reversible(NtmItems.ASBESTOS_SHEET, NtmBlocks.ASBESTOS_BLOCK);
                make3x3Reversible(NtmItems.AUSTRALIUM_INGOT, NtmBlocks.AUSTRALIUM_BLOCK);
                make3x3Reversible(NtmItems.BAKELITE_BAR, NtmBlocks.BAKELITE_BLOCK);
                make3x3Reversible(NtmItems.BERYLLIUM_INGOT, NtmBlocks.BERYLLIUM_BLOCK);
                make3x3Reversible(NtmItems.BISMUTH_INGOT, NtmBlocks.BISMUTH_BLOCK);
                make3x3Reversible(NtmItems.BORON_INGOT, NtmBlocks.BORON_BLOCK);
                make3x3Reversible(NtmItems.CADMIUM_INGOT, NtmBlocks.CADMIUM_BLOCK);
                make3x3Reversible(NtmItems.CADMIUM_STEEL_INGOT, NtmBlocks.CADMIUM_STEEL_BLOCK);
                make3x3Reversible(NtmItems.CMB_STEEL_INGOT, NtmBlocks.CMB_STEEL_BLOCK);
                make3x3Reversible(NtmItems.COAL_COKE, NtmBlocks.COAL_COKE_BLOCK);
                make3x3Reversible(NtmItems.COBALT_INGOT, NtmBlocks.COBALT_BLOCK);
                make3x3Reversible(NtmItems.COLTAN, NtmBlocks.COLTAN_BLOCK);
                make3x3Reversible(NtmItems.DESH_INGOT, NtmBlocks.DESH_BLOCK);
                make3x3Reversible(NtmItems.DINEUTRONIUM_INGOT, NtmBlocks.DINEUTRONIUM_BLOCK);
                make3x3Reversible(NtmItems.EUPHEMIUM_INGOT, NtmBlocks.EUPHEMIUM_BLOCK);
                make3x3Reversible(NtmItems.FERRIC_SCHARBIDATE_INGOT, NtmBlocks.FERRIC_SCHRABIDATE_BLOCK);
                make3x3Reversible(NtmItems.FLUORITE, NtmBlocks.FLUORITE_BLOCK);
                make3x3Reversible(NtmItems.GRAPHITE_INGOT, NtmBlocks.GRAPHITE_BLOCK);
                make3x3Reversible(NtmItems.HIGH_SPEED_STEEL_INGOT, NtmBlocks.HIGH_SPEED_STEEL_BLOCK);
                make3x3Reversible(NtmItems.LIGNITE_COKE, NtmBlocks.LIGNITE_COKE_BLOCK);
                make3x3Reversible(NtmItems.LEAD_INGOT, NtmBlocks.LEAD_BLOCK);
                make3x3Reversible(NtmItems.LITHIUM_CUBE, NtmBlocks.LITHIUM_BLOCK);
                make3x3Reversible(NtmItems.MAGNETIZED_TUNGSTEN_INGOT, NtmBlocks.MAGNETIZED_TUNGSTEN_BLOCK);
                make3x3Reversible(NtmItems.MOX_FUEL_INGOT, NtmBlocks.MOX_FUEL_BLOCK);
                make3x3Reversible(NtmItems.NEPTUNIUM_INGOT, NtmBlocks.NEPTUNIUM_BLOCK);
                make3x3Reversible(NtmItems.NIOBIUM_INGOT, NtmBlocks.NIOBIUM_BLOCK);
                make3x3Reversible(NtmItems.NITER, NtmBlocks.NITER_BLOCK);
                make3x3Reversible(NtmItems.PETROLEUM_COKE, NtmBlocks.PETROLEUM_COKE_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_INGOT, NtmBlocks.PLUTONIUM_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_FUEL_INGOT, NtmBlocks.PLUTONIUM_FUEL_BLOCK);
                make3x3Reversible(NtmItems.REACTOR_GRADE_PLUTONIUM_INGOT, NtmBlocks.REACTOR_GRADE_PLUTONIUM_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_238_INGOT, NtmBlocks.PLUTONIUM_238_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_239_INGOT, NtmBlocks.PLUTONIUM_239_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_240_INGOT, NtmBlocks.PLUTONIUM_240_BLOCK);
                make3x3Reversible(NtmItems.PLUTONIUM_241_INGOT, NtmBlocks.PLUTONIUM_241_BLOCK);
                make3x3Reversible(NtmItems.POLONIUM_210_INGOT, NtmBlocks.POLONIUM_210_BLOCK);
                make3x3Reversible(NtmItems.POLYMER_BAR, NtmBlocks.POLYMER_BLOCK);
                make3x3Reversible(NtmItems.RADIUM_226_INGOT, NtmBlocks.RADIUM_226_BLOCK);
                make3x3Reversible(NtmItems.RED_COPPER_INGOT, NtmBlocks.RED_COPPER_BLOCK);
                make3x3Reversible(NtmItems.RED_PHOSPHORUS, NtmBlocks.RED_PHOSPHORUS_BLOCK);
                make3x3Reversible(NtmItems.RUBBER_BAR, NtmBlocks.RUBBER_BLOCK);
                make3x3Reversible(NtmItems.SCHRABIDIUM_INGOT, NtmBlocks.SCHRABIDIUM_BLOCK);
                make3x3Reversible(NtmItems.SCHRABIDIUM_FUEL_INGOT, NtmBlocks.SCHRABIDIUM_FUEL_BLOCK);
                make3x3Reversible(NtmItems.SCHRARANIUM_INGOT, NtmBlocks.SCHRARANIUM_BLOCK);
                make3x3Reversible(NtmItems.SEMTEX_BAR, NtmBlocks.SEMTEX_BLOCK);
                make3x3Reversible(NtmItems.SOLINIUM_INGOT, NtmBlocks.SOLINIUM_BLOCK);
                make3x3Reversible(NtmItems.STARMETAL_INGOT, NtmBlocks.STARMETAL_BLOCK);
                make3x3Reversible(NtmItems.STEEL_INGOT, NtmBlocks.STEEL_BLOCK);
                make3x3Reversible(NtmItems.SULFUR, NtmBlocks.SULFUR_BLOCK);
                make3x3Reversible(NtmItems.TANTALUM_INGOT, NtmBlocks.TANTALUM_BLOCK);
                make3x3Reversible(NtmItems.TECHNETIUM_STEEL_INGOT, NtmBlocks.TECHNETIUM_STEEL_BLOCK);
                make3x3Reversible(NtmItems.THORIUM_232_INGOT, NtmBlocks.THORIUM_232_BLOCK);
                make3x3Reversible(NtmItems.THORIUM_FUEL_INGOT, NtmBlocks.THORIUM_FUEL_BLOCK);
                make3x3Reversible(NtmItems.TITANIUM_INGOT, NtmBlocks.TITANIUM_BLOCK);
                make3x3Reversible(NtmItems.TUNGSTEN_INGOT, NtmBlocks.TUNGSTEN_BLOCK);
                make3x3Reversible(NtmItems.URANIUM_INGOT, NtmBlocks.URANIUM_BLOCK);
                make3x3Reversible(NtmItems.URANIUM_FUEL_INGOT, NtmBlocks.URANIUM_FUEL_BLOCK);
                make3x3Reversible(NtmItems.URANIUM_233_INGOT, NtmBlocks.URANIUM_233_BLOCK);
                make3x3Reversible(NtmItems.URANIUM_235_INGOT, NtmBlocks.URANIUM_235_BLOCK);
                make3x3Reversible(NtmItems.URANIUM_238_INGOT, NtmBlocks.URANIUM_238_BLOCK);
                make3x3Reversible(NtmItems.WHITE_PHOSPHORUS_BAR, NtmBlocks.WHITE_PHOSPHORUS_BLOCK);
                make3x3Reversible(NtmItems.YELLOWCAKE, NtmBlocks.YELLOWCAKE_BLOCK);
                make3x3Reversible(NtmItems.ZIRCONIUM_CUBE, NtmBlocks.ZIRCONIUM_BLOCK);

                make3x3Reversible(NtmItems.ACTINIUM_227_NUGGET, NtmItems.ACTINIUM_227_INGOT);
                make3x3Reversible(NtmItems.AMERICIUM_241_NUGGET, NtmItems.AMERICIUM_241_INGOT);
                make3x3Reversible(NtmItems.AMERICIUM_242_NUGGET, NtmItems.AMERICIUM_242_INGOT);
                make3x3Reversible(NtmItems.AMERICIUM_FUEL_NUGGET, NtmItems.AMERICIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.REACTOR_GRADE_AMERICIUM_NUGGET, NtmItems.REACTOR_GRADE_AMERICIUM_INGOT);
                make3x3Reversible(NtmItems.ARSENIC_NUGGET, NtmItems.ARSENIC_INGOT);
                make3x3Reversible(NtmItems.AUSTRALIUM_NUGGET, NtmItems.AUSTRALIUM_INGOT);
                make3x3Reversible(NtmItems.BERYLLIUM_NUGGET, NtmItems.BERYLLIUM_INGOT);
                make3x3Reversible(NtmItems.BISMUTH_NUGGET, NtmItems.BISMUTH_INGOT);
                make3x3Reversible(NtmItems.COBALT_NUGGET, NtmItems.COBALT_INGOT);
                make3x3Reversible(NtmItems.COBALT_60_NUGGET, NtmItems.COBALT_60_INGOT);
                make3x3Reversible(NtmItems.DESH_NUGGET, NtmItems.DESH_INGOT);
                make3x3Reversible(NtmItems.DINEUTRONIUM_NUGGET, NtmItems.DINEUTRONIUM_INGOT);
                make3x3Reversible(NtmItems.EUPHEMIUM_NUGGET, NtmItems.EUPHEMIUM_INGOT);
                make3x3Reversible(NtmItems.GHIORSIUM_336_NUGGET, NtmItems.GHIORSIUM_336_INGOT);
                make3x3Reversible(NtmItems.GOLD_198_NUGGET, NtmItems.GOLD_198_INGOT);
                make3x3Reversible(NtmItems.LEAD_NUGGET, NtmItems.LEAD_INGOT);
                make3x3Reversible(NtmItems.LEAD_209_NUGGET, NtmItems.LEAD_209_INGOT);
                make3x3Reversible(NtmItems.MOX_FUEL_NUGGET, NtmItems.MOX_FUEL_INGOT);
                make3x3Reversible(NtmItems.NEPTUNIUM_NUGGET, NtmItems.NEPTUNIUM_INGOT);
                make3x3Reversible(NtmItems.NEPTUNIUM_FUEL_NUGGET, NtmItems.NEPTUNIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.NIOBIUM_NUGGET, NtmItems.NIOBIUM_INGOT);
                make3x3Reversible(NtmItems.OSMIRIDIUM_NUGGET, NtmItems.OSMIRIDIUM_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_NUGGET, NtmItems.PLUTONIUM_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_FUEL_NUGGET, NtmItems.PLUTONIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.REACTOR_GRADE_PLUTONIUM_NUGGET, NtmItems.REACTOR_GRADE_PLUTONIUM_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_238_NUGGET, NtmItems.PLUTONIUM_238_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_239_NUGGET, NtmItems.PLUTONIUM_239_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_240_NUGGET, NtmItems.PLUTONIUM_240_INGOT);
                make3x3Reversible(NtmItems.PLUTONIUM_241_NUGGET, NtmItems.PLUTONIUM_241_INGOT);
                make3x3Reversible(NtmItems.POLONIUM_210_NUGGET, NtmItems.POLONIUM_210_INGOT);
                make3x3Reversible(NtmItems.RADIUM_226_NUGGET, NtmItems.RADIUM_226_INGOT);
                make3x3Reversible(NtmItems.SCHRABIDIUM_NUGGET, NtmItems.SCHRABIDIUM_INGOT);
                make3x3Reversible(NtmItems.SCHRABIDIUM_FUEL_NUGGET, NtmItems.SCHRABIDIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_NUGGET, NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_NUGGET, NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.SILICON_NUGGET, NtmItems.SILICON_BOULE);
                make3x3Reversible(NtmItems.SOLINIUM_NUGGET, NtmItems.SOLINIUM_INGOT);
                make3x3Reversible(NtmItems.STRONTIUM_90_NUGGET, NtmItems.STRONTIUM_90_INGOT);
                make3x3Reversible(NtmItems.TANTALUM_NUGGET, NtmItems.TANTALUM_INGOT);
                make3x3Reversible(NtmItems.TECHNETIUM_99_NUGGET, NtmItems.TECHNETIUM_99_INGOT);
                make3x3Reversible(NtmItems.THORIUM_232_NUGGET, NtmItems.THORIUM_232_INGOT);
                make3x3Reversible(NtmItems.THORIUM_FUEL_NUGGET, NtmItems.THORIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.URANIUM_NUGGET, NtmItems.URANIUM_INGOT);
                make3x3Reversible(NtmItems.URANIUM_FUEL_NUGGET, NtmItems.URANIUM_FUEL_INGOT);
                make3x3Reversible(NtmItems.URANIUM_233_NUGGET, NtmItems.URANIUM_233_INGOT);
                make3x3Reversible(NtmItems.URANIUM_235_NUGGET, NtmItems.URANIUM_235_INGOT);
                make3x3Reversible(NtmItems.URANIUM_238_NUGGET, NtmItems.URANIUM_238_INGOT);
            }

            private void makeTools(
              Supplier<? extends ItemLike> material,
              Supplier<? extends ItemLike> sword,
              Supplier<? extends ItemLike> pickaxe,
              Supplier<? extends ItemLike> axe,
              Supplier<? extends ItemLike> shovel,
              Supplier<? extends ItemLike> hoe
            ) {
                this.shaped(RecipeCategory.TOOLS, sword.get(), 1)
                  .pattern(" M ")
                  .pattern(" M ")
                  .pattern(" S ")
                  .define('M', material.get())
                  .define('S', Items.STICK)
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter);
                this.shaped(RecipeCategory.TOOLS, pickaxe.get(), 1)
                  .pattern("MMM")
                  .pattern(" S ")
                  .pattern(" S ")
                  .define('M', material.get())
                  .define('S', Items.STICK)
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter);
                this.shaped(RecipeCategory.TOOLS, axe.get(), 1)
                  .pattern(" MM")
                  .pattern(" SM")
                  .pattern(" S ")
                  .define('M', material.get())
                  .define('S', Items.STICK)
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter);
                this.shaped(RecipeCategory.TOOLS, shovel.get(), 1)
                  .pattern(" M ")
                  .pattern(" S ")
                  .pattern(" S ")
                  .define('M', material.get())
                  .define('S', Items.STICK)
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter);
                this.shaped(RecipeCategory.TOOLS, hoe.get(), 1)
                  .pattern(" MM")
                  .pattern(" S ")
                  .pattern(" S ")
                  .define('M', material.get())
                  .define('S', Items.STICK)
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter);
            }

            private void make3x3Reversible(
              Supplier<? extends ItemLike> material,
              Supplier<? extends ItemLike> output
            ) {
                make3x3(material, output);
                this.shapeless(RecipeCategory.MISC, material.get(), 9)
                  .requires(output.get())
                  .unlockedBy(RecipeProvider.getHasName(output.get()), this.has(output.get()))
                  .save(exporter, material.get().asItem() + "_from_reverse_3x3");
            }

            private void make3x3(
              Supplier<? extends ItemLike> material,
              Supplier<? extends ItemLike> output
            ) {
                this.shaped(RecipeCategory.MISC, output.get(), 1)
                  .pattern("MMM")
                  .pattern("MMM")
                  .pattern("MMM")
                  .define('M', material.get())
                  .unlockedBy(RecipeProvider.getHasName(material.get()), this.has(material.get()))
                  .save(exporter, output.get().asItem() + "_from_3x3");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Crafting-Recipe Provider";
    }
}
