package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.blocks.cauldron.ModCauldronBehavior;
import com.gedrite.blocks.dispenser.ModDispenserBehavior;
import com.gedrite.effects.ModEffects;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItemGroups;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import com.gedrite.world.ModBiomeModification;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gedrite implements ModInitializer {
	public static final String MOD_ID = "gedrite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModParticleTypes.register();
		ModEffects.registerEffects();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.register();
		ModBiomeModification.register();
		ModCauldronBehavior.registerBehavior();
		ModDispenserBehavior.registerDefaults();
		FuelRegistryEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.GEDRITED_COAL, 3200);
		}));
	}
}