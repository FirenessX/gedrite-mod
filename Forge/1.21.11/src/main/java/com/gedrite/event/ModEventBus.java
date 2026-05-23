package com.gedrite.event;

import com.gedrite.client.particle.ModDripParticle;
import com.gedrite.core.particles.ModParticles;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

public class ModEventBus {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.DECAY_PARTICLE.get(), SpellParticle.Provider::new);

        event.registerSpriteSet(
                ModParticles.DRIPPING_GEDRITED_WATER.get(),
                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
                    TextureAtlasSprite atlas = spriteSet.get(world.random);
                    return ModDripParticle.createGedritedWaterHangParticle(
                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet);
                }
        );

        event.registerSpriteSet(
                ModParticles.FALLING_GEDRITED_WATER.get(),
                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
                    TextureAtlasSprite atlas = spriteSet.get(world.random);
                    return ModDripParticle.createGedritedWaterFallParticle(
                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet
                    );
                }
        );

        event.registerSpriteSet(
                ModParticles.LANDING_GEDRITED_WATER.get(),
                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
                    TextureAtlasSprite atlas = spriteSet.get(world.random);
                    return ModDripParticle.createGedritedWaterLandParticle(
                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet
                    );
                }
        );
//        ((ParticleResourcesAccessor) Minecraft.getInstance()).gedrite$getParticleResources().register(
//                ModParticles.DRIPPING_GEDRITED_WATER.get(),
//                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
//                    TextureAtlasSprite atlas = spriteSet.get(world.random);
//                    return ModDripParticle.createGedritedWaterHangParticle(
//                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet);
//                }
//        );
//        ((ParticleResourcesAccessor) Minecraft.getInstance()).gedrite$getParticleResources().register(
//                ModParticles.FALLING_GEDRITED_WATER.get(),
//                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
//                    TextureAtlasSprite atlas = spriteSet.get(world.random);
//                    return ModDripParticle.createGedritedWaterFallParticle(
//                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet);
//                }
//        );
//        ((ParticleResourcesAccessor) Minecraft.getInstance()).gedrite$getParticleResources().register(
//                ModParticles.LANDING_GEDRITED_WATER.get(),
//                spriteSet -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ, randomSource) -> {
//                    TextureAtlasSprite atlas = spriteSet.get(world.random);
//                    return ModDripParticle.createGedritedWaterLandParticle(
//                            parameters, world, x, y, z, velocityX, velocityY, velocityZ, atlas, spriteSet);
//                }
//        );
//        }
    }
    public static BlockState gedriteFluidPlaceBlockEvent(LevelAccessor level, BlockPos pos, BlockPos liquidPos, BlockState state) {
        return new BlockEvent.FluidPlaceBlockEvent(level, pos, liquidPos, state).getNewState();
    }
}