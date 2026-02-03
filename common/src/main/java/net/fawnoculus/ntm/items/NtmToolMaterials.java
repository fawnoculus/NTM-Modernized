package net.fawnoculus.ntm.items;

import net.fawnoculus.ntm.api.tags.NtmBlockTags;
import net.fawnoculus.ntm.api.tags.NtmItemTags;
import net.minecraft.world.item.ToolMaterial;

public class NtmToolMaterials {
    // TODO: rebalance tools to fit into the Post Combat update era
    public static final ToolMaterial STEEL_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_STEEL_TOOL,
      500,
      6.5f,
      1.5f,
      10,
      NtmItemTags.STEEL_TOOL_MATERIALS
    );
    public static final ToolMaterial TITANIUM_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_TITANIUM_TOOL,
      400,
      6.25f,
      2.0f,
      10,
      NtmItemTags.TITANIUM_TOOL_MATERIALS
    );
    public static final ToolMaterial ADVANCED_ALLOY_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_ADVANCED_ALLOY_TOOL,
      500,
      7.0f,
      2.0f,
      10,
      NtmItemTags.ADVANCED_ALLOY_TOOL_MATERIALS
    );
    public static final ToolMaterial CMB_STEEL_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_CMB_STEEL_TOOL,
      500,
      8.5f,
      6.0f,
      10,
      NtmItemTags.CMB_STEEL_TOOL_MATERIALS
    );
    public static final ToolMaterial COBALT_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_COBALT_TOOL,
      500,
      11.0f,
      1.5f,
      10,
      NtmItemTags.COBALT_TOOL_MATERIALS
    );
    public static final ToolMaterial DECORATED_COBALT_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_DECORATED_COBALT_TOOL,
      500,
      12.0f,
      3f,
      10,
      NtmItemTags.DECORATED_COBALT_TOOL_MATERIALS
    );
    public static final ToolMaterial STARMETAL_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_STARMETAL_TOOL,
      500,
      15.0f,
      5.0f,
      10,
      NtmItemTags.STARMETAL_TOOL_MATERIALS
    );
    public static final ToolMaterial DESH_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_DESH_TOOL,
      500,
      4.0f,
      2.0f,
      10,
      NtmItemTags.DESH_TOOL_MATERIALS
    );
    public static final ToolMaterial SCHRABIDIUM_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_SCHRABIDIUM_TOOL,
      500,
      20.0f,
      9.0f,
      10,
      NtmItemTags.SCHRABIDIUM_TOOL_MATERIALS
    );
    public static final ToolMaterial BISMUTH_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_BISMUTH_TOOL,
      500,
      2.0f,
      14.0f,
      15,
      NtmItemTags.BISMUTH_TOOL_MATERIALS
    );
    public static final ToolMaterial MOLTEN_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_MOLTEN_TOOL,
      500,
      2.0f,
      14.0f,
      15,
      NtmItemTags.MOLTEN_TOOL_MATERIALS
    );
    public static final ToolMaterial CHLOTOPHYTE_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_CHLOROPHYTE_TOOL,
      500,
      2.0f,
      19.0f,
      20,
      NtmItemTags.CHLOROPHYTE_TOOL_MATERIALS
    );
    public static final ToolMaterial MESE_TOOL_MATERIAL = new ToolMaterial(
      NtmBlockTags.INCORRECT_FOR_MESE_TOOL,
      500,
      50.0f,
      34.0f,
      35,
      NtmItemTags.MESE_TOOL_MATERIALS
    );

    public static void init() {
    }
}
