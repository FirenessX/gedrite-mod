package com.gedrite.world.level.levelgen.feature.trunkplacers;

import com.gedrite.blocks.ModBlocks;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BlackSapoteTrunkPlacer extends GiantTrunkPlacer {
    public static final MapCodec<BlackSapoteTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i ->
            BlackSapoteTrunkPlacer.trunkPlacerParts(i).apply(i, BlackSapoteTrunkPlacer::new));

    public BlackSapoteTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerType.BLACK_SAPOTE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        ArrayList<FoliagePlacer.FoliageAttachment> attachments = Lists.newArrayList();
        attachments.addAll(super.placeTrunk(level, trunkSetter, random, treeHeight, origin, config));

        int woodRootRadius = 1;
        float woodRootChance = 0.7f;

        for (int xOffset = 0; xOffset <= 1 + woodRootRadius; xOffset++) {
            for (int zOffset = -woodRootRadius; zOffset <= 1 + woodRootRadius; zOffset++) {
                BlockPos currentWoodPos = origin.offset(xOffset, 0, zOffset);
                if (random.nextFloat() < woodRootChance) {
                    this.placeWoodRoot(level, trunkSetter, random, currentWoodPos, config,
                            s -> s.isAir() || s.is(BlockTags.REPLACEABLE_BY_TREES));
                }
            }
        }
        for (int branchHeight = treeHeight - 2 - random.nextInt(1); branchHeight > treeHeight/2; branchHeight -= 3 + random.nextInt(1)) {
            float angle = random.nextFloat() * ((float)Math.PI * 2);
            int bx = 0;
            int bz = 0;
            int ibx = 0;
            int ibz = 0;
            for (int b = 1; b < 3; ++b) {
                bx = (int)(1.5f + Mth.cos(angle) * (float)b);
                bz = (int)(1.5f + Mth.sin(angle) * (float)b);
                ibx = (int)(1.5f + Mth.cos(angle) * (float)(b - 1));
                ibz = (int)(1.5f + Mth.sin(angle) * (float)(b - 1));
                BlockPos pos = origin.offset(bx, branchHeight - 3 + b / 2, bz);
                BlockPos ipos = origin.offset(1 - ibx, treeHeight - branchHeight - 2 + b/ 2, 1 - ibz);
                this.placeLog(level, trunkSetter, random, pos, config);
                this.placeLog(level, trunkSetter, random, ipos, config);
            }
            attachments.add(new FoliagePlacer.FoliageAttachment(origin.offset(bx, branchHeight, bz), -2, false));
            attachments.add(new FoliagePlacer.FoliageAttachment(origin.offset(1 - ibx, treeHeight - branchHeight + 1, 1 - ibz), -2, false));
        }
        return attachments;
    }

    private void placeWoodRoot(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Predicate<BlockState> replaceable) {
        if (replaceable.test(level.getBlockState(pos))) {
            trunkSetter.accept(pos, ModBlocks.BLACK_SAPOTE_WOOD.defaultBlockState());
        }
    }
}