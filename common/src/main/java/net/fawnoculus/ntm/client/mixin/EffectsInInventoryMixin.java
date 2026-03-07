package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.NtmClientConfig.EffectLevelFix;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EffectsInInventory.class)
public class EffectsInInventoryMixin {

    @Inject(at = @At("RETURN"), method = "getEffectName", cancellable = true)
    private void fixHighAmplifiers(MobEffectInstance statusEffect, CallbackInfoReturnable<Component> cir) {
        if (NtmClientConfig.FIX_EFFECT_LEVEL.getValue() == EffectLevelFix.NONE) {
            return;
        }

        if (NtmClientConfig.FIX_EFFECT_LEVEL.getValue() == EffectLevelFix.ALWAYS_DECIMAL) {
            cir.setReturnValue(statusEffect.getEffect().value().getDisplayName().copy()
              .append(CommonComponents.SPACE)
              .append(Integer.toString(statusEffect.getAmplifier())));
            return;
        }

        if(statusEffect.getAmplifier() <= 9 || !(cir.getReturnValue() instanceof MutableComponent returned)) {
            return;
        }

        if (NtmClientConfig.FIX_EFFECT_LEVEL.getValue() == EffectLevelFix.DECIMAL_IF_TO_BIG) {
            returned.append(CommonComponents.SPACE).append(Integer.toString(statusEffect.getAmplifier()));
            return;
        }

        if (NtmClientConfig.FIX_EFFECT_LEVEL.getValue() == EffectLevelFix.LARGE_ROMAN_NUMERALS || statusEffect.getAmplifier() <= 3999) {
            returned.append(CommonComponents.SPACE).append(NtmTextUtil.romanNumeral(statusEffect.getAmplifier()));
        }
    }
}
