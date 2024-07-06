package com.gedrite.blocks.cauldron;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface ModCauldronBehavior {
    Map<Item, CauldronBehavior> GEDRITED_WATER_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    CauldronBehavior FILL_WITH_GEDRITED_WATER = (state, world, pos, player, hand, stack) ->
            CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.GEDRITED_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY);

    static void registerBehavior() {
        GEDRITED_WATER_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModFluids.GEDRITED_WATER_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL));
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        GEDRITED_WATER_CAULDRON_BEHAVIOR.put(ModFluids.GEDRITED_WATER_BUCKET, FILL_WITH_GEDRITED_WATER);
        GEDRITED_WATER_CAULDRON_BEHAVIOR.put(Items.WATER_BUCKET,  CauldronBehavior.FILL_WITH_WATER);
        GEDRITED_WATER_CAULDRON_BEHAVIOR.put(Items.LAVA_BUCKET,  CauldronBehavior.FILL_WITH_LAVA);
        GEDRITED_WATER_CAULDRON_BEHAVIOR.put(Items.POWDER_SNOW_BUCKET,  CauldronBehavior.FILL_WITH_POWDER_SNOW);
    }
}
