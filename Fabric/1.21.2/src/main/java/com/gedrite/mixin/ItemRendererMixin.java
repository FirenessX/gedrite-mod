//package com.gedrite.mixin;
//
//import com.llamalad7.mixinextras.sugar.Local;
//import com.gedrite.Gedrite;
//import com.gedrite.items.ModItems;
//import net.minecraft.client.render.item.ItemModels;
//import net.minecraft.client.render.item.ItemRenderer;
//import net.minecraft.client.render.model.BakedModel;
//import net.minecraft.item.ModelTransformationMode;
//import net.minecraft.client.util.ModelIdentifier;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.ModifyVariable;
//
//@Mixin(ItemRenderer.class)
//public abstract class ItemRendererMixin {
//
//    @Shadow
//    @Final
//    private ItemModels models;
//
//    @Shadow
//    public abstract ItemModels getModels();
//
//    @ModifyVariable(
//            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
//            at = @At(value = "HEAD"),
//            argsOnly = true
//    )
//    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
//        if (stack.getItem() == ModItems.METAL_DETECTOR && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
//            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Gedrite.MOD_ID, "spectre_staff")));
//        }
//
//        return bakedModel;
//    }
//
//    @ModifyVariable(
//            method = "getModel*",
//            at = @At(value = "STORE"),
//            ordinal = 1
//    )
//    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
//        if (stack.getItem() == ModItems.METAL_DETECTOR) {
//            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Gedrite.MOD_ID, "spectre_staff_3d")));
//        }
//
//        return bakedModel;
//    }
//}