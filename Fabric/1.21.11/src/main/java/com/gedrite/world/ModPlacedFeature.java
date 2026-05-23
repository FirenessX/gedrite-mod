package com.gedrite.world;

import com.gedrite.Gedrite;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeature {
    public static final RegistryKey<PlacedFeature> GEDRITE_ORE_SMALL_PLACED_KEY = registerKey("ore_gedrite");
    public static final RegistryKey<PlacedFeature> GEDRITE_ORE_BURIED_PLACED_KEY = registerKey("ore_gedrite_buried");
    public static final RegistryKey<PlacedFeature> GEDRITE_ORE_LARGE_PLACED_KEY = registerKey("ore_gedrite_large");
    public static final RegistryKey<PlacedFeature> GEDRITE_ORE_MEDIUM_PLACED_KEY = registerKey("ore_gedrite_medium");

    public static void bootStrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, GEDRITE_ORE_SMALL_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GEDRITE_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, GEDRITE_ORE_BURIED_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GEDRITE_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(3, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, GEDRITE_ORE_LARGE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GEDRITE_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(6, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, GEDRITE_ORE_MEDIUM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GEDRITE_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(-4))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Gedrite.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
