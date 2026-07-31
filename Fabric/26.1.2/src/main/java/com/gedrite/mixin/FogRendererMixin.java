package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
    @Unique
    private static final Vector4f GEDRITE_FOG_COLOR = new Vector4f(135f/255f, 34f/255f, 102f/255f, 1.0f);

    @ModifyVariable(
            method = "setupFog(Lnet/minecraft/client/Camera;ILnet/minecraft/client/DeltaTracker;FLnet/minecraft/client/multiplayer/ClientLevel;)Lnet/minecraft/client/renderer/fog/FogData;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/fog/environment/FogEnvironment;setupFog(Lnet/minecraft/client/renderer/fog/FogData;Lnet/minecraft/client/Camera;Lnet/minecraft/client/multiplayer/ClientLevel;FLnet/minecraft/client/DeltaTracker;)V",
                    shift = At.Shift.AFTER
            ),
            name = "fog")
    private FogData gedrite$modifyFogData(FogData fogData, Camera camera, int viewDistance, DeltaTracker deltaTracker, float f, ClientLevel clientLevel) {
        FluidState fluidState = clientLevel.getFluidState(camera.blockPosition());
        if (ModFluids.isGedritedWater(fluidState) && camera.getFluidInCamera() == FogType.WATER) {
            fogData.renderDistanceStart = -5.0F;
            fogData.renderDistanceEnd = 10.0F;
            fogData.environmentalStart = -5.0F;
            fogData.environmentalEnd = 10.0F;
            fogData.skyEnd = 10.0F;
            fogData.cloudEnd = 10.0F;
        }
        return fogData;
    }

    @Inject(
            method = "computeFogColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void gedrite$computeFogColor(Camera camera, float tickProgress, ClientLevel level, int viewDistance, float skyDarkness, Vector4f fogColor, CallbackInfo ci) { // Added Vector4f fogColor parameter, changed to CallbackInfo
        FluidState fluidState = level.getFluidState(camera.blockPosition());
        if (ModFluids.isGedritedWater(fluidState) && camera.getFluidInCamera() == FogType.WATER) {
            fogColor.set(GEDRITE_FOG_COLOR);
            ci.cancel();
        }
    }
}