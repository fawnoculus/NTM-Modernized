package net.fawnoculus.ntm.client.gui.area;

import net.fawnoculus.ntm.client.util.ClientUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface InfoArea extends Renderable {
    int getX();

    int getY();

    int getWidth();

    int getHeigh();

    int getOffsetX();

    int getOffsetY();

    void appendTooltip(Consumer<Component> tooltip);

    Identifier getTexture();

    @Range(from = 0, to = 1)
    double getFillState();

    default List<Component> getTooltip() {
        List<Component> tooltip = new ArrayList<>();
        this.appendTooltip(tooltip::add);
        return tooltip;
    }

    default int getRelativeMouseX(int mouseX) {
        return mouseX - this.getOffsetX();
    }

    default int getRelativeMouseY(int mouseY) {
        return mouseY - this.getOffsetY();
    }

    default boolean isInBounds(int mouseX, int mouseY) {
        return this.getRelativeMouseX(mouseX) > this.getX()
          && this.getRelativeMouseX(mouseX) < this.getX() + this.getWidth()
          && this.getRelativeMouseY(mouseY) > this.getY()
          && this.getRelativeMouseY(mouseY) < this.getY() + this.getHeigh();
    }

    default void render(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        this.drawTooltip(guiGraphics, mouseX, mouseY);
    }

    default void drawTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (!isInBounds(mouseX, mouseY)) return;
        guiGraphics.setComponentTooltipForNextFrame(ClientUtil.getFont(), this.getTooltip(), mouseX, mouseY);
    }
}
