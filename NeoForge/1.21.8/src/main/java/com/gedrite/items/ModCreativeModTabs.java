package com.gedrite.items;

import com.gedrite.Gedrite;
import com.gedrite.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Gedrite.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEDRITE_TAB = CREATIVE_MODE_TABS.register("gedirte_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEDRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.gedrite_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.GEDRITE_INGOT.get());
                        pOutput.accept(ModItems.RAW_GEDRITE.get());
                        pOutput.accept(ModItems.GEDRITED_COAL.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.GEDRITED_WATER_BUCKET.get());

                        pOutput.accept(ModBlocks.GEDRITE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_GEDRITE_BLOCK.get());
                        pOutput.accept(ModBlocks.GEDRITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_GEDRITE_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}