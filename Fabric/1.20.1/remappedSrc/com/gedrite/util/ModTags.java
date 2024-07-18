package com.gedrite.util;

import com.gedrite.Gedrite;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> BLOCKS_WITH_GEDRITE =
                createTag("blocks_with_gedrite");

        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Gedrite.MOD_ID, name));
        }
        public static final TagKey<Block> METAL_BLOCKS_FOR_MD =
                createTag("metal_blocks_for_md");
    }

    public static class Items{
        public static final TagKey<Item> GEDRITE_ITEMS =
                createTag("gedrite_items");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Gedrite.MOD_ID, name));
        }

    }

    //////////  Gedirted Water
    public static final TagKey<Fluid> GEDRITED_WATER = ModTags.of("gedrited_water");
    //////////
    private static TagKey<Fluid> of(String id) {
        return TagKey.of(RegistryKeys.FLUID, new Identifier(id));
    }
    // В вашем классе ModTags добавьте этот метод
    public static boolean isSpecificFluid(Fluid fluid, TagKey<Fluid> tag) {
        return fluid.isIn(tag);
    }

}
