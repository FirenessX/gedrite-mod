package com.gedrite.effects;

import com.gedrite.entity.damage.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;

import java.util.Random;

public class DecayEffect extends StatusEffect {
    private final Random random = new Random();

    protected DecayEffect(StatusEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getEntityWorld();
        if (!entity.getWorld().isClient) {
            if(random.nextFloat() < 0.2f) {
                System.out.println("decay");
                entity.damage(ModDamageTypes.of(world, ModDamageTypes.DECAY), (float) (random.nextInt(4) + 1));
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
