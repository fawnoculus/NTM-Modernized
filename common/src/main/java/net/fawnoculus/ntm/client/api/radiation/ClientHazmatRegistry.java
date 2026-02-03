package net.fawnoculus.ntm.client.api.radiation;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.network.s2c.HazmatRegistryPayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class ClientHazmatRegistry {
    private static HashMap<Identifier, Double> hazmatGetter = new HashMap<>();
    private static HashMap<Identifier, Double> hazmatOverrides = new HashMap<>();

    public static double getResistance(LivingEntity entity) {
        double resistance = 0;
        if (entity instanceof EquipmentUser equipmentHolder) {
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.HEAD));
            } catch (Throwable ignored) {
            }
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.CHEST));
            } catch (Throwable ignored) {
            }
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.LEGS));
            } catch (Throwable ignored) {
            }
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.FEET));
            } catch (Throwable ignored) {
            }
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.BODY));
            } catch (Throwable ignored) {
            }
            try {
                resistance += getResistance(equipmentHolder.getItemBySlot(EquipmentSlot.SADDLE));
            } catch (Throwable ignored) {
            }
        }
        return resistance;
    }

    public static double getResistance(ItemStack stack) {
        return getResistance(stack.getItem()) * stack.getCount();
    }

    public static double getResistance(Item item) {
        return getResistance(BuiltInRegistries.ITEM.getKey(item));
    }

    public static double getResistance(Identifier identifier) {
        Double override = hazmatOverrides.get(identifier);
        if (override != null) {
            return override;
        }
        return hazmatGetter.getOrDefault(identifier, 0.0);
    }

    public static void updateFromPacket(HazmatRegistryPayload payload, NetworkManager.PacketContext ignored) {
        hazmatGetter = payload.serializedRegistry().hazmatGetter();
        hazmatOverrides = payload.serializedRegistry().hazmatOverrides();
    }
}
