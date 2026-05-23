package com.gedrite.world.entity.projectile;

import com.gedrite.core.particles.ModParticles;
import com.gedrite.items.ModItems;
import com.gedrite.world.effects.ModEffects;
import com.gedrite.world.entity.ModEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class GedriteArrow extends AbstractArrow {
    private int duration = 60;
    public GedriteArrow(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public GedriteArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.GEDRITE_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    public GedriteArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.GEDRITE_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide() && !this.isInGround()) {
            this.level().addParticle(ModParticles.DECAY_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.DECAY, this.duration);
        living.addEffect(mobeffectinstance, this.getEffectSource());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput p_421509_) {
        super.readAdditionalSaveData(p_421509_);
        this.duration = p_421509_.getIntOr("Duration", 200);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput p_422688_) {
        super.addAdditionalSaveData(p_422688_);
        p_422688_.putInt("Duration", this.duration);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GEDRITE_ARROW.get());
    }
}
