package com.gedrite.world.level.levelgen.feature.trunkplacers;

import com.gedrite.Gedrite;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerType<P extends TrunkPlacer> {
    public static final TrunkPlacerType<BlackSapoteTrunkPlacer> BLACK_SAPOTE_TRUNK_PLACER = register("black_sapote_trunk_placer", BlackSapoteTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name), new TrunkPlacerType<>(codec));
    }

    public static void register() {
        Gedrite.LOGGER.info("Registering Trunk Placer Types for " + Gedrite.MOD_ID);
    }

}
