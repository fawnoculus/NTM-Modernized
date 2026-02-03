package net.fawnoculus.ntm.gui;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

/**
 * A simple wrapper around Architectury's {@link ExtendedMenuProvider} to provide support for extra data through {@linkplain StreamCodec StreamCodecs}
 * @param <T> the type of extra data to transfer
 */
public interface NtmMenuProvider<T> extends ExtendedMenuProvider {
    @Override
    default void saveExtraData(FriendlyByteBuf buf) {
        this.getCodec().encode(buf, this.getData());
    }

    StreamCodec<? super FriendlyByteBuf, T> getCodec();

    T getData();
}
