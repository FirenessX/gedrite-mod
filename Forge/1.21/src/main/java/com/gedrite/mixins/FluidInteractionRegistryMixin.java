package com.gedrite.mixins;

import com.gedrite.blocks.ModBlocks;
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
import net.minecraftforge.fluids.FluidInteractionRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidInteractionRegistry.class)
public class FluidInteractionRegistryMixin {
    @Inject(method = "canInteract", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void gedrite$shouldSpreadLiquid(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        FluidState fluidState = level.getFluidState(pos);
        boolean bl = level.getBlockState(pos.below()).is(Blocks.SOUL_SOIL);
        if (fluidState.is(FluidTags.LAVA)) {          /// LAVA
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
//            if (!world.getFluidState(blockPos).isIn(ModTags.Fluids.GEDRITED_WATER) && world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                    level.setBlockAndUpdate(pos, block.defaultBlockState());
                    System.out.println("COBBLESTONE");
                    fizz(level, pos);
                    cir.setReturnValue(true);
                }
                if (level.getFluidState(blockPos).is(ModTags.Fluids.GEDRITED_WATER)) {
                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.SOUL_SAND;
                    level.setBlockAndUpdate(pos, block.defaultBlockState());
                    System.out.println("SOUL_SAND");
                    fizz(level, pos);
                    cir.setReturnValue(true);
                }
                if (!bl || !level.getBlockState(blockPos).is(Blocks.BLUE_ICE)) continue;
                level.setBlockAndUpdate(pos, Blocks.BASALT.defaultBlockState());

                fizz(level, pos);
                cir.setReturnValue(true);
            }
        }
        if (fluidState.is(FluidTags.WATER)) {           /// WATER
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
                if (!level.getBlockState(blockPos).is(ModBlocks.GEDRITE_BLOCK.get())) continue;
                // replaceWaterWithGedritedWater(level, pos);
                System.out.println(fluidState.getAmount());
                if (fluidState.getAmount() == 8){
                    level.setBlockAndUpdate(pos, ModBlocks.GEDRITED_WATER_BLOCK.get().defaultBlockState());

                    pssh(level, pos);
                    cir.setReturnValue(true);
                }
            }
        }
        if (fluidState.is(ModTags.Fluids.GEDRITED_WATER)) {          /// GEDRITED WATER
            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.relative(direction.getOpposite());
                if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    level.setBlockAndUpdate(pos, Blocks.COARSE_DIRT.defaultBlockState());
                    pssh(level, pos);
                    cir.setReturnValue(true);
                }
            }
        }
        cir.setReturnValue(false);
    }

    @Unique
    private static void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    @Unique
    private static void pssh(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
