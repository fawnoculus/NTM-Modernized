package net.fawnoculus.ntm.client.render.wavefront.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.Function;

public interface Model3d {
    List<BakedQuad> bake(@NotNull ModelBaker baker, ModelDebugName simpleModel, Function<Vector3f, Vector3f> offset);

    float getMaxX();

    float getMaxY();

    float getMaxZ();

    float getMinX();

    float getMinY();

    float getMinZ();

    default Function<Vector3f, Vector3f> defaultBlockTransforms() {
        return vector3f -> vector3f.add(0.5f, 0f, 0.5f);
    }

    default Function<Vector3f, Vector3f> defaultItemTransforms() {
        final Vector3f itemCenterOffset = this.itemCenterOffset();
        final float itemScalingFactor = this.itemScalingFactor();

        return vector3f -> vector3f.add(itemCenterOffset).mul(itemScalingFactor);
    }

    private Vector3f itemCenterOffset() {
        return new Vector3f(-this.getMinX(), -this.getMinY(), -this.getMinZ());
    }

    private float itemScalingFactor() {
        float xTotal = this.getMaxX() - this.getMinX();
        float yTotal = this.getMaxY() - this.getMinY();
        float zTotal = this.getMaxZ() - this.getMinZ();

        return Math.min(Math.min(1 / xTotal, 1 / yTotal), 1 / zTotal);
    }
}
