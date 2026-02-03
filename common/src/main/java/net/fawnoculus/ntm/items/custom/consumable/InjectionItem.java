package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.BiConsumer;

public class InjectionItem extends Item {
    private final @Nullable RegistrySupplier<SoundEvent> SOUND;
    private final List<RegistrySupplier<Item>> RETURN_ITEMS;
    private final BiConsumer<ServerLevel, LivingEntity> SERVER_USE;

    public InjectionItem(Properties settings, @Nullable RegistrySupplier<SoundEvent> sound, @Nullable RegistrySupplier<Item> returnItem, BiConsumer<ServerLevel, LivingEntity> serverUse) {
        this(settings, sound, returnItem != null ? List.of(returnItem) : List.of(), serverUse);
    }

    public InjectionItem(Properties settings, @Nullable RegistrySupplier<SoundEvent> sound, List<RegistrySupplier<Item>> returnItems, BiConsumer<ServerLevel, LivingEntity> serverUse) {
        super(settings);

        this.SOUND = sound;
        this.RETURN_ITEMS = returnItems;
        this.SERVER_USE = serverUse;
    }

    @Override
    public @NonNull InteractionResult use(Level world, @NonNull Player player, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (!player.isCreative()) {
            ItemStack stack = player.getItemInHand(hand);
            stack.shrink(1);
        }
        if (this.SOUND != null) {
            world.playSound(null, BlockPos.containing(player.position()).above(), this.SOUND.get(), SoundSource.PLAYERS);
        }
        for (RegistrySupplier<Item> returnItem : RETURN_ITEMS) {
            player.getInventory().placeItemBackInInventory(new ItemStack(returnItem));
        }

        this.SERVER_USE.accept((ServerLevel) world, player);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, LivingEntity target, @NonNull LivingEntity attacker) {
        if (target.level().isClientSide() || !(attacker instanceof Player player)) {
            return;
        }
        ServerLevel world = (ServerLevel) target.level();
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        if (this.SOUND != null) {
            world.playSound(null, BlockPos.containing(player.position()).above(), this.SOUND.get(), SoundSource.PLAYERS);
        }
        for (RegistrySupplier<Item> returnItem : RETURN_ITEMS) {
            player.getInventory().placeItemBackInInventory(new ItemStack(returnItem));
        }

        this.SERVER_USE.accept(world, target);
    }
}
