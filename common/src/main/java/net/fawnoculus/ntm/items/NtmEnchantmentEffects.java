package net.fawnoculus.ntm.items;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class NtmEnchantmentEffects {
    private static ResourceKey<Enchantment> of(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, Ntm.id(path));
    }

    private static <T extends EnchantmentEntityEffect> RegistrySupplier<MapCodec<T>> register(String id, MapCodec<T> codec) {
        return NtmDeferredRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPES.register(id, () -> codec);
    }

    //public static final RegistryKey<Enchantment> THUNDERING_KEY = of("thundering");
    //public static MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning_effect", LightningEnchantmentEffect.CODEC);

    public static void init() {
    }
}
