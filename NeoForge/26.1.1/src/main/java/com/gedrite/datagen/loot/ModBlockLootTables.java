    package com.gedrite.datagen.loot;

    import com.gedrite.blocks.ModBlocks;
    import com.gedrite.items.ModItems;
    import net.minecraft.core.Holder;
    import net.minecraft.core.HolderLookup;
    import net.minecraft.core.registries.Registries;
    import net.minecraft.data.loot.BlockLootSubProvider;
    import net.minecraft.world.flag.FeatureFlags;
    import net.minecraft.world.item.enchantment.Enchantment;
    import net.minecraft.world.item.enchantment.Enchantments;
    import net.minecraft.world.level.block.Block;
    import net.minecraft.world.level.storage.loot.LootTable;
    import net.minecraft.world.level.storage.loot.entries.LootItem;
    import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
    import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
    import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
    import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
    import net.neoforged.neoforge.registries.DeferredBlock;
    import net.neoforged.neoforge.registries.DeferredHolder;
    import org.jetbrains.annotations.NotNull;

    import java.util.Set;

    public class ModBlockLootTables extends BlockLootSubProvider {

        public ModBlockLootTables(HolderLookup.Provider pRegistries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
        }

        @Override
        protected void generate() {
            dropSelf(ModBlocks.GEDRITE_BLOCK.get());
            dropSelf(ModBlocks.RAW_GEDRITE_BLOCK.get());

            add(ModBlocks.GEDRITE_ORE.get(),
                    block -> createGedriteOreDrops(ModBlocks.GEDRITE_ORE.get()));

            add(ModBlocks.DEEPSLATE_GEDRITE_ORE.get(),
                    block -> createGedriteOreDrops(ModBlocks.DEEPSLATE_GEDRITE_ORE.get()));


        }

        public LootTable.Builder createGedriteOreDrops(Block pBlock) {
            HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
            return createSilkTouchDispatchTable(pBlock,
                    (LootPoolEntryContainer.Builder)
                            applyExplosionDecay(pBlock, LootItem.lootTableItem(ModItems.RAW_GEDRITE.get())
                                    .apply(SetItemCountFunction
                                            .setCount(UniformGenerator.between(1.0f, 4.0f)))
                                    .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }
