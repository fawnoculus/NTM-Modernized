package net.fawnoculus.ntm.util;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

public class CodecUtil {
    public static <T extends Enum<T>> Codec<T> getEnumCodec(Class<T> enumClass) {
        return Codec.STRING.comapFlatMap(string -> {
              try {
                  return DataResult.success(T.valueOf(enumClass, string));
              } catch (Throwable ignored) {
                  return DataResult.error(() -> "Given String is not found in the Enum");
              }
          },
          Enum::name
        );
    }

    public static <T> boolean isValueValid(Codec<T> codec, T value) {
        // Not the most elegant solution, but it shall suffice for now
        return codec.encodeStart(JsonOps.INSTANCE, value).isSuccess();
    }

    public static <T> boolean isValueInvalid(Codec<T> codec, T value) {
        return !isValueValid(codec, value);
    }

    public static <T> String toJsonString(Codec<T> codec, T value) {
        DataResult<JsonElement> result = codec.encodeStart(JsonOps.INSTANCE, value);
        return result.getPartialOrThrow(message -> new IllegalStateException("Failed to encode value to json, value: " + value + ", message: " + message)).toString();
    }
}
