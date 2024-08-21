package com.gedrite.event;

import com.gedrite.Gedrite;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.util.ModTags;
import com.gedrite.world.effects.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gedrite.MOD_ID)
public class ModEventBus {

//    @SubscribeEvent
//    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
//        LivingEntity livingEntity = event.getEntity();
//
//        if (livingEntity instanceof LivingEntity){
//            if (livingEntity.isAlive()) {
//                if (updateMovementInFluid(livingEntity, ModTags.Fluids.GEDRITED_WATER)) {
//                    if (!livingEntity.hasEffect(ModEffects.DECAY.get())) {
//                        livingEntity.addEffect(new MobEffectInstance(ModEffects.DECAY.get(), 60, 0, false, false, true));
//                    }
//                } else {
//                    livingEntity.removeEffect(ModEffects.DECAY.get());
//                }
//            }
//        }
//    }
//
//    public static boolean updateMovementInFluid(LivingEntity livingEntity, TagKey<Fluid> pFluidTag) {
//        livingEntity.updateFluidHeightAndDoFluidPushing();
//        if (pFluidTag == ModTags.Fluids.GEDRITED_WATER) return livingEntity.isInFluidType(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get());
//        else return false;
//    }
//
//    @SubscribeEvent
//    private void travel(LivingEvent.LivingTickEvent event) {
//        LivingEntity entity = event.getEntity();
//
//        if (entity.isControlledByLocalInstance()){
//            boolean bl;
//            double d = 0.08;
//            boolean bl2 = bl = entity.getDeltaMovement().y <= 0.0;
//            if (bl && entity.hasEffect(MobEffects.SLOW_FALLING)) {
//                d = 0.01;
//            }
//
//            BlockPos pos = entity.getOnPos();
//            FluidState fluidState = entity.level().getFluidState(pos);
//
//            if ((fluidState.getType() == ModFluids.SOURCE_GEDRITED_WATER.get() ||
//                    fluidState.getType() == ModFluids.FLOWING_GEDRITED_WATER.get()) &&
//                    !entity.canStandOnFluid(fluidState) &&
//                    !(entity instanceof Player && ((Player) entity).getAbilities().flying))
//            {
//                Vec3 vec3;
//                double e = entity.getY();
//
//                if (entity.getFluidTypeHeight(ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.get()) <= entity.getFluidJumpThreshold()){
//                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.7, 0.9, 0.7));
//                    vec3 = entity.getFluidFallingAdjustedMovement(d, bl, entity.getDeltaMovement());
//                    entity.setDeltaMovement(vec3);
//                } else {
//                    entity.getDeltaMovement().multiply(0.7, 0.7, 0.7);
//                }
//                vec3 = entity.getDeltaMovement();
//                if (entity.horizontalCollision && entity.isFree(vec3.x, vec3.y + (double)0.6f - entity.getY() + e, vec3.z)) {
//                    entity.setDeltaMovement(vec3.x, 0.3f, vec3.z);
//                }
//                entity.setAirSupply(entity.getMaxAirSupply());
//            }
//        }
//    }
}