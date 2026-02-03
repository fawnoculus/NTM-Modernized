package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record AdvancedMessagePayload(AdvancedMessage message) implements CustomPacketPayload {
    public static final Identifier ADVANCED_MESSAGE_PAYLOAD_ID = Ntm.id("advanced_message");
    public static final Type<AdvancedMessagePayload> ID = new Type<>(ADVANCED_MESSAGE_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, AdvancedMessagePayload> STREAM_CODEC = StreamCodec.composite(AdvancedMessage.STREAM_CODEC, AdvancedMessagePayload::message, AdvancedMessagePayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
