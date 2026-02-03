package net.fawnoculus.ntm.api.radiation;

import com.google.gson.JsonObject;
import dev.architectury.platform.Platform;
import io.netty.buffer.ByteBuf;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.util.ExceptionUtil;
import net.fawnoculus.ntm.util.JsonUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class HazmatRegistry {
    private static final HashMap<Identifier, Double> hazmatGetter = new HashMap<>();
    private static final HashMap<Identifier, Double> hazmatOverrides = new HashMap<>();

    public static void init() {
        loadOverrides();
    }

    private static void loadOverrides() {
        File file = Platform.getConfigFolder().resolve("ntm/overrides/hazmat.json").toFile();
        if (!file.exists()) {
            try {
                boolean ignored = file.getParentFile().mkdirs();
                boolean newFile = file.createNewFile();
                if (newFile) {
                    Ntm.LOGGER.info("Created Hazmat Overrides File");
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write("{\n");
                        fileWriter.write("\t\"mod_id:id_of_equipable_item\": 0,\n");
                        fileWriter.write("\t\"example:now_gives_radiation_resistance\": 0.5,\n");
                        fileWriter.write("\t\"example:no_longer_radiation_resistance_giving\": 0\n");
                        fileWriter.write("}");
                    } catch (Exception e) {
                        Ntm.LOGGER.error("An Exception occurred while trying write Examples to Hazmat Overrides File\nException:{}", ExceptionUtil.makePretty(e));
                    }
                } else {
                    Ntm.LOGGER.warn("Failed to crate Hazmat Overrides File");
                }
            } catch (Exception e) {
                Ntm.LOGGER.error("An Exception occurred while trying to crate Hazmat Overrides File\nException:{}", ExceptionUtil.makePretty(e));
            }
            return;
        }
        JsonObject overrides = new JsonObject();
        try {
            FileReader fileReader = new FileReader(file);
            overrides = JsonUtil.jsonFromReader(fileReader);
        } catch (Exception e) {
            Ntm.LOGGER.error("An Exception occurred while trying read Hazmat Overrides File\nException:{}", ExceptionUtil.makePretty(e));
        }

        for (String key : overrides.keySet()) {
            try {
                Identifier identifier = Identifier.parse(key);
                Double value = overrides.get(key).getAsDouble();
                hazmatOverrides.put(identifier, value);
            } catch (Exception e) {
                Ntm.LOGGER.error("An Exception occurred while trying parse Hazmat Overrides for {}\nException:{}", key, ExceptionUtil.makePretty(e));
            }
        }

        Ntm.LOGGER.info("Successfully loaded Hazmat Overrides for {} identifier(s)", hazmatOverrides.size());
    }

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

    public static void register(Block block, double resistance) {
        register(BuiltInRegistries.BLOCK.getKey(block), resistance);
    }

    public static void register(Item item, double resistance) {
        register(BuiltInRegistries.ITEM.getKey(item), resistance);
    }

    public static void register(Identifier identifier, double resistance) {
        if (resistance <= 0) {
            hazmatGetter.remove(identifier);
        } else {
            hazmatGetter.put(identifier, resistance);
        }
    }

    public static Serialized serialize() {
        return new Serialized(hazmatGetter, hazmatOverrides);
    }

    public record Serialized(HashMap<Identifier, Double> hazmatGetter, HashMap<Identifier, Double> hazmatOverrides) {
        public static final StreamCodec<ByteBuf, Serialized> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public Serialized decode(ByteBuf byteBuf) {
                CompoundTag nbt = FriendlyByteBuf.readNbt(byteBuf);
                if (nbt == null) nbt = new CompoundTag();
                return Serialized.decode(nbt);
            }

            @Override
            public void encode(ByteBuf byteBuf, Serialized registry) {
                FriendlyByteBuf.writeNbt(byteBuf, Serialized.encode(registry));
            }
        };

        public static CompoundTag encode(Serialized registry) {
            CompoundTag hazmatGetter = new CompoundTag();
            for (Identifier key : registry.hazmatGetter().keySet()) {
                Double value = registry.hazmatGetter().get(key);
                hazmatGetter.putDouble(key.toString(), value);
            }

            CompoundTag radioactivityOverrides = new CompoundTag();
            for (Identifier key : registry.hazmatOverrides().keySet()) {
                Double value = registry.hazmatOverrides().get(key);
                radioactivityOverrides.putDouble(key.toString(), value);
            }

            CompoundTag nbt = new CompoundTag();
            nbt.put("hazmatGetter", hazmatGetter);
            nbt.put("hazmatOverrides", radioactivityOverrides);
            return nbt;
        }

        public static Serialized decode(CompoundTag json) {
            CompoundTag jsonHazmatGetter = json.getCompoundOrEmpty("hazmatGetter");
            CompoundTag jsonHazmatOverrides = json.getCompoundOrEmpty("hazmatOverrides");

            HashMap<Identifier, Double> hazmatGetter = new HashMap<>();
            for (String key : jsonHazmatGetter.keySet()) {
                Identifier identifier = Identifier.parse(key);
                double value = jsonHazmatGetter.getDoubleOr(key, 0);
                if (value == 0) continue;
                hazmatGetter.put(identifier, value);
            }

            HashMap<Identifier, Double> hazmatOverrides = new HashMap<>();
            for (String key : jsonHazmatOverrides.keySet()) {
                Identifier identifier = Identifier.parse(key);
                double value = jsonHazmatOverrides.getDoubleOr(key, 0);
                hazmatOverrides.put(identifier, value);
            }

            return new Serialized(hazmatGetter, hazmatOverrides);
        }
    }
}
