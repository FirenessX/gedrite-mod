package com.gedrite.mixin;

import com.gedrite.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Inject(method = "removeIfInvalid", at = @At("HEAD"), cancellable = true)
    private void gedrite$removeIfInvalid(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        FishingBobberEntity bobber = (FishingBobberEntity) (Object) this;
        if(bobber.updateMovementInFluid(ModTags.Fluids.GEDRITED_WATER, 0.014)){
            bobber.discard();
            cir.setReturnValue(true);
        }
    }
}
