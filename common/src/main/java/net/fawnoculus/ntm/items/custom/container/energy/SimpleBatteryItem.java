package net.fawnoculus.ntm.items.custom.container.energy;

import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.fawnoculus.ntm.util.TextUtil;
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
        Component energy = TextUtil.unit(getEnergy(stack));
        Component maxEnergy = TextUtil.unit(getMaxEnergy(stack), "generic.ntm.energy");
        Component chargeRate = TextUtil.unit(getChargeRate(stack), "generic.ntm.energy_t");
        Component dischargeRate = TextUtil.unit(getDischargeRate(stack), "generic.ntm.energy_t");
        tooltip.accept(Component.translatable("tooltip.ntm.energy.stored", energy, maxEnergy).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.energy.charge", chargeRate).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.energy.discharge", dischargeRate).withStyle(ChatFormatting.GRAY));
    }
}
