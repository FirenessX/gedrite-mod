package com.gedrite.world.effects;

import com.gedrite.Gedrite;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Gedrite.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> DECAY =
            MOB_EFFECTS.register("decay", () -> new DecayEffect(MobEffectCategory.HARMFUL, 0xf722d7));

//    public static Holder<MobEffect> getDecayHolder() {
//        return ForgeRegistries.MOB_EFFECTS.getDelegateOrThrow(DECAY.getId());
//    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
