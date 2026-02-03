package net.fawnoculus.ntm.items.custom.container.energy;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InfiniteBatteryItem extends Item implements EnergyContainingItem {
    public InfiniteBatteryItem(Properties settings) {
        super(settings.stacksTo(1));
    }

    @Override
    public long getEnergy(ItemStack stack) {
        return Long.MAX_VALUE / 2;
    }

    @Override
    public void setEnergy(ItemStack stack, long energy) {
    }

    @Override
    public long getMaxEnergy(ItemStack stack) {
        return Long.MAX_VALUE;
    }

    @Override
    public long getChargeRate(ItemStack stack) {
        return Long.MAX_VALUE;
    }

    @Override
    public long getDischargeRate(ItemStack stack) {
        return Long.MAX_VALUE;
    }
}
