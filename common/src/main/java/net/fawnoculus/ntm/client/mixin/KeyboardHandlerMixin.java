package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.events.custom.OnKeyPressedEvent;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/FramerateLimitTracker;onInputReceived()V"), method = "keyPress", cancellable = true)
    private void onKeyPressed(long window, int action, KeyEvent input, CallbackInfo ci) {
        boolean isCanceled = OnKeyPressedEvent.EVENT.invoker().onKeyPressed(this.minecraft, input);
        if (isCanceled) {
            ci.cancel();
        }
    }
}
