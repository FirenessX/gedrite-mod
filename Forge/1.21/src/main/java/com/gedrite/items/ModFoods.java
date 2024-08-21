package com.gedrite.items;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties GEDRITE_INGOT = (new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).effect(new MobEffectInstance(MobEffects.POISON, 100, 1), 1.0F)).build();
}
