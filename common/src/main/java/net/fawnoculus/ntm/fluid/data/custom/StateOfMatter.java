package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.util.CodecUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum StateOfMatter {
    SOLID(Component.translatable("fluid_tooltip.ntm.state.solid").withStyle(ChatFormatting.BLUE)),
    LIQUID(Component.translatable("fluid_tooltip.ntm.state.liquid").withStyle(ChatFormatting.BLUE)),
    GASEOUS(Component.translatable("fluid_tooltip.ntm.state.gaseous").withStyle(ChatFormatting.BLUE)),
    PLASMA(Component.translatable("fluid_tooltip.ntm.state.plasma").withStyle(ChatFormatting.LIGHT_PURPLE));

    public static final Codec<StateOfMatter> CODEC = CodecUtil.getEnumCodec(StateOfMatter.class);
    public final Component TOOLTIP;

    StateOfMatter(Component tooltip) {
        this.TOOLTIP = tooltip;
    }
}
