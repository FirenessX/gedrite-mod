package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.items.custom.MetalDetectorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
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
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(9)));
    ///fuel
    public static final Item GEDRITED_COAL = registerItem("gedrited_coal", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(GEDRITE_INGOT);
        entries.add(RAW_GEDRITE);
        entries.add(GEDRITED_COAL);
    }

    private static void addItemsToNaturalTabItemGroup(FabricItemGroupEntries entries){
        entries.add(GEDRITE_ORE);
        entries.add(DEEPSLATE_GEDRITE_ORE);
        entries.add(RAW_GEDRITE_BLOCK);
    }

    private static void addItemsToBuildingTabItemGroup(FabricItemGroupEntries entries){
        entries.add(GEDRITE_BLOCK);
    }

    private static void addItemsToToolsTabItemGroup(FabricItemGroupEntries entries){
        entries.add(METAL_DETECTOR);
    }

    private static void addItemsToFoodTabItemGroup(FabricItemGroupEntries entries){
        entries.add(GEDRITE_INGOT);
    }

    private static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Gedrite.MOD_ID, id), item);
    }

    private static Item registerItem(String id, Item item, RegistryKey<ItemGroup> itemGroup){
        Item returnItem = Registry.register(Registries.ITEM, new Identifier(Gedrite.MOD_ID, id), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(returnItem));
        return returnItem;
    }

    public static void registerModItems() {
        Gedrite.LOGGER.debug("Registering items for: " + Gedrite.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToNaturalTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingTabItemGroup);
    }
}
