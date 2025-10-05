package com.gedrite.world.entity;

import com.gedrite.Gedrite;
import com.gedrite.world.entity.projectile.GedriteArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Gedrite.MOD_ID);

    public static final ResourceKey<EntityType<?>> GEDRITE_ARROW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("gedrite_arrow"));

    public static final Supplier<EntityType<GedriteArrow>> GEDRITE_ARROW =
            ENTITY_TYPES.register("gedrite_arrow", () -> EntityType.Builder.<GedriteArrow>of(GedriteArrow::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(GEDRITE_ARROW_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}