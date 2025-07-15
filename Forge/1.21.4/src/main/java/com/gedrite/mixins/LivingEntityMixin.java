package com.gedrite.mixins;

import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import java.util.function.Predicate;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void gedrite$baseTick(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof LivingEntity){
            if (livingEntity.isAlive()) {
                Predicate<FluidState> isGW = state -> state.isSource() && state.getType().isSame(ModFluids.SOURCE_GEDRITED_WATER.get()) || !state.isSource() && state.getType().isSame(ModFluids.FLOWING_GEDRITED_WATER.get());
                if(updateMovementInFluid(livingEntity, isGW)){
                    if (!livingEntity.hasEffect(ModEffects.DECAY.getHolder().get())) {
                        livingEntity.addEffect(new MobEffectInstance(ModEffects.DECAY.getHolder().get(), 60, 0, false, false, true));
                    }
                } else {
//                    livingEntity.removeEffect(ModEffects.DECAY.getHolder().get());
                }
            }
        }
    }

    @Unique
    private static boolean updateMovementInFluid(LivingEntity entity, Predicate<FluidState> shouldUpdate) {
        entity.updateFluidHeightAndDoFluidPushing(shouldUpdate);
        return entity.isInFluidType(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get());
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void gedrite$travel(Vec3 pTravelVector, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
//        System.out.println();
//        System.out.println(entity.getDeltaMovement().x * 10000);
//        System.out.println(entity.getDeltaMovement().y * 10000);
//        System.out.println(entity.getDeltaMovement().z * 10000);
//        System.out.println();
        if (entity.isControlledByLocalInstance()){
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
//                double d8 = entity.getY();
////                entity.moveRelative(0.02F, pTravelVector);
////                entity.move(MoverType.SELF, entity.getDeltaMovement());
//                if (entity.getFluidHeight(ModTags.Fluids.GEDRITED_WATER) <= entity.getFluidJumpThreshold()) {
//                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7D, (double)0.9F, 0.7D));
//                    Vec3 vec33 = gedrite$getFluidFallingAdjustedMovement(d0, flag, entity.getDeltaMovement(), entity);
//                    entity.setDeltaMovement(vec33);
//                } else {
//                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.7D));
//                }
//
//                Vec3 vec34 = entity.getDeltaMovement();
//                if (entity.horizontalCollision && entity.isFree(vec34.x, vec34.y + (double)0.6F - entity.getY() + d8, vec34.z)) {
//                    entity.setDeltaMovement(vec34.x, (double)0.3F, vec34.z);
//                }

                d9 = entity.getY();
//                entity.moveRelative(0.02F, pTravelVector);
//                entity.move(MoverType.SELF, entity.getDeltaMovement());
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
