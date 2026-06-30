package com.gedrite;

import com.gedrite.client.particle.ModBlockLeakParticle;
import com.gedrite.client.render.entity.GedriteArrowRenderer;
import com.gedrite.entity.ModEntities;
import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

@Environment(EnvType.CLIENT)
public class GedriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderingRegistry.register(ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER,
                new FluidModel.Unbaked(
                        new Material(Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "block/gedrited_water_still")),
                                new Material(Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "block/gedrited_water_flow")),
                                        new Material(Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "block/gedrited_water_overlay")),
                        BlockTintSources.constant(ARGB.opaque(0xf722d7))
                ));

        EntityRenderers.register(ModEntities.GEDRITE_ARROW, GedriteArrowRenderer::new);

        ParticleProviderRegistry.getInstance().register(ModParticleTypes.DECAY_PARTICLE, SpellParticle.Provider::new);

        ParticleProviderRegistry.getInstance().register(ModParticleTypes.DRIPPING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createDrippingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.get(random)));

        ParticleProviderRegistry.getInstance().register(ModParticleTypes.FALLING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createFallingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.get(random)));

        ParticleProviderRegistry.getInstance().register(ModParticleTypes.LANDING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createLandingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.get(random)));
    }
}