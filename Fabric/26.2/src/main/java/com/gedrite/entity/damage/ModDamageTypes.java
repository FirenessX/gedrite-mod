package com.gedrite.entity.damage;

import com.gedrite.Gedrite;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> DECAY = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "decay"));

    static DamageSource of(ServerLevel serverLevel, ResourceKey<DamageType> key) {
        return new DamageSource(serverLevel.registryAccess().getOrThrow(Registries.DAMAGE_TYPE).value().getOrThrow(key));
    }
}