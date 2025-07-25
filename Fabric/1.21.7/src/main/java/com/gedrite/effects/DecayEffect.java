package com.gedrite.effects;

import com.gedrite.entity.damage.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.Random;

public class DecayEffect extends StatusEffect {
    private final Random random = new Random();

    protected DecayEffect(StatusEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        if (!entity.getWorld().isClient) {
            if(random.nextFloat() < 0.2f) {
                entity.serverDamage(ModDamageTypes.of(world, DamageTypes.GENERIC), (float) (random.nextInt(4) + 1));
            }
        }
        super.applyUpdateEffect(serverWorld, entity, amplifier);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
