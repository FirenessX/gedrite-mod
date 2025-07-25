package com.gedrite.mixins;

import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Inject(method = "shouldStopFishing", at = @At("HEAD"), cancellable = true)
    private void gedrite$removeIfInvalid(Player player, CallbackInfoReturnable<Boolean> cir){
        FishingHook hook = (FishingHook) (Object) this;
        BlockPos blockpos = hook.blockPosition();
        FluidState fluidstate = hook.level().getFluidState(blockpos);
        if(hook.updateFluidHeightAndDoFluidPushing(ModTags.Fluids.GEDRITED_WATER, 0.014D)){
            hook.discard();
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static boolean updateMovementInFluid(Entity entity, Predicate<FluidState> shouldUpdate) {
        entity.updateFluidHeightAndDoFluidPushing();
        return entity.isInFluidType(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get());
    }
}
