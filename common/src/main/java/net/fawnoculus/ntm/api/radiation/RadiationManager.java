package net.fawnoculus.ntm.api.radiation;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.api.radiation.processor.EmptyRadiationProcessor;
import net.fawnoculus.ntm.api.radiation.processor.RadiationProcessor;
import net.fawnoculus.ntm.api.radiation.processor.RadiationProcessorMultiHolder;
import net.fawnoculus.ntm.api.radiation.processor.SimpleRadiationProcessor;
import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.fawnoculus.ntm.entity.NtmStatusEffects;
import net.fawnoculus.ntm.misc.data.CustomDataHolder;
import net.fawnoculus.ntm.network.s2c.RadiationInformationPayload;
import net.fawnoculus.ntm.util.EntityUtil;
import net.fawnoculus.ntm.util.WorldUtil;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.entity.EntityTickList;
import net.minecraft.world.phys.Vec3;

/**
 * The Server Radiation Manager is run on the Logical Server (So it also Runs on Singleplayer Worlds).
 * <br>It Handles the active Radiation in All Loaded Chunks, Giving Players the effects for Radiation Exposure &AMP;
 * Send Update Packets to the Players that should Receive Radiation Information so that the ClientRadiationManager can give it to the Player
 */
public class RadiationManager {
    // Packets
    public static void sendPacket(ServerPlayer player) {
        NetworkManager.sendToPlayer(player, new RadiationInformationPayload(new RadiationInformationPayload.RadiationInfo(
          getRadiationExposure(player),
          getInventoryRadiation(player),
          getPassiveChunkRadiation(player.level(), player.position()),
          getActiveChunkRadiation(player.level(), player.position())
        )));
    }

    // Radiation Helpers
    public static double getRadiationExposure(LivingEntity entity) {
        CompoundTag entityData = CustomDataHolder.from(entity).ntm$getCustomData();
        return entityData.getDoubleOr("radiation_exposure", 0.0);
    }

    public static void setRadiationExposure(LivingEntity entity, double radiation) {
        CompoundTag entityData = CustomDataHolder.from(entity).ntm$getCustomData();
        entityData.putDouble("radiation_exposure", radiation);
    }

    public static double getRadiationResistance(LivingEntity entity) {
        double resistance = HazmatRegistry.getResistance(entity);
        if (entity.hasEffect(NtmStatusEffects.RAD_X)) {
            resistance += 0.2;
        }
        if (entity.hasEffect(NtmStatusEffects.TAINTED_HEART)) {
            // Gives 100% resistance to all radiation
            resistance += 1000;
        }
        return resistance;
    }

    public static double getRadiationResistancePercentage(LivingEntity entity) {
        return 100 - (getRadiationModifier(entity) * 100);
    }

    public static double getRadiationModifier(LivingEntity entity) {
        return Math.pow(10.0, -getRadiationResistance(entity));
    }

    // All Radiation values are in RAD/s not RAD/t so don't forget to divide by 20 when applying radiation per tick!
    public static double getInventoryRadiation(LivingEntity entity) {
        if (entity instanceof InventoryCarrier inventoryOwner) {
            return RadiationRegistry.getRadioactivity(inventoryOwner.getInventory());
        }
        if (entity instanceof Player player) {
            return RadiationRegistry.getRadioactivity(player.getInventory());
        }
        return 0;
    }

    public static double getActiveChunkRadiation(ServerLevel world, Vec3 pos) {
        if (world == null || pos == null) return 0;
        return getRadiationProcessor(world, pos).getActiveRadiation(pos);
    }

    public static double getPassiveChunkRadiation(ServerLevel world, Vec3 pos) {
        if (world == null || pos == null) return 0;
        return getRadiationProcessor(world, pos).getPassiveRadiation(pos);
    }

    public static double getChunkRadiation(ServerLevel world, Vec3 pos) {
        if (world == null || pos == null) return 0;
        return getActiveChunkRadiation(world, pos) + getPassiveChunkRadiation(world, pos);
    }

    public static double getTotalRadiation(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }

        if (entity.level() instanceof ServerLevel serverWorld) {
            return getChunkRadiation(serverWorld, entity.position()) + getInventoryRadiation(entity);
        }

        return 0;
    }

    public static RadiationProcessor getRadiationProcessor(ServerLevel world, Vec3 pos) {
        return getRadiationProcessor(world, WorldUtil.getChunkPos(pos));
    }

    public static RadiationProcessor getRadiationProcessor(ServerLevel world, Vec3i pos) {
        return getRadiationProcessor(world, WorldUtil.getChunkPos(pos));
    }

    public static RadiationProcessor getRadiationProcessor(ServerLevel world, ChunkPos pos) {
        RadiationProcessor radiationProcessor = RadiationProcessorMultiHolder.from(world).ntm$getRadiationProcessor(pos);
        if (radiationProcessor == null) {
            radiationProcessor = new EmptyRadiationProcessor();
        }
        return radiationProcessor;
    }

    // Radiation Modifiers
    public static void decreaseRadiationExposure(LivingEntity entity, double amount) {
        double radiationExposure = getRadiationExposure(entity);
        setRadiationExposure(entity, Math.clamp(radiationExposure - amount, 0, 1_000_000));
    }

    public static void increaseRadiationExposure(LivingEntity entity, double amount) {
        if (!(entity.level() instanceof ServerLevel serverWorld)) return;

        double radiationExposure = getRadiationExposure(entity);
        radiationExposure += amount;

        if (radiationExposure >= 1_000_000) {
            EntityUtil.applyDamage(entity, serverWorld, NtmDamageTypes.RADIATION, Float.MAX_VALUE);
            setRadiationExposure(entity, 0);
        } else {
            setRadiationExposure(entity, radiationExposure);
        }
    }

    // Radiation Ticking
    public static void tick(ServerLevel world, EntityTickList entityList) {
        for (RadiationProcessor processor : RadiationProcessorMultiHolder.from(world).ntm$getRadiationProcessors()) {
            processor.tick();
        }

        entityList.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                processEntityRadiation(livingEntity);
            }
        });

        world.players().forEach(RadiationManager::sendPacket);
        ;
    }

    public static void processEntityRadiation(LivingEntity entity) {
        if (NtmConfig.DISABLE_ENTITY_RADIATION.getValue()) return;
        if (entity.isInvulnerable() || entity.hasInfiniteMaterials()) return;
        if (!(entity.level() instanceof ServerLevel serverWorld)) return;
        increaseRadiationExposure(entity, getTotalRadiation(entity) * getRadiationModifier(entity) / 20.0);

        RandomSource random = serverWorld.getRandom();
        double radiationExposure = getRadiationExposure(entity);

        // Special Entity events
        switch (entity) {
            case Cow cow -> {
                if (radiationExposure >= 50) {
                    EntityType.MOOSHROOM.spawn(serverWorld, cow.blockPosition(), EntitySpawnReason.EVENT);
                    cow.setRemoved(Entity.RemovalReason.KILLED);
                }
            }
            case Creeper creeper -> {
                if (radiationExposure >= 200) {
          /* TODO: Nuclear Creeper
           if(random.nextInt(3) == 0){
             ModEntityType.NUCLEAR_CREEPER.spawn(serverWorld, creeper.getBlockPos(), SpawnReason.POST_SAVE);
           }
          */
                    creeper.setRemoved(Entity.RemovalReason.KILLED);
                }
            }
      /* TODO: duck & Quackatos
      case DuckEntity duck -> {
        if(radiationExposure >= 200){
          ModEntityType.QUACKATOS.spawn(serverWorld, duck.getBlockPos(), SpawnReason.POST_SAVE);
          duck.setRemoved(Entity.RemovalReason.KILLED);
        }
      }
       */
            case Villager villager -> {
                if (radiationExposure >= 500) {
                    EntityType.ZOMBIE.spawn(serverWorld, villager.blockPosition(), EntitySpawnReason.EVENT);
                    villager.setRemoved(Entity.RemovalReason.KILLED);
                }
            }
            default -> {
            }
        }

        // Regular Radiation Effects
        if (radiationExposure >= 1_000_000) {
            EntityUtil.applyDamage(entity, serverWorld, NtmDamageTypes.RADIATION, Float.MAX_VALUE);
            setRadiationExposure(entity, 0);
            return;
        }
        if (radiationExposure >= 800_000) {
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 5, 0, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 5, 2, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 5, 2, false, false, true));
            }
            if (random.nextInt(2500) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 5, 2, false, false, true));
            }
            if (random.nextInt(5000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 5, 1, false, false, true));
            }
            return;
        }
        if (radiationExposure >= 600_000) {
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 5, 0, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 5, 2, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 5, 2, false, false, true));
            }
            if (random.nextInt(15000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 5, 1, false, false, true));
            }
            return;
        }
        if (radiationExposure >= 400_000) {
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 5, 0, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 5, 1, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 5, 1, false, false, true));
            }
            return;
        }
        if (radiationExposure >= 200_000) {
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 5, 0, false, false, true));
            }
            if (random.nextInt(1000) == 0) {
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 5, 0, false, false, true));
            }
        }
    }

    public static RadiationProcessor makeNewRadiationProcessor(ServerLevel world, ChunkPos pos) {
        if (NtmConfig.DISABLE_CHUNK_RADIATION.getValue()) {
            return new EmptyRadiationProcessor();
        }
        return new SimpleRadiationProcessor(world, pos);
    }
}
