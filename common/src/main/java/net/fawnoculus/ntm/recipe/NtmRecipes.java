package net.fawnoculus.ntm.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.misc.NtmDeferredRegistries;
import net.fawnoculus.ntm.recipe.custom.AlloyFurnaceRecipe;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class NtmRecipes {
    public static final RegistrySupplier<AlloyFurnaceRecipe.Serializer> ALLOY_FURNACE_RECIPE_SERIALIZER = register("alloy_furnace", AlloyFurnaceRecipe.Serializer::new);
    public static final RegistrySupplier<RecipeType<AlloyFurnaceRecipe>> ALLOY_FURNACE_RECIPE_TYPE = register("alloy_furnace");


    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> register(String name) {
        Identifier identifier = Ntm.id(name);
        return NtmDeferredRegistries.RECIPE_TYPES.register(identifier, () -> new RecipeType<>() {
            public String toString() {
                return identifier.toString();
            }
        });
    }

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> RegistrySupplier<S> register(String name, Supplier<S> serializer) {
        return NtmDeferredRegistries.RECIPE_SERIALIZERS.register(Ntm.id(name), serializer);
    }

    public static void init() {
    }
}
