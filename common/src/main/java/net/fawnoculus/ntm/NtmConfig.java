package net.fawnoculus.ntm;

import com.mojang.serialization.Codec;
import dev.architectury.platform.Platform;
import net.fawnoculus.ntm.api.config.ConfigFile;
import net.fawnoculus.ntm.api.config.ConfigOption;
import net.fawnoculus.ntm.api.config.PerWorldConfigFile;
import net.fawnoculus.ntm.api.config.PerWorldConfigOption;
import net.fawnoculus.ntm.api.config.encoder.JsonConfigEncoder;
import net.fawnoculus.ntm.util.CodecUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Function;

public class NtmConfig {
    public static final ConfigFile COMMON_CONFIG_FILE = new ConfigFile(
      JsonConfigEncoder.getInstance(),
      "ntm/common.json"
    );
    public static final ConfigOption<Integer> MAX_EXPLOSIONS = COMMON_CONFIG_FILE.newOption(
      "max_explosions", Codec.INT, 10_000,
      "The maximum amount of explosions that can happen at once, any explosions that happens after the limit is reached will be ignored (values below 1 will disable non vanilla explosions) [default: 10000]"
    );
    public static final ConfigOption<Integer> MIN_EXPLOSION_NANOS = COMMON_CONFIG_FILE.newOption(
      "min_explosion_nanos", Codec.INT, 10_000_000,
      "The minimum amount of nanoseconds to spend processing explosions in one tick [default: 10000000]"
    );
    public static final ConfigOption<List<Block>> VEIN_MINER_ABILITY_EXCLUDE = COMMON_CONFIG_FILE.newOption(
      "vein_miner_ability_exclude",
      BuiltInRegistries.BLOCK.byNameCodec().listOf(),
      List.of(
        Blocks.STONE,
        Blocks.COBBLESTONE,
        Blocks.DEEPSLATE,
        Blocks.COBBLED_DEEPSLATE,
        Blocks.NETHERRACK
      ),
      "Blocks that will be excluded from the Vein Miner Tool Ability, it already excludes all blocks that the tool can not mine"
    );
    public static final ConfigOption<List<Block>> AOE_ABILITY_EXCLUDE = COMMON_CONFIG_FILE.newOption(
      "aoe_ability_exclude",
      BuiltInRegistries.BLOCK.byNameCodec().listOf(),
      List.of(
        Blocks.BARRIER,
        Blocks.BEDROCK
      ),
      "Blocks that will be excluded from the Aoe and Flat Aoe Tool Ability, it already excludes all blocks that the tool can not mine"
    );
    public static final ConfigOption<FluidUnit> FLUID_UNIT = COMMON_CONFIG_FILE.newOption(
      "fluid_unit", FluidUnit.CODEC, FluidUnit.MilliBuckets, "Fluid Unit to be displayed (MilliBuckets, Droplets) [default: MilliBuckets]"
    );
    public static final ConfigOption<TempUnit> TEMP_UNIT = COMMON_CONFIG_FILE.newOption(
      "temp_unit", TempUnit.CODEC, TempUnit.Celsius, "Temperature Unit to be displayed (Celsius, Fahrenheit, Kelvin) [default: Celsius]"
    );

    public static final ConfigOption<Boolean> DEV_MODE = COMMON_CONFIG_FILE.newOption(
      "dev_mode", Codec.BOOL, Platform.isDevelopmentEnvironment(),
      "Activates a bunch of dev features and makes more errors print into the log (like /ntm dev)"
    );
    public static final ConfigOption<Boolean> PRINT_STACKTRACE = COMMON_CONFIG_FILE.newOption(
      "print_stacktrace", Codec.BOOL, Platform.isDevelopmentEnvironment(),
      "Make (most) of the Exceptions logged by NTM print their Stacktrace"
    );
    public static final ConfigOption<Integer> MAX_NODE_SCAN_DEPTH = COMMON_CONFIG_FILE.newOption(
      "max_node_scan_depth", Codec.INT, 100_000,
      "The maximum amount of nodes (cables and pipes) to scan when reconstructing node-networks (use anything lower that 1 to disable) [default: 100000]"
    );

    public static final PerWorldConfigFile WORLD_CONFIG = new PerWorldConfigFile(
      JsonConfigEncoder.getInstance(),
      "ntm/world_default.json",
      "ntm/world_config.json"
    );
    public static final PerWorldConfigOption<Boolean> DISABLE_ENTITY_RADIATION = WORLD_CONFIG.newOption(
      "disable_entity_radiation", Codec.BOOL, false,
      "Stops Entities from being able to receive Radiation, does not remove already existent Radiation Poisoning [default: false]"
    );
    public static final PerWorldConfigOption<Boolean> DISABLE_CHUNK_RADIATION = WORLD_CONFIG.newOption(
      "disable_chunk_radiation", Codec.BOOL, false,
      "Disables Chunks having Radioactivity (Requires restating the world or server) [default: false]"
    );

    public static void init() {
        COMMON_CONFIG_FILE.init();
        WORLD_CONFIG.init();
    }

    public enum TempUnit {
        Celsius(celsius -> Component.literal(String.format("%,.1f", celsius)).append(Component.translatable("generic.ntm.temp.c"))),
        Fahrenheit(celsius -> Component.literal(String.format("%,.1f", (celsius * 9 / 5) + 32)).append(Component.translatable("generic.ntm.temp.f"))),
        Kelvin(celsius -> Component.literal(String.format("%,.1f", celsius - 273.15)).append(Component.translatable("generic.ntm.temp.k")));

        public static final Codec<TempUnit> CODEC = CodecUtil.getEnumCodec(TempUnit.class);
        public final Function<Double, MutableComponent> CELSIUS_TO_TEXT;


        TempUnit(Function<Double, MutableComponent> celsiusToText) {
            this.CELSIUS_TO_TEXT = celsiusToText;
        }
    }

    public enum FluidUnit {
        MilliBuckets,
        Droplets;

        public static final Codec<FluidUnit> CODEC = CodecUtil.getEnumCodec(FluidUnit.class);
    }
}
