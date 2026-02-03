package net.fawnoculus.ntm.api.tool;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.util.EnchantmentUtil;
import net.fawnoculus.ntm.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Abilities {
    /**
     * VeinMiner causes all blocks of the same type as the one broken to be mined
     * <p>
     * MaxBlocks is the max Distance that a block can have from the originally broken block before it will no longer be harvested by VeinMiner
     */
    public static final ItemAbility VEIN_MINER = new ItemAbility(Ntm.id("vein_miner"), 10, false) {
        @Override
        public MutableComponent getLevelText(@Range(from = 0, to = 10) int level) {
            return Component.literal("(" + (level + 2) + ")");
        }

        @Override
        public void addExtraBlocks(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level, ArrayList<BlockPos> extraBlocks) {
            if (!isCorrectForDrops(stack, state)) {
                return;
            }
            Block compareBlock = state.getBlock();

            if (NtmConfig.VEIN_MINER_ABILITY_EXCLUDE.getValue().contains(compareBlock)) {
                return;
            }

            extraBlocks.add(pos);
            try {
                scanNeighbours(world, compareBlock, pos, pos, extraBlocks, level);
            } catch (StackOverflowError ignored) {
            }
        }

        // These updates the List scannedBlocks
        private void scanNeighbours(Level world, Block compareBlock, BlockPos originPos, BlockPos scanningPos, List<BlockPos> scannedBlocks, @Range(from = 0, to = 10) int level) {
            scanBlock(world, compareBlock, originPos, scanningPos.above(), scannedBlocks, level);
            scanBlock(world, compareBlock, originPos, scanningPos.below(), scannedBlocks, level);
            scanBlock(world, compareBlock, originPos, scanningPos.north(), scannedBlocks, level);
            scanBlock(world, compareBlock, originPos, scanningPos.east(), scannedBlocks, level);
            scanBlock(world, compareBlock, originPos, scanningPos.south(), scannedBlocks, level);
            scanBlock(world, compareBlock, originPos, scanningPos.west(), scannedBlocks, level);
        }

        private void scanBlock(Level world, Block compareBlock, BlockPos originPos, BlockPos scanningPos, List<BlockPos> scannedBlocks, @Range(from = 0, to = 10) int level) {
            if (scannedBlocks.contains(scanningPos)) return;
            if (world.getBlockState(scanningPos).getBlock() != compareBlock) return;
            if (!originPos.closerThan(scanningPos, level + 2)) return;
            scannedBlocks.add(scanningPos);
            scanNeighbours(world, compareBlock, originPos, scanningPos, scannedBlocks, level);
        }
    };

    /**
     * AoE breaks a cube around the block broken by the player
     * <p>
     * blockAmount decides how big the Cube is (Value of 1 would cause a 3x3 Cube, Value of 2 a 5x5 Cube and so on)
     */
    public static final ItemAbility AOE = new ItemAbility(Ntm.id("aoe"), 10, false) {
        @Override
        public void addExtraBlocks(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level, ArrayList<BlockPos> extraBlocks) {
            if (!isCorrectForDrops(stack, state) || NtmConfig.AOE_ABILITY_EXCLUDE.getValue().contains(state.getBlock())) {
                return;
            }

            for (int x = pos.getX() - level; x <= pos.getX() + level; x++) {
                for (int y = pos.getY() - level; y <= pos.getY() + level; y++) {
                    for (int z = pos.getZ() - level; z <= pos.getZ() + level; z++) {
                        BlockState checkBlock = world.getBlockState(new BlockPos(x, y, z));

                        if (isCorrectForDrops(stack, checkBlock) && !NtmConfig.AOE_ABILITY_EXCLUDE.getValue().contains(checkBlock.getBlock())) {
                            extraBlocks.add(new BlockPos(x, y, z));
                        }
                    }
                }
            }
        }
    };

    /**
     * AoE breaks a cube around the block broken by the player
     * <p>
     * blockAmount decides how big the Cube is (Value of 1 would cause a 3x3 Cube, Value of 2 a 5x5 Cube and so on)
     */
    public static final ItemAbility FLAT_AOE = new ItemAbility(Ntm.id("flat_aoe"), 10, false) {
        @Override
        public void addExtraBlocks(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level, ArrayList<BlockPos> extraBlocks) {
            if (!isCorrectForDrops(stack, state) || NtmConfig.AOE_ABILITY_EXCLUDE.getValue().contains(state.getBlock())) {
                return;
            }

            if (!(miner.pick(miner.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE), 1, false) instanceof BlockHitResult hitResult))
                return;

            int xRange = level;
            int yRange = level;
            int zRange = 0;

            switch (hitResult.getDirection()) {
                case WEST, EAST -> {
                    xRange = 0;
                    zRange = level;
                }
                case UP, DOWN -> {
                    yRange = 0;
                    zRange = level;
                }
            }


            for (int x = pos.getX() - xRange; x <= pos.getX() + xRange; x++) {
                for (int y = pos.getY() - yRange; y <= pos.getY() + yRange; y++) {
                    for (int z = pos.getZ() - zRange; z <= pos.getZ() + zRange; z++) {
                        BlockState checkBlock = world.getBlockState(new BlockPos(x, y, z));

                        if (isCorrectForDrops(stack, checkBlock) && !NtmConfig.AOE_ABILITY_EXCLUDE.getValue().contains(checkBlock.getBlock())) {
                            extraBlocks.add(new BlockPos(x, y, z));
                        }
                    }
                }
            }
        }
    };

    /**
     * Creates an explosion when a block that the tool can be used for is broken
     * <p>
     * The resulting EXPLOSION has the Strength specified by "explosionStrength"
     */
    public static final ItemAbility EXPLOSION = new ItemAbility(Ntm.id("explosion"), 10, false, true) {
        @Override
        public MutableComponent getLevelText(@Range(from = 0, to = 10) int level) {
            if (level < 2) {
                return Component.literal("(2.5)");
            }

            return Component.literal("(" + 5 * (level - 1) + ".0)");
        }

        private float fromLevel(int level) {
            if (level < 2) {
                return 2.5f;
            }

            return 5f * (level - 1);
        }

        @Override
        public void preMine(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            if (!isCorrectForDrops(stack, state)) return;
            ExplosionDamageCalculator explosionBehavior = new SimpleExplosionDamageCalculator(true, false, Optional.empty(), Optional.empty());
            world.explode(null, null, explosionBehavior, Vec3.atLowerCornerOf(pos), this.fromLevel(level), false, Level.ExplosionInteraction.TNT);
        }
    };

    /**
     * It's the same thing as if you had the enchantment
     */
    public static final ItemAbility SILK_TOUCH = new ItemAbility(Ntm.id("silk_touch"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            EnchantmentUtil.addEnchantment(world, Enchantments.SILK_TOUCH, 1, stack);
            WorldUtil.dropItemsFromBlock(world, pos, miner, stack);
            EnchantmentUtil.removeEnchantment(Enchantments.SILK_TOUCH, stack);
            return false;
        }
    };

    /**
     * It's the same thing as if you had the enchantment
     * <p>
     * The Level represents the Enchantment Level of FORTUNE that will be applied
     */
    public static final ItemAbility FORTUNE = new ItemAbility(Ntm.id("fortune"), 10, true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            EnchantmentUtil.addEnchantment(world, Enchantments.FORTUNE, level, stack);
            WorldUtil.dropItemsFromBlock(world, pos, miner, stack);
            EnchantmentUtil.removeEnchantment(Enchantments.FORTUNE, stack);
            return false;
        }
    };

    /**
     * Makes blocks drop what the thing they dropped would have produced when smelted
     * If the drop doesn't have a smelting recipe it will the regular drop will be used instead
     */
    public static final ItemAbility AUTO_SMELT = new ItemAbility(Ntm.id("auto_smelt"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state) || !(world instanceof ServerLevel serverWorld)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            LootParams.Builder builder = new LootParams.Builder(serverWorld)
              .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
              .withParameter(LootContextParams.BLOCK_STATE, state)
              .withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(pos))
              .withOptionalParameter(LootContextParams.THIS_ENTITY, miner)
              .withParameter(LootContextParams.TOOL, stack);

            List<ItemStack> list = state.getDrops(builder);
            for (ItemStack checkedStack : list) {
                SingleRecipeInput recipeInput = new SingleRecipeInput(checkedStack);

                Optional<RecipeHolder<SmeltingRecipe>> optional = RecipeManager.createCheck(RecipeType.SMELTING).getRecipeFor(recipeInput, serverWorld);
                if (optional.isEmpty()) {
                    continue;
                }

                RecipeHolder<SmeltingRecipe> recipeEntry = optional.get();

                ItemStack output = recipeEntry.value().assemble(recipeInput, serverWorld.registryAccess());
                output.setCount(checkedStack.getCount());

                Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), output);
            }

            return false;
        }
    };

    /**
     * Makes blocks drop what the thing they dropped would have produced when shredded
     * If the drop doesn't have a shredding recipe it will the regular drop will be used instead
     */
    public static final ItemAbility AUTO_SHREADER = new ItemAbility(Ntm.id("auto_shredder"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state) || !(world instanceof ServerLevel serverWorld)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            //TODO: Do this once you have Shreader Recipes
            return super.onBreakBlock(stack, world, pos, miner, level);
        }
    };

    /**
     * Makes blocks drop what the thing they dropped would have produced when centrifuged
     * If the drop doesn't have a centrifuging recipe it will the regular drop will be used instead
     */
    public static final ItemAbility AUTO_CENTRIFUGE = new ItemAbility(Ntm.id("auto_centrifuge"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state) || !(world instanceof ServerLevel serverWorld)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            //TODO: Do this once you have Centrifuge Recipes
            return super.onBreakBlock(stack, world, pos, miner, level);
        }
    };

    /**
     * Makes blocks drop what the thing they dropped would have produced when Crystallized (aka: put in an Ore Acidizer)
     * If the drop doesn't have a Crystallizing recipe it will the regular drop will be used instead
     */
    public static final ItemAbility AUTO_CRYSTALLIZER = new ItemAbility(Ntm.id("auto_crystallizer"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            if (!isCorrectForDrops(stack, state) || !(world instanceof ServerLevel serverWorld)) {
                return true;
            }

            if (miner.preventsBlockDrops()) {
                return false;
            }

            //TODO: Do this once you have Crystallizer Recipes
            return super.onBreakBlock(stack, world, pos, miner, level);
        }
    };

    /**
     * Makes redstone blocks &AMP; ores drop a random amount of mercury drops
     */
    public static final ItemAbility MERCURY_TOUCH = new ItemAbility(Ntm.id("mercury_touch"), true) {
        @Override
        public boolean onBreakBlock(ItemStack stack, Level world, BlockPos pos, Player miner, @Range(from = 0, to = 10) int level) {
            BlockState state = world.getBlockState(pos);

            int mercury = 0;
            if (state.getBlock() == Blocks.REDSTONE_ORE || state.getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE)
                mercury = miner.getRandom().nextInt(5) + 4;
            if (state.getBlock() == Blocks.REDSTONE_BLOCK)
                mercury = miner.getRandom().nextInt(7) + 8;

            if (mercury > 0) {
                Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(NtmItems.NULL, mercury)); //TODO: replace this with Mercury Drops once they exist
                stack.hurtWithoutBreaking(1, miner);

                return false;
            }

            return true;
        }
    };

    private static boolean isCorrectForDrops(ItemStack stack, BlockState state) {
        Tool toolComponent = stack.get(DataComponents.TOOL);
        return toolComponent != null && toolComponent.isCorrectForDrops(state);
    }
}
