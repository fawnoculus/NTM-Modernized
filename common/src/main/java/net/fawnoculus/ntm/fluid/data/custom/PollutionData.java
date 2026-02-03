package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.fluid.FluidUnit;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

// FIXME (once there is an Atmosphere &/or Pollution System)
public record PollutionData(String name, Double amount) {
    public static final Codec<PollutionData> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.STRING.fieldOf("name").forGetter(PollutionData::name),
        Codec.DOUBLE.fieldOf("amount").forGetter(PollutionData::amount)
      ).apply(instance, PollutionData::new)
    );

    public String getFluidName() {
        // TODO: make Pollution a Thing
        return this.name;
    }

    public @NotNull MutableComponent getTooltip() {
        return switch (NtmConfig.FLUID_UNIT.getValue()) {
            case NtmConfig.FluidUnit.MilliBuckets ->
              Component.translatable("fluid_tooltip.ntm.polluting.val", this.getFluidName(), this.amount() / FluidUnit.MILLI_BUCKET.DROPLETS)
                .append(Component.translatable("generic.ntm.fluid.mb"));
            case NtmConfig.FluidUnit.Droplets ->
              Component.translatable("fluid_tooltip.ntm.polluting.val", this.getFluidName(), this.amount())
                .append(Component.translatable("generic.ntm.fluid.droplets"));
        };
    }
}
