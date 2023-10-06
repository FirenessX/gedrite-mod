package com.gedrite.items.custom;


import com.gedrite.blocks.ModBlocks;
import com.gedrite.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos playerPos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            int sphereRadius = 8;

            int radiusSq = sphereRadius * sphereRadius;

            for (int x = -sphereRadius; x <= sphereRadius; x++) {
                for (int y = -sphereRadius; y <= sphereRadius; y++) {
                    for (int z = -sphereRadius; z <= sphereRadius; z++) {
                        BlockPos blockPos = new BlockPos(playerPos.getX() + x, playerPos.getY() + y, playerPos.getZ() + z);

                        int distanceSq = (x * x) + (y * y) + (z * z);
                        if (distanceSq <= radiusSq) {
                            BlockState state = pContext.getLevel().getBlockState(blockPos);
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
                player.sendSystemMessage(Component.translatable("gedrite.metal_detector.dontFoundBlock"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.translatable("gedrite.metal_detector.foundBlock"));
        player.sendSystemMessage(Component.literal(I18n.get(block.getDescriptionId()) + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state){
        return state.is(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendHoverText(stack, pLevel, pTooltipComponents, pIsAdvanced);
    }


}
