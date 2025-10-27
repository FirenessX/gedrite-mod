//package com.gedrite.client.hud;
//
//import com.gedrite.mixin.GameRendererInvoker;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.render.Camera;
//import net.minecraft.util.math.Vec3d;
//import org.jetbrains.annotations.Nullable;
//import org.joml.Vector3f;
//
//public class DetectedBlockRender {
//    public static @Nullable Vector3f project(Vec3d worldPos, float tickDelta) {
//        MinecraftClient mc = MinecraftClient.getInstance();
//        if (mc == null || mc.world == null || mc.player == null) return null;
//
//        Camera camera = mc.gameRenderer.getCamera();
//        Vec3d camPos = camera.getPos();
//
//        double x = worldPos.x - camPos.x;
//        double y = worldPos.y - camPos.y;
//        double z = worldPos.z - camPos.z;
//        float yawRad = (float) Math.toRadians(camera.getYaw());
//        float pitchRad = (float) Math.toRadians(camera.getPitch());
//        double cosY = Math.cos(-yawRad), sinY = Math.sin(-yawRad);
//        double rx = x * cosY - z * sinY;
//        double rz = x * sinY + z * cosY; // rizz?
//        double cosP = Math.cos(-pitchRad), sinP = Math.sin(-pitchRad);
//        double ry = y * cosP - rz * sinP;
//        double rz2 = y * sinP + rz * cosP;
//
//        if (rz2 >= 0) return null;
//
//        int sw = mc.getWindow().getScaledWidth();
//        int sh = mc.getWindow().getScaledHeight();
//        double fovDeg = ((GameRendererInvoker) mc.gameRenderer).invokeGetFov(camera, tickDelta, true);
//        double fovRad = Math.toRadians(fovDeg);
//        double focal = sh / (2.0 * Math.tan(fovRad / 2.0));
//        float sx = (float) (sw * 0.5 + (rx / -rz2) * focal);
//        float sy = (float) (sh * 0.5 + (ry / -rz2) * focal);
//
//        return new Vector3f(sx, sy, (float) (-rz2));
//    }
//}
