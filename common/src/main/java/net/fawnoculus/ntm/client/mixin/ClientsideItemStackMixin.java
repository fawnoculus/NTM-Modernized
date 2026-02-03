package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.radiation.ClientHazmatRegistry;
import net.fawnoculus.ntm.client.api.radiation.ClientRadiationRegistry;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.items.custom.DangerousDrop;
import net.fawnoculus.ntm.items.custom.ExtraInfo;
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
      Item.TooltipContext context, TooltipDisplay displayComponent, @Nullable Player player, TooltipFlag type, Consumer<Component> tooltip, CallbackInfo ci
    ) {
        double milliRads = ClientRadiationRegistry.getRadioactivity(this.getItem());
        if (milliRads > 0) {
            tooltip.accept(Component.translatable("tooltip.ntm.radioactive").withStyle(ChatFormatting.GREEN));
            tooltip.accept(Component.translatable("generic.ntm.radiation.rad_s", milliRads / 1000.0).withStyle(ChatFormatting.YELLOW));
            if (this.count > 1) {
                tooltip.accept(Component.translatable("tooltip.ntm.stack_rads", milliRads * this.count / 1000.0).withStyle(ChatFormatting.YELLOW));
            }
        }
        double radiationResistance = ClientHazmatRegistry.getResistance(this.getItem());
        if (radiationResistance > 0) {
            tooltip.accept(Component.translatable("tooltip.ntm.radiation_resistance", radiationResistance).withStyle(ChatFormatting.YELLOW));
        }

        if (this.getItem() instanceof ExtraInfo extraInfo) {
            if (!NtmKeybinds.DISPLAY_EXTRA_INFO.isDown()) {
                tooltip.accept(extraInfo.getHelpText(NtmKeybinds.DISPLAY_EXTRA_INFO.getTranslatedKeyMessage()));
            } else {
                extraInfo.getInfo().forEach(tooltip);
            }
        }
        if (this.getItem() instanceof DangerousDrop) {
            tooltip.accept(Component.translatable("tooltip.ntm.dangerous_drop").withStyle(ChatFormatting.RED));
        }
    }
}
