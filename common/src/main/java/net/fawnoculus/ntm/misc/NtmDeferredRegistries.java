package net.fawnoculus.ntm.misc;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import net.fawnoculus.ntm.Ntm;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class NtmDeferredRegistries {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Ntm.MOD_ID, Registries.SOUND_EVENT);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Ntm.MOD_ID, Registries.MOB_EFFECT);
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Ntm.MOD_ID, Registries.DATA_COMPONENT_TYPE);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Ntm.MOD_ID, Registries.FLUID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Ntm.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Ntm.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Ntm.MOD_ID, Registries.RECIPE_SERIALIZER);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Ntm.MOD_ID, Registries.RECIPE_TYPE);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Ntm.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Ntm.MOD_ID, Registries.PARTICLE_TYPE);
    public static final DeferredRegister<CreativeModeTab> ITEM_TABS = DeferredRegister.create(Ntm.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Ntm.MOD_ID, Registries.MENU);
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECT_TYPES = DeferredRegister.create(Ntm.MOD_ID, Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE);

    public static void init() {
        SOUNDS.register();
        MOB_EFFECTS.register();
        DATA_COMPONENTS.register();
        FLUIDS.register(); // Fluid need to be registered before Blocks on fabric
        BLOCKS.register(); // Blocks need to be registered before Items because of block-items
        ITEMS.register();
        RECIPE_SERIALIZERS.register();
        RECIPE_TYPES.register();
        BLOCK_ENTITIES.register();
        PARTICLES.register();
        ITEM_TABS.register();
        MENU_TYPES.register();
        ENCHANTMENT_ENTITY_EFFECT_TYPES.register();
    }
}
