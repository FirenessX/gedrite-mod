package com.gedrite.entity.damage;

import com.gedrite.Gedrite;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public interface ModDamageTypes {
    RegistryKey<DamageType> DECAY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Gedrite.MOD_ID, "decay"));

    static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}