package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class TooltipItemP11 extends Item {
    private final int TOOLTIP_COUNT;

    public TooltipItemP11(Properties settings) {
        this(settings, 1);
    }

    public TooltipItemP11(Properties settings, int tooltipCount) {
        super(settings);

        this.TOOLTIP_COUNT = tooltipCount;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        if (NtmConfig.polaroidID == 11) {
            if (this.TOOLTIP_COUNT == 1) {
                tooltip.accept(NtmTextUtil.tooltip11(this).withStyle(ChatFormatting.GRAY));
                return;
            }

            for (int i = 1; i <= this.TOOLTIP_COUNT; i++) {
                tooltip.accept(NtmTextUtil.tooltip11(this, i).withStyle(ChatFormatting.GRAY));
            }
        }


        if (this.TOOLTIP_COUNT == 1) {
            tooltip.accept(NtmTextUtil.tooltip(this).withStyle(ChatFormatting.GRAY));
            return;
        }

        for (int i = 1; i <= this.TOOLTIP_COUNT; i++) {
            tooltip.accept(NtmTextUtil.tooltip(this, i).withStyle(ChatFormatting.GRAY));
        }
    }
}
