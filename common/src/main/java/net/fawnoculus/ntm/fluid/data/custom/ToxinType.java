package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.util.CodecUtil;
import net.minecraft.network.chat.Component;

public enum ToxinType {
    AIRBORNE_PARTICLES(Component.translatable("fluid_tooltip.ntm.toxin_type.airborne_particles")),
    PARTICULATES(Component.translatable("fluid_tooltip.ntm.toxin_type.particulates")),
    CHEMICAL_GAS(Component.translatable("fluid_tooltip.ntm.toxin_type.chemical_gas")),
    CORROSIVE_FUMES(Component.translatable("fluid_tooltip.ntm.toxin_type.corrosive_fumes")),
    AEROSOLS(Component.translatable("fluid_tooltip.ntm.toxin_type.aerosols")),
    CARBON_MONOXIDE(Component.translatable("fluid_tooltip.ntm.toxin_type.carbon_monoxide"));

    public static final Codec<ToxinType> CODEC = CodecUtil.getEnumCodec(ToxinType.class);
    public final Component NAME;

    ToxinType(Component name) {
        this.NAME = name;
    }
}
