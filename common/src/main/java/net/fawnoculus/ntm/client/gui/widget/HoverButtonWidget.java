package net.fawnoculus.ntm.client.gui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import org.jspecify.annotations.NonNull;

public class HoverButtonWidget extends AbstractWidget {
    private final Identifier TEXTURE;
    private final int U;
    private final int V;
    private final int TEXTURE_WIDTH;
    private final int TEXTURE_HEIGHT;
    private final Runnable ON_CLICKED;

    public HoverButtonWidget(
      int x, int y,
      int width, int height,
      Component message, Identifier texture,
      int u, int v,
      int textureWidth, int textureHeight,
      Runnable onClicked
    ) {
        super(x, y, width, height, message);
        this.TEXTURE = texture;
        this.U = u;
        this.V = v;
        this.TEXTURE_WIDTH = textureWidth;
        this.TEXTURE_HEIGHT = textureHeight;
        this.ON_CLICKED = onClicked;
    }

    @Override
    protected void renderWidget(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        if (this.isHovered()) {
            guiGraphics.blit(
              RenderPipelines.GUI_TEXTURED, TEXTURE,
              this.getX(), this.getY(),
              this.U, this.V,
              this.width, this.height,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
        }
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput builder) {
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 0.5F));
    }

    @Override
    public void onClick(@NonNull MouseButtonEvent click, boolean doubled) {
        ON_CLICKED.run();
    }
}
