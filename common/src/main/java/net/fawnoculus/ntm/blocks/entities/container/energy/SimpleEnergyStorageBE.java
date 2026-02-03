package net.fawnoculus.ntm.blocks.entities.container.energy;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.api.node.StorageMode;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.entities.InteractableBE;
import net.fawnoculus.ntm.gui.NtmMenuProvider;
import net.fawnoculus.ntm.gui.menus.EnergyStorageMenu;
import net.fawnoculus.ntm.items.custom.container.energy.EnergyContainingItem;
import net.fawnoculus.ntm.misc.stack.EnergyStack;
import net.fawnoculus.ntm.util.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

public class SimpleEnergyStorageBE extends EnergyInventoryBE implements NtmMenuProvider<BlockPos>, InteractableBE {
    public static final Component NAME = Component.translatable("container.ntm.energy_storage");

    public static final int DISCHARGE_SLOT_INDEX = 0;
    public static final int CHARGE_SLOT_INDEX = 1;
    public static final Identifier CYCLE_POWERED_MODE = Ntm.id("cycle_powered_mode");
    public static final Identifier CYCLE_UNPOWERED_MODE = Ntm.id("cycle_unpowered_mode");
    public final EnergyStack.Storage energy = new EnergyStack.Storage(this).setStorageMode(StorageMode.Consume).onChange(this::setChanged);
    public boolean isPowered = false;
    public StorageMode poweredMode = StorageMode.Provide;
    public StorageMode unpoweredMode = StorageMode.Consume;
    public long previousEnergy = 0;
    public long[] energyChange = new long[20];
    public int energyChangeIndex = 0;

    public SimpleEnergyStorageBE(BlockPos pos, BlockState state) {
        super(NtmBlockEntities.SIMPLE_ENERGY_STORAGE_BE.get(), pos, state, 2);
    }

    public static void tick(Level ignored, BlockPos ignored2, BlockState ignored3, @NotNull SimpleEnergyStorageBE entity) {
        entity.processBatteries();
        entity.updateEnergyChange();
        entity.setChanged();
    }

    private void processBatteries() {
        ItemStack dischargeStack = getItem(DISCHARGE_SLOT_INDEX);
        if (dischargeStack.getItem() instanceof EnergyContainingItem energyContainingItem) {
            energyContainingItem.discharge(dischargeStack, this.energy);
        }
        ItemStack chargeStack = getItem(CHARGE_SLOT_INDEX);
        if (chargeStack.getItem() instanceof EnergyContainingItem energyContainingItem) {
            energyContainingItem.charge(chargeStack, this.energy);
        }
    }

    private void updateEnergyChange() {
        if (this.energyChangeIndex >= 20 || this.energyChangeIndex < 0) {
            this.energyChangeIndex = 0;
        }
        long energyDifference = this.energy.getValue() - this.previousEnergy;
        this.energyChange[this.energyChangeIndex] = energyDifference;
        this.energyChangeIndex++;
        this.previousEnergy = this.energy.getValue();
    }

    public void onBlockUpdate() {
        if (this.level != null) {
            boolean isNowPowered = this.level.getBestNeighborSignal(this.worldPosition) > 0;
            if (isNowPowered == isPowered) return;

            if (isNowPowered) {
                this.energy.setStorageMode(this.poweredMode);
            } else {
                this.energy.setStorageMode(this.unpoweredMode);
            }

            this.isPowered = isNowPowered;
        }
    }

    public Component getEnergyPerSec() {
        OptionalDouble optional = Arrays.stream(energyChange).average();
        long energyPerSec = (long) optional.orElse(0);
        if (energyPerSec < 0) {
            return TextUtil.unit(energyPerSec, "generic.ntm.energy_s").withStyle(ChatFormatting.RED);
        }

        ChatFormatting formatting = ChatFormatting.YELLOW;
        if (energyPerSec > 0) formatting = ChatFormatting.GREEN;

        return Component.literal("+").append(TextUtil.unit(energyPerSec, "generic.ntm.energy_s")).withStyle(formatting);
    }

    @Override
    public Collection<NodeValueContainer> getContainers() {
        return List.of(this.energy);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        this.energy.readData(input);

        input.read("powered_mode", StorageMode.CODEC).ifPresent(mode -> this.poweredMode = mode);
        input.read("unpowered_mode", StorageMode.CODEC).ifPresent(mode -> this.unpoweredMode = mode);
        this.isPowered = input.getBooleanOr("is_powered", false);
        this.energyChangeIndex = Math.clamp(
          input.getIntOr("energy_change_index", 0), 0, energyChange.length - 1
        );

        for (int i = 0; i < energyChange.length; i++) {
            energyChange[i] = input.getLongOr("energy_change_" + i, 0);
        }
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        output.store("powered_mode", StorageMode.CODEC, this.poweredMode);
        output.store("unpowered_mode", StorageMode.CODEC, this.unpoweredMode);
        output.putBoolean("is_powered", this.isPowered);
        output.putInt("energy_change_index", this.energyChangeIndex);
        for (int i = 0; i < energyChange.length; i++) {
            output.putLong("energy_change_" + i, energyChange[i]);
        }

        this.energy.writeData(output);

        super.saveAdditional(output);
    }

    @Override
    public @NonNull Component getDisplayName() {
        return NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return new EnergyStorageMenu(containerId, playerInventory, this);
    }

    @Override
    public void onInteraction(ServerPlayer source, Identifier action, CompoundTag extraData) {
        if (action.equals(CYCLE_POWERED_MODE)) {
            if (this.isPowered) {
                this.energy.setStorageMode(this.poweredMode.cycle());
            }
            this.poweredMode = this.poweredMode.cycle();
        } else if (action.equals(CYCLE_UNPOWERED_MODE)) {
            if (!this.isPowered) {
                this.energy.setStorageMode(this.unpoweredMode.cycle());
            }
            this.unpoweredMode = this.unpoweredMode.cycle();
        }
    }

    @Override
    public StreamCodec<? super FriendlyByteBuf, BlockPos> getCodec() {
        return BlockPos.STREAM_CODEC;
    }

    @Override
    public BlockPos getData() {
        return this.getBlockPos();
    }
}
