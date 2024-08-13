package com.gedrite.blocks.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface ModCauldronBehavior {
    CauldronBehavior.CauldronBehaviorMap GEDRITED_WATER_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("gedrited_water");
    CauldronBehavior FILL_WITH_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
        CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY);

    static void registerBehavior() {
        Map<Item, CauldronBehavior> mapE = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapW = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapL = CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapS = CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR.map();
        Map<Item, CauldronBehavior> mapGW = GEDRITED_WATER_CAULDRON_BEHAVIOR.map();
        mapGW.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModFluids.GEDRITED_WATER_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL));
        mapE.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        mapL.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        mapW.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        mapS.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        mapGW.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        mapGW.put(Items.WATER_BUCKET,  CauldronBehavior.FILL_WITH_WATER);
        mapGW.put(Items.LAVA_BUCKET,  CauldronBehavior.FILL_WITH_LAVA);
        mapGW.put(Items.POWDER_SNOW_BUCKET,  CauldronBehavior.FILL_WITH_POWDER_SNOW);
    }
}
