package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;

public class ModRecipeProvider extends RecipeGenerator {
    private static final List<ItemConvertible> GEDRITE_SMELTABLES = List.of(ModItems.RAW_GEDRITE, ModBlocks.GEDRITE_ORE, ModBlocks.DEEPSLATE_GEDRITE_ORE);

    public ModRecipeProvider(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        RegistryEntryLookup<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
        offerSmelting(GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT,
                0.7f, 200, "gedrite");
        offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.GEDRITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GEDRITE_BLOCK);
        offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.RAW_GEDRITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_GEDRITE_BLOCK);
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, ModItems.METAL_DETECTOR, 1)
                .pattern("lcl")
                .pattern("igi")
                .pattern(" i ")
                .input('c', ModBlocks.GEDRITE_BLOCK)
                .input('i', Items.IRON_INGOT)
                .input('g', Items.GOLD_INGOT)
                .input('l', Items.GLOWSTONE_DUST)
                .criterion(hasItem(ModBlocks.GEDRITE_BLOCK), conditionsFromItem(ModBlocks.GEDRITE_BLOCK))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.GEDRITED_COAL, 2)
                .pattern("c c")
                .pattern("###")
                .pattern("c c")
                .input('c', ModItems.GEDRITE_INGOT)
                .input('#', Items.COAL)
                .criterion(hasItem(ModItems.GEDRITE_INGOT), conditionsFromItem(ModItems.GEDRITE_INGOT))
                .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                .offerTo(exporter);
    }
}
