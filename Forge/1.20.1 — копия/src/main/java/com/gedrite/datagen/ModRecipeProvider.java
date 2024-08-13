package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModCreativeModTabs;
import com.gedrite.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;


import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> GEDRITE_SMELTABLES = List.of(
            ModItems.RAW_GEDRITE.get(),
            ModBlocks.GEDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 200, "gedrite");
        oreBlasting(pWriter, GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 100, "gedrite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GEDRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 9)
                .requires(ModBlocks.GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RAW_GEDRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_GEDRITE.get()), has(ModItems.RAW_GEDRITE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_GEDRITE.get(), 9)
                .requires(ModBlocks.RAW_GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_GEDRITE_BLOCK.get()), has(ModBlocks.RAW_GEDRITE_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEDRITED_COAL.get())
                .pattern("g g")
                .pattern("###")
                .pattern("g g")
                .define('g', ModItems.GEDRITE_INGOT.get())
                .define('#', Items.COAL)
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR.get())
                .pattern("l#l")
                .pattern("igi")
                .pattern(" i ")
                .define('#', ModBlocks.GEDRITE_BLOCK.get())
                .define('l', Items.GLOWSTONE_DUST)
                .define('i', Items.IRON_INGOT)
                .define('g', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Gedrite.MOD_ID + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
