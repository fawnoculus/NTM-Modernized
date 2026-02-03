package net.fawnoculus.ntm.client.gui.handled;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.entities.container.energy.SimpleEnergyStorageBE;
import net.fawnoculus.ntm.client.gui.area.EnergyBar;
import net.fawnoculus.ntm.client.gui.widget.StorageModeWidget;
import net.fawnoculus.ntm.gui.menus.EnergyStorageMenu;
import net.fawnoculus.ntm.network.c2s.BEInteractionPayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NonNull;

public class EnergyStorageScreen extends AbstractContainerScreen<EnergyStorageMenu> {
    private static final Identifier TEXTURE = Ntm.id("textures/gui/storage/energy_storage.png");
    private final SimpleEnergyStorageBE BE = this.menu.getBlockEntity();
    private final EnergyBar energyBar = new EnergyBar(62, 17, 52, 52, BE.energy, BE::getEnergyPerSec);

    public EnergyStorageScreen(EnergyStorageMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        energyBar.setOffsets(this.leftPos, this.topPos);
        this.addRenderableOnly(energyBar);
        this.addRenderableWidget(new StorageModeWidget(this.leftPos + 133, this.topPos + 16, Component.translatable("narration.ntm.storage_mode_button.unpowered"), () -> BE.unpoweredMode,
          () -> NetworkManager.sendToServer(new BEInteractionPayload(BE.getBlockPos(), SimpleEnergyStorageBE.CYCLE_UNPOWERED_MODE))
        ));
        this.addRenderableWidget(new StorageModeWidget(this.leftPos + 133, this.topPos + 52, Component.translatable("narration.ntm.storage_mode_button.powered"), () -> BE.poweredMode,
          () -> NetworkManager.sendToServer(new BEInteractionPayload(BE.getBlockPos(), SimpleEnergyStorageBE.CYCLE_POWERED_MODE))
        ));
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float deltaTicks, int mouseX, int mouseY) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    public void render(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        super.render(guiGraphics, mouseX, mouseY, deltaTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
