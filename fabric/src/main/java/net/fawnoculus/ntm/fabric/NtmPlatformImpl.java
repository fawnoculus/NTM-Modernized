package net.fawnoculus.ntm.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fawnoculus.ntm.NtmPlatform;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @see NtmPlatform
 */
@SuppressWarnings("unused")
public class NtmPlatformImpl {
    @SafeVarargs
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> makeBlockEntityType(
      Identifier identifier,
      BiFunction<BlockPos, BlockState, T> blockEntityFactory,
      Supplier<Block>... blocks
    ) {
        return () -> {
            FabricBlockEntityTypeBuilder<T> blockEntityTypeBuilder = FabricBlockEntityTypeBuilder.create(blockEntityFactory::apply);
            for (Supplier<Block> block : blocks) {
                blockEntityTypeBuilder.addBlock(block.get());
            }
            return blockEntityTypeBuilder.build();
        };
    }

    public static void modifyCreativeTab(ResourceKey<CreativeModeTab> resourceKey, Consumer<NtmPlatform.TabModifier> modifications) {
        ItemGroupEvents.modifyEntriesEvent(resourceKey).register(entries -> modifications.accept(entries::accept));
    }

    @Environment(EnvType.CLIENT)
    public static void addHudElementFirst(Identifier hudId, NtmPlatform.HudElement hudElement) {
        HudElementRegistry.addFirst(hudId, hudElement::render);
    }

    @Environment(EnvType.CLIENT)
    public static void addHudElementLast(Identifier hudId, NtmPlatform.HudElement hudElement) {
        HudElementRegistry.addLast(hudId, hudElement::render);
    }

    @Environment(EnvType.CLIENT)
    public static void addHudElementBefore(Identifier before, Identifier hudId, NtmPlatform.HudElement hudElement) {
        HudElementRegistry.attachElementBefore(before, hudId, hudElement::render);
    }

    @Environment(EnvType.CLIENT)
    public static void addHudElementAfter(Identifier after, Identifier hudId, NtmPlatform.HudElement hudElement) {
        HudElementRegistry.attachElementAfter(after, hudId, hudElement::render);
    }

    @Environment(EnvType.CLIENT)
    public static boolean registerBuiltinResourcePack(Identifier identifier, Component name, NtmPlatform.PackActivationType activationType) {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer(identifier.getPath());

        return container.filter(modContainer -> ResourceLoader.registerBuiltinPack(identifier, modContainer, name, fromNtm(activationType))).isPresent();

    }

    @Environment(EnvType.CLIENT)
    private static PackActivationType fromNtm(NtmPlatform.PackActivationType type) {
        return switch (type) {
            case ALWAYS_ON -> PackActivationType.NORMAL;
            case ON_BY_DEFAULT -> PackActivationType.DEFAULT_ENABLED;
            case OFF_BY_DEFAULT -> PackActivationType.ALWAYS_ENABLED;
        };
    }

    @Environment(EnvType.CLIENT)
    public static void registerItemModelOverride(Function<Identifier, ItemModel.@Nullable Unbaked> override) {
        ModelLoadingPlugin.register(pluginContext ->
          pluginContext.modifyItemModelBeforeBake().register((original, context) -> Objects.requireNonNullElse(override.apply(context.itemId()), original))
        );
    }

    @Environment(EnvType.CLIENT)
    public static void registerBlockModelOverride(Function<BlockState, BlockStateModel.@Nullable UnbakedRoot> override) {
        ModelLoadingPlugin.register(pluginContext ->
          pluginContext.modifyBlockModelBeforeBake().register((original, context) -> Objects.requireNonNullElse(override.apply(context.state()), original))
        );
    }

    @Environment(EnvType.CLIENT)
    public static @Nullable TextureAtlasSprite getFluidSprites(Fluid fluid) {
        return FluidVariantRendering.getSprite(FluidVariant.of(fluid));
    }

    @Environment(EnvType.CLIENT)
    public static List<Component> getFluidTooltip(Fluid fluid) {
        return FluidVariantRendering.getTooltip(FluidVariant.of(fluid));
    }
}
