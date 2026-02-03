package net.fawnoculus.ntm.gui.menus;

import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.blocks.entities.ElectricFurnaceBE;
import net.fawnoculus.ntm.gui.NtmMenuType;
import net.fawnoculus.ntm.gui.slots.BatterySlot;
import net.fawnoculus.ntm.gui.slots.OutputSlot;
import net.fawnoculus.ntm.gui.slots.UpgradeSlot;
import net.fawnoculus.ntm.network.s2c.BlockPosPayload;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class ElectricFurnaceMenu extends AbstractContainerMenu {
    private final ElectricFurnaceBE blockEntity;
    private final ContainerLevelAccess screenContext;
    private final ContainerData propertyDelegate;

    // Client Constructor
    public ElectricFurnaceMenu(int containerId, Inventory playerInventory, @NotNull BlockPosPayload payload) {
        this(containerId, playerInventory, (ElectricFurnaceBE) playerInventory.player.level().getBlockEntity(payload.pos()), new SimpleContainerData(2));
    }

    // Common Constructor
    public ElectricFurnaceMenu(int containerId, @NotNull Inventory playerInventory, ElectricFurnaceBE blockEntity, ContainerData propertyDelegate) {
        super(NtmMenuType.ELECTRIC_FURNACE.get(), containerId);

        this.blockEntity = blockEntity;
        assert this.blockEntity.getLevel() != null;
        this.screenContext = ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos());
        checkContainerDataCount(propertyDelegate, 2);
        this.propertyDelegate = propertyDelegate;
        addDataSlots(this.propertyDelegate);

        SimpleContainer blockInventory = this.blockEntity.getInventory();
        blockInventory.startOpen(playerInventory.player);
        checkContainerSize(blockInventory, 4);

        addPlayerInventory(playerInventory);
        addBlockInventory(blockInventory);
    }

    private void addBlockInventory(SimpleContainer inventory) {
        addSlot(new BatterySlot(inventory, ElectricFurnaceBE.BATTERY_SLOT_INDEX, 56, 53));
        addSlot(new Slot(inventory, ElectricFurnaceBE.INPUT_SLOT_INDEX, 56, 17));
        addSlot(new OutputSlot(inventory, ElectricFurnaceBE.OUTPUT_SLOT_INDEX, 116, 35));
        addSlot(new UpgradeSlot(inventory, ElectricFurnaceBE.UPGRADE_SLOT_INDEX, 147, 34));
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
        return stillValid(screenContext, player, NtmBlocks.ELECTRIC_FURNACE.get());
    }

    public ElectricFurnaceBE getBlockEntity() {
        return this.blockEntity;
    }

    public ContainerData getPropertyDelegate() {
        return this.propertyDelegate;
    }
}
