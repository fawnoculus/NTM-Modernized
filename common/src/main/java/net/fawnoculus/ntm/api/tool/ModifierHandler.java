package net.fawnoculus.ntm.api.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public record ModifierHandler(List<Tuple<ItemModifier, @NotNull Integer>> MODIFIERS) {
    public static Builder builder() {
        return new Builder();
    }

    public void appendTooltip(Consumer<Component> tooltip) {
        if (!MODIFIERS.isEmpty()) {
            tooltip.accept(Component.translatable("tooltip.ntm.modifier.start").withStyle(ChatFormatting.GRAY));
            for (Tuple<ItemModifier, @NotNull Integer> pair : MODIFIERS) {
                tooltip.accept(Component.literal("  ").append(pair.getA().getFullName(pair.getB()).withStyle(ChatFormatting.RED)));
            }
        }
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (Tuple<ItemModifier, @NotNull Integer> pair : MODIFIERS) {
            pair.getA().postHit(stack, target, attacker, pair.getB());
        }
    }

    public static class Builder {
        private final List<Tuple<ItemModifier, @NotNull Integer>> modifiers = new ArrayList<>();

        /**
         * Use this one if the Modifier doesn't support levels
         *
         * @param modifier The Modifier to add
         */
        public Builder addModifier(ItemModifier modifier) {
            modifiers.add(new Tuple<>(modifier, 0));
            return this;
        }

        /**
         * Use this one if the Modifier supports levels
         *
         * @param modifier The Modifier to add
         * @param level    the level of the modifier
         */
        public Builder addModifier(ItemModifier modifier, int level) {
            modifiers.add(new Tuple<>(modifier, level));
            return this;
        }

        public ModifierHandler build() {
            return new ModifierHandler(modifiers);
        }
    }
}
