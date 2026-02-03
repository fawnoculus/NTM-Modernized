package net.fawnoculus.ntm.blocks.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

/**
 * An Interface for Block Entities to respond Clients to sending Interaction Packets
 */
public interface InteractableBE {
    void onInteraction(ServerPlayer source, Identifier action, CompoundTag extraData);
}
