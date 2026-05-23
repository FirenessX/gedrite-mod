package com.gedrite.mixins;

import com.gedrite.event.ModEventBus;
import com.gedrite.fluids.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {
    @Inject(method = "spreadTo", at = @At(value = "HEAD"), cancellable = true)
    private void gedrite$spreadTo(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){
        Fluid fluid = (Fluid) (Object) this;
        FluidState fluidstate1 = level.getFluidState(pos);
        Fluid fluid1 = fluidstate1.getType();
        if (direction == Direction.DOWN) {
            if (fluid.is(FluidTags.WATER) && fluid1.isSame(ModFluids.FLOWING_GEDRITED_WATER.get())) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, ModEventBus.gedriteFluidPlaceBlockEvent(level, pos, pos, Blocks.COARSE_DIRT.defaultBlockState()), 3);
                }
                playSplashSound(level, pos);
                ci.cancel();
            }
        }
    }
    private void playSplashSound(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
