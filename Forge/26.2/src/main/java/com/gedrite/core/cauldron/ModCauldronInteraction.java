package com.gedrite.core.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import com.gedrite.mixins.DispatcherInvoker; // Re-adding the import
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;

import java.util.function.Predicate;

public class ModCauldronInteraction {
    private static final ExtraCodecs.LateBoundIdMapper<String, CauldronInteraction.Dispatcher> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();

    public static CauldronInteraction.Dispatcher GEDRITED_WATER = newDispatcher("gedrited_water");
    private static boolean isUnderWater(Level p_362699_, BlockPos p_366592_) {
        FluidState fluidstate = p_362699_.getFluidState(p_366592_.above());
        return fluidstate.is(FluidTags.WATER);
    }

    private static CauldronInteraction.Dispatcher newDispatcher(String name) {
        CauldronInteraction.Dispatcher result = new CauldronInteraction.Dispatcher();
        ID_MAPPER.put(name, result);
        return result;
    }

    private static InteractionResult emptyBucket(
            Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand, BlockState newState, SoundEvent soundEvent
    ) {
        if (!level.isClientSide()) {
            Item itemUsed = itemInHand.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, new ItemStack(Items.BUCKET)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(itemUsed));
            level.setBlockAndUpdate(pos, newState);
            level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }

        return InteractionResult.SUCCESS;
    }

    private static InteractionResult fillBucket(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            ItemStack itemInHand,
            ItemStack newItem,
            Predicate<BlockState> canFill,
            SoundEvent soundEvent
    ) {
        if (!canFill.test(state)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else {
            if (!level.isClientSide()) {
                Item itemUsed = itemInHand.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, newItem));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(itemUsed));
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.SUCCESS;
        }
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

    public static void registerBehavior() {
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.BUCKET, (state, level, pos, player, hand, stack) -> fillBucket(state, level, pos, player, hand, stack, new ItemStack(ModItems.GEDRITED_WATER_BUCKET.get()), statex -> true, SoundEvents.BUCKET_FILL));
        ((DispatcherInvoker) CauldronInteractions.EMPTY).gedrite$put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.LAVA).gedrite$put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.WATER).gedrite$put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.POWDER_SNOW).gedrite$put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction);
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.WATER_BUCKET,  ModCauldronInteraction::fillWaterInteraction);
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.LAVA_BUCKET,  ModCauldronInteraction::fillLavaInteraction);
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.POWDER_SNOW_BUCKET,  ModCauldronInteraction::fillPowderSnowInteraction);

        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.ARROW, (state, level, pos, player, hand, stack) -> {
            if (level.isClientSide()) return InteractionResult.SUCCESS;

            int arrowsInHand = stack.getCount();
            int arrowsToConvert = Math.min(arrowsInHand, 5);
            if (!player.isCreative()) stack.shrink(arrowsToConvert - 1);
            return fillBucket(state, level, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW.get(), arrowsToConvert),statex -> true, SoundEvents.CANDLE_EXTINGUISH);
        });
    }

//    static void addDefaultInteractions(Map<Item, CauldronInteraction> pInteractionsMap) {
//        pInteractionsMap.put(ModItems.GEDRITED_WATER_BUCKET.get(), ModCauldronInteraction::fillGedritedWaterInteraction
//        );
//    }
}