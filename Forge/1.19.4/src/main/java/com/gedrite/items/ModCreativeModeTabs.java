package com.gedrite.items;

import com.gedrite.Gedrite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gedrite.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab GEDRITE_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        GEDRITE_TAB = event.registerCreativeModeTab(new ResourceLocation(Gedrite.MOD_ID, "tutorial_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.GEDRITE_INGOT.get()))
                        .title(Component.translatable("creativetab.gedrite_tab")));
    }
}