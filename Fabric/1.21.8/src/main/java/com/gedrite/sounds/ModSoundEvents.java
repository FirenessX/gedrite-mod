package com.gedrite.sounds;

import com.gedrite.Gedrite;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent METAL_DETECTOR_USE = registerSoundEvent("metal_detector_use");

    private static SoundEvent registerSoundEvent(String id) {
        Identifier identifier = Identifier.of(Gedrite.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {
        Gedrite.LOGGER.info("Registering Mod Sounds for " + Gedrite.MOD_ID);
    }
}