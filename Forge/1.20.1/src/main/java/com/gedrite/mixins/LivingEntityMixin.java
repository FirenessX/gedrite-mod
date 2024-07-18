package com.gedrite.mixins;

import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private static final UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    @Unique
    private static final AttributeModifier SLOW_FALLING = new AttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION); // Add -0.07 to 0.08 so we get the vanilla default of 0.01
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void gedrite$baseTick(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof LivingEntity){
            if (livingEntity.isAlive()) {
                if (updateMovementInFluid(livingEntity)) {
                    if (!livingEntity.hasEffect(ModEffects.DECAY.get())) {
                        livingEntity.addEffect(new MobEffectInstance(ModEffects.DECAY.get(), 60, 0, false, false, true));
                    }
                } else {
                    livingEntity.removeEffect(ModEffects.DECAY.get());
                }
            }
        }
    }

    @Unique
    private static boolean updateMovementInFluid(LivingEntity livingEntity) {
        livingEntity.updateFluidHeightAndDoFluidPushing();
        return livingEntity.isInFluidType(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get());
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void gedrite$travel(Vec3 pTravelVector, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        System.out.println();
        System.out.println(entity.getDeltaMovement().x * 10000);
        System.out.println(entity.getDeltaMovement().y * 10000);
        System.out.println(entity.getDeltaMovement().z * 10000);
        System.out.println();
        if (entity.isControlledByLocalInstance()){
            boolean flag;
            double d0 = 0.08D;
            AttributeInstance gravity = entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
            boolean bl2 = flag = entity.getDeltaMovement().y <= 0.0;
            if (flag && entity.hasEffect(MobEffects.SLOW_FALLING)) {
                assert gravity != null;
                if (!gravity.hasModifier(SLOW_FALLING)) gravity.addTransientModifier(SLOW_FALLING);
            } else {
                assert gravity != null;
                if (gravity.hasModifier(SLOW_FALLING)) {
                    gravity.removeModifier(SLOW_FALLING);
                }
            }

            d0 = gravity.getValue();

            BlockPos pos = entity.blockPosition();
            FluidState fluidState = entity.level().getFluidState(pos);

            if (fluidState.getFluidType() == ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get() &&
                    entity.isAffectedByPotions() &&
                    !entity.canStandOnFluid(fluidState) &&
                    !(entity instanceof Player player && player.getAbilities().flying))
            {
                double d8 = entity.getY();
//                entity.moveRelative(0.02F, pTravelVector);
//                entity.move(MoverType.SELF, entity.getDeltaMovement());
                if (entity.getFluidHeight(ModTags.Fluids.GEDRITED_WATER) <= entity.getFluidJumpThreshold()) {
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7D, (double)0.9F, 0.7D));
                    Vec3 vec33 = gedrite$getFluidFallingAdjustedMovement(d0, flag, entity.getDeltaMovement(), entity);
                    entity.setDeltaMovement(vec33);
                } else {
                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.7D));
//                                                  14,61538461538462                    4,639473684210526
                }

                Vec3 vec34 = entity.getDeltaMovement();
                if (entity.horizontalCollision && entity.isFree(vec34.x, vec34.y + (double)0.6F - entity.getY() + d8, vec34.z)) {
                    entity.setDeltaMovement(vec34.x, (double)0.3F, vec34.z);
                }
            }
        }
    }

    @Unique
    private Vec3 gedrite$getFluidFallingAdjustedMovement(double pGravity, boolean pIsFalling, Vec3 pDeltaMovement, LivingEntity entity) {
        if (!entity.isNoGravity()) {
            double d0;
            if (pIsFalling && Math.abs(pDeltaMovement.y - 0.005D) >= 0.003D && Math.abs(pDeltaMovement.y - pGravity / 16.0D) < 0.003D) {
                d0 = -0.003D;
            } else {
                d0 = pDeltaMovement.y - pGravity / 16.0D;
            }

            return new Vec3(pDeltaMovement.x, d0, pDeltaMovement.z);
        } else {
            return pDeltaMovement;
        }
    }
}
