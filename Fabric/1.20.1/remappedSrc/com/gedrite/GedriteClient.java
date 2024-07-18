package com.gedrite;

import com.gedrite.client.particle.ModBlockLeakParticle;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class GedriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DRIPPING_GEDRITED_WATER, spriteProvider ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModBlockLeakParticle.createDrippingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FALLING_GEDRITED_WATER, spriteProvider ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModBlockLeakParticle.createFallingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.LANDING_GEDRITED_WATER, spriteProvider ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModBlockLeakParticle.createLandingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider));

        //        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DRIPPING_DRIPSTONE_GEDRITED_WATER, ModBlockLeakParticle::createDrippingDripstoneGedritedWater);
//        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FALLING_DRIPSTONE_GEDRITED_WATER, ModBlockLeakParticle::createFallingDripstoneGedritedWater);
    }
}