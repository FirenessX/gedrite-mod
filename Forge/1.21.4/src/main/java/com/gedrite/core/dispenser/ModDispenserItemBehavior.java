package com.gedrite.core.dispenser;

import com.gedrite.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.BlockHitResult;

public interface ModDispenserItemBehavior {
    static void registerBehavior() {
//        DispenseItemBehavior dispenseitembehavior = new DefaultDispenseItemBehavior() {
//            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
//
//            /**
//             * Dispense the specified stack, play the dispense sound, and spawn particles.
//             */
//            public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
//                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)p_123562_.getItem();
//                BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
//                Level level = p_123561_.getLevel();
//                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null, p_123562_)) {
//                    dispensiblecontaineritem.checkExtraContent((Player)null, level, p_123562_, blockpos);
//                    return new ItemStack(Items.BUCKET);
//                } else {
//                    return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
//                }
//            }
//        };
//        DispenserBlock.registerBehavior(ModItems.GEDRITED_WATER_BUCKET.get(), dispenseitembehavior);
        DispenseItemBehavior dispenseitembehavior1 = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource p_333645_, ItemStack p_333855_) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)p_333855_.getItem();
                BlockPos blockpos = p_333645_.pos().relative((Direction)p_333645_.state().getValue(DispenserBlock.FACING));
                Level level = p_333645_.level();
                if (dispensiblecontaineritem.emptyContents((Player)null, level, blockpos, (BlockHitResult)null, p_333855_)) {
                    dispensiblecontaineritem.checkExtraContent((Player)null, level, p_333855_, blockpos);
                    return this.consumeWithRemainder(p_333645_, p_333855_, new ItemStack(Items.BUCKET));
                } else {
                    return this.defaultDispenseItemBehavior.dispense(p_333645_, p_333855_);
                }
            }
        };
        DispenserBlock.registerBehavior(ModItems.GEDRITED_WATER_BUCKET.get(), dispenseitembehavior1);

    }
}
