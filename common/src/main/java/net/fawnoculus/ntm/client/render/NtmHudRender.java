package net.fawnoculus.ntm.client.render;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.client.render.hud.*;
import net.minecraft.resources.Identifier;

public class NtmHudRender {
    public static final Identifier HUD_WIGGLER = Ntm.id("hud_wiggler");
    public static final Identifier TOLL_ABILITY_ID = Ntm.id("tool_ability");
    public static final Identifier MESSAGE_SYSTEM_ID = Ntm.id("message_system");
    public static final Identifier GEIGER_COUNTER_ID = Ntm.id("geiger_counter");
    public static final Identifier BLOCK_HOVER_TOOLTIP_ID = Ntm.id("block_hover_tooltip");
    public static final Identifier FLASH_BANG_ID = Ntm.id("flash_bang");

    public static void init() {
        NtmPlatform.addHudElementFirst(HUD_WIGGLER, HudWigglerRender::renderFlashBang);
        NtmPlatform.addHudElementAfter(NtmPlatform.VanillaHudElements.MISC_OVERLAYS, FLASH_BANG_ID, FlashBangRender::flashBang);
        NtmPlatform.addHudElementAfter(NtmPlatform.VanillaHudElements.CROSSHAIR, TOLL_ABILITY_ID, ToolAbilityRender::drawToolAbility);
        NtmPlatform.addHudElementLast(MESSAGE_SYSTEM_ID, MessageSystemRender::drawMessageSystem);
        NtmPlatform.addHudElementLast(GEIGER_COUNTER_ID, GeigerCounterRender::drawGeigerCounter);
        NtmPlatform.addHudElementLast(BLOCK_HOVER_TOOLTIP_ID, BlockHoverTooltipRender::drawBlockHoverTooltip);
    }
}
