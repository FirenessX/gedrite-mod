package com.gedrite.datagen;

import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import com.gedrite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        valueLookupBuilder(ModTags.Items.GEDRITE_ITEMS)
                .add(ModItems.GEDRITE_INGOT)
                .add(ModItems.RAW_GEDRITE)
                .add(ModItems.METAL_DETECTOR)
                .add(ModItems.GEDRITED_COAL)
                .add(ModFluids.GEDRITED_WATER_BUCKET);
    }
}
