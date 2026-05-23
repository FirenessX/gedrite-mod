package com.gedrite.items.custom;

import com.gedrite.entity.custom.projectile.GedriteArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GedriteArrowItem extends ArrowItem {
    public GedriteArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new GedriteArrowEntity(shooter, world, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        GedriteArrowEntity gedriteArrowEntity = new GedriteArrowEntity(pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1), (ItemStack)null);
        gedriteArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return gedriteArrowEntity;
    }
}
