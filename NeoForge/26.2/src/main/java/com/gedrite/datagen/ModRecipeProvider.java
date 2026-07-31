package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider {
    public static final List<ItemLike> GEDRITE_SMELTABLES = List.of(
            ModItems.RAW_GEDRITE.get(),
            ModBlocks.GEDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

    protected ModRecipeProvider(HolderLookup.Provider p_361709_, RecipeOutput p_365321_) {
        super(p_361709_, p_365321_);
    }


    @Override
    public void buildRecipes() {
        HolderLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
        oreSmelting(GEDRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 200, "gedrite");
        oreBlasting(GEDRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 100, "gedrite");

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, ModBlocks.GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.GEDRITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 9)
                .requires(ModBlocks.GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, ModBlocks.RAW_GEDRITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RAW_GEDRITE.get())
                .unlockedBy(getHasName(ModItems.RAW_GEDRITE.get()), has(ModItems.RAW_GEDRITE.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.MISC, ModItems.RAW_GEDRITE.get(), 9)
                .requires(ModBlocks.RAW_GEDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_GEDRITE_BLOCK.get()), has(ModBlocks.RAW_GEDRITE_BLOCK.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.MISC, ModItems.GEDRITED_COAL.get())
                .pattern("g g")
                .pattern("###")
                .pattern("g g")
                .define('g', ModItems.GEDRITE_INGOT.get())
                .define('#', Items.COAL)
                .unlockedBy(getHasName(ModItems.GEDRITE_INGOT.get()), has(ModItems.GEDRITE_INGOT.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, ModItems.METAL_DETECTOR.get())
                .pattern("l#l")
                .pattern("igi")
                .pattern(" i ")
                .define('#', ModBlocks.GEDRITE_BLOCK.get())
                .define('l', Items.GLOWSTONE_DUST)
                .define('i', Items.IRON_INGOT)
                .define('g', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModBlocks.GEDRITE_BLOCK.get()), has(ModBlocks.GEDRITE_BLOCK.get()))
                .save(output);

    }

    protected void oreSmelting(List<ItemLike> smeltables, RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group) {
        this.oreCooking(SmeltingRecipe::new, smeltables, craftingCategory, cookingCategory, result, experience, cookingTime, group, "_from_smelting");
    }

    protected void oreBlasting(List<ItemLike> smeltables, RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group) {
        this.oreCooking(BlastingRecipe::new, smeltables, craftingCategory, cookingCategory, result, experience, cookingTime, group, "_from_blasting");
    }

}
