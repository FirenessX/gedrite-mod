package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModCreativeModTabs;
import com.gedrite.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> GEDRITE_SMELTABLES = List.of(
            ModItems.RAW_GEDRITE.get(),
            ModBlocks.GEDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

//    public ModRecipeProvider(PackOutput pOutput) {
//        super(pOutput);
//    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        oreSmelting(recipeOutput, GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 200, "gedrite");
        oreBlasting(recipeOutput, GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 100, "gedrite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GEDRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 9)
                .requires(ModBlocks.GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RAW_GEDRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_GEDRITE.get()), has(ModItems.RAW_GEDRITE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_GEDRITE.get(), 9)
                .requires(ModBlocks.RAW_GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_GEDRITE_BLOCK.get()), has(ModBlocks.RAW_GEDRITE_BLOCK.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEDRITED_COAL.get())
                .pattern("g g")
                .pattern("###")
                .pattern("g g")
                .define('g', ModItems.GEDRITE_INGOT.get())
                .define('#', Items.COAL)
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR.get())
                .pattern("l#l")
                .pattern("igi")
                .pattern(" i ")
                .define('#', ModBlocks.GEDRITE_BLOCK.get())
                .define('l', Items.GLOWSTONE_DUST)
                .define('i', Items.IRON_INGOT)
                .define('g', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(recipeOutput);

    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        Iterator var10 = pIngredients.iterator();

        while(var10.hasNext()) {
            ItemLike itemlike = (ItemLike)var10.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pRecipeOutput, getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }

    }
}
