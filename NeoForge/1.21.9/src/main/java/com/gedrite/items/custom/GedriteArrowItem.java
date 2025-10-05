package com.gedrite.items.custom;

import com.gedrite.world.entity.projectile.GedriteArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GedriteArrowItem extends ArrowItem {
    public GedriteArrowItem(Item.Properties p_40512_) {
        super(p_40512_);
    }
    
    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, ItemStack stack, @NotNull LivingEntity entity, @javax.annotation.Nullable ItemStack userStack) {
        return new GedriteArrow(level, entity, stack.copyWithCount(1), userStack);
    }

    @Override
    public @NotNull Projectile asProjectile(@NotNull Level level, Position pos, ItemStack stack, @NotNull Direction direction) {
        GedriteArrow gedriteArrow = new GedriteArrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), (ItemStack)null);
        gedriteArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return gedriteArrow;
    }
}
