package com.gedrite.effects;

import com.gedrite.entity.damage.ModDamageTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.NonNull;

import java.util.Random;

public class DecayEffect extends MobEffect {
    private final Random random = new Random();

    protected DecayEffect(MobEffectCategory category, int color, SimpleParticleType particle) {
        super(category, color, particle);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NonNull ServerLevel serverLevel, LivingEntity entity, int amplifier) {
        DamageSource damageSource = new DamageSource(
                serverLevel.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .get(ModDamageTypes.DECAY.identifier()).orElseThrow());

        if (!entity.level().isClientSide()) {
            if(random.nextFloat() < 0.2f) {
                entity.hurtServer(serverLevel, damageSource, (float) (random.nextInt(4) + 1));
            }
        }
        super.applyEffectTick(serverLevel, entity, amplifier);
        return true;
    }
}
