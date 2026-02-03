package net.fawnoculus.ntm.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceBE;
import net.fawnoculus.ntm.blocks.entities.AlloyFurnaceExtensionBE;
import net.fawnoculus.ntm.blocks.entities.ElectricFurnaceBE;
import net.fawnoculus.ntm.blocks.entities.container.energy.EnergyConnectorBE;
import net.fawnoculus.ntm.blocks.entities.container.energy.SimpleEnergyStorageBE;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class NtmBlockEntities {

    @SafeVarargs
    private static <E extends BlockEntity> RegistrySupplier<BlockEntityType<E>> register(String name, BiFunction<BlockPos, BlockState, E> blockEntityFactory, Supplier<Block>... blocks) {
        return NtmDeferredRegistries.BLOCK_ENTITIES.register(Ntm.id(name), NtmPlatform.makeBlockEntityType(Ntm.id(name), blockEntityFactory, blocks));
    }

    public static void init() {
    }

    public static final RegistrySupplier<BlockEntityType<AlloyFurnaceBE>> ALLOY_FURNACE_BE = register("alloy_furnace", AlloyFurnaceBE::new,
      NtmBlocks.ALLOY_FURNACE
    );
    public static final RegistrySupplier<BlockEntityType<AlloyFurnaceExtensionBE>> ALLOY_FURNACE_EXTENSION_BE = register("alloy_furnace_extension", AlloyFurnaceExtensionBE::new,
      NtmBlocks.ALLOY_FURNACE_EXTENSION
    );
    public static final RegistrySupplier<BlockEntityType<ElectricFurnaceBE>> ELECTRIC_FURNACE_BE = register("electric_furnace", ElectricFurnaceBE::new,
      NtmBlocks.ELECTRIC_FURNACE
    );
    public static final RegistrySupplier<BlockEntityType<EnergyConnectorBE>> SIMPLE_ENERGY_CONNECTOR_BE = register("simple_energy_connector", EnergyConnectorBE::new,
      NtmBlocks.TEMP_CABLE
    );
    public static final RegistrySupplier<BlockEntityType<SimpleEnergyStorageBE>> SIMPLE_ENERGY_STORAGE_BE = register("simple_energy_storage", SimpleEnergyStorageBE::new,
      NtmBlocks.POTATO_BATTERY_BLOCK,
      NtmBlocks.ENERGY_STORAGE_BLOCK,
      NtmBlocks.LITHIUM_ION_ENERGY_STORAGE_BLOCK,
      NtmBlocks.SCHRABIDIUM_ENERGY_STORAGE_BLOCK,
      NtmBlocks.SPARK_ENERGY_STORAGE_BLOCK
    );


}
