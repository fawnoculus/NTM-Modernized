package net.fawnoculus.ntm.api.config;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PerWorldConfigFile {
    private static final List<PerWorldConfigFile> PER_WORLD_CONFIG_FILES = new ArrayList<>();
    private final List<PerWorldConfigOption<?>> OPTIONS = new ArrayList<>();
    private final ConfigEncoding ENCODING;
    private final String WORLD_SUB_PATH;
    private final String DEFAULT_SUB_PATH;
    private final ConfigFile DEFAULT_CONFIG;
    private @Nullable ConfigFile WORLD_CONFIG;

    public PerWorldConfigFile(ConfigEncoding encoding, String defaultSubPath, String worldSubPath) {
        this.ENCODING = encoding;
        this.WORLD_SUB_PATH = worldSubPath;
        this.DEFAULT_SUB_PATH = defaultSubPath;
        this.DEFAULT_CONFIG = new ConfigFile(encoding, defaultSubPath);

        PER_WORLD_CONFIG_FILES.add(this);
    }

    public static List<PerWorldConfigFile> getPerWorldConfigFiles() {
        return PER_WORLD_CONFIG_FILES;
    }

    public void setWorldPath(@NotNull Path worldSavePath) {
        this.WORLD_CONFIG = new ConfigFile(this.ENCODING, worldSavePath.resolve(this.WORLD_SUB_PATH), WORLD_SUB_PATH);
        for (PerWorldConfigOption<?> option : this.OPTIONS) {
            option.setWorldConfigFile(this.WORLD_CONFIG);
        }
        this.WORLD_CONFIG.init();
    }

    public void removeWorldPath() {
        if (this.WORLD_CONFIG != null) {
            this.WORLD_CONFIG.writeFile();
        }
        for (PerWorldConfigOption<?> option : this.OPTIONS) {
            option.unsetWorldConfigFile();
        }
        this.WORLD_CONFIG = null;
    }

    public ArgumentBuilder<CommandSourceStack, ?> getCommand(String commandName) {
        LiteralArgumentBuilder<CommandSourceStack> get = Commands.literal("get");
        LiteralArgumentBuilder<CommandSourceStack> set = Commands.literal("set");
        LiteralArgumentBuilder<CommandSourceStack> setDefault = Commands.literal("set-default");

        for (PerWorldConfigOption<?> option : OPTIONS) {
            get.then(option.getGetCommand());
            set.then(option.getSetCommand(this));
            set.then(option.getSetDefaultCommand(this));
        }

        return Commands.literal(commandName)
          .then(Commands.literal("reload")
            .executes(context -> {
                if (this.WORLD_CONFIG == null) {
                    context.getSource().sendFailure(Component.translatable("command.ntm.reload_world_configs.doesnt_exist"));
                    return -1;
                }
                this.WORLD_CONFIG.readFile();
                context.getSource().sendSuccess(() -> Component.translatable("command.ntm.reload_world_configs", this.WORLD_SUB_PATH), true);
                return 0;
            })
          )
          .then(Commands.literal("reload-default")
            .executes(context -> {
                this.DEFAULT_CONFIG.readFile();
                context.getSource().sendSuccess(() -> Component.translatable("command.ntm.reload_world_default_configs", this.DEFAULT_SUB_PATH), true);
                return 0;
            })
          )
          .then(set)
          .then(setDefault)
          .then(get);
    }

    public void init() {
        this.DEFAULT_CONFIG.init();
    }

    public ConfigFile defaultConfig() {
        return this.DEFAULT_CONFIG;
    }

    public ConfigFile worldConfig() {
        return this.WORLD_CONFIG;
    }

    public <T> PerWorldConfigOption<T> newOption(String name, Codec<T> codec, T defaultValue) {
        return this.newOption(name, codec, defaultValue, null);
    }

    public <T> PerWorldConfigOption<T> newOption(String name, Codec<T> codec, T defaultValue, String comment) {
        PerWorldConfigOption<T> option = new PerWorldConfigOption<>(name, codec, this.DEFAULT_CONFIG.newOption(name, codec, defaultValue, comment), comment);
        this.OPTIONS.add(option);
        return option;
    }
}
