package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Blocks;

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

        blockModelGenerators.woodProvider(ModBlocks.BLACK_SAPOTE_LOG).logWithHorizontal(ModBlocks.BLACK_SAPOTE_LOG).wood(ModBlocks.BLACK_SAPOTE_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG).logWithHorizontal(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG).wood(ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD);

        blockModelGenerators.registerSimpleFlatItemModel(ModBlocks.BLACK_SAPOTE_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.BLACK_SAPOTE_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.BLACK_SAPOTE_SAPLING, ModBlocks.POTTED_BLACK_SAPOTE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.createFlatItemModel(ModItems.GEDRITE_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.RAW_GEDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.METAL_DETECTOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModItems.GEDRITED_COAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.createFlatItemModel(ModFluids.GEDRITED_WATER_BUCKET, ModelTemplates.FLAT_ITEM);
    }
}
