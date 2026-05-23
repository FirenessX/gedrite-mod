package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, modId);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .add(ModBlocks.GEDRITE_BLOCK.get(), ModBlocks.RAW_GEDRITE_BLOCK.get(), ModBlocks.GEDRITE_ORE.get(), ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

        this.tag(ModTags.Blocks.METAL_BLOCKS_FOR_MD)
                .addTag(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .addTag(BlockTags.IRON_ORES)
                .addTag(BlockTags.GOLD_ORES)
                .addTag(BlockTags.COPPER_ORES)
                .add(Blocks.IRON_BLOCK, Blocks.RAW_IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.RAW_GOLD_BLOCK, Blocks.COPPER_BLOCK, Blocks.RAW_COPPER_BLOCK);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GEDRITE_BLOCK.get())
                .add(ModBlocks.RAW_GEDRITE_BLOCK.get())
                .add(ModBlocks.GEDRITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GEDRITE_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_GEDRITE_BLOCK.get())
                .add(ModBlocks.GEDRITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
    }
}
