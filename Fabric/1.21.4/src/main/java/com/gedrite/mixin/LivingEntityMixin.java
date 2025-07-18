package com.gedrite.mixin;

import com.gedrite.effects.ModEffects;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void gedrite$baseTick(CallbackInfo ci){
        LivingEntity entity = (LivingEntity) (Object) this;
        if(entity.isAlive()){
//            if(entity.updateMovementInFluid(ModTags.Fluids.GEDRITED_WATER, 0.014)){
            if(entity.updateMovementInFluid(ModTags.Fluids.GEDRITED_WATER, 0.014)){
//                System.out.println("0.014");
                if (!entity.hasStatusEffect(ModEffects.DECAY)) {
//                    System.out.println("decay");
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

        if (entity.isLogicalSideForUpdatingMovement()){
            boolean bl;
            double d = 0.08;
            boolean bl2 = bl = entity.getVelocity().y <= 0.0;
            if (bl && entity.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
                d = 0.01;
            }

            BlockPos pos = entity.getBlockPos();
            FluidState fluidState = entity.getWorld().getFluidState(pos);

            if ((fluidState.getFluid() == ModFluids.GEDRITED_WATER ||
                    fluidState.getFluid() == ModFluids.FLOWING_GEDRITED_WATER) &&
                    !entity.canWalkOnFluid(fluidState) &&
                    !(entity instanceof PlayerEntity player && player.getAbilities().flying))
            {
                Vec3d vec3d;
                double e = entity.getY();

                if (entity.getFluidHeight(ModTags.Fluids.GEDRITED_WATER) <= entity.getSwimHeight()){
                    entity.setVelocity(entity.getVelocity().multiply(0.7, 0.9, 0.7));
                    vec3d = entity.applyFluidMovingSpeed(d, bl, entity.getVelocity());
                    entity.setVelocity(vec3d);
                } else {
                    entity.getVelocity().multiply(0.7);
                }
                vec3d = entity.getVelocity();
                if (entity.horizontalCollision && entity.doesNotCollide(vec3d.x, vec3d.y + (double)0.6f - entity.getY() + e, vec3d.z)) {
                    entity.setVelocity(vec3d.x, 0.3f, vec3d.z);
                }
                entity.setAir(entity.getMaxAir());
            }
        }
    }
}
