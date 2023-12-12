//package com.gedrite.handler;
//
//import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
//import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.texture.Sprite;
//import net.minecraft.client.texture.SpriteAtlasTexture;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.BlockRenderView;
//import org.jetbrains.annotations.Nullable;
//import net.minecraft.util.Identifier;
//import com.gedrite.fluids.ModFluids;
//
//public class GedriteHandler implements FluidRenderHandler {
//
//    private Sprite stillSprite;
//    private Sprite flowingSprite;
//
//    public GedriteHandler() {
//
//    }
//
//    @Override
//    public Sprite[] getFluidSprites(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
//        if (stillSprite == null || flowingSprite == null) {
//            return new Sprite[]{};
//        }
//        return new Sprite[]{stillSprite, flowingSprite};
//    }
//
//    @Override
//    public int getFluidColor(@Nullable BlockRenderView view, @Nullable BlockPos pos, FluidState state) {
//        return 0xFF31DF;
//    }
//
//    @Override
//    public void renderFluid(BlockPos pos, BlockRenderView world, VertexConsumer vertexConsumer, BlockState blockState, FluidState fluidState) {
//        FluidRenderHandler.super.renderFluid(pos, world, vertexConsumer, blockState, fluidState);
//    }
//
//    @Override
//    public void reloadTextures(SpriteAtlasTexture textureAtlas) {
//        Identifier stillTextureId = new Identifier("gedrite", "textures/block/gedrited_water_still.png");
//        Identifier flowingTextureId = new Identifier("gedrite", "textures/block/gedrited_water_flow.png");
//
//        stillSprite = textureAtlas.getSprite(stillTextureId);
//        flowingSprite = textureAtlas.getSprite(flowingTextureId);
//    }
//
//    public static void register() {
//        // Register your fluid renderer with FluidRenderHandlerRegistry
//        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GEDRITED_WATER, new GedriteHandler());
//        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.FLOWING_GEDRITED_WATER, new GedriteHandler());
//    }
//}
