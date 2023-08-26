package com.gedrite.items;


import com.gedrite.Gedrite;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, Gedrite.MOD_ID);

    public static final RegistryObject<Item> GEDRITE_INGOT = ITEMS.register("gedrite_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_GEDRITE = ITEMS.register("raw_gedrite",
           () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
