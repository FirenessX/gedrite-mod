package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.fluids.custom.GedritedWaterFluid;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static FlowableFluid GEDRITED_WATER;
    public static FlowableFluid FLOWING_GEDRITED_WATER;
    public static Block GEDRITED_WATER_BLOCK;

    public static void register() {
        GEDRITED_WATER = Registry.register(Registries.FLUID,
                new Identifier(Gedrite.MOD_ID, "gedrited_water"), new GedritedWaterFluid.Still());
        FLOWING_GEDRITED_WATER = Registry.register(Registries.FLUID,
                new Identifier(Gedrite.MOD_ID, "flowing_gedrited_water"), new GedritedWaterFluid.Flowing());

        GEDRITED_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(Gedrite.MOD_ID, "gedrited_water_block"),
                new FluidBlock(ModFluids.GEDRITED_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()) {
                });
    }
}
