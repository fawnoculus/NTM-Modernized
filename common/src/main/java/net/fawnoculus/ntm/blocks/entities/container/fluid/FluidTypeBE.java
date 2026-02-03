package net.fawnoculus.ntm.blocks.entities.container.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public abstract class FluidTypeBE extends FluidBE {
    private Holder<Fluid> fluidType = BuiltInRegistries.FLUID.wrapAsHolder(Fluids.EMPTY);

    public FluidTypeBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public @Nullable Fluid getNodeFluid() {
        return fluidType.value();
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        this.fluidType = input.read("fluidType", BuiltInRegistries.FLUID.holderByNameCodec()).orElse(BuiltInRegistries.FLUID.wrapAsHolder(Fluids.EMPTY));
        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.store("fluidType", BuiltInRegistries.FLUID.holderByNameCodec(), this.fluidType);
    }
}
