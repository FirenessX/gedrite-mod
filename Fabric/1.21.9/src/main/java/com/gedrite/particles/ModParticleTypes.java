package com.gedrite.particles;

import com.gedrite.Gedrite;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticleTypes {
//    public static final SimpleParticleType LANDING_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final SimpleParticleType DRIPPING_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final SimpleParticleType FALLING_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final SimpleParticleType DRIPPING_DRIPSTONE_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final SimpleParticleType FALLING_DRIPSTONE_GEDRITED_WATER = FabricParticleTypes.simple();
    public static final SimpleParticleType FALLING_GEDRITED_WATER = registerParticle("falling_gedrited_water", FabricParticleTypes.simple());
    public static final SimpleParticleType LANDING_GEDRITED_WATER = registerParticle("landing_gedrited_water", FabricParticleTypes.simple());
    public static final SimpleParticleType DRIPPING_GEDRITED_WATER = registerParticle("dripping_gedrited_water", FabricParticleTypes.simple());

    public static final SimpleParticleType DECAY_PARTICLE = registerParticle("decay_particle", FabricParticleTypes.simple());

    public static SimpleParticleType registerParticle(String id, SimpleParticleType particle) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Gedrite.MOD_ID, id), particle);
    }

    public static void registerModParticles() {
        Gedrite.LOGGER.debug("Registering particles for: " + Gedrite.MOD_ID);
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gedrite", "landing_gedrited_water"), LANDING_GEDRITED_WATER);
//        System.out.println("Registered particle: splash_gedrited_water");

//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gedrite", "dripping_gedrited_water"), DRIPPING_GEDRITED_WATER);
//        System.out.println("Registered particle: dripping_gedrited_water");

//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gedrite", "falling_gedrited_water"), FALLING_GEDRITED_WATER);
//        System.out.println("Registered particle: falling_gedrited_water");

//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gedrite", "dripping_dripstone_gedrited_water"), DRIPPING_DRIPSTONE_GEDRITED_WATER);
//        System.out.println("Registered particle: dripping_dripstone_gedrited_water");
//
//        Registry.register(Registries.PARTICLE_TYPE, Identifier.of("gedrite", "falling_dripstone_gedrited_water"), FALLING_DRIPSTONE_GEDRITED_WATER);
//        System.out.println("Registered particle: falling_dripstone_gedrited_water");
    }
}
