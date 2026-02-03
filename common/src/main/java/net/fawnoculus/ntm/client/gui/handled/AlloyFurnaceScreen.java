package net.fawnoculus.ntm.client.gui.handled;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceBE;
import net.fawnoculus.ntm.gui.menus.AlloyFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NonNull;

public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {
    private static final Identifier TEXTURE = Ntm.id("textures/gui/machine/alloy_furnace.png");

    public AlloyFurnaceScreen(AlloyFurnaceMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void renderBg(@NonNull GuiGraphics guiGraphics, float deltaTicks, int mouseX, int mouseY) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        AlloyFurnaceBE entity = this.menu.getBlockEntity();
        boolean showFire = entity.showFireInGUI();
        int fuelBarSize = Mth.ceil(entity.getFuel() * 54);
        int progressBarSize = Mth.ceil(entity.getProgress() * 24);

        if (showFire) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos + 63, this.topPos + 38, 176, 0, 14, 14, 256, 256);
        }

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos + 101, this.topPos + 36, 176, 14, progressBarSize, 17, 256, 256);

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos + 43, this.topPos + 17 + 54 - fuelBarSize, 200, 54 - fuelBarSize, 18, fuelBarSize, 256, 256);

    }

    @Override
    public void render(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        super.render(guiGraphics, mouseX, mouseY, deltaTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
