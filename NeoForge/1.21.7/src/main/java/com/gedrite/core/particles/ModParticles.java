package com.gedrite.core.particles;

import com.gedrite.Gedrite;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Gedrite.MODID);

    public static final Supplier<SimpleParticleType> DRIPPING_GEDRITED_WATER =
            PARTICLE_TYPES.register("dripping_gedrited_water", (properties) -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> FALLING_GEDRITED_WATER =
            PARTICLE_TYPES.register("falling_gedrited_water", (properties) -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> LANDING_GEDRITED_WATER =
            PARTICLE_TYPES.register("landing_gedrited_water", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
