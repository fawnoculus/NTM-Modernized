package net.fawnoculus.ntm.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClientUtil {
    @NotNull
    public static Minecraft getClient() {
        return Objects.requireNonNull(Minecraft.getInstance(), "Tried to get client before it was initialized");
    }

    public static boolean hasPlayer() {
        return getClient().player != null;
    }

    @NotNull
    public static LocalPlayer getPlayer() {
        return Objects.requireNonNull(getClient().player, "Client does not have a player");
    }

    public static boolean hasLevel() {
        return getClient().level != null;
    }

    @NotNull
    public static ClientLevel getLevel() {
        return Objects.requireNonNull(getClient().level, "Client does not have a level");
    }

    @NotNull
    public static Font getFont() {
        return Objects.requireNonNull(getClient().font, "Client did not contain valid Font");
    }

    public static void playSound(SoundInstance sound) {
        getClient().getSoundManager().play(sound);
    }
}
