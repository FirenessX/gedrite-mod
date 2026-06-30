package com.gedrite.sounds;

import com.gedrite.Gedrite;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static final SoundEvent METAL_DETECTOR_USE = registerSoundEvent("metal_detector_use");

    private static SoundEvent registerSoundEvent(String id) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void registerSounds() {
        Gedrite.LOGGER.info("Registering Mod Sounds for " + Gedrite.MOD_ID);
    }
}