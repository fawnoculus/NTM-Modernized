package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.events.custom.OnMouseClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    private double xpos;

    @Shadow
    private double ypos;

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/FramerateLimitTracker;onInputReceived()V"), method = "onButton", cancellable = true)
    private void onMouseClicked(long window, MouseButtonInfo input, int action, CallbackInfo ci) {
        boolean isCanceled = OnMouseClickEvent.EVENT.invoker().onClick(this.minecraft, new MouseButtonEvent(this.xpos, this.ypos, input));
        if (isCanceled) {
            ci.cancel();
        }
    }
}
