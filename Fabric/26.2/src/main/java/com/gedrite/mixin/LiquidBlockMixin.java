package com.gedrite.mixin;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public class LiquidBlockMixin {

    @Shadow @Final protected FlowingFluid fluid;

    @Inject(method = "shouldSpreadLiquid", at = @At(value = "HEAD"), cancellable = true)
    private void gedrite$shouldSpreadLiquid(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        FluidState fluidState = level.getFluidState(pos);
        boolean bl = level.getBlockState(pos.below()).is(Blocks.SOUL_SOIL);
        if (fluidState.is(FluidTags.LAVA)) {          /// LAVA
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
                if (!level.getFluidState(blockPos).is(ModTags.Fluids.GEDRITED_WATER) && level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                    level.setBlockAndUpdate(pos, block.defaultBlockState());
                    this.playExtinguishSound(level, pos);
                    cir.setReturnValue(false);
                }
                if (level.getFluidState(blockPos).is(ModTags.Fluids.GEDRITED_WATER)) {
                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.SOUL_SAND;
                    level.setBlockAndUpdate(pos, block.defaultBlockState());
                    this.playExtinguishSound(level, pos);
                    cir.setReturnValue(false);
                }
                if (!bl || !level.getBlockState(blockPos).is(Blocks.BLUE_ICE)) continue;
                level.setBlockAndUpdate(pos, Blocks.BASALT.defaultBlockState());
                this.playExtinguishSound(level, pos);
                cir.setReturnValue(false);
            }
        }
        if (!fluidState.is(ModTags.Fluids.GEDRITED_WATER) && fluidState.is(FluidTags.WATER)) {           /// WATER
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
                if (!level.getBlockState(blockPos).is(ModBlocks.GEDRITE_BLOCK)) continue;
                if (fluidState.getAmount() == 8){
                    level.setBlockAndUpdate(pos, ModFluids.GEDRITED_WATER_BLOCK.defaultBlockState());
                    this.playSplashSound(level, pos);
                    cir.setReturnValue(false);
                }
            }
        }
        if (fluidState.is(ModTags.Fluids.GEDRITED_WATER) && fluidState.is(FluidTags.WATER)) {          /// GEDRITED WATER
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
                if (!level.getFluidState(blockPos).is(ModTags.Fluids.GEDRITED_WATER) && level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    level.setBlockAndUpdate(pos, Blocks.COARSE_DIRT.defaultBlockState());
                    this.playSplashSound(level, pos);
                    cir.setReturnValue(false);
                }
            }
        }
        cir.setReturnValue(true);
    }

    private void playExtinguishSound(LevelAccessor level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    private void playSplashSound(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}