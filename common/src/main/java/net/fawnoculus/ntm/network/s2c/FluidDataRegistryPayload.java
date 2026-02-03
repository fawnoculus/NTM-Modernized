package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record FluidDataRegistryPayload(CompoundTag registryNBT) implements CustomPacketPayload {
    public static final Identifier FLUID_DATA_REGISTRY_PAYLOAD_ID = Ntm.id("fluid_data_registry");
    public static final Type<FluidDataRegistryPayload> ID = new Type<>(FLUID_DATA_REGISTRY_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidDataRegistryPayload> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.COMPOUND_TAG, FluidDataRegistryPayload::registryNBT, FluidDataRegistryPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
