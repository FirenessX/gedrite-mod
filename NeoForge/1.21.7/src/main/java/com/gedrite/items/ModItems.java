package com.gedrite.items;


import com.gedrite.Gedrite;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.custom.FuelItem;
import com.gedrite.items.custom.MetalDetectorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Gedrite.MODID);

    public static final DeferredItem<Item> GEDRITE_INGOT = ITEMS.registerItem("gedrite_ingot",
            Item::new, new Item.Properties().food(ModFoods.GEDRITE_INGOT, ModFoods.GEDRITE_INGOT_EFFECT)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrite_ingot"))));


    public static final DeferredItem<Item> RAW_GEDRITE = ITEMS.registerItem("raw_gedrite",
            Item::new, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "raw_gedrite"))));


    public static final DeferredItem<Item> GEDRITED_COAL = ITEMS.registerItem("gedrited_coal",
            (properties) -> new FuelItem(properties, 3200), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrited_coal"))));

    public static final DeferredItem<MetalDetectorItem> METAL_DETECTOR = ITEMS.registerItem("metal_detector",
            MetalDetectorItem::new, new Item.Properties().stacksTo(1).durability(9)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "metal_detector"))));

    public static final DeferredItem<Item> GEDRITED_WATER_BUCKET = ITEMS.registerItem("gedrited_water_bucket",
            (properties) -> new BucketItem(ModFluids.SOURCE_GEDRITED_WATER.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MODID, "gedrited_water_bucket")))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
