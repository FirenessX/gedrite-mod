package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.GEDRITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_GEDRITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GEDRITED_COAL.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createTrivialCube(ModBlocks.GEDRITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_GEDRITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.RAW_GEDRITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GEDRITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GEDRITED_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GEDRITED_WATER_CAULDRON.get());
    }
}
