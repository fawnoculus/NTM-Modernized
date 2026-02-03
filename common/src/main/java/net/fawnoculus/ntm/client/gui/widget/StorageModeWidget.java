package net.fawnoculus.ntm.client.gui.widget;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.node.StorageMode;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class StorageModeWidget extends AbstractWidget {
    private static final Identifier TEXTURE = Ntm.id("textures/gui/generic/storage_mode.png");
    private final Supplier<StorageMode> STORAGE_MODE;
    private final Runnable ON_CLICKED;

    public StorageModeWidget(int x, int y, Component message, Supplier<StorageMode> storageMode, Runnable onClicked) {
        super(x, y, 18, 18, message);
        this.STORAGE_MODE = storageMode;
        this.ON_CLICKED = onClicked;
    }

    @Override
    public void onClick(@NonNull MouseButtonEvent click, boolean doubled) {
        super.onClick(click, doubled);
        this.ON_CLICKED.run();
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
          TEXTURE,
          this.getX(),
          this.getY(),
          this.getU(STORAGE_MODE.get()),
          this.getV(STORAGE_MODE.get()),
          this.width,
          this.height,
          36,
          36
        );
    }

    @Contract(pure = true)
    private float getU(@NotNull StorageMode storageMode) {
        return switch (storageMode) {
            case None, Provide -> 18;
            default -> 0;
        };
    }

    @Contract(pure = true)
    private float getV(@NotNull StorageMode storageMode) {
        return switch (storageMode) {
            case None, Share -> 18;
            default -> 0;
        };
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput builder) {
        builder.add(NarratedElementType.HINT, Component.translatable(this.STORAGE_MODE.get().translationKey));
    }
}
