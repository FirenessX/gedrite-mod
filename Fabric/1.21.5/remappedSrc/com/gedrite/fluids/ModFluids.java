package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.fluids.custom.GedritedWaterFluid;
import com.gedrite.util.ModTags;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Iterator;

public class ModFluids {
    public static FlowableFluid GEDRITED_WATER;
    public static FlowableFluid FLOWING_GEDRITED_WATER;
    public static Block GEDRITED_WATER_BLOCK;

    public static Item GEDRITED_WATER_BUCKET;

    public static boolean isGedritedWater(FluidState state) {
        return state.isOf(ModFluids.GEDRITED_WATER) || state.isOf(ModFluids.FLOWING_GEDRITED_WATER);
    }

    public static boolean isTouchingGedritedWater(LivingEntity entity) {
        return entity.updateMovementInFluid(ModTags.Fluids.GEDRITED_WATER, 0.014);
    }


    public static void register() {
        GEDRITED_WATER = Registry.register(Registries.FLUID,
                Identifier.of(Gedrite.MOD_ID, "gedrited_water_still"), new GedritedWaterFluid.Still());

        FLOWING_GEDRITED_WATER = Registry.register(Registries.FLUID,
                Identifier.of(Gedrite.MOD_ID, "gedrited_water_flow"), new GedritedWaterFluid.Flowing());

        GEDRITED_WATER_BLOCK = Registry.register(Registries.BLOCK,
                Identifier.of(Gedrite.MOD_ID, "gedrited_water_block"),
                new FluidBlock(ModFluids.GEDRITED_WATER, AbstractBlock.Settings
                        .copy(Blocks.WATER)
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Gedrite.MOD_ID, "gedrited_water_block")))
                        .mapColor(MapColor.DULL_PINK)) {});


        GEDRITED_WATER_BUCKET = Registry.register(Registries.ITEM,
                Identifier.of(Gedrite.MOD_ID, "gedrited_water_bucket"), new BucketItem(ModFluids.GEDRITED_WATER, new Item.Settings()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Gedrite.MOD_ID, "gedrited_water_bucket")))
                        .recipeRemainder(Items.BUCKET).maxCount(1)));
    }

    static {
        Iterator var0 = Registries.FLUID.iterator();

        while(var0.hasNext()) {
            Fluid fluid = (Fluid)var0.next();
            UnmodifiableIterator var2 = fluid.getStateManager().getStates().iterator();

            while(var2.hasNext()) {
                FluidState fluidState = (FluidState)var2.next();
                Fluid.STATE_IDS.add(fluidState);
            }
        }

    }
}
