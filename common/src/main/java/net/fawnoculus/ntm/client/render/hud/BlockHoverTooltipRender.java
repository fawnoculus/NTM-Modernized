package net.fawnoculus.ntm.client.render.hud;

import net.fawnoculus.ntm.blocks.custom.HoverTooltipBlock;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class BlockHoverTooltipRender {
    private static boolean shouldDraw() {
        return ClientUtil.getFont() != null
          && ClientUtil.getPlayer() != null;
    }

    public static void drawBlockHoverTooltip(GuiGraphics guiGraphics, DeltaTracker ignored) {
        if (!shouldDraw()) return;
        ClientLevel world = ClientUtil.getLevel();

        if (!(ClientUtil.getClient().hitResult instanceof BlockHitResult result)) return;
        if (result.getType() == HitResult.Type.MISS) return;

        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof HoverTooltipBlock hoverTooltipBlock)) return;
        if (!hoverTooltipBlock.shouldDisplayTooltip(world, pos, state)) return;

        Font textRenderer = ClientUtil.getFont();
        List<Component> tooltip = hoverTooltipBlock.getTooltip(world, pos, state);

        int y = (guiGraphics.guiHeight() / 2) - (textRenderer.lineHeight * tooltip.size() / 2);
        final int x = guiGraphics.guiWidth() / 2 + 7;

        for (Component text : tooltip) {
            guiGraphics.drawString(textRenderer, text, x, y, ARGB.color(256, 256, 256), true);
            y += textRenderer.lineHeight;
        }
    }
}
