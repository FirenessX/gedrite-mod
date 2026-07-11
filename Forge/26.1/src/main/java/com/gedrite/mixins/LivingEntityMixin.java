package com.gedrite.mixins;

import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "baseTick", at = @At("HEAD"), remap = false)
    private void gedrite$baseTick(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity != null){
            if (livingEntity.isAlive()) {
                Predicate<FluidState> isGW = state -> state.isSource() && state.getType().isSame(ModFluids.SOURCE_GEDRITED_WATER.get()) || !state.isSource() && state.getType().isSame(ModFluids.FLOWING_GEDRITED_WATER.get());
                if(ModFluids.isBoundingBoxInFluid(livingEntity, ModTags.Fluids.GEDRITED_WATER)){
                    ModFluids.decayEffect(livingEntity);
                }
            }
        }
    }

    @Inject(method = "travel", at = @At("HEAD"), remap = false)
    private void gedrite$travel(Vec3 pTravelVector, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.isLocalInstanceAuthoritative()){
            double d0 = entity.getGravity();
            boolean flag = entity.getDeltaMovement().y <= 0.0;
            if (flag && entity.hasEffect(MobEffects.SLOW_FALLING)) {
                d0 = Math.min(d0, 0.01);
            }

            BlockPos pos = entity.blockPosition();
            FluidState fluidState = entity.level().getFluidState(pos);
            double d9;
            if (fluidState.getFluidType() == ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get() &&
                    entity.isAffectedByPotions() &&
                    !entity.canStandOnFluid(fluidState) &&
                    !(entity instanceof Player player && player.getAbilities().flying))
            {
                d9 = entity.getY();
                Vec3 vec35;
                if (entity.getFluidHeight(ModTags.Fluids.GEDRITED_WATER) <= entity.getFluidJumpThreshold()) {
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7, 0.9000000134110451, 0.7));
                    vec35 = entity.getFluidFallingAdjustedMovement(d0, flag, entity.getDeltaMovement());
                    entity.setDeltaMovement(vec35);
                } else {
                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.7));
                }

                vec35 = entity.getDeltaMovement();
                if (entity.horizontalCollision && entity.isFree(vec35.x, vec35.y + 0.6000000238418579 - entity.getY() + d9, vec35.z)) {
                    entity.setDeltaMovement(vec35.x, 0.30000001192092896, vec35.z);
                }
            }
        }
    }
}
