package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
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
        oreSmelting(GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 200, "gedrite");
        oreBlasting(GEDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GEDRITE_INGOT.get(), 0.7f, 100, "gedrite");

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

    public void oreSmelting(List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        this.oreCooking(RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    public void oreBlasting(List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        this.oreCooking(RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    public <T extends AbstractCookingRecipe> void oreCooking(
            RecipeSerializer<T> p_251817_,
            AbstractCookingRecipe.Factory<T> p_312098_,
            List<ItemLike> p_249619_,
            RecipeCategory p_251154_,
            ItemLike p_250066_,
            float p_251871_,
            int p_251316_,
            String p_251450_,
            String p_249236_
    ) {
        for (ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_, p_312098_)
                    .group(p_251450_)
                    .unlockedBy(getHasName(itemlike), this.has(itemlike))
                    .save(this.output, getItemName(p_250066_) + p_249236_ + "_" + getItemName(itemlike));
        }
    }
}
