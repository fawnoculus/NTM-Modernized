package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Range;

public record HeatingData(Double tuPerDroplet,
                          @Range(from = 0, to = 1) Double boilingMultiplier,
                          @Range(from = 0, to = 1) Double heatingMultiplier,
                          @Range(from = 0, to = 1) Double pwrCoolantMultiplier,
                          @Range(from = 0, to = 1) Double icfCoolantMultiplier,
                          @Range(from = 0, to = 1) Double particleAcceleratorCoolantMultiplier
) {
    public static final HeatingData DEFAULT = new HeatingData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    public static final Codec<HeatingData> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.DOUBLE.fieldOf("tu_per_droplet").forGetter(HeatingData::tuPerDroplet),
        Codec.DOUBLE.fieldOf("boiling_multiplier").forGetter(HeatingData::boilingMultiplier),
        Codec.DOUBLE.fieldOf("heating_multiplier").forGetter(HeatingData::heatingMultiplier),
        Codec.DOUBLE.fieldOf("pwr_coolant_multiplier").forGetter(HeatingData::pwrCoolantMultiplier),
        Codec.DOUBLE.fieldOf("icf_coolant_multiplier").forGetter(HeatingData::icfCoolantMultiplier),
        Codec.DOUBLE.fieldOf("particle_accelerator_coolant_multiplier").forGetter(HeatingData::particleAcceleratorCoolantMultiplier)
      ).apply(instance, HeatingData::new)
    );

    public boolean isBoilable() {
        return this.boilingMultiplier > 0;
    }

    public boolean isHeatable() {
        return this.heatingMultiplier > 0;
    }

    public boolean isPwrCoolant() {
        return this.pwrCoolantMultiplier > 0;
    }

    public boolean isIcfCoolant() {
        return this.icfCoolantMultiplier > 0;
    }

    public boolean isParticleAcceleratorCoolant() {
        return this.particleAcceleratorCoolantMultiplier > 0;
    }
}
