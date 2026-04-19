package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.NtmClientConfig;
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
    @Inject(method = "getEffectName", at = @At(value = "RETURN"), cancellable = true)
    private void fixHighAmplifiers(MobEffectInstance effect, CallbackInfoReturnable<Component> cir) {
        switch (NtmClientConfig.FIX_EFFECT_LEVEL.getValue()) {
            case LARGE_ROMAN_NUMERALS -> {
                if (effect.getAmplifier() > 9 && effect.getAmplifier() < 4000 && cir.getReturnValue() instanceof MutableComponent returned) {
                    returned.append(CommonComponents.SPACE).append(NtmTextUtil.romanNumeral(effect.getAmplifier()));
                }
            }
            case DECIMAL_IF_TO_BIG -> {
                if (effect.getAmplifier() > 9 && cir.getReturnValue() instanceof MutableComponent returned) {
                    returned.append(CommonComponents.SPACE).append(Integer.toString(effect.getAmplifier()));
                }
            }
            case ALWAYS_DECIMAL -> cir.setReturnValue(effect.getEffect().value().getDisplayName().copy()
              .append(CommonComponents.SPACE)
              .append(Integer.toString(effect.getAmplifier())));
        }
    }
}
