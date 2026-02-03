package net.fawnoculus.ntm.neoforge.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fawnoculus.ntm.neoforge.NtmPlatformImpl;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {

    // method should be "lambda$bakeModels$0", but it really doesn't like that for some reason
    @WrapOperation(method = "method_68018", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/BlockStateModel$UnbakedRoot;bake(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/renderer/block/model/BlockStateModel;"))
    private static BlockStateModel wrapBlockModelBake(BlockStateModel.UnbakedRoot unbakedModel, BlockState state, ModelBaker baker, Operation<BlockStateModel> operation) {
        BlockStateModel.@Nullable UnbakedRoot override = NtmPlatformImpl.getBlockModelOverride(state);
        if (override == null) {
            return operation.call(unbakedModel, state, baker);
        }

        return override.bake(state, baker);
    }

    // method should be "lambda$bakeModels$1", but it really doesn't like that for some reason
    @WrapOperation(method = "method_68019", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;bake(Lnet/minecraft/client/renderer/item/ItemModel$BakingContext;)Lnet/minecraft/client/renderer/item/ItemModel;"))
    private ItemModel wrapItemModelBake(ItemModel.Unbaked unbakedModel, ItemModel.BakingContext bakeContext, Operation<ItemModel> operation, @Local(argsOnly = true) Identifier itemId) {
        ItemModel.@Nullable Unbaked override = NtmPlatformImpl.getItemModelOverride(itemId);
        if (override == null) {
            return operation.call(unbakedModel, bakeContext);
        }

        return override.bake(bakeContext);
    }
}
