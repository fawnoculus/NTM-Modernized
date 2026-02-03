package net.fawnoculus.ntm.items.custom.consumable;

import net.fawnoculus.ntm.entity.NtmDamageTypes;
import net.fawnoculus.ntm.items.custom.TooltipItem;
import net.fawnoculus.ntm.util.EntityUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class PlanC extends TooltipItem {
    public PlanC(Properties settings) {
        super(settings);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level world, @NonNull LivingEntity user) {
        if (world instanceof ServerLevel serverWorld) {
            if (!user.hasInfiniteMaterials()) {
                EntityUtil.applyDamage(user, serverWorld, NtmDamageTypes.EUTHANIZED, Float.MAX_VALUE);
            } else {
                EntityUtil.applyDamage(user, serverWorld, NtmDamageTypes.EUTHANIZED, 10);
            }
        }
        return super.finishUsingItem(stack, world, user);
    }
}
