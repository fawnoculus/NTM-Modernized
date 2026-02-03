package net.fawnoculus.ntm.api.tags;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NtmItemTags {
    // Tool Stuff
    public static final TagKey<Item> STEEL_TOOL_MATERIALS = of("steel_tool_materials");
    public static final TagKey<Item> TITANIUM_TOOL_MATERIALS = of("titanium_tool_materials");
    public static final TagKey<Item> ADVANCED_ALLOY_TOOL_MATERIALS = of("advanced_alloy_tool_materials");
    public static final TagKey<Item> CMB_STEEL_TOOL_MATERIALS = of("cmb_steel_tool_materials");
    public static final TagKey<Item> DESH_TOOL_MATERIALS = of("desh_tool_materials");
    public static final TagKey<Item> COBALT_TOOL_MATERIALS = of("cobalt_tool_materials");
    public static final TagKey<Item> DECORATED_COBALT_TOOL_MATERIALS = of("decorated_cobalt_tool_materials");
    public static final TagKey<Item> STARMETAL_TOOL_MATERIALS = of("starmetal_tool_materials");
    public static final TagKey<Item> SCHRABIDIUM_TOOL_MATERIALS = of("schrabidium_tool_materials");
    public static final TagKey<Item> BISMUTH_TOOL_MATERIALS = of("bismuth_tool_materials");
    public static final TagKey<Item> MOLTEN_TOOL_MATERIALS = of("molten_tool_materials");
    public static final TagKey<Item> CHLOROPHYTE_TOOL_MATERIALS = of("chlorophyte_tool_materials");
    public static final TagKey<Item> MESE_TOOL_MATERIALS = of("mese_tool_materials");

    // Ores
    public static final TagKey<Item> URANIUM_ORES = of("uranium_ores");
    public static final TagKey<Item> SCORCHED_URANIUM_ORES = of("scorched_uranium_ores");
    public static final TagKey<Item> THORIUM_ORES = of("thorium_ores");
    public static final TagKey<Item> TITANIUM_ORES = of("titanium_ores");
    public static final TagKey<Item> NITER_ORES = of("niter_ores");
    public static final TagKey<Item> SULFUR_ORES = of("sulfur_ores");
    public static final TagKey<Item> TUNGSTEN_ORES = of("tungsten_ores");
    public static final TagKey<Item> CRYOLITE_ORES = of("cryolite_ores");
    public static final TagKey<Item> FLUORITE_ORES = of("fluorite_ores");
    public static final TagKey<Item> BERYLLIUM_ORES = of("beryllium_ores");
    public static final TagKey<Item> LEAD_ORES = of("lead_ores");
    public static final TagKey<Item> LIGNITE_ORES = of("lignite_ores");
    public static final TagKey<Item> ASBESTOS_ORES = of("asbestos_ores");
    public static final TagKey<Item> SCHRABIDIUM_ORES = of("schrabidium_ores");
    public static final TagKey<Item> AUSTRALIUM_ORES = of("australium_ores");
    public static final TagKey<Item> RARE_EARTH_ORES = of("rare_earth_ores");
    public static final TagKey<Item> COBALT_ORES = of("cobalt_ores");
    public static final TagKey<Item> CINNABAR_ORES = of("cinnabar_ores");
    public static final TagKey<Item> COLTAN_ORES = of("coltan_ores");
    public static final TagKey<Item> PLUTONIUM_ORES = of("plutonium_ores");
    public static final TagKey<Item> PHOSPHORUS_ORES = of("phosphorus_ores");
    public static final TagKey<Item> TRIXITE_ORES = of("trixite_ores");
    public static final TagKey<Item> OSMIRIDIUM_ORES = of("osmiridium_ores");
    public static final TagKey<Item> METEORIC_IRON_ORES = of("meteoric_iron_ores");
    public static final TagKey<Item> METEORIC_COPPER_ORES = of("meteoric_copper_ores");
    public static final TagKey<Item> METEORIC_ALUMINIUM_ORES = of("meteoric_aluminium_ores");
    public static final TagKey<Item> METEORIC_RARE_EARTH_ORES = of("meteoric_rare_earth_ores");
    public static final TagKey<Item> METEORIC_COBALT_ORES = of("meteoric_cobalt_ores");

    private static TagKey<Item> of(String name) {
        return of(Ntm.id(name));
    }

    private static TagKey<Item> of(Identifier identifier) {
        return TagKey.create(Registries.ITEM, identifier);
    }
}
