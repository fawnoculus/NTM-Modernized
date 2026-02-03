package net.fawnoculus.ntm.items.custom.tools;

import net.fawnoculus.ntm.api.tags.NtmBlockTags;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.fawnoculus.ntm.api.tool.ModifierHandler;
import net.minecraft.world.item.ToolMaterial;


public class SpecialBigAxeItem extends SpecialToolItem {
    public SpecialBigAxeItem(Properties settings, ToolMaterial material, float attackDamage, float attackSpeed) {
        this(settings, material, attackDamage, attackSpeed, AbilityHandler.builder().build(), ModifierHandler.builder().build(), false);
    }

    public SpecialBigAxeItem(Properties settings, ToolMaterial material, float attackDamage, float attackSpeed, AbilityHandler abilities, ModifierHandler modifiers, boolean canBreakDepthRock) {
        super(settings.tool(material, NtmBlockTags.BIG_AXE_MINEABLE, attackDamage, attackSpeed, 5.0F), abilities, modifiers, canBreakDepthRock);
    }
}
