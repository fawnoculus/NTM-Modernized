package net.fawnoculus.ntm.misc;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.UnaryOperator;

public class NtmDataComponentTypes {
    public static final RegistrySupplier<DataComponentType<AbilityHandler.StackData>> ABILITY_COMPONENT_TYPE =
      register("ability", stackDataBuilder -> stackDataBuilder.persistent(AbilityHandler.StackData.CODEC));
    public static final RegistrySupplier<DataComponentType<Integer>> COOLDOWN_COMPONENT_TYPE =
      register("cooldown", integerBuilder -> integerBuilder.persistent(Codec.INT));
    public static final RegistrySupplier<DataComponentType<Long>> ENERGY_COMPONENT_TYPE =
      register("energy", longBuilder -> longBuilder.persistent(Codec.LONG));
    public static final RegistrySupplier<DataComponentType<Long>> MAX_FLUID =
      register("max_fluid", longBuilder -> longBuilder.persistent(Codec.LONG));
    public static final RegistrySupplier<DataComponentType<BlockPos>> BLOCK_POS_COMPONENT_TYPE =
      register("block_pos", blockPosBuilder -> blockPosBuilder.persistent(BlockPos.CODEC));
    public static final RegistrySupplier<DataComponentType<BlockState>> BLOCK_STATE_COMPONENT_TYPE =
      register("block", blockBuilder -> blockBuilder.persistent(BlockState.CODEC));

    private static <T> RegistrySupplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
        return NtmDeferredRegistries.DATA_COMPONENTS.register(Ntm.id(name), builderUnaryOperator.apply(DataComponentType.builder())::build);
    }

    public static void init() {
    }
}
