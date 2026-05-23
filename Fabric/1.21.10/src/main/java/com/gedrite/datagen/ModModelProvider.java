package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_GEDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEDRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GEDRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEDRITED_WATER_CAULDRON);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GEDRITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GEDRITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.GEDRITED_COAL, Models.GENERATED);
        itemModelGenerator.register(ModFluids.GEDRITED_WATER_BUCKET, Models.GENERATED);
    }
}
