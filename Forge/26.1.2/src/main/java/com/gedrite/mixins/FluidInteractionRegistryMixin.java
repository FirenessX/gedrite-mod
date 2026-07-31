package com.gedrite.mixins;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FluidInteractionRegistry.class)
public class FluidInteractionRegistryMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"), cancellable = true)
    private static void gedrite$static(CallbackInfo ci) {
        FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get(), fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.SOUL_SAND.defaultBlockState()));

        FluidInteractionRegistry.addInteraction(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get(), new FluidInteractionRegistry.InteractionInformation((level, currentPos, relativePos, currentState) -> level.getFluidState(relativePos).is(Fluids.FLOWING_WATER) || level.getFluidState(relativePos).is(Fluids.WATER), Blocks.COARSE_DIRT.defaultBlockState()));

        FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation((level, currentPos, relativePos, currentState) -> level.getBlockState(relativePos).is(ModBlocks.GEDRITE_BLOCK.get()) && currentState.isSource(), ModFluids.SOURCE_GEDRITED_WATER.get().defaultFluidState().createLegacyBlock()));
    }
}