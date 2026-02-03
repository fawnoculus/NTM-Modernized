package net.fawnoculus.ntm.client.render;

import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.client.render.model.BlockModel3D;
import net.fawnoculus.ntm.client.render.model.ItemModel3D;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class NtmModelOverrides {
    private static final HashMap<Identifier, @NotNull Function<BlockState, ? extends BlockStateModel.@NotNull UnbakedRoot>> BLOCK_MODEL_OVERRIDES = new HashMap<>();
    private static final HashMap<Identifier, @NotNull Supplier<? extends ItemModel.@NotNull Unbaked>> ITEM_MODEL_OVERRIDES = new HashMap<>();

    public static void addBlock(
      @NotNull Block block,
      @NotNull Supplier<? extends ItemModel.@NotNull Unbaked> itemModel,
      @NotNull Function<BlockState, ? extends BlockStateModel.@NotNull UnbakedRoot> blockModel
    ) {
        addItem(block.asItem(), itemModel);
        addBlock(block, blockModel);
    }

    public static void addBlock(
      @NotNull Identifier identifier,
      @NotNull Supplier<? extends ItemModel.@NotNull Unbaked> itemModel,
      @NotNull Function<BlockState, ? extends BlockStateModel.@NotNull UnbakedRoot> blockModel
    ) {
        addItem(identifier, itemModel);
        addBlock(identifier, blockModel);
    }

    public static void addItem(@NotNull ItemLike itemConvertible, @NotNull Supplier<? extends ItemModel.@NotNull Unbaked> model) {
        ITEM_MODEL_OVERRIDES.put(BuiltInRegistries.ITEM.getKey(itemConvertible.asItem()), model);
    }

    public static void addItem(@NotNull Identifier itemId, @NotNull Supplier<? extends ItemModel.@NotNull Unbaked> model) {
        ITEM_MODEL_OVERRIDES.put(itemId, model);
    }

    public static void addBlock(@NotNull Block block, @NotNull Function<BlockState, ? extends BlockStateModel.@NotNull UnbakedRoot> model) {
        BLOCK_MODEL_OVERRIDES.put(BuiltInRegistries.BLOCK.getKey(block), model);
    }

    public static void addBlock(@NotNull Identifier blockId, @NotNull Function<BlockState, ? extends BlockStateModel.@NotNull UnbakedRoot> model) {
        BLOCK_MODEL_OVERRIDES.put(blockId, model);
    }

    private static BlockStateModel.@Nullable UnbakedRoot getBlockModel(BlockState state) {
        Function<BlockState, ? extends BlockStateModel.UnbakedRoot> override = BLOCK_MODEL_OVERRIDES.get(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
        if (override == null) {
            return null;
        }

        return override.apply(state);
    }

    private static ItemModel.@Nullable Unbaked getItemModel(Identifier identifier) {
        Supplier<? extends ItemModel.Unbaked> override = ITEM_MODEL_OVERRIDES.get(identifier);
        if (override == null) {
            return null;
        }

        return override.get();
    }

    private static Identifier blockID(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/");
    }

    public static void init() {
        NtmPlatform.registerItemModelOverride(NtmModelOverrides::getItemModel);
        NtmPlatform.registerBlockModelOverride(NtmModelOverrides::getBlockModel);

        addBlock(NtmBlocks.ALLOY_FURNACE_EXTENSION.getId(),
          () -> new ItemModel3D.Unbaked(NtmWavefrontModels.ALLOY_FURNACE_EXTENSION, blockID(NtmBlocks.ALLOY_FURNACE_EXTENSION.get())),
          ignored -> new BlockModel3D.MultipartUnbaked(NtmWavefrontModels.ALLOY_FURNACE_EXTENSION, blockID(NtmBlocks.ALLOY_FURNACE_EXTENSION.get()))
        );
    }
}
