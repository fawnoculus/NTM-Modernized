package net.fawnoculus.ntm.fabric.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fawnoculus.ntm.fabric.client.datagen.NtmAdvancementProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmEnchantmentProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmModelProvider;
import net.fawnoculus.ntm.fabric.client.datagen.NtmRegistryProvider;
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

@Environment(EnvType.CLIENT)
public class NtmDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(NtmRegistryProvider::new);

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
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, NtmConfiguredFeatures::init);
        registryBuilder.add(Registries.PLACED_FEATURE, NtmPlacedFeatures::init);
    }
}
