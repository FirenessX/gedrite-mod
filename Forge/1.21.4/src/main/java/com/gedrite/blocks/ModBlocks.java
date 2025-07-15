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
            () -> new ColoredFallingBlock(new ColorRGBA(15280585), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).speedFactor(0.6F).jumpFactor(0.75F).sound(SoundType.HONEY_BLOCK)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite_block")))));

    public static final RegistryObject<Block> RAW_GEDRITE_BLOCK = registerBlock("raw_gedrite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.CORAL_BLOCK)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "raw_gedrite_block")))));

    public static final RegistryObject<Block> GEDRITE_ORE = registerBlock("gedrite_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite_ore")))));

    public static final RegistryObject<Block> DEEPSLATE_GEDRITE_ORE = registerBlock("deepslate_gedrite_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "deepslate_gedrite_ore")))));

    public static final RegistryObject<LiquidBlock> GEDRITED_WATER_BLOCK = registerBlock("gedrited_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_GEDRITED_WATER, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_block")))));

    public static final RegistryObject<LiquidBlock> TEST_FLUID_BLOCK = registerBlock("test_fluid_block",
            () -> new LiquidBlock(ModFluids.SOURCE_TEST, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "test_fluid_block")))));

    public static final RegistryObject<Block> GEDRITED_WATER_CAULDRON = registerBlock("gedrited_water_cauldron",
            () -> new GedritedWaterCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_cauldron")))));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}