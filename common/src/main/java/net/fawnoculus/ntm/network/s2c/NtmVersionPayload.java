package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.nio.charset.StandardCharsets;

public record NtmVersionPayload(String version) implements CustomPacketPayload {
    public static final Identifier NTM_VERSION_PAYLOAD_ID = Ntm.id("version");
    public static final Type<NtmVersionPayload> ID = new Type<>(NTM_VERSION_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, NtmVersionPayload> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public NtmVersionPayload decode(RegistryFriendlyByteBuf byteBuf) {
            return new NtmVersionPayload(new String(byteBuf.readByteArray(), StandardCharsets.UTF_8));
        }

        @Override
        public void encode(RegistryFriendlyByteBuf byteBuf, NtmVersionPayload payload) {
            byteBuf.writeByteArray(payload.version.getBytes(StandardCharsets.UTF_8));
        }
    };

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
