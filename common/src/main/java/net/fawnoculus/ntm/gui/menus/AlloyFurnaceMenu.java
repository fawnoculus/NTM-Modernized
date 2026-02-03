package net.fawnoculus.ntm.gui.menus;

import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceBE;
import net.fawnoculus.ntm.gui.NtmMenuType;
import net.fawnoculus.ntm.gui.slots.ItemFuelSlot;
import net.fawnoculus.ntm.gui.slots.OutputSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class AlloyFurnaceMenu extends AbstractContainerMenu {
    private final AlloyFurnaceBE blockEntity;
    private final ContainerLevelAccess screenContext;

    // Client Constructor
    public AlloyFurnaceMenu(int containerId, Inventory playerInventory, BlockPos blockPos) {
        this(containerId, playerInventory, (AlloyFurnaceBE) playerInventory.player.level().getBlockEntity(blockPos));
    }

    // Common Constructor
    public AlloyFurnaceMenu(int containerId, @NotNull Inventory playerInventory, AlloyFurnaceBE blockEntity) {
        super(NtmMenuType.ALLOY_FURNACE.get(), containerId);

        this.blockEntity = blockEntity;
        assert this.blockEntity.getLevel() != null;
        this.screenContext = ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos());

        SimpleContainer blockInventory = this.blockEntity.getInventory();
        blockInventory.startOpen(playerInventory.player);
        checkContainerSize(blockInventory, 4);

        addPlayerInventory(playerInventory);
        addBlockInventory(blockInventory);
    }

    private void addBlockInventory(SimpleContainer inventory) {
        addSlot(new ItemFuelSlot(Objects.requireNonNull(blockEntity.getLevel()).fuelValues(), inventory, AlloyFurnaceBE.FUEL_SLOT_INDEX, 8, 36));
        addSlot(new Slot(inventory, AlloyFurnaceBE.INPUT_TOP_SLOT_INDEX, 80, 18));
        addSlot(new Slot(inventory, AlloyFurnaceBE.INPUT_BOTTOM_SLOT_INDEX, 80, 54));
        addSlot(new OutputSlot(inventory, AlloyFurnaceBE.OUTPUT_SLOT_INDEX, 134, 36));
    }

    private void addPlayerInventory(Inventory playerInventory) {
        addPartialPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addPartialPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int colum = 0; colum < 9; colum++) {
                addSlot(new Slot(playerInventory, 9 + (colum + (row * 9)), 8 + (colum * 18), 84 + (row * 18)));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int colum = 0; colum < 9; colum++) {
            addSlot(new Slot(playerInventory, colum, 8 + (colum * 18), 142));
        }
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        this.blockEntity.getInventory().stopOpen(player);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slotIndex) {
        return ItemStack.EMPTY;
    /* TODO: fix this
    ItemStack stack = ItemStack.EMPTY;
    Slot slot = getSlot(slotIndex);

    if (slot == null || !slot.hasStack()) {
      return ItemStack.EMPTY;
    }

    ItemStack inSlot = slot.getStack();
    stack = inSlot.copy();

    if (slotIndex < this.blockEntity.getInventory().size()) {
      if (!insertItem(inSlot, this.blockEntity.getInventory().size(), this.slots.size(), true)) {
        return ItemStack.EMPTY;
      }
    } else if (!insertItem(inSlot, 0, this.blockEntity.getInventory().size(), false)) {
      return ItemStack.EMPTY;
    }
    if (inSlot.isEmpty()) {
      slot.setStack(ItemStack.EMPTY);
    } else {
      slot.markDirty();
    }
    return stack;

     */
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return stillValid(screenContext, player, NtmBlocks.ALLOY_FURNACE.get());
    }

    public AlloyFurnaceBE getBlockEntity() {
        return this.blockEntity;
    }
}
