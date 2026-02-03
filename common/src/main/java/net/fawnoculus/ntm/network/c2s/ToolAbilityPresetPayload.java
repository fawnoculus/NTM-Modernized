package net.fawnoculus.ntm.network.c2s;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ToolAbilityPresetPayload(AbilityHandler.StackData stackData) implements CustomPacketPayload {
    public static final Identifier TOOL_ABILITY_PRESET_PAYLOAD_ID = Ntm.id("tool_ability_preset");
    public static final Type<ToolAbilityPresetPayload> ID = new Type<>(TOOL_ABILITY_PRESET_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ToolAbilityPresetPayload> STREAM_CODEC = StreamCodec.composite(
      AbilityHandler.StackData.STREAM_CODEC, ToolAbilityPresetPayload::stackData,
      ToolAbilityPresetPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
