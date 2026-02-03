package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

public record Combustible(@NotNull Boolean isCombustible, @NotNull Long ntePerDroplet, @NotNull FuelGrade fuelGrade) {
    public static final Combustible DEFAULT = new Combustible(false, 0L, FuelGrade.LOW);
    public static final Codec<Combustible> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.BOOL.fieldOf("is_combustible").forGetter(Combustible::isCombustible),
        Codec.LONG.fieldOf("nte_per_droplet").forGetter(Combustible::ntePerDroplet),
        FuelGrade.CODEC.fieldOf("fuel_grade").forGetter(Combustible::fuelGrade)
      ).apply(instance, Combustible::new)
    );
}
