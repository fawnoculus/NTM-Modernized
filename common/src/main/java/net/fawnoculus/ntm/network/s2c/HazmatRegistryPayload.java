package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.radiation.HazmatRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record HazmatRegistryPayload(HazmatRegistry.Serialized serializedRegistry) implements CustomPacketPayload {
    public static final Identifier HAZMAT_REGISTRY_PAYLOAD_ID = Ntm.id("hazmat_registry");
    public static final Type<HazmatRegistryPayload> ID = new Type<>(HAZMAT_REGISTRY_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, HazmatRegistryPayload> STREAM_CODEC = StreamCodec.composite(HazmatRegistry.Serialized.STREAM_CODEC, HazmatRegistryPayload::serializedRegistry, HazmatRegistryPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
