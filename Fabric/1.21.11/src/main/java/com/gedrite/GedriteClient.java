package com.gedrite;

import com.gedrite.client.particle.ModBlockLeakParticle;
import com.gedrite.client.render.entity.GedriteArrowEntityRenderer;
import com.gedrite.entity.ModEntities;
import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.client.render.*;
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

        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT, ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER);

        EntityRendererRegistry.register(ModEntities.GEDRITE_ARROW, GedriteArrowEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DECAY_PARTICLE, SpellParticle.DefaultFactory::new);

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DRIPPING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createDrippingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.getSprite(random)));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FALLING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createFallingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.getSprite(random)));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.LANDING_GEDRITED_WATER, sprite ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ, random) -> ModBlockLeakParticle.createLandingGedritedWater(parameters, world, x, y, z, velocityX, velocityY, velocityZ, sprite.getSprite(random)));

//                ParticleFactoryRegistry.getInstance().register(ModParticleTypes.DRIPPING_DRIPSTONE_GEDRITED_WATER, ModBlockLeakParticle::createDrippingDripstoneGedritedWater);
//        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FALLING_DRIPSTONE_GEDRITED_WATER, ModBlockLeakParticle::createFallingDripstoneGedritedWater);
//        HudRenderCallback.EVENT.register((draw, tickDelta) -> {
//            if (MetalDetectorItem.foundPos == null) return;
//
//            MinecraftClient mc = MinecraftClient.getInstance();
//            if (mc.world == null || mc.player == null) return;
//
//            var state = mc.world.getBlockState(MetalDetectorItem.foundPos);
//            BlockStateModel model = mc.getBlockRenderManager().getModel(state);
//            Sprite sprite = model.particleSprite();
//            if (sprite == null) return;
//
//            Vector3f screen = DetectedBlockRender.project(
//                    Vec3d.ofCenter(MetalDetectorItem.foundPos), tickDelta.getDynamicDeltaTicks());
//            if (screen == null) return;
//
//            float distanceScale = MathHelper.clamp(1.0f / (screen.z * 0.1f), 0.5f, 2.0f);
//            int size = (int) (28 * distanceScale);
//            int x = Math.round(screen.x) - size / 2;
//            int y = Math.round(screen.y) - size / 2;
//
//            draw.drawSpriteStretched(RenderPipelines.GUI_TEXTURED, sprite, x, y, size, size);
//        });
    }
}