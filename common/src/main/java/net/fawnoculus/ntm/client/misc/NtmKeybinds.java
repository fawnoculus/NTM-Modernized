package net.fawnoculus.ntm.client.misc;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.fawnoculus.ntm.client.NtmClient;
import net.fawnoculus.ntm.client.api.events.custom.ClientTickEvents;
import net.fawnoculus.ntm.client.api.events.custom.OnKeyPressedEvent;
import net.fawnoculus.ntm.client.api.events.custom.OnMouseClickEvent;
import net.fawnoculus.ntm.client.api.qmaw.QmawManager;
import net.fawnoculus.ntm.client.gui.screen.ToolAbilityCustomizationScreen;
import net.fawnoculus.ntm.client.mixin.accessor.AbstractContainerScreenInvoker;
import net.fawnoculus.ntm.client.util.ClientUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;
import java.util.function.Function;

public class NtmKeybinds {
    public static final KeyMapping.Category NTM_CATEGORY = KeyMapping.Category.register(Ntm.id("keys"));

    public static final KeyMapping DUCK = new KeyMapping(
      "key.ntm.duck",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_O,
      NTM_CATEGORY
    );
    public static final KeyMapping DISPLAY_EXTRA_INFO = new KeyMapping(
      "key.ntm.extra_info",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_LEFT_SHIFT,
      NTM_CATEGORY
    );
    public static final KeyMapping OPEN_QMAW_PAGE = new KeyMapping(
      "key.ntm.qmaw",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_F1,
      NTM_CATEGORY
    );
    public static final KeyMapping OPEN_TOOL_ABILITY_GUI = new KeyMapping(
      "key.ntm.open_tool_ability_gui",
      InputConstants.Type.KEYSYM,
      GLFW.GLFW_KEY_LEFT_ALT,
      NTM_CATEGORY
    );

    // TODO: make key conflicts of keys that can only be used in certain situations (only in Guis or smth.) not be shown as regular key conflicts,
    //  as to not be "That annoying mod whose default keybinds always have conflicts"

    public static void init() {
        KeyMappingRegistry.register(DUCK);
        KeyMappingRegistry.register(DISPLAY_EXTRA_INFO);
        KeyMappingRegistry.register(OPEN_QMAW_PAGE);
        KeyMappingRegistry.register(OPEN_TOOL_ABILITY_GUI);

        onKeyPressedTick(OPEN_TOOL_ABILITY_GUI, client -> {
            ItemStack stack = ClientUtil.getPlayer().getMainHandItem();

            if (client.screen == null
              && stack.getItem() instanceof SpecialTool specialTool
              && !specialTool.abilityHandler().ABILITIES().isEmpty()
            ) {
                client.setScreen(new ToolAbilityCustomizationScreen(specialTool, stack));
            }
        });

        // FIXME This shit is fucked up
        // And I have no idea whats wrong with it
        onKeyPressedEvery(OPEN_QMAW_PAGE, client -> {
            NtmClient.LOGGER.info("AAA");
            if (client.screen instanceof AbstractContainerScreen<?> handledScreen) {
                Slot hoveredSlot = ((AbstractContainerScreenInvoker) handledScreen).ntm$getSlotAt(client.mouseHandler.xpos(), client.mouseHandler.ypos());
                return QmawManager.openQmawPage(client, hoveredSlot);
            }
            return false;
        });
    }

    public static void onKeyPressedTick(KeyMapping key, Consumer<Minecraft> onPressed) {
        ClientTickEvents.EVENT.register(client -> {
            while (key.consumeClick()) {
                onPressed.accept(client);
            }
        });
    }

    public static void onKeyPressedEvery(KeyMapping keyBinding, Function<Minecraft, Boolean> onPressed) {
        onKeyboardPressed(keyBinding, (client, ignored) -> onPressed.apply(client));
        onMousePressed(keyBinding, (client, ignored) -> onPressed.apply(client));
    }

    public static void onKeyboardPressed(KeyMapping keyBinding, OnKeyPressedEvent onPressed) {
        OnKeyPressedEvent.EVENT.register((client, keyInput) -> {
            if (keyBinding.matches(keyInput)) {
                return onPressed.onKeyPressed(client, keyInput);
            }
            return false;
        });
    }

    public static void onMousePressed(KeyMapping keyBinding, OnMouseClickEvent onPressed) {
        OnMouseClickEvent.EVENT.register((client, click) -> {
            if (keyBinding.matchesMouse(click)) {
                return onPressed.onClick(client, click);
            }
            return false;
        });
    }
}
