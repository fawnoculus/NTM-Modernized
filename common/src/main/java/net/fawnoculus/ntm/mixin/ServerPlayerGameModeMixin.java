package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {
    @Shadow
    @Final
    protected ServerPlayer player;

    @Shadow
    protected ServerLevel level;

    @Inject(method = "destroyBlock", at = @At(value = "HEAD"))
    private void preBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (player.getMainHandItem().getItem() instanceof SpecialTool specialTool) {
            ItemStack stack = player.getMainHandItem();
            BlockState state = level.getBlockState(pos);

            if (stack.canDestroyBlock(state, level, pos, player) && !(level.getBlockState(pos).getBlock() instanceof GameMasterBlock && !player.canUseGameMasterBlocks())) {
                specialTool.preMine(stack, level, state, pos, player);
            }
        }
    }
}
