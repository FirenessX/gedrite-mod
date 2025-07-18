package com.gedrite.items.custom;

import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class MetalDetectorItem extends Item{
    public MetalDetectorItem(net.minecraft.item.Item.Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos playerPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        if(!context.getWorld().isClient()){
            boolean foundBlock = false;

            int sphereRadius = 8;

            int radiusSq = sphereRadius * sphereRadius;

            for (int x = -sphereRadius; x <= sphereRadius; x++) {
                for (int y = -sphereRadius; y <= sphereRadius; y++) {
                    for (int z = -sphereRadius; z <= sphereRadius; z++) {
                        BlockPos blockPos = playerPos.add(x, y, z);

                        int distanceSq = (x * x) + (y * y) + (z * z);
                        if (distanceSq <= radiusSq) {
                            BlockState state = context.getWorld().getBlockState(blockPos);
                            if(isValuableBlock(state)){
                                assert playerEntity != null;
                                outputValuableCoordinates(blockPos, playerEntity, state.getBlock());
                                foundBlock = true;

                                break;
                            }
                        }
                    }
                }
            }
            if(!foundBlock){
                assert playerEntity != null;
                playerEntity.sendMessage(Text.translatable("gedrite.metal_detector.dontFoundBlock"), true);
            }
        }

        assert playerEntity != null;
        context.getStack().damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.translatable("gedrite.metal_detector.foundBlock"), true);
        player.sendMessage(Text.literal(block.asItem().getName().getString() + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), true);
    }

    private boolean isValuableBlock(BlockState state){
        return state.isIn(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
