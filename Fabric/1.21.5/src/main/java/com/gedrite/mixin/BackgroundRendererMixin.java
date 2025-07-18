package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.BackgroundRenderer.FogType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
    @Shadow
    private static boolean fogEnabled;

    private static float r, s ,t;

//    @ModifyArgs(method = "getFogColor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V", remap = false))
//    private static void gedrite$render(Args args, Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness) {
//        FluidState state = world.getFluidState(camera.getBlockPos());
//        if (ModFluids.isGedritedWater(state) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
//            r = (float) 135 / 255;
//            s = (float) 34 / 255;
//            t = (float) 102 / 255;
//        if (player != null && player.hasStatusEffect(ModEffects.DECAY) && camera.getSubmersionType() != CameraSubmersionType.WATER) {
//            float pulseStrength = (float) Math.sin((double) (System.currentTimeMillis() / 200.0)) * 0.5f + 0.5f;
//            red = (float) 135/255 * pulseStrength;
//            green = (float) 34/255 * pulseStrength;
//            blue = (float) 102/255 * pulseStrength;
//        }
//    }
//    }

    @Inject(method = "getFogColor", at = @At(value = "HEAD"))
    private static void gedrite$getFogColor(Camera camera, float tickDelta, ClientWorld world, int clampedViewDistance, float skyDarkness, CallbackInfoReturnable<Vector4f> cir){
        FluidState fluidState = world.getFluidState(camera.getBlockPos());
        if(ModFluids.isGedritedWater(fluidState) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            r = (float) 135 / 255;
            s = (float) 34 / 255;
            t = (float) 102 / 255;
        }
//        cir.setReturnValue(new Vector4f(r, s, t, 1.0F));
    }

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void gedrite$applyFog(Camera camera, FogType fogType, Vector4f color, float viewDistance, boolean thickFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        assert MinecraftClient.getInstance().world != null;
        FluidState state = MinecraftClient.getInstance().world.getFluidState(camera.getBlockPos());

        if (!fogEnabled) {
             cir.setReturnValue(Fog.DUMMY);
        } else if (ModFluids.isGedritedWater(state) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            r = (float) 135 / 255;
            s = (float) 34 / 255;
            t = (float) 102 / 255;
            cir.setReturnValue(new Fog(-5, 10, FogShape.CYLINDER, r, s, t, 1));
        }
//        ClientPlayerEntity player = MinecraftClient.getInstance().player;
//        Entity entity = camera.getFocusedEntity();
//         else if (player != null && player.hasStatusEffect(ModEffects.DECAY) && camera.getSubmersionType() != CameraSubmersionType.WATER) {
//            RenderSystem.setShaderFogStart(0);                 /// decay fog
//            RenderSystem.setShaderFogEnd(5);
//            ci.cancel();
//        }
    }
}