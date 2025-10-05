package com.gedrite.fluids;

import jdk.jfr.EventFactory;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Function;

public record GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, FluidInteractionRegistry.FluidInteraction interaction) {
    public GedritedWaterInteraction(FluidType type, BlockState state) {
        this(type, (fluidState) -> {
            return state;
        });
    }

//    public GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, BlockState state) {
//        this(predicate, (fluidState) -> {
//            return state;
//        });
//    }

    public GedritedWaterInteraction(FluidType type, Function<FluidState, BlockState> getState) {
        this((level, currentPos, relativePos, currentState) -> {
            return level.getFluidState(relativePos).getFluidType() == type;
        }, getState);
    }

    public GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, Function<FluidState, BlockState> getState) {
        this(predicate, (level, currentPos, relativePos, currentState) -> {
            RandomSource randomsource = level.random;
            level.setBlockAndUpdate(currentPos, EventHooks.fireFluidPlaceBlockEvent(level, currentPos, currentPos, (BlockState)getState.apply(currentState)));
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
