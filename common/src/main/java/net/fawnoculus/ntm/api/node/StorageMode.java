package net.fawnoculus.ntm.api.node;


import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.util.CodecUtil;
import org.jetbrains.annotations.NotNull;

public enum StorageMode {
    Provide(true, false, "narration.ntm.storage_mode.provide"),
    Consume(false, true, "narration.ntm.storage_mode.consume"),
    Share(true, true, "narration.ntm.storage_mode.share"),
    None(false, false, "narration.ntm.storage_mode.none");

    public static final Codec<StorageMode> CODEC = CodecUtil.getEnumCodec(StorageMode.class);

    public final boolean provides;
    public final boolean consumes;
    public final String translationKey;

    StorageMode(boolean provides, boolean consumes, String translationKey) {
        this.provides = provides;
        this.consumes = consumes;
        this.translationKey = translationKey;
    }

    public static StorageMode cycle(@NotNull StorageMode mode) {
        return switch (mode) {
            case None -> Provide;
            case Provide -> Consume;
            case Consume -> Share;
            case Share -> None;
        };
    }

    public StorageMode cycle() {
        return cycle(this);
    }
}
