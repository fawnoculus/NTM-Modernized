package net.fawnoculus.ntm.client.render.hud;

import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class ToolAbilityRender {
    private static final int TEXTURE_WIDTH = 16;
    private static final int TEXTURE_HEIGHT = 16;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    private static boolean shouldDraw() {
        return ClientUtil.hasPlayer();
    }

    public static void drawToolAbility(GuiGraphics guiGraphics, DeltaTracker ignored) {
        if (!shouldDraw()) return;
        ItemStack stack = ClientUtil.getPlayer().getMainHandItem();
        if (!(stack.getItem() instanceof SpecialTool specialTool)) return;

        final int xOffset = NtmClientConfig.TOOL_ABILITY_DISPLAY_X_OFFSET.getValue();
        final int yOffset = NtmClientConfig.TOOL_ABILITY_DISPLAY_Y_OFFSET.getValue();

        AbilityHandler.Preset preset = specialTool.abilityHandler().getCurrentPreset(stack);

        int centerX = guiGraphics.guiWidth() / 2;
        int centerY = guiGraphics.guiHeight() / 2;

        Identifier top = preset.topAbility().getCrosshairExtension();
        if (top != null) {
            guiGraphics.blit(
              RenderPipelines.CROSSHAIR, top,
              centerX - WIDTH - xOffset,
              centerY + yOffset,
              0, 0,
              WIDTH, HEIGHT,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
        }

        Identifier bottom = preset.bottomAbility().getCrosshairExtension();
        if (bottom != null) {
            guiGraphics.blit(
              RenderPipelines.CROSSHAIR, bottom,
              centerX + xOffset,
              centerY + yOffset,
              0, 0,
              WIDTH, HEIGHT,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
        }
    }
}
