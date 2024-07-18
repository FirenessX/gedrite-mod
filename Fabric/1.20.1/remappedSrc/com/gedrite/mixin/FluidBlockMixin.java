package com.gedrite.mixin;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

    @Shadow @Final protected FlowableFluid fluid;

    @Inject(method = "receiveNeighborFluids", at = @At(value = "HEAD"), cancellable = true)
    private void gedrite$receiveNeighborFluids(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        FluidState fluidState = world.getFluidState(pos);
        boolean bl = world.getBlockState(pos.down()).isOf(Blocks.SOUL_SOIL);
        if (fluidState.isIn(FluidTags.LAVA)) {          /// LAVA
            for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.offset(direction.getOpposite());
                if (!world.getFluidState(blockPos).isIn(ModTags.Fluids.GEDRITED_WATER) && world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                    Block block = fluidState.isStill() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                    world.setBlockState(pos, block.getDefaultState());
                    this.playExtinguishSound(world, pos);
                    cir.setReturnValue(false);
                }
                if (world.getFluidState(blockPos).isIn(ModTags.Fluids.GEDRITED_WATER)) {
                    Block block = fluidState.isStill() ? Blocks.OBSIDIAN : Blocks.SOUL_SAND;
                    world.setBlockState(pos, block.getDefaultState());
                    this.playExtinguishSound(world, pos);
                    cir.setReturnValue(false);
                }
                if (!bl || !world.getBlockState(blockPos).isOf(Blocks.BLUE_ICE)) continue;
                world.setBlockState(pos, Blocks.BASALT.getDefaultState());
                this.playExtinguishSound(world, pos);
                cir.setReturnValue(false);
            }
        }
        if (!fluidState.isIn(ModTags.Fluids.GEDRITED_WATER) && fluidState.isIn(FluidTags.WATER)) {           /// WATER
            for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.offset(direction.getOpposite());
                if (!world.getBlockState(blockPos).isOf(ModBlocks.GEDRITE_BLOCK)) continue;
                // this.replaceWaterWithGedritedWater(world, pos);
                System.out.println(fluidState.getLevel());
                if (fluidState.getLevel() == 8){
                    world.setBlockState(pos, ModFluids.GEDRITED_WATER_BLOCK.getDefaultState());
                    this.playSplashSound(world, pos);
                    cir.setReturnValue(false);
                }
            }
        }
        if (fluidState.isIn(ModTags.Fluids.GEDRITED_WATER) && fluidState.isIn(FluidTags.WATER)) {          /// GEDRITED WATER
            for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
                BlockPos blockPos = pos.offset(direction.getOpposite());
                if (!world.getFluidState(blockPos).isIn(ModTags.Fluids.GEDRITED_WATER) && world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                    Block block = Blocks.COARSE_DIRT;
                    world.setBlockState(pos, block.getDefaultState());
                    this.playSplashSound(world, pos);
                    cir.setReturnValue(false);
                }
            }
        }
        cir.setReturnValue(true);
    }

//    private void replaceWaterWithGedritedWater(World world, BlockPos centerPos) {
//        for (int x = -1; x <= 1; x++) {
//            for (int y = -1; y <= 1; y++) {
//                for (int z = -1; z <= 1; z++) {
//                    int distanceSq = (x * x) + (y * y) + (z * z);
//                    BlockPos currentPos = centerPos.add(x, y, z);
//                    if (world.getFluidState(currentPos).isIn(FluidTags.WATER) && !world.getFluidState(currentPos).isIn(ModTags.Fluids.GEDRITED_WATER)) {
//                        if (distanceSq <= 1) {
//                            world.setBlockState(currentPos, ModFluids.GEDRITED_WATER_BLOCK.getDefaultState());
//                        }
//                    }
//                }
//            }
//        }
//    }

    private void playExtinguishSound(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(WorldEvents.LAVA_EXTINGUISHED, pos, 0);
    }

    private void playSplashSound(WorldAccess world, BlockPos pos){
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}