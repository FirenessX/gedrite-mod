package com.gedrite.blocks.custom;

import com.gedrite.fluids.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GedritedWaterBlock extends FluidBlock {

    public GedritedWaterBlock() {
        super(ModFluids.GEDRITED_WATER, Block.Settings.copy(Blocks.WATER).noCollision().strength(100.0F, 100.0F).dropsNothing());
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            // Check if the entity is a player and can swim
            if (livingEntity instanceof PlayerEntity && canSwim((PlayerEntity) livingEntity)) {
                handleSwimming(world, pos, livingEntity);
            }
        }
    }

    private boolean canSwim(PlayerEntity player) {
        // Add your logic to determine if the player can swim (e.g., check for potion effects)
        // For simplicity, let's assume all players can swim
        return true;
    }

    private void handleSwimming(World world, BlockPos pos, LivingEntity entity) {
        // Handle swimming effects (e.g., adjust pose, apply particle effects)
        entity.setSwimming(true);

        // Optionally, adjust the entity's velocity to simulate swimming
        Vec3d motion = entity.getVelocity();
        double swimSpeed = 0.1;
        entity.setVelocity(motion.x, swimSpeed, motion.z);
    }
}
