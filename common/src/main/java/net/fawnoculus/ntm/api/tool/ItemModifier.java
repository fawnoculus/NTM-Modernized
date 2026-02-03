package net.fawnoculus.ntm.api.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Optional;

/**
 * An Empty Interface
 * <p>
 * Everything that implements this is an ItemModifier
 * <p>
 * All modifiers can be found in {@link Modifiers}
 */
public abstract class ItemModifier {
    public static final HashMap<Identifier, ItemModifier> ID_TO_MODIFIER = new HashMap<>();
    private final Identifier ID;

    public ItemModifier(Identifier id) {
        this.ID = id;

        ID_TO_MODIFIER.put(id, this);
    }

    public static Optional<ItemModifier> get(Identifier id) {
        return Optional.ofNullable(ID_TO_MODIFIER.get(id));
    }

    public Identifier getId() {
        return ID;
    }

    public MutableComponent getFullName(int level) {
        if (level == 0) {
            return getName();
        }
        return getName().append(" ").append(getLevelText(level));
    }

    public MutableComponent getName() {
        return Component.translatable("tooltip." + getId().getNamespace() + ".modifier." + getId().getPath());
    }

    public MutableComponent getLevelText(int level) {
        return Component.literal("(" + level + ")");
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, int level) {
    }
}
