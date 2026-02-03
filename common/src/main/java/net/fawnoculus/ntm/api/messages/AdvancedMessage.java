package net.fawnoculus.ntm.api.messages;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

import java.util.Objects;

public class AdvancedMessage {
    public static final StreamCodec<ByteBuf, AdvancedMessage> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public AdvancedMessage decode(ByteBuf byteBuf) {
            return AdvancedMessage.decode(Objects.requireNonNull(RegistryFriendlyByteBuf.readNbt(byteBuf)));
        }

        @Override
        public void encode(ByteBuf byteBuf, AdvancedMessage message) {
            RegistryFriendlyByteBuf.writeNbt(byteBuf, AdvancedMessage.encode(message));
        }
    };
    private static final float BLEND_TIME = 500.0f;
    private final Identifier IDENTIFIER;
    private final Component TEXT;
    private float millisLeft;

    /**
     * @param identifier    Identifier of the Message (can override other messages if the Identifier is the same)
     * @param text          Text to be displayed
     * @param millisSeconds Amount of Milliseconds the Message should be displayed for;
     */
    public AdvancedMessage(Identifier identifier, Component text, float millisSeconds) {
        if (text.getStyle().getColor() == null) {
            text = text.copy().withStyle(ChatFormatting.WHITE);
        }
        this.IDENTIFIER = identifier;
        this.TEXT = text;
        this.millisLeft = millisSeconds;
    }

    public static CompoundTag encode(AdvancedMessage message) {
        CompoundTag nbt = new CompoundTag();
        nbt.store("identifier", Identifier.CODEC, message.IDENTIFIER);
        nbt.store("millis_left", Codec.FLOAT, message.millisLeft);
        nbt.store("text", ComponentSerialization.CODEC, message.TEXT);
        return nbt;
    }

    public static AdvancedMessage decode(CompoundTag nbt) {
        Identifier identifier = nbt.read("identifier", Identifier.CODEC).orElseThrow();
        float millisLeft = nbt.read("millis_left", Codec.FLOAT).orElseThrow();
        Component text = nbt.read("text", ComponentSerialization.CODEC).orElseThrow();
        return new AdvancedMessage(identifier, text, millisLeft);
    }

    public Identifier getID() {
        return IDENTIFIER;
    }

    public Component getText() {
        return this.TEXT;
    }

    public float getMillisLeft() {
        return this.millisLeft;
    }

    public void setMillisLeft(float millisLeft) {
        this.millisLeft = millisLeft;
    }

    public int getOpacity() {
        if (this.millisLeft > BLEND_TIME) return 255;
        return (int) Math.clamp(this.millisLeft / BLEND_TIME * 255, 0, 255);
    }
}
