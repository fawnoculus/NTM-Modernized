package net.fawnoculus.ntm.api.explosion.custom;

import net.fawnoculus.ntm.api.explosion.NtmExplosionData;
import net.fawnoculus.ntm.api.explosion.NtmExplosionSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NtmBalefireExplosion extends NtmNuclearExplosion {
    private static final NtmBalefireExplosion INSTANCE = new NtmBalefireExplosion();

    protected NtmBalefireExplosion() {
    }

    public static void addExplosion(ServerLevel level, BlockPos pos, int radius) {
        if (radius < 1) {
            radius = 25;
        }

        int strength = radius * 2;
        int speed = (int) Math.ceil(100000.0 / strength);
        int length = radius;

        addExplosion(new NtmExplosionData(level.getServer(), level, pos), new NtmNuclearExplosion.ExtraData(strength, speed, length));
    }

    public static void addExplosion(NtmExplosionData explosionData, NtmNuclearExplosion.ExtraData extraData) {
        NtmExplosionSystem.addExplosion(INSTANCE, explosionData, extraData);
    }

    @Override
    protected void handleTip(ServerLevel level, BlockPos pos) {
        if (Block.isFaceFull(level.getBlockState(pos.below()).getShape(level, pos.below()), Direction.UP) && level.getRandom().nextInt(5) == 0) {
            level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL); // TODO: replace this with NtmBlocks.BALEFIRE.get() once we have balefire
            return;
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
    }
}
