package net.fawnoculus.ntm.gui.slots;

import net.fawnoculus.ntm.items.custom.container.energy.EnergyContainingItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BatterySlot extends Slot {
    public BatterySlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() instanceof EnergyContainingItem;
    }
}
