package com.gedrite.util;

import com.gedrite.Gedrite;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BLOCKS_WITH_GEDRITE =
                createTag("blocks_with_gedrite");
        public static final TagKey<Block> METAL_BLOCKS_FOR_MD =
                createTag("metal_blocks_for_md");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEDRITE_ITEMS =
                createTag("gedrite_items");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Gedrite.MOD_ID, name));
        }

    }

    public static class Fluids {
        public static final TagKey<Fluid> GEDRITED_WATER =
                createTag("gedrited_water");

        private static TagKey<Fluid> createTag(String name){
            return TagKey.of(RegistryKeys.FLUID, Identifier.of(Gedrite.MOD_ID, name));
        }
    }
//
//    //////////  Gedirted Water
//    public static final TagKey<Fluid> GEDRITED_WATER = ModTags.of("gedrited_water");
//    //////////
//    private static TagKey<Fluid> of(String id) {
//        return TagKey.of(RegistryKeys.FLUID, Identifier.of(id));
//    }
}
