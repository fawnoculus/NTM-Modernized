package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.radiation.ClientHazmatRegistry;
import net.fawnoculus.ntm.client.api.radiation.ClientRadiationRegistry;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.items.custom.DangerousDrop;
import net.fawnoculus.ntm.items.custom.ExtraInfo;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ClientsideItemStackMixin {
    @Shadow
    private int count;

    @Shadow
    public abstract Item getItem();

    @Inject(method = "addDetailsToTooltip", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/world/item/Item;appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V",
      shift = At.Shift.AFTER
    ))
    public void appendTooltip(
      Item.TooltipContext context, TooltipDisplay tooltipDisplay, @Nullable Player player, TooltipFlag tooltipFlag, Consumer<Component> tooltipAdder, CallbackInfo ci
    ) {
        double milliRads = ClientRadiationRegistry.getRadioactivity(this.getItem());
        if (milliRads > 0) {
            tooltipAdder.accept(Component.translatable(NtmTranslations.TOOLTIP_RADIOACTIVE).withStyle(ChatFormatting.GREEN));
            tooltipAdder.accept(Component.literal("%.1f ".formatted(milliRads / 1000.0)).append(Component.translatable(NtmTranslations.GENERIC_RAD_PER_SEC)).withStyle(ChatFormatting.YELLOW));
            if (this.count > 1) {
                tooltipAdder.accept(Component.translatable(NtmTranslations.TOOLTIP_STACK_RADS, milliRads * this.count / 1000.0).withStyle(ChatFormatting.YELLOW));
            }
        }
        double radiationResistance = ClientHazmatRegistry.getResistance(this.getItem());
        if (radiationResistance > 0) {
            tooltipAdder.accept(Component.translatable(NtmTranslations.TOOLTIP_RADIATION_RESISTANCE, radiationResistance).withStyle(ChatFormatting.YELLOW));
        }

        if (this.getItem() instanceof ExtraInfo extraInfo) {
            if (!NtmKeybinds.DISPLAY_EXTRA_INFO.isDown()) {
                tooltipAdder.accept(extraInfo.getHelpText(NtmKeybinds.DISPLAY_EXTRA_INFO.getTranslatedKeyMessage()));
            } else {
                extraInfo.getInfo().forEach(tooltipAdder);
            }
        }
        if (this.getItem() instanceof DangerousDrop) {
            tooltipAdder.accept(Component.translatable(NtmTranslations.TOOLTIP_DANGEROUS_DROP).withStyle(ChatFormatting.RED));
        }
    }
}
