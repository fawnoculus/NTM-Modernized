package net.fawnoculus.ntm.client.render.hud;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.client.api.radiation.ClientRadiationManager;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class GeigerCounterRender {
    private static final Identifier TEXTURE = Ntm.id("textures/gui/hud/geiger_counter.png");
    private static final int THRESHOLD_1 = 10_000;
    private static final int THRESHOLD_2 = 50_000;
    private static final int THRESHOLD_3 = 100_000;
    private static final int COLOR = ARGB.color(256, 256, 256);

    private static boolean shouldDraw() {
        return ClientUtil.hasPlayer() && PlayerUtil.hasItem(ClientUtil.getPlayer(), NtmItems.GEIGER_COUNTER);
    }

    public static void drawGeigerCounter(GuiGraphics guiGraphics, DeltaTracker ignored) {
        if (!shouldDraw()) return;

        final int x = 5;
        final int y = guiGraphics.guiHeight() - 20;


        final double radPercentage = ClientRadiationManager.radiationExposure / 1000000;
        final double incomingMilliRads = ClientRadiationManager.totalRadiation;

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, 94, 18, 128, 128);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 1, y + 1, 1, 19, (int) (radPercentage * 73), 16, 128, 128);

        if (incomingMilliRads >= THRESHOLD_1) {
            int u = 0;
            if (incomingMilliRads >= THRESHOLD_2) {
                u += 18;
            }
            if (incomingMilliRads >= THRESHOLD_3) {
                u += 18;
            }
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 76, y - 18, u, 36, 18, 18, 128, 128);
        }
        if (incomingMilliRads > 0) {
            String rads = String.valueOf(Math.round(incomingMilliRads / 1000.0));
            if (incomingMilliRads < 1000) {
                rads = "<1";
            }

            Font textRenderer = Minecraft.getInstance().font;
            Component text = Component.translatable("generic.ntm.radiation.rad_s", rads).withStyle(ChatFormatting.RED);
            guiGraphics.drawString(textRenderer, text, x + 2, y - textRenderer.lineHeight, COLOR, true);
        }
    }
}
