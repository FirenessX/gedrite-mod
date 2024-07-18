package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    private void gedrite$updateSwimming(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        World world = player.method_48926();
        BlockPos pos = player.getBlockPos();
        FluidState fluidState = world.getFluidState(pos);

        if (fluidState.getFluid() == ModFluids.GEDRITED_WATER || fluidState.getFluid() == ModFluids.FLOWING_GEDRITED_WATER) {
            player.setSwimming(false);
            ci.cancel();
        }
    }
}
