package net.fawnoculus.ntm.items.custom.tools;

import dev.architectury.networking.NetworkManager;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.api.messages.AdvancedMessage;
import net.fawnoculus.ntm.api.tool.AbilityHandler;
import net.fawnoculus.ntm.api.tool.ModifierHandler;
import net.fawnoculus.ntm.api.tool.SpecialTool;
import net.fawnoculus.ntm.network.s2c.AdvancedMessagePayload;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;


public class SpecialAxeItem extends AxeItem implements SpecialTool {
    private final AbilityHandler ABILITIES;
    private final ModifierHandler MODIFIERS;
    private final boolean CAN_BREAK_DEPTH_ROCK;

    public SpecialAxeItem(Properties settings, ToolMaterial material, float attackDamage, float attackSpeed, AbilityHandler abilities, ModifierHandler modifiers, boolean canBreakDepthRock) {
        super(material, attackDamage, attackSpeed, settings);

        this.ABILITIES = abilities;
        this.MODIFIERS = modifiers;
        this.CAN_BREAK_DEPTH_ROCK = canBreakDepthRock;
    }

    @Override
    public AbilityHandler abilityHandler() {
        return this.ABILITIES;
    }

    @Override
    public ModifierHandler modifierHandler() {
        return this.MODIFIERS;
    }

    @Override
    public boolean canBreakDepthRock() {
        return this.CAN_BREAK_DEPTH_ROCK;
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (this.ABILITIES.canNotSwitch(stack)) {
            return super.use(world, player, hand);
        }

        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (player instanceof ServerPlayer serverPlayer) {
            if (player.isShiftKeyDown()) {
                this.ABILITIES.setSelectedPreset(stack, 0);
            } else {
                this.ABILITIES.incrementPresetSelection(stack, 1);
            }

            if (this.ABILITIES.abilitiesDisabled(stack)) {
                Ntm.PROXY.playSoundToPlayer(player, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.25f, 0.75f);
            } else {
                Ntm.PROXY.playSoundToPlayer(player, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.25f, 1.25f);
            }

            NetworkManager.sendToPlayer(serverPlayer, new AdvancedMessagePayload(
              new AdvancedMessage(SpecialTool.ADVANCED_MESSAGE_ID, this.ABILITIES.changeMessage(stack), 1000f)
            ));
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
        this.MODIFIERS.postHit(stack, target, attacker);
        super.hurtEnemy(stack, target, attacker);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> tooltip, @NonNull TooltipFlag type) {
        this.ABILITIES.appendTooltip(tooltip);
        this.MODIFIERS.appendTooltip(tooltip);

        if (this.CAN_BREAK_DEPTH_ROCK) {
            tooltip.accept(Component.empty());
            tooltip.accept(Component.translatable("tooltip.ntm.can_break_depth_rock").withStyle(ChatFormatting.RED));
        }
    }
}
