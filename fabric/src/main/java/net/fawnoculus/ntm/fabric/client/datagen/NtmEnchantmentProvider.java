package net.fawnoculus.ntm.fabric.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmEnchantmentProvider extends FabricDynamicRegistryProvider {
    public NtmEnchantmentProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, @NonNull Entries entries) {
        HolderLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

    /*
    register(entries, NTMEnchantmentEffects.THUNDERING_KEY, Enchantment.builder(
        Enchantment.definition(
            itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
            10,
            3,
            Enchantment.leveledCost(1, 10),
            Enchantment.leveledCost(1, 15),
            5,
            AttributeModifierSlot.HAND
        )
    ).addEffect(
        EnchantmentEffectComponentTypes.POST_ATTACK,
        EnchantmentEffectTarget.ATTACKER,
        EnchantmentEffectTarget.VICTIM,
        new LightningEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.4f, 0.2f))
    ));
     */
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Enchantment Provider";
    }
}
