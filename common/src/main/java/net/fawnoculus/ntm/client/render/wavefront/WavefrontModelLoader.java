package net.fawnoculus.ntm.client.render.wavefront;

import net.fawnoculus.ntm.client.NtmClient;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.GeometryVertex;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.TextureCoordinate;
import net.fawnoculus.ntm.client.render.wavefront.Polygon.VertexNormal;
import net.fawnoculus.ntm.client.render.wavefront.model.WavefrontModel;
import net.fawnoculus.ntm.util.ExceptionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class WavefrontModelLoader {
    private final static String OBJ_OBJECT_NAME = "o";
    private final static String OBJ_GROUP_NAME = "g";
    private final static String OBJ_GEOMETRY_VERTEX = "v";
    private final static String OBJ_TEXTURE_COORDINATE = "vt";
    private final static String OBJ_VERTEX_NORMAL = "vn";
    private final static String OBJ_FACE = "f";

    public static WavefrontModel ofWavefrontObj(Identifier resourceIdentifier) {
        Optional<Resource> resource = Minecraft.getInstance().getResourceManager().getResource(resourceIdentifier);
        if (resource.isEmpty()) {
            NtmClient.LOGGER.warn("Could not load '{}' Wavefront File, because it does not exist", resourceIdentifier);
            return WavefrontModel.EMPTY;
        }

        WavefrontModel toBeReturned = WavefrontModel.EMPTY;
        try {
            toBeReturned = ofWavefrontObj(resource.get().openAsReader(), resourceIdentifier.toString());
        } catch (IOException e) {
            NtmClient.LOGGER.warn("Exception occurred while reading '{}' Wavefront File\nException: {}", resourceIdentifier, ExceptionUtil.makePretty(e));
        } catch (IndexOutOfBoundsException e) {
            NtmClient.LOGGER.warn("Exception occurred while parsing '{}' Wavefront File\nException: {}", resourceIdentifier, ExceptionUtil.makePretty(e));
        }

        return toBeReturned;
    }

    public static WavefrontModel ofWavefrontObj(@NotNull BufferedReader reader, String name) throws IOException {
        WavefrontModel.Builder builder = WavefrontModel.builder(name);

        String line;
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }

            if (line.startsWith(OBJ_GROUP_NAME)) {
                builder.setGroup(getGroupName(line));
                continue;
            }
            if (line.startsWith(OBJ_OBJECT_NAME)) {
                builder.setObject(getObjectName(line));
                continue;
            }
            if (line.startsWith(OBJ_TEXTURE_COORDINATE)) {
                builder.addTextureCord(getTextureCoordinate(line));
                continue;
            }
            if (line.startsWith(OBJ_VERTEX_NORMAL)) {
                builder.addVertexNormal(getVertexNormal(line));
                continue;
            }
            if (line.startsWith(OBJ_GEOMETRY_VERTEX)) {
                builder.addGeometryVertex(getGeometryVertex(line));
                continue;
            }
            if (line.startsWith(OBJ_FACE)) {
                builder.addIndexedFace(getFaceIndex(line));
            }
        }

        return builder.build();
    }

    // Strip the extra space only when it is there
    private static @NotNull String getGroupName(@NotNull String line) {
        if (line.startsWith(OBJ_GROUP_NAME + " ")) {
            return line.substring(2);
        }
        return line.substring(1);
    }

    // Strip the extra space only when it is there
    private static @NotNull String getObjectName(@NotNull String line) {
        if (line.startsWith(OBJ_OBJECT_NAME + " ")) {
            return line.substring(2);
        }
        return line.substring(1);
    }

    private static @NotNull TextureCoordinate getTextureCoordinate(String line) {
        float[] floats = parseFloats(2, line, OBJ_TEXTURE_COORDINATE.length());
        return new TextureCoordinate(floats[0], floats[1]);
    }

    public static @NotNull VertexNormal getVertexNormal(String line) {
        float[] floats = parseFloats(3, line, OBJ_TEXTURE_COORDINATE.length());
        return new VertexNormal(floats[0], floats[1], floats[2]);
    }

    public static @NotNull GeometryVertex getGeometryVertex(String line) {
        float[] floats = parseFloats(4, line, OBJ_TEXTURE_COORDINATE.length());
        if (floats[3] == 0) {
            floats[3] = 1;
        }
        return new GeometryVertex(floats[0], floats[1], floats[2], floats[3]);
    }

    public static Polygon.Indexed getFaceIndex(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return null;
        }

        int[] vertexIndices = new int[3];
        int[] coordinateIndices = new int[3];
        int[] normalIndices = new int[3];
        int index = 0;

        char[] chars = line.toCharArray();
        int charsLength = chars.length;

        int currentIndex = whitespaceEnd(OBJ_FACE.length(), chars);
        int start;
        int end;

        while (currentIndex < charsLength) {
            start = whitespaceEnd(currentIndex, chars);
            end = whitespaceStart(start, chars);

            int[] indices = parseFaceIndices(chars, start, end);

            vertexIndices[index] = indices[0];
            coordinateIndices[index] = indices[1];
            normalIndices[index] = indices[2];

            index++;
            if (index >= 3) {
                break;
            }

            currentIndex = end;
            if (currentIndex >= charsLength) {
                break;
            }
        }

        return new Polygon.Indexed(vertexIndices, coordinateIndices, normalIndices);
    }

    @Contract(pure = true)
    public static int whitespaceEnd(int index, char @NotNull [] chars) {
        while (index < chars.length) {
            if (chars[index] != ' '
              && chars[index] != '\n'
              && chars[index] != '\t'
              && chars[index] != '\r'
            ) {
                break;
            }
            index++;
        }
        return index;
    }

    @Contract(pure = true)
    public static int whitespaceStart(int index, char @NotNull [] chars) {
        while (index < chars.length) {
            if (chars[index] == ' '
              || chars[index] == '\n'
              || chars[index] == '\t'
              || chars[index] == '\r'
            ) {
                break;
            }
            index++;
        }
        return index;
    }

    public static float[] parseFloats(int floatCount, String string, int startIndex) {
        if (string == null) {
            return null;
        }
        if (string.isEmpty()) {
            return null;
        }

        float[] returnArray = new float[floatCount];
        int returnArrayCount = 0;

        char[] chars = string.toCharArray();
        int charsLength = chars.length;

        int currentIndex = whitespaceEnd(startIndex, chars);
        int floatStart;
        int floatEnd;
        int floatLength;

        while (currentIndex < charsLength) {
            floatStart = whitespaceEnd(currentIndex, chars);
            floatEnd = whitespaceStart(floatStart, chars);
            floatLength = floatEnd - floatStart;

            try {
                returnArray[returnArrayCount] = Float.parseFloat(new String(chars, floatStart, floatLength));
                returnArrayCount++;
            } catch (NumberFormatException e) {
                returnArrayCount++;
            }

            if (returnArrayCount >= floatCount) {
                break;
            }

            currentIndex = floatEnd;
            if (currentIndex >= charsLength) {
                break;
            }
        }

        return returnArray;
    }

    @Contract(pure = true)
    public static int faceIndexSeparatorStart(int index, char @NotNull [] chars) {
        while (index < chars.length) {
            if (chars[index] == '/'
              || chars[index] == ' '
              || chars[index] == '\n'
              || chars[index] == '\t'
              || chars[index] == '\r') {
                break;
            }
            index++;
        }
        return index;
    }

    public static int @NotNull [] parseFaceIndices(char @NotNull [] chars, int currentIndex, int end) {
        int charsLength = chars.length;

        int[] returnArray = new int[3];
        int returnArrayCount = 0;

        currentIndex = whitespaceEnd(currentIndex, chars);

        int intStart = currentIndex;
        int intEnd;
        int intLength;

        while (intStart < end) {
            intEnd = Math.min(faceIndexSeparatorStart(intStart, chars), end);
            intLength = intEnd - intStart;

            try {
                returnArray[returnArrayCount] = Integer.parseInt(new String(chars, intStart, intLength));
                returnArrayCount++;
            } catch (NumberFormatException e) {
                returnArrayCount++;
            }

            if (returnArrayCount >= 3) {
                break;
            }

            currentIndex = intEnd;
            intStart = currentIndex + 1;
            if (currentIndex >= charsLength) {
                break;
            }
        }

        return returnArray;
    }
}
