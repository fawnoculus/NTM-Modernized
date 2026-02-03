package net.fawnoculus.ntm.network.c2s;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ItemInteractionPayload(Identifier action, CompoundTag extraData) implements CustomPacketPayload {
    public static final Identifier ITEM_INTERACTION_PAYLOAD_ID = Ntm.id("item_interaction");
    public static final Type<ItemInteractionPayload> ID = new Type<>(ITEM_INTERACTION_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemInteractionPayload> STREAM_CODEC = StreamCodec.composite(
      Identifier.STREAM_CODEC, ItemInteractionPayload::action,
      ByteBufCodecs.COMPOUND_TAG, ItemInteractionPayload::extraData,
      ItemInteractionPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
