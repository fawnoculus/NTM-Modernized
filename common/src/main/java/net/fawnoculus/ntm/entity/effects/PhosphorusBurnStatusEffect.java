package net.fawnoculus.ntm.entity.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

public class PhosphorusBurnStatusEffect extends MobEffect {
    public PhosphorusBurnStatusEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0 && duration > 0;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel world, LivingEntity entity, int amplifier) {
        entity.setRemainingFireTicks(20);
        return true;
    }
}
