package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin{
    @Inject(method = "flow", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){

        if (direction == Direction.DOWN) {
//            System.out.println("apErik");
            FluidState fluidState2 = world.getFluidState(pos);
            BlockPos balls = new BlockPos(-234, -59, 419);
            FluidState ballsState = world.getFluidState(balls);
//            System.out.println(fluidState2);
//            System.out.println(ballsState);
//            System.out.println(ballsState.isIn(ModTags.Fluids.GEDRITED_WATER));
            if (fluidState.isIn(FluidTags.LAVA) && fluidState2.isIn(FluidTags.WATER) && !fluidState2.isIn(ModTags.Fluids.GEDRITED_WATER)) {
                if (state.getBlock() instanceof FluidBlock) {
                    world.setBlockState(pos, Blocks.STONE.getDefaultState(), Block.NOTIFY_ALL);
                }
                playExtinguishEvent(world, pos);
                ci.cancel();
            }
            if (fluidState.isIn(FluidTags.LAVA) && fluidState2.isIn(ModTags.Fluids.GEDRITED_WATER)) {
//                System.out.println("bareri patorik");

                if (state.getBlock() instanceof FluidBlock) {
//                    System.out.println("pokrik ximarik");
                    world.setBlockState(pos, Blocks.SOUL_SOIL.getDefaultState(), Block.NOTIFY_ALL);
                }
                playExtinguishEvent(world, pos);
                ci.cancel();
            }
        }
    }
    @Inject(method = "canBeReplacedWith", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(state.getHeight(world, pos) >= 0.44444445F && fluid.isIn(ModTags.Fluids.GEDRITED_WATER));
    }
    @Unique
    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(WorldEvents.LAVA_EXTINGUISHED, pos, 0);
    }
}
