package net.fawnoculus.ntm.client.gui.area;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public interface InfoBar extends InfoArea {
    int getU();

    int getV();

    int getTextureHeight();

    int getTextureWidth();

    @Nullable Identifier getTexture();

    double getFillState();

    @Override
    default void render(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        InfoArea.super.render(guiGraphics, mouseX, mouseY, deltaTicks);
        this.drawBar(guiGraphics);
    }

    default void drawBar(GuiGraphics guiGraphics) {
        if (this.getTexture() == null) return;

        int barSize = Mth.ceil(this.getFillState() * this.getHeigh());

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, this.getTexture(),
          this.getX() + this.getOffsetX(),
          this.getY() + this.getOffsetY() + this.getHeigh() - barSize,
          this.getU(),
          this.getV() + this.getHeigh() - barSize,
          this.getWidth(),
          barSize,
          this.getTextureWidth(),
          this.getTextureHeight()
        );
    }
}
