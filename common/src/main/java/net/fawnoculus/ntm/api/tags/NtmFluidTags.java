package net.fawnoculus.ntm.api.tags;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class NtmFluidTags {

    private static TagKey<Fluid> of(String name) {
        return of(Ntm.id(name));
    }

    private static TagKey<Fluid> of(Identifier identifier) {
        return TagKey.create(Registries.FLUID, identifier);
    }
}
