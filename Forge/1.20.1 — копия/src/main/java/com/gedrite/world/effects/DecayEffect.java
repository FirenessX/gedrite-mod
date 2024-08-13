package com.gedrite.world.effects;

import com.gedrite.entity.damage.ModDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Random;

public class DecayEffect extends MobEffect {
    private final Random random = new Random();

    protected DecayEffect(MobEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level world = entity.getCommandSenderWorld();
        if (!entity.getCommandSenderWorld().isClientSide()) {
            if(random.nextFloat() < 0.2f) {
                System.out.println("decay");
                entity.hurt(ModDamageTypes.of(world, ModDamageTypes.DECAY), (float) (random.nextInt(4) + 1));
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}

