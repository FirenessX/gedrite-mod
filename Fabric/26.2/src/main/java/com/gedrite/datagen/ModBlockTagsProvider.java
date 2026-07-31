package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .add(ModBlocks.GEDRITE_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.RAW_GEDRITE_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.GEDRITE_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE.builtInRegistryHolder().key());

        builder(ModTags.Blocks.METAL_BLOCKS_FOR_MD)
                .forceAddTag(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .add(Blocks.IRON_BLOCK.builtInRegistryHolder().key())
                .add(Blocks.RAW_IRON_BLOCK.builtInRegistryHolder().key())
                .add(Blocks.GOLD_BLOCK.builtInRegistryHolder().key())
                .add(Blocks.RAW_GOLD_BLOCK.builtInRegistryHolder().key())
//                .add(Blocks.COPPER_BLOCK.builtInRegistryHolder().key())
                .add(Blocks.RAW_COPPER_BLOCK.builtInRegistryHolder().key());

        builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GEDRITE_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.RAW_GEDRITE_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.GEDRITE_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.GEDRITED_WATER_CAULDRON.builtInRegistryHolder().key());

        builder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_GEDRITE_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.GEDRITE_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE.builtInRegistryHolder().key());

        builder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GEDRITE_BLOCK.builtInRegistryHolder().key());

        builder(BlockTags.NEEDS_DIAMOND_TOOL);

        builder(BlockTags.LOGS)
                .add(ModBlocks.BLACK_SAPOTE_LOG.builtInRegistryHolder().key())
                .add(ModBlocks.BLACK_SAPOTE_WOOD.builtInRegistryHolder().key())
                .add(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG.builtInRegistryHolder().key())
                .add(ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD.builtInRegistryHolder().key());

        builder(TagKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath("fabric", "needs_tool_level_4")));
    }
}