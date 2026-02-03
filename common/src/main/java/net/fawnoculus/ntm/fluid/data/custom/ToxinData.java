package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record ToxinData(@NotNull Boolean isToxic, ToxinType type, float damagePerSec,
                        @NotNull Optional<Identifier> damageID, @NotNull List<MobEffectInstance> effects) {
    public static final ToxinData DEFAULT = new ToxinData(false, ToxinType.CHEMICAL_GAS, 0, Optional.empty(), List.of());
    public static final Codec<ToxinData> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.BOOL.fieldOf("is_toxic").forGetter(ToxinData::isToxic),
        ToxinType.CODEC.fieldOf("type").forGetter(ToxinData::type),
        Codec.FLOAT.fieldOf("dps").forGetter(ToxinData::damagePerSec),
        Codec.optionalField("damageID", Identifier.CODEC, false).forGetter(ToxinData::damageID),
        Codec.list(MobEffectInstance.CODEC).fieldOf("effects").forGetter(ToxinData::effects)
      ).apply(instance, ToxinData::new)
    );

    public ToxinData(@NotNull Boolean isToxic, ToxinType type, float damagePerSec, Identifier damageID, @NotNull List<MobEffectInstance> effects) {
        this(isToxic, type, damagePerSec, Optional.ofNullable(damageID), effects);
    }

    public ResourceKey<DamageType> getDamageType() {
        return ResourceKey.create(Registries.DAMAGE_TYPE, this.damageID().orElse(DamageTypes.GENERIC.identifier()));
    }
}
