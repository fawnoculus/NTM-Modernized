package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.api.events.custom.ServerTickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Unique
    long ntm$tickStartNano = Long.MIN_VALUE;

    @Inject(at = @At("HEAD"), method = "tickChildren")
    private void preTick(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        ProfilerFiller profiler = Profiler.get();
        profiler.push("ntm_pre_tick_event");

        ntm$tickStartNano = System.nanoTime();
        ServerTickEvent.PRE_TICK.invoker().onTick((MinecraftServer) (Object) this, hasTimeLeft, ntm$tickStartNano);

        profiler.pop();
    }

    @Inject(at = @At("TAIL"), method = "tickChildren")
    private void postTick(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        ProfilerFiller profiler = Profiler.get();
        profiler.push("ntm_post_tick_event");

        if (ntm$tickStartNano == Long.MIN_VALUE) {
            return;
        }

        ServerTickEvent.POST_TICK.invoker().onTick((MinecraftServer) (Object) this, hasTimeLeft, ntm$tickStartNano);

        profiler.pop();
    }
}
