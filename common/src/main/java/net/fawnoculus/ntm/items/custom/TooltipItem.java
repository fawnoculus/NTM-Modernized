package net.fawnoculus.ntm.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class TooltipItem extends Item {
    private final int TOOLTIP_COUNT;

    public TooltipItem(Properties settings) {
        this(settings, 1);
    }

    public TooltipItem(Properties settings, int tooltipCount) {
        super(settings);

        this.TOOLTIP_COUNT = tooltipCount;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        if (this.TOOLTIP_COUNT == 1) {
            tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5)).withStyle(ChatFormatting.GRAY));
            return;
        }
        for (int i = 1; i <= this.TOOLTIP_COUNT; i++) {
            tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5) + i).withStyle(ChatFormatting.GRAY));
        }
    }
}
