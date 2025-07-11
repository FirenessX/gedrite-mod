//package com.gedrite.mixin;
//
//import com.gedrite.Gedrite;
//import net.minecraft.client.color.block.BlockColors;
//import net.minecraft.client.render.model.BlockStatesLoader;
//import net.minecraft.client.render.model.ModelBaker;
//import net.minecraft.client.render.model.json.JsonUnbakedModel;
//import net.minecraft.client.util.ModelIdentifier;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.profiler.Profiler;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.List;
//import java.util.Map;
//
//@Mixin(ModelBaker.class)
//public abstract class ModelLoaderMixin {
//
//    @Shadow protected abstract void loadItemModel(ModelIdentifier id);
//
//    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 0, shift = At.Shift.AFTER))
//    public void addMetalDetector(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<BlockStatesLoader.PackBlockStateDefinition>> blockStates, CallbackInfo ci) {
//        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Gedrite.MOD_ID, "metal_detector_3d")));
//    }
//}