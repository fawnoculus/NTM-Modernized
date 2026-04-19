package net.fawnoculus.ntm.entity.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Used because the regular {@link MobEffect#MobEffect(MobEffectCategory, int) constructor} or MobEffect is protected
 */
public class GenericMobEffect extends MobEffect {
    // Used because the regular @link
    public GenericMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
