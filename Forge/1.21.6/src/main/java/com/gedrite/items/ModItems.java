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

import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, Gedrite.MOD_ID);

    public static final RegistryObject<Item> GEDRITE_INGOT = ITEMS.register("gedrite_ingot",
            () -> new Item((new Item.Properties()).food(ModFoods.GEDRITE_INGOT, ModFoods.GEDRITE_INGOT_EFFECT)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite_ingot")))));

    public static final RegistryObject<Item> RAW_GEDRITE = ITEMS.register("raw_gedrite",
           () -> new Item(new Item.Properties()
                   .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "raw_gedrite")))));

    public static final RegistryObject<Item> GEDRITED_COAL = ITEMS.register("gedrited_coal",
           () -> new FuelItem(new Item.Properties()
                   .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_coal"))), 3200));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
           () -> new MetalDetectorItem(new Item.Properties().durability(9)
                   .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "metal_detector")))));

    public static final RegistryObject<Item> GEDRITED_WATER_BUCKET = ITEMS.register("gedrited_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_GEDRITED_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_bucket")))));


    public static void register(BusGroup busGroup) {
        ITEMS.register(busGroup);
    }
}
