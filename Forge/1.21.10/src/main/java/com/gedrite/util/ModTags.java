package com.gedrite.util;

import com.gedrite.Gedrite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
    public static class Blocks{
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }

        public static final TagKey<Block> BLOCKS_WITH_GEDRITE =
                createTag("blocks_with_gedrite");
        public static final TagKey<Block> METAL_BLOCKS_FOR_MD =
                createTag("metal_blocks_for_md");
    }

    public static class Items{
        public static final TagKey<Item> GEDRITE_ITEMS =
                createTag("gedrite_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }
    }

    public static class Fluids{
        public static final TagKey<Fluid> GEDRITED_WATER =
                createTag("gedrited_water");

        private static TagKey<Fluid> createTag(String name) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        }
    }

//    //////////  Gedirted Water
//    public static final TagKey<Fluid> GEDRITED_WATER = ModTags.of("gedrited_water");
//    //////////
//    private static TagKey<Fluid> of(String id) {
//        return FluidTags.create(ResourceLocation.fromNamespaceAndPath(id));
//    }
}
