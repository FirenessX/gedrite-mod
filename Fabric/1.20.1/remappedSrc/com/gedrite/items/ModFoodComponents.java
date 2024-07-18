package com.gedrite.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent GEDRITE_INGOT = new FoodComponent.Builder().hunger(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100), 1f).build();
}
