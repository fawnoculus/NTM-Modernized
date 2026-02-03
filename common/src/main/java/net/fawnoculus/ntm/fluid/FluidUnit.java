package net.fawnoculus.ntm.fluid;

import net.fawnoculus.ntm.NtmConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum FluidUnit {
    BUCKET(81000),
    BLOCK(81000),
    BOTTLE(27000),
    BOWL(27000),
    INGOT(9000),
    NUGGET(1000),
    MILLI_BUCKET(81),
    DROPLET(1);

    public final long DROPLETS;

    FluidUnit(long droplets) {
        this.DROPLETS = droplets;
    }

    public static long convert(long amount, FluidUnit from, FluidUnit to) {
        // Will inevitably lead to los of flood when converting, so this should not be used when accuracy is needed
        return Math.floorDiv(amount * from.DROPLETS, to.DROPLETS);
    }

    public static long dropletsToMB(long droplets) {
        return Math.floorDiv(droplets, MILLI_BUCKET.DROPLETS);
    }

    public static double dropletsToMB(double droplets) {
        return droplets / MILLI_BUCKET.DROPLETS;
    }

    public static long mbToDroplets(long droplets) {
        return droplets * MILLI_BUCKET.DROPLETS;
    }

    public static double mbToDroplets(double droplets) {
        return droplets * MILLI_BUCKET.DROPLETS;
    }

    public static MutableComponent text(long amount, FluidUnit unit) {
        return text(amount * unit.DROPLETS);
    }

    public static MutableComponent text(long droplets) {
        if (NtmConfig.FLUID_UNIT.getValue() == NtmConfig.FluidUnit.Droplets) {
            return textDroplets(droplets);
        }
        return textMB(droplets);
    }

    public static MutableComponent textMB(long droplets) {
        return Component.translatable("generic.ntm.fluid.mb", String.format("%,d", droplets));
    }

    public static MutableComponent textDroplets(long droplets) {
        return Component.translatable("generic.ntm.fluid.droplets", String.format("%,d", droplets));
    }
}
