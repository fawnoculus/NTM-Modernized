package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record RemoveMessagePayload(Identifier identifier) implements CustomPacketPayload {
    public static final Identifier CLEAR_ALL_MESSAGES_PAYLOAD_ID = Ntm.id("remove_message");
    public static final Type<RemoveMessagePayload> ID = new Type<>(CLEAR_ALL_MESSAGES_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, RemoveMessagePayload> STREAM_CODEC = StreamCodec.composite(Identifier.STREAM_CODEC, RemoveMessagePayload::identifier, RemoveMessagePayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
