package com.gedrite.blocks;

import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block GEDRITE_BLOCK = registerBlock("gedrite_block",
            new FallingBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.HONEY).mapColor(DyeColor.MAGENTA).velocityMultiplier(0.6f).jumpVelocityMultiplier(0.75f)));
    public static final Block RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.CORAL).mapColor(DyeColor.MAGENTA)));
    public static final Block GEDRITE_ORE = registerBlock("gedrite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            new GedritedWaterCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON)));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Gedrite.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Gedrite.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Gedrite.LOGGER.info("Registering Mod Blocks for " + Gedrite.MOD_ID);
    }
}