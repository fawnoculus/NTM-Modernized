package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Range;

public record CoolingData(Double tuPerDroplet,
                          @Range(from = 0, to = 1) Double coolingMultiplier,
                          @Range(from = 0, to = 1) Double turbineMultiplier
) {
    public static final CoolingData DEFAULT = new CoolingData(0.0, 0.0, 0.0);
    public static final Codec<CoolingData> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.DOUBLE.fieldOf("tu_per_droplet").forGetter(CoolingData::tuPerDroplet),
        Codec.DOUBLE.fieldOf("boiling_multiplier").forGetter(CoolingData::coolingMultiplier),
        Codec.DOUBLE.fieldOf("heating_multiplier").forGetter(CoolingData::turbineMultiplier)
      ).apply(instance, CoolingData::new)
    );

    public boolean isCoolable() {
        return coolingMultiplier > 0;
    }

    public boolean isTurbineable() {
        return turbineMultiplier > 0;
    }
}
