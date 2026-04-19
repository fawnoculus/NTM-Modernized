package net.fawnoculus.ntm.gui.menus;

import net.fawnoculus.ntm.gui.NtmMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class EnergyStorageMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess screenContext;

    // Client Constructor
    public EnergyStorageMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(pos));
    }

    // Common Constructor
    public EnergyStorageMenu(int containerId, @NotNull Inventory playerInventory, BlockEntity blockEntity) {
        super(NtmMenuType.ENERGY_STORAGE.get(), containerId);

        assert blockEntity.getLevel() != null;
        this.screenContext = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        addPlayerInventory(playerInventory);
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
}
