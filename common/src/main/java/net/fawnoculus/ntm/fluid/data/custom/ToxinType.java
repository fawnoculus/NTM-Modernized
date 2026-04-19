package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmCodecUtil;
import net.minecraft.network.chat.Component;

public enum ToxinType {
    AIRBORNE_PARTICLES(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_AIRBORNE_PARTICLES)),
    PARTICULATES(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_PARTICULATES)),
    CHEMICAL_GAS(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_CHEMICAL_GAS)),
    CORROSIVE_FUMES(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_CORROSIVE_FUMES)),
    AEROSOLS(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_AEROSOLS)),
    CARBON_MONOXIDE(Component.translatable(NtmTranslations.FLUID_TOXIN_TYPE_CARBON_MONOXIDE));

    public static final Codec<ToxinType> CODEC = NtmCodecUtil.getEnumCodec(ToxinType.class);
    public final Component NAME;

    ToxinType(Component name) {
        this.NAME = name;
    }
}
