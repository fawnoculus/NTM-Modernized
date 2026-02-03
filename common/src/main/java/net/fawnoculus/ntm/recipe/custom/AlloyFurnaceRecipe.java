package net.fawnoculus.ntm.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fawnoculus.ntm.recipe.NtmRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record AlloyFurnaceRecipe(Ingredient ingredient1, Ingredient ingredient2,
                                 ItemStack output) implements Recipe<AlloyFurnaceRecipeInput> {

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient1);
        list.add(this.ingredient2);
        return list;
    }

    @Override
    public boolean matches(AlloyFurnaceRecipeInput input, Level world) {
        if (world.isClientSide()) {
            return false;
        }

        return ingredient1.test(input.getItem(1)) && ingredient2.test(input.getItem(0))
          || ingredient1.test(input.getItem(0)) && ingredient2.test(input.getItem(1));
    }

    @Override
    public @NonNull ItemStack assemble(AlloyFurnaceRecipeInput input, HolderLookup.@NonNull Provider registries) {
        return output.copy();
    }

    @Override
    public @NonNull Serializer getSerializer() {
        return NtmRecipes.ALLOY_FURNACE_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NonNull RecipeType<? extends Recipe<AlloyFurnaceRecipeInput>> getType() {
        return NtmRecipes.ALLOY_FURNACE_RECIPE_TYPE.get();
    }

    @Override
    public @NonNull PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(ingredient1, ingredient2));
    }

    @Override
    public @NonNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<AlloyFurnaceRecipe> {
        public static final MapCodec<AlloyFurnaceRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
          Ingredient.CODEC.fieldOf("ingredient1").forGetter(AlloyFurnaceRecipe::ingredient1),
          Ingredient.CODEC.fieldOf("ingredient2").forGetter(AlloyFurnaceRecipe::ingredient2),
          ItemStack.CODEC.fieldOf("result").forGetter(AlloyFurnaceRecipe::output)
        ).apply(inst, AlloyFurnaceRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AlloyFurnaceRecipe> STREAM_CODEC =
          StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, AlloyFurnaceRecipe::ingredient1,
            Ingredient.CONTENTS_STREAM_CODEC, AlloyFurnaceRecipe::ingredient2,
            ItemStack.STREAM_CODEC, AlloyFurnaceRecipe::output,
            AlloyFurnaceRecipe::new
          );

        @Override
        public @NonNull MapCodec<AlloyFurnaceRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NonNull StreamCodec<RegistryFriendlyByteBuf, AlloyFurnaceRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
