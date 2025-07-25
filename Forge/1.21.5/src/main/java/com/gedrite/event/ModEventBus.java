package com.gedrite.event;

import com.gedrite.Gedrite;
import com.gedrite.client.particle.ModDripParticle;
import com.gedrite.core.particles.ModParticles;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gedrite.MOD_ID)
public class ModEventBus {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.DRIPPING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterHangParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
        Minecraft.getInstance().particleEngine.register(ModParticles.FALLING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterFallParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
        Minecraft.getInstance().particleEngine.register(ModParticles.LANDING_GEDRITED_WATER.get(), spriteSet ->
                (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> ModDripParticle.createGedritedWaterLandParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteSet));
    }

    public static BlockState gedriteFluidPlaceBlockEvent(LevelAccessor level, BlockPos pos, BlockPos liquidPos, BlockState state) {
        return new BlockEvent.FluidPlaceBlockEvent(level, pos, liquidPos, state).getNewState();
    }
}