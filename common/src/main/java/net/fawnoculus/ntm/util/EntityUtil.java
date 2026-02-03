package net.fawnoculus.ntm.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class EntityUtil {
    @Contract("_, _ -> new")
    public static @NotNull DamageSource newDamageSource(Level world, ResourceKey<DamageType> damageTypeKey) {
        return newDamageSource(world, damageTypeKey, null, null);
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull DamageSource newDamageSource(@NotNull Level world, ResourceKey<DamageType> damageTypeKey, @Nullable Entity source, @Nullable Entity attacker) {
        DamageType damageType = world.registryAccess()
          .lookupOrThrow(Registries.DAMAGE_TYPE)
          .getValue(damageTypeKey);

        if (damageType == null) {
            damageType = world.registryAccess()
              .lookupOrThrow(Registries.DAMAGE_TYPE)
              .getValueOrThrow(DamageTypes.GENERIC);
        }

        Holder<DamageType> damageTypeEntry = world.registryAccess()
          .lookupOrThrow(Registries.DAMAGE_TYPE)
          .wrapAsHolder(damageType);

        return new DamageSource(damageTypeEntry, source, attacker);
    }

    public static void applyDamage(@NotNull Entity entity, ServerLevel serverWorld, ResourceKey<DamageType> damageTypeKey, float amount) {
        entity.hurtServer(serverWorld, newDamageSource(serverWorld, damageTypeKey), amount);
    }

    public static void removeNegativeEffects(@NotNull LivingEntity entity) {
        Collection<MobEffectInstance> effectInstances = entity.getActiveEffects();
        for (MobEffectInstance effectInstance : effectInstances) {
            if (!effectInstance.getEffect().value().isBeneficial()) {
                entity.removeEffect(effectInstance.getEffect());
            }
        }
    }

    public static void addEffectDuration(@NotNull LivingEntity entity, Holder<MobEffect> effect, int ticksToAdd) {
        Collection<MobEffectInstance> effectInstances = entity.getActiveEffects();
        boolean hasEffect = false;
        for (MobEffectInstance effectInstance : effectInstances) {
            if (effectInstance.getEffect() == effect) {
                entity.removeEffect(effectInstance.getEffect());
                entity.addEffect(new MobEffectInstance(
                  effect,
                  effectInstance.getDuration() + ticksToAdd,
                  effectInstance.getAmplifier(),
                  effectInstance.isAmbient(),
                  effectInstance.isVisible(),
                  effectInstance.showIcon())
                );
                hasEffect = true;
            }
        }

        if (!hasEffect) {
            entity.addEffect(new MobEffectInstance(effect, ticksToAdd, 0, false, false, true));
        }
    }
}
