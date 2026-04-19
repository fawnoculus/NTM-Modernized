package net.fawnoculus.ntm.items.custom.container.energy;

import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class SimpleBatteryItem extends Item implements EnergyContainingItem {
    private final long MAX_ENERGY;
    private final long CHARGE;
    private final long DISCHARGE;

    public SimpleBatteryItem(Properties settings, long maxEnergy, long energyPerTick) {
        this(settings, maxEnergy, energyPerTick, energyPerTick);
    }

    public SimpleBatteryItem(Properties settings, long maxEnergy, long chargeRate, long dischargeRate) {
        super(settings.stacksTo(1));
        this.MAX_ENERGY = maxEnergy;
        this.CHARGE = chargeRate;
        this.DISCHARGE = dischargeRate;
    }

    @Override
    public long getEnergy(ItemStack stack) {
        return stack.getOrDefault(NtmDataComponentTypes.ENERGY_COMPONENT_TYPE.get(), 0L);
    }

    @Override
    public void setEnergy(ItemStack stack, long energy) {
        stack.set(NtmDataComponentTypes.ENERGY_COMPONENT_TYPE.get(), energy);
    }

    @Override
    public long getMaxEnergy(ItemStack stack) {
        return this.MAX_ENERGY;
    }

    @Override
    public long getChargeRate(ItemStack stack) {
        return this.CHARGE;
    }

    @Override
    public long getDischargeRate(ItemStack stack) {
        return this.DISCHARGE;
    }

    @Override
    public boolean isBarVisible(@NonNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        float f = Math.max(0.0F, (float) this.getEnergyPercentage(stack) / 100);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public int getBarWidth(@NonNull ItemStack stack) {
        return (int) Math.clamp((double) this.getEnergy(stack) / (double) this.getMaxEnergy(stack) * 13, 0, 13);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        Component energy = NtmTextUtil.unit(getEnergy(stack));
        Component maxEnergy = NtmTextUtil.unit(getMaxEnergy(stack), NtmTranslations.GENERIC_ENERGY);
        Component chargeRate = NtmTextUtil.unit(getChargeRate(stack), NtmTranslations.GENERIC_ENERGY_PER_TICK);
        Component dischargeRate = NtmTextUtil.unit(getDischargeRate(stack), NtmTranslations.GENERIC_ENERGY_PER_TICK);
        tooltip.accept(Component.translatable(NtmTranslations.TOOLTIP_ENERGY_STORED, energy, maxEnergy).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable(NtmTranslations.TOOLTIP_ENERGY_CHARGE, chargeRate).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable(NtmTranslations.TOOLTIP_ENERGY_DISCHARGE, dischargeRate).withStyle(ChatFormatting.GRAY));
    }
}
