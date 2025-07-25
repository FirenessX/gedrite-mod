package com.gedrite.event;

import com.gedrite.Gedrite;
import com.gedrite.client.particle.ModDripParticle;
import com.gedrite.core.particles.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gedrite.MOD_ID)
public class ModEventBus {
    public static BlockState gedriteFluidPlaceBlockEvent(LevelAccessor level, BlockPos pos, BlockPos liquidPos, BlockState state) {
        return new BlockEvent.FluidPlaceBlockEvent(level, pos, liquidPos, state).getNewState();
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.DRIPPING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterHangParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
        Minecraft.getInstance().particleEngine.register(ModParticles.FALLING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterFallParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
        Minecraft.getInstance().particleEngine.register(ModParticles.LANDING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterLandParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
    }
}