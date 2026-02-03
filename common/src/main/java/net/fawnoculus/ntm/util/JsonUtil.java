package net.fawnoculus.ntm.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.io.StringReader;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static JsonObject jsonFromString(String string) {
        return JsonUtil.jsonFromReader(new StringReader(string));
    }

    public static JsonObject jsonFromReader(Reader reader) {
        return gson.fromJson(new JsonReader(reader), JsonObject.class);
    }
}
