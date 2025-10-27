package com.gedrite;

import com.gedrite.datagen.*;
import com.gedrite.trims.ModTrimMaterials;
import com.gedrite.world.ModConfiguredFeatures;
import com.gedrite.world.ModPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class GedriteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

//		pack.addProvider(ModBlockTagProvider::new));
		pack.addProvider(ModItemTagProvider::new);
//		pack.addProvider(ModLootTableGenerator::new);
//		pack.addProvider(ModRecipeProvider::new);          // ERROR
//		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	private static <T extends DataProvider> DataProvider.Factory<T> toFactory(
			BiFunction<DataOutput, CompletableFuture<RegistryWrapper.WrapperLookup>, T> baseFactory, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		return output -> (T)baseFactory.apply(output, registriesFuture);
	}
	
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder){
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeature::bootStrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
	}
}
