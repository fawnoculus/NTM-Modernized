package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.events.custom.ClientTickEvents;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(at = @At("HEAD"), method = "runTick")
    private void updateAdvancedMessages(CallbackInfo ci) {
        ClientTickEvents.EVENT.invoker().onTick((Minecraft) (Object) this);
    }
}
