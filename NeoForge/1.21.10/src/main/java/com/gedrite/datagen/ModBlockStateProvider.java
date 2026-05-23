//package com.gedrite.datagen;
//
//import com.gedrite.Gedrite;
//import com.gedrite.blocks.ModBlocks;
//import net.minecraft.core.BlockPos;
//import net.minecraft.data.PackOutput;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
//import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
//import net.neoforged.neoforge.registries.DeferredBlock;
//
//public class ModBlockStateProvider extends BlockStateProvider {
//
//    @Override
//    protected void registerStatesAndModels() {
//        blockWithItem(ModBlocks.GEDRITE_BLOCK);
//        blockWithItem(ModBlocks.RAW_GEDRITE_BLOCK);
//        blockWithItem(ModBlocks.GEDRITE_ORE);
//        blockWithItem(ModBlocks.DEEPSLATE_GEDRITE_ORE);
//    }
//
//    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
//    }
//
//    @Override
//    protected BlockStateProviderType<?> type() {
//        return null;
//    }
//
//    @Override
//    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
//        return null;
//    }
//}
