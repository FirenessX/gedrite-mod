package com.gedrite.fluids.custom;

import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class GedritedWaterFluid extends FlowingFluid {
    @Override
    public @NonNull Fluid getFlowing() {
        return ModFluids.FLOWING_GEDRITED_WATER;
    }

    @Override
    public @NonNull Fluid getSource() {
        return ModFluids.GEDRITED_WATER;
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState fluidState, RandomSource random) {
        if (random.nextInt(50) == 0) {
            level.addParticle(
                    ModParticleTypes.DECAY_PARTICLE, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.0, Math.sin(level.getGameTime()), 1.0 , Math.sin(level.getGameTime()
                    ));
        }
    }

    @Override
    public @Nullable ParticleOptions getDripParticle() {
        return ModParticleTypes.DRIPPING_GEDRITED_WATER;
    }

    @Override
    public boolean isSame(Fluid other) {
        return other == ModFluids.GEDRITED_WATER || other == ModFluids.FLOWING_GEDRITED_WATER;
    }

    @Override
    protected boolean canConvertToSource(@NonNull ServerLevel level) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        playSplashSound(level, pos);
    }

    @Override
    protected int getSlopeFindDistance(LevelReader level) {
        return 3;
    }

    @Override
    protected int getDropOff(LevelReader level) {
        return 2;
    }

    @Override
    public @NonNull Item getBucket() {
        return ModFluids.GEDRITED_WATER_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction) {
        return direction == Direction.DOWN && !other.is(ModTags.Fluids.GEDRITED_WATER);
    }

    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState target) {
        if (direction == Direction.DOWN) {
            FluidState fluidState2 = level.getFluidState(pos);
            if (this.is(ModTags.Fluids.GEDRITED_WATER) && (fluidState2.is(FluidTags.WATER) && !fluidState2.is(ModTags.Fluids.GEDRITED_WATER))) {
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, Blocks.COARSE_DIRT.defaultBlockState(), Block.UPDATE_ALL);
                }
                this.playSplashSound(level, pos);
                return;
            }

        }
        super.spreadTo(level, pos, state, direction, target);
    }

    @Override
    public @NonNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 20;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected @NonNull BlockState createLegacyBlock(@NonNull FluidState fluidState) {
        return ModFluids.GEDRITED_WATER_BLOCK.defaultBlockState().setValue(LiquidBlock.LEVEL, GedritedWaterFluid.getLegacyLevel(fluidState));

    }

    @Override
    public boolean isSource(FluidState fluidState) {
        return false;
    }

    @Override
    public int getAmount(FluidState fluidState) {
        return 0;
    }

    private void playSplashSound(LevelAccessor level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static class Flowing
            extends GedritedWaterFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return false;
        }
    }

    public static class Source
            extends GedritedWaterFluid {
        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }
}
