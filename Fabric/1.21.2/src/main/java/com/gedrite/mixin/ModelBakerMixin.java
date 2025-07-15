//package com.gedrite.mixin;
//
//import com.gedrite.Gedrite;
//import net.minecraft.client.render.model.ModelBaker;
//import net.minecraft.client.util.ModelIdentifier;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ModelBaker.class)
//public abstract class ModelBakerMixin {
//    @Shadow
//    protected abstract void loadItemModel(ModelIdentifier id);
//
//    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelBaker;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 1))
//    private void onInit(CallbackInfo ci) {
//        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Gedrite.MOD_ID, "metal_detector_3d")));
//    }
//}