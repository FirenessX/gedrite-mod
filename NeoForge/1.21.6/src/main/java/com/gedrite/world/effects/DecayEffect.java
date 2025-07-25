package com.gedrite.world.effects;

import com.gedrite.entity.damage.ModDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DecayEffect extends MobEffect {
    private final Random random = new Random();

    protected DecayEffect(MobEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull ServerLevel level, LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            if(random.nextFloat() < 0.2f) {
//                System.out.println("decay");
                entity.hurt(ModDamageTypes.of(level, DamageTypes.GENERIC), (float) (random.nextInt(4) + 1));
            }
        }
        super.applyEffectTick(level, entity, amplifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}

