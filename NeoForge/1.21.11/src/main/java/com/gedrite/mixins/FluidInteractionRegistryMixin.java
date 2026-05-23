package com.gedrite.mixins;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.GedritedWaterInteraction;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Mixin(FluidInteractionRegistry.class)
public class FluidInteractionRegistryMixin {
//    @Inject(method = "canInteract", at = @At(value = "HEAD"), remap = false, cancellable = true)
//    private static void gedrite$shouldSpreadLiquid(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
//        FluidState fluidState = level.getFluidState(pos);
//        boolean bl = level.getBlockState(pos.below()).is(Blocks.SOUL_SOIL);
//        if (fluidState.is(FluidTags.LAVA)) {          /// LAVA
//            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
//                BlockPos blockPos = pos.relative(direction.getOpposite());
////            if (!world.getFluidState(blockPos).isIn(ModTags.Fluids.GEDRITED_WATER) && world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
//                if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
//                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
//                    level.setBlockAndUpdate(pos, block.defaultBlockState());
//                    System.out.println("COBBLESTONE");
//                    fizz(level, pos);
//                    cir.setReturnValue(true);
//                }
//                if (level.getFluidState(blockPos).is(ModTags.Fluids.GEDRITED_WATER)) {
//                    Block block = fluidState.isSource() ? Blocks.OBSIDIAN : Blocks.SOUL_SAND;
//                    level.setBlockAndUpdate(pos, block.defaultBlockState());
//                    System.out.println("SOUL_SAND");
//                    fizz(level, pos);
//                    cir.setReturnValue(true);
//                }
//                if (!bl || !level.getBlockState(blockPos).is(Blocks.BLUE_ICE)) continue;
//                level.setBlockAndUpdate(pos, Blocks.BASALT.defaultBlockState());
//
//                fizz(level, pos);
//                cir.setReturnValue(true);
//            }
//        }
//        if (fluidState.is(FluidTags.WATER)) {           /// WATER
//            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
//                BlockPos blockPos = pos.relative(direction.getOpposite());
//                if (!level.getBlockState(blockPos).is(ModBlocks.GEDRITE_BLOCK.get())) continue;
//                // replaceWaterWithGedritedWater(level, pos);
//                System.out.println(fluidState.getAmount());
//                if (fluidState.getAmount() == 8){
//                    level.setBlockAndUpdate(pos, ModBlocks.GEDRITED_WATER_BLOCK.get().defaultBlockState());
//
//                    pssh(level, pos);
//                    cir.setReturnValue(true);
//                }
//            }
//        }
//        if (fluidState.is(ModTags.Fluids.GEDRITED_WATER)) {          /// GEDRITED WATER
//            for (Direction direction : LiquidBlock.POSSIBLE_FLOW_DIRECTIONS) {
//                BlockPos blockPos = pos.relative(direction.getOpposite());
//                if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
//                    level.setBlockAndUpdate(pos, Blocks.COARSE_DIRT.defaultBlockState());
//                    pssh(level, pos);
//                    cir.setReturnValue(true);
//                }
//            }
//        }
//        cir.setReturnValue(false);
//    }

    @Inject(method = "<clinit>", at = @At("TAIL"), cancellable = true)
    private static void gedrite$static(CallbackInfo ci) {
        FluidInteractionRegistry.addInteraction((FluidType) NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation((FluidType) ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get(), (fluidState) ->
                fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.SOUL_SAND.defaultBlockState()));

//        FluidInteractionRegistry.addInteraction((FluidType)NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation((FluidType)NeoForgeMod.WATER_TYPE.value(), (fluidState) ->
//                fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState()));


        FluidInteractionRegistry.addInteraction((FluidType) NeoForgeMod.WATER_TYPE.value(), GedritedWaterInteraction((level, currentPos, relativePos, currentState) -> {
            return level.getFluidState(relativePos).is(ModFluids.FLOWING_GEDRITED_WATER.get()) || level.getFluidState(relativePos).is(ModFluids.SOURCE_GEDRITED_WATER.get());
        }, Blocks.COARSE_DIRT.defaultBlockState()));


        FluidInteractionRegistry.addInteraction((FluidType)NeoForgeMod.WATER_TYPE.value(), GedriteInteraction((level, currentPos, relativePos, currentState) -> {
            return level.getBlockState(relativePos).is(ModBlocks.GEDRITE_BLOCK.get()) && currentState.isSource();
        }, ModFluids.SOURCE_GEDRITED_WATER.get().defaultFluidState().createLegacyBlock()));
        ci.cancel();
    }

    @Unique
    private static FluidInteractionRegistry.InteractionInformation GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, BlockState state) {
        return GedritedWaterInteraction(predicate, (fluidState) -> {
            return state;
        });
    }

    @Unique
    private FluidInteractionRegistry.InteractionInformation GedritedWaterInteraction(FluidType type, Function<FluidState, BlockState> getState) {
        return GedritedWaterInteraction((level, currentPos, relativePos, currentState) -> {
            return level.getFluidState(relativePos).getFluidType() == type;
        }, getState);
    }

    @Unique
    private static FluidInteractionRegistry.InteractionInformation GedritedWaterInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, Function<FluidState, BlockState> getState) {
        return new FluidInteractionRegistry.InteractionInformation(predicate, (level, currentPos, relativePos, currentState) -> {
            pssh(level, currentPos);
            level.setBlockAndUpdate(currentPos, EventHooks.fireFluidPlaceBlockEvent(level, currentPos, currentPos, (BlockState)getState.apply(currentState)));
        });
    }

    @Unique
    private static FluidInteractionRegistry.InteractionInformation GedriteInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, BlockState state) {
        return GedriteInteraction(predicate, (fluidState) -> {
            return state;
        });
    }

    @Unique
    private FluidInteractionRegistry.InteractionInformation GedriteInteraction(FluidType type, Function<FluidState, BlockState> getState) {
        return GedriteInteraction((level, currentPos, relativePos, currentState) -> {
            return level.getFluidState(relativePos).getFluidType() == type;
        }, getState);
    }



    @Unique
    private static FluidInteractionRegistry.InteractionInformation GedriteInteraction(FluidInteractionRegistry.HasFluidInteraction predicate, Function<FluidState, BlockState> getState) {
        return new FluidInteractionRegistry.InteractionInformation(predicate, (level, currentPos, relativePos, currentState) -> {
            level.setBlockAndUpdate(currentPos, EventHooks.fireFluidPlaceBlockEvent(level, currentPos, currentPos, (BlockState)getState.apply(currentState)));
        });
    }

    @Unique
    private static final Map<FluidType, List<GedritedWaterInteraction>> INTERACTIONS = new HashMap<>();

    @Unique
    private static synchronized void addModInteraction(FluidType source, GedritedWaterInteraction interaction) {
        ((List)INTERACTIONS.computeIfAbsent(source, (s) -> {return new ArrayList();})).add(interaction);
    }

    @Unique
    private static void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    @Unique
    private static void pssh(LevelAccessor level, BlockPos pos){
        level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}
