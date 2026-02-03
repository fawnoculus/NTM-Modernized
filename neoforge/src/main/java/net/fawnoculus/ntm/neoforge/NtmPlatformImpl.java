package net.fawnoculus.ntm.neoforge;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmPlatform;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.client.textures.FluidSpriteCache;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @see NtmPlatform
 */
@SuppressWarnings("unused")
@EventBusSubscriber(modid = Ntm.MOD_ID)
public class NtmPlatformImpl {
    private static final List<ResourcePack> RESOURCE_PACKS = new ArrayList<>();
    private static final Map<ResourceKey<CreativeModeTab>, Consumer<NtmPlatform.TabModifier>> CREATIVE_TAB_MODIFIERS = new HashMap<>();
    private static final List<HudElementInstance> FIST_HUD_ELEMENTS = new ArrayList<>();
    private static final List<HudElementInstance> LAST_HUD_ELEMENTS = new ArrayList<>();
    private static final List<RelativeHudElementInstance> BEFORE_HUD_ELEMENTS = new ArrayList<>();
    private static final List<RelativeHudElementInstance> AFTER_HUD_ELEMENTS = new ArrayList<>();
    private static final List<Function<Identifier, ItemModel.@Nullable Unbaked>> ITEM_MODEL_OVERRIDES = new ArrayList<>();
    private static final List<Function<BlockState, BlockStateModel.@Nullable UnbakedRoot>> BLOCK_STATE_MODEL_OVERRIDES = new ArrayList<>();

    @SafeVarargs
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> makeBlockEntityType(
      Identifier identifier,
      BiFunction<BlockPos, BlockState, T> blockEntityFactory,
      Supplier<Block>... blocks
    ) {
        return () -> {
            Set<Block> blockSet = new HashSet<>(blocks.length);
            for (Supplier<Block> block : blocks) {
                blockSet.add(block.get());
            }
            return new BlockEntityType<>(blockEntityFactory::apply, blockSet, false);
        };
    }

    public static void modifyCreativeTab(ResourceKey<CreativeModeTab> resourceKey, Consumer<NtmPlatform.TabModifier> modifications) {
        Consumer<NtmPlatform.TabModifier> modifier = CREATIVE_TAB_MODIFIERS.get(resourceKey);
        if (modifier != null) {
            modifications = modifications.andThen(modifier);
        }

        CREATIVE_TAB_MODIFIERS.put(resourceKey, modifications);
    }

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        Consumer<NtmPlatform.TabModifier> modifier = CREATIVE_TAB_MODIFIERS.get(event.getTabKey());
        if (modifier != null) {
            modifier.accept(event::accept);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addHudElementFirst(Identifier hudId, NtmPlatform.HudElement hudElement) {
        FIST_HUD_ELEMENTS.add(new HudElementInstance(hudId, hudElement));
    }

    @OnlyIn(Dist.CLIENT)
    public static void addHudElementLast(Identifier hudId, NtmPlatform.HudElement hudElement) {
        LAST_HUD_ELEMENTS.add(new HudElementInstance(hudId, hudElement));
    }

    @OnlyIn(Dist.CLIENT)
    public static void addHudElementBefore(Identifier before, Identifier hudId, NtmPlatform.HudElement hudElement) {
        BEFORE_HUD_ELEMENTS.add(new RelativeHudElementInstance(toNeoforgeHudId(before), hudId, hudElement));
    }

    @OnlyIn(Dist.CLIENT)
    public static void addHudElementAfter(Identifier after, Identifier hudId, NtmPlatform.HudElement hudElement) {
        AFTER_HUD_ELEMENTS.add(new RelativeHudElementInstance(toNeoforgeHudId(after), hudId, hudElement));
    }

    @SubscribeEvent
    public static void addHudElements(RegisterGuiLayersEvent event) {
        for (HudElementInstance instance : FIST_HUD_ELEMENTS) {
            event.registerAboveAll(instance.hudId, instance.hudElement::render);
        }
        for (HudElementInstance instance : LAST_HUD_ELEMENTS) {
            event.registerBelowAll(instance.hudId, instance.hudElement::render);
        }
        for (RelativeHudElementInstance instance : BEFORE_HUD_ELEMENTS) {
            event.registerAbove(instance.relative, instance.hudId, instance.hudElement::render);
        }
        for (RelativeHudElementInstance instance : AFTER_HUD_ELEMENTS) {
            event.registerBelow(instance.relative, instance.hudId, instance.hudElement::render);
        }
    }

    public static Identifier toNeoforgeHudId(Identifier ntmPlatformId) {
        if (NtmPlatform.VanillaHudElements.MISC_OVERLAYS.equals(ntmPlatformId)) {
            return VanillaGuiLayers.CAMERA_OVERLAYS;
        } else if (NtmPlatform.VanillaHudElements.CROSSHAIR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.CROSSHAIR;
        } else if (NtmPlatform.VanillaHudElements.HOTBAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.HOTBAR;
        } else if (NtmPlatform.VanillaHudElements.ARMOR_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.ARMOR_LEVEL;
        } else if (NtmPlatform.VanillaHudElements.HEALTH_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.PLAYER_HEALTH;
        } else if (NtmPlatform.VanillaHudElements.FOOD_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.FOOD_LEVEL;
        } else if (NtmPlatform.VanillaHudElements.AIR_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.AIR_LEVEL;
        } else if (NtmPlatform.VanillaHudElements.MOUNT_HEALTH.equals(ntmPlatformId)) {
            return VanillaGuiLayers.VEHICLE_HEALTH;
        } else if (NtmPlatform.VanillaHudElements.INFO_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.CONTEXTUAL_INFO_BAR;
        } else if (NtmPlatform.VanillaHudElements.EXPERIENCE_LEVEL.equals(ntmPlatformId)) {
            return VanillaGuiLayers.EXPERIENCE_LEVEL;
        } else if (NtmPlatform.VanillaHudElements.HELD_ITEM_TOOLTIP.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SELECTED_ITEM_NAME;
        } else if (NtmPlatform.VanillaHudElements.SPECTATOR_TOOLTIP.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SPECTATOR_TOOLTIP;
        } else if (NtmPlatform.VanillaHudElements.STATUS_EFFECTS.equals(ntmPlatformId)) {
            return VanillaGuiLayers.EFFECTS;
        } else if (NtmPlatform.VanillaHudElements.BOSS_BAR.equals(ntmPlatformId)) {
            return VanillaGuiLayers.BOSS_OVERLAY;
        } else if (NtmPlatform.VanillaHudElements.SLEEP.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SLEEP_OVERLAY;
        } else if (NtmPlatform.VanillaHudElements.DEMO_TIMER.equals(ntmPlatformId)) {
            return VanillaGuiLayers.DEMO_OVERLAY;
        } else if (NtmPlatform.VanillaHudElements.SCOREBOARD.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SCOREBOARD_SIDEBAR;
        } else if (NtmPlatform.VanillaHudElements.OVERLAY_MESSAGE.equals(ntmPlatformId)) {
            return VanillaGuiLayers.OVERLAY_MESSAGE;
        } else if (NtmPlatform.VanillaHudElements.TITLE_AND_SUBTITLE.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SUBTITLE_OVERLAY;
        } else if (NtmPlatform.VanillaHudElements.CHAT.equals(ntmPlatformId)) {
            return VanillaGuiLayers.CHAT;
        } else if (NtmPlatform.VanillaHudElements.PLAYER_LIST.equals(ntmPlatformId)) {
            return VanillaGuiLayers.TAB_LIST;
        } else if (NtmPlatform.VanillaHudElements.SUBTITLES.equals(ntmPlatformId)) {
            return VanillaGuiLayers.SUBTITLE_OVERLAY;
        }
        return ntmPlatformId;
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean registerBuiltinResourcePack(Identifier identifier, Component name, NtmPlatform.PackActivationType activationType) {
        boolean resourcePackValid = ModList.get().getModContainerById(identifier.getPath())
          .map(modContainer -> modContainer
            .getModInfo()
            .getOwningFile()
            .getFile()
            .getContents()
            .containsFile(identifier
              .withPrefix("resourcepacks/")
              .withSuffix("/pack.mcmeta")
              .getPath()
              .replace('/', File.separatorChar)
            )
          ).orElse(false);

        if (!resourcePackValid) {
            return false;
        }

        RESOURCE_PACKS.add(new ResourcePack(identifier, name, activationType));
        return true;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addPackFindersEvent(AddPackFindersEvent addPackFindersEvent) {
        if (addPackFindersEvent.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        for (ResourcePack resourcePack : RESOURCE_PACKS) {
            addPackFindersEvent.addPackFinders(
              resourcePack.identifier,
              PackType.CLIENT_RESOURCES,
              resourcePack.name,
              PackSource.BUILT_IN,
              resourcePack.activationType == NtmPlatform.PackActivationType.ALWAYS_ON,
              Pack.Position.BOTTOM
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerItemModelOverride(Function<Identifier, ItemModel.@Nullable Unbaked> override) {
        ITEM_MODEL_OVERRIDES.add(override);
    }

    @OnlyIn(Dist.CLIENT)
    public static ItemModel.@Nullable Unbaked getItemModelOverride(Identifier itemId) {
        ItemModel.@Nullable Unbaked model = null;
        for (Function<Identifier, ItemModel.@Nullable Unbaked> override : ITEM_MODEL_OVERRIDES) {
            ItemModel.@Nullable Unbaked newModel = override.apply(itemId);
            if (newModel != null) {
                model = newModel;
            }
        }
        return model;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerBlockModelOverride(Function<BlockState, BlockStateModel.@Nullable UnbakedRoot> override) {
        BLOCK_STATE_MODEL_OVERRIDES.add(override);
    }

    @OnlyIn(Dist.CLIENT)
    public static BlockStateModel.@Nullable UnbakedRoot getBlockModelOverride(BlockState blockState) {
        BlockStateModel.@Nullable UnbakedRoot model = null;
        for (Function<BlockState, BlockStateModel.@Nullable UnbakedRoot> override : BLOCK_STATE_MODEL_OVERRIDES) {
            BlockStateModel.@Nullable UnbakedRoot newModel = override.apply(blockState);
            if (newModel != null) {
                model = newModel;
            }
        }
        return model;
    }

    @OnlyIn(Dist.CLIENT)
    public static TextureAtlasSprite getFluidSprites(Fluid fluid) {
        return FluidSpriteCache.getSprite(BuiltInRegistries.FLUID.getKey(fluid));
    }

    @OnlyIn(Dist.CLIENT)
    public static List<Component> getFluidTooltip(Fluid fluid) {
        return List.of(); // TODO: actually get Fluid Tooltips on Neoforge
    }

    @OnlyIn(Dist.CLIENT)
    private record ResourcePack(Identifier identifier, Component name, NtmPlatform.PackActivationType activationType) {
    }

    @OnlyIn(Dist.CLIENT)
    private record HudElementInstance(Identifier hudId, NtmPlatform.HudElement hudElement) {
    }

    @OnlyIn(Dist.CLIENT)
    private record RelativeHudElementInstance(Identifier relative, Identifier hudId,
                                              NtmPlatform.HudElement hudElement) {
    }
}
