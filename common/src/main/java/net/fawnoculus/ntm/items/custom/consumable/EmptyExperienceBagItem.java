package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.misc.NtmSounds;
import net.fawnoculus.ntm.network.s2c.AdvancedMessagePayload;
import net.fawnoculus.ntm.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class EmptyExperienceBagItem extends Item {
    public static final int XP_PER_BAG = 500;

    public EmptyExperienceBagItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, Player player, @NonNull InteractionHand hand) {
        if (player.totalExperience < XP_PER_BAG) {
            if (!world.isClientSide()) {
                NetworkManager.sendToPlayer((ServerPlayer) player, new AdvancedMessagePayload(new AdvancedMessage(
                  Ntm.id("empty_xp_bag"),
                  Component.translatable("message.ntm.not_enough_xp").withStyle(ChatFormatting.RED),
                  1000.0f))
                );
            }
            return InteractionResult.FAIL;
        }
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (!player.isCreative()) {
            ItemStack stack = player.getItemInHand(hand);
            stack.shrink(1);
        }
        world.playSound(null, BlockPos.containing(player.position()).above(), NtmSounds.IV_BAG_INJECTS.get(), SoundSource.PLAYERS);
        PlayerUtil.removeExperience(player, XP_PER_BAG);
        player.getInventory().placeItemBackInInventory(new ItemStack(NtmItems.EXPERIENCE_BAG));

        return InteractionResult.SUCCESS_SERVER;
    }
}
