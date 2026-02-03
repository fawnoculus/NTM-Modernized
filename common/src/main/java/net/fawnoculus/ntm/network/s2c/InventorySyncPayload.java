package net.fawnoculus.ntm.network.s2c;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public record InventorySyncPayload(BlockPos pos, SimpleContainer inventory) implements CustomPacketPayload {
    public static final Identifier GENERIC_INVENTORY_PAYLOAD_ID = Ntm.id("inventory_sync");
    public static final Type<InventorySyncPayload> ID = new Type<>(GENERIC_INVENTORY_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, SimpleContainer> INVENTORY_CODEC = new StreamCodec<>() {
        @Override
        public SimpleContainer decode(RegistryFriendlyByteBuf buf) {
            int size = buf.readVarInt();

            SimpleContainer inventory = new SimpleContainer(size);
            for (int i = 0; i < size; i++) {
                inventory.setItem(i, ItemStack.OPTIONAL_STREAM_CODEC.decode(buf));
            }

            return inventory;
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, SimpleContainer inventory) {
            buf.writeVarInt(inventory.getContainerSize());

            for (ItemStack stack : inventory) {
                ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, stack);
            }
        }
    };

    public static final StreamCodec<RegistryFriendlyByteBuf, InventorySyncPayload> STREAM_CODEC = StreamCodec.composite(
      BlockPos.STREAM_CODEC, InventorySyncPayload::pos,
      INVENTORY_CODEC, InventorySyncPayload::inventory,
      InventorySyncPayload::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
