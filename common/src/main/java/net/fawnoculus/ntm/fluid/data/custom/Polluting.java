package net.fawnoculus.ntm.fluid.data.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Polluting(@NotNull Boolean isPolluting, @NotNull List<PollutionData> whenSpilled,
                        @NotNull List<PollutionData> whenBurned) {
    public static final Polluting DEFAULT = new Polluting(false, List.of(), List.of());
    public static final Codec<Polluting> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(
        Codec.BOOL.fieldOf("is_polluting").forGetter(Polluting::isPolluting),
        Codec.list(PollutionData.CODEC).fieldOf("when_spilled").forGetter(Polluting::whenSpilled),
        Codec.list(PollutionData.CODEC).fieldOf("when_burned").forGetter(Polluting::whenBurned)
      ).apply(instance, Polluting::new)
    );
}
