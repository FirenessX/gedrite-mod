package com.gedrite.mixins;

import com.gedrite.fluids.ModFluidTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Inject(method = "shouldStopFishing", at = @At("HEAD"), cancellable = true)
    private void gedrite$removeIfInvalid(Player player, CallbackInfoReturnable<Boolean> cir){
        FishingHook hook = (FishingHook) (Object) this;
        if(updateMovementInFluid(hook)){
            hook.discard();
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static boolean updateMovementInFluid(Entity entity) {
        entity.updateFluidHeightAndDoFluidPushing();
        return entity.isInFluidType(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get());
    }
}
