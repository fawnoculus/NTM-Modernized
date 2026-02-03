package net.fawnoculus.ntm.api.config.encoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.Strictness;
import com.mojang.serialization.JsonOps;
import net.fawnoculus.ntm.api.config.ConfigEncoding;
import net.fawnoculus.ntm.api.config.ConfigFile;
import net.fawnoculus.ntm.api.config.ConfigOption;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class JsonConfigEncoder implements ConfigEncoding {
    public static final Gson GSON = new GsonBuilder()
      .serializeNulls()
      .setStrictness(Strictness.LENIENT)
      .setPrettyPrinting()
      .create();
    private static final JsonConfigEncoder INSTANCE = new JsonConfigEncoder();

    private JsonConfigEncoder() {
    }

    public static JsonConfigEncoder getInstance() {
        return INSTANCE;
    }


    @Override
    public void read(Reader reader, ConfigFile to) {
        JsonObject json = GSON.fromJson(GSON.newJsonReader(reader), JsonObject.class);
        for (ConfigOption<?> option : to.getOptions()) {
            option.setValueFrom(json.get(option.getName()), JsonOps.INSTANCE);
        }
    }

    @Override
    public void write(Writer writer, ConfigFile from) {
        JsonObject json = new JsonObject();
        for (ConfigOption<?> option : from.getOptions()) {
            if (option.getComment() != null) {
                json.addProperty("__" + option.getName() + "_comment", option.getComment());
            }
            json.add(option.getName(), option.getEncodedValue(JsonOps.INSTANCE));
        }
        GSON.toJson(json, writer);
        try {
            writer.flush();
            writer.close();
        } catch (IOException ignored) {
        }
    }
}
