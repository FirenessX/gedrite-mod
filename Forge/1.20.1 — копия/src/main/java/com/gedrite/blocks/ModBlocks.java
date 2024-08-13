package com.gedrite.blocks;


import com.gedrite.Gedrite;
import com.gedrite.blocks.custom.GedritedWaterCauldronBlock;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, Gedrite.MOD_ID);

    public static final RegistryObject<Block> GEDRITE_BLOCK = registerBlock("gedrite_block",
            () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.STONE).speedFactor(0.6F).jumpFactor(0.75F).sound(SoundType.HONEY_BLOCK)));

    public static final RegistryObject<Block> RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> GEDRITE_ORE = registerBlock("gedrite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<LiquidBlock> GEDRITED_WATER_BLOCK = registerBlock("gedrited_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_GEDRITED_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> TEST_FLUID_BLOCK = registerBlock("test_fluid_block",
            () -> new LiquidBlock(ModFluids.SOURCE_TEST, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            () -> new GedritedWaterCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}