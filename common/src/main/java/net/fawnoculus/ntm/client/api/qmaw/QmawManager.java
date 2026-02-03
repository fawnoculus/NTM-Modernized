package net.fawnoculus.ntm.client.api.qmaw;

import net.fawnoculus.ntm.client.NtmClient;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public class QmawManager {
    public static boolean openQmawPage(Minecraft client, @Nullable Slot hoveredSlot) {
        if (hoveredSlot != null) {
            NtmClient.LOGGER.info("Hovered Item {}", hoveredSlot.getItem().getItem().getName());
        } else {
            NtmClient.LOGGER.info("Hovered Slot == null");
        }

        //TODO this
        return true;
    }
}
