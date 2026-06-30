//package com.gedrite.entity.damage;
//
//import net.minecraft.core.Holder;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.damagesource.DamageType;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.phys.Vec3;
//import org.jspecify.annotations.Nullable;
//
//public class DecayDamageSource extends DamageSource {
//    public DecayDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
//        super(type, directEntity, causingEntity);
//    }
//
//    public DecayDamageSource(Holder<DamageType> type, Vec3 damageSourcePosition) {
//        super(type, damageSourcePosition);
//    }
//
//    public DecayDamageSource(Holder<DamageType> type, @Nullable Entity causingEntity) {
//        super(type, causingEntity);
//    }
//
//    public DecayDamageSource(Holder<DamageType> type) {
//        super(type);
//    }
//    public DecayDamageSource(RegistryAccess.RegistryEntry<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
//        super(type, source, attacker);
//    }
//
//    public DecayDamageSource(RegistryEntry<DamageType> type, Vec3d position) {
//        super(type, position);
//    }
//
//    public DecayDamageSource(RegistryEntry<DamageType> type, @Nullable Entity attacker) {
//        super(type, attacker);
//    }
//
//    public DecayDamageSource(RegistryAccess.RegistryEntry<DamageType> type) {
//        super(type);
//    }
//}