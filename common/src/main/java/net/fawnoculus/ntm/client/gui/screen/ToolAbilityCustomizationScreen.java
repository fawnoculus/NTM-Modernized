package net.fawnoculus.ntm.client.gui.screen;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.fawnoculus.ntm.api.tool.ItemAbility;
import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.fawnoculus.ntm.client.gui.widget.HoverButtonWidget;
import net.fawnoculus.ntm.client.gui.widget.ToolAbilityWidget;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.fawnoculus.ntm.client.util.RenderUtil;
import net.fawnoculus.ntm.network.c2s.ToolAbilityPresetPayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToolAbilityCustomizationScreen extends Screen {
    private static final Identifier TEXTURE = Ntm.id("textures/gui/ability/screen.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 128;
    private final AbilityHandler ABILITY_HANDLER;
    private final int BACKGROUND_HEIGHT = 77;
    private final List<ToolAbilityWidget> TOP_ABILITIES = new ArrayList<>();
    private final List<ToolAbilityWidget> BOTTOM_ABILITIES = new ArrayList<>();
    private final SpecialTool TOOL;
    private final List<ModifiablePreset> PRESETS;
    private int BACKGROUND_WIDTH = 186;
    private int presetIndex;
    private boolean sendPacketOnClose = false;
    private int x = 0;
    private int y = 0;

    public ToolAbilityCustomizationScreen(SpecialTool specialTool, ItemStack stack) {
        super(Component.translatable("screen.ntm.tool_abilities"));

        this.TOOL = specialTool;
        this.ABILITY_HANDLER = specialTool.abilityHandler();

        AbilityHandler.StackData stackData = ABILITY_HANDLER.getStackData(stack);
        this.PRESETS = new ArrayList<>(stackData.presets().stream().map(ModifiablePreset::new).toList());
        this.presetIndex = stackData.selectedPreset();


        if (ClientUtil.getPlayer().getMainHandItem() != stack || stack.getItem() != TOOL) {
            this.onClose();
        }
    }

    @Override
    protected void clearWidgets() {
        super.clearWidgets();
        this.TOP_ABILITIES.clear();
        this.BOTTOM_ABILITIES.clear();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
        if (this.sendPacketOnClose) {
            NetworkManager.sendToServer(new ToolAbilityPresetPayload(
              new AbilityHandler.StackData(this.PRESETS.stream().map(ModifiablePreset::toRegularPreset).toList(), this.presetIndex)
            ));
        }
    }

    @Override
    protected void init() {
        super.init();
        this.sendPacketOnClose = false;

        this.BACKGROUND_WIDTH = Math.max(
          (ItemAbility.TOP_ABILITIES.size() * 20) + 15 + 90,
          (ItemAbility.BOTTOM_ABILITIES.size() * 20) + 15 + 15
        );

        this.x = (this.width - BACKGROUND_WIDTH) / 2;
        this.y = (this.height - BACKGROUND_HEIGHT) / 2;

        List<Component> names = List.of(
          Component.translatable("tooltip.ntm.ability.gui.reset_presets"),
          Component.translatable("tooltip.ntm.ability.gui.delete_current"),
          Component.translatable("tooltip.ntm.ability.gui.add_new"),
          Component.translatable("tooltip.ntm.ability.gui.select_first"),
          Component.translatable("tooltip.ntm.ability.gui.next"),
          Component.translatable("tooltip.ntm.ability.gui.previous"),
          Component.translatable("tooltip.ntm.ability.gui.close_gui")
        );
        List<Runnable> onPressed = List.of(
          this::resetPresets,
          this::deleteCurrent,
          this::addNew,
          this::selectFirst,
          this::selectNext,
          this::selectPrevious,
          this::onClose
        );

        int widgetX = this.x + BACKGROUND_WIDTH - 86;
        int widgetY = this.y + 11;
        int widgetU = 100;
        int widgetV = 91;
        for (int i = 0; i < names.size(); i++) {
            this.addRenderableWidget(
              new HoverButtonWidget(
                widgetX, widgetY,
                9, 9,
                names.get(i), TEXTURE,
                widgetU, widgetV,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                onPressed.get(i)
              )
            );
            widgetX += 11;
            widgetU += 9;
        }


        widgetX = this.x + 15;
        widgetY = this.y + 25;
        for (ItemAbility ability : ItemAbility.TOP_ABILITIES) {
            ToolAbilityWidget widget = new ToolAbilityWidget(widgetX, widgetY, ability, TOOL.abilityHandler().getMaxLevel(ability), false, TOP_ABILITIES, BOTTOM_ABILITIES);
            this.TOP_ABILITIES.add(widget);
            this.addRenderableWidget(widget);
            widgetX += 20;
        }

        widgetX = this.x + 15;
        widgetY = this.y + 45;
        for (ItemAbility ability : ItemAbility.BOTTOM_ABILITIES) {
            ToolAbilityWidget widget = new ToolAbilityWidget(widgetX, widgetY, ability, TOOL.abilityHandler().getMaxLevel(ability), true, BOTTOM_ABILITIES, TOP_ABILITIES);
            this.BOTTOM_ABILITIES.add(widget);
            this.addRenderableWidget(widget);
            widgetX += 20;
        }

        this.setPresetIndex(presetIndex);

        this.sendPacketOnClose = true;
    }

    public void setPreset(ModifiablePreset preset) {
        for (ToolAbilityWidget widget : this.TOP_ABILITIES) {
            widget.setPreset(preset);
        }

        for (ToolAbilityWidget widget : this.BOTTOM_ABILITIES) {
            widget.setPreset(preset);
        }
    }

    public void setPresetIndex(int index) {
        if (index < 0 || index >= this.PRESETS.size()) {
            return;
        }
        this.presetIndex = index;
        this.setPreset(this.PRESETS.get(index));
    }

    @Override
    public boolean keyPressed(@NonNull KeyEvent input) {
        if (this.minecraft.options.keyInventory.matches(input)) {
            this.onClose();
        }
        return super.keyPressed(input);
    }

    @Override
    public void renderBackground(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTicks) {
        super.renderBackground(guiGraphics, mouseX, mouseY, deltaTicks);
        if (this.minecraft.player == null || this.minecraft.player.getMainHandItem().getItem() != TOOL) {
            this.sendPacketOnClose = false;
            this.onClose();
            return;
        }

        RenderUtil.drawVariableWidthRect(
          guiGraphics, TEXTURE,
          this.x, this.y,
          0, 0,
          BACKGROUND_HEIGHT, BACKGROUND_WIDTH,
          186, 73, 86,
          TEXTURE_WIDTH, TEXTURE_HEIGHT
        );

        Optional<GuiEventListener> optional = this.getChildAt(mouseX, mouseY);
        if (optional.isPresent() && optional.get() instanceof AbstractWidget widget) {
            Component message = widget.getMessage();
            int messageWidth = Math.max(6, this.font.width(message));
            int messageX = this.x + (this.BACKGROUND_WIDTH / 2) - (messageWidth / 2);
            int messageY = this.y + this.BACKGROUND_HEIGHT + 5;
            RenderUtil.drawVariableWidthRect(
              guiGraphics, TEXTURE,
              messageX - 5, messageY - 4,
              0, 76,
              15, messageWidth + 10,
              186, 3, 3,
              TEXTURE_WIDTH, TEXTURE_HEIGHT
            );
            guiGraphics.drawString(this.font, message, messageX, messageY, CommonColors.WHITE, false);
        }

        renderDigit(guiGraphics, this.x + BACKGROUND_WIDTH - 71, this.y + 25, ((this.presetIndex + 1) / 10) % 10);
        renderDigit(guiGraphics, this.x + BACKGROUND_WIDTH - 58, this.y + 25, (this.presetIndex + 1) % 10);
        renderDigit(guiGraphics, this.x + BACKGROUND_WIDTH - 37, this.y + 25, (this.PRESETS.size() / 10) % 10);
        renderDigit(guiGraphics, this.x + BACKGROUND_WIDTH - 25, this.y + 25, this.PRESETS.size() % 10);
    }

    public void renderDigit(GuiGraphics guiGraphics, int x, int y, @Range(from = 0, to = 9) int digit) {
        guiGraphics.blit(
          RenderPipelines.GUI_TEXTURED, TEXTURE,
          x, y,
          digit * 10, 91,
          10, 15,
          TEXTURE_WIDTH, TEXTURE_HEIGHT
        );
    }

    private void resetPresets() {
        this.PRESETS.clear();
        this.ABILITY_HANDLER.DEFAULT_STACK_DATA().presets().stream().map(ModifiablePreset::new).forEach(this.PRESETS::add);
        if (this.presetIndex >= this.PRESETS.size()) {
            this.presetIndex = this.PRESETS.size() - 1;
        }
        this.setPresetIndex(this.presetIndex);
    }

    private void deleteCurrent() {
        if (this.PRESETS.size() <= 1) {
            return;
        }
        this.PRESETS.remove(this.presetIndex);
        if (this.presetIndex >= this.PRESETS.size()) {
            this.presetIndex = this.PRESETS.size() - 1;
        }
        this.setPresetIndex(presetIndex);
    }

    private void addNew() {
        if (this.PRESETS.size() >= 99) {
            return;
        }
        this.PRESETS.addFirst(new ModifiablePreset());
        this.presetIndex += 1;
    }

    private void selectFirst() {
        this.setPresetIndex(0);
    }

    private void selectNext() {
        this.setPresetIndex(this.presetIndex + 1);
    }

    private void selectPrevious() {
        this.setPresetIndex(this.presetIndex - 1);
    }

    public static class ModifiablePreset {
        public ItemAbility topAbility;
        public int topAbilityLevel;
        public ItemAbility bottomAbility;
        public int bottomAbilityLevel;

        public ModifiablePreset() {
            this(ItemAbility.NONE, 1, ItemAbility.NONE, 1);
        }

        public ModifiablePreset(AbilityHandler.Preset from) {
            this(from.topAbility(), from.topAbilityLevel(), from.bottomAbility(), from.bottomAbilityLevel());
        }

        public ModifiablePreset(ItemAbility topAbility, int topAbilityLevel, ItemAbility bottomAbility, int bottomAbilityLevel) {
            this.topAbility = topAbility;
            this.topAbilityLevel = topAbilityLevel;
            this.bottomAbility = bottomAbility;
            this.bottomAbilityLevel = bottomAbilityLevel;
        }

        public void setTopAbility(ItemAbility itemAbility, int level) {
            this.topAbility = itemAbility;
            this.topAbilityLevel = level;
        }

        public void setBottomAbility(ItemAbility itemAbility, int level) {
            this.bottomAbility = itemAbility;
            this.bottomAbilityLevel = level;
        }

        public AbilityHandler.Preset toRegularPreset() {
            return new AbilityHandler.Preset(this.topAbility, this.topAbilityLevel, this.bottomAbility, this.bottomAbilityLevel);
        }
    }
}
