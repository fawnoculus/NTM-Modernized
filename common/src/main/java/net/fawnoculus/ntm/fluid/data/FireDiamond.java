package net.fawnoculus.ntm.fluid.data;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.util.CodecUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

// Fire Diamond, aka: The Symbol on FluidNetworkType Containers once you set their Fluids
public class FireDiamond {
    public static final FluidDataType<@Range(from = 0, to = 4) Integer> RED = register("flammability", Codec.INT, 0);     // FLAMMABILITY
    public static final FluidDataType<@Range(from = 0, to = 4) Integer> BLUE = register("health", Codec.INT, 0);          // HEALTH
    public static final FluidDataType<@Range(from = 0, to = 4) Integer> YELLOW = register("instability", Codec.INT, 0);   // INSTABILITY
    public static final FluidDataType<SpecialNotice> WHITE = register("special_notice", SpecialNotice.CODEC, SpecialNotice.NONE);        // SPECIAL_NOTICE

    private static <T> @NotNull FluidDataType<T> register(String name, Codec<T> codec, @Nullable T defaultValue) {
        return new FluidDataType<>(Ntm.id(name), codec, defaultValue, null, false).register();
    }

    public enum SpecialNotice {
        NONE,
        WATER_REACTIVE,           // W
        OXIDIZER,                 // OX
        WATER_REACTIVE_OXIDIZER,  // W+OX or W OX or WOX
        ACID,                     // ACID
        BIOHAZARDOUS,             // BIO
        CORROSIVE,                // COR
        CRYOGENIC,                // CRYO
        POISONOUS,                // POI
        RADIOACTIVE,              // RE
        ANTIMATTER;               // ANTI

        public static final Codec<SpecialNotice> CODEC = CodecUtil.getEnumCodec(SpecialNotice.class);
    }
}
