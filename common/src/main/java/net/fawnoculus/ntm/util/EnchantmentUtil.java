package net.fawnoculus.ntm.util;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EnchantmentUtil {
    public static void addEnchantment(@NotNull Level world, ResourceKey<Enchantment> enchantmentKey, int level, @NotNull ItemStack stack) {
        Holder.Reference<Enchantment> enchantmentRegistryEntry = getEnchantmentEntry(world, enchantmentKey);
        stack.enchant(enchantmentRegistryEntry, level);
    }

    public static void removeEnchantment(ResourceKey<Enchantment> enchantmentKey, @NotNull ItemStack stack) {
        ItemEnchantments itemEnchantmentsComponent = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(itemEnchantmentsComponent);
        builder.removeIf(registryEntry -> registryEntry.is(enchantmentKey.identifier()));

        stack.set(DataComponents.ENCHANTMENTS, builder.toImmutable());
    }

    public static @NotNull Holder.Reference<Enchantment> getEnchantmentEntry(@NotNull Level world, @NotNull ResourceKey<Enchantment> enchantmentKey) {
        Optional<Holder.Reference<Enchantment>> enchantment = world.registryAccess()
          .lookupOrThrow(Registries.ENCHANTMENT)
          .get(enchantmentKey);

        if (enchantment.isPresent()) {
            return enchantment.get();
        }

        Optional<Holder.Reference<Enchantment>> optional = world.registryAccess()
          .lookupOrThrow(Registries.ENCHANTMENT)
          .getAny();
        if (optional.isEmpty()) {
            throw new IllegalStateException("Enchantment Registry does not have a default Value to fall back to");
        }
        return optional.get();
    }
}
