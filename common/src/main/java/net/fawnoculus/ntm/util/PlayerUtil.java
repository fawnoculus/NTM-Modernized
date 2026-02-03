package net.fawnoculus.ntm.util;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class PlayerUtil {
    public static void removeExperience(@NotNull Player player, int xp) {
        player.giveExperiencePoints(-xp);
    }

    public static boolean hasItem(Player player, @NotNull RegistrySupplier<? extends ItemLike> item) {
        return hasItem(player.getInventory(), item.get().asItem());
    }

    public static boolean hasItem(Player player, @NotNull ItemLike item) {
        return hasItem(player.getInventory(), item.asItem());
    }

    public static boolean hasItem(Iterable<ItemStack> stacks, ItemLike item) {
        return hasItem(stacks, item.asItem());
    }

    public static boolean hasItem(Iterable<ItemStack> stacks, Item item) {
        for (ItemStack stack : stacks) {
            if (stack.getItem() == item) {
                return true;
            }
        }

        return false;
    }
}
