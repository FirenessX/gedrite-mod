package com.gedrite.world.level.levelgen.feature.foliageplacers;

import com.gedrite.Gedrite;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerType<P extends FoliagePlacer> {
    public static final FoliagePlacerType<BlackSapoteFoliagePlacer> BLACK_SAPOTE_FOLIAGE_PLACER = register("black_sapote_foliage_placer", BlackSapoteFoliagePlacer.CODEC);
    private final MapCodec<P> codec;

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name), new FoliagePlacerType<P>(codec));
    }

    public static void register() {
        Gedrite.LOGGER.info("Registering Foliage Placer Types for " + Gedrite.MOD_ID);
    }

    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     */
    public ModFoliagePlacerType(MapCodec<P> codec) {
        this.codec = codec;
    }

    public MapCodec<P> codec() {
        return this.codec;
    }
}