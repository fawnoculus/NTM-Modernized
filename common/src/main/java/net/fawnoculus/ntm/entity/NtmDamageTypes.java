package net.fawnoculus.ntm.entity;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class NtmDamageTypes {
    public static final ResourceKey<DamageType> EUTHANIZED = of("euthanized");
    public static final ResourceKey<DamageType> BLOOD_LOSS = of("blood_loss");
    public static final ResourceKey<DamageType> LEAD_POISONING = of("lead_poisoning");
    public static final ResourceKey<DamageType> RADIATION = of("radiation");

    private static ResourceKey<DamageType> of(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Ntm.id(name));
    }

    public static void init() {
    }
}
