package com.gedrite.util;

import com.gedrite.Gedrite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BLOCKS_WITH_GEDRITE =
                createTag("blocks_with_gedrite");
        public static final TagKey<Block> METAL_BLOCKS_FOR_MD =
                createTag("metal_blocks_for_md");

        private static TagKey<Block> createTag(String name){
            return TagKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEDRITE_ITEMS =
                createTag("gedrite_items");

        private static TagKey<Item> createTag(String name){
            return TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }

    }

    public static class Fluids {
        public static final TagKey<Fluid> GEDRITED_WATER =
                createTag("gedrited_water");

        private static TagKey<Fluid> createTag(String name){
            return TagKey.create(BuiltInRegistries.FLUID.key(), Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }
    }
}
