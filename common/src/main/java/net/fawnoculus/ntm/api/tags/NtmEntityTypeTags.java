package net.fawnoculus.ntm.api.tags;

import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class NtmEntityTypeTags {

    private static TagKey<EntityType<?>> of(String name) {
        return of(Ntm.id(name));
    }

    private static TagKey<EntityType<?>> of(Identifier identifier) {
        return TagKey.create(Registries.ENTITY_TYPE, identifier);
    }
}
