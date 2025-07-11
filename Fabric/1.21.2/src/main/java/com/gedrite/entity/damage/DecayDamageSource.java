package com.gedrite.entity.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class DecayDamageSource extends DamageSource {
    public DecayDamageSource(RegistryEntry<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    public DecayDamageSource(RegistryEntry<DamageType> type, Vec3d position) {
        super(type, position);
    }

    public DecayDamageSource(RegistryEntry<DamageType> type, @Nullable Entity attacker) {
        super(type, attacker);
    }

    public DecayDamageSource(RegistryEntry<DamageType> type) {
        super(type);
    }
}