package net.fawnoculus.ntm.client.render.wavefront.model;

import net.fawnoculus.ntm.client.render.wavefront.Polygon;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record WavefrontModelObject(String name, List<Polygon> polygons) implements Model3d {
    public void setTexture(Identifier blockId) {
        for (Polygon face : polygons) {
            face.setTexture(blockId);
        }
    }

    public void setTexture(Material spriteIdentifier) {
        for (Polygon face : polygons) {
            face.setTexture(spriteIdentifier);
        }
    }

    public @NotNull List<BakedQuad> bake(@NotNull ModelBaker baker, ModelDebugName simpleModel, Function<Vector3f, Vector3f> offset) {
        List<BakedQuad> quads = new ArrayList<>();
        for (Polygon face : polygons) {
            quads.add(face.bake(baker, simpleModel, offset));
        }
        return quads;
    }

    @Override
    public float getMaxX() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.x() > current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }

    @Override
    public float getMaxY() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.y() > current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }

    @Override
    public float getMaxZ() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.z() > current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }

    @Override
    public float getMinX() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.x() < current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }

    @Override
    public float getMinY() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.y() < current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }

    @Override
    public float getMinZ() {
        float current = 0f;

        for (Polygon polygon : this.polygons) {
            for (Polygon.GeometryVertex vertex : polygon.vertices) {
                if (vertex.z() < current) {
                    current = vertex.x();
                }
            }
        }

        return current;
    }
}
