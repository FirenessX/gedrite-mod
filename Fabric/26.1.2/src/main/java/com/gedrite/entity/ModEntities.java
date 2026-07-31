package com.gedrite.entity;

import com.gedrite.Gedrite;
import com.gedrite.entity.custom.projectile.GedriteArrow;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<GedriteArrow> GEDRITE_ARROW = register("gedrite_arrow",
            EntityType.Builder.<GedriteArrow>of(GedriteArrow::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntities() {
        Gedrite.LOGGER.debug("Registering entities for: " + Gedrite.MOD_ID);
    }
}
