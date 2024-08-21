package com.gedrite.mixins;

import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
    @Inject(method = "spreadTo", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$flow(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){
        if (direction == Direction.DOWN) {
            FluidState fluidState2 = level.getFluidState(pos);
            if (fluidState.is(FluidTags.LAVA) && fluidState2.is(FluidTags.WATER)) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(level, pos, pos, Blocks.STONE.defaultBlockState()), 3);
                }
                gedrite$fizz(level, pos);
                ci.cancel();
            }
            if (fluidState.is(FluidTags.LAVA) && fluidState2.is(ModTags.Fluids.GEDRITED_WATER)) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(level, pos, pos, Blocks.SOUL_SOIL.defaultBlockState()), 3);
                }
                gedrite$fizz(level, pos);
                ci.cancel();
            }
        }
    }
    @Unique
    private static void gedrite$fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    @Inject(method = "canBeReplacedWith", at = @At(value = "HEAD"), cancellable = true)
    public void gedrite$canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection, CallbackInfoReturnable<Boolean> cir) {
         cir.setReturnValue(pFluidState.getHeight(pBlockReader, pPos) >= 0.44444445F && pFluid.is(FluidTags.WATER) && pFluid.is(ModTags.Fluids.GEDRITED_WATER));
    }
}
