package net.fawnoculus.ntm.client;


import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.api.config.ConfigFile;
import net.fawnoculus.ntm.api.config.ConfigOption;
import net.fawnoculus.ntm.api.config.encoder.JsonConfigEncoder;

public class NtmClientConfig {
    public static final ConfigFile CLIENT_CONFIG_FILE = new ConfigFile(JsonConfigEncoder.getInstance(), "ntm/client.json");
    public static final ConfigOption<Integer> TOOL_ABILITY_DISPLAY_X_OFFSET = CLIENT_CONFIG_FILE.newOption(
      "tool_ability_x_offset", Codec.INT, 10,
      "Tool ability display x offset from crosshair [default: 10]"
    );
    public static final ConfigOption<Integer> TOOL_ABILITY_DISPLAY_Y_OFFSET = CLIENT_CONFIG_FILE.newOption(
      "tool_ability_y_offset", Codec.INT, 10,
      "Tool ability display y offset from crosshair [default: 10]"
    );
    public static final ConfigOption<Boolean> FIX_EFFECT_LEVEL = CLIENT_CONFIG_FILE.newOption(
      "fix_effect_level", Codec.BOOL, true,
      "If Status Effects with high Amplifiers should be displayed as Arabic Numerals if the roman Numeral is not Available [default: true]"
    );
    public static final ConfigOption<Boolean> BLOCK_MODEL_AMBIENT_OCCLUSION = CLIENT_CONFIG_FILE.newOption(
      "block_model_ambient_occlusion", Codec.BOOL, false,
      "makes the models lock a little better, but looks weird when they are placed next to each other [default: false]"
    );
    public static final ConfigOption<Boolean> SHADE_MODELS = CLIENT_CONFIG_FILE.newOption(
      "shade_models", Codec.BOOL, true,
      "whether to shade the models [default: true]"
    );

    public static void init() {
        CLIENT_CONFIG_FILE.init();
    }
}
