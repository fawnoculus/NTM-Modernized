package net.fawnoculus.ntm.client.render.wavefront;

import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.util.RenderUtil;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Polygon {
    public final List<GeometryVertex> vertices = new ArrayList<>();
    public final List<TextureCoordinate> coordinates = new ArrayList<>();
    public final List<@Nullable VertexNormal> normals = new ArrayList<>();
    @SuppressWarnings("deprecation")
    private Material texture = new Material(TextureAtlas.LOCATION_BLOCKS, MissingTextureAtlasSprite.getLocation());

    private static Direction normalsToDirection(@NotNull List<VertexNormal> normals) {
        double averageX = 0;
        double averageY = 0;
        double averageZ = 0;

        for (VertexNormal normal : normals) {
            averageX += normal.x();
            averageY += normal.y();
            averageZ += normal.z();
        }

        averageX /= normals.size();
        averageY /= normals.size();
        averageZ /= normals.size();

        double absX = Math.abs(averageX);
        double absY = Math.abs(averageY);
        double absZ = Math.abs(averageZ);

        if (absX > absY && absX > absZ) {
            if (averageX > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        } else if (absY > absZ) {
            if (averageY > 0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (averageZ > 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NORTH;
            }
        }
    }

    public void addVertex(@NotNull GeometryVertex vertex) {
        this.vertices.add(vertex);
    }

    public void addCoordinate(TextureCoordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public void addNormal(@Nullable VertexNormal normal) {
        this.normals.add(normal);
    }

    @SuppressWarnings("deprecation")
    public void setTexture(Identifier blockId) {
        this.texture = new Material(TextureAtlas.LOCATION_BLOCKS, blockId);
    }

    public void setTexture(Material spriteIdentifier) {
        this.texture = spriteIdentifier;
    }

    @Override
    public String toString() {
        return "Polygon{" +
          "vertices=" + vertices +
          ", coordinates=" + coordinates +
          ", normals=" + normals +
          ", texture=" + texture +
          '}';
    }

    // Yes we are turning a triangle into a quad
    // Yes I also hate it
    public BakedQuad bake(@NotNull ModelBaker baker, ModelDebugName simpleModel, Function<Vector3f, Vector3f> offset) {
        Vector3f[] corners = new Vector3f[4];
        if (vertices.size() == 3) {
            corners[0] = offset.apply(vertices.get(0).toVec3f());
            corners[1] = offset.apply(vertices.get(1).toVec3f());
            corners[2] = offset.apply(vertices.get(2).toVec3f());
            corners[3] = offset.apply(vertices.get(0).toVec3f());
        } else if (vertices.size() == 4) {
            corners[0] = offset.apply(vertices.get(0).toVec3f());
            corners[1] = offset.apply(vertices.get(1).toVec3f());
            corners[2] = offset.apply(vertices.get(2).toVec3f());
            corners[3] = offset.apply(vertices.get(3).toVec3f());
        } else {
            throw new IllegalStateException("Can't bake Polygon to quad, vertex count must be either 3 or 4");
        }

        Vector2f[] textureCords = new Vector2f[4];
        if (coordinates.size() == 3) {
            textureCords[0] = coordinates.get(0).toVec2f();
            textureCords[1] = coordinates.get(1).toVec2f();
            textureCords[2] = coordinates.get(2).toVec2f();
            textureCords[3] = coordinates.get(0).toVec2f();
        } else if (coordinates.size() == 4) {
            textureCords[0] = coordinates.get(0).toVec2f();
            textureCords[1] = coordinates.get(1).toVec2f();
            textureCords[2] = coordinates.get(2).toVec2f();
            textureCords[3] = coordinates.get(3).toVec2f();
        } else {
            throw new IllegalStateException("Can't bake Polygon to quad, texture coordinate count must be either 3 or 4");
        }

        return RenderUtil.makeQuad(
          baker.parts(),
          corners,
          textureCords,
          -1,
          baker.sprites().get(texture, simpleModel),
          normalsToDirection(this.normals),
          NtmClientConfig.SHADE_MODELS.getValue(),
          0
        );
    }

    public record Indexed(int[] vertexIndexes, int[] coordinateIndexes, int[] normalIndexes) {
        public Polygon toPolygon(List<GeometryVertex> vertices, List<TextureCoordinate> coordinates, List<VertexNormal> vertexNormals) {
            Polygon polygon = new Polygon();

            for (Integer index : vertexIndexes) {
                if (index < 0) {
                    polygon.addVertex(vertices.get(vertices.size() + index));
                    continue;
                }
                polygon.addVertex(vertices.get(index - 1));
            }
            for (Integer index : coordinateIndexes) {
                if (index < 0) {
                    polygon.addCoordinate(coordinates.get(coordinates.size() + index));
                    continue;
                }
                polygon.addCoordinate(coordinates.get(index - 1));
            }
            for (Integer index : normalIndexes) {
                if (index < 0) {
                    polygon.addNormal(vertexNormals.get(coordinates.size() + index));
                    continue;
                }
                polygon.addNormal(vertexNormals.get(index - 1));
            }

            return polygon;
        }
    }

    public record GeometryVertex(float x, float y, float z) {
        public GeometryVertex(float x, float y, float z, float w) {
            this(
              w != 0 ? x / w : x,
              w != 0 ? y / w : y,
              w != 0 ? z / w : w
            );
        }

        public Vector3f toVec3f() {
            return new Vector3f(x, y, z);
        }
    }

    public record TextureCoordinate(float u, float v) {
        public Vector2f toVec2f() {
            return new Vector2f(1 - u, 1 - v);
        }
    }

    public record VertexNormal(float x, float y, float z) {
    }
}
