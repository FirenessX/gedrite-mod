package com.gedrite.blocks.custom;

import com.gedrite.core.cauldron.ModCauldronInteraction;
import com.gedrite.world.effects.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GedritedWaterCauldronBlock extends AbstractCauldronBlock {
    public GedritedWaterCauldronBlock(BlockBehaviour.Properties p_153498_) {
        super(p_153498_, ModCauldronInteraction.GEDRITED_WATER);
    }

    @Override
    protected double getContentHeight(@NotNull BlockState p_153500_) {
        return 0.9375D;
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (this.isEntityInsideContent(state, pos, entity)) {
            if (entity instanceof LivingEntity livingEntity) {
                if (!livingEntity.hasEffect(ModEffects.DECAY.get())) {
                    livingEntity.addEffect(new MobEffectInstance(ModEffects.DECAY.get(), 60, 0, false, false, true));
                }
            }
        }
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state,@NotNull Level level,@NotNull BlockPos pos) {
        return 2;
    }

    @Override
    public boolean isFull(@NotNull BlockState pState) {
        return true;
    }
}
