package com.gedrite.core.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
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
    private static boolean isUnderWater(Level p_362699_, BlockPos p_366592_) {
        FluidState fluidstate = p_362699_.getFluidState(p_366592_.above());
        return fluidstate.is(FluidTags.WATER);
    }

    private static InteractionResult fillWaterInteraction(
            BlockState p_363465_, Level p_369690_, BlockPos p_365994_, Player p_361538_, InteractionHand p_363296_, ItemStack p_369551_
    ) {
        return emptyBucket(
                p_369690_,
                p_365994_,
                p_361538_,
                p_363296_,
                p_369551_,
                Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)),
                SoundEvents.BUCKET_EMPTY
        );
    }

    private static InteractionResult fillGedritedWaterInteraction(
            BlockState p_365957_, Level p_368892_, BlockPos p_365280_, Player p_368758_, InteractionHand p_369203_, ItemStack p_369309_
    ) {
        return (InteractionResult)(isUnderWater(p_368892_, p_365280_)
                ? InteractionResult.CONSUME
                : emptyBucket(p_368892_, p_365280_, p_368758_, p_369203_, p_369309_, ModBlocks.GEDRITED_WATER_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY));
    }
    private static InteractionResult fillLavaInteraction(
            BlockState p_365957_, Level p_368892_, BlockPos p_365280_, Player p_368758_, InteractionHand p_369203_, ItemStack p_369309_
    ) {
        return (InteractionResult)(isUnderWater(p_368892_, p_365280_)
                ? InteractionResult.CONSUME
                : emptyBucket(p_368892_, p_365280_, p_368758_, p_369203_, p_369309_, Blocks.LAVA_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA));
    }

    private static InteractionResult fillPowderSnowInteraction(
            BlockState p_367322_, Level p_368177_, BlockPos p_369168_, Player p_362349_, InteractionHand p_363299_, ItemStack p_365742_
    ) {
        return (InteractionResult)(isUnderWater(p_368177_, p_369168_)
                ? InteractionResult.CONSUME
                : emptyBucket(
                p_368177_,
                p_369168_,
                p_362349_,
                p_363299_,
                p_365742_,
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
