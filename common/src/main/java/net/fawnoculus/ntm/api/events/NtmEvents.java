package net.fawnoculus.ntm.api.events;

import dev.architectury.event.events.common.ChunkEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.config.PerWorldConfigFile;
import net.fawnoculus.ntm.api.events.custom.EarlyPlayerJoinEvent;
import net.fawnoculus.ntm.api.events.custom.LevelChunkSaveEvent;
import net.fawnoculus.ntm.api.events.custom.ServerTickEvent;
import net.fawnoculus.ntm.api.explosion.NtmExplosionSystem;
import net.fawnoculus.ntm.api.radiation.HazmatRegistry;
import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.fawnoculus.ntm.api.radiation.RadiationRegistry;
import net.fawnoculus.ntm.api.radiation.processor.RadiationProcessor;
import net.fawnoculus.ntm.api.radiation.processor.RadiationProcessorHolder;
import net.fawnoculus.ntm.api.radiation.processor.RadiationProcessorMultiHolder;
import net.fawnoculus.ntm.fluid.data.FluidDataRegistry;
import net.fawnoculus.ntm.misc.data.CustomDataHolder;
import net.fawnoculus.ntm.mixin.accessor.MinecraftServerAccessor;
import net.fawnoculus.ntm.network.s2c.FluidDataRegistryPayload;
import net.fawnoculus.ntm.network.s2c.HazmatRegistryPayload;
import net.fawnoculus.ntm.network.s2c.NtmVersionPayload;
import net.fawnoculus.ntm.network.s2c.RadiationRegistryPayload;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.chunk.LevelChunk;

import java.nio.file.Path;

public class NtmEvents {
    public static void init() {
        ChunkEvent.SAVE_DATA.register((chunk, level, data) -> {
            CompoundTag customData = CustomDataHolder.from(chunk).ntm$getCustomData();
            if (level != null && chunk instanceof LevelChunk levelChunk) {
                LevelChunkSaveEvent.SAVE.invoker().onSaveEvent(level, levelChunk, customData);
            }
            CustomDataHolder.from(data).ntm$setCustomData(customData);
        });

        ChunkEvent.LOAD_DATA.register((chunk, level, data) -> {
            CompoundTag customData = CustomDataHolder.from(data).ntm$getCustomData();
            CustomDataHolder.from(chunk).ntm$setCustomData(customData);
            if (level == null || !(chunk instanceof LevelChunk levelChunk)) {
                return;
            }
            LevelChunkSaveEvent.LOAD.invoker().onSaveEvent(level, levelChunk, customData);

        });

        PlayerEvent.PLAYER_JOIN.register(player -> NetworkManager.sendToPlayer(player, new RadiationRegistryPayload(RadiationRegistry.serialize())));
        PlayerEvent.PLAYER_JOIN.register(player -> NetworkManager.sendToPlayer(player, new HazmatRegistryPayload(HazmatRegistry.serialize())));
        PlayerEvent.PLAYER_JOIN.register(player -> NetworkManager.sendToPlayer(player, new FluidDataRegistryPayload(FluidDataRegistry.encodeAllFluidData())));

        EarlyPlayerJoinEvent.EVENT.register((connection, player, cookie) ->
          NetworkManager.sendToPlayer(player, new NtmVersionPayload(Ntm.MOD_VERSION))
        );

        LifecycleEvent.SERVER_BEFORE_START.register(server -> {
            @SuppressWarnings("all") // Ignore the warning about LevelStorage.Session being an auto closable
            Path worldConfigDir = ((MinecraftServerAccessor) server).ntm$getStorageSource().getLevelDirectory().path().resolve("data");

            for (PerWorldConfigFile configFile : PerWorldConfigFile.getPerWorldConfigFiles()) {
                configFile.setWorldPath(worldConfigDir);
            }
        });

        LifecycleEvent.SERVER_STOPPED.register(ignored -> {
            PerWorldConfigFile.getPerWorldConfigFiles().forEach(PerWorldConfigFile::removeWorldPath);
        });

        ServerTickEvent.POST_TICK.register((server, shouldKeepTicking, tickStartNanoTime) -> {
            if (!server.tickRateManager().runsNormally()) {
                return;
            }

            long nanosPerTick = (long) Math.floor(server.tickRateManager().tickrate() * 20);

            NtmExplosionSystem.processExplosions(nanosPerTick - (tickStartNanoTime - System.nanoTime()));
        });

        LevelChunkSaveEvent.SAVE.register((level, levelChunk, compoundTag) -> {
            RadiationProcessorHolder.from(levelChunk).ntm$getRadiationProcessor().writeData(compoundTag);
            RadiationProcessorMultiHolder.from(level).ntm$removeRadiationProcessor(levelChunk.getPos());
        });

        LevelChunkSaveEvent.LOAD.register((level, levelChunk, compoundTag) -> {
            RadiationProcessor radiationProcessor = RadiationManager.makeNewRadiationProcessor(level, levelChunk.getPos());
            radiationProcessor.readData(compoundTag);
            RadiationProcessorHolder.from(levelChunk).ntm$setRadiationProcessor(radiationProcessor);
            RadiationProcessorMultiHolder.from(level).ntm$addRadiationProcessor(radiationProcessor, levelChunk.getPos());
        });
    }
}
