package net.fawnoculus.ntm.client.api.messages;

import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageSystem {
    private static final List<AdvancedMessage> ALL_MESSAGES = new ArrayList<>();
    private static final HashMap<String, AdvancedMessage> MESSAGE_HASH_MAP = new HashMap<>();
    private static final AtomicBoolean isEnabled = new AtomicBoolean(true);
    private static long LastNanoTime = System.nanoTime();

    public static void onClientTick(Minecraft ignored) {
        float deltaMillis = (float) ((System.nanoTime() - LastNanoTime) / 1000000);

        List<AdvancedMessage> toBeRemovedMessages = new ArrayList<>();
        for (AdvancedMessage message : ALL_MESSAGES) {
            float millisLeft = message.getMillisLeft();
            if (millisLeft < 0) {
                toBeRemovedMessages.add(message);
                continue;
            }
            message.setMillisLeft(millisLeft - deltaMillis);
        }
        for (AdvancedMessage message : toBeRemovedMessages) {
            MessageSystem.removeMessage(message.getID());
        }

        LastNanoTime = System.nanoTime();
    }

    public static void addMessage(AdvancedMessage message) {
        if (!isEnabled.get()) return;

        Identifier identifier = message.getID();
        removeMessage(identifier);
        ALL_MESSAGES.add(message);
        MESSAGE_HASH_MAP.put(identifier.toString(), message);
    }

    private static void removeMessage(AdvancedMessage message) {
        ALL_MESSAGES.remove(message);
        MESSAGE_HASH_MAP.remove(message.getID().toString());
    }

    public static void removeMessage(Identifier identifier) {
        if (MESSAGE_HASH_MAP.containsKey(identifier.toString())) {
            removeMessage(getMessage(identifier));
        }
    }

    public static void removeAllMessages() {
        ALL_MESSAGES.clear();
        MESSAGE_HASH_MAP.clear();
    }

    public static AdvancedMessage getMessage(Identifier identifier) {
        return MESSAGE_HASH_MAP.get(identifier.toString());
    }

    public static List<AdvancedMessage> getAllMessages() {
        return ALL_MESSAGES;
    }

    public static boolean getIsEnabled() {
        return isEnabled.get();
    }

    public static void setIsEnabled(boolean enabled) {
        isEnabled.set(enabled);
    }
}
