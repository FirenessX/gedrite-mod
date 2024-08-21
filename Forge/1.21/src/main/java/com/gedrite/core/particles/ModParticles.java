package com.gedrite.core.particles;

import com.gedrite.Gedrite;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Gedrite.MOD_ID);

    public static final RegistryObject<SimpleParticleType> DRIPPING_GEDRITED_WATER =
            PARICLE_TYPES.register("dripping_gedrited_water", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FALLING_GEDRITED_WATER =
            PARICLE_TYPES.register("falling_gedrited_water", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> LANDING_GEDRITED_WATER =
            PARICLE_TYPES.register("landing_gedrited_water", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARICLE_TYPES.register(eventBus);
    }
}
