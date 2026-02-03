package net.fawnoculus.ntm.api.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.*;

/**
 * All Abilities can be found at {@link Abilities}
 */
public abstract class ItemAbility {
    public static final HashMap<Identifier, ItemAbility> ID_TO_ABILITY = new HashMap<>();
    public static final List<ItemAbility> TOP_ABILITIES = new ArrayList<>();
    public static final List<ItemAbility> BOTTOM_ABILITIES = new ArrayList<>();
    public static final Identifier NONE_ID = Ntm.id("none");
    public static final ItemAbility NONE = new ItemAbility(NONE_ID, 1, false, false, true) {
    };
    public static final Codec<ItemAbility> CODEC = Identifier.CODEC.flatXmap(
      identifier -> get(identifier).map(DataResult::success).orElse(DataResult.error(() -> "Identifier not valid")),
      ability -> DataResult.success(ability.getId())
    );

    private final Identifier ID;
    private final Identifier ENABLED_TEXTURE;
    private final Identifier DISABLED_TEXTURE;
    private final @Nullable Identifier CROSSHAIR_EXTENSION;
    private final int MAX_LEVEL;
    private final boolean IS_BOTTOM;
    private final boolean DISABLES_OTHER_ROW;

    protected ItemAbility(Identifier id, boolean isBottom) {
        this(id, 1, isBottom, false, false);
    }

    protected ItemAbility(Identifier id, @Range(from = 1, to = 10) int maxLevel, boolean isBottom) {
        this(id, maxLevel, isBottom, false, false);
    }

    protected ItemAbility(Identifier id, @Range(from = 1, to = 10) int maxLevel, boolean isBottom, boolean disablesOtherRow) {
        this(id, maxLevel, isBottom, disablesOtherRow, false);
    }

    private ItemAbility(Identifier id, @Range(from = 1, to = 10) int maxLevel, boolean isBottom, boolean disablesOtherRow, boolean isNone) {
        this.ID = id;
        this.MAX_LEVEL = maxLevel;
        this.IS_BOTTOM = isBottom;
        this.DISABLES_OTHER_ROW = disablesOtherRow;
        this.ENABLED_TEXTURE = Identifier.fromNamespaceAndPath(id.getNamespace(), "textures/gui/ability/" + id.getPath() + "_enabled.png");
        this.DISABLED_TEXTURE = Identifier.fromNamespaceAndPath(id.getNamespace(), "textures/gui/ability/" + id.getPath() + "_disabled.png");

        if (!isBottom && !isNone) {
            this.CROSSHAIR_EXTENSION = Identifier.fromNamespaceAndPath(id.getNamespace(), "textures/gui/ability/" + id.getPath() + "_crosshair.png");
        } else {
            this.CROSSHAIR_EXTENSION = null;
        }

        ID_TO_ABILITY.put(id, this);

        if (!isBottom || isNone) {
            TOP_ABILITIES.add(this);
        }
        if (isBottom || isNone) {
            BOTTOM_ABILITIES.add(this);
        }
    }

    public static Optional<ItemAbility> get(Identifier identifier) {
        return Optional.ofNullable(ID_TO_ABILITY.get(identifier));
    }

    public Identifier getId() {
        return this.ID;
    }

    public Identifier getEnabledTexture() {
        return this.ENABLED_TEXTURE;
    }

    public Identifier getDisabledTexture() {
        return this.DISABLED_TEXTURE;
    }

    public @Nullable Identifier getCrosshairExtension() {
        return this.CROSSHAIR_EXTENSION;
    }

    public @Range(from = 1, to = 10) int maxLevel() {
        return this.MAX_LEVEL;
    }

    // UWU
    public boolean isBottom() {
        return this.IS_BOTTOM;
    }

    public boolean isTop() {
        return !this.IS_BOTTOM;
    }

    public boolean disablesOtherRow() {
        return this.DISABLES_OTHER_ROW;
    }

    public boolean isNone() {
        return Objects.equals(this.ID, NONE_ID);
    }

    public boolean isNotNone() {
        return !Objects.equals(this.ID, NONE_ID);
    }

    public MutableComponent getFullName(@Range(from = 0, to = 10) int level) {
        if (this.MAX_LEVEL <= 1) {
            return this.getName();
        }
        return this.getName().append(" ").append(getLevelText(level));
    }

    public MutableComponent getName() {
        return Component.translatable("tooltip." + this.ID.getNamespace() + ".ability." + this.ID.getPath());
    }

    public MutableComponent getLevelText(@Range(from = 0, to = 10) int level) {
        return Component.literal("(" + level + ")");
    }

    /**
     * Called for every block broken by this tool, including extra blocks from other abilities
     *
     * @return whether to do regular block drops
     */
    public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
        return !miner.preventsBlockDrops();
    }


    /**
     * Add Extra Blocks to be broken
     */
    public void addExtraBlocks(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level, ArrayList<BlockPos> extraBlocks) {
    }

    /**
     * Called before braking any blocks
     */
    public void preMine(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
    }

    @Override
    public String toString() {
        return "ItemAbility{" + ID + '}';
    }
}
