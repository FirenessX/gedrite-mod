//package com.gedrite.mixin;
//
//import com.gedrite.client.particle.ModBlockLeakParticle;
//import com.gedrite.client.particle.ModDefaultParticleType;
//import com.gedrite.client.particle.ModParticleManager;
//import com.gedrite.particles.ModParticleTypes;
//import com.google.common.collect.Maps;
//import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
//import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
//import net.minecraft.client.particle.BlockLeakParticle;
//import net.minecraft.client.particle.ParticleFactory;
//import net.minecraft.client.particle.ParticleManager;
//import net.minecraft.client.particle.SpriteBillboardParticle;
//import net.minecraft.particle.ParticleEffect;
//import net.minecraft.particle.ParticleType;
//import net.minecraft.registry.Registries;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Map;
//
//@Mixin(ParticleManager.class)
//public class ParticleManagerMixin {
//
//    @Unique
//    private final Map<Identifier, ModParticleManager.SimpleSpriteProvider> spriteAwareFactories = Maps.newHashMap();
//    @Unique
//    private final Int2ObjectMap<ParticleFactory<?>> factories = new Int2ObjectOpenHashMap<>();
//
//    @Inject(method = "registerDefaultFactories", at = @At("HEAD"))
//    private void gedrite$registerDefaultFactories(CallbackInfo ci){
//        this.registerBlockLeakFactory(ModParticleTypes.DRIPPING_GEDRITED_WATER, ModBlockLeakParticle::createDrippingGedritedWater);
//        this.registerBlockLeakFactory(ModParticleTypes.FALLING_GEDRITED_WATER, ModBlockLeakParticle::createFallingGedritedWater);
//    }
//
//
//    @Unique
//    private <T extends ParticleEffect> void registerBlockLeakFactory(ParticleType<T> particleType, ParticleFactory.BlockLeakParticleFactory<T> factory) {
//        this.registerFactory(particleType, spriteProvider -> (type, world, x, y, z, velocityX, velocityY, velocityZ) -> {
//            SpriteBillboardParticle particle = factory.createParticle(type, world, x, y, z, velocityX, velocityY, velocityZ);
//            if (particle != null) {
//                particle.setSprite(spriteProvider);
//            }
//            return particle;
//        });
//    }
//
//    @Unique
//    private <T extends ParticleEffect> void registerFactory(ParticleType<T> type, ModParticleManager.SpriteAwareFactory<T> factory) {
//        ModParticleManager.SimpleSpriteProvider simpleSpriteProvider = new ModParticleManager.SimpleSpriteProvider();
//        this.spriteAwareFactories.put(Registries.PARTICLE_TYPE.getId(type), simpleSpriteProvider);
//        this.factories.put(Registries.PARTICLE_TYPE.getRawId(type), factory.create(simpleSpriteProvider));
//    }
//}
