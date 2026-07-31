package com.gedrite.datagen;

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
        valueLookupBuilder(ModTags.Items.GEDRITE_ITEMS)
                .add(ModItems.GEDRITE_INGOT)
                .add(ModItems.RAW_GEDRITE)
                .add(ModItems.METAL_DETECTOR)
                .add(ModItems.GEDRITED_COAL)
                .add(ModFluids.GEDRITED_WATER_BUCKET)
                .add(ModItems.GEDRITE_ARROW);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.GEDRITE_INGOT);
    }
}
