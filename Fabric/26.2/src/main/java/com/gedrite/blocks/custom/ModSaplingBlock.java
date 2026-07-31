package com.gedrite.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class ModSaplingBlock extends SaplingBlock {
    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     *
     * @param treeGrower
     * @param properties
     */
    private final Block placeOn;

    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block placeOn) {
        super(treeGrower, properties);
        this.placeOn = placeOn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(this.placeOn);
    }
}
