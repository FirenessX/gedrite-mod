package com.gedrite.datagen;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.GEDRITE_BLOCK);
        addDrop(ModBlocks.RAW_GEDRITE_BLOCK);

        addDrop(ModBlocks.GEDRITE_ORE, gedriteOreDrops(ModBlocks.GEDRITE_ORE));
        addDrop(ModBlocks.DEEPSLATE_GEDRITE_ORE, gedriteOreDrops(ModBlocks.DEEPSLATE_GEDRITE_ORE));

    }

    public LootTable.Builder gedriteOreDrops(Block drop) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop,
                (LootPoolEntry.Builder)
                        this.applyExplosionDecay(drop, ItemEntry.builder(ModItems.RAW_GEDRITE)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
