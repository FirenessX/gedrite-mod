package com.gedrite.items.custom;

import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item{
    public MetalDetectorItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos playerPos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
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
                                outputValuableCoordinates(blockPos, player, state.getBlock());
                                foundBlock = true;

                                break;
                            }
                        }
                    }
                }
            }
            if(!foundBlock){
                player.sendMessage(Text.translatable("gedrite.metal_detector.dontFoundBlock"));
            }
        }

        context.getStack().damage(1,context.getPlayer(), playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.translatable("gedrite.metal_detector.foundBlock"), false);
        player.sendMessage(Text.literal(block.asItem().getName().getString() + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(BlockState state){
        return state.isIn(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
