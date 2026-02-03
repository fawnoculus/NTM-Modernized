package net.fawnoculus.ntm.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.client.api.NtmClientApi;
import net.fawnoculus.ntm.client.commands.NtmClientCommands;
import net.fawnoculus.ntm.client.gui.NtmHandledScreens;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.client.network.NtmClientPayloadHandler;
import net.fawnoculus.ntm.client.render.*;
import net.fawnoculus.ntm.gui.NtmMenuType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class NtmClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(Ntm.MOD_NAME + "/Client");

    public static void init() {
        LOGGER.debug("Initializing Client Components ...");

        NtmClientConfig.init();
        NtmClientApi.init();

        NtmKeybinds.init();

        NtmClientCommands.init();

        NtmModelOverrides.init();

        NtmBlockEntityRender.init();
        NtmParticleRender.init();
        NtmHudRender.init();

        NtmMenuType.init();
        NtmHandledScreens.init();

        NtmClientPayloadHandler.init();

        LOGGER.debug("Finished Client Initialization");
    }
}
