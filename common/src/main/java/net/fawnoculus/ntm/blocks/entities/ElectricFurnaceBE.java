package net.fawnoculus.ntm.blocks.entities;

import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.custom.AlloyFurnaceBlock;
import net.fawnoculus.ntm.blocks.custom.ElectricFurnaceBlock;
import net.fawnoculus.ntm.blocks.entities.container.energy.EnergyInventoryBE;
import net.fawnoculus.ntm.gui.NtmMenuType;
import net.fawnoculus.ntm.items.custom.container.energy.EnergyContainingItem;
import net.fawnoculus.ntm.misc.stack.EnergyStack;
import net.minecraft.core.BlockPos;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ElectricFurnaceBE extends EnergyInventoryBE implements MenuProvider {
    public static final Component NAME = Component.translatable("container.ntm.electric_furnace");

    public static final int OUTPUT_SLOT_INDEX = 0;
    public static final int INPUT_SLOT_INDEX = 1;
    public static final int BATTERY_SLOT_INDEX = 2;
    public static final int UPGRADE_SLOT_INDEX = 3;
    public final EnergyStack energy = new EnergyStack(this).setMaxValue(100_000).setConsumes(true).onChange(this::setChanged);
    private final ContainerData propertyDelegate;
    private double progress = 0;

    public ElectricFurnaceBE(BlockPos pos, BlockState state) {
        super(NtmBlockEntities.ELECTRIC_FURNACE_BE.get(), pos, state, 4);
        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) progress;
                    case 1 -> getRequiredProgress();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    progress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public static void tick(Level ignored, BlockPos ignored2, BlockState ignored3, ElectricFurnaceBE entity) {
        entity.processBattery();
        if (entity.canCraft()) {
            entity.addProgress();
            if (entity.progressFinished()) {
                entity.craftOutput();
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
        }
        entity.setChanged();
    }

    private Optional<RecipeHolder<SmeltingRecipe>> getRecipe() {
        if (!(this.level instanceof ServerLevel serverWorld)) {
            return Optional.empty();
        }
        SingleRecipeInput recipeInput = new SingleRecipeInput(getItem(INPUT_SLOT_INDEX));
        return RecipeManager.createCheck(RecipeType.SMELTING).getRecipeFor(recipeInput, serverWorld);
    }

    private int getRequiredProgress() {
        return this.getRecipe()
          .map(recipeEntry -> recipeEntry.value().cookingTime())
          .orElse(0);
    }

    private double getProgressPerTick() {
        // TODO: upgrades People, Upgrades
        return 1;
    }

    private long getEnergyPerTick() {
        // TODO: upgrades People, Upgrades
        return 50;
    }

    private void craftOutput() {
        Optional<RecipeHolder<SmeltingRecipe>> optional = this.getRecipe();
        if (optional.isEmpty()) return;

        RecipeHolder<SmeltingRecipe> recipe = optional.get();
        ItemStack output = recipe.value().assemble(new SingleRecipeInput(getItem(INPUT_SLOT_INDEX)), VanillaRegistries.createLookup());

        output.setCount(getItem(OUTPUT_SLOT_INDEX).getCount() + 1);
        setItem(OUTPUT_SLOT_INDEX, output);

        getItem(INPUT_SLOT_INDEX).shrink(1);
    }

    private boolean canCraft() {
        return this.energy.getValue() >= this.getEnergyPerTick()
          && this.getRecipe().isPresent()
          && canInsertIntoSlot(OUTPUT_SLOT_INDEX, this.getRecipe().get().value().assemble(new SingleRecipeInput(getItem(INPUT_SLOT_INDEX)), VanillaRegistries.createLookup()));
    }

    private boolean progressFinished() {
        return this.progress >= this.getRequiredProgress();
    }

    private void addProgress() {
        this.progress += this.getProgressPerTick();
        this.energy.remove(this.getEnergyPerTick());

        if (this.level != null) {
            BlockState state = this.level.getBlockState(this.worldPosition).setValue(AlloyFurnaceBlock.LIT, true);
            this.level.setBlockAndUpdate(this.worldPosition, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;

        if (this.level != null && !this.canCraft()) {
            BlockState state = this.level.getBlockState(this.worldPosition).setValue(ElectricFurnaceBlock.LIT, false);
            this.level.setBlockAndUpdate(this.worldPosition, state);
        }
    }

    private void processBattery() {
        ItemStack stack = getItem(BATTERY_SLOT_INDEX);
        if (stack.getItem() instanceof EnergyContainingItem energyContainingItem) {
            energyContainingItem.discharge(stack, this.energy);
        }
    }

    @Override
    public Collection<NodeValueContainer> getContainers() {
        return List.of(this.energy);
    }


    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        this.energy.readData(input);
        this.progress = input.getDoubleOr("progress", 0);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        output.putDouble("progress", this.progress);
        this.energy.writeData(output);
        super.saveAdditional(output);
    }

    public double getProgress(int requiredProgress) {
        return progress / requiredProgress;
    }

    public boolean showFireInGUI() {
        if (this.level == null) return false;
        return this.level.getBlockState(this.worldPosition).getValue(AlloyFurnaceBlock.LIT);
    }

    @Override
    public @NonNull Component getDisplayName() {
        return NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return NtmMenuType.ELECTRIC_FURNACE.get().create(containerId, playerInventory);
    }
}
