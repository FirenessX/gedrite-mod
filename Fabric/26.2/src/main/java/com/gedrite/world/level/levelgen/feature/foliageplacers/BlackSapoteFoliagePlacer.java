package com.gedrite.world.level.levelgen.feature.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class BlackSapoteFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<BlackSapoteFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> foliagePlacerParts(instance)
                    .and(Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height))
                    .apply(instance, BlackSapoteFoliagePlacer::new)
    );
    protected final int height;

    public BlackSapoteFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerType.BLACK_SAPOTE_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        int leafHeight = foliageAttachment.doubleTrunk() ? foliageHeight : 1 + random.nextInt(1); // <- 2
        for (int yo = offset; yo >= offset - leafHeight; --yo) {
            int currentRadius = leafRadius + foliageAttachment.radiusOffset() + 1 - yo;
            this.placeLeavesRow(level, foliageSetter, random, config, foliageAttachment.pos(), currentRadius, yo, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        if (dx + dz >= 7) {
            return true;
        }
        return dx * dx + dz * dz > currentRadius * currentRadius;
    }
}
