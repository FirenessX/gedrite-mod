package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin{
    @Inject(method = "flow", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){
        if (direction == Direction.DOWN) {
            FluidState fluidState2 = world.getFluidState(pos);
            if (fluidState.isIn(FluidTags.LAVA) && fluidState2.isIn(FluidTags.WATER) && !fluidState2.isIn(ModTags.Fluids.GEDRITED_WATER)) {
                if (state.getBlock() instanceof FluidBlock) {
                    world.setBlockState(pos, Blocks.STONE.getDefaultState(), Block.NOTIFY_ALL);
                }
                playExtinguishEvent(world, pos);
                ci.cancel();
            }
            if (fluidState.isIn(FluidTags.LAVA) && fluidState2.isIn(ModTags.Fluids.GEDRITED_WATER)) {
                if (state.getBlock() instanceof FluidBlock) {
                    world.setBlockState(pos, Blocks.SOUL_SOIL.getDefaultState(), Block.NOTIFY_ALL);
                }
                playExtinguishEvent(world, pos);
                ci.cancel();
            }
        }
    }
    @Unique
    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(WorldEvents.LAVA_EXTINGUISHED, pos, 0);
    }
}
