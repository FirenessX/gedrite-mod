package com.gedrite.effects;

import com.gedrite.Gedrite;
import com.gedrite.particles.ModParticleTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static Holder<MobEffect> DECAY;

    public static Holder<MobEffect> registerMobEffect(String id) {
        return register(id, new DecayEffect(MobEffectCategory.HARMFUL, 0xf722d7, ModParticleTypes.DECAY_PARTICLE));
    }

    private static Holder<MobEffect> register(String id, MobEffect MobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, id), MobEffect);
    }

    public static void registerEffects() {
        DECAY = registerMobEffect("decay");
    }
}