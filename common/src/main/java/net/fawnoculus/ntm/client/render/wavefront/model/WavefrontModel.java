package net.fawnoculus.ntm.client.render.wavefront.model;

import net.fawnoculus.ntm.client.render.wavefront.Polygon;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.GeometryVertex;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.TextureCoordinate;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.VertexNormal;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;

import java.util.*;

public record WavefrontModel(String name, HashMap<String, WavefrontModelGroup> models) implements MultiModel3d {
    public static final WavefrontModel EMPTY = new WavefrontModel("Empty", new HashMap<>());

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public void setTexture(Identifier blockId) {
        for (WavefrontModelGroup wavefrontModelGroup : models.values()) {
            wavefrontModelGroup.setTexture(blockId);
        }
    }

    public void setTexture(Material spriteIdentifier) {
        for (WavefrontModelGroup wavefrontModelGroup : models.values()) {
            wavefrontModelGroup.setTexture(spriteIdentifier);
        }
    }

    public Optional<WavefrontModelGroup> get(String groupName) {
        return Optional.ofNullable(models.get(groupName));
    }

    public Optional<WavefrontModelObject> get(String groupName, String objectName) {
        Optional<WavefrontModelGroup> group = this.get(groupName);
        return group.flatMap(wavefrontModelGroup -> wavefrontModelGroup.get(objectName));
    }

    @Override
    public Collection<? extends MultiModel3d> getModels() {
        return this.models.values();
    }

    public static class Builder {
        private final String NAME;
        private final List<GeometryVertex> geometryVertices = new ArrayList<>();
        private final List<TextureCoordinate> textureCoordinates = new ArrayList<>();
        private final List<VertexNormal> vertexNormals = new ArrayList<>();
        private final HashMap<String, HashMap<String, List<Polygon.Indexed>>> groupedFaces = new HashMap<>();
        private List<Polygon.Indexed> faceIndices = new ArrayList<>();
        private String group = "";
        private String object = "";

        public Builder(String name) {
            this.NAME = name;
        }

        public void addGeometryVertex(GeometryVertex geometryVertex) {
            this.geometryVertices.add(geometryVertex);
        }

        public void addTextureCord(TextureCoordinate textureCoordinate) {
            this.textureCoordinates.add(textureCoordinate);
        }

        public void addVertexNormal(VertexNormal vertexNormal) {
            this.vertexNormals.add(vertexNormal);
        }

        public void addIndexedFace(Polygon.Indexed indexedFace) {
            this.faceIndices.add(indexedFace);
        }

        public void setGroup(String group) {
            this.resetLocation();

            this.group = group;
            this.faceIndices = this.groupedFaces
              .getOrDefault(group, new HashMap<>())
              .getOrDefault(this.object, new ArrayList<>());
        }

        public void setObject(String object) {
            this.resetLocation();

            this.object = object;
            this.faceIndices = this.groupedFaces
              .getOrDefault(this.group, new HashMap<>())
              .getOrDefault(object, new ArrayList<>());
        }

        private void resetLocation() {
            HashMap<String, List<Polygon.Indexed>> groupMap = this.groupedFaces.getOrDefault(this.group, new HashMap<>());
            groupMap.put(this.object, this.faceIndices);
            this.groupedFaces.put(this.group, groupMap);

            this.faceIndices = new ArrayList<>();
        }

        public WavefrontModel build() {
            this.resetLocation();

            HashMap<String, WavefrontModelGroup> groups = new HashMap<>();
            for (String group : this.groupedFaces.keySet()) {
                HashMap<String, WavefrontModelObject> objects = new HashMap<>();
                HashMap<String, List<Polygon.Indexed>> objectMap = this.groupedFaces.get(group);

                for (String object : objectMap.keySet()) {
                    List<Polygon.Indexed> indexedPolygons = objectMap.get(object);
                    objects.put(object, new WavefrontModelObject(object, indexedPolygons.stream()
                      .map(indexed -> indexed.toPolygon(this.geometryVertices, this.textureCoordinates, this.vertexNormals))
                      .toList()
                    ));
                }

                groups.put(group, new WavefrontModelGroup(group, objects));
            }


            return new WavefrontModel(this.NAME, groups);
        }
    }
}
