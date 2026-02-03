package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.NtmClientConfig;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EffectsInInventory.class)
public class EffectsInInventoryMixin {

    @Inject(at = @At("RETURN"), method = "getEffectName")
    private void fixHighAmplifiers(MobEffectInstance statusEffect, CallbackInfoReturnable<Component> cir) {
        if (NtmClientConfig.FIX_EFFECT_LEVEL.getValue()
          && statusEffect.getAmplifier() > 9
          && cir.getReturnValue() instanceof MutableComponent mutableText
        ) {
            mutableText.append(" " + (statusEffect.getAmplifier() - 1));
        }
    }
}
