//package com.gedrite.items;
//
//import com.gedrite.Gedrite;
//import com.gedrite.blocks.ModBlocks;
//import com.gedrite.fluids.ModFluids;
//import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
//import net.minecraft.core.Registry;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.resources.Identifier;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.ItemStack;
//import org.w3c.dom.Text;
//
//public class ModItemGroups {
//    public static final CreativeModeTab GEDRITE_GROUP = Registry.register(Registries.ITEM_GROUP,
//            Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite"),
//            FabricCreativeModeTab.builder().build(Text.translatable("itemgroup.gedrite")).icon(
//                    () -> new ItemStack(ModItems.GEDRITE_INGOT)).entries((displayContext, entries) -> {
//                entries.add(ModItems.GEDRITE_INGOT);
//                entries.add(ModItems.RAW_GEDRITE);
//                entries.add(ModItems.GEDRITED_COAL);
//                entries.add(ModItems.GEDRITE_ARROW);
//                entries.add(ModItems.METAL_DETECTOR);
//                entries.add(ModFluids.GEDRITED_WATER_BUCKET);
//                entries.add(ModBlocks.GEDRITE_BLOCK);
//                entries.add(ModBlocks.RAW_GEDRITE_BLOCK);
//                entries.add(ModBlocks.GEDRITE_ORE);
//                entries.add(ModBlocks.DEEPSLATE_GEDRITE_ORE);
//            }).build());
//
//    public static void registerItemGroups(){
//        Gedrite.LOGGER.info("Registering Item Groups for " + Gedrite.MOD_ID);
//    }
//}
