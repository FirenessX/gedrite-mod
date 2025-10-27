package com.gedrite.items;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodComponent GEDRITE_INGOT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.25f).build();

    public static final ConsumableComponent GEDRITE_INGOT_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.POISON, 100), 1f)).build();
}