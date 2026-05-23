package com.gedrite.sounds;

import com.gedrite.Gedrite;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Gedrite.MOD_ID);

    public static RegistryObject<SoundEvent> METAL_DETECTOR_USE = registerSoundEvent("metal_detector_use");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)));
    }
    public static void register(BusGroup busGroup) {
        SOUND_EVENTS.register(busGroup);
    }
}