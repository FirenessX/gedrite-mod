package com.gedrite.blocks;

import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block GEDRITE_BLOCK = registerBlock("gedrite_block",
            properties -> new ColoredFallingBlock(new ColorRGBA(15280585), properties
                    .sound(SoundType.HONEY_BLOCK)
                    .mapColor(DyeColor.MAGENTA)
                    .speedFactor(0.6f)
                    .jumpFactor(0.75f)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)));

    public static final Block RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            properties -> new Block(properties
                    .strength(3.0f, 3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CORAL_BLOCK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(DyeColor.PURPLE)));

    public static final Block GEDRITE_ORE = registerBlock("gedrite_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0), properties
                    .strength(3.0f, 3.0f)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.STONE)));

    public static final Block DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0), properties
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)));

    public static final Block GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            properties -> new GedritedWaterCauldronBlock(properties
                    .mapColor(MapColor.STONE)
                    .strength(2.0F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name), toRegister);
    }

//    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
//        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name), function.apply(BlockBehaviour.Properties.of()
//                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
//    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Gedrite.LOGGER.info("Registering Mod Blocks for " + Gedrite.MOD_ID);
    }
}