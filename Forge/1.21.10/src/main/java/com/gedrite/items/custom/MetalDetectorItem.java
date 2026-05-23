package com.gedrite.items.custom;


import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!level.isClientSide()){
            level.playSound(null, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), ModSoundEvents.METAL_DETECTOR_USE.get(), SoundSource.BLOCKS);
            boolean foundBlock = false;

            int sphereRadius = 8;

            int radiusSq = sphereRadius * sphereRadius;

            for (int x = -sphereRadius; x <= sphereRadius; x++) {
                for (int y = -sphereRadius; y <= sphereRadius; y++) {
                    for (int z = -sphereRadius; z <= sphereRadius; z++) {
                        BlockPos blockPos = new BlockPos(player.getOnPos().getX() + x, player.getOnPos().getY() + y, player.getOnPos().getZ() + z);

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
                player.displayClientMessage(Component.translatable("gedrite.metal_detector.dontFoundBlock"), true);
            }
        }

        player.getItemInHand(hand).hurtAndBreak(1, player, hand.asEquipmentSlot());
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
