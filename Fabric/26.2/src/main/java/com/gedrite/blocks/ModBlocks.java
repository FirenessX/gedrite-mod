package com.gedrite.blocks;

import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import com.gedrite.blocks.custom.ModSaplingBlock;
import com.gedrite.util.ModTags;
import com.gedrite.world.tree.ModTreeGrower;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
//    public static final Block GEDRITE_BLOCK = registerBlock("gedrite_block",
//            properties -> new ColoredFallingBlock(new ColorRGBA(15280585), properties
//                    .sound(SoundType.HONEY_BLOCK)
//                    .mapColor(DyeColor.MAGENTA)
//                    .speedFactor(0.6f)
//                    .jumpFactor(0.75f)
//                    .requiresCorrectToolForDrops()
//                    .strength(1.5f, 3.0f)));

    public static final Block GEDRITE_BLOCK = register("gedrite_block",
            settings -> new ColoredFallingBlock(new ColorRGBA(15280585), settings),
            BlockBehaviour.Properties.of()
                    .sound(SoundType.HONEY_BLOCK)
                    .mapColor(DyeColor.MAGENTA)
                    .speedFactor(0.6f)
                    .jumpFactor(0.75f)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f),
            true);


//    public static final Block RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
//            properties -> new Block(properties
//            .strength(3.0f, 3.0f)
//            .requiresCorrectToolForDrops()
//            .sound(SoundType.CORAL_BLOCK)
//            .instrument(NoteBlockInstrument.BASEDRUM)
//            .mapColor(DyeColor.PURPLE)));

    public static final Block RAW_GEDRITE_BLOCK = register("raw_gedrite_block",
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(3.0f, 3.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CORAL_BLOCK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(DyeColor.PURPLE),
            true);

//    public static final Block GEDRITE_ORE = registerBlock("gedrite_ore",
//            properties -> new DropExperienceBlock(ConstantInt.of(0), properties
//                    .strength(3.0f, 3.0f)
//                    .instrument(NoteBlockInstrument.BASEDRUM)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.STONE)
//                    .mapColor(MapColor.STONE)));

    public static final Block GEDRITE_ORE = register(
            "gedrite_ore",
            settings -> new DropExperienceBlock(ConstantInt.of(0), settings),
            BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.STONE),
            true);

//    public static final Block DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
//            properties -> new DropExperienceBlock(ConstantInt.of(0), properties
//                    .requiresCorrectToolForDrops()
//                    .instrument(NoteBlockInstrument.BASEDRUM)
//                    .strength(4.5f, 3.0f)
//                    .sound(SoundType.DEEPSLATE)
//                    .mapColor(MapColor.DEEPSLATE)));

    public static final Block DEEPSLATE_GEDRITE_ORE = register(
            "deepslate_gedrite_ore",
            settings -> new DropExperienceBlock(ConstantInt.of(0), settings),
            BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE),
            true);


//    public static final Block GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
//            properties -> new GedritedWaterCauldronBlock(properties
//                    .mapColor(MapColor.STONE)
//                    .strength(2.0F)
//                    .noOcclusion()
//                    .pushReaction(PushReaction.DESTROY)));

    public static final Block GEDRITED_WATER_CAULDRON = register(
            "gedrited_water_cauldron",
            GedritedWaterCauldronBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY),
            true);

    public static final Block BLACK_SAPOTE_LOG = register(
            "black_sapote_log",
        RotatedPillarBlock::new,
        Blocks.logProperties(MapColor.COLOR_BROWN, MapColor.COLOR_GREEN, SoundType.WOOD)
                .ignitedByLava(),
        true);

    public static final Block BLACK_SAPOTE_WOOD = register(
            "black_sapote_wood",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .sound(SoundType.WOOD)
                .strength(2.0f)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .ignitedByLava(),
        true);

    public static final Block STRIPPED_BLACK_SAPOTE_LOG = register(
            "stripped_black_sapote_log",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .ignitedByLava(),
        true);

    public static final Block STRIPPED_BLACK_SAPOTE_WOOD = register(
            "stripped_black_sapote_wood",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .sound(SoundType.WOOD)
                .strength(2.0f)
                .instrument(NoteBlockInstrument.BASS)
                .ignitedByLava(),
        true);

    public static final Block BLACK_SAPOTE_PLANKS = register("black_sapote_planks",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            true);

    public static final Block BLACK_SAPOTE_LEAVES = register("black_sapote_leaves",
            settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 9461408), settings),
            Blocks.leavesProperties(SoundType.GRASS)
                    .ignitedByLava(),
            true);

    public static final Block BLACK_SAPOTE_SAPLING = register("black_sapote_sapling",
            settings -> new ModSaplingBlock(ModTreeGrower.BLACK_SAPOTE, settings, Blocks.COBBLESTONE),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CALCITE)
                    .pushReaction(PushReaction.DESTROY),
            true);

    public static final Block POTTED_BLACK_SAPOTE_SAPLING = register("potted_black_sapote_sappling",
            settings -> new FlowerPotBlock(BLACK_SAPOTE_SAPLING, settings),
            Blocks.flowerPotProperties(),
            false);


    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(properties.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
    }

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