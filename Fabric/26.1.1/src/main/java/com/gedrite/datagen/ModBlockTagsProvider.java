package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .add(ModBlocks.GEDRITE_BLOCK)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE);

        valueLookupBuilder(ModTags.Blocks.METAL_BLOCKS_FOR_MD)
                .forceAddTag(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .add(Blocks.IRON_BLOCK)
                .add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.GOLD_BLOCK)
                .add(Blocks.RAW_GOLD_BLOCK)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.RAW_COPPER_BLOCK);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GEDRITE_BLOCK)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE)
                .add(ModBlocks.GEDRITED_WATER_CAULDRON);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GEDRITE_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        valueLookupBuilder(TagKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath("fabric", "needs_tool_level_4")));
    }
}
