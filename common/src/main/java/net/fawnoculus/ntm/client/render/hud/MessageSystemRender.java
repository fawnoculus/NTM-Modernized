package net.fawnoculus.ntm.client.render.hud;

import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.client.api.messages.MessageSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

import java.util.List;

public class MessageSystemRender {
    private static final int backgroundColor = ARGB.color(150, 100, 100, 100);
    private static final int marginSide = 10;
    private static final int marginTop = 10;
    private static final int paddingSide = 5;
    private static final int paddingTop = 5;


    public static void drawMessageSystem(GuiGraphics guiGraphics, DeltaTracker ignored) {
        List<AdvancedMessage> messages = MessageSystem.getAllMessages();
        if (messages.isEmpty()) {
            return;
        }

        Font textRenderer = Minecraft.getInstance().font;

        int width = 0;
        for (AdvancedMessage message : messages) {
            if (textRenderer.width(message.getText()) > width) {
                width = textRenderer.width(message.getText());
            }
        }
        int height = textRenderer.lineHeight * messages.size();

        guiGraphics.fill(marginSide, marginTop, marginSide + (paddingSide * 2) + width, marginTop + (paddingTop * 2) + height, backgroundColor);

        for (int i = 0; i < messages.size(); i++) {
            Component text = messages.get(i).getText();
            guiGraphics.drawString(textRenderer, text, marginSide + paddingSide, marginTop + paddingTop + (textRenderer.lineHeight * i), ARGB.color(messages.get(i).getOpacity(), 256, 256, 256), true);
        }
    }
}
