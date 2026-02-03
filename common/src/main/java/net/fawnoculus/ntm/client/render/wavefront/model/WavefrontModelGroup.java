package net.fawnoculus.ntm.client.render.wavefront.model;


import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public record WavefrontModelGroup(String name, HashMap<String, WavefrontModelObject> models) implements MultiModel3d {
    public void setTexture(Identifier blockId) {
        for (WavefrontModelObject model3d : models.values()) {
            model3d.setTexture(blockId);
        }
    }

    public void setTexture(Material spriteIdentifier) {
        for (WavefrontModelObject model3d : models.values()) {
            model3d.setTexture(spriteIdentifier);
        }
    }

    @Override
    public Collection<? extends Model3d> getModels() {
        return this.models.values();
    }

    public Optional<WavefrontModelObject> get(String objectName) {
        return Optional.ofNullable(models.get(objectName));
    }
}
