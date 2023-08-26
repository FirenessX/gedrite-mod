package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.items.custom.MetalDetectorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.gedrite.blocks.ModBlocks.*;

public class ModItems {
    ///materials
    public static final Item GEDRITE_INGOT = registerItem("gedrite_ingot", new Item(new FabricItemSettings().food(ModFoodComponents.GEDRITE_INGOT)));
    public static final Item RAW_GEDRITE = registerItem("raw_gedrite", new Item(new FabricItemSettings()));
    ///tools
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(69)));
    ///fuel
    public static final Item GEDRITED_COAL = registerItem("gedrited_coal", new Item(new FabricItemSettings()));

    private static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Gedrite.MOD_ID, id), item);
    }

    public static void addItemsToItemGroup() {
        itemAddToItemGroup(ItemGroups.INGREDIENTS, GEDRITE_INGOT);
        itemAddToItemGroup(ItemGroups.INGREDIENTS, RAW_GEDRITE);
        itemAddToItemGroup(ItemGroups.TOOLS, METAL_DETECTOR);

        blockAddToItemGroup(ItemGroups.BUILDING_BLOCKS, GEDRITE_BLOCK);
        blockAddToItemGroup(ItemGroups.NATURAL, RAW_GEDRITE_BLOCK);


        itemAddToItemGroup(ModItemGroups.GEDRITE, GEDRITE_INGOT);
        itemAddToItemGroup(ModItemGroups.GEDRITE, RAW_GEDRITE);
        itemAddToItemGroup(ModItemGroups.GEDRITE, GEDRITED_COAL);
        itemAddToItemGroup(ModItemGroups.GEDRITE, METAL_DETECTOR);

        blockAddToItemGroup(ModItemGroups.GEDRITE, GEDRITE_BLOCK);
        blockAddToItemGroup(ModItemGroups.GEDRITE, RAW_GEDRITE_BLOCK);
        blockAddToItemGroup(ModItemGroups.GEDRITE, GEDRITE_ORE);
        blockAddToItemGroup(ModItemGroups.GEDRITE, DEEPSLATE_GEDRITE_ORE);
    }

    private static void itemAddToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    private static void blockAddToItemGroup(ItemGroup group, Block block) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(block));
    }

    public static void registerModItems() {
        Gedrite.LOGGER.debug("Registering items for: " + Gedrite.MOD_ID);
        addItemsToItemGroup();
    }
}
