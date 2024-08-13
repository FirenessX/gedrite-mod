package com.gedrite.blocks.custom;

import com.gedrite.blocks.cauldron.ModCauldronBehavior;
import com.gedrite.effects.ModEffects;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LavaCauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GedritedWaterCauldronBlock extends AbstractCauldronBlock {
    public GedritedWaterCauldronBlock(AbstractBlock.Settings settings) {
        super(settings, ModCauldronBehavior.GEDRITED_WATER_CAULDRON_BEHAVIOR);
    }

    public static final MapCodec<GedritedWaterCauldronBlock> CODEC = createCodec(GedritedWaterCauldronBlock::new);

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.9375;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (this.isEntityTouchingFluid(state, pos, entity)) {
            if (entity instanceof LivingEntity livingEntity) {
                if (!livingEntity.hasStatusEffect(ModEffects.DECAY)) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.DECAY, 60, 0, false, false, true));
                }
            }
        }
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 2;
    }
}
