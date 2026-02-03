package net.fawnoculus.ntm.entity.effects;

import net.fawnoculus.ntm.util.EntityUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

import java.util.Optional;

public class ExplosionStatusEffect extends MobEffect {
    public ExplosionStatusEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        // TODO: better explosion
        ExplosionDamageCalculator explosionBehavior = new SimpleExplosionDamageCalculator(true, true, Optional.empty(), Optional.empty());
        world.explode(
          null,
          EntityUtil.newDamageSource(world, DamageTypes.EXPLOSION),
          explosionBehavior,
          entity.getX(), entity.getY(), entity.getZ(),
          amplifier, false,
          Level.ExplosionInteraction.MOB
        );


        return super.applyEffectTick(world, entity, amplifier);
    }
}
