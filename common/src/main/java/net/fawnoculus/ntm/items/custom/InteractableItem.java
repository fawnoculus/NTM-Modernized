package net.fawnoculus.ntm.items.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

/**
 * An Interface for Items to respond Clients to sending Interaction Packets
 */
public interface InteractableItem {
    void onInteraction(ServerPlayer source, ItemStack heldStack, Identifier action, CompoundTag extraData);
}
