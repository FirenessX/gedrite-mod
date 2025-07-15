package com.gedrite;

import com.gedrite.client.particle.ModBlockLeakParticle;
import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GedriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER,
                new SimpleFluidRenderHandler(
                        Identifier.of("gedrite:block/gedrited_water_still"),
                        Identifier.of("gedrite:block/gedrited_water_flow"),
                        0xf722d7
                ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER);


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