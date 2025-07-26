package com.gedrite.core.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.emptyBucket;

public interface ModCauldronInteraction {

    CauldronInteraction.InteractionMap GEDRITED_WATER = CauldronInteraction.newInteractionMap("gedrited_water");
    private static boolean isUnderWater(Level level, BlockPos blockPos) {
        FluidState fluidstate = level.getFluidState(blockPos.above());
        return fluidstate.is(FluidTags.WATER);
    }

    private static InteractionResult fillWaterInteraction(
            BlockState p_363465_, Level blockState, BlockPos blockPos, Player player, InteractionHand hand, ItemStack stack
    ) {
        return emptyBucket(
                blockState,
                blockPos,
                player,
                hand,
                stack,
                Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)),
                SoundEvents.BUCKET_EMPTY
        );
    }

    private static InteractionResult fillGedritedWaterInteraction(
            BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack stack
    ) {
        return (InteractionResult)(isUnderWater(level, blockPos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, blockPos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY));
    }
    private static InteractionResult fillLavaInteraction(
            BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack stack
    ) {
        return (InteractionResult)(isUnderWater(level, blockPos)
                ? InteractionResult.CONSUME
                : emptyBucket(level, blockPos, player, hand, stack, Blocks.LAVA_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA));
    }

    private static InteractionResult fillPowderSnowInteraction(
            BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack stack
    ) {
        return (InteractionResult)(isUnderWater(level, blockPos)
                ? InteractionResult.CONSUME
                : emptyBucket(
                level,
                blockPos,
                player,
                hand,
                stack,
                Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)),
                SoundEvents.BUCKET_EMPTY_POWDER_SNOW
        ));
    }

    static void registerBehavior() {
//        GEDRITED_WATER.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITED_WATER_BUCKET.get()), (statex) -> true, SoundEvents.BUCKET_FILL));
//        addDefaultInteractions(GEDRITED_WATER);
//        CauldronInteraction.EMPTY.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
//        CauldronInteraction.LAVA.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
//        CauldronInteraction.WATER.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
//        CauldronInteraction.POWDER_SNOW.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
//        GEDRITED_WATER.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
//        GEDRITED_WATER.put(Items.WATER_BUCKET,  CauldronInteraction.FILL_WATER);
//        GEDRITED_WATER.put(Items.LAVA_BUCKET,  CauldronInteraction.FILL_LAVA);
//        GEDRITED_WATER.put(Items.POWDER_SNOW_BUCKET,  CauldronInteraction.FILL_POWDER_SNOW);

        Map<Item, CauldronInteraction> mapE = CauldronInteraction.EMPTY.map();
        Map<Item, CauldronInteraction> mapW = CauldronInteraction.WATER.map();
        Map<Item, CauldronInteraction> mapL = CauldronInteraction.LAVA.map();
        Map<Item, CauldronInteraction> mapS = CauldronInteraction.POWDER_SNOW.map();
        Map<Item, CauldronInteraction> mapGW = GEDRITED_WATER.map();
        mapGW.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITED_WATER_BUCKET.get()), statex -> true, SoundEvents.BUCKET_FILL));
        mapE.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        mapL.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        mapW.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        mapS.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        mapGW.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        mapGW.put(Items.WATER_BUCKET,  ModCauldronInteraction::fillWaterInteraction);
        mapGW.put(Items.LAVA_BUCKET,  ModCauldronInteraction::fillLavaInteraction);
        mapGW.put(Items.POWDER_SNOW_BUCKET,  ModCauldronInteraction::fillPowderSnowInteraction);
    }

//    static void addDefaultInteractions(Map<Item, CauldronInteraction> pInteractionsMap) {
//        pInteractionsMap.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction
//        );
//    }
}
