package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.items.custom.GedriteArrowItem;
import com.gedrite.items.custom.MetalDetectorItem;
import com.gedrite.trims.ModTrimMaterials;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

import static com.gedrite.blocks.ModBlocks.*;
import static com.gedrite.fluids.ModFluids.GEDRITED_WATER_BUCKET;

public class ModItems {
    /// materials
    public static final Item GEDRITE_INGOT = registerItem("gedrite_ingot", properties ->
            new Item(properties.food(ModFoodComponents.GEDRITE_INGOT, ModFoodComponents.GEDRITE_INGOT_EFFECT).trimMaterial(ModTrimMaterials.GEDRITE)));
    public static final Item RAW_GEDRITE = registerItem("raw_gedrite", Item::new);

    /// tools
    public static final Item METAL_DETECTOR = registerItem("metal_detector", properties ->  new MetalDetectorItem(properties.durability(69).useCooldown(4)));

    /// fuel
    public static final Item GEDRITED_COAL = registerItem("gedrited_coal", Item::new);

    /// weapon
    public static final Item GEDRITE_ARROW = registerItem("gedrite_arrow", GedriteArrowItem::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
    }

    public static final ResourceKey<CreativeModeTab> GEDRITE_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab GEDRITE_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(GEDRITE_INGOT))
            .title(Component.translatable("creativetab.gedrite_tab"))
            .displayItems((params, output) -> {
                output.accept(GEDRITE_INGOT);
                output.accept(RAW_GEDRITE);
                output.accept(GEDRITED_COAL);
                output.accept(GEDRITE_ARROW);
                output.accept(METAL_DETECTOR);
                output.accept(GEDRITED_WATER_BUCKET);

                output.accept(GEDRITE_BLOCK);
                output.accept(RAW_GEDRITE_BLOCK);

                output.accept(GEDRITE_ORE);
                output.accept(DEEPSLATE_GEDRITE_ORE);

                output.accept(BLACK_SAPOTE_LOG);
                output.accept(BLACK_SAPOTE_WOOD);
                output.accept(STRIPPED_BLACK_SAPOTE_LOG);
                output.accept(STRIPPED_BLACK_SAPOTE_WOOD);

                output.accept(BLACK_SAPOTE_PLANKS);
                output.accept(BLACK_SAPOTE_LEAVES);

                output.accept(BLACK_SAPOTE_SAPLING);
            })
            .build();

    public static void registerModItems() {
        Gedrite.LOGGER.debug("Registering items for: " + Gedrite.MOD_ID);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GEDRITE_CREATIVE_TAB_KEY, GEDRITE_CREATIVE_TAB);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(GEDRITE_INGOT);
            output.accept(RAW_GEDRITE);
            output.accept(GEDRITE_ARROW);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(GEDRITE_ORE);
            output.accept(DEEPSLATE_GEDRITE_ORE);
            output.accept(RAW_GEDRITE_BLOCK);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
           output.accept(GEDRITE_BLOCK);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(METAL_DETECTOR);
            output.accept(GEDRITED_WATER_BUCKET);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.accept(GEDRITE_INGOT);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(GEDRITE_INGOT);
        });
    }
}
