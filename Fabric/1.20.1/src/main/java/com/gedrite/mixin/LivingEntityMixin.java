package com.gedrite.mixin;

import com.gedrite.effects.ModEffects;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void gedrite$baseTick(CallbackInfo ci){
        LivingEntity entity = (LivingEntity) (Object) this;
        if(entity.isAlive()){
            if(entity.updateMovementInFluid(ModTags.GEDRITED_WATER, 0.014)){
                if (!entity.hasStatusEffect(ModEffects.DECAY)) {
                    entity.addStatusEffect(new StatusEffectInstance(ModEffects.DECAY, 60, 0, false, false, true));
                }
            } else {
//                entity.removeStatusEffect(ModEffects.DECAY);
            }
        }
    }


    @Inject(method = "travel", at = @At("HEAD"))
    private void onTravel(Vec3d movementInput, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        BlockPos pos = entity.getBlockPos();
        FluidState fluidState = entity.getWorld().getFluidState(pos);

        if (fluidState.getFluid() == ModFluids.GEDRITED_WATER || fluidState.getFluid() == ModFluids.FLOWING_GEDRITED_WATER) {
            entity.setVelocity(entity.getVelocity().multiply(0.7, 0.9, 0.7));
        }
    }
}
