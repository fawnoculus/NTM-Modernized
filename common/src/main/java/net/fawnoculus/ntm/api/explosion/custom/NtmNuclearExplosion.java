package net.fawnoculus.ntm.api.explosion.custom;

import net.fawnoculus.ntm.api.explosion.NtmExplosionData;
import net.fawnoculus.ntm.api.explosion.NtmExplosionSystem;
import net.fawnoculus.ntm.api.explosion.NtmExplosionType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Range;
import org.joml.Vector3f;

import java.util.*;

public class NtmNuclearExplosion implements NtmExplosionType<NtmNuclearExplosion.ExtraData, NtmNuclearExplosion.ExplosionState> {
    private static final NtmNuclearExplosion INSTANCE = new NtmNuclearExplosion();

    protected NtmNuclearExplosion() {
    }

    public static void addExplosion(ServerLevel level, BlockPos pos, int radius) {
        if (radius < 1) {
            radius = 25;
        }

        int strength = radius * 2;
        int speed = (int) Math.ceil(100000.0 / strength);
        int length = radius;

        addExplosion(new NtmExplosionData(level.getServer(), level, pos), new ExtraData(strength, speed, length));
    }

    public static void addExplosion(NtmExplosionData explosionData, NtmNuclearExplosion.ExtraData extraData) {
        NtmExplosionSystem.addExplosion(INSTANCE, explosionData, extraData);
    }

    @Override
    public NtmNuclearExplosion.ExplosionState createExplosion(NtmExplosionData explosionData, ExtraData extraData) {
        return new NtmNuclearExplosion.ExplosionState();
    }

    @Override
    public void processExplosion(@Range(from = 0, to = Long.MAX_VALUE) long maxNanos, NtmExplosionData explosionData, ExtraData extraData, ExplosionState explosionState) {
        long endTime = System.nanoTime() + maxNanos;

        while (System.nanoTime() < endTime) {
            if (explosionState.isExplosionDone) {
                return;
            }

            if (explosionState.isAnalysisDone) {
                doDestruction(explosionData, extraData, explosionState);
                continue;
            }

            doAnalysis(explosionData, extraData, explosionState);
        }
    }

    private void doAnalysis(NtmExplosionData explosionData, ExtraData extraData, ExplosionState explosionState) {
        int count = extraData.speed * 10;
        int amountProcessed = 0;

        int posX = explosionData.pos().getX();
        int posY = explosionData.pos().getY();
        int posZ = explosionData.pos().getZ();

        ServerLevel level = explosionData.level();

        while (extraData.gspNumMax >= explosionState.gspNum) {
            // Get Cartesian coordinates for spherical coordinates
            Vec3 vec = explosionState.getSpherical2cartesian();

            int length = extraData.strength;
            float res = extraData.strength;

            Vector3f lastPos = null;
            HashSet<ChunkPos> chunkPoses = new HashSet<>();

            for (int i = 0; i < length; i++) {

                if (i > extraData.length)
                    break;

                float x0 = (float) (posX + (vec.x() * i));
                float y0 = (float) (posY + (vec.y() * i));
                float z0 = (float) (posZ + (vec.z() * i));

                BlockPos blockPos = new BlockPos((int) Math.floor(x0), (int) Math.floor(y0), (int) Math.floor(z0));

                double fac = 100 - ((double) i) / ((double) length) * 100;
                fac *= 0.07D;

                BlockState blockState = level.getBlockState(blockPos);

                blockState.getBlock().getExplosionResistance();

                res -= (float) Math.pow(getExplosionResistance(blockState), 7.5D - fac);

                if (res > 0 && !blockState.isAir()) {
                    lastPos = new Vector3f(x0, y0, z0);
                    //all-air chunks don't need to be buffered at all
                    ChunkPos chunkPos = new ChunkPos(blockPos);
                    chunkPoses.add(chunkPos);
                }

                if (res <= 0 || i + 1 >= length || i == length - 1) {
                    break;
                }
            }

            for (ChunkPos pos : chunkPoses) {
                List<Vector3f> triplets = explosionState.perChunk.computeIfAbsent(pos, ignored -> new ArrayList<>());
                triplets.add(lastPos);
            }

            // Raise one generalized spiral points
            explosionState.generateGspUp(extraData);

            amountProcessed++;
            if (amountProcessed >= count) {
                return;
            }
        }

        List<ChunkPos> posList = new ArrayList<>(explosionState.perChunk.keySet());
        ChunkPos originChunk = new ChunkPos(explosionData.pos());
        posList.sort((chunkPos1, chunkPos2) -> {
            int diff1 = Math.abs((originChunk.x - chunkPos1.x)) + Math.abs((originChunk.z - chunkPos1.z));
            int diff2 = Math.abs((originChunk.x - chunkPos2.x)) + Math.abs((originChunk.z - chunkPos2.z));
            return diff1 - diff2;
        });

        explosionState.orderedChunks.addAll(posList);
        explosionState.isAnalysisDone = true;
    }

    private float getExplosionResistance(BlockState state) {
        if (state.getBlock() == Blocks.SANDSTONE || state.getBlock() == Blocks.OBSIDIAN) {
            return Blocks.STONE.getExplosionResistance();
        }
        return state.getBlock().getExplosionResistance();
    }

    private void doDestruction(NtmExplosionData explosionData, ExtraData extraData, ExplosionState explosionState) {
        ChunkPos chunkPos = explosionState.orderedChunks.peek();
        if (chunkPos == null) {
            explosionState.isExplosionDone = true;
            return;
        }

        List<Vector3f> list = explosionState.perChunk.get(chunkPos);
        if (list == null) {
            explosionState.isExplosionDone = true;
            return;
        }

        ServerLevel level = explosionData.level();
        HashSet<BlockPos> toRem = new HashSet<>();
        HashSet<BlockPos> toRemTips = new HashSet<>();
        BlockPos origin = explosionData.pos();

        int enter = Math.max(
          Math.min(
            Math.abs(origin.getX() - (chunkPos.x << 4)),
            Math.abs(origin.getZ() - (chunkPos.z << 4))
          ) - 16,
          0
        );

        for (Vector3f triplet : list) {
            float x = triplet.x();
            float y = triplet.y();
            float z = triplet.z();

            Vec3 vec = new Vec3(x - origin.getX(), y - origin.getY(), z - origin.getZ());
            double vecLength = vec.length();

            double pX = vec.x() / vecLength;
            double pY = vec.y() / vecLength;
            double pZ = vec.z() / vecLength;

            int tipX = (int) Math.floor(x);
            int tipY = (int) Math.floor(y);
            int tipZ = (int) Math.floor(z);

            boolean inChunk = false;
            for (int i = enter; i < vecLength; i++) {
                int x0 = (int) Math.floor(origin.getX() + pX * i);
                int y0 = (int) Math.floor(origin.getY() + pY * i);
                int z0 = (int) Math.floor(origin.getZ() + pZ * i);

                if (x0 >> 4 != chunkPos.x || z0 >> 4 != chunkPos.z) {
                    if (inChunk) {
                        break;
                    } else {
                        continue;
                    }
                }

                inChunk = true;

                BlockPos pos = new BlockPos(x0, y0, z0);
                if (!level.getBlockState(pos).isAir()) {
                    if (x0 == tipX && y0 == tipY && z0 == tipZ) {
                        toRemTips.add(pos);
                    }
                    toRem.add(pos);
                }
            }
        }

        for (BlockPos pos : toRem) {
            if (toRemTips.contains(pos)) {
                handleTip(level, pos);
            } else {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
            }
        }

        explosionState.orderedChunks.poll();
        explosionState.perChunk.remove(chunkPos);
    }

    protected void handleTip(ServerLevel level, BlockPos pos) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
    }

    @Override
    public boolean isDone(NtmExplosionData explosionData, ExtraData extraData, ExplosionState explosionState) {
        return explosionState.isExplosionDone;
    }

    public record ExtraData(int strength, int length, int speed, int gspNumMax) {
        public ExtraData(int strength, int speed, int length) {
            this(strength, speed, length, (int) (2.5 * Math.PI * Math.pow(strength, 2)));
        }
    }

    public static final class ExplosionState {
        public HashMap<ChunkPos, List<Vector3f>> perChunk = new HashMap<>();
        public Queue<ChunkPos> orderedChunks = new ArrayDeque<>();
        public boolean isExplosionDone = false;
        public boolean isAnalysisDone = false;
        public int gspNum = 1;
        public double gspX = Math.PI;
        public double gspY = 0.0;

        private Vec3 getSpherical2cartesian() {
            double dx = Math.sin(this.gspX) * Math.cos(this.gspY);
            double dz = Math.sin(this.gspX) * Math.sin(this.gspY);
            double dy = Math.cos(this.gspX);
            return new Vec3(dx, dy, dz);
        }

        public void generateGspUp(ExtraData extraData) {
            if (this.gspNum < extraData.gspNumMax) {
                int k = this.gspNum + 1;
                double hk = -1.0 + 2.0 * (k - 1.0) / (extraData.gspNumMax - 1.0);
                this.gspX = Math.acos(hk);

                double prev_lon = this.gspY;
                double lon = prev_lon + 3.6 / Math.sqrt(extraData.gspNumMax) / Math.sqrt(1.0 - hk * hk);
                this.gspY = lon % (Math.PI * 2);
            } else {
                this.gspX = 0.0;
                this.gspY = 0.0;
            }
            this.gspNum++;
        }
    }
}
