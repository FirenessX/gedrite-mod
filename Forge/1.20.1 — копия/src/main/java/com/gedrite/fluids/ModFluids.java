package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
//import com.gedrite.world.level.material.GedritedWaterFluid;
import com.gedrite.world.level.material.GedritedWaterFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Gedrite.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GEDRITED_WATER = FLUIDS.register("gedrited_water_still",
            () -> new GedritedWaterFluid.Source());

    public static final RegistryObject<FlowingFluid> FLOWING_GEDRITED_WATER = FLUIDS.register("gedrited_water_flow",
            () -> new GedritedWaterFluid.Flowing());

    public static final RegistryObject<FlowingFluid> SOURCE_TEST = FLUIDS.register("test_still",
            () -> new ForgeFlowingFluid.Source(ModFluids.TEST_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_TEST = FLUIDS.register("test_flow",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.TEST_PROPERTIES));


//    public static final ForgeFlowingFluid.Properties GEDRITED_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE, ModFluids.SOURCE_GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER)
//            .levelDecreasePerBlock(2)
//            .block(ModBlocks.GEDRITED_WATER_BLOCK)
//            .bucket(ModItems.GEDRITED_WATER_BUCKET)
//            .tickRate(20)
//            .explosionResistance(100F);

    public static final ForgeFlowingFluid.Properties TEST_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.TEST_FLUID_TYPE, ModFluids.SOURCE_TEST, ModFluids.FLOWING_TEST)
            .levelDecreasePerBlock(2)
            .block(ModBlocks.TEST_FLUID_BLOCK)
            .tickRate(20)
            .explosionResistance(100F);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
