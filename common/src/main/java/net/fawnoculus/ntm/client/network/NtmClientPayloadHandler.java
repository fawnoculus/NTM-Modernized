package net.fawnoculus.ntm.client.network;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.client.api.messages.MessageSystem;
import net.fawnoculus.ntm.client.api.radiation.ClientHazmatRegistry;
import net.fawnoculus.ntm.client.api.radiation.ClientRadiationManager;
import net.fawnoculus.ntm.client.api.radiation.ClientRadiationRegistry;
import net.fawnoculus.ntm.client.data.ClientFluidDataRegistry;
import net.fawnoculus.ntm.network.s2c.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class NtmClientPayloadHandler {
    public static void init() {
        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          AdvancedMessagePayload.ID,
          AdvancedMessagePayload.STREAM_CODEC,
          (payload, context) -> MessageSystem.addMessage(payload.message())
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          RemoveMessagePayload.ID,
          RemoveMessagePayload.STREAM_CODEC,
          (payload, context) -> MessageSystem.removeMessage(payload.identifier())
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          RemoveAllMessagesPayload.ID,
          RemoveAllMessagesPayload.STREAM_CODEC,
          (payload, context) -> MessageSystem.removeAllMessages()
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          RadiationInformationPayload.ID,
          RadiationInformationPayload.STREAM_CODEC,
          ClientRadiationManager::handlePacket
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          RadiationRegistryPayload.ID,
          RadiationRegistryPayload.STREAM_CODEC,
          ClientRadiationRegistry::updateFromPacket
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          HazmatRegistryPayload.ID,
          HazmatRegistryPayload.STREAM_CODEC,
          ClientHazmatRegistry::updateFromPacket
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          FluidDataRegistryPayload.ID,
          FluidDataRegistryPayload.STREAM_CODEC,
          ClientFluidDataRegistry::updateFromPacket
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          NtmVersionPayload.ID,
          NtmVersionPayload.STREAM_CODEC,
          ClientReceivedVersionHandler::handlePacket
        );

        NetworkManager.registerReceiver(
          NetworkManager.Side.S2C,
          InventorySyncPayload.ID,
          InventorySyncPayload.STREAM_CODEC,
          NtmClientPayloadHandler::handleInventorySync
        );
    }

    private static void handleInventorySync(InventorySyncPayload payload, @NotNull NetworkManager.PacketContext context) {
        if (!(context.getPlayer().level() instanceof ClientLevel world)) {
            return;
        }

        BlockEntity be = world.getBlockEntity(payload.pos());
        if (be instanceof Container inventory) {
            int i = 0;
            for (ItemStack stack : payload.inventory()) {
                inventory.setItem(i, stack);
                i++;
            }

            world.sendBlockUpdated(payload.pos(), be.getBlockState(), be.getBlockState(), Block.UPDATE_ALL);
        }
    }

}
