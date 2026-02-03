package net.fawnoculus.ntm.api.radiation;

import com.google.gson.JsonObject;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import io.netty.buffer.ByteBuf;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.util.ExceptionUtil;
import net.fawnoculus.ntm.util.JsonUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class RadiationRegistry {
    private static final HashMap<Identifier, Double> radioactivityGetter = new HashMap<>();
    private static final HashMap<Identifier, Double> radioactivityOverrides = new HashMap<>();

    public static void init() {
        loadOverrides();
    }

    public static void loadOverrides() {
        File file = Platform.getConfigFolder().resolve("ntm/overrides/radiation.json").toFile();
        if (!file.exists()) {
            try {
                boolean ignored = file.getParentFile().mkdirs();
                boolean newFile = file.createNewFile();
                if (newFile) {
                    Ntm.LOGGER.info("Created Radiation Overrides File");
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        fileWriter.write("{\n");
                        fileWriter.write("\t\"mod_id:item_or_block_id\": 0,\n");
                        fileWriter.write("\t\"example:not_radioactive_before\": 5,\n");
                        fileWriter.write("\t\"example:no_longer_radioactive\": 0,\n");
                        fileWriter.write("\t\"example:values_are_in_milli_rads\": 0,\n");
                        fileWriter.write("\t\"example:this_is_one_rad_per_second\": 1000,\n");
                        fileWriter.write("\t\"example:decimals_are_also_allowed\": 0.75,\n");
                        fileWriter.write("\t\"example:these_overrides_are_for_items_blocks_and_dimensions\": 0\n");
                        fileWriter.write("}");
                    } catch (Exception e) {
                        Ntm.LOGGER.error("An Exception occurred while trying write Examples to Radiation Overrides File\nException:{}", ExceptionUtil.makePretty(e));
                    }
                } else {
                    Ntm.LOGGER.warn("Failed to crate Radiation Overrides File");
                }
            } catch (Exception e) {
                Ntm.LOGGER.error("An Exception occurred while trying to crate Radiation Overrides File\nException:{}", ExceptionUtil.makePretty(e));
            }
            return;
        }
        JsonObject overrides = new JsonObject();
        try {
            FileReader fileReader = new FileReader(file);
            overrides = JsonUtil.jsonFromReader(fileReader);
        } catch (Exception e) {
            Ntm.LOGGER.error("An Exception occurred while trying read Radiation Overrides File\nException:{}", ExceptionUtil.makePretty(e));
        }

        for (String key : overrides.keySet()) {
            try {
                Identifier identifier = Identifier.parse(key);
                Double value = overrides.get(key).getAsDouble();
                radioactivityOverrides.put(identifier, value);
            } catch (Exception e) {
                Ntm.LOGGER.error("An Exception occurred while trying parse Radiation Overrides for {}\nException:{}", key, ExceptionUtil.makePretty(e));
            }
        }

        Ntm.LOGGER.info("Successfully loaded Radioactivity Overrides for {} identifier(s)", radioactivityOverrides.size());
    }

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

    public static void register(RegistrySupplier<? extends ItemLike> item, double milliRads) {
        register(item.getId(), milliRads);
    }

    public static void register(Block block, double milliRads) {
        register(BuiltInRegistries.BLOCK.getKey(block), milliRads);
    }

    public static void register(Item item, double milliRads) {
        register(BuiltInRegistries.ITEM.getKey(item), milliRads);
    }

    public static void register(Identifier identifier, double milliRads) {
        if (milliRads <= 0) {
            radioactivityGetter.remove(identifier);
        } else {
            radioactivityGetter.put(identifier, milliRads);
        }
    }

    public static Serialized serialize() {
        return new Serialized(radioactivityGetter, radioactivityOverrides);
    }

    public record Serialized(HashMap<Identifier, Double> radioactivityGetter,
                             HashMap<Identifier, Double> radioactivityOverrides) {
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
            CompoundTag radioactivityGetter = new CompoundTag();
            for (Identifier key : registry.radioactivityGetter().keySet()) {
                radioactivityGetter.putDouble(key.toString(), registry.radioactivityGetter().get(key));
            }

            CompoundTag radioactivityOverrides = new CompoundTag();
            for (Identifier key : registry.radioactivityOverrides().keySet()) {
                radioactivityOverrides.putDouble(key.toString(), registry.radioactivityOverrides().get(key));
            }

            CompoundTag nbt = new CompoundTag();
            nbt.put("radioactivityGetter", radioactivityGetter);
            nbt.put("radioactivityOverrides", radioactivityOverrides);
            return nbt;
        }

        public static Serialized decode(CompoundTag nbt) {
            CompoundTag jsonRadioactivityGetter = nbt.getCompoundOrEmpty("radioactivityGetter");
            CompoundTag jsonRadioactivityOverrides = nbt.getCompoundOrEmpty("radioactivityOverrides");

            HashMap<Identifier, Double> radioactivityGetter = new HashMap<>();
            for (String key : jsonRadioactivityGetter.keySet()) {
                Identifier identifier = Identifier.parse(key);
                double value = jsonRadioactivityGetter.getDoubleOr(key, 0);
                if (value == 0) continue;
                radioactivityGetter.put(identifier, value);
            }

            HashMap<Identifier, Double> radioactivityOverrides = new HashMap<>();
            for (String key : jsonRadioactivityOverrides.keySet()) {
                Identifier identifier = Identifier.parse(key);
                double value = jsonRadioactivityOverrides.getDoubleOr(key, 0);
                radioactivityOverrides.put(identifier, value);
            }

            return new Serialized(radioactivityGetter, radioactivityOverrides);
        }
    }
}
