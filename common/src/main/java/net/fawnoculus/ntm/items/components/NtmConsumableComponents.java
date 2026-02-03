package net.fawnoculus.ntm.items.components;

import net.fawnoculus.ntm.entity.NtmStatusEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;

import java.util.List;

public class NtmConsumableComponents {
    public static final Consumable RAD_X = Consumables.defaultFood().consumeSeconds(0.8F)
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(NtmStatusEffects.RAD_X, 3600, 0, false, false, true)))
      .build();
    public static final Consumable IODINE_PILL = Consumables.defaultFood().consumeSeconds(0.8F)
      .onConsume(new ClearAllStatusEffectsConsumeEffect())
      .build();
    public static final Consumable IPECAC_SYRUP = Consumables.defaultFood().consumeSeconds(0.8F)
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 255, false, false, true)))
      .build();

    public static final Consumable BASIC_LEAD_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(NtmStatusEffects.LEAD_POISONING, 300, 2, false, false, true)))
      .build();
    public static final Consumable GOOD_LEAD_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(NtmStatusEffects.LEAD_POISONING, 1200, 4, false, false, true)))
      .build();
    public static final Consumable EPIC_LEAD_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(NtmStatusEffects.LEAD_POISONING, 1200, 255, false, false, true)))
      .build();

    public static final Consumable BASIC_SCHRABIDIUM_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.REGENERATION, 600, 4, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0, false, false, true),
        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0, false, false, true)
      )))
      .build();
    public static final Consumable GOOD_SCHRABIDIUM_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.SPEED, 1200, 2, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, 1200, 2, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, 1200, 4, false, false, true),
        new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 9, false, false, true),
        new MobEffectInstance(MobEffects.ABSORPTION, 1200, 4, false, false, true),
        new MobEffectInstance(MobEffects.SATURATION, 1200, 9, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 1200, 4, false, false, true),
        new MobEffectInstance(MobEffects.REGENERATION, 1200, 4, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, 1200, 4, false, false, true),
        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0, false, false, true)
      )))
      .build();
    public static final Consumable EPIC_SCHRABIDIUM_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.SPEED, MobEffectInstance.INFINITE_DURATION, 3, false, false, true),
        new MobEffectInstance(MobEffects.HASTE, MobEffectInstance.INFINITE_DURATION, 4, false, false, true),
        new MobEffectInstance(MobEffects.STRENGTH, MobEffectInstance.INFINITE_DURATION, 9, false, false, true),
        new MobEffectInstance(MobEffects.HEALTH_BOOST, MobEffectInstance.INFINITE_DURATION, 24, false, false, true),
        new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 14, false, false, true),
        new MobEffectInstance(MobEffects.SATURATION, MobEffectInstance.INFINITE_DURATION, 99, false, false, true),
        new MobEffectInstance(MobEffects.JUMP_BOOST, MobEffectInstance.INFINITE_DURATION, 4, false, false, true),
        new MobEffectInstance(MobEffects.REGENERATION, MobEffectInstance.INFINITE_DURATION, 4, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, MobEffectInstance.INFINITE_DURATION, 1, false, false, true),
        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 0, false, false, true)
      )))
      .build();
    public static final Consumable EUPHEMIUM_APPLE = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.SATURATION, MobEffectInstance.INFINITE_DURATION, 122, false, false, true),
        new MobEffectInstance(MobEffects.RESISTANCE, MobEffectInstance.INFINITE_DURATION, 122, false, false, true),
        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 0, false, false, true)
      )))
      .build();
    public static final Consumable VEGAN_SCHNITZEL = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.HUNGER, 3600, 4, false, false, true),
        new MobEffectInstance(MobEffects.WITHER, 60, 0, false, false, true),
        new MobEffectInstance(MobEffects.NAUSEA, 600, 0, false, false, true),
        new MobEffectInstance(MobEffects.BLINDNESS, 200, 0, false, false, true)
      )))
      .build();
    public static final Consumable RADIOACTIVE_COTTON_CANDY = Consumables.defaultFood()
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.SPEED, 750, 2, false, false, true),
        new MobEffectInstance(MobEffects.WEAKNESS, 750, 2, false, false, true),
        new MobEffectInstance(MobEffects.POISON, 300, 0, false, false, true),
        new MobEffectInstance(MobEffects.WITHER, 100, 0, false, false, true)
      )))
      .build();
    public static final Consumable ARIZONA_MUCHO_MANGO = Consumables.defaultDrink().consumeSeconds(10f).build();
    public static final Consumable RADIUM_CHOCOLATE = Consumables.defaultFood().consumeSeconds(0.8F)
      .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
        new MobEffectInstance(MobEffects.SPEED, 1200, 3),
        new MobEffectInstance(MobEffects.HASTE, 1200, 3),
        new MobEffectInstance(MobEffects.JUMP_BOOST, 1200, 3)
      )))
      .build();
}
