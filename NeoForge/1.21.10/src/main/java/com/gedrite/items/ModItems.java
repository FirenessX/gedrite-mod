package com.gedrite.items;


import com.gedrite.Gedrite;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.custom.FuelItem;
import com.gedrite.items.custom.GedriteArrowItem;
import com.gedrite.items.custom.MetalDetectorItem;
import com.gedrite.trims.ModTrimMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Gedrite.MOD_ID);

    public static final DeferredItem<Item> GEDRITE_INGOT = ITEMS.registerItem("gedrite_ingot",
            Item::new, properties -> properties.food(ModFoods.GEDRITE_INGOT, ModFoods.GEDRITE_INGOT_EFFECT).trimMaterial(ModTrimMaterials.GEDRITE)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite_ingot"))));


    public static final DeferredItem<Item> RAW_GEDRITE = ITEMS.registerItem("raw_gedrite",
            Item::new, properties -> properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "raw_gedrite"))));

    public static final DeferredItem<Item> GEDRITED_COAL = ITEMS.registerItem("gedrited_coal",
            (properties) -> new FuelItem(properties, 3200), properties -> properties
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_coal"))));

    public static final DeferredItem<MetalDetectorItem> METAL_DETECTOR = ITEMS.registerItem("metal_detector",
            MetalDetectorItem::new, properties -> properties.stacksTo(1).durability(69).useCooldown(4)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "metal_detector"))));

    public static final DeferredItem<Item> GEDRITED_WATER_BUCKET = ITEMS.registerItem("gedrited_water_bucket",
            (properties) -> new BucketItem(ModFluids.SOURCE_GEDRITED_WATER.get(), properties.stacksTo(1).craftRemainder(Items.BUCKET)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_bucket")))));

    public static final DeferredItem<Item> GEDRITE_ARROW = ITEMS.registerItem("gedrite_arrow",
            GedriteArrowItem::new, properties -> properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite_arrow"))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
