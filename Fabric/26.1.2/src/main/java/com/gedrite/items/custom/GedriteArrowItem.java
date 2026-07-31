package com.gedrite.items.custom;

import com.gedrite.entity.custom.projectile.GedriteArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GedriteArrowItem extends ArrowItem {
    public GedriteArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity owner, @Nullable ItemStack firedFromWeapon) {
        return new GedriteArrow(level, owner, itemStack.copyWithCount(1), firedFromWeapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack itemStack, Direction direction) {
        GedriteArrow arrow = new GedriteArrow(level, pos.x(), pos.y(), pos.z(), itemStack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
