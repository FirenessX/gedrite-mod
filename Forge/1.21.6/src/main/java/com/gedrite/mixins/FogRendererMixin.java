//package com.gedrite.mixins;
//
//import com.gedrite.fluids.ModFluids;
//import com.mojang.blaze3d.shaders.FogShape;
//import net.minecraft.client.Camera;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.ClientLevel;
//import net.minecraft.client.renderer.FogParameters;
//import net.minecraft.client.renderer.FogRenderer;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.FogType;
//import org.joml.Vector4f;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(FogRenderer.class)
//public class FogRendererMixin {
//    @Shadow
//    private static boolean fogEnabled;
//
//    private static float r, s ,t;
//
//
//    @Inject(method = "computeFogColor", at = @At(value = "HEAD"))
//    private static void gedrite$computeFogColor(Camera camera, float tickDelta, ClientLevel level, int clampedViewDistance, float skyDarkness, CallbackInfoReturnable<Vector4f> cir){
//        FluidState fluidState = level.getFluidState(camera.getBlockPosition());
//        if(ModFluids.isGedritedWater(fluidState) && camera.getFluidInCamera() == FogType.WATER) {
//            r = (float) 135 / 255;
//            s = (float) 34 / 255;
//            t = (float) 102 / 255;
//        }
//    }
//
//    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
//    private static void gedrite$setupFog(Camera camera, FogType fogType, Vector4f color, float viewDistance, boolean thickFog, float tickDelta, CallbackInfoReturnable<FogParameters> cir) {
//        assert Minecraft.getInstance().level != null;
//        FluidState state = Minecraft.getInstance().level.getFluidState(camera.getBlockPosition());
//        if (!fogEnabled) {
//            cir.setReturnValue(FogParameters.NO_FOG);
//        } else if (ModFluids.isGedritedWater(state) && camera.getFluidInCamera() == FogType.WATER) {
//            r = (float) 135 / 255;
//            s = (float) 34 / 255;
//            t = (float) 102 / 255;
//            cir.setReturnValue(new FogParameters(-5, 10, FogShape.CYLINDER, r, s, t, 1));
//        }
//    }
//}
