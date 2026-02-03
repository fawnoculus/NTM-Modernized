package net.fawnoculus.ntm.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Collection;

public interface ExtraInfo {
    Collection<Component> getInfo();

    default Component getHelpText(Component extraInfoKeybind) {
        return Component.translatable("tooltip.ntm.hold_for_info", extraInfoKeybind).withStyle(ChatFormatting.DARK_GRAY);
    }
}
