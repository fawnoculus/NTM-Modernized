package net.fawnoculus.ntm.client.render;

import dev.architectury.registry.ReloadListenerRegistry;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.client.NtmClient;
import net.fawnoculus.ntm.client.api.events.custom.ResourceLoadingEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class NtmResources {
    public static void init() {
        ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, new ReloadListener(), Ntm.id("reload_listener"));

        if (!NtmPlatform.registerBuiltinResourcePack(Ntm.id("legacy"), Component.translatable("resourcePack.ntm_legacy.name"), NtmPlatform.PackActivationType.OFF_BY_DEFAULT)) {
            NtmClient.LOGGER.warn("Failed to register Legacy Resource Pack, it will not be available");
        }
    }

    private static class ReloadListener implements PreparableReloadListener {
        @Override
        public @NonNull CompletableFuture<Void> reload(
          @NonNull SharedState sharedState,
          @NonNull Executor prepareExecutor,
          @NonNull PreparationBarrier barrier,
          @NonNull Executor applyExectutor
        ) {
            return CompletableFuture.runAsync(() -> {
                    ProfilerFiller profiler = Profiler.get();
                    profiler.push("[NTM] Loading Wavefront Models");
                    NtmClient.LOGGER.info("Loading Wavefront Models");
                    ResourceLoadingEvent.LOAD_WAVEFRONT_MODELS.invoker().load();
                    profiler.pop();

                    profiler.push("[NTM] Loading Wavefront Model Textures");
                    NtmClient.LOGGER.info("Loading Wavefront Model Textures");
                    ResourceLoadingEvent.LOAD_WAVEFRONT_MODEL_TEXTURES.invoker().load();
                    profiler.pop();
                }, prepareExecutor
              )
              .thenCompose(barrier::wait)
              .thenRunAsync(() -> {
                }, applyExectutor
              );
        }

        @Override
        public @NonNull String getName() {
            return Ntm.MOD_NAME + " Reload-Listener";
        }
    }
}
