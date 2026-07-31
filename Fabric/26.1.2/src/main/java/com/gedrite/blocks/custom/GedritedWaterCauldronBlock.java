package com.gedrite.blocks.custom;

import com.gedrite.core.cauldron.ModCauldronInteractions;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GedritedWaterCauldronBlock extends AbstractCauldronBlock {
    public GedritedWaterCauldronBlock(Properties properties) {
        super(properties, ModCauldronInteractions.GEDRITED_WATER);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return null;
    }

    @Override
    public boolean isFull(BlockState state) {
        return false;
    }
}
