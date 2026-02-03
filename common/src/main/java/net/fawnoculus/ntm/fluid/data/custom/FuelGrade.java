package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.util.CodecUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum FuelGrade {
    LOW(Component.translatable("fluid_tooltip.ntm.fuel_grade.low").withStyle(ChatFormatting.RED)),
    MEDIUM(Component.translatable("fluid_tooltip.ntm.fuel_grade.medium").withStyle(ChatFormatting.RED)),
    HIGH(Component.translatable("fluid_tooltip.ntm.fuel_grade.high").withStyle(ChatFormatting.RED)),
    AVIATION(Component.translatable("fluid_tooltip.ntm.fuel_grade.aviation").withStyle(ChatFormatting.RED)),
    GASEOUS(Component.translatable("fluid_tooltip.ntm.fuel_grade.gaseous").withStyle(ChatFormatting.RED));

    public static final Codec<FuelGrade> CODEC = CodecUtil.getEnumCodec(FuelGrade.class);
    public final Component NAME;

    FuelGrade(Component name) {
        this.NAME = name;
    }
}
