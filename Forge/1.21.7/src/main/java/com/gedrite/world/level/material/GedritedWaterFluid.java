package com.gedrite.world.level.material;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.core.particles.ModParticles;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public class GedritedWaterFluid extends FlowingFluid {
    @Override
    public @NotNull Fluid getFlowing() {
        return ModFluids.FLOWING_GEDRITED_WATER.get();
    }

    @Override
    public @NotNull Fluid getSource() {
        return ModFluids.SOURCE_GEDRITED_WATER.get();
    }

    @Override
    protected boolean canConvertToSource(ServerLevel p_369955_) {
        return false;
    }

    @Override
    public @NotNull Item getBucket() {
        return ModItems.GEDRITED_WATER_BUCKET.get();
    }

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get();
    }

    @Override
    public void animateTick(@NotNull Level pLevel, @NotNull BlockPos pPos, FluidState pState, @NotNull RandomSource pRandom) {
        if (!pState.isSource() && !pState.getValue(FALLING)) {
            if (pRandom.nextInt(64) == 0) {
                pLevel.playLocalSound((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, pRandom.nextFloat() * 0.25F + 0.75F, pRandom.nextFloat() + 0.5F, false);
            }
        } /*else if (pRandom.nextInt(10) == 0) {
            pLevel.addParticle(ParticleTypes.UNDERWATER, (double)pPos.getX() + pRandom.nextDouble(), (double)pPos.getY() + pRandom.nextDouble(), (double)pPos.getZ() + pRandom.nextDouble(), 0.0D, 0.0D, 0.0D);
        }*/

    }

    @Nullable
    public ParticleOptions getDripParticle() {
        return ModParticles.DRIPPING_GEDRITED_WATER.get();
    }

    @Override
    protected void spreadTo(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockState pBlockState, @NotNull Direction pDirection, @NotNull FluidState pFluidState) {
        if (pDirection == Direction.DOWN) {
            FluidState fluidstate = pLevel.getFluidState(pPos);
            if (fluidstate.is(FluidTags.WATER)) {
                if (pBlockState.getBlock() instanceof LiquidBlock) {
                    pLevel.setBlock(pPos, Blocks.COARSE_DIRT.defaultBlockState(), 3);
                }
                this.playSplashSound(pLevel, pPos);
                return;
            }
        }

        super.spreadTo(pLevel, pPos, pBlockState, pDirection, pFluidState);
    }

    private void playSplashSound(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    protected void beforeDestroyingBlock(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, BlockState pState) {
        BlockEntity blockentity = pState.hasBlockEntity() ? pLevel.getBlockEntity(pPos) : null;
        Block.dropResources(pState, pLevel, pPos, blockentity);
    }

    @Override
    public int getSlopeFindDistance(@NotNull LevelReader pLevel) {
        return 3;
    }

    @Override
    public @NotNull BlockState createLegacyBlock(@NotNull FluidState pState) {
        return ModBlocks.GEDRITED_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(pState)));
    }

    @Override
    public boolean isSource(@NotNull FluidState pState) {
        return false;
    }

    @Override
    public boolean isSame(@NotNull Fluid pFluid) {
        return pFluid == ModFluids.SOURCE_GEDRITED_WATER.get() || pFluid == ModFluids.FLOWING_GEDRITED_WATER.get();
    }

    @Override
    public int getDropOff(@NotNull LevelReader pLevel) {
        return 2;
    }

    @Override
    public int getAmount(@NotNull FluidState pState) {
        return 0;
    }

    @Override
    public int getTickDelay(@NotNull LevelReader pLevel) {
        return 20;
    }

    @Override
    public boolean canBeReplacedWith(@NotNull FluidState pFluidState, @NotNull BlockGetter pBlockReader, @NotNull BlockPos pPos, @NotNull Fluid pFluid, @NotNull Direction pDirection) {
        return pDirection == Direction.DOWN && !(pFluid.is(FluidTags.WATER) && pFluid.is(ModTags.Fluids.GEDRITED_WATER));
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends GedritedWaterFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState pState) {
            return pState.getValue(LEVEL);
        }

    }

    public static class Source extends GedritedWaterFluid {
        @Override
        public int getAmount(@NotNull FluidState pState) {
            return 8;
        }

        @Override
        public boolean isSource(@NotNull FluidState pState) {
            return true;
        }
    }
}