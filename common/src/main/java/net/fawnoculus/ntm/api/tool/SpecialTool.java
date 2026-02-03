package net.fawnoculus.ntm.api.tool;


import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface SpecialTool {
    Identifier ADVANCED_MESSAGE_ID = Ntm.id("tool_ability");

    AbilityHandler abilityHandler();

    ModifierHandler modifierHandler();

    boolean canBreakDepthRock();

    default void preMine(ItemStack stack, Level world, BlockState state, BlockPos pos, Player miner) {
        this.abilityHandler().preBreak(stack, world, state, pos, miner);
    }
}
