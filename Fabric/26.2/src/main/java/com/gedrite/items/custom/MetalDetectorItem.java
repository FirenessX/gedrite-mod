package com.gedrite.items.custom;

import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class MetalDetectorItem extends Item {
    public static BlockPos foundPos;
    public static BlockState foundState;

    public MetalDetectorItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        player.level().playSound(player, player.blockPosition(), ModSoundEvents.METAL_DETECTOR_USE, SoundSource.PLAYERS);
        if(level.isClientSide()){
            BlockPos playerPos = player.blockPosition();
            boolean foundBlock = false;

            int sphereRadius = 8;

            int radiusSq = sphereRadius * sphereRadius;

            for (int x = -sphereRadius; x <= sphereRadius; x++) {
                for (int y = -sphereRadius; y <= sphereRadius; y++) {
                    for (int z = -sphereRadius; z <= sphereRadius; z++) {
                        BlockPos blockPos = playerPos.offset(x, y, z);

                        int distanceSq = (x * x) + (y * y) + (z * z);
                        if (distanceSq <= radiusSq) {
                            BlockState state = level.getBlockState(blockPos);
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
                player.sendOverlayMessage(Component.translatable("gedrite.metal_detector.dontFoundBlock"));
            }
        }

        player.getMainHandItem().hurtAndBreak(1, player, hand.asEquipmentSlot());
        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendOverlayMessage(Component.translatable("gedrite.metal_detector.foundBlock"));
        player.sendOverlayMessage(Component.literal(block.asItem().getName(block.asItem().getDefaultInstance()).getString() + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
        if (player.level().isClientSide()) {
            foundPos = blockPos;
            foundState = player.level().getBlockState(blockPos);
        }
    }

    private boolean isValuableBlock(BlockState state){
        return state.is(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag flag) {
        textConsumer.accept(Component.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendHoverText(stack, context, displayComponent, textConsumer, flag);
    }
}