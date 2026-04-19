package net.fawnoculus.ntm.fabric.client.datagen.tags;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {
    public NtmEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENTITY_TYPE, registriesFuture);
    }

    private static Identifier id(RegistrySupplier<EntityType<?>> entityType) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType.get());
    }

    private static TagKey<EntityType<?>> cTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("c", name));
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
    }

    @Override
    public @NonNull String getName() {
        return "EntityType-Tag Provider";
    }
}
