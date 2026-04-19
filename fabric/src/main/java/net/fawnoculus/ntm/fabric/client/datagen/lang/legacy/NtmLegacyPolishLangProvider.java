package net.fawnoculus.ntm.fabric.client.datagen.lang.legacy;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fawnoculus.ntm.fabric.client.datagen.lang.NtmCommonLangProvider;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmLegacyPolishLangProvider extends NtmCommonLangProvider {
    public NtmLegacyPolishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "pl_pl", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, @NonNull TranslationBuilder builder) {
        builder.add(tooltip(NtmItems.BOTTLE_OPENER, 1), "My very own bottle opener.");

        builder.add(NtmTranslations.GENERIC_ENERGY, "HE");
        builder.add(NtmTranslations.GENERIC_ENERGY_PER_SEC, "HE/s");
        builder.add(NtmTranslations.GENERIC_ENERGY_PER_TICK, "HE/t");

        addDownloadedTranslations("pl_PL", builder);
    }
}
