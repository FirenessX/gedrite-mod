package com.gedrite.entity;

import com.gedrite.Gedrite;
import com.gedrite.entity.custom.projectile.GedriteArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<GedriteArrowEntity> GEDRITE_ARROW = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Gedrite.MOD_ID, "gedrite_arrow"),
            EntityType.Builder.<GedriteArrowEntity>create(GedriteArrowEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("gedrite_arrow"))));

    public static void registerModEntities() {
        Gedrite.LOGGER.debug("Registering entities for: " + Gedrite.MOD_ID);
    }
}
