package net.fawnoculus.ntm.items.custom.consumable;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.items.NtmItems;
import net.fawnoculus.ntm.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Consumer;

public class BottleItem extends Item {
    public final List<MobEffectInstance> EFFECTS;
    public final List<RegistrySupplier<Item>> RETURN_ITEMS;

    public BottleItem(Properties settings, List<MobEffectInstance> effects, List<RegistrySupplier<Item>> returnItems) {
        super(settings.component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK));
        this.EFFECTS = effects;
        this.RETURN_ITEMS = returnItems;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, Consumer<Component> tooltip, net.minecraft.world.item.@NonNull TooltipFlag type) {
        tooltip.accept(Component.translatable("tooltip." + this.getDescriptionId().substring(5)).withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.ntm.needs_bottle_opener").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, @NonNull Player playerEntity, @NonNull InteractionHand hand) {
        if (!PlayerUtil.hasItem(playerEntity, NtmItems.BOTTLE_OPENER)) {
            return InteractionResult.FAIL;
        }
        return super.use(world, playerEntity, hand);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, Level world, @NonNull LivingEntity entity) {
        if (!world.isClientSide() && entity instanceof Player player) {
            for (MobEffectInstance effect : this.EFFECTS) {
                player.addEffect(new MobEffectInstance(effect));
            }
            for (RegistrySupplier<Item> returnItem : this.RETURN_ITEMS) {
                player.getInventory().placeItemBackInInventory(new ItemStack(returnItem));
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }
}
