package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.world.effects.ModEffects;
import com.gedrite.world.level.material.GedritedWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, Gedrite.MOD_ID);

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_GEDRITED_WATER = FLUIDS.register("gedrited_water_still",
            (properties) -> new GedritedWaterFluid.Source());

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_GEDRITED_WATER = FLUIDS.register("gedrited_water_flow",
            (properties) -> new GedritedWaterFluid.Flowing());

    public static boolean isGedritedWater(FluidState state) {
        return state.is(ModFluids.SOURCE_GEDRITED_WATER.get()) || state.is(ModFluids.FLOWING_GEDRITED_WATER.get());
    }

    public static boolean isBoundingBoxInFluid(Entity entity, TagKey<Fluid> fluidTag) {
        AABB boundingBox = entity.getBoundingBox();
        int minX = Mth.floor(boundingBox.minX);
        int minY = Mth.floor(boundingBox.minY);
        int minZ = Mth.floor(boundingBox.minZ);
        int maxX = Mth.ceil(boundingBox.maxX);
        int maxY = Mth.ceil(boundingBox.maxY);
        int maxZ = Mth.ceil(boundingBox.maxZ);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    FluidState fluidState = entity.level().getFluidState(pos);
                    if (fluidState.is(fluidTag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void decayEffect(LivingEntity entity) {
        if (!entity.hasEffect(ModEffects.DECAY.getDelegate())) {
            entity.addEffect(new MobEffectInstance(ModEffects.DECAY.getDelegate(), 60, 0, false, true, true));
        }
    }

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    // Перенесенные методы из FluidInteractionRegistryMixin
    public static void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    public static void pssh(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}