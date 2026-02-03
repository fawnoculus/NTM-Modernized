package net.fawnoculus.ntm.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class NtmSounds {

    public static final RegistrySupplier<SoundEvent> SYRINGE_INJECTS = register("item.syringe_injects");
    public static final RegistrySupplier<SoundEvent> IV_BAG_INJECTS = register("item.iv_bag_injects");
    public static final RegistrySupplier<SoundEvent> PAIN_SAW = register("item.pain_saw");
    public static final RegistrySupplier<SoundEvent> TECH_BOOP = register("item.tech_boop");
    public static final RegistrySupplier<SoundEvent> TECH_BEEP = register("item.tech_bleep");


    private static RegistrySupplier<SoundEvent> register(String id) {
        Identifier identifier = Ntm.id(id);
        return NtmDeferredRegistries.SOUNDS.register(identifier, () -> SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void init() {
    }
}
