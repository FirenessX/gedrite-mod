package com.gedrite.entity.custom.projectile;

import com.gedrite.effects.ModEffects;
import com.gedrite.entity.ModEntities;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class GedriteArrow extends AbstractArrow {
    private int duration = 60;
    public GedriteArrow(EntityType<GedriteArrow> type, Level level) {
        super(type, level);
    }

    public GedriteArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @org.jspecify.annotations.Nullable ItemStack firedFromWeapon) {
        super(ModEntities.GEDRITE_ARROW, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    public GedriteArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.GEDRITE_ARROW, owner, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide() && !this.isInGround()) {
            this.level().addParticle(ModParticleTypes.DECAY_PARTICLE, this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
        }

    }

    @Override
    protected void doPostHurtEffects(LivingEntity mob) {
        super.doPostHurtEffects(mob);
        MobEffectInstance mobEffectInstance = new MobEffectInstance(ModEffects.DECAY, this.duration, 0);
        mob.addEffect(mobEffectInstance, this.getEffectSource());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.duration = input.getIntOr("Duration", 60);    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Duration", this.duration);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GEDRITE_ARROW);
    }
}
