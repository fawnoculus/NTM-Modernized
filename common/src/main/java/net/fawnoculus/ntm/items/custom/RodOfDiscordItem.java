package net.fawnoculus.ntm.items.custom;

import net.fawnoculus.ntm.util.NtmTextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class RodOfDiscordItem extends Item {
    public RodOfDiscordItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        Vec3 start = player.getEyePosition();
        Vec3 end = start.add(player.getLookAngle().multiply(100, 100, 100));

        BlockHitResult blockHitResult = level.clipIncludingBorder(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, CollisionContext.emptyWithFluidCollisions()));

        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return super.use(level, player, hand);
        }

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        Vec3 targetPos = blockHitResult.getBlockPos().getCenter();

        switch (blockHitResult.getDirection()) {
            case DOWN -> targetPos = targetPos.add(0, -1, 0);
            case UP -> targetPos = targetPos.add(0, 1, 0);
            case NORTH -> targetPos = targetPos.add(0, 0, -1);
            case SOUTH -> targetPos = targetPos.add(0, 0, 1);
            case WEST -> targetPos = targetPos.add(-1, 0, 0);
            case EAST -> targetPos = targetPos.add(1, 0, 0);
        }

        for (int i = 0; i < 32; i++) {
            serverLevel.sendParticles(
              ParticleTypes.PORTAL,
              player.getX(),
              player.getY() + (player.getRandom().nextDouble() * player.getBbHeight()),
              player.getZ(),
              1,
              player.getRandom().nextGaussian(),
              0,
              player.getRandom().nextGaussian(),
              0
            );
        }

        serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);

        player.stopRiding();
        player.getPassengers().forEach(Entity::stopRiding);
        player.teleportTo(targetPos.x(), targetPos.y(), targetPos.z());
        player.fallDistance = 0;

        serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        tooltip.accept(NtmTextUtil.tooltip(this, 1).withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC));
        tooltip.accept(NtmTextUtil.tooltip(this, 2).withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC));
        tooltip.accept(CommonComponents.EMPTY);
        tooltip.accept(NtmTextUtil.tooltip(this, 3).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
        tooltip.accept(NtmTextUtil.tooltip(this, 4).withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
        tooltip.accept(CommonComponents.EMPTY);
        tooltip.accept(NtmTextUtil.tooltip(this, 5).withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC));
        tooltip.accept(NtmTextUtil.tooltip(this, 6).withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC));
    }
}
