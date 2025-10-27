//package com.gedrite.mixin;
//
//import net.minecraft.client.render.Camera;
//import net.minecraft.client.render.GameRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.gen.Invoker;
//
//@Mixin(GameRenderer.class)
//public interface GameRendererInvoker {
//    @Invoker("getFov")
//    float invokeGetFov(Camera camera, float tickProgress, boolean changingFov);
//}