package net.fawnoculus.ntm;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NtmPlatform {

    /**
     * Create a Block Entity type (this is Modloader specific for some reason)
     *
     * @param identifier         the Identifier of the Block Entity
     * @param blockEntityFactory A Function to construct the Block Entity from a {@link BlockPos} and {@link BlockState} (usually the Block Entity constructor)
     * @param blocks             The blocks that will use this block entity
     * @param <T>                The block entity for which a type should be created
     * @return The block entity type supplier to be registered
     */
    @SafeVarargs
    @ExpectPlatform
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> makeBlockEntityType(
      Identifier identifier,
      BiFunction<BlockPos, BlockState, T> blockEntityFactory,
      Supplier<Block>... blocks
    ) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.makeBlockEntityType(), Something has gone Terribly Wrong");
    }

    /**
     * Modify the items in an {@link CreativeModeTab}, aka: add items to it,
     * Architectury technically has a way to do this through {@linkplain net.minecraft.world.item.Item.Properties ItemProperties} but I don't like it
     *
     * @param resourceKey   the key of the Creative Tab
     * @param modifications a consumer of {@linkplain TabModifier TabModifier}, though which you can add items to the tab
     */
    @ExpectPlatform
    public static void modifyCreativeTab(ResourceKey<CreativeModeTab> resourceKey, Consumer<TabModifier> modifications) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.modifyCreativeTab(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void addHudElementFirst(Identifier hudId, HudElement hudElement) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBlockModelOverride(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void addHudElementLast(Identifier hudId, HudElement hudElement) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBlockModelOverride(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void addHudElementBefore(Identifier before, Identifier hudId, HudElement hudElement) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBlockModelOverride(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void addHudElementAfter(Identifier after, Identifier hudId, HudElement hudElement) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBlockModelOverride(), Something has gone Terribly Wrong");
    }

    /**
     * Register a built-in resource pack
     *
     * @param identifier     The path represents the mod-id and the path represents the file-path to the resource pack relative to MOD-JAR/resourcepacks
     *                       (or src/main/resources/resourcepacks in a dev environment)
     * @param name           the name of the resource pack (Some mod-loaders may also use the name in the pack.mcmeta, so just use the same one)
     * @param activationType decides if the resource pack is enabled by default and if you can disable it
     * @return if the resourceful was successfully added (it just checks if the path to the resource pack is valid)
     */
    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static boolean registerBuiltinResourcePack(Identifier identifier, Component name, PackActivationType activationType) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBuiltinResourcePack(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void registerItemModelOverride(Function<Identifier, ItemModel.@Nullable Unbaked> override) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerItemModelOverride(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static void registerBlockModelOverride(Function<BlockState, BlockStateModel.@Nullable UnbakedRoot> override) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.registerBlockModelOverride(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static @Nullable TextureAtlasSprite getFluidSprites(Fluid fluid) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.getFluidSprite(), Something has gone Terribly Wrong");
    }

    @ExpectPlatform
    @Environment(EnvType.CLIENT)
    public static List<Component> getFluidTooltip(Fluid fluid) {
        throw new AssertionError("Architectury has not remapped NtmPlatform.getFluidTooltip(), Something has gone Terribly Wrong");
    }

    public enum PackActivationType {
        ALWAYS_ON,
        ON_BY_DEFAULT, // ON_BY_DEFAULT doesn't work neoforge as it has no support for on by default, but still disableable resource packs
        OFF_BY_DEFAULT
    }

    @FunctionalInterface
    public interface TabModifier {
        void accept(ItemStack stack, CreativeModeTab.TabVisibility tabVisibility);

        default void accept(ItemStack stack) {
            this.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        default void accept(RegistrySupplier<? extends ItemLike> item, CreativeModeTab.TabVisibility tabVisibility) {
            this.accept(new ItemStack(item.get()), tabVisibility);
        }

        default void accept(RegistrySupplier<? extends ItemLike> item) {
            this.accept(new ItemStack(item.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        default void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {
            this.accept(new ItemStack(item), tabVisibility);
        }

        default void accept(ItemLike item) {
            this.accept(new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @FunctionalInterface
    @Environment(EnvType.CLIENT)
    public interface HudElement {
        void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);
    }

    /**
     * These Identifiers are equivalent to Fabrics VanillaHudElements.
     * On neoforge the neoforge versions will be used automatically when you use these
     *
     * <p>The identifiers in this interface are the vanilla hud layers in the order they are drawn in.
     * The first element is drawn first, which means it is at the bottom.
     * All vanilla layers except {@link #SLEEP} are in sub drawers and have a render condition attached ({@link net.minecraft.client.Options#hideGui}).
     * Operations relative to any element will generally inherit that element's render condition.
     * There is currently no mechanism to change the render condition of an element.
     */
    public static final class VanillaHudElements {
        /**
         * The identifier for the vanilla miscellaneous overlays (such as vignette, spyglass, and powder snow) element.
         */
        public static final Identifier MISC_OVERLAYS = Identifier.withDefaultNamespace("misc_overlays");
        /**
         * The identifier for the vanilla crosshair element.
         */
        public static final Identifier CROSSHAIR = Identifier.withDefaultNamespace("crosshair");
        /**
         * The identifier for the vanilla hotbar.
         */
        public static final Identifier HOTBAR = Identifier.withDefaultNamespace("hotbar");
        /**
         * The identifier for the player armor level bar.
         */
        public static final Identifier ARMOR_BAR = Identifier.withDefaultNamespace("armor_bar");
        /**
         * The identifier for the player health bar.
         */
        public static final Identifier HEALTH_BAR = Identifier.withDefaultNamespace("health_bar");
        /**
         * The identifier for the player hunger level bar.
         */
        public static final Identifier FOOD_BAR = Identifier.withDefaultNamespace("food_bar");
        /**
         * The identifier for the player air level bar.
         */
        public static final Identifier AIR_BAR = Identifier.withDefaultNamespace("air_bar");
        /**
         * The identifier for the vanilla mount health.
         */
        public static final Identifier MOUNT_HEALTH = Identifier.withDefaultNamespace("mount_health");
        /**
         * The identifier for the info bar, either empty, experience bar, locator, or jump bar.
         */
        public static final Identifier INFO_BAR = Identifier.withDefaultNamespace("info_bar");
        /**
         * The identifier for experience level tooltip.
         */
        public static final Identifier EXPERIENCE_LEVEL = Identifier.withDefaultNamespace("experience_level");
        /**
         * The identifier for held item tooltip.
         */
        public static final Identifier HELD_ITEM_TOOLTIP = Identifier.withDefaultNamespace("held_item_tooltip");
        /**
         * The identifier for the vanilla spectator tooltip.
         */
        public static final Identifier SPECTATOR_TOOLTIP = Identifier.withDefaultNamespace("spectator_tooltip");
        /**
         * The identifier for the vanilla status effects element.
         */
        public static final Identifier STATUS_EFFECTS = Identifier.withDefaultNamespace("status_effects");
        /**
         * The identifier for the vanilla boss bar element.
         */
        public static final Identifier BOSS_BAR = Identifier.withDefaultNamespace("boss_bar");
        /**
         * The identifier for the vanilla sleep overlay element.
         */
        public static final Identifier SLEEP = Identifier.withDefaultNamespace("sleep");
        /**
         * The identifier for the vanilla demo timer element.
         */
        public static final Identifier DEMO_TIMER = Identifier.withDefaultNamespace("demo_timer");
        /**
         * The identifier for the vanilla scoreboard element.
         */
        public static final Identifier SCOREBOARD = Identifier.withDefaultNamespace("scoreboard");
        /**
         * The identifier for the vanilla overlay message element.
         */
        public static final Identifier OVERLAY_MESSAGE = Identifier.withDefaultNamespace("overlay_message");
        /**
         * The identifier for the vanilla title and subtitle element.
         *
         * <p>Note that this is not the sound subtitles.
         */
        public static final Identifier TITLE_AND_SUBTITLE = Identifier.withDefaultNamespace("title_and_subtitle");
        /**
         * The identifier for the vanilla chat element.
         */
        public static final Identifier CHAT = Identifier.withDefaultNamespace("chat");
        /**
         * The identifier for the vanilla player list element.
         */
        public static final Identifier PLAYER_LIST = Identifier.withDefaultNamespace("player_list");
        /**
         * The identifier for the vanilla sound subtitles element.
         */
        public static final Identifier SUBTITLES = Identifier.withDefaultNamespace("subtitles");

        private VanillaHudElements() {
        }
    }
}
