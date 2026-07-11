package com.gedrite.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodProperties GEDRITE_INGOT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.25f).build();

    public static final Consumable GEDRITE_INGOT_EFFECT = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 100), 1f)).build();
}