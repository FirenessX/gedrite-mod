package com.gedrite.blocks.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.function.Predicate;

public interface ModCauldronBehavior {
    CauldronBehavior.CauldronBehaviorMap GEDRITED_WATER_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("gedrited_water");
//    CauldronBehavior FILL_WITH_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
//        CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY);

    private static ActionResult tryFillWithGedritedWater(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return isUnderwater(world, pos) ? ActionResult.CONSUME : CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_FILL);
    }

    private static ActionResult tryFillWithWater(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return CauldronBehavior.fillCauldron(world, pos, player, hand, stack, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY);
    }

    private static ActionResult tryFillWithLava(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return isUnderwater(world, pos) ? ActionResult.CONSUME : CauldronBehavior.fillCauldron(world, pos, player, hand, stack, Blocks.LAVA_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
    }

    private static ActionResult tryFillWithPowderSnow(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return isUnderwater(world, pos) ? ActionResult.CONSUME : CauldronBehavior.fillCauldron(world, pos, player, hand, stack, Blocks.POWDER_SNOW_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3), SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW);
    }

    private static boolean isUnderwater(World world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos.up());
        return fluidState.isIn(FluidTags.WATER);
    }

//    static ActionResult gedriteArrowUse(
//            BlockState state,
//            World world,
//            BlockPos pos,
//            PlayerEntity player,
//            Hand hand,
//            ItemStack stack,
//            ItemStack output,
//            Predicate<BlockState> fullPredicate,
//            SoundEvent soundEvent
//    ) {
//        if (!fullPredicate.test(state)) {
//            return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
//        } else {
//            if (!world.isClient) {
//                Item item = stack.getItem();
//                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
//                player.incrementStat(Stats.USE_CAULDRON);
//                player.incrementStat(Stats.USED.getOrCreateStat(item));
//                world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
//            }
//
//            return ActionResult.SUCCESS;
//        }
//    }

    static void registerBehavior() {
        Map<Item, CauldronBehavior> mapE = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapW = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapL = CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapS = CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapGW = GEDRITED_WATER_CAULDRON_BEHAVIOR.map();
        mapGW.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModFluids.GEDRITED_WATER_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL));
//        mapGW.put(Items.ARROW, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW),statex -> true, SoundEvents.BLOCK_CANDLE_EXTINGUISH));

        mapGW.put(Items.ARROW, (state, world, pos, player, hand, stack) -> {
            if (world.isClient()) return ActionResult.SUCCESS;

            int arrowsInHand = stack.getCount();
            int arrowsToConvert = Math.min(arrowsInHand, 5);
            if (!player.isCreative()) stack.decrement(arrowsToConvert - 1);
            return CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW, arrowsToConvert),statex -> true, SoundEvents.BLOCK_CANDLE_EXTINGUISH);
        });

////////////////////////////////////
//        AtomicInteger arrowMax = new AtomicInteger();
//        mapGW.put(Items.ARROW, (state, world, pos, player, hand, stack) -> {
//            arrowMax.getAndIncrement();
//            if(arrowMax.get() == 5){
//                arrowMax.getAndSet(0);
//                return CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW),statex -> true, SoundEvents.BLOCK_CANDLE_EXTINGUISH);
//            }
//            return gedriteArrowUse(state, world, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW),statex -> true, SoundEvents.BLOCK_CANDLE_EXTINGUISH);
//        });
////////////////////////////////////

        mapE.put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronBehavior::tryFillWithGedritedWater);
        mapL.put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronBehavior::tryFillWithGedritedWater);
        mapW.put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronBehavior::tryFillWithGedritedWater);
        mapS.put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronBehavior::tryFillWithGedritedWater);
        mapGW.put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronBehavior::tryFillWithGedritedWater);
        mapGW.put(Items.WATER_BUCKET,  ModCauldronBehavior::tryFillWithWater);
        mapGW.put(Items.LAVA_BUCKET,  ModCauldronBehavior::tryFillWithLava);
        mapGW.put(Items.POWDER_SNOW_BUCKET, ModCauldronBehavior::tryFillWithPowderSnow);
    }
}
