package com.gedrite.entity.damage;

import net.minecraft.entity.damage.DamageSources;
import net.minecraft.registry.DynamicRegistryManager;

public class ModDamageSources extends DamageSources {
    public ModDamageSources(DynamicRegistryManager registryManager) {
        super(registryManager);
    }
}
