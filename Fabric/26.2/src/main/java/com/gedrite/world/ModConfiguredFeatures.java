package com.gedrite.world;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import com.gedrite.world.level.levelgen.feature.foliageplacers.BlackSapoteFoliagePlacer;
import com.gedrite.world.level.levelgen.feature.trunkplacers.BlackSapoteTrunkPlacer;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;


import java.util.List;

import static net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.CAN_PLACE_BELOW_TREE_TRUNKS;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_SMALL_KEY = resourceKey("ore_gedrite_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_BURIED_KEY = resourceKey("ore_gedrite_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_LARGE_KEY = resourceKey("ore_gedrite_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEDRITE_ORE_MEDIUM_KEY = resourceKey("ore_gedrite_medium");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_SAPOTE_KEY = resourceKey("black_sapote");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldGedriteOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.GEDRITE_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_GEDRITE_ORE.defaultBlockState()));

        register(context, GEDRITE_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 4));
        register(context, GEDRITE_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 8));
        register(context, GEDRITE_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 12));
        register(context, GEDRITE_ORE_MEDIUM_KEY, Feature.ORE, new OreConfiguration(overworldGedriteOres, 8));

        BlockStateProvider belowTrunkProvider = RuleBasedStateProvider.ifTrueThenProvide(CAN_PLACE_BELOW_TREE_TRUNKS, Blocks.STONE);

        register(context, BLACK_SAPOTE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLACK_SAPOTE_LOG),
                new BlackSapoteTrunkPlacer(8, 2, 2),
                BlockStateProvider.simple(ModBlocks.BLACK_SAPOTE_LEAVES),
                new BlackSapoteFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 1),
                new TwoLayersFeatureSize(1, 1, 2),
                belowTrunkProvider).ignoreVines().build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}