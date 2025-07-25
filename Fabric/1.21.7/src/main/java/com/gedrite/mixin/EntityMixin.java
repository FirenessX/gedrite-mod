package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "updateSwimming", at = @At("HEAD"), cancellable = true)
    private void gedrite$updateSwimming(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        World world = entity.getWorld();
        BlockPos pos = entity.getBlockPos();
        FluidState fluidState = world.getFluidState(pos);

        if(entity instanceof DrownedEntity) {
            if (fluidState.getFluid() == ModFluids.GEDRITED_WATER || fluidState.getFluid() == ModFluids.FLOWING_GEDRITED_WATER) {
                entity.setSwimming(false);
                ci.cancel();
            }
        }
            if(entity instanceof PlayerEntity player) {
                if (fluidState.getFluid() == ModFluids.GEDRITED_WATER || fluidState.getFluid() == ModFluids.FLOWING_GEDRITED_WATER) {
                    entity.setSwimming(false);
                    if (!player.getAbilities().flying) {
                        entity.setSprinting(false);
                    }
                ci.cancel();
            }
        }
    }
}
