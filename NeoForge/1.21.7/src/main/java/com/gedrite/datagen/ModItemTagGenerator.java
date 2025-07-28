package com.gedrite.datagen;

import com.gedrite.Gedrite;
import com.gedrite.items.ModItems;
import com.gedrite.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, modId);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.GEDRITE_ITEMS)
                .add(ModItems.GEDRITE_INGOT.get(), ModItems.RAW_GEDRITE.get(), ModItems.GEDRITED_COAL.get(), ModItems.METAL_DETECTOR.get());
    }
}
