package net.fawnoculus.ntm.api.radiation.processor;

import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.fawnoculus.ntm.api.radiation.RadiationRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**
 * Simple Chunk Based Radiation Processor
 */
public class SimpleRadiationProcessor implements RadiationProcessor {
    private final ServerLevel WORLD;
    private final ChunkPos POS;
    private double passiveRadiation;
    private double activeRadiation;

    public SimpleRadiationProcessor(ServerLevel world, ChunkPos pos) {
        this.WORLD = world;
        this.POS = pos;
        this.passiveRadiation = RadiationRegistry.getRadioactivity(WORLD);
        this.activeRadiation = 0;
    }

    @Override
    public void tick() {
        double toBeSpreadRadiation = (this.activeRadiation + this.passiveRadiation) / 5; // one fifth of total radiation is to be spread

        this.activeRadiation = (this.activeRadiation / 5) * 4; // remove one third of active radiation
        if (this.activeRadiation < 1) {
            this.activeRadiation = 0; // remove all active radiation of there is less than 1 milliRAD/s
        }

        double spreadRadiationPerChunk = toBeSpreadRadiation / 4; // one fifth of the to be spread radiation is lost

        if (RadiationManager.getRadiationProcessor(this.WORLD, new ChunkPos(this.POS.x - 1, this.POS.z)) instanceof SimpleRadiationProcessor processor) {
            processor.activeRadiation += spreadRadiationPerChunk;
        }
        if (RadiationManager.getRadiationProcessor(this.WORLD, new ChunkPos(this.POS.x + 1, this.POS.z)) instanceof SimpleRadiationProcessor processor) {
            processor.activeRadiation += spreadRadiationPerChunk;
        }
        if (RadiationManager.getRadiationProcessor(this.WORLD, new ChunkPos(this.POS.x, this.POS.z - 1)) instanceof SimpleRadiationProcessor processor) {
            processor.activeRadiation += spreadRadiationPerChunk;
        }
        if (RadiationManager.getRadiationProcessor(this.WORLD, new ChunkPos(this.POS.x, this.POS.z + 1)) instanceof SimpleRadiationProcessor processor) {
            processor.activeRadiation += spreadRadiationPerChunk;
        }
        // if a chunk is not loaded the radiation going into it is also lost
    }

    @Override
    public double getPassiveRadiation(Vec3 pos) {
        return this.passiveRadiation;
    }

    @Override
    public double getActiveRadiation(Vec3 pos) {
        return this.activeRadiation;
    }

    @Override
    public void onChangeBlock(BlockState newState, BlockState previousState, BlockPos pos) {
        this.passiveRadiation = Math.clamp(
          this.passiveRadiation - RadiationRegistry.getRadioactivity(previousState) + RadiationRegistry.getRadioactivity(newState),
          0, Double.MAX_VALUE
        );
    }

    @Override
    public void writeData(CompoundTag data) {
        data.putString("radiation_processor_type", "simple");
        data.putDouble("active_radiation", this.activeRadiation);
        data.putDouble("passive_radiation", this.passiveRadiation);
    }

    @Override
    public void readData(CompoundTag data) {
        String type = data.getStringOr("radiation_processor_type", "no_processor");
        if (type.equals("simple")) {
            this.activeRadiation = data.getDoubleOr("active_radiation", 0.0);
            this.passiveRadiation = data.getDoubleOr("passive_radiation", 0.0);
        }
    }
}
