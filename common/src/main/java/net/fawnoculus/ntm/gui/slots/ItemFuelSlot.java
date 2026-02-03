package net.fawnoculus.ntm.gui.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jspecify.annotations.NonNull;

public class ItemFuelSlot extends Slot {
    final FuelValues FUEL_REGISTRY;

    public ItemFuelSlot(FuelValues fuelRegistry, Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.FUEL_REGISTRY = fuelRegistry;
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }

    @Override
    public boolean mayPlace(@NonNull ItemStack stack) {
        return this.FUEL_REGISTRY.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(@NonNull ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }
}
