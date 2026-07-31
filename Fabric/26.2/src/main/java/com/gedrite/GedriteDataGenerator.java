package com.gedrite;

import com.gedrite.datagen.*;
import com.gedrite.trims.ModTrimMaterials;
import com.gedrite.world.ModConfiguredFeatures;
import com.gedrite.world.ModPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class GedriteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

//		pack.addProvider(ModBlockTagsProvider::new);
//		pack.addProvider(ModItemTagsProvider::new);
//		pack.addProvider(ModLootTableGenerator::new);
//		pack.addProvider(ModRecipeProvider::new);
//		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModWorldGenerator::new);

	}
	
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder){
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeature::bootStrap);
		registryBuilder.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
	}
}