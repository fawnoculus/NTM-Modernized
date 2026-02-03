package net.fawnoculus.ntm.client.mixin.accessor;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenInvoker {
    @Invoker("getHoveredSlot")
    Slot ntm$getSlotAt(double mouseX, double mouseY);
}
