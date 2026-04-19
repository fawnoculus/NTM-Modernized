package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmCodecUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum StateOfMatter {
    SOLID(Component.translatable(NtmTranslations.FLUID_STATE_SOLID).withStyle(ChatFormatting.BLUE)),
    LIQUID(Component.translatable(NtmTranslations.FLUID_STATE_LIQUID).withStyle(ChatFormatting.BLUE)),
    GASEOUS(Component.translatable(NtmTranslations.FLUID_STATE_GASEOUS).withStyle(ChatFormatting.BLUE)),
    PLASMA(Component.translatable(NtmTranslations.FLUID_STATE_PLASMA).withStyle(ChatFormatting.LIGHT_PURPLE));

    public static final Codec<StateOfMatter> CODEC = NtmCodecUtil.getEnumCodec(StateOfMatter.class);
    public final Component TOOLTIP;

    StateOfMatter(Component tooltip) {
        this.TOOLTIP = tooltip;
    }
}
