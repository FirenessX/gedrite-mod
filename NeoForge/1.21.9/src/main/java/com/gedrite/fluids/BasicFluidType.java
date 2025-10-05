//package com.gedrite.fluids;
//
//import net.minecraft.client.Camera;
//import net.minecraft.client.multiplayer.ClientLevel;
//import net.minecraft.client.renderer.fog.FogData;
//import net.minecraft.client.renderer.fog.environment.FogEnvironment;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
//import net.neoforged.neoforge.fluids.FluidType;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.joml.Vector4f;
//
//public class BasicFluidType extends FluidType {
//    /**
//     * Default constructor.
//     *
//     * @param properties the general properties of the fluid type
//     */
//    public BasicFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture,
//                          final ResourceLocation overlayTexture, final int tintColor, final Vector4f fogColor, final Properties properties) {
//        super(properties);
//        this.stillTexture = stillTexture;
//        this.flowingTexture = flowingTexture;
//        this.overlayTexture = overlayTexture;
//        this.tintColor = tintColor;
//        this.fogColor = new Vector4f((float) 135 / 255, (float) 34 / 255, (float) 102 / 255, 1.0F);
//    }
//    private final ResourceLocation stillTexture;
//    private final ResourceLocation flowingTexture;
//    private final ResourceLocation overlayTexture;
//    private final int tintColor;
//    private final Vector4f fogColor;
//
//    public ResourceLocation getStillTexture() {
//        return stillTexture;
//    }
//
//    public ResourceLocation getFlowingTexture() {
//        return flowingTexture;
//    }
//
//    public int getTintColor() {
//        return tintColor;
//    }
//
//    public ResourceLocation getOverlayTexure() {
//        return overlayTexture;
//    }
//
//    public Vector4f getFogColor() {
//        return fogColor;
//    }
//
//    public IClientFluidTypeExtensions getClientFluidTypeExtensions() {
//        return new IClientFluidTypeExtensions() {
//            @Override
//            public ResourceLocation getStillTexture() {
//                return stillTexture;
//            }
//
//            @Override
//            public ResourceLocation getFlowingTexture() {
//                return flowingTexture;
//            }
//
//            @Override
//            public ResourceLocation getOverlayTexture() {
//                return overlayTexture;
//            }
//
//            @Override
//            public int getTintColor() {
//                return tintColor;
//            }
//
//            @Override
//            public @NotNull Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
//                return fogColor;
//            }
//
//            @Override
//            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
//                fogData.renderDistanceStart = -5.0F;
//                fogData.renderDistanceEnd = 10.0F;
//                fogData.environmentalStart = -5.0F;
//                fogData.environmentalEnd = 10.0F;
//                fogData.skyEnd = 10.0F;
//                fogData.cloudEnd = 10.0F;
//            }
//        };
//    }
//}
