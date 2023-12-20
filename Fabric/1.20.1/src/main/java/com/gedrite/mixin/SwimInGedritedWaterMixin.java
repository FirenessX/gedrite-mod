//package com.gedrite.mixin;
//
//import com.gedrite.util.ModTags;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.registry.tag.TagKey;
//import net.minecraft.entity.damage.DamageSource;
//import net.minecraft.util.math.Box;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(LivingEntity.class)
//public abstract class SwimInGedritedWaterMixin {
//
//    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
//    private void swimInGW(CallbackInfo ci) {
//        LivingEntity entity = (LivingEntity) (Object) this;
//        World world = entity.getEntityWorld();
//
//        if (isPlayerInGW(entity)) {
//            // Apply water-like effects in Gedrited Water
//            float GWSwimSpeed = 0.02F;
//            entity.setSwimming(true);
//            entity.setVelocity(entity.getVelocity().add(0, GWSwimSpeed, 0));
//
//            // Cancel the normal movement handling
//            ci.cancel();
//        }
//    }
//
//    private boolean isPlayerInGW(LivingEntity entity) {
//        // Check if the player is in contact with Gedrited Water
//        return isEntityInFluid(entity, ModTags.GEDRITED_WATER);
//    }
//
//    private boolean isEntityInFluid(LivingEntity entity, TagKey<Fluid> fluidTag) {
//        Box boundingBox = entity.getBoundingBox().contract(0.2);
//        int minX = (int) Math.floor(boundingBox.minX);
//        int minY = (int) Math.floor(boundingBox.minY);
//        int minZ = (int) Math.floor(boundingBox.minZ);
//        int maxX = (int) Math.ceil(boundingBox.maxX);
//        int maxY = (int) Math.ceil(boundingBox.maxY);
//        int maxZ = (int) Math.ceil(boundingBox.maxZ);
//
//        for (int x = minX; x <= maxX; x++) {
//            for (int y = minY; y <= maxY; y++) {
//                for (int z = minZ; z <= maxZ; z++) {
//                    if (entity.getEntityWorld().getFluidState(entity.getBlockPos().add(x, y, z)).isIn(fluidTag)) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//}
