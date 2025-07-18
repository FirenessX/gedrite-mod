//package com.gedrite.mixin;
//
//import com.gedrite.Gedrite;
//import com.gedrite.items.ModItems;
//import com.gedrite.mixin.ItemRendererAccessor;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.item.ItemRenderer;
//import net.minecraft.client.render.model.BakedModel;
//import net.minecraft.client.util.ModelIdentifier;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ModelTransformationMode;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.ModifyVariable;
//
//@Mixin(ItemRenderer.class)
//public abstract class ItemRendererMixin {
//    @ModifyVariable(method = "renderItem*", at = @At(value = "HEAD"), argsOnly = true)
//    public BakedModel useMetalDetectorModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        if (stack.isOf(ModItems.METAL_DETECTOR) && renderMode != ModelTransformationMode.GUI) {
//            return ((ItemRendererAccessor) this).mccourse$getModels().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Gedrite.MOD_ID, "metal_detector_3d")).id());
//        }
//        return value;
//    }
//}