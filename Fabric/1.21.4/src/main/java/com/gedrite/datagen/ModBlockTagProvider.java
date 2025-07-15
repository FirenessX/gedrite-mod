package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.BLOCKS_WITH_GEDRITE)
                .add(ModBlocks.GEDRITE_BLOCK)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE);

        getOrCreateTagBuilder(ModTags.Blocks.METAL_BLOCKS_FOR_MD)
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

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GEDRITE_BLOCK)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_GEDRITE_BLOCK)
                .add(ModBlocks.GEDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_GEDRITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GEDRITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, Identifier.of("fabric", "needs_tool_level_4")));
    }
}
