package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.GEDRITE_BLOCK);
        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.RAW_GEDRITE_BLOCK);
        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.GEDRITE_ORE);
        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.DEEPSLATE_GEDRITE_ORE);
        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.GEDRITED_WATER_CAULDRON);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.createFlatItemModel(ModItems.GEDRITE_INGOT, ModelTemplates.CUBE);
        itemModelGenerators.createFlatItemModel(ModItems.RAW_GEDRITE, ModelTemplates.CUBE);
        itemModelGenerators.createFlatItemModel(ModItems.METAL_DETECTOR, ModelTemplates.CUBE);
        itemModelGenerators.createFlatItemModel(ModItems.GEDRITED_COAL, ModelTemplates.CUBE);
        itemModelGenerators.createFlatItemModel(ModFluids.GEDRITED_WATER_BUCKET, ModelTemplates.CUBE);
    }
}
