package com.gedrite.world.effects;

import com.gedrite.Gedrite;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Gedrite.MOD_ID);

    public static final RegistryObject<MobEffect> DECAY =
        MOB_EFFECTS.register("decay", () -> new DecayEffect(MobEffectCategory.HARMFUL, 0xf722d7));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
