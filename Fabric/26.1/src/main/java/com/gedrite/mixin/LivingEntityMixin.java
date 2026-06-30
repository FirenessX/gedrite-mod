package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey; // Added import
import net.minecraft.util.Mth; // Added import
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid; // Added import
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB; // Added import
import net.minecraft.world.phys.Vec3;
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
            if(ModFluids.isBoundingBoxInFluid(entity, ModTags.Fluids.GEDRITED_WATER)){
                ModFluids.decayEffect(entity);
            }
        }
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void onTravel(Vec3 input, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasLandedInLiquid()){
            boolean bl;
            double d = 0.08;
            boolean bl2 = bl = entity.getDeltaMovement().y <= 0.0;
            if (bl && entity.hasEffect(MobEffects.SLOW_FALLING)) {
                d = 0.01;
            }

            BlockPos pos = entity.blockPosition();
            FluidState fluidState = entity.level().getFluidState(pos);

            if ((fluidState.getType() == ModFluids.GEDRITED_WATER ||
                    fluidState.getType() == ModFluids.FLOWING_GEDRITED_WATER) &&
                    !entity.canStandOnFluid(fluidState) &&
                    !(entity instanceof Player player && player.getAbilities().flying))
            {
                Vec3 vec3d;
                double e = entity.getY();

                if (entity.getFluidHeight(ModTags.Fluids.GEDRITED_WATER) <= entity.getFluidJumpThreshold()){
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7, 0.9, 0.7));
                    vec3d = entity.getFluidFallingAdjustedMovement(d, bl, entity.getDeltaMovement());
                    entity.setDeltaMovement(vec3d);
                } else {
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7D, 0.7D, 0.7D));
                }
                vec3d = entity.getDeltaMovement();
                if (entity.horizontalCollision && entity.isFree(vec3d.x, vec3d.y + (double)0.6f - entity.getY() + e, vec3d.z)) {
                    entity.setDeltaMovement(vec3d.x, 0.3f, vec3d.z);
                }
                entity.setAirSupply(entity.getMaxAirSupply());
            }
        }
    }
}