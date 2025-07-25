package com.gedrite.blocks.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public interface ModCauldronBehavior {
    CauldronBehavior.CauldronBehaviorMap GEDRITED_WATER_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("gedrited_water");
    CauldronBehavior FILL_WITH_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
        CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY);
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

    static void registerBehavior() {
        Map<Item, CauldronBehavior> mapE = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapW = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapL = CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapS = CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapGW = GEDRITED_WATER_CAULDRON_BEHAVIOR.map();
        mapGW.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModFluids.GEDRITED_WATER_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL));
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
