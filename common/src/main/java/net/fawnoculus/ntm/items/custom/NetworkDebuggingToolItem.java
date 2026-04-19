package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.api.node.Node;
import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.api.node.network.NodeNetwork;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Consumer;

public class NetworkDebuggingToolItem extends Item {
    public NetworkDebuggingToolItem(Properties settings) {
        super(settings);
    }

    private static long getConsumerCount(NodeNetwork network) {
        long consumers = 0;
        for (List<NodeValueContainer> containers : network.PRIORITISED_CONSUMERS.values()) {
            consumers += containers.size();
        }
        return consumers;
    }

    private static long getProviderCount(NodeNetwork network) {
        long providers = 0;
        for (List<NodeValueContainer> containers : network.PRIORITISED_PROVIDERS.values()) {
            providers += containers.size();
        }
        return providers;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        ServerLevel world = (ServerLevel) context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (player == null) {
            // how
            return InteractionResult.FAIL;
        }

        if (!(world.getBlockEntity(pos) instanceof Node clickedNode)) {
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_NOT_NODE).withStyle(ChatFormatting.RED), false);
            return InteractionResult.SUCCESS_SERVER;
        }
        NodeNetwork network = clickedNode.getNetwork();
        if (network == null) {
            player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_NODE_NO_NETWORK).withStyle(ChatFormatting.RED), false);
            return InteractionResult.SUCCESS_SERVER;
        }

        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_NETWORK_NAME, Component.literal(network.ID.toString()).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GOLD), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_NETWORK_TYPE, network.TYPE.getName().withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_NODE_COUNT, Component.literal(String.valueOf(network.LOADED_NODES.size())).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_PROVIDER_COUNT, Component.literal(String.valueOf(getProviderCount(network))).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_PROVIDER_PRIORITIES, Component.literal(network.REVERSED_PROVIDER_PRIORITIES.toString()).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_CONSUMER_COUNT, Component.literal(String.valueOf(getConsumerCount(network))).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);
        player.displayClientMessage(Component.translatable(NtmTranslations.MESSAGE_NETWORK_DEBUG_CONSUMER_PRIORITIES, Component.literal(network.REVERSED_CONSUMER_PRIORITIES.toString()).withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.YELLOW), false);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable(NtmTranslations.TOOLTIP_CREATIVE_ONLY).withStyle(ChatFormatting.GRAY));
        tooltip.accept(NtmTextUtil.tooltip(this, 1).withStyle(ChatFormatting.RED));
        tooltip.accept(NtmTextUtil.tooltip(this, 2).withStyle(ChatFormatting.RED));
    }
}
