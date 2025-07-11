package com.gedrite.effects;

import com.gedrite.Gedrite;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static RegistryEntry<StatusEffect> DECAY;

    public static RegistryEntry<StatusEffect> registerStatusEffect(String id) {
        return register(String.valueOf(Identifier.of(Gedrite.MOD_ID, id)),
                new DecayEffect(StatusEffectCategory.HARMFUL, 0xf722d7));
    }

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(id), statusEffect);
    }

    public static void registerEffects() {
        DECAY = registerStatusEffect("decay");
    }
}
