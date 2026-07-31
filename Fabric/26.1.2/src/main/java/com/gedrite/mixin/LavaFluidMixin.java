package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
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
public abstract class LavaFluidMixin{
    @Inject(method = "spreadTo", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$flow(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState target, CallbackInfo ci){

        if (direction == Direction.DOWN) {
            FluidState fluidState2 = level.getFluidState(pos);
            if (target.is(FluidTags.LAVA) && fluidState2.is(FluidTags.WATER) && !fluidState2.is(ModTags.Fluids.GEDRITED_WATER)) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, Blocks.STONE.defaultBlockState(), Block.UPDATE_ALL);
                }
                playExtinguishEvent(level, pos);
                ci.cancel();
            }
            if (target.is(FluidTags.LAVA) && fluidState2.is(ModTags.Fluids.GEDRITED_WATER)) {

                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, Blocks.SOUL_SOIL.defaultBlockState(), Block.UPDATE_ALL);
                }
                playExtinguishEvent(level, pos);
                ci.cancel();
            }
        }
    }
    @Inject(method = "canBeReplacedWith", at = @At(value = "HEAD"), cancellable = true)
    protected void gedrite$canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(state.getHeight(level, pos) >= 0.44444445F && other.is(ModTags.Fluids.GEDRITED_WATER));
    }
    @Unique
    private void playExtinguishEvent(LevelAccessor level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
