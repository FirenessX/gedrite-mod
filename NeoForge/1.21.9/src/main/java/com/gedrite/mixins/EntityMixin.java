package com.gedrite.mixins;

import com.gedrite.fluids.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    private void gedrite$updateSwimming(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Level level = entity.level();
        BlockPos pos = entity.blockPosition();
        FluidState fluidState = level.getFluidState(pos);

        if(entity instanceof Drowned) {
            if (fluidState.getType() == ModFluids.SOURCE_GEDRITED_WATER.get() || fluidState.getType() == ModFluids.FLOWING_GEDRITED_WATER.get()) {
                entity.setSwimming(false);
                ci.cancel();
            }
        }
    }
}
