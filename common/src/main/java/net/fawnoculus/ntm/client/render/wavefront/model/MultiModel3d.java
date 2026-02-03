package net.fawnoculus.ntm.client.render.wavefront.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface MultiModel3d extends Model3d {
    Collection<? extends Model3d> getModels();

    @Override
    default List<BakedQuad> bake(@NotNull ModelBaker baker, ModelDebugName simpleModel, Function<Vector3f, Vector3f> offset) {
        List<BakedQuad> quads = new ArrayList<>();
        for (Model3d model3d : this.getModels()) {
            quads.addAll(model3d.bake(baker, simpleModel, offset));
        }
        return quads;
    }

    @Override
    default float getMaxX() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMaxX();
            if (subModel > current) {
                current = subModel;
            }
        }
        return current;
    }

    @Override
    default float getMaxY() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMaxY();
            if (subModel > current) {
                current = subModel;
            }
        }
        return current;
    }

    @Override
    default float getMaxZ() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMaxZ();
            if (subModel > current) {
                current = subModel;
            }
        }
        return current;
    }

    @Override
    default float getMinX() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMinX();
            if (subModel < current) {
                current = subModel;
            }
        }
        return current;
    }

    @Override
    default float getMinY() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMinY();
            if (subModel < current) {
                current = subModel;
            }
        }
        return current;
    }

    @Override
    default float getMinZ() {
        float current = 0;
        for (Model3d model3d : this.getModels()) {
            float subModel = model3d.getMinZ();
            if (subModel < current) {
                current = subModel;
            }
        }
        return current;
    }
}
