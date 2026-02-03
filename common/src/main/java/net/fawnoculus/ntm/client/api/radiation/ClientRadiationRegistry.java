package net.fawnoculus.ntm.client.api.radiation;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.network.s2c.RadiationRegistryPayload;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ClientRadiationRegistry {
    private static HashMap<Identifier, Double> radioactivityGetter = new HashMap<>();
    private static HashMap<Identifier, Double> radioactivityOverrides = new HashMap<>();

    public static double getRadioactivity(Iterable<ItemStack> stacks) {
        double radioactivity = 0;
        boolean hasTungstenReacher = false;
        for (ItemStack stack : stacks) {
            radioactivity += getRadioactivity(stack);
            if (stack.getItem() == NtmItems.TUNGSTEN_REACHER) {
                hasTungstenReacher = true;
            }
            if (stack.has(DataComponents.CONTAINER)) {
                radioactivity += getRadioactivity(
                  stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).stream().toList()
                );
            }
        }
        if (hasTungstenReacher) {
            radioactivity = Math.sqrt(radioactivity);
        }
        return radioactivity;
    }

    public static double getRadioactivity(Level world) {
        try {
            return getRadioactivity(world.registryAccess().lookupOrThrow(Registries.DIMENSION_TYPE).getKey(world.dimensionType()));
        } catch (Throwable throwable) {
            return 0;
        }
    }

    public static double getRadioactivity(BlockState state) {
        return getRadioactivity(state.getBlock());
    }

    public static double getRadioactivity(Block block) {
        return getRadioactivity(BuiltInRegistries.BLOCK.getKey(block));
    }

    public static double getRadioactivity(ItemStack stack) {
        return getRadioactivity(stack.getItem()) * stack.getCount();
    }

    public static double getRadioactivity(Item item) {
        return getRadioactivity(BuiltInRegistries.ITEM.getKey(item));
    }

    public static double getRadioactivity(Identifier identifier) {
        Double override = radioactivityOverrides.get(identifier);
        if (override != null) {
            return override;
        }
        return radioactivityGetter.getOrDefault(identifier, 0.0);
    }

    public static void updateFromPacket(@NotNull RadiationRegistryPayload payload, NetworkManager.PacketContext ignored) {
        radioactivityGetter = payload.serializedRegistry().radioactivityGetter();
        radioactivityOverrides = payload.serializedRegistry().radioactivityOverrides();
    }
}
