//package com.gedrite.mixin;
//
//import com.gedrite.particles.ModParticleTypes;
//import com.gedrite.util.ModTags;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.PointedDripstoneBlock;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.particle.SimpleParticleType;
//import net.minecraft.particle.ParticleTypes;
//import net.minecraft.registry.tag.FluidTags;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(PointedDripstoneBlock.class)
//public class PointedDripstoneBlockMixin {
//    @Inject(method = "createParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
//    private static void gedrite$createParticle(World world, BlockPos pos, BlockState state, Fluid fluid, CallbackInfo ci){
//        Fluid fluid2 = getDripFluid(world, fluid);
//        SimpleParticleType particleEffect = fluid2.isIn(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : fluid2.isIn(ModTags.Fluids.GEDRITED_WATER) ? ModParticleTypes.DRIPPING_DRIPSTONE_GEDRITED_WATER : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
//    }
//
//    @Unique
//    private static Fluid getDripFluid(World world, Fluid fluid) {
//        if (fluid.matchesType(Fluids.EMPTY)) {
//            return world.getDimension().ultrawarm() ? Fluids.LAVA : Fluids.WATER;
//        }
//        return fluid;
//    }
//}
