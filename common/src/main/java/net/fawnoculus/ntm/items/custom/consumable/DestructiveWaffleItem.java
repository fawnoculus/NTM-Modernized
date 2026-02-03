package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.items.components.NtmFoodComponents;
import net.fawnoculus.ntm.network.s2c.AdvancedMessagePayload;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class DestructiveWaffleItem extends Item {
    public DestructiveWaffleItem(Properties settings) {
        super(settings.food(NtmFoodComponents.WAFFLE_OF_MASS_DESTRUCTION));
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, @NonNull LivingEntity user) {
        if (user instanceof ServerPlayer player) {
            NetworkManager.sendToPlayer(player, new AdvancedMessagePayload(new AdvancedMessage(
              Ntm.id("waffle_of_mass_destruction"),
              Component.literal("Now you would violently Explode, but Nuclear Explosions are not implemented yet").withStyle(ChatFormatting.RED),
              4000.0f))
            );
        }
        // TODO: this, once we have Nuclear Explosions
        return super.finishUsingItem(stack, world, user);
    }
}
