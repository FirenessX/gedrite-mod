package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GEDRITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Gedrite.MOD_ID, "cum"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gedrite")).icon(
                    () -> new ItemStack(ModItems.GEDRITE_INGOT)).entries((displayContext, entries) -> {
                entries.add(ModItems.GEDRITE_INGOT);
                entries.add(ModItems.RAW_GEDRITE);
                entries.add(ModItems.GEDRITED_COAL);
                entries.add(ModItems.METAL_DETECTOR);
                entries.add(ModFluids.GEDRITED_WATER_BUCKET);
                entries.add(ModBlocks.GEDRITE_BLOCK);
                entries.add(ModBlocks.RAW_GEDRITE_BLOCK);
                entries.add(ModBlocks.GEDRITE_ORE);
                entries.add(ModBlocks.DEEPSLATE_GEDRITE_ORE);
            }).build());

    public static void registerItemGroups(){
        Gedrite.LOGGER.info("Registering Item Groups for " + Gedrite.MOD_ID);
    }
}
