package net.fawnoculus.ntm.api.config;

import java.io.*;
import java.nio.file.Path;

public interface ConfigEncoding {
    default void readPath(Path path, ConfigFile to) throws IOException {
        this.read(new FileReader(path.toFile()), to);
    }

    default void writePath(Path path, ConfigFile to) throws IOException {
        this.write(new FileWriter(path.toFile()), to);
    }

    void read(Reader reader, ConfigFile to);

    void write(Writer writer, ConfigFile from);
}
