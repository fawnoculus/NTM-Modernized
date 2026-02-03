package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record RemoveAllMessagesPayload() implements CustomPacketPayload {
    public static final Identifier CLEAR_ALL_MESSAGES_PAYLOAD_ID = Ntm.id("remove_all_messages");
    public static final Type<RemoveAllMessagesPayload> ID = new Type<>(CLEAR_ALL_MESSAGES_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, RemoveAllMessagesPayload> STREAM_CODEC = StreamCodec.unit(new RemoveAllMessagesPayload());

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
