package net.fawnoculus.ntm.gui;


import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.gui.menus.AlloyFurnaceMenu;
import net.fawnoculus.ntm.gui.menus.ElectricFurnaceMenu;
import net.fawnoculus.ntm.gui.menus.EnergyStorageMenu;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.fawnoculus.ntm.network.s2c.BlockPosPayload;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

public class NtmMenuType {
    public static <T extends AbstractContainerMenu, D extends CustomPacketPayload> RegistrySupplier<MenuType<@NotNull T>>
    register(String name, ScreenFactory<@NotNull T, @NotNull D> screenFactory, StreamCodec<? super FriendlyByteBuf, D> packetCodec) {
        return NtmDeferredRegistries.MENU_TYPES.register(Ntm.id(name), () ->
          MenuRegistry.ofExtended((id, inventory, buf) -> screenFactory.makeMenu(id, inventory, packetCodec.decode(buf)))
        );
    }

    public static void init() {
    }

    @FunctionalInterface
    public interface ScreenFactory<T extends AbstractContainerMenu, D> {
        T makeMenu(int id, Inventory inventory, D data);
    }    public static final RegistrySupplier<MenuType<AlloyFurnaceMenu>> ALLOY_FURNACE =
      register("alloy_furnace", AlloyFurnaceMenu::new, BlockPosPayload.STREAM_CODEC);

    public static final RegistrySupplier<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE =
      register("electric_furnace", ElectricFurnaceMenu::new, BlockPosPayload.STREAM_CODEC);

    public static final RegistrySupplier<MenuType<EnergyStorageMenu>> ENERGY_STORAGE =
      register("energy_storage", EnergyStorageMenu::new, BlockPosPayload.STREAM_CODEC);



}
