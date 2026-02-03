package net.fawnoculus.ntm;

import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fawnoculus.ntm.client.NtmClientProxy;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.ApiStatus;

public class NtmProxy {
    @Environment(EnvType.SERVER)
    @ApiStatus.Internal
    private static final NtmProxy INSTANCE = new NtmProxy();

    @ApiStatus.Internal
    protected NtmProxy() {
    }

    /**
     * Gets the mod-loader specific Proxy for the Server
     * You can technically call this on the client to get a version of the server proxy, but why would you do that?
     * This is a Singleton
     *
     * @return The proxy to be used on Servers
     */
    @ApiStatus.Internal
    @Environment(EnvType.SERVER)
    private static NtmProxy getCommonProxy() {
        return INSTANCE;
    }

    /**
     * Gets the current mod-loader & Environment specific proxy
     * This is a Singleton
     *
     * @return The proxy that should be used
     */
    public static NtmProxy getProxy() {
        return switch (Platform.getEnv()) {
            case SERVER -> getCommonProxy();
            case CLIENT -> NtmClientProxy.getClientProxy();
            default -> throw new IllegalStateException("Environment is Neither Client nor Server");
        };
    }

    /**
     * Get a Mutable component of a Keybind, this will be the name of the keybind when ran on a dedicated server and the key bound to that keybind when ran on the client
     *
     * @param keybindTranslationKey The translation key of the keybind
     * @return Mutable text component that contains either the name of the keybind or the key bound to it
     */
    public MutableComponent getKeyText(String keybindTranslationKey) {
        return Component.translatable(keybindTranslationKey);
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, SoundEvent sound, SoundSource category) {
        playSoundToPlayer(player, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound), category, player.getX(), player.getY(), player.getZ(), 0f, 0f, player.getRandom().nextLong());
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, SoundEvent sound, SoundSource category, float volume, float pitch) {
        playSoundToPlayer(player, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound), category, player.getX(), player.getY(), player.getZ(), volume, pitch, player.getRandom().nextLong());
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, SoundEvent sound, SoundSource category, double x, double y, double z, float volume, float pitch) {
        playSoundToPlayer(player, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound), category, x, y, z, volume, pitch, player.getRandom().nextLong());
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, SoundEvent sound, SoundSource category, double x, double y, double z, float volume, float pitch, long seed) {
        playSoundToPlayer(player, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound), category, x, y, z, volume, pitch, seed);
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, Holder<SoundEvent> sound, SoundSource category) {
        playSoundToPlayer(player, sound, category, player.getX(), player.getY(), player.getZ(), 0f, 0f, player.getRandom().nextLong());
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, Holder<SoundEvent> sound, SoundSource category, float volume, float pitch) {
        playSoundToPlayer(player, sound, category, player.getX(), player.getY(), player.getZ(), volume, pitch, player.getRandom().nextLong());
    }

    /**
     * it's just {@link NtmProxy#playSoundToPlayer(Player, Holder, SoundSource, double, double, double, float, float, long)} with some public static parameters
     */
    public void playSoundToPlayer(Player player, Holder<SoundEvent> sound, SoundSource category, double x, double y, double z, float volume, float pitch) {
        playSoundToPlayer(player, sound, category, x, y, z, volume, pitch, player.getRandom().nextLong());
    }

    /**
     * Plays a sound to the specified player, this is achived by sending them á sound packet if they are a server player entity
     * or directly telling the client to play the sound when the current {@link net.minecraft.client.player.LocalPlayer} is passed in
     *
     * @param player   The player that should hear the sound
     * @param sound    The sound to play
     * @param category the category for the sound
     * @param x        the x position of the sound
     * @param y        the y position of the sound
     * @param z        the z position of the sound
     * @param volume   the volume of the sound (between 0 - 1 this affects how loud it is, any value over 1 will increase the range at which the sound can be heard)
     * @param pitch    the pitch of the sound (between 0 - 2, any out of bounds value will be clamped to the limit (maybe?))
     * @param seed     A seed used to decide which sound to play if the sound event can be multiple sounds
     */
    public void playSoundToPlayer(Player player, Holder<SoundEvent> sound, SoundSource category, double x, double y, double z, float volume, float pitch, long seed) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundSoundPacket(sound, category, x, y, z, volume, pitch, seed));
        }
    }
}
