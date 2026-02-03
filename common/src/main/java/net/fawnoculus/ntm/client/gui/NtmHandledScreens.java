package net.fawnoculus.ntm.client.gui;

import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.client.gui.handled.AlloyFurnaceScreen;
import net.fawnoculus.ntm.client.gui.handled.ElectricFurnaceScreen;
import net.fawnoculus.ntm.client.gui.handled.EnergyStorageScreen;
import net.fawnoculus.ntm.gui.NtmMenuType;

public class NtmHandledScreens {
    public static void init() {
        NtmPlatform.registerScreenFactory(NtmMenuType.ALLOY_FURNACE, AlloyFurnaceScreen::new);
        NtmPlatform.registerScreenFactory(NtmMenuType.ELECTRIC_FURNACE, ElectricFurnaceScreen::new);
        NtmPlatform.registerScreenFactory(NtmMenuType.ENERGY_STORAGE, EnergyStorageScreen::new);
    }
}
