package net.fawnoculus.ntm.entity;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.entity.effects.*;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class NtmMobEffects {
    // TODO
    public static final RegistrySupplier<MobEffect> ASTOLFIZATION = of("astolfization",
      new LeadPoisoningMobEffect(MobEffectCategory.HARMFUL, color(55, 55, 60))
    );

    // TODO
    public static final RegistrySupplier<MobEffect> CONTAMINATED = of("contaminated",
      new GenericMobEffect(MobEffectCategory.HARMFUL, color(55, 55, 60))
    );

    public static final RegistrySupplier<MobEffect> EXPLOSION = of("explosion",
      new ExplosionMobEffect(MobEffectCategory.HARMFUL, color(55, 55, 60))
    );

    public static final RegistrySupplier<MobEffect> LEAD_POISONING = of("lead_poisoning",
      new LeadPoisoningMobEffect(MobEffectCategory.HARMFUL, color(55, 55, 60))
    );

    public static final RegistrySupplier<MobEffect> PHOSPHORUS_BURNS = of("phosphorus_burns",
      new PhosphorusBurnMobEffect(MobEffectCategory.HARMFUL, color(255, 100, 0))
    );

    // TODO
    public static final RegistrySupplier<MobEffect> POTION_SICKNESS = of("potion_sickness",
      new GenericMobEffect(MobEffectCategory.HARMFUL, color(55, 55, 60))
    );

    public static final RegistrySupplier<MobEffect> RAD_AWAY = of("rad_away",
      new RadAwayMobEffect(MobEffectCategory.BENEFICIAL, color(230, 75, 30))
    );

    public static final RegistrySupplier<MobEffect> RAD_X = of("rad_x",
      new GenericMobEffect(MobEffectCategory.BENEFICIAL, color(250, 100, 40))
    );

    // TODO
    public static final RegistrySupplier<MobEffect> STABILITY = of("stability",
      new GenericMobEffect(MobEffectCategory.BENEFICIAL, color(55, 55, 60))
    );

    public static final RegistrySupplier<MobEffect> TAINT = of("taint",
      new TaintMobEffect(MobEffectCategory.BENEFICIAL, color(120, 40, 200))
    );

    public static final RegistrySupplier<MobEffect> TAINTED_HEART = of("tainted_heart",
      new GenericMobEffect(MobEffectCategory.BENEFICIAL, color(200, 40, 75))
    );

    private static RegistrySupplier<MobEffect> of(String name, MobEffect effect) {
        return NtmDeferredRegistries.MOB_EFFECTS.register(Ntm.id(name), () -> effect);
    }

    private static int color(int red, int green, int blue) {
        return ARGB.color(red, green, blue);
    }

    public static void init() {
    }
}
