package net.fawnoculus.ntm.client.gui.widget;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.ItemAbility;
import net.fawnoculus.ntm.client.gui.screen.ToolAbilityCustomizationScreen.ModifiablePreset;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.fawnoculus.ntm.misc.NtmSounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ToolAbilityWidget extends AbstractWidget {
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int TEXTURE_WIDTH = 16;
    private static final int TEXTURE_HEIGHT = 16;
    private static final Identifier LEVEL_TEXTURE = Ntm.id("textures/gui/ability/screen.png");
    private static final int LEVEL_TEXTURE_WIDTH = 256;
    private static final int LEVEL_TEXTURE_HEIGHT = 128;
    private static final int LEVEL_TEXTURE_V = 106;

    private final @Range(from = 0, to = 10) int MAX_LEVEL;
    private final boolean IS_BOTTOM;
    private final ItemAbility ABILITY;
    private final List<ToolAbilityWidget> ROW;
    private final List<ToolAbilityWidget> OPPOSITE_ROW;
    private ModifiablePreset preset;
    private boolean isDisabled;
    private int currentLevel = -1;

    public ToolAbilityWidget(int x, int y, ItemAbility ability, int maxLevel, boolean isBottom, List<ToolAbilityWidget> row, List<ToolAbilityWidget> oppositeRow) {
        super(x, y, WIDTH, HEIGHT, maxLevel >= 1 ? ability.getFullName(maxLevel) : ability.getFullName(0));

        this.MAX_LEVEL = ability.isNone() ? 1 : maxLevel;
        this.IS_BOTTOM = isBottom;
        this.ABILITY = ability;
        this.ROW = row;
        this.OPPOSITE_ROW = oppositeRow;

        this.isDisabled = this.MAX_LEVEL < 1;
    }

    @Override
    protected void renderWidget(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        if (this.isHovered()) {
            guiGraphics.renderOutline(this.getX() - 1, this.getY() - 1, this.getWidth() + 2, this.getHeight() + 2, CommonColors.GRAY);
        }

        if (this.currentLevel >= 0) {
            guiGraphics.renderOutline(this.getX() - 1, this.getY() - 1, this.getWidth() + 2, this.getHeight() + 2, CommonColors.WHITE);
        }

        if (this.ABILITY.maxLevel() > 1) {
            guiGraphics.blit(
              RenderPipelines.GUI_TEXTURED,
              LEVEL_TEXTURE,
              this.getX() + WIDTH + 1, this.getY() + 1,
              Math.clamp(currentLevel, 0, 10) * 2, LEVEL_TEXTURE_V,
              2, 14,
              LEVEL_TEXTURE_WIDTH, LEVEL_TEXTURE_HEIGHT
            );
        }

        if (this.isDisabled) {
            guiGraphics.blit(
              RenderPipelines.GUI_TEXTURED,
              this.ABILITY.getDisabledTexture(),
              this.getX(), this.getY(),
              0, 0,
              WIDTH, HEIGHT,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
        } else {
            guiGraphics.blit(
              RenderPipelines.GUI_TEXTURED,
              this.ABILITY.getEnabledTexture(),
              this.getX(), this.getY(),
              0, 0,
              WIDTH, HEIGHT,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
        }
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput builder) {
    }

    public void setLevel(int level) {
        if (level < 0) {
            this.currentLevel = -1;
            this.setMessage(this.ABILITY.getName());
            return;
        }

        this.currentLevel = level;
        this.setMessage(this.ABILITY.getFullName(this.currentLevel));

        if (this.preset != null) {
            if (this.IS_BOTTOM) {
                this.preset.setBottomAbility(this.ABILITY, level);
            } else {
                this.preset.setTopAbility(this.ABILITY, level);
            }
        }


        for (ToolAbilityWidget widget : ROW) {
            if (widget == this) continue;
            widget.setLevel(-1);
        }
        for (ToolAbilityWidget widget : OPPOSITE_ROW) {
            if (widget.ABILITY.isNone() && this.ABILITY.disablesOtherRow()) {
                widget.setLevel(0);
                continue;
            }
            widget.forceDisable(this.ABILITY.disablesOtherRow());
        }
    }


    public void setPreset(ModifiablePreset preset) {
        assert preset != null;
        this.preset = preset;
        if (this.IS_BOTTOM && this.ABILITY == preset.bottomAbility) {
            this.setLevel(preset.bottomAbilityLevel);
        }
        if (!this.IS_BOTTOM && this.ABILITY == preset.topAbility) {
            this.setLevel(preset.topAbilityLevel);
        }
    }

    public void forceDisable(boolean disable) {
        if (disable) {
            this.isDisabled = true;
            this.setLevel(-1);
        } else {
            this.isDisabled = this.MAX_LEVEL < 1;
        }
    }

    @Override
    public void playDownSound(@NonNull SoundManager soundManager) {
    }

    @Override
    public void onClick(@NonNull MouseButtonEvent click, boolean doubled) {
        if (this.isDisabled) return;

        int previousLevel = currentLevel;

        if (this.MAX_LEVEL < 2) {
            if (this.currentLevel != this.MAX_LEVEL) {
                this.setLevel(this.MAX_LEVEL);
            }
        } else {
            this.setLevel(Math.max(0, this.currentLevel) % this.MAX_LEVEL + 1);
        }

        if (previousLevel != currentLevel) {
            ClientUtil.playSound(SimpleSoundInstance.forUI(NtmSounds.TECH_BOOP, 2));
        }
    }
}
