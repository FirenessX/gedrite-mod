package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootSubProvider {
    public ModLootTableGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.GEDRITE_BLOCK);
        dropSelf(ModBlocks.RAW_GEDRITE_BLOCK);

        add(ModBlocks.GEDRITE_ORE, oreDrops(ModBlocks.GEDRITE_ORE, 1.0f, 4.0f));
        add(ModBlocks.DEEPSLATE_GEDRITE_ORE, oreDrops(ModBlocks.DEEPSLATE_GEDRITE_ORE, 1.0f, 4.0f));

        dropSelf(ModBlocks.BLACK_SAPOTE_LOG);
        dropSelf(ModBlocks.BLACK_SAPOTE_WOOD);
        dropSelf(ModBlocks.STRIPPED_BLACK_SAPOTE_LOG);
        dropSelf(ModBlocks.STRIPPED_BLACK_SAPOTE_WOOD);
        dropSelf(ModBlocks.BLACK_SAPOTE_PLANKS);
        dropSelf(ModBlocks.BLACK_SAPOTE_SAPLING);

        add(ModBlocks.BLACK_SAPOTE_LEAVES, createLeavesDrops(ModBlocks.BLACK_SAPOTE_LEAVES, ModBlocks.BLACK_SAPOTE_SAPLING, 0.0625f));
    }

    public LootTable.Builder oreDrops(@NonNull Block block, float min, float max) {
        HolderGetter<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                ((LootPoolSingletonContainer.Builder<?>) LootItem.lootTableItem(ModItems.RAW_GEDRITE).apply(SetItemCountFunction
                        .setCount(UniformGenerator.between(min, max)))).apply(ApplyBonusCount.addOreBonusCount(
                                enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
