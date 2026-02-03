package net.fawnoculus.ntm.entity.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class TaintEffect extends MobEffect {
    public TaintEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, @NonNull LivingEntity entity, int amplifier) {
        // TODO: this (I don't even know what this Effect does)
        return super.applyEffectTick(world, entity, amplifier);
    }
}
