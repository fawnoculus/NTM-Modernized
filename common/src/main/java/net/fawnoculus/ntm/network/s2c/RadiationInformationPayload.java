package net.fawnoculus.ntm.network.s2c;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.netty.buffer.ByteBuf;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.util.JsonUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;

import java.nio.charset.StandardCharsets;

public record RadiationInformationPayload(RadiationInfo info) implements CustomPacketPayload {
    public static final Identifier RADIATION_INFORMATION_PAYLOAD_ID = Ntm.id("radiation_information");
    public static final Type<RadiationInformationPayload> ID = new Type<>(RADIATION_INFORMATION_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, RadiationInformationPayload> STREAM_CODEC = StreamCodec.composite(RadiationInfo.STREAM_CODEC, RadiationInformationPayload::info, RadiationInformationPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public record RadiationInfo(
      @Range(from = 0, to = 1_000_000) double radiationExposure,
      double inventoryRadiation,
      double passiveChunkRadiation,
      double activeChunkRadiation
    ) {
        public static final StreamCodec<ByteBuf, RadiationInfo> STREAM_CODEC = new StreamCodec<>() {
            public RadiationInfo decode(ByteBuf byteBuf) {
                String string = new String(FriendlyByteBuf.readByteArray(byteBuf), StandardCharsets.UTF_8);
                return RadiationInfo.decode(JsonUtil.jsonFromString(string));
            }

            public void encode(ByteBuf byteBuf, RadiationInfo message) {
                JsonObject json = RadiationInfo.encode(message);
                FriendlyByteBuf.writeByteArray(byteBuf, json.toString().getBytes(StandardCharsets.UTF_8));
            }
        };

        private static JsonObject encode(RadiationInfo info) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("radiationExposure", new JsonPrimitive(info.radiationExposure));
            jsonObject.add("inventoryRadiation", new JsonPrimitive(info.inventoryRadiation));
            jsonObject.add("passiveChunkRadiation", new JsonPrimitive(info.passiveChunkRadiation));
            jsonObject.add("activeChunkRadiation", new JsonPrimitive(info.activeChunkRadiation));
            return jsonObject;

        }

        private static RadiationInfo decode(JsonObject json) {
            double radiationExposure = json.get("radiationExposure").getAsJsonPrimitive().getAsDouble();
            double inventoryRadiation = json.get("inventoryRadiation").getAsJsonPrimitive().getAsDouble();
            double passiveChunkRadiation = json.get("passiveChunkRadiation").getAsJsonPrimitive().getAsDouble();
            double activeChunkRadiation = json.get("activeChunkRadiation").getAsJsonPrimitive().getAsDouble();
            return new RadiationInfo(radiationExposure, inventoryRadiation, passiveChunkRadiation, activeChunkRadiation);
        }


    }
}
