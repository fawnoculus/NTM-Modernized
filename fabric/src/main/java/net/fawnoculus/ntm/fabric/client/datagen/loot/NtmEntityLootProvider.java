package net.fawnoculus.ntm.fabric.client.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmEntityLootProvider extends FabricEntityLootTableProvider {
    public NtmEntityLootProvider(FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generate() {

    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Entity-Loot Provider";
    }
}
