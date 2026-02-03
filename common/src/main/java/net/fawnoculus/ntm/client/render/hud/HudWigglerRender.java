package net.fawnoculus.ntm.client.render.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public class HudWigglerRender {
    private static final float MOMENTUM_LOSS = 0.1F;
    private static final float SPRING_NESS = 0.15F;
    private static float xOffset = 0;
    private static float yOffset = 0;
    private static float xVelocity = 0;
    private static float yVelocity = 0;

    public static void addVelocity(float x, float y) {
        xVelocity += x;
        yOffset += y;
    }

    public static void setVelocity(float x, float y) {
        xVelocity = x;
        yOffset = y;
    }

    public static void renderFlashBang(GuiGraphics guiGraphics, DeltaTracker tickCounter) {
        float deltaTick = tickCounter.getGameTimeDeltaPartialTick(true);

        xOffset += xVelocity * deltaTick;
        yOffset += yVelocity * deltaTick;
        xVelocity -= xOffset * deltaTick * SPRING_NESS;
        yVelocity -= yOffset * deltaTick * SPRING_NESS;
        xVelocity *= 1f - Math.clamp(MOMENTUM_LOSS * deltaTick, 0f, 1f);
        yVelocity *= 1f - Math.clamp(MOMENTUM_LOSS * deltaTick, 0f, 1f);

        guiGraphics.pose().translate(xOffset, yOffset);
    }
}
