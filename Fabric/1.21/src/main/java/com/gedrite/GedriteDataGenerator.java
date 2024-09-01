package com.gedrite;

import com.gedrite.datagen.*;
import com.gedrite.world.ModConfiguredFeatures;
import com.gedrite.world.ModPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class GedriteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

//		pack.addProvider(ModBlockTagProvider::new);
//		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableGenerator::new);
//		pack.addProvider(ModRecipeProvider::new);
//		pack.addProvider(ModModelProvider::new);
//		pack.addProvider(ModWorldGenerator::new);

	}
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder){
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeature::bootStrap);
	}
}
