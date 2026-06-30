package com.gedrite.blocks.dispenser;

import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import org.jspecify.annotations.NonNull;

public interface ModDispenserBehavior {
    static void registerDefaults() {
        DispenserBlock.registerProjectileBehavior(ModItems.GEDRITE_ARROW);
        DefaultDispenseItemBehavior filledBucketBehavior = new DefaultDispenseItemBehavior(){
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            public @NonNull ItemStack execute(BlockSource source, ItemStack dispensed) {
                DispensibleContainerItem bucket = (DispensibleContainerItem)((Object)dispensed.getItem());
                BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                ServerLevel level = source.level();
                if (bucket.emptyContents(null, level, target, null)) {
                    bucket.checkExtraContent(null, level, dispensed, target);
                    return this.consumeWithRemainder(source, dispensed, new ItemStack(Items.BUCKET));
                }
                return this.defaultDispenseItemBehavior.dispense(source, dispensed);
            }
        };
        DispenserBlock.registerBehavior(ModFluids.GEDRITED_WATER_BUCKET, filledBucketBehavior);
    }
}
