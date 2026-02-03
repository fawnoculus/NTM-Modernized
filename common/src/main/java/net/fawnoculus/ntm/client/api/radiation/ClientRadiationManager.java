package net.fawnoculus.ntm.client.api.radiation;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.api.radiation.RadiationManager;
import net.fawnoculus.ntm.network.s2c.RadiationInformationPayload;
import org.jetbrains.annotations.Range;

/**
 * The Client Radiation Manager is mosty responsible for getting Information like:
 * <br>- Current Chunk Radiation
 * <br>- Total Environmental Radiation
 * <br>- Player Radiation Exposure
 * <br>- etc.
 * <br>So that Items like the Geiger Counter can display them to the Player
 * <br>It is not responsible for Handling anything Else, Everything else is done in the {@link RadiationManager RadiationManager}
 */
public class ClientRadiationManager {
    public static @Range(from = 0, to = 1000000) double radiationExposure = 0;
    public static double inventoryRadiation = 0;
    public static double activeChunkRadiation = 0;
    public static double passiveChunkRadiation = 0;
    public static double totalChunkRadiation = 0;
    public static double totalRadiation = 0;

    public static void handlePacket(RadiationInformationPayload payload, NetworkManager.PacketContext ignored) {
        RadiationInformationPayload.RadiationInfo info = payload.info();
        radiationExposure = info.radiationExposure();
        inventoryRadiation = info.inventoryRadiation();
        activeChunkRadiation = info.activeChunkRadiation();
        passiveChunkRadiation = info.passiveChunkRadiation();
        totalChunkRadiation = activeChunkRadiation + passiveChunkRadiation;
        totalRadiation = inventoryRadiation + totalChunkRadiation;
    }
}
