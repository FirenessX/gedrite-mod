package com.gedrite.entity.custom.projectile;

import com.gedrite.effects.ModEffects;
import com.gedrite.entity.ModEntities;
import com.gedrite.items.ModItems;
import com.gedrite.particles.ModParticleTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GedriteArrowEntity extends PersistentProjectileEntity {
    private int duration = 60;
    public GedriteArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public GedriteArrowEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(ModEntities.GEDRITE_ARROW, x, y, z, world, stack, weapon);
    }

    public GedriteArrowEntity(LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntities.GEDRITE_ARROW, owner, world, stack, shotFrom);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.isInGround()) {
            this.getWorld().addParticleClient(ModParticleTypes.DECAY_PARTICLE, this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
        }

    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(ModEffects.DECAY, this.duration, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.duration = view.getInt("Duration", 60);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Duration", this.duration);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.GEDRITE_ARROW);
    }
}
