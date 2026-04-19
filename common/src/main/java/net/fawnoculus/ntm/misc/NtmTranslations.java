package net.fawnoculus.ntm.misc;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

public class NtmTranslations {
    public static final String COMMAND_GET_OPTION_INFO_NAME = commandKey("get_option_info.name");
    public static final String COMMAND_GET_OPTION_INFO_COMMENT = commandKey("get_option_info.comment");
    public static final String COMMAND_GET_OPTION_INFO_VALUE = commandKey("get_option_info.current_value");
    public static final String COMMAND_GET_OPTION_INFO_DEFAULT = commandKey("get_option_info.default");
    public static final String COMMAND_GET_OPTION_INFO_WORLD_DEFAULT = commandKey("get_option_info.world-default");
    public static final String COMMAND_RELOAD_CONFIG = commandKey("reload_configs");
    public static final String COMMAND_RELOAD_WORLD_CONFIG = commandKey("reload_world_configs");
    public static final String COMMAND_RELOAD_WORLD_CONFIG_NOT_EXIST = commandKey("reload_world_configs.doesnt_exist");
    public static final String COMMAND_RELOAD_WORLD_DEFAULT_CONFIG = commandKey("reload_world_default_configs");
    public static final String COMMAND_SET_CONFIG = commandKey("set_config_value");
    public static final String COMMAND_SET_CONFIG_FAILED = commandKey("set_config_value.failed");
    public static final String COMMAND_SET_CONFIG_PER_WORLD_NOT_EXIST = commandKey("set_config_value.per_world_doesnt_exist");
    public static final String COMMAND_SET_CONFIG_WORLD_DEFAULT = commandKey("set_config_value.world-default");
    public static final String COMMAND_SET_CONFIG_FAILED_WORLD_DEFAULT = commandKey("set_config_value.failed.world-default");
    public static final String COMMAND_SET_CONFIG_INVALID_JSON = commandKey("set_config_value.invalid_json");

    public static final String RESOURCE_PACK_LEGACY_NAME = "resourcePack.ntm_legacy.name";
    public static final String RESOURCE_PACK_LEGACY_DESCRIPTION = "resourcePack.ntm_legacy.description";

    public static final String NARRATION_STORAGE_MODE_NONE = narrationKey("storage_mode.none");
    public static final String NARRATION_STORAGE_MODE_CONSUME = narrationKey("storage_mode.consume");
    public static final String NARRATION_STORAGE_MODE_PROVIDE = narrationKey("storage_mode.provide");
    public static final String NARRATION_STORAGE_MODE_SHARE = narrationKey("storage_mode.share");

    public static final String SCREEN_TOOL_ABILITIES = screenKey("tool_abilities");

    public static final String CONTAINER_ALLOY_FURNACE = containerKey("alloy_furnace");
    public static final String CONTAINER_ELECTRIC_FURNACE = containerKey("electric_furnace");
    public static final String CONTAINER_ENERGY_STORAGE = containerKey("energy_storage");

    public static final String GENERIC_DELTA_KELVIN = genericKey("delta_kelvin"); // Temperature Units (TU). 1TU = change by 1 Kelvin (1ΔK)
    public static final String GENERIC_ENERGY = genericKey("energy");
    public static final String GENERIC_ENERGY_PER_SEC = genericKey("energy_per_sec");
    public static final String GENERIC_ENERGY_PER_TICK = genericKey("energy_per_tick");
    public static final String GENERIC_FLUID_DROPLET = genericKey("fluid.droplet");
    public static final String GENERIC_FLUID_DROPLETS = genericKey("fluid.droplets");
    public static final String GENERIC_FLUID_MB = genericKey("fluid.mb");
    public static final String GENERIC_RAD = genericKey("radiation.rad");
    public static final String GENERIC_RAD_PER_SEC = genericKey("radiation.rad_per_sec");
    public static final String GENERIC_RAD_PER_TICK = genericKey("radiation.rad_per_tick");
    public static final String GENERIC_TEMP_CELSIUS = genericKey("temp.celsius");
    public static final String GENERIC_TEMP_FAHRENHEIT = genericKey("temp.fahrenheit");
    public static final String GENERIC_TEMP_KELVIN = genericKey("temp.kelvin");
    public static final String GENERIC_UNIT_PREFIX_KILO = genericKey("unit_prefix.kilo");
    public static final String GENERIC_UNIT_PREFIX_MEGA = genericKey("unit_prefix.mega");
    public static final String GENERIC_UNIT_PREFIX_GIGA = genericKey("unit_prefix.giga");
    public static final String GENERIC_UNIT_PREFIX_TERA = genericKey("unit_prefix.tera");
    public static final String GENERIC_UNIT_PREFIX_PETA = genericKey("unit_prefix.peta");
    public static final String GENERIC_UNIT_PREFIX_EXA = genericKey("unit_prefix.exa");

    public static final String FLUID_ANTIMATTER = fluidTooltipKey("antimatter");
    public static final String FLUID_BOILABLE = fluidTooltipKey("boilable");
    public static final String FLUID_BREATHABLE = fluidTooltipKey("breathable");
    public static final String FLUID_COMBUSTIBLE = fluidTooltipKey("combustible");
    public static final String FLUID_COMBUSTIBLE_FUEL_GRADE = fluidTooltipKey("combustible.fuel_grade");
    public static final String FLUID_FUEL_GRADE_AVIATION = fluidTooltipKey("fuel_grade.aviation");
    public static final String FLUID_FUEL_GRADE_GASEOUS = fluidTooltipKey("fuel_grade.gaseous");
    public static final String FLUID_FUEL_GRADE_HIGH = fluidTooltipKey("fuel_grade.high");
    public static final String FLUID_FUEL_GRADE_MEDIUM = fluidTooltipKey("fuel_grade.medium");
    public static final String FLUID_FUEL_GRADE_LOW = fluidTooltipKey("fuel_grade.low");
    public static final String FLUID_COOLABLE = fluidTooltipKey("coolable");
    public static final String FLUID_HEATABLE = fluidTooltipKey("heatable");
    public static final String FLUID_DELICIOUS = fluidTooltipKey("delicious");
    public static final String FLUID_EFFICIENCY = fluidTooltipKey("efficiency");
    public static final String FLUID_FLAMMABLE = fluidTooltipKey("flammable");
    public static final String FLUID_GASEOUS_AT_ROOM_TEMP = fluidTooltipKey("gaseous_at_room_temperature");
    public static final String FLUID_GLYPHID_PHEROMONES = fluidTooltipKey("glyphid_pheromones");
    public static final String FLUID_MODIFIED_PHEROMONES = fluidTooltipKey("modified_pheromones");
    public static final String FLUID_ICF_COOLANT = fluidTooltipKey("icf_coolant");
    public static final String FLUID_IGNORED_BY_SIPHON = fluidTooltipKey("ignored_by_siphon");
    public static final String FLUID_PARTICLE_ACCELERATOR_COOLANT = fluidTooltipKey("particle_accelerator_coolant");
    public static final String FLUID_POLLUTING = fluidTooltipKey("polluting");
    public static final String FLUID_POLLUTING_BURNED = fluidTooltipKey("polluting.burned");
    public static final String FLUID_POLLUTING_SPILLED = fluidTooltipKey("polluting.spilled");
    public static final String FLUID_POLLUTING_VAL = fluidTooltipKey("polluting.val");
    public static final String FLUID_PROVIDES = fluidTooltipKey("provides");
    public static final String FLUID_PWR_COOLANT = fluidTooltipKey("pwr_coolant");
    public static final String FLUID_PWR_FLUX_MULTIPLIER = fluidTooltipKey("pwr_flux_multiplier");
    public static final String FLUID_PWR_FLUX_MULTIPLIER_VALUE = fluidTooltipKey("pwr_flux_multiplier.val");
    public static final String FLUID_RADIOACTIVE = fluidTooltipKey("radioactive");
    public static final String FLUID_STATE_PLASMA = fluidTooltipKey("state.plasma");
    public static final String FLUID_STATE_GASEOUS = fluidTooltipKey("state.gaseous");
    public static final String FLUID_STATE_LIQUID = fluidTooltipKey("state.liquid");
    public static final String FLUID_STATE_SOLID = fluidTooltipKey("state.solid");
    public static final String FLUID_THERMAL_CAPACITY = fluidTooltipKey("thermal_capacity");
    public static final String FLUID_TOXIC_FUMES = fluidTooltipKey("toxic_fumes");
    public static final String FLUID_TOXIN = fluidTooltipKey("toxin");
    public static final String FLUID_TOXIN_DPS = fluidTooltipKey("toxin.dps");
    public static final String FLUID_TOXIN_EFFECTS = fluidTooltipKey("toxin.effects");
    public static final String FLUID_TOXIN_TYPE = fluidTooltipKey("toxin.type");
    public static final String FLUID_TOXIN_TYPE_AEROSOLS = fluidTooltipKey("toxin_type.aerosols");
    public static final String FLUID_TOXIN_TYPE_AIRBORNE_PARTICLES = fluidTooltipKey("toxin_type.airborne_particles");
    public static final String FLUID_TOXIN_TYPE_CARBON_MONOXIDE = fluidTooltipKey("toxin_type.carbon_monoxide");
    public static final String FLUID_TOXIN_TYPE_CHEMICAL_GAS = fluidTooltipKey("toxin_type.chemical_gas");
    public static final String FLUID_TOXIN_TYPE_CORROSIVE_FUMES = fluidTooltipKey("toxin_type.corrosive_fumes");
    public static final String FLUID_TOXIN_TYPE_PARTICULATES = fluidTooltipKey("toxin_type.particulates");
    public static final String FLUID_TURBINE_STEAM = fluidTooltipKey("turbine_steam");
    public static final String FLUID_VISCOUS = fluidTooltipKey("viscous");

    public static final String TOOLTIP_CREATIVE_ONLY = tooltipKey("creative_only");
    public static final String TOOLTIP_NEEDS_BOTTLE_OPENER = tooltipKey("needs_bottle_opener");
    public static final String TOOLTIP_RADIOACTIVE = tooltipKey("radioactive");
    public static final String TOOLTIP_STACK_RADS = tooltipKey("stack_rads");
    public static final String TOOLTIP_RADIATION_RESISTANCE = tooltipKey("radiation_resistance");
    public static final String TOOLTIP_CONSTRUCTION_WAND_BLOCK = tooltipKey("construction_wand.block");
    public static final String TOOLTIP_CONSTRUCTION_WAND_POS = tooltipKey("construction_wand.pos");
    public static final String TOOLTIP_CONSTRUCTION_WAND_NO_POS = tooltipKey("construction_wand.no_pos");
    public static final String TOOLTIP_DANGEROUS_DROP = tooltipKey("dangerous_drop");
    public static final String TOOLTIP_CAN_BREAK_DEPTH_ROCK = tooltipKey("can_break_depth_rock");
    public static final String TOOLTIP_HOLD_FOR_INFO = tooltipKey("hold_for_info");
    public static final String TOOLTIP_ENERGY_CHARGE = tooltipKey("energy.charge");
    public static final String TOOLTIP_ENERGY_DISCHARGE = tooltipKey("energy.discharge");
    public static final String TOOLTIP_ENERGY_STORED = tooltipKey("energy.stored");

    public static final String TOOLTIP_MODIFIER_START = tooltipKey("modifier.start");
    public static final String TOOLTIP_ABILITY_START = tooltipKey("ability.start");
    public static final String TOOLTIP_ABILITY_END_1 = tooltipKey("ability.end1");
    public static final String TOOLTIP_ABILITY_END_2 = tooltipKey("ability.end2");
    public static final String TOOLTIP_ABILITY_END_3 = tooltipKey("ability.end3");
    public static final String TOOLTIP_ABILITY_GUI_ADD_NEW = tooltipKey("ability.gui.add_new");
    public static final String TOOLTIP_ABILITY_GUI_CLOSE = tooltipKey("ability.gui.close_gui");
    public static final String TOOLTIP_ABILITY_GUI_DELETE_CURRENT = tooltipKey("ability.gui.delete_current");
    public static final String TOOLTIP_ABILITY_GUI_NEXT = tooltipKey("ability.gui.next");
    public static final String TOOLTIP_ABILITY_GUI_PREVIOUS = tooltipKey("ability.gui.previous");
    public static final String TOOLTIP_ABILITY_GUI_RESET_PRESETS = tooltipKey("ability.gui.reset_presets");
    public static final String TOOLTIP_ABILITY_GUI_SELECT_FIRST = tooltipKey("ability.gui.select_first");

    public static final String MESSAGE_ABILITY_DEACTIVATE = messageKey("ability.deactivate");
    public static final String MESSAGE_ABILITY_ENABLE_1 = messageKey("ability.enable_1");
    public static final String MESSAGE_ABILITY_ENABLE_2 = messageKey("ability.enable_2");
    public static final String MESSAGE_ALL_EFFECTS_AFFECTED_TARGETS = messageKey("all_effects.affected_targets");
    public static final String MESSAGE_ALL_EFFECTS_IMMUNE_TARGETS = messageKey("all_effects.immune_targets");
    public static final String MESSAGE_CLEAN_LOGS = messageKey("clean_logs");
    public static final String MESSAGE_CONSTRUCTION_WAND_FILLED = messageKey("construction_wand.filled");
    public static final String MESSAGE_CONSTRUCTION_WAND_FILLING = messageKey("construction_wand.filling");
    public static final String MESSAGE_CONSTRUCTION_WAND_SET_BLOCK = messageKey("construction_wand.set_block");
    public static final String MESSAGE_CONSTRUCTION_WAND_SET_POS = messageKey("construction_wand.set_pos");
    public static final String MESSAGE_DOSIMETER = messageKey("dosimeter");
    public static final String MESSAGE_FORCE_DISCONNECT = messageKey("force_disconnect");
    public static final String MESSAGE_GEIGER_COUNTER = messageKey("geiger_counter");
    public static final String MESSAGE_GET_COMPONENTS_NO_ITEM = messageKey("get_components.could_not_get_item");
    public static final String MESSAGE_GET_COMPONENTS_MAX_LENGTH = messageKey("get_components.value_max_length");
    public static final String MESSAGE_GET_NODE_NETWORKS = messageKey("get_node_networks");
    public static final String MESSAGE_MESSAGE_CLEARED_ALL = messageKey("message.cleared_all");
    public static final String MESSAGE_MESSAGE_CLEARED_SPECIFIC = messageKey("message.cleared_specific");
    public static final String MESSAGE_MESSAGE_SEND = messageKey("message.sent");
    public static final String MESSAGE_MESSAGE_CLIENT_ADDED = messageKey("message_client.added");
    public static final String MESSAGE_MESSAGE_CLIENT_CLEARED = messageKey("message_client.cleared");
    public static final String MESSAGE_MESSAGE_CLIENT_IS_DISABLED = messageKey("message_client.is_disabled");
    public static final String MESSAGE_MESSAGE_CLIENT_IS_ENABLED = messageKey("message_client.is_enabled");
    public static final String MESSAGE_MESSAGE_CLIENT_REMOVED = messageKey("message_client.removed");
    public static final String MESSAGE_MESSAGE_CLIENT_SET_DISABLED = messageKey("message_client.set_disabled");
    public static final String MESSAGE_MESSAGE_CLIENT_DET_ENABLED = messageKey("message_client.set_enabled");
    public static final String MESSAGE_MUST_BE_EXECUTED_BY_PLAYER = messageKey("must_be_executed_by_player");
    public static final String MESSAGE_NETWORK_DEBUG_NETWORK_NAME = messageKey("network_debug.network_name");
    public static final String MESSAGE_NETWORK_DEBUG_NETWORK_TYPE = messageKey("network_debug.network_type");
    public static final String MESSAGE_NETWORK_DEBUG_NO_NETWORK = messageKey("network_debug.no_network");
    public static final String MESSAGE_NETWORK_DEBUG_CONSUMER_COUNT = messageKey("network_debug.consumer_count");
    public static final String MESSAGE_NETWORK_DEBUG_CONSUMER_PRIORITIES = messageKey("network_debug.consumer_priorities");
    public static final String MESSAGE_NETWORK_DEBUG_NODE_COUNT = messageKey("network_debug.node_count");
    public static final String MESSAGE_NETWORK_DEBUG_NODE_NO_NETWORK = messageKey("network_debug.node_no_network");
    public static final String MESSAGE_NETWORK_DEBUG_NOT_NODE = messageKey("network_debug.not_node");
    public static final String MESSAGE_NETWORK_DEBUG_PROVIDER_COUNT = messageKey("network_debug.provider_count");
    public static final String MESSAGE_NETWORK_DEBUG_PROVIDER_PRIORITIES = messageKey("network_debug.provider_priorities");
    public static final String MESSAGE_NOT_ENOUGH_XP = messageKey("not_enough_xp");
    public static final String MESSAGE_CHUNK_RADIATION = messageKey("radiation.chunk_radiation");
    public static final String MESSAGE_ENVIRONMENTAL_RADIATION = messageKey("radiation.environmental_radiation");
    public static final String MESSAGE_PLAYER_CONTAMINATION = messageKey("radiation.player_contamination");
    public static final String MESSAGE_PLAYER_RESISTANCE = messageKey("radiation.player_resistance");
    public static final String MESSAGE_SET_DEV_CONSTANT = messageKey("set_dev_constant");
    public static final String MESSAGE_SET_ENERGY_FAIL = messageKey("set_energy.fail");
    public static final String MESSAGE_SET_ENERGY_SUCCESS = messageKey("set_energy.success");
    public static final String MESSAGE_TEETH_TO_SOFT = messageKey("teeth_to_soft");
    public static final String MESSAGE_THE_FUNNY = messageKey("the_funny");
    public static final String MESSAGE_VERSION = messageKey("version");
    public static final String MESSAGE_VERSION_CLIENT = messageKey("version.client");
    public static final String MESSAGE_VERSION_MISMATCH_1 = messageKey("version.mismatch1");
    public static final String MESSAGE_VERSION_MISMATCH_2 = messageKey("version.mismatch2");
    public static final String MESSAGE_VERSION_NOT_INSTALLED_1 = messageKey("version.not_installed1");
    public static final String MESSAGE_VERSION_NOT_INSTALLED_2 = messageKey("version.not_installed2");
    public static final String MESSAGE_VERSION_SERVER = messageKey("version.server");

    public static @NonNull String messageKey(String name) {
        return "message." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String commandKey(String name) {
        return "command." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String narrationKey(String name) {
        return "narration." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String screenKey(String name) {
        return "screen." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String containerKey(String name) {
        return "container." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String genericKey(String name) {
        return "generic." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String fluidTooltipKey(String name) {
        return "fluid_tooltip." + Ntm.MOD_ID + "." + name;
    }

    public static @NonNull String tooltipKey(String name) {
        return "tooltip." + Ntm.MOD_ID + "." + name;
    }

    @Contract(pure = true)
    public static @NonNull String tooltipKey11(@NonNull Item item, int line) {
        return tooltipKey(item, line) + ".P11";
    }

    @Contract(pure = true)
    public static @NonNull String tooltipKey11(@NonNull Item item) {
        return tooltipKey(item) + ".P11";
    }

    @Contract(pure = true)
    public static @NonNull String tooltipKey(@NonNull Item item, int line) {
        return "tooltip." + item.getDescriptionId() + line;
    }

    @Contract(pure = true)
    public static @NonNull String tooltipKey(@NonNull Item item) {
        return "tooltip." + item.getDescriptionId();
    }
}
