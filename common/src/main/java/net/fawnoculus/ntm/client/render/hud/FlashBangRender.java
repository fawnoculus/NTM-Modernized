package net.fawnoculus.ntm.client.render.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.ARGB;

// HELP I CANNOT SEE
// Doesn't actually render flash-bangs, it used for large explosions
public class FlashBangRender {
    private static final int OVERDRAW = 50;
    private static float decay = 0f;
    private static float alpha = 0f;

    public static void addFlash(float newAlpha, float newDecay) {
        alpha = Math.max(newAlpha, alpha);
        decay = Math.max(newDecay, decay);
    }

    public static void flashBang(GuiGraphics guiGraphics, DeltaTracker tickCounter) {
        guiGraphics.fill(
          -OVERDRAW, -OVERDRAW,
          guiGraphics.guiWidth() + OVERDRAW + OVERDRAW, guiGraphics.guiHeight() + OVERDRAW + OVERDRAW,
          ARGB.colorFromFloat(Math.clamp(alpha, 0f, 1f),
            1f, 1f, 1f
          )
        );

        alpha -= decay * tickCounter.getGameTimeDeltaPartialTick(true);

        if (alpha <= 0) {
            alpha = 0;
            decay = 0;
        }
    }
}
