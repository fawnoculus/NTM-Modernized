package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.misc.NtmSounds;
import net.fawnoculus.ntm.util.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class IvBagItem extends Item {
    public IvBagItem(Properties settings) {
        super(settings);
    }


    @Override
    public @NonNull InteractionResult use(Level world, @NonNull Player player, @NonNull InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (!player.isCreative()) {
            EntityUtil.applyDamage(player, (ServerLevel) world, NtmDamageTypes.BLOOD_LOSS, 5F);
            ItemStack stack = player.getItemInHand(hand);
            stack.shrink(1);
        }
        world.playSound(null, BlockPos.containing(player.position()).above(), NtmSounds.IV_BAG_INJECTS.get(), SoundSource.PLAYERS);
        player.getInventory().placeItemBackInInventory(new ItemStack(NtmItems.BLOOD_BAG));

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, LivingEntity target, @NonNull LivingEntity attacker) {
        if (!(target.level() instanceof ServerLevel world) || !(attacker instanceof Player player)) {
            return;
        }
        if (!player.isCreative()) {
            EntityUtil.applyDamage(target, world, NtmDamageTypes.BLOOD_LOSS, 5F);
            stack.shrink(1);
        }
        world.playSound(null, BlockPos.containing(target.position()).above(), NtmSounds.IV_BAG_INJECTS.get(), SoundSource.PLAYERS);
        player.getInventory().placeItemBackInInventory(new ItemStack(NtmItems.BLOOD_BAG));
    }
}
