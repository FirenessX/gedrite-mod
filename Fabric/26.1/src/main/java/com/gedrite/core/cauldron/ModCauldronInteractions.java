package com.gedrite.core.cauldron;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import com.gedrite.mixin.DispatcherInvoker;
import com.mojang.serialization.Codec;
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

import java.util.Map;
import java.util.function.Predicate;

public class ModCauldronInteractions {
    private static final ExtraCodecs.LateBoundIdMapper<String, CauldronInteraction.Dispatcher> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<CauldronInteraction.Dispatcher> CODEC = ID_MAPPER.codec(Codec.STRING);

    public static final CauldronInteraction.Dispatcher GEDRITED_WATER = ModCauldronInteractions.newDispatcher("gedrited_water");
    private static CauldronInteraction.Dispatcher newDispatcher(String name) {
        CauldronInteraction.Dispatcher result = new CauldronInteraction.Dispatcher();
        ID_MAPPER.put(name, result);
        return result;
    }

    private static InteractionResult fillGedritedWaterInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand) {
        return isUnderWater(level, pos) ? InteractionResult.CONSUME : emptyBucket(level, pos, player, hand, itemInHand, ModBlocks.GEDRITED_WATER_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    }

    private static InteractionResult fillWaterInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand) {
        return emptyBucket(level, pos, player, hand, itemInHand, (BlockState) Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY);
    }

    private static InteractionResult fillLavaInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand) {
        return isUnderWater(level, pos) ? InteractionResult.CONSUME : emptyBucket(level, pos, player, hand, itemInHand, Blocks.LAVA_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
    }

    private static InteractionResult fillPowderSnowInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand) {
        return isUnderWater(level, pos) ? InteractionResult.CONSUME : emptyBucket(level, pos, player, hand, itemInHand, (BlockState)Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY_POWDER_SNOW);
    }

    public static void bootstrap(){
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.ARROW, (state, level, pos, player, hand, stack) -> {
            if (level.isClientSide()) return InteractionResult.SUCCESS;

            int arrowsInHand = stack.getCount();
            int arrowsToConvert = Math.min(arrowsInHand, 5);
            if (!player.isCreative()) stack.shrink(arrowsToConvert - 1);
            return fillBucket(state, level, pos, player, hand, stack, new ItemStack(ModItems.GEDRITE_ARROW, arrowsToConvert), statex -> true, SoundEvents.CANDLE_EXTINGUISH);
        });

        ModCauldronInteractions.addDefaultInteractions((DispatcherInvoker) GEDRITED_WATER);
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(Items.BUCKET, (state, level, pos, player, hand, itemInHand) -> fillBucket(state, level, pos, player, hand, itemInHand, new ItemStack(ModFluids.GEDRITED_WATER_BUCKET), blockState -> true, SoundEvents.BUCKET_FILL));
        ((DispatcherInvoker) GEDRITED_WATER).gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.EMPTY).gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.LAVA).gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.WATER).gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
        ((DispatcherInvoker) CauldronInteractions.POWDER_SNOW).gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
    }

    static void addDefaultInteractions(DispatcherInvoker interactionMap) {
        interactionMap.gedrite$put(ModFluids.GEDRITED_WATER_BUCKET, ModCauldronInteractions::fillGedritedWaterInteraction);
        interactionMap.gedrite$put(Items.LAVA_BUCKET, ModCauldronInteractions::fillLavaInteraction);
        interactionMap.gedrite$put(Items.WATER_BUCKET, ModCauldronInteractions::fillWaterInteraction);
        interactionMap.gedrite$put(Items.POWDER_SNOW_BUCKET, ModCauldronInteractions::fillPowderSnowInteraction);
    }

    static InteractionResult emptyBucket(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand, BlockState newState, SoundEvent soundEvent) {
        if (!level.isClientSide()) {
            Item itemUsed = itemInHand.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, new ItemStack(Items.BUCKET)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(itemUsed));
            level.setBlockAndUpdate(pos, newState);
            level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f);
            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        return InteractionResult.SUCCESS;
    }

    static InteractionResult fillBucket(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand, ItemStack newItem, Predicate<BlockState> canFill, SoundEvent soundEvent) {
        if (!canFill.test(state)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
        if (!level.isClientSide()) {
            Item itemUsed = itemInHand.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemInHand, player, newItem));
            player.awardStat(Stats.USE_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(itemUsed));
            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f);
            level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
        }
        return InteractionResult.SUCCESS;
    }

    private static boolean isUnderWater(Level level, BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos.above());
        return fluidState.is(FluidTags.WATER);
    }
}