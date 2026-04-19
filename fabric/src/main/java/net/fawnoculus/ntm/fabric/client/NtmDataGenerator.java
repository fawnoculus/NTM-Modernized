package net.fawnoculus.ntm.fabric.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.annotations.ClientOnly;
import net.fawnoculus.ntm.fabric.client.datagen.NtmAdvancementProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmDynamicRegistryProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmEnchantmentProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmModelProvider;
import net.fawnoculus.ntm.fabric.client.datagen.lang.*;
import net.fawnoculus.ntm.fabric.client.datagen.lang.legacy.*;
import net.fawnoculus.ntm.fabric.client.datagen.loot.NtmBlockLootProvider;
import net.fawnoculus.ntm.fabric.client.datagen.loot.NtmChestLootProvider;
import net.fawnoculus.ntm.fabric.client.datagen.loot.NtmEntityLootProvider;
import net.fawnoculus.ntm.fabric.client.datagen.recipes.NtmCraftingRecipeProvider;
import net.fawnoculus.ntm.fabric.client.datagen.recipes.NtmSmeltingRecipeProvider;
import net.fawnoculus.ntm.fabric.client.datagen.tags.NtmBlockTagProvider;
import net.fawnoculus.ntm.fabric.client.datagen.tags.NtmDamageTypeTagProvider;
import net.fawnoculus.ntm.fabric.client.datagen.tags.NtmEntityTypeTagProvider;
import net.fawnoculus.ntm.fabric.client.datagen.tags.NtmItemTagProvider;
import net.fawnoculus.ntm.world.NtmConfiguredFeatures;
import net.fawnoculus.ntm.world.NtmPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

@ClientOnly
public class NtmDataGenerator implements DataGeneratorEntrypoint {
    // Some data providers download stuff directly from the originals git repository, and parse it to our format
    // This is the specific commit from which said stuff will be downloaded from
    public static final String DOWNLOAD_COMMIT = "db43064ee2d0174b1f0b86d3faeaf97ab66a737c";

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(NtmDynamicRegistryProvider::new);

        pack.addProvider(NtmItemTagProvider::new);
        pack.addProvider(NtmBlockTagProvider::new);
        pack.addProvider(NtmEntityTypeTagProvider::new);
        pack.addProvider(NtmDamageTypeTagProvider::new);

        pack.addProvider(NtmBlockLootProvider::new);
        pack.addProvider(NtmChestLootProvider::new);
        pack.addProvider(NtmEntityLootProvider::new);

        pack.addProvider(NtmAdvancementProvider::new);

        pack.addProvider(NtmCraftingRecipeProvider::new);
        pack.addProvider(NtmSmeltingRecipeProvider::new);

        pack.addProvider(NtmEnchantmentProvider::new);

        pack.addProvider(NtmModelProvider::new);

        pack.addProvider(NtmEnglishLangProvider::new);
        pack.addProvider(NtmFrenchLangProvider::new);
        pack.addProvider(NtmGermanLangProvider::new);
        pack.addProvider(NtmItalianLangProvider::new);
        pack.addProvider(NtmPolishLangProvider::new);
        pack.addProvider(NtmRussianLangProvider::new);
        pack.addProvider(NtmUkrainianLangProvider::new);
        pack.addProvider(NtmChineseLangProvider::new);

        FabricDataGenerator.Pack legacyPack = fabricDataGenerator.createBuiltinResourcePack(Ntm.id("legacy"));
        legacyPack.addProvider(NtmLegacyEnglishLangProvider::new);
        legacyPack.addProvider(NtmLegacyFrenchLangProvider::new);
        legacyPack.addProvider(NtmLegacyGermanLangProvider::new);
        legacyPack.addProvider(NtmLegacyItalianLangProvider::new);
        legacyPack.addProvider(NtmLegacyPolishLangProvider::new);
        legacyPack.addProvider(NtmLegacyRussianLangProvider::new);
        legacyPack.addProvider(NtmLegacyUkrainianLangProvider::new);
        legacyPack.addProvider(NtmLegacyChineseLangProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, NtmConfiguredFeatures::init);
        registryBuilder.add(Registries.PLACED_FEATURE, NtmPlacedFeatures::init);
    }
}
