package net.fawnoculus.ntm.client.gui;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.client.gui.MenuScreenRegistry;
import net.fawnoculus.ntm.client.gui.handled.AlloyFurnaceScreen;
import net.fawnoculus.ntm.client.gui.handled.ElectricFurnaceScreen;
import net.fawnoculus.ntm.client.gui.handled.EnergyStorageScreen;
import net.fawnoculus.ntm.gui.NtmMenuType;

public class NtmHandledScreens {
    public static void init() {
        ClientLifecycleEvent.CLIENT_SETUP.register(client -> {
            MenuScreenRegistry.registerScreenFactory(NtmMenuType.ALLOY_FURNACE.get(), AlloyFurnaceScreen::new);
            MenuScreenRegistry.registerScreenFactory(NtmMenuType.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
            MenuScreenRegistry.registerScreenFactory(NtmMenuType.ENERGY_STORAGE.get(), EnergyStorageScreen::new);
        });
    }
}
