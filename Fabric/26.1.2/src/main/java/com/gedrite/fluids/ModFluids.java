package com.gedrite.fluids;

import com.gedrite.Gedrite;
import com.gedrite.effects.ModEffects;
import com.gedrite.fluids.custom.GedritedWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.AABB;

import java.util.function.Function;

public class ModFluids {
    public static FlowingFluid GEDRITED_WATER;
    public static FlowingFluid FLOWING_GEDRITED_WATER;
    public static Block GEDRITED_WATER_BLOCK;

    public static Item GEDRITED_WATER_BUCKET;

    public static boolean isGedritedWater(FluidState state) {
        return state.is(ModFluids.GEDRITED_WATER) || state.is(ModFluids.FLOWING_GEDRITED_WATER);
    }

    public static void decayEffect(Entity entity){
        if (entity instanceof LivingEntity livingEntity) {
            if (!livingEntity.hasEffect(ModEffects.DECAY)) {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.DECAY, 60, 0, false, true, true));
            }
        }
    }

    public static void register() {
        GEDRITED_WATER = Registry.register(BuiltInRegistries.FLUID,
                Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_still"), new GedritedWaterFluid.Source());

        FLOWING_GEDRITED_WATER = Registry.register(BuiltInRegistries.FLUID,
                Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrited_water_flow"), new GedritedWaterFluid.Flowing());

        GEDRITED_WATER_BLOCK = registerBlock("gedrited_water_block",
                properties -> new LiquidBlock(ModFluids.GEDRITED_WATER, properties
                        .replaceable()
                        .noCollision()
                        .strength(100.0f)
                        .pushReaction(PushReaction.DESTROY)
                        .noLootTable()
                        .liquid()
                        .sound(SoundType.EMPTY)
                        .mapColor(MapColor.CRIMSON_STEM)));

        GEDRITED_WATER_BUCKET = registerItem("gedrited_water_bucket", properties -> new BucketItem(ModFluids.GEDRITED_WATER, properties
                .craftRemainder(Items.BUCKET).stacksTo(1)));
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, name)))));
    }

    /**
     * Checks if any part of the entity's bounding box is in the specified fluid.
     * This is a custom implementation to replace the functionality of updateMovementInFluid for fluid tags.
     * @param entity The living entity to check.
     * @param fluidTag The fluid tag to check against.
     * @return True if any part of the bounding box is in the fluid, false otherwise.
     */
    public static boolean isBoundingBoxInFluid(Entity entity, TagKey<Fluid> fluidTag) {
        AABB boundingBox = entity.getBoundingBox();
        int minX = Mth.floor(boundingBox.minX);
        int minY = Mth.floor(boundingBox.minY);
        int minZ = Mth.floor(boundingBox.minZ);
        int maxX = Mth.ceil(boundingBox.maxX);
        int maxY = Mth.ceil(boundingBox.maxY);
        int maxZ = Mth.ceil(boundingBox.maxZ);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    FluidState fluidState = entity.level().getFluidState(pos);
                    if (fluidState.is(fluidTag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
