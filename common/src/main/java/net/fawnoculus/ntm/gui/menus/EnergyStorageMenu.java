package net.fawnoculus.ntm.gui.menus;

import net.fawnoculus.ntm.blocks.entities.container.energy.SimpleEnergyStorageBE;
import net.fawnoculus.ntm.gui.NtmMenuType;
import net.fawnoculus.ntm.gui.slots.BatterySlot;
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

public class EnergyStorageMenu extends AbstractContainerMenu {
    private final SimpleEnergyStorageBE blockEntity;
    private final ContainerLevelAccess screenContext;

    // Client Constructor
    public EnergyStorageMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, (SimpleEnergyStorageBE) playerInventory.player.level().getBlockEntity(pos));
    }

    // Common Constructor
    public EnergyStorageMenu(int containerId, @NotNull Inventory playerInventory, SimpleEnergyStorageBE blockEntity) {
        super(NtmMenuType.ENERGY_STORAGE.get(), containerId);

        this.blockEntity = blockEntity;
        assert this.blockEntity.getLevel() != null;
        this.screenContext = ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos());

        SimpleContainer blockInventory = this.blockEntity.getInventory();
        blockInventory.startOpen(playerInventory.player);
        checkContainerSize(blockInventory, 2);

        addPlayerInventory(playerInventory);
        addBlockInventory(blockInventory);
    }

    private void addBlockInventory(SimpleContainer inventory) {
        addSlot(new BatterySlot(inventory, SimpleEnergyStorageBE.DISCHARGE_SLOT_INDEX, 26, 17));
        addSlot(new BatterySlot(inventory, SimpleEnergyStorageBE.CHARGE_SLOT_INDEX, 26, 53));
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
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slot) {
        // TODO: fix this
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return screenContext.evaluate((world, pos) -> player.isWithinBlockInteractionRange(pos, 4.0), true);
    }

    public SimpleEnergyStorageBE getBlockEntity() {
        return this.blockEntity;
    }
}
