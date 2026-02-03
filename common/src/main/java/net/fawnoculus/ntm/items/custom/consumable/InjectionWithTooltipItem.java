package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class InjectionWithTooltipItem extends InjectionItem {
    private final int TOOLTIP_COUNT;

    public InjectionWithTooltipItem(Properties settings, RegistrySupplier<SoundEvent> sound, RegistrySupplier<Item> returnItem, BiConsumer<ServerLevel, LivingEntity> serverUse) {
        this(settings, 1, sound, returnItem, serverUse);
    }

    public InjectionWithTooltipItem(Properties settings, int tooltipCount, RegistrySupplier<SoundEvent> sound, RegistrySupplier<Item> returnItem, BiConsumer<ServerLevel, LivingEntity> serverUse) {
        super(settings, sound, returnItem, serverUse);

        this.TOOLTIP_COUNT = tooltipCount;
    }

    public InjectionWithTooltipItem(Properties settings, int tooltipCount, RegistrySupplier<SoundEvent> sound, List<RegistrySupplier<Item>> returnItems, BiConsumer<ServerLevel, LivingEntity> serverUse) {
        super(settings, sound, returnItems, serverUse);

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
