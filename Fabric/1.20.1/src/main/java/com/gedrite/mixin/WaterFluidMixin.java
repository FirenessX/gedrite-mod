package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin{
    @Inject(method = "canBeReplacedWith", at = @At(value = "HEAD"), cancellable = true)
    public void gedrite$canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(direction == Direction.DOWN && !(fluid.isIn(FluidTags.WATER) && !fluid.isIn(ModTags.GEDRITED_WATER)));
    }
}