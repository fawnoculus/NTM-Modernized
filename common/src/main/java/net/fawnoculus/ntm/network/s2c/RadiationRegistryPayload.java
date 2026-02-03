package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.radiation.RadiationRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record RadiationRegistryPayload(RadiationRegistry.Serialized serializedRegistry) implements CustomPacketPayload {
    public static final Identifier RADIATION_REGISTRY_PAYLOAD_ID = Ntm.id("radiation_registry");
    public static final Type<RadiationRegistryPayload> ID = new Type<>(RADIATION_REGISTRY_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, RadiationRegistryPayload> STREAM_CODEC = StreamCodec.composite(RadiationRegistry.Serialized.STREAM_CODEC, RadiationRegistryPayload::serializedRegistry, RadiationRegistryPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
