package net.fawnoculus.ntm.gui.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class OutputSlot extends Slot {
    public OutputSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(@NonNull ItemStack stack) {
        return false;
    }
}
