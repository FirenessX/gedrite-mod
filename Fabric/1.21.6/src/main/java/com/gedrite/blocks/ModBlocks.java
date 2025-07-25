package com.gedrite.blocks;

import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

public class ModBlocks {
    public static final Block GEDRITE_BLOCK = registerBlock("gedrite_block",
            new ColoredFallingBlock(new ColorCode(15280585), AbstractBlock.Settings.copy(Blocks.STONE)
                    .sounds(BlockSoundGroup.HONEY).mapColor(DyeColor.MAGENTA).velocityMultiplier(0.6f).jumpVelocityMultiplier(0.75f)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "gedrite_block")))));
    public static final Block RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.CORAL).mapColor(DyeColor.MAGENTA)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "raw_gedrite_block")))));
    public static final Block GEDRITE_ORE = registerBlock("gedrite_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.copy(Blocks.IRON_ORE)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "gedrite_ore")))));
    public static final Block DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "deepslate_gedrite_ore")))));
    public static final Block GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            new GedritedWaterCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "gedrited_water_cauldron")))));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Gedrite.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(Gedrite.MOD_ID, name), new BlockItem(block, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Gedrite.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Gedrite.LOGGER.info("Registering Mod Blocks for " + Gedrite.MOD_ID);
    }
}