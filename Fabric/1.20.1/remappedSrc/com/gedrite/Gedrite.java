package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.blocks.cauldron.ModCauldronBehavior;
import com.gedrite.blocks.dispenser.ModDispenserBehavior;
import com.gedrite.effects.ModEffects;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItemGroups;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gedrite implements ModInitializer {
	public static final String MOD_ID = "gedrite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<PlacedFeature> GEDRITE_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("gedrite","gedrite_ore"));

	@Override
	public void onInitialize() {
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, GEDRITE_ORE_PLACED_KEY);
		ModParticleTypes.register();
		ModEffects.registerEffects();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.register();
		ModCauldronBehavior.registerBehavior();
		ModDispenserBehavior.registerDefaults();
		FuelRegistry.INSTANCE.add(ModItems.GEDRITED_COAL, 3200);

		FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER,
				new SimpleFluidRenderHandler(
						new Identifier("gedrite:block/gedrited_water_still"),
						new Identifier("gedrite:block/gedrited_water_flow"),
						0xf722d7
				));

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
				ModFluids.GEDRITED_WATER, ModFluids.FLOWING_GEDRITED_WATER);
	}
}