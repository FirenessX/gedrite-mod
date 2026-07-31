package com.gedrite.fluids;

import com.gedrite.Gedrite;
//import com.gedrite.world.level.material.GedritedWaterFluid;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import com.gedrite.world.level.material.GedritedWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import net.minecraft.world.phys.AABB;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Gedrite.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GEDRITED_WATER = FLUIDS.register("gedrited_water_still",
            GedritedWaterFluid.Source::new);

    public static final RegistryObject<FlowingFluid> FLOWING_GEDRITED_WATER = FLUIDS.register("gedrited_water_flow",
            GedritedWaterFluid.Flowing::new);

//    public static final RegistryObject<FlowingFluid> SOURCE_TEST = FLUIDS.register("test_still",
//            () -> new ForgeFlowingFluid.Source(ModFluids.TEST_PROPERTIES));
//
//    public static final RegistryObject<FlowingFluid> FLOWING_TEST = FLUIDS.register("test_flow",
//            () -> new ForgeFlowingFluid.Flowing(ModFluids.TEST_PROPERTIES));


//    public static final ForgeFlowingFluid.Properties GEDRITED_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE, ModFluids.SOURCE_GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER)
//            .levelDecreasePerBlock(2)
//            .block(ModBlocks.GEDRITED_WATER_BLOCK)
//            .bucket(ModItems.GEDRITED_WATER_BUCKET)
//            .tickRate(20)
//            .explosionResistance(100F);

//    public static final ForgeFlowingFluid.Properties TEST_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.TEST_FLUID_TYPE, ModFluids.SOURCE_TEST, ModFluids.FLOWING_TEST)
//            .levelDecreasePerBlock(2)
//            .block(ModBlocks.TEST_FLUID_BLOCK)
//            .tickRate(20)
//            .explosionResistance(100F)
//            ;

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
        if (!entity.hasEffect(ModEffects.DECAY.getHolder().get())) {
            entity.addEffect(new MobEffectInstance(ModEffects.DECAY.getHolder().get(), 60, 0, false, false, true));
        }
    }

    public static void register(BusGroup busGroup) {
        FLUIDS.register(busGroup);
    }
}
