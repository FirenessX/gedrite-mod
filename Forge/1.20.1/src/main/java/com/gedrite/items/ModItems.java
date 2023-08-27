package com.gedrite.items;


import com.gedrite.Gedrite;
import com.gedrite.items.custom.FuelItem;
import com.gedrite.items.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, Gedrite.MOD_ID);

    public static final RegistryObject<Item> GEDRITE_INGOT = ITEMS.register("gedrite_ingot",
            () -> new Item(new Item.Properties().food(ModFoodComponents.GEDRITE_INGOT)));

    public static final RegistryObject<Item> RAW_GEDRITE = ITEMS.register("raw_gedrite",
           () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEDRITED_COAL = ITEMS.register("gedrited_coal",
           () -> new FuelItem(new Item.Properties(), 3200));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
           () -> new MetalDetectorItem(new Item.Properties().durability(9)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
