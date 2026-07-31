package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import com.gedrite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(ModTags.Items.GEDRITE_ITEMS)
                .add(ModItems.GEDRITE_INGOT.builtInRegistryHolder().key())
                .add(ModItems.RAW_GEDRITE.builtInRegistryHolder().key())
                .add(ModItems.METAL_DETECTOR.builtInRegistryHolder().key())
                .add(ModItems.GEDRITED_COAL.builtInRegistryHolder().key())
                .add(ModFluids.GEDRITED_WATER_BUCKET.builtInRegistryHolder().key())
                .add(ModItems.GEDRITE_ARROW.builtInRegistryHolder().key())
        ;

        builder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.GEDRITE_INGOT.builtInRegistryHolder().key())
        ;
        builder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACK_SAPOTE_LOG.asItem().builtInRegistryHolder().key())
                .add(ModBlocks.BLACK_SAPOTE_WOOD.asItem().builtInRegistryHolder().key())
                .add(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG.asItem().builtInRegistryHolder().key())
                .add(ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD.asItem().builtInRegistryHolder().key());

        builder(ItemTags.PLANKS)
                .add(ModBlocks.BLACK_SAPOTE_PLANKS.asItem().builtInRegistryHolder().key());
    }
}