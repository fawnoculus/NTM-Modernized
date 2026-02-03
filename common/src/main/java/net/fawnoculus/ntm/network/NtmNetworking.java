package net.fawnoculus.ntm.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.NetworkManager.PacketContext;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.fawnoculus.ntm.blocks.entities.InteractableBE;
import net.fawnoculus.ntm.items.custom.InteractableItem;
import net.fawnoculus.ntm.network.c2s.BEInteractionPayload;
import net.fawnoculus.ntm.network.c2s.ItemInteractionPayload;
import net.fawnoculus.ntm.network.c2s.ToolAbilityPresetPayload;
import net.fawnoculus.ntm.network.s2c.AdvancedMessagePayload;
import net.fawnoculus.ntm.util.WorldUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class NtmNetworking {
    public static void init() {
        // It appears that Architectury only requires you to register S2C payloads & C2S payloads are done automatically
        /*
        NetworkManager.registerS2CPayloadType(AdvancedMessagePayload.ID, AdvancedMessagePayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(RemoveMessagePayload.ID, RemoveMessagePayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(RemoveAllMessagesPayload.ID, RemoveAllMessagesPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(RadiationInformationPayload.ID, RadiationInformationPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(RadiationRegistryPayload.ID, RadiationRegistryPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(HazmatRegistryPayload.ID, HazmatRegistryPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(FluidDataRegistryPayload.ID, FluidDataRegistryPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(NtmVersionPayload.ID, NtmVersionPayload.STREAM_CODEC);
        NetworkManager.registerS2CPayloadType(InventorySyncPayload.ID, InventorySyncPayload.STREAM_CODEC);

         */

        /* Backup in case Architectury changes its mind on not needing to register C2S payloads
        NetworkManager.registerC2SPayloadType(BEInteractionPayload.ID, BEInteractionPayload.STREAM_CODEC);
        NetworkManager.registerC2SPayloadType(ItemInteractionPayload.ID, ItemInteractionPayload.STREAM_CODEC);
        NetworkManager.registerC2SPayloadType(ToolAbilityPresetPayload.ID, ToolAbilityPresetPayload.STREAM_CODEC);
         */

        NetworkManager.registerReceiver(
          NetworkManager.Side.C2S,
          BEInteractionPayload.ID,
          BEInteractionPayload.STREAM_CODEC,
          NtmNetworking::handleBEInteractionPayload
        );
        NetworkManager.registerReceiver(
          NetworkManager.Side.C2S,
          ItemInteractionPayload.ID,
          ItemInteractionPayload.STREAM_CODEC,
          NtmNetworking::handleItemInteractionPayload
        );
        NetworkManager.registerReceiver(
          NetworkManager.Side.C2S,
          ToolAbilityPresetPayload.ID,
          ToolAbilityPresetPayload.STREAM_CODEC,
          NtmNetworking::handleToolAbilityPresetPayload
        );
    }

    private static void handleBEInteractionPayload(BEInteractionPayload payload, PacketContext context) {
        if (!(context.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        if (player.getEyePosition().distanceTo(WorldUtil.getVec3d(payload.pos())) > player.blockInteractionRange() + 1) {
            if (NtmConfig.DEV_MODE.getValue()) {
                Ntm.LOGGER.warn("Player '{}' tried to use action '{}' on BE at '{}' but was to far away", player.getName().tryCollapseToString(), payload.action().toString(), payload.pos().toShortString());
            }
            return;
        }

        @SuppressWarnings("all") // ignore ServerLevel being an auto-closable
        ServerLevel world = player.level();
        if (world.getBlockEntity(payload.pos()) instanceof InteractableBE interactableBE) {
            interactableBE.onInteraction(player, payload.action(), payload.extraData());
        }
    }

    private static void handleItemInteractionPayload(ItemInteractionPayload payload, PacketContext context) {
        if (!(context.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        if (player.getMainHandItem().getItem() instanceof InteractableItem interactableItem) {
            interactableItem.onInteraction(player, player.getMainHandItem(), payload.action(), payload.extraData());
        }
    }

    private static void handleToolAbilityPresetPayload(ToolAbilityPresetPayload payload, PacketContext context) {
        if (!(context.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack stack = player.getMainHandItem();

        if (stack.getItem() instanceof SpecialTool specialTool
          && specialTool.abilityHandler().verifyPresets(payload.stackData().presets())
          && payload.stackData().isValid()
        ) {
            specialTool.abilityHandler().setStackData(stack, payload.stackData());

            NetworkManager.sendToPlayer(player, new AdvancedMessagePayload(
              new AdvancedMessage(SpecialTool.ADVANCED_MESSAGE_ID, specialTool.abilityHandler().changeMessage(stack), 1000f)
            ));
        }
    }
}
