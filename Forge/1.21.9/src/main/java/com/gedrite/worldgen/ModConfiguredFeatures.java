package com.gedrite.worldgen;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_SMALL_KEY = registerKey("ore_gedrite_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_BURIED_KEY = registerKey("ore_gedrite_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_LARGE_KEY = registerKey("ore_gedrite_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_MEDIUM_KEY = registerKey("ore_gedrite_medium");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldGedriteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.GEDRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_GEDRITE_ORE.get().defaultBlockState()));

//        register(context, OVERWORLD_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 9));
//        register(context, NETHER_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
//                ModBlocks.NETHER_SAPPHIRE_ORE.get().defaultBlockState(), 9));
//        register(context, END_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
//                ModBlocks.END_STONE_SAPPHIRE_ORE.get().defaultBlockState(), 9));
        register(context, GEDRITE_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 4));
        register(context, GEDRITE_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 8));
        register(context, GEDRITE_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 12));
        register(context, GEDRITE_ORE_MEDIUM_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 8));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}