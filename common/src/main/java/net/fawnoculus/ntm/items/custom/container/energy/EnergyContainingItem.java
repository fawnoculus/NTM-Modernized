package net.fawnoculus.ntm.items.custom.container.energy;

import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.minecraft.world.item.ItemStack;

public interface EnergyContainingItem {

    long getEnergy(ItemStack stack);

    void setEnergy(ItemStack stack, long energy);

    long getMaxEnergy(ItemStack stack);

    long getChargeRate(ItemStack stack);

    long getDischargeRate(ItemStack stack);

    /**
     * @param stack  the Stack to be Charged
     * @param energy the amount of energy to be inserted
     * @return the remaining energy that the item could not hold
     */
    default long charge(ItemStack stack, long energy) {
        long possibleCharge = this.getMaxEnergy(stack) - this.getEnergy(stack);
        if (possibleCharge > this.getChargeRate(stack)) {
            possibleCharge = this.getChargeRate(stack);
        }
        if (energy > possibleCharge) {
            this.setEnergy(stack, this.getEnergy(stack) + possibleCharge);
            return energy - possibleCharge;
        }
        this.setEnergy(stack, this.getEnergy(stack) + energy);
        return 0;
    }

    default void charge(ItemStack stack, NodeValueContainer container) {
        long energy = container.getValue();
        energy = this.charge(stack, energy);
        container.setValue(energy);
    }

    /**
     * @param stack  the Stack to be Discharged
     * @param energy the amount of energy to be removed
     * @return the remaining energy that could not be removed
     */
    default long discharge(ItemStack stack, long energy) {
        long possibleDischarge = this.getEnergy(stack);
        if (possibleDischarge > this.getDischargeRate(stack)) {
            possibleDischarge = this.getDischargeRate(stack);
        }
        if (energy > possibleDischarge) {
            this.setEnergy(stack, this.getEnergy(stack) - possibleDischarge);
            return energy - possibleDischarge;
        }
        this.setEnergy(stack, this.getEnergy(stack) - energy);
        return 0;
    }

    default void discharge(ItemStack stack, NodeValueContainer container) {
        long missingEnergy = container.getMaxValue() - container.getValue();
        missingEnergy = this.discharge(stack, missingEnergy);
        container.setValue(container.getMaxValue() - missingEnergy);
    }

    default double getEnergyPercentage(ItemStack stack) {
        return (double) this.getEnergy(stack) / (double) this.getMaxEnergy(stack) * 100;
    }
}
