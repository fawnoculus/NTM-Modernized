package net.fawnoculus.ntm.api.config;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.serialization.Codec;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.util.ExceptionUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigFile {
    private static final HashMap<String, ConfigFile> CONFIG_FILES = new HashMap<>();
    private final List<ConfigOption<?>> OPTIONS = new ArrayList<>();
    private final String SUB_PATH;
    private final ConfigEncoding ENCODING;
    private final Path PATH;


    public ConfigFile(ConfigEncoding encoding, String subPath) {
        this.ENCODING = encoding;
        this.SUB_PATH = subPath;
        this.PATH = Platform.getConfigFolder().resolve(subPath);

        CONFIG_FILES.put(subPath, this);
    }

    public ConfigFile(ConfigEncoding encoding, Path path, String subPath) {
        this.ENCODING = encoding;
        this.SUB_PATH = subPath;
        this.PATH = path;

        CONFIG_FILES.put(subPath, this);
    }

    @Nullable
    public static ConfigFile getFile(@NotNull String subPath) {
        return CONFIG_FILES.get(subPath);
    }

    public void init() {
        if (this.PATH.toFile().exists()) {
            this.readFile();
        }
        this.writeFile();
    }

    public void readFile() {
        try {
            this.ENCODING.readPath(this.PATH, this);
        } catch (Exception e) {
            Ntm.LOGGER.warn("Failed to read config file '{}'\nException: {}", this.PATH, ExceptionUtil.makePretty(e));
        }
    }

    public void writeFile() {
        try {
            if (this.PATH.toFile().exists()) {
                boolean ignored = this.PATH.toFile().delete();
            } else {
                boolean ignored = this.PATH.getParent().toFile().mkdirs();
            }
            this.ENCODING.writePath(this.PATH, this);
        } catch (Exception e) {
            Ntm.LOGGER.warn("Failed to write config file '{}'\nException: {}", this.PATH, ExceptionUtil.makePretty(e));
        }
    }

    public ArgumentBuilder<CommandSourceStack, ?> getCommand(String commandName) {
        LiteralArgumentBuilder<CommandSourceStack> get = Commands.literal("get");
        LiteralArgumentBuilder<CommandSourceStack> set = Commands.literal("set");

        for (ConfigOption<?> option : OPTIONS) {
            get.then(option.getGetCommand());
            set.then(option.getSetCommand(this));
        }

        return Commands.literal(commandName)
          .then(Commands.literal("reload")
            .executes(context -> {
                context.getSource().sendSuccess(() -> Component.translatable("command.ntm.reload_configs", this.SUB_PATH), true);
                this.readFile();
                return 0;
            })
          ).then(set).then(get);
    }

    @Environment(EnvType.CLIENT)
    public ArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack, ?> getClientCommand(String commandName) {
        LiteralArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack> get = ClientCommandRegistrationEvent.literal("get");
        LiteralArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack> set = ClientCommandRegistrationEvent.literal("set");

        for (ConfigOption<?> option : OPTIONS) {
            get.then(option.getGetClientCommand());
            set.then(option.getSetClientCommand(this));
        }

        return ClientCommandRegistrationEvent.literal(commandName)
          .then(ClientCommandRegistrationEvent.literal("reload")
            .executes(context -> {
                context.getSource().arch$sendSuccess(() -> Component.translatable("command.ntm.reload_configs", this.SUB_PATH), true);
                this.readFile();
                return 0;
            })
          ).then(set).then(get);
    }

    @NotNull
    public List<ConfigOption<?>> getOptions() {
        return this.OPTIONS;
    }

    public String getSubPath() {
        return this.SUB_PATH;
    }

    public void addOption(@NotNull ConfigOption<?> option) {
        this.OPTIONS.add(option);
    }

    public <T> ConfigOption<T> newOption(@NotNull String name, @NotNull Codec<T> codec, @NotNull T defaultValue) {
        return this.newOption(name, codec, defaultValue, null);
    }

    public <T> ConfigOption<T> newOption(@NotNull String name, @NotNull Codec<T> codec, @NotNull T defaultValue, @Nullable String comment) {
        ConfigOption<T> option = new ConfigOption<>(name, codec, defaultValue, comment);
        this.addOption(option);
        return option;
    }
}
