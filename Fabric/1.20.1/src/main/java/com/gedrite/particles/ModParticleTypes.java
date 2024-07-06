package com.gedrite.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticleTypes {
    public static final DefaultParticleType LANDING_GEDRITED_WATER = FabricParticleTypes.simple();
    public static final DefaultParticleType DRIPPING_GEDRITED_WATER = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final DefaultParticleType DRIPPING_DRIPSTONE_GEDRITED_WATER = FabricParticleTypes.simple();
//    public static final DefaultParticleType FALLING_DRIPSTONE_GEDRITED_WATER = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("gedrite", "landing_gedrited_water"), LANDING_GEDRITED_WATER);
        System.out.println("Registered particle: splash_gedrited_water");

        Registry.register(Registries.PARTICLE_TYPE, new Identifier("gedrite", "dripping_gedrited_water"), DRIPPING_GEDRITED_WATER);
        System.out.println("Registered particle: dripping_gedrited_water");

        Registry.register(Registries.PARTICLE_TYPE, new Identifier("gedrite", "falling_gedrited_water"), FALLING_GEDRITED_WATER);
        System.out.println("Registered particle: falling_gedrited_water");

//        Registry.register(Registries.PARTICLE_TYPE, new Identifier("gedrite", "dripping_dripstone_gedrited_water"), DRIPPING_DRIPSTONE_GEDRITED_WATER);
//        System.out.println("Registered particle: dripping_dripstone_gedrited_water");
//
//        Registry.register(Registries.PARTICLE_TYPE, new Identifier("gedrite", "falling_dripstone_gedrited_water"), FALLING_DRIPSTONE_GEDRITED_WATER);
//        System.out.println("Registered particle: falling_dripstone_gedrited_water");
    }
}
