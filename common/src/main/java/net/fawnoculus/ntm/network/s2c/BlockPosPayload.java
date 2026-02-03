package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record BlockPosPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Identifier BLOCK_POS_PAYLOAD_ID = Ntm.id("block_pos");
    public static final Type<BlockPosPayload> ID = new Type<>(BLOCK_POS_PAYLOAD_ID);

    public static final StreamCodec<FriendlyByteBuf, BlockPosPayload> STREAM_CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, BlockPosPayload::pos, BlockPosPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
