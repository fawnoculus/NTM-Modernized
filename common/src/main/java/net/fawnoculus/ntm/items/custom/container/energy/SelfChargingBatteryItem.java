package net.fawnoculus.ntm.items.custom.container.energy;

import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class SelfChargingBatteryItem extends Item implements EnergyContainingItem {
    private final long ENERGY_PER_TICK;

    public SelfChargingBatteryItem(Properties settings, long energyPerTick) {
        super(settings.stacksTo(1));
        this.ENERGY_PER_TICK = energyPerTick;
    }

    @Override
    public long getEnergy(ItemStack stack) {
        return Long.MAX_VALUE;
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
        return 0;
    }

    @Override
    public long getDischargeRate(ItemStack stack) {
        return this.ENERGY_PER_TICK;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(NtmTextUtil.unit(this.ENERGY_PER_TICK, NtmTranslations.GENERIC_ENERGY_PER_TICK).withStyle(ChatFormatting.YELLOW));
    }
}
