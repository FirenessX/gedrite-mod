    package com.gedrite.datagen.loot;

    import com.gedrite.blocks.ModBlocks;
    import com.gedrite.items.ModItems;
    import net.minecraft.data.loot.BlockLootSubProvider;
    import net.minecraft.world.flag.FeatureFlagSet;
    import net.minecraft.world.flag.FeatureFlags;
    import net.minecraft.world.item.Item;
    import net.minecraft.world.item.Items;
    import net.minecraft.world.item.enchantment.Enchantments;
    import net.minecraft.world.level.block.Block;
    import net.minecraft.world.level.storage.loot.LootTable;
    import net.minecraft.world.level.storage.loot.entries.LootItem;
    import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
    import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
    import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
    import net.minecraftforge.registries.RegistryObject;

    import java.util.Set;

    public class ModBlockLootTables extends BlockLootSubProvider {

        public ModBlockLootTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            this.dropSelf(ModBlocks.GEDRITE_BLOCK.get());
            this.dropSelf(ModBlocks.RAW_GEDRITE_BLOCK.get());

            this.add(ModBlocks.GEDRITE_ORE.get(),
                    block -> createGedriteOreDrops(ModBlocks.GEDRITE_ORE.get(), ModItems.RAW_GEDRITE.get()));

            this.add(ModBlocks.DEEPSLATE_GEDRITE_ORE.get(),
                    block -> createGedriteOreDrops(ModBlocks.DEEPSLATE_GEDRITE_ORE.get(), ModItems.RAW_GEDRITE.get()));


        }

        protected LootTable.Builder createGedriteOreDrops(Block pBlock, Item item) {
            return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(ModItems.RAW_GEDRITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks(){
            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }
