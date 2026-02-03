package net.fawnoculus.ntm.misc;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;
import java.util.function.Supplier;

public class NtmParticles {
    public static final RegistrySupplier<SimpleParticleType> TEST = register("test");

    private static RegistrySupplier<SimpleParticleType> register(String name) {
        return register(name, simple());
    }

    private static <T extends ParticleType<? extends ParticleOptions>> RegistrySupplier<T> register(String name, Supplier<T> particleType) {
        return NtmDeferredRegistries.PARTICLES.register(Ntm.id(name), particleType);
    }


    public static Supplier<SimpleParticleType> simple() {
        return simple(false);
    }

    public static Supplier<SimpleParticleType> simple(boolean alwaysSpawn) {
        return () -> new SimpleParticleType(alwaysSpawn) {
        };
    }

    public static <T extends ParticleOptions> Supplier<ParticleType<T>> complex(
      MapCodec<T> codec,
      StreamCodec<? super RegistryFriendlyByteBuf, T> packetCodec
    ) {
        return complex(false, codec, packetCodec);
    }

    public static <T extends ParticleOptions> Supplier<ParticleType<T>> complex(
      boolean alwaysSpawn,
      final MapCodec<T> codec,
      final StreamCodec<? super RegistryFriendlyByteBuf, T> packetCodec
    ) {
        return () -> new ParticleType<>(alwaysSpawn) {
            public @NonNull MapCodec<T> codec() {
                return codec;
            }

            public @NonNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return packetCodec;
            }
        };
    }

    public static <T extends ParticleOptions> Supplier<ParticleType<T>> complex(
      Function<ParticleType<T>, MapCodec<T>> codecGetter,
      Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> packetCodecGetter
    ) {
        return complex(false, codecGetter, packetCodecGetter);
    }

    public static <T extends ParticleOptions> Supplier<ParticleType<T>> complex(
      boolean alwaysSpawn,
      final Function<ParticleType<T>, MapCodec<T>> codecGetter,
      final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> packetCodecGetter
    ) {
        return () -> new ParticleType<>(alwaysSpawn) {
            public @NonNull MapCodec<T> codec() {
                return codecGetter.apply(this);
            }

            public @NonNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return packetCodecGetter.apply(this);
            }
        };
    }

    public static void init() {
    }
}
