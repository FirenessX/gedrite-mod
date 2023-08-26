package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup GEDRITE;

    public static void registerItemGroups() {
        GEDRITE = FabricItemGroup.builder(new Identifier(Gedrite.MOD_ID, "gedrite"))
                .displayName(Text.translatable("itemgroup.gedrite"))
                .icon(() -> new ItemStack(ModItems.GEDRITE_INGOT)).build();
    }
}

