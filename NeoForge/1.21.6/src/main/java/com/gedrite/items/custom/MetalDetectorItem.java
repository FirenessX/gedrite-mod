package com.gedrite.items.custom;


import com.gedrite.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        BlockPos playerPos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        if(!pContext.getLevel().isClientSide()){
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
//                            System.out.println(state);
//                            System.out.println(state.getTags());
//                            System.out.println(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
                            if(isValuableBlock(state)){
//                                System.out.println("OPA");
                                assert player != null;
                                outputValuableCoordinates(blockPos, player, state.getBlock());
                                foundBlock = true;

                                break;
                            }
                        }
                    }
                }
            }
            if(!foundBlock){
//                System.out.println("5");
                assert player != null;
                player.displayClientMessage(Component.translatable("gedrite.metal_detector.dontFoundBlock"), true);
            }
        }

        assert player != null;
        pContext.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.displayClientMessage(Component.translatable("gedrite.metal_detector.foundBlock"), true);
        player.displayClientMessage(Component.literal(I18n.get(block.getDescriptionId()) + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), true);
    }

    private boolean isValuableBlock(BlockState state){
        return state.is(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull TooltipDisplay tooltipDisplay, @NotNull Consumer<Component> tooltipComponent, @NotNull TooltipFlag flag) {
        tooltipComponent.accept(Component.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipComponent, flag);
    }


}
