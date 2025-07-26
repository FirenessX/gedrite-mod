package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.util.ModTags;
import com.gedrite.world.level.material.GedritedWaterFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, Gedrite.MODID);

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_GEDRITED_WATER = FLUIDS.register("gedrited_water_still",
            (properties) -> new GedritedWaterFluid.Source());

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_GEDRITED_WATER = FLUIDS.register("gedrited_water_flow",
            (properties) -> new GedritedWaterFluid.Flowing());
//
//    public static final DeferredRegister<FlowingFluid> SOURCE_TEST = FLUIDS.register("test_still",
//            () -> new FlowingFluid.Source(ModFluids.TEST_PROPERTIES));
//
//    public static final DeferredRegister<FlowingFluid> FLOWING_TEST = FLUIDS.register("test_flow",
//            () -> new ForgeFlowingFluid.Flowing(ModFluids.TEST_PROPERTIES));


//    public static final ForgeFlowingFluid.Properties GEDRITED_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get(), ModFluids.SOURCE_GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER)
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

    public static boolean isTouchingGedritedWater(LivingEntity entity) {
        return entity.updateFluidHeightAndDoFluidPushing(ModTags.Fluids.GEDRITED_WATER, 0.014);
    }

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
