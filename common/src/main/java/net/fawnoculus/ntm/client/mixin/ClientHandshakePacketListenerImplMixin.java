package net.fawnoculus.ntm.client.mixin;

import net.fawnoculus.ntm.client.api.events.custom.ConnectToServerEvent;
import net.fawnoculus.ntm.client.api.events.custom.DisconnectFromServerEvent;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.protocol.login.ClientboundLoginFinishedPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientHandshakePacketListenerImpl.class)
public abstract class ClientHandshakePacketListenerImplMixin {

    @Inject(at = @At("TAIL"), method = "handleLoginFinished")
    private void onConnect(ClientboundLoginFinishedPacket packet, CallbackInfo ci) {
        ConnectToServerEvent.EVENT.invoker().onJoin((ClientHandshakePacketListenerImpl) (Object) this, packet);
    }

    @Inject(at = @At("TAIL"), method = "onDisconnect")
    private void onDisconnect(DisconnectionDetails details, CallbackInfo ci) {
        DisconnectFromServerEvent.EVENT.invoker().onJoin((ClientHandshakePacketListenerImpl) (Object) this);
    }
}
