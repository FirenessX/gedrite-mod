package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.blocks.cauldron.ModCauldronBehavior;
import com.gedrite.blocks.dispenser.ModDispenserBehavior;
import com.gedrite.effects.ModEffects;
import com.gedrite.entity.ModEntities;
import com.gedrite.entity.damage.ModDamageTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItemGroups;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.trims.ModTrimMaterials;
import com.gedrite.world.ModBiomeModification;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
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