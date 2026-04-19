package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmCodecUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum FuelGrade {
    LOW(Component.translatable(NtmTranslations.FLUID_FUEL_GRADE_LOW).withStyle(ChatFormatting.RED)),
    MEDIUM(Component.translatable(NtmTranslations.FLUID_FUEL_GRADE_MEDIUM).withStyle(ChatFormatting.RED)),
    HIGH(Component.translatable(NtmTranslations.FLUID_FUEL_GRADE_HIGH).withStyle(ChatFormatting.RED)),
    AVIATION(Component.translatable(NtmTranslations.FLUID_FUEL_GRADE_AVIATION).withStyle(ChatFormatting.RED)),
    GASEOUS(Component.translatable(NtmTranslations.FLUID_FUEL_GRADE_GASEOUS).withStyle(ChatFormatting.RED));

    public static final Codec<FuelGrade> CODEC = NtmCodecUtil.getEnumCodec(FuelGrade.class);
    public final Component NAME;

    FuelGrade(Component name) {
        this.NAME = name;
    }
}
