package net.fawnoculus.ntm.fabric.client.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {
    public NtmEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENTITY_TYPE, registriesFuture);
    }

    private static Identifier id(EntityType<?> entityType) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " EntityType-Tag Provider";
    }
}
