package net.fawnoculus.ntm.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class WorldUtil {
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ChunkPos getChunkPos(@NotNull Vec3 pos) {
        return new ChunkPos((int) (pos.x / 16), (int) (pos.z / 16));
    }

    @Contract("_ -> new")
    public static @NotNull ChunkPos getChunkPos(@NotNull Vec3i pos) {
        return new ChunkPos(pos.getX() / 16, pos.getZ() / 16);
    }

    @Contract("_ -> new")
    public static @NotNull Vec3 getVec3d(@NotNull Vec3i pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }

    public static void removeBlock(Level world, BlockPos pos, Player player, boolean doBlockDrops) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        BlockState originalState = world.getBlockState(pos);
        Block block = originalState.getBlock();
        BlockState newState = block.playerWillDestroy(world, pos, originalState, player);
        boolean bl = world.removeBlock(pos, false);
        if (bl) {
            block.destroy(world, pos, newState);
        }

        if (doBlockDrops) {
            ItemStack itemStack = player.getMainHandItem();
            ItemStack itemStack2 = itemStack.copy();
            boolean bl2 = player.hasCorrectToolForDrops(newState);
            itemStack.mineBlock(world, newState, pos, player);
            if (bl && bl2) {
                block.playerDestroy(world, player, pos, newState, blockEntity, itemStack2);
            }
        }
    }

    public static void dropItemsFromBlock(Level world, BlockPos pos, Player miner, ItemStack tool) {
        BlockState state = world.getBlockState(pos);
        Block.dropResources(state, world, pos, world.getBlockEntity(pos), miner, tool);
    }
}
