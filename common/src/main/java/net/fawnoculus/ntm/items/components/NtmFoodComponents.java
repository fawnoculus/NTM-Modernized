package net.fawnoculus.ntm.items.components;

import net.minecraft.world.food.FoodProperties;

public class NtmFoodComponents {
    // Saturation = nutrition * saturationModifier * 2, I don't know why it is like that
    public static final FoodProperties ALWAYS_EDIBLE = new FoodProperties.Builder().nutrition(0).saturationModifier(0.0f).alwaysEdible().build();

    public static final FoodProperties LEAD_APPLE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.0f).alwaysEdible().build();
    public static final FoodProperties SCHRABIDIUM_APPLE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.5f).alwaysEdible().build();

    public static final FoodProperties WAFFLE_OF_MASS_DESTRUCTION = new FoodProperties.Builder().nutrition(20).saturationModifier(1.0f).build();

    public static final FoodProperties RADIOACTIVE_COTTON_CANDY = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build();
    public static final FoodProperties GLOWING_MUSHROOM_STEW = new FoodProperties.Builder().nutrition(6).saturationModifier(0.59f).build();
    public static final FoodProperties LEMON = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodProperties MRE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodProperties LOOPS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.25f).build();
    public static final FoodProperties IT_BREAKFAST = new FoodProperties.Builder().nutrition(10).saturationModifier(0.5f).build();
    public static final FoodProperties SPONGEBOB_MACARONI = new FoodProperties.Builder().nutrition(5).saturationModifier(1f).build();
    public static final FoodProperties FOOD_ITEM = new FoodProperties.Builder().nutrition(2).saturationModifier(5f).build();
    public static final FoodProperties TWINKIE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.17f).build();
    public static final FoodProperties TV_STATIC_SANDWICH = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();
    public static final FoodProperties PUDDING = new FoodProperties.Builder().nutrition(6).saturationModifier(1f).build();
    public static final FoodProperties SCRAP_PANCAKE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.5f).build();
    public static final FoodProperties CHICKEN_NUGGET = new FoodProperties.Builder().nutrition(Integer.MAX_VALUE).saturationModifier(0.5f).build();
    public static final FoodProperties MARSHMALLOW_ON_A_STICK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).build();
    public static final FoodProperties ROASTED_MARSHMALLOW_ON_A_STICK = new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.7f).build();
    public static final FoodProperties CHEESE_QUESADILLA = new FoodProperties.Builder().nutrition(8).saturationModifier(1f).build();
    public static final FoodProperties GLYPHID_MEAT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodProperties GRILLED_GLYPHID_MEAT = new FoodProperties.Builder().nutrition(8).saturationModifier(0.75f).build();
    public static final FoodProperties ARIZONA_MUCHO_MANGO = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties RADIUM_CHOCOLATE = new FoodProperties.Builder().nutrition(0).saturationModifier(0).build();
}
