package com.gedrite.blocks.dispenser;

import com.gedrite.fluids.ModFluids;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FluidModificationItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface ModDispenserBehavior {
    static void registerDefaults() {
//        ItemDispenserBehavior dispenserBehavior = new ItemDispenserBehavior(){
//            private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();
//
//            @Override
//            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
//                FluidModificationItem fluidModificationItem = (FluidModificationItem) stack.getItem();
//                BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
//                ServerWorld world = pointer.getWorld();
//                if (fluidModificationItem.placeFluid(null, world, blockPos, null)) {
//                    fluidModificationItem.onEmptied(null, world, stack, blockPos);
//                    return new ItemStack(Items.BUCKET);
//                }
//                return this.fallbackBehavior.dispense(pointer, stack);
//            }
//        };
        DispenserBehavior dispenserBehavior = new ItemDispenserBehavior() {
            private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();

            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                FluidModificationItem fluidModificationItem = (FluidModificationItem)stack.getItem();
                BlockPos blockPos = pointer.pos().offset((Direction)pointer.state().get(DispenserBlock.FACING));
                World world = pointer.world();
                if (fluidModificationItem.placeFluid((PlayerEntity)null, world, blockPos, (BlockHitResult)null)) {
                    fluidModificationItem.onEmptied((PlayerEntity)null, world, stack, blockPos);
                    return this.decrementStackWithRemainder(pointer, stack, new ItemStack(Items.BUCKET));
                } else {
                    return this.fallbackBehavior.dispense(pointer, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(ModFluids.GEDRITED_WATER_BUCKET, dispenserBehavior);
    }
}
