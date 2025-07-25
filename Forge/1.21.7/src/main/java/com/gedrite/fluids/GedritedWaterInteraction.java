package com.gedrite.fluids;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Function;

public record GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, FluidInteractionRegistry.FluidInteraction interaction) {
    public GedritedWaterInteraction(FluidType type, BlockState state) {
        this(type, (fluidState) -> {
            return state;
        });
    }

    public GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, BlockState state) {
        this(predicate, (fluidState) -> {
            return state;
        });
    }

    public GedritedWaterInteraction(FluidType type, Function<FluidState, BlockState> getState) {
        this((level, currentPos, relativePos, currentState) -> {
            return level.getFluidState(relativePos).getFluidType() == type;
        }, getState);
    }

    public GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, Function<FluidState, BlockState> getState) {
        this(predicate, (level, currentPos, relativePos, currentState) -> {
            RandomSource randomsource = level.random;
            level.setBlockAndUpdate(currentPos, ForgeEventFactory.fireFluidPlaceBlockEvent(level, currentPos, currentPos, (BlockState)getState.apply(currentState)));
        });
    }

    public GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, FluidInteractionRegistry.FluidInteraction interaction) {
        this.predicate = predicate;
        this.interaction = interaction;
    }

    public FluidInteractionRegistry.HasFluidInteraction predicate() {
        return this.predicate;
    }

    public FluidInteractionRegistry.FluidInteraction interaction() {
        return this.interaction;
    }
}
