package net.fawnoculus.ntm.fabric.client.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class NtmDamageTypeTagProvider extends FabricTagProvider<DamageType> {
    public NtmDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_ARMOR)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.LEAD_POISONING.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_EFFECTS)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_ENCHANTMENTS)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.LEAD_POISONING.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_RESISTANCE)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.LEAD_POISONING.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_INVULNERABILITY)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.BYPASSES_COOLDOWN)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.LEAD_POISONING.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());

        getOrCreateRawBuilder(DamageTypeTags.NO_KNOCKBACK)
          .addOptionalElement(NtmDamageTypes.EUTHANIZED.identifier())
          .addOptionalElement(NtmDamageTypes.BLOOD_LOSS.identifier())
          .addOptionalElement(NtmDamageTypes.LEAD_POISONING.identifier())
          .addOptionalElement(NtmDamageTypes.RADIATION.identifier());
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " DamageType-Tag Provider";
    }
}
