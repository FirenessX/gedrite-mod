package com.gedrite.blocks;


import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Gedrite.MODID);

    public static final DeferredBlock<Block> GEDRITE_BLOCK = registerBlock("gedrite_block",
            (properties) -> new ColoredFallingBlock(new ColorRGBA(15280585), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).speedFactor(0.6F).jumpFactor(0.75F).sound(SoundType.HONEY_BLOCK)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrite_block")))));

    public static final DeferredBlock<Block> RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.CORAL_BLOCK)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "raw_gedrite_block")))));

    public static final DeferredBlock<Block> GEDRITE_ORE = registerBlock("gedrite_ore",
            (properties) -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrite_ore")))));

    public static final DeferredBlock<Block> DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            (properties) -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "deepslate_gedrite_ore")))));

    public static final DeferredBlock<LiquidBlock> GEDRITED_WATER_BLOCK = registerBlock("gedrited_water_block",
            (properties) -> new LiquidBlock(ModFluids.SOURCE_GEDRITED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrited_water_block")))));

//    public static final DeferredBlock<LiquidBlock> TEST_FLUID_BLOCK = registerBlock("test_fluid_block",
//            (properties) -> new LiquidBlock(ModFluids.SOURCE_TEST, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
//                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "test_fluid_block")))));

    public static final DeferredBlock<Block> GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            (properties) -> new GedritedWaterCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrited_water_cauldron")))));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}