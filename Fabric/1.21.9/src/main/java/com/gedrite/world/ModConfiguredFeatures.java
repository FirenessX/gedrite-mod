package com.gedrite.world;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_SMALL_KEY = registryKey("ore_gedrite_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_BURIED_KEY = registryKey("ore_gedrite_buried");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_LARGE_KEY = registryKey("ore_gedrite_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_MEDIUM_KEY = registryKey("ore_gedrite_medium");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldGedriteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.GEDRITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_GEDRITE_ORE.getDefaultState()));

        register(context, GEDRITE_ORE_SMALL_KEY, Feature.ORE, new OreFeatureConfig(overworldGedriteOres, 4));
        register(context, GEDRITE_ORE_BURIED_KEY, Feature.ORE, new OreFeatureConfig(overworldGedriteOres, 8));
        register(context, GEDRITE_ORE_LARGE_KEY, Feature.ORE, new OreFeatureConfig(overworldGedriteOres, 12));
        register(context, GEDRITE_ORE_MEDIUM_KEY, Feature.ORE, new OreFeatureConfig(overworldGedriteOres, 8));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Gedrite.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>>
    void register(Registerable<ConfiguredFeature<?, ?>> context,
                  RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
