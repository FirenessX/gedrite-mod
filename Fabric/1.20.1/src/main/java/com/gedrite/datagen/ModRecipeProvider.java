package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> GEDRITE_SMELTABLES = List.of(ModItems.RAW_GEDRITE, ModBlocks.GEDRITE_ORE, ModBlocks.DEEPSLATE_GEDRITE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT,
                0.7f, 200, "gedrite");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.GEDRITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GEDRITE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_GEDRITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_GEDRITE_BLOCK);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR, 1)
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
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.METAL_DETECTOR)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GEDRITED_COAL, 2)
                .pattern("c c")
                .pattern("###")
                .pattern("c c")
                .input('c', ModItems.GEDRITE_INGOT)
                .input('#', Items.COAL)
                .criterion(hasItem(ModItems.GEDRITE_INGOT), conditionsFromItem(ModItems.GEDRITE_INGOT))
                .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GEDRITED_COAL)));
    }
}
