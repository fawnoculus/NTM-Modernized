package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.items.components.NtmFoodComponents;
import net.fawnoculus.ntm.network.s2c.AdvancedMessagePayload;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class ScrapPancakeItem extends Item {
    public ScrapPancakeItem(Properties settings) {
        super(settings.food(NtmFoodComponents.SCRAP_PANCAKE));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, @NonNull Player player, @NonNull InteractionHand hand) {
        // TODO: this, once we have lunar cybernetic armor
        NetworkManager.sendToPlayer((ServerPlayer) player, new AdvancedMessagePayload(new AdvancedMessage(
          Ntm.id("scrap_pancake"),
          Component.translatable("message.ntm.teeth_to_soft").withStyle(ChatFormatting.YELLOW),
          1000.0f)));
        return InteractionResult.FAIL;
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, @NonNull LivingEntity user) {
        // TODO: this, once we have lunar cybernetic armor
        return super.finishUsingItem(stack, world, user);
    }
}
