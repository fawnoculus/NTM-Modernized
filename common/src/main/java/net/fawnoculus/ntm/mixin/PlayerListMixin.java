package net.fawnoculus.ntm.mixin;

import net.fawnoculus.ntm.api.events.custom.EarlyPlayerJoinEvent;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getRecipeManager()Lnet/minecraft/world/item/crafting/RecipeManager;"))
    private void earlyJoin(Connection connection, ServerPlayer player, CommonListenerCookie clientData, CallbackInfo ci) {
        EarlyPlayerJoinEvent.EVENT.invoker().onJoin(connection, player, clientData);
    }
}
