package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.player.Player;
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
        BlockPos pos = entity.getOnPos();
        FluidState fluidState = level.getFluidState(pos);

        if(entity instanceof Drowned) {
            if (fluidState.getType() == ModFluids.GEDRITED_WATER || fluidState.getType() == ModFluids.FLOWING_GEDRITED_WATER) {
                entity.setSwimming(false);
                ci.cancel();
            }
        }
            if(entity instanceof Player player) {
                if (fluidState.getType() == ModFluids.GEDRITED_WATER || fluidState.getType() == ModFluids.FLOWING_GEDRITED_WATER) {
                    entity.setSwimming(false);
                    if (!player.getAbilities().flying) {
                        entity.setSprinting(false);
                    }
                ci.cancel();
            }
        }
    }
}
