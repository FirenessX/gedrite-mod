package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.include.com.google.common.collect.ImmutableList;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final ImmutableList<ItemLike> GEDRITE_SMELTABLES = ImmutableList.of(ModItems.RAW_GEDRITE, ModBlocks.GEDRITE_ORE, ModBlocks.DEEPSLATE_GEDRITE_ORE);

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                oreSmelting(GEDRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.GEDRITE_INGOT,
                        0.7f, 200, "gedrite");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.GEDRITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GEDRITE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_GEDRITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_GEDRITE_BLOCK);
                shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR, 1)
                        .pattern("lcl")
                        .pattern("igi")
                        .pattern(" i ")
                        .define('c', ModBlocks.GEDRITE_BLOCK)
                        .define('i', Items.IRON_INGOT)
                        .define('g', Items.GOLD_INGOT)
                        .define('l', Items.GLOWSTONE_DUST)
                        .unlockedBy("has_gedrite_block", has(ModBlocks.GEDRITE_BLOCK))
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                        .unlockedBy("has_glowstone_dust", has(Items.GLOWSTONE_DUST))
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.GEDRITED_COAL, 2)
                        .pattern("c c")
                        .pattern("###")
                        .pattern("c c")
                        .define('c', ModItems.GEDRITE_INGOT)
                        .define('#', Items.COAL)
                        .unlockedBy("has_gedrite_ingot", has(ModItems.GEDRITE_INGOT))
                        .unlockedBy("has_coal", has(Items.COAL))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return Gedrite.MOD_ID;
    }
}
