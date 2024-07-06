package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer.FogType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(net.minecraft.client.render.BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    @Shadow
    private static float red;

    @Shadow
    private static float green;

    @Shadow
    private static float blue;

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V", remap = false))
    private static void gedrite$render(Args args, Camera camera, float partialTicks, ClientWorld level, int renderDistanceChunks, float bossColorModifier) {
        FluidState state = level.getFluidState(camera.getBlockPos());
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (ModFluids.isGedritedWater(state) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            red = (float) 135 / 255;
            green = (float) 34 / 255;
            blue = (float) 102 / 255;
//        if (player != null && player.hasStatusEffect(ModEffects.DECAY) && camera.getSubmersionType() != CameraSubmersionType.WATER) {
//            float pulseStrength = (float) Math.sin((double) (System.currentTimeMillis() / 200.0)) * 0.5f + 0.5f;
//            red = (float) 135/255 * pulseStrength;
//            green = (float) 34/255 * pulseStrength;
//            blue = (float) 102/255 * pulseStrength;
//        }
        }
    }
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void gedrite$applyFog(Camera camera, FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        assert MinecraftClient.getInstance().world != null;
        FluidState state = MinecraftClient.getInstance().world.getFluidState(camera.getBlockPos());
        if (ModFluids.isGedritedWater(state) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            RenderSystem.setShaderFogStart(-5);                 /// gedrited water fog
            RenderSystem.setShaderFogEnd(10);
            ci.cancel();
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