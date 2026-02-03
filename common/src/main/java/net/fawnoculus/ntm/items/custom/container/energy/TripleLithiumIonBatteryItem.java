package net.fawnoculus.ntm.items.custom.container.energy;

import net.fawnoculus.ntm.util.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class TripleLithiumIonBatteryItem extends SimpleBatteryItem {
    public TripleLithiumIonBatteryItem(Properties settings) {
        super(settings, 2_250_000L, 1_000L);
    }

    // Why couldn't HBM just slightly adjust the Max energy, then this goofy shit would not be necessary
    private static MutableComponent unitWithExtraDigit(long val) {
        if (val >= 1_000_000_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.e", String.format("%.2f", val / 1_000_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.p", String.format("%.2f", val / 1_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.t", String.format("%.2f", val / 1_000_000_000_000.0));
        }
        if (val >= 1_000_000_000L) {
            return Component.translatable("generic.ntm.unit.g", String.format("%.2f", val / 1_000_000_000.0));
        }
        if (val >= 1_000_000L) {
            return Component.translatable("generic.ntm.unit.m", String.format("%.2f", val / 1_000_000.0));
        }
        if (val >= 1_000L) {
            return Component.translatable("generic.ntm.unit.k", String.format("%.2f", val / 1_000.0));
        }
        return Component.literal(String.valueOf(val));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> tooltip, TooltipFlag type) {
        Component energy = TextUtil.unit(getEnergy(stack));
        Component maxEnergy = TextUtil.unit(getMaxEnergy(stack), "generic.ntm.energy");
        Component chargeRate = unitWithExtraDigit(getChargeRate(stack)).append(Component.translatable("generic.ntm.energy_t"));
        Component dischargeRate = TextUtil.unit(getDischargeRate(stack), "generic.ntm.energy_t");
        tooltip.accept(Component.translatable("tooltip.ntm.energy.stored", energy, maxEnergy).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.energy.charge", chargeRate).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.energy.discharge", dischargeRate).withStyle(ChatFormatting.GRAY));
    }
}
