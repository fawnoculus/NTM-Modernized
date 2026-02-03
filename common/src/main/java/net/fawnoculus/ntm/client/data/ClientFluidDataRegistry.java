package net.fawnoculus.ntm.client.data;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.fluid.data.FluidDataRegistry;
import net.fawnoculus.ntm.fluid.data.FluidDataType;
import net.fawnoculus.ntm.network.s2c.FluidDataRegistryPayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Receives a packed containing an NBT Serialized version on the Servers FluidNetworkType Data on Join
 */
public class ClientFluidDataRegistry {
    private static final HashMap<Identifier, ClientFluidDataContainer> CLIENT_FLUID_DATA = new HashMap<>();

    public static @NotNull ClientFluidDataContainer getOrCreate(Fluid fluid) {
        return getOrCreate(BuiltInRegistries.FLUID.getKey(fluid));
    }

    public static @NotNull ClientFluidDataContainer getOrCreate(Identifier fluidID) {
        return CLIENT_FLUID_DATA.computeIfAbsent(fluidID, k -> new ClientFluidDataContainer(new HashMap<>()));
    }

    public static void updateFromPacket(FluidDataRegistryPayload payload, NetworkManager.PacketContext ignored) {
        CompoundTag registryNBT = payload.registryNBT();
        for (String key : registryNBT.keySet()) {
            try {
                Identifier fluidID = Objects.requireNonNull(Identifier.tryParse(key));
                CompoundTag fluidContainerNBT = registryNBT.getCompoundOrEmpty(key);
                ClientFluidDataContainer container = ClientFluidDataContainer.decode(fluidContainerNBT);
                CLIENT_FLUID_DATA.put(fluidID, container);
            } catch (Throwable ignored2) {
            }
        }
    }

    public record ClientFluidDataContainer(HashMap<Identifier, Object> DATA) {
        public static @NotNull ClientFluidDataContainer decode(@NotNull CompoundTag nbt) {
            final HashMap<Identifier, Object> data = new HashMap<>();

            for (String key : nbt.keySet()) {
                try {
                    Identifier typeID = Objects.requireNonNull(Identifier.tryParse(key));
                    FluidDataType<?> type = FluidDataRegistry.getDataType(typeID);
                    putInContainer(type, data, type.decode(nbt));
                } catch (ClassCastException | NullPointerException ignored) {
                }
            }

            return new ClientFluidDataContainer(data);
        }

        private static <T> void getTooltip(FluidDataType<T> type, Object value, boolean showExtraInfo, Consumer<Component> tooltip) throws ClassCastException {
            type.appendTooltip(cast(value), showExtraInfo, tooltip);
        }

        private static <T> void putInContainer(FluidDataType<T> type, @NotNull HashMap<Identifier, Object> data, Object value) throws ClassCastException {
            data.put(type.identifier(), cast(value));
        }

        @SuppressWarnings("unchecked")
        private static <T> T cast(Object value) throws ClassCastException {
            return (T) value;
        }

        public void appendTooltip(boolean showExtraInfo, Consumer<Component> tooltip) {
            boolean hasExtraInfo = false;

            for (Identifier dataID : DATA.keySet()) {
                try {
                    FluidDataType<?> fluidDataType = FluidDataRegistry.getDataType(dataID);
                    if (fluidDataType.hasExtraInfo()) {
                        hasExtraInfo = true;
                    }
                    getTooltip(fluidDataType, getOrDefault(fluidDataType), showExtraInfo, tooltip);

                } catch (Throwable ignored) {
                }
            }

            if (hasExtraInfo && !showExtraInfo) {
                tooltip.accept(Component.translatable("tooltip.ntm.hold_for_info", NtmKeybinds.DISPLAY_EXTRA_INFO.getTranslatedKeyMessage()));
            }
        }

        public <T> Optional<T> get(@NotNull FluidDataType<T> type) {
            try {
                return Optional.ofNullable(
                  cast(DATA.get(type.identifier()))
                );
            } catch (ClassCastException cce) {
                return Optional.empty();
            }
        }

        public <T> T getOrDefault(@NotNull FluidDataType<T> type) {
            return get(type).orElse(type.defaultValue());
        }
    }
}
