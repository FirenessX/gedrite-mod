package com.gedrite.effects;

import com.gedrite.Gedrite;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect DECAY;

    public static StatusEffect registerStatusEffect(String id) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Gedrite.MOD_ID, id),
                new DecayEffect(StatusEffectCategory.HARMFUL, 0xf722d7));
    }

    public static void registerEffects() {
        DECAY = registerStatusEffect("decay");
    }
}
