package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import com.gedrite.world.level.material.GedritedWaterFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
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
            DeferredRegister.create(Registries.FLUID, Gedrite.MOD_ID);

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_GEDRITED_WATER = FLUIDS.register("gedrited_water_still",
            (properties) -> new GedritedWaterFluid.Source());

    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_GEDRITED_WATER = FLUIDS.register("gedrited_water_flow",
            (properties) -> new GedritedWaterFluid.Flowing());

    public static boolean isGedritedWater(FluidState state) {
        return state.is(ModFluids.SOURCE_GEDRITED_WATER.get()) || state.is(ModFluids.FLOWING_GEDRITED_WATER.get());
    }

    public static boolean isTouchingGedritedWater(LivingEntity entity) {
        return entity.updateFluidHeightAndDoFluidPushing(ModTags.Fluids.GEDRITED_WATER, 0.014);
    }

    public static void decayEffect(LivingEntity entity) {
        if (!entity.hasEffect(ModEffects.DECAY.getDelegate())) {
            entity.addEffect(new MobEffectInstance(ModEffects.DECAY.getDelegate(), 60, 0, false, true, true));
        }
    }

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
