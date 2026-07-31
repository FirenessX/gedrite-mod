package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin{
    @Inject(method = "canBeReplacedWith", at = @At(value = "HEAD"), cancellable = true)
    public void gedrite$canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(direction == Direction.DOWN && !(other.is(FluidTags.WATER) && !other.is(ModTags.Fluids.GEDRITED_WATER)));
    }
}