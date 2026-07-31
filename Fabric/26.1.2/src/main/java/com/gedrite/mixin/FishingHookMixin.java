package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Inject(method = "shouldStopFishing", at = @At("HEAD"), cancellable = true)
    private void gedrite$shouldStopFishing(Player player, CallbackInfoReturnable<Boolean> cir){
        FishingHook bobber = (FishingHook) (Object) this;
        if(ModFluids.isBoundingBoxInFluid(bobber, ModTags.Fluids.GEDRITED_WATER)){
            bobber.discard();
            cir.setReturnValue(true);
        }
    }
}
