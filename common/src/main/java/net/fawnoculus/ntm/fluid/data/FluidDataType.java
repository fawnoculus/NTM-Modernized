package net.fawnoculus.ntm.fluid.data;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public record FluidDataType<T>(
  @NotNull Identifier identifier,
  @NotNull Codec<T> codec,
  @Nullable T defaultValue,
  @Nullable TooltipProvider<T> tooltip,
  boolean hasExtraInfo
) {
    public void encode(@NotNull CompoundTag nbt, @Nullable T value) {
        if (value != null) {
            nbt.store(this.identifier.toString(), this.codec, value);
        }
    }

    public T decode(@NotNull CompoundTag nbt) {
        return nbt.read(this.identifier.toString(), this.codec).orElse(this.defaultValue);
    }

    public void appendTooltip(T value, boolean showExtraInfo, Consumer<Component> tooltip) {
        if (this.tooltip != null) {
            this.tooltip.appendTooltip(value, showExtraInfo, tooltip);
        }
    }

    public FluidDataType<T> register() {
        FluidDataRegistry.registerDataType(this);
        return this;
    }

    @FunctionalInterface
    public interface TooltipProvider<T> {
        void appendTooltip(T value, boolean showExtraInfo, Consumer<Component> tooltip);
    }
}
