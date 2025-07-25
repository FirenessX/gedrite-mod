package com.gedrite.mixin;

import com.gedrite.fluids.ModFluids;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
    @Unique
    private static final Vector4f GEDRITE_FOG_COLOR = new Vector4f(135f/255f, 34f/255f, 102f/255f, 1.0f);

    // Перехватываем создание FogData и модифицируем его
    @ModifyVariable(
            method = "applyFog(Lnet/minecraft/client/render/Camera;IZLnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/fog/FogModifier;applyStartEndModifier(Lnet/minecraft/client/render/fog/FogData;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/world/ClientWorld;FLnet/minecraft/client/render/RenderTickCounter;)V",
                    shift = At.Shift.AFTER
            ),
            ordinal = 0
    )
    private FogData gedrite$modifyFogData(FogData fogData, Camera camera, int viewDistance, boolean thick,
                                          RenderTickCounter tickCounter, float skyDarkness, ClientWorld world) {

        FluidState fluidState = world.getFluidState(camera.getBlockPos());
        if (ModFluids.isGedritedWater(fluidState) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            fogData.renderDistanceStart = -5.0F;
            fogData.renderDistanceEnd = 10.0F;
            fogData.environmentalStart = -5.0F;
            fogData.environmentalEnd = 10.0F;
            fogData.skyEnd = 10.0F;
            fogData.cloudEnd = 10.0F;
        }
        return fogData;
    }

    // Изменяем цвет тумана
    @Inject(
            method = "getFogColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void gedrite$getFogColor(Camera camera, float tickProgress, ClientWorld world,
                                     int viewDistance, float skyDarkness, boolean thick, CallbackInfoReturnable<Vector4f> cir) {

        FluidState fluidState = world.getFluidState(camera.getBlockPos());
        if (ModFluids.isGedritedWater(fluidState) && camera.getSubmersionType() == CameraSubmersionType.WATER) {
            cir.setReturnValue(GEDRITE_FOG_COLOR);
        }
    }
}