package net.fawnoculus.ntm.fabric.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NtmAdvancementProvider extends FabricAdvancementProvider {
    public NtmAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {

    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Advancement Provider";
    }
}
