package net.fawnoculus.ntm.blocks.entities.container.energy;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.network.s2c.InventorySyncPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;


public abstract class EnergyInventoryBE extends EnergyBE implements Container {
    private final SimpleContainer inventory;

    public EnergyInventoryBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySlots) {
        super(type, pos, state);

        EnergyInventoryBE be = this;
        this.inventory = new SimpleContainer(inventorySlots) {
            @Override
            public void setChanged() {
                super.setChanged();
                be.setChanged();
            }
        };
    }

    public static void sendSyncInventoryPacket(Level world, BlockPos pos, SimpleContainer inventory) {
        if (!(world instanceof ServerLevel serverWorld)) return;
        int viewDistance = serverWorld.getServer().getPlayerList().getViewDistance();
        NetworkManager.sendToPlayers(
          serverWorld.getPlayers(serverPlayer -> serverPlayer.getPosition(1f).closerThan(pos.getCenter(), viewDistance)),
          new InventorySyncPayload(pos, inventory)
        );
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public boolean canInsertIntoSlot(int slotIndex, ItemStack stack) {
        ItemStack switchStack = this.getInventory().getItem(slotIndex);
        if (switchStack.isEmpty()) return true;

        if (switchStack.getItem() == stack.getItem()) {
            return switchStack.getCount() + stack.getCount() <= switchStack.getMaxStackSize();
        }

        return false;
    }

    @Override
    public int getContainerSize() {
        return this.getInventory().getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return this.getInventory().isEmpty();
    }

    @Override
    public @NonNull ItemStack getItem(int slot) {
        return this.getInventory().getItem(slot);
    }

    @Override
    public @NonNull ItemStack removeItem(int slot, int amount) {
        return this.getInventory().removeItem(slot, amount);
    }

    @Override
    public @NonNull ItemStack removeItemNoUpdate(int slot) {
        return this.getInventory().removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, @NonNull ItemStack stack) {
        this.getInventory().setItem(slot, stack);
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return this.getInventory().stillValid(player);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        if (this.level != null && !this.level.isClientSide()) {
            sendSyncInventoryPacket(this.level, this.worldPosition, this.getInventory());
        }
    }

    @Override
    public void clearContent() {
        inventory.clearContent();
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.inventory.getItems());
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.getInventory().getItems());
        super.saveAdditional(output);
    }
}
