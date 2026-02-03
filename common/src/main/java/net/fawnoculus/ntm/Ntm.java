package net.fawnoculus.ntm;

import dev.architectury.platform.Mod;
import dev.architectury.platform.Platform;
import net.fawnoculus.ntm.api.NtmApi;
import net.fawnoculus.ntm.blocks.NtmBlockEntities;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.blocks.NtmExtraBlockData;
import net.fawnoculus.ntm.commands.NtmCommands;
import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.fawnoculus.ntm.entity.NtmStatusEffects;
import net.fawnoculus.ntm.fluid.NtmFluidData;
import net.fawnoculus.ntm.fluid.NtmFluids;
import net.fawnoculus.ntm.items.*;
import net.fawnoculus.ntm.misc.*;
import net.fawnoculus.ntm.network.NtmNetworking;
import net.fawnoculus.ntm.recipe.NtmRecipes;
import net.fawnoculus.ntm.world.NtmWorldGeneration;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ntm {
    public static final String MOD_ID = "ntm";
    public static final Mod MOD = Platform.getMod(MOD_ID);
    public static final String MOD_NAME = MOD.getName();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final String MOD_VERSION = MOD.getVersion();
    public static final NtmProxy PROXY = NtmProxy.getProxy();

    public static void init() {
        LOGGER.info("Initializing ...");

        NtmConfig.init();
        NtmApi.init();

        NtmDamageTypes.init();
        NtmStatusEffects.init();

        NtmSounds.init();
        NtmParticles.init();

        NtmFluids.init();
        NtmFluidData.init();

        NtmBlocks.init();
        NtmBlockEntities.init();
        NtmExtraBlockData.init();

        NtmToolMaterials.init();
        NtmDataComponentTypes.init();
        NtmItems.init();
        NtmItemGroups.init();
        NtmExtraItemData.init();
        NtmEnchantmentEffects.init();

        NtmRecipes.init();

        NtmCommands.init();

        NtmWorldGeneration.init();

        NtmNetworking.init();

        NtmResourceLoading.init();
        NtmDeferredRegistries.init();

        LOGGER.info("Finished Initialization");
    }

    @Contract("_ -> new")
    public static @NotNull Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
