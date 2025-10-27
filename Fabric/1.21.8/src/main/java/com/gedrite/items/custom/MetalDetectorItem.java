package com.gedrite.items.custom;

import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class MetalDetectorItem extends Item{
    public static BlockPos foundPos;
    public static BlockState foundState;
    public MetalDetectorItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.getWorld().playSound(user, user.getBlockPos(), ModSoundEvents.METAL_DETECTOR_USE, SoundCategory.PLAYERS);
        if(world.isClient()){
            BlockPos playerPos = user.getBlockPos();
            boolean foundBlock = false;

            int sphereRadius = 8;

            int radiusSq = sphereRadius * sphereRadius;

            for (int x = -sphereRadius; x <= sphereRadius; x++) {
                for (int y = -sphereRadius; y <= sphereRadius; y++) {
                    for (int z = -sphereRadius; z <= sphereRadius; z++) {
                        BlockPos blockPos = playerPos.add(x, y, z);

                        int distanceSq = (x * x) + (y * y) + (z * z);
                        if (distanceSq <= radiusSq) {
                            BlockState state = world.getBlockState(blockPos);
                            if(isValuableBlock(state)){
                                outputValuableCoordinates(blockPos, user, state.getBlock());
                                foundBlock = true;

                                break;
                            }
                        }
                    }
                }
            }
            if(!foundBlock){
                user.sendMessage(Text.translatable("gedrite.metal_detector.dontFoundBlock"), true);
            }
        }

        user.getMainHandStack().damage(1, user, LivingEntity.getSlotForHand(hand));
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.translatable("gedrite.metal_detector.foundBlock"), true);
        player.sendMessage(Text.literal(block.asItem().getName().getString() + " (" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), true);
        if (player.getWorld().isClient) {
            foundPos = blockPos;
            foundState = player.getWorld().getBlockState(blockPos);
        }
    }

    private boolean isValuableBlock(BlockState state){
        return state.isIn(ModTags.Blocks.METAL_BLOCKS_FOR_MD);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.gedrite.metal_detector.tooltip"));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}