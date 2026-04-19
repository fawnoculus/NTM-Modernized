package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Collection;

public interface ExtraInfo {
    Collection<Component> getInfo();

    default Component getHelpText(Component extraInfoKeybind) {
        return Component.translatable(NtmTranslations.TOOLTIP_HOLD_FOR_INFO, extraInfoKeybind).withStyle(ChatFormatting.DARK_GRAY);
    }
}
