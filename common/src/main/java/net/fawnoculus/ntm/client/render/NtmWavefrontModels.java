package net.fawnoculus.ntm.client.render;

import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.client.render.wavefront.WavefrontModelLoader;
import net.fawnoculus.ntm.client.render.wavefront.model.WavefrontModel;
import org.jetbrains.annotations.Contract;

public class NtmWavefrontModels {
    public static WavefrontModel ALLOY_FURNACE_EXTENSION;
    public static WavefrontModel ZIRNOX;
    public static WavefrontModel ZIRNOX_DESTROYED;

    public static void loadModels() {
        ALLOY_FURNACE_EXTENSION = of("alloy_furnace_extension");
        ZIRNOX = of("zirnox");
        ZIRNOX_DESTROYED = of("zirnox_destroyed");
    }

    public static void loadModelTextures() {
        ALLOY_FURNACE_EXTENSION.get("", "Top")
          .ifPresent(model -> model.setTexture(Ntm.id("block/alloy_furnace_top")));
        ALLOY_FURNACE_EXTENSION.get("", "Side")
          .ifPresent(model -> model.setTexture(Ntm.id("block/alloy_furnace_extension")));
        ALLOY_FURNACE_EXTENSION.get("", "Bottom")
          .ifPresent(model -> model.setTexture(Ntm.id("block/alloy_furnace_bottom")));
        ZIRNOX.setTexture(Ntm.id("block/models/zirnox"));
        ZIRNOX_DESTROYED.setTexture(Ntm.id("block/models/zirnox_destroyed"));
    }

    @Contract("_ -> new")
    private static WavefrontModel of(String name) {
        return WavefrontModelLoader.ofWavefrontObj(Ntm.id("models/obj/" + name + ".obj"));
    }
}
