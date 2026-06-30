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
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
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
//		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.register();
		ModBiomeModification.register();
		ModCauldronInteractions.bootstrap();
		ModDispenserBehavior.registerDefaults();

		FuelValueEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.GEDRITED_COAL, 3200);
		}));
	}
}