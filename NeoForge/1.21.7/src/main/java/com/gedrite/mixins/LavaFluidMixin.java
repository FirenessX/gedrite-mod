package com.gedrite.mixins;

import com.gedrite.fluids.ModFluids;
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
import net.neoforged.neoforge.event.EventHooks;
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
        Fluid fluid = (Fluid) (Object) this;
        FluidState fluidstate1 = level.getFluidState(pos);
        Fluid fluid1 = fluidstate1.getType();
        if (direction == Direction.DOWN) {
//            System.out.println("ready");
//            if (fluid.is(FluidTags.LAVA)){
//                System.out.println("is");
//            }
//            if (fluid.isSame(Fluids.FLOWING_LAVA) || fluid.isSame(Fluids.LAVA)) {
//                System.out.println("isSame");
//            }
//            if (fluidstate1.is(ModTags.Fluids.GEDRITED_WATER)) {
//                System.out.println("fluidstate GW");
//                System.out.println(fluid1);
//            } else {
//                System.out.println("--------------");
//                System.out.println(fluidState);
//                System.out.println(fluidstate1);
//                System.out.println(fluid1);
//            }
            if (fluid.is(FluidTags.LAVA) && fluid1.isSame(ModFluids.FLOWING_GEDRITED_WATER.get())) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, EventHooks.fireFluidPlaceBlockEvent(level, pos, pos, Blocks.SOUL_SOIL.defaultBlockState()), 3);
//                    System.out.println("soil");
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
