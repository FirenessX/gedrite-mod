package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.blocks.dispenser.ModDispenserBehavior;
import com.gedrite.core.cauldron.ModCauldronInteractions;
import com.gedrite.effects.ModEffects;
import com.gedrite.entity.ModEntities;
import com.gedrite.entity.damage.ModDamageTypes; // Import ModDamageTypes
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.world.ModBiomeModification;
import com.gedrite.world.level.levelgen.feature.foliageplacers.ModFoliagePlacerType;
import com.gedrite.world.level.levelgen.feature.trunkplacers.ModTrunkPlacerType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gedrite implements ModInitializer {
	public static final String MOD_ID = "gedrite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModParticleTypes.registerModParticles();
		ModSoundEvents.registerSounds();
		ModEntities.registerModEntities();
		ModEffects.registerEffects();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.register();
		ModBiomeModification.register();
		ModTrunkPlacerType.register();
		ModFoliagePlacerType.register();
		ModCauldronInteractions.bootstrap();
		ModDispenserBehavior.registerDefaults();

		FuelValueEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.GEDRITED_COAL, 3200);
		}));

		StrippableBlockRegistry.register(ModBlocks.BLACK_SAPOTE_LOG, ModBlocks.STRIPPED_BLACK_SAPOTE_LOG);
		StrippableBlockRegistry.register(ModBlocks.BLACK_SAPOTE_WOOD, ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACK_SAPOTE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACK_SAPOTE_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACK_SAPOTE_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACK_SAPOTE_LEAVES, 30, 60);
	}
}