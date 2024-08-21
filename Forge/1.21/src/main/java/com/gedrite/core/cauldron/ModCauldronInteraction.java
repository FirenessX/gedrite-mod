package com.gedrite.core.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;

public interface ModCauldronInteraction {
//    Map<Item, CauldronInteraction> GEDRITED_WATER = CauldronInteraction.newInteractionMap();
//    CauldronInteraction FILL_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
//            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);

    CauldronInteraction.InteractionMap GEDRITED_WATER = CauldronInteraction.newInteractionMap("gedrited_water");
    CauldronInteraction FILL_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);


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
        mapE.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
        mapL.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
        mapW.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
        mapS.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
        mapGW.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
        mapGW.put(Items.WATER_BUCKET,  CauldronInteraction.FILL_WATER);
        mapGW.put(Items.LAVA_BUCKET,  CauldronInteraction.FILL_LAVA);
        mapGW.put(Items.POWDER_SNOW_BUCKET,  CauldronInteraction.FILL_POWDER_SNOW);
    }

    static void addDefaultInteractions(Map<Item, CauldronInteraction> pInteractionsMap) {
        pInteractionsMap.put(ModItems.GEDRITED_WATER_BUCKET.get(), FILL_GEDRITED_WATER);
    }
}
