package com.gedrite.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface ParticleResourcesAccessor {
    @Accessor
    ParticleResources getParticleResources();
}
