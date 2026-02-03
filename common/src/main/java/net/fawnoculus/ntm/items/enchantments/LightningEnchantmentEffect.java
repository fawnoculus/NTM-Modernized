package net.fawnoculus.ntm.items.enchantments;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record LightningEnchantmentEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<LightningEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
      instance ->
        instance.group(
          LevelBasedValue.CODEC.fieldOf("amount").forGetter(LightningEnchantmentEffect::amount)
        ).apply(instance, LightningEnchantmentEffect::new)
    );

    @Override
    public void apply(@NonNull ServerLevel world, int level, @NonNull EnchantedItemInUse context, @NonNull Entity target, @NonNull Vec3 pos) {
        if (target instanceof LivingEntity victim && context.owner() instanceof @SuppressWarnings("unused")Player player) {
            float numStrikes = this.amount.calculate(level);

            BlockPos position = victim.blockPosition();

            for (float i = 0; i < numStrikes; i++) {
                EntityType.LIGHTNING_BOLT.spawn(world, position, EntitySpawnReason.TRIGGERED);
            }
        }
    }

    @Override
    public @NonNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
