package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.core.cauldron.ModCauldronInteraction;
import com.gedrite.core.dispenser.ModDispenserItemBehavior;
import com.gedrite.core.particles.ModParticles;
import com.gedrite.event.ModEventBus;
import com.gedrite.event.ModEventBusEvents;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModCreativeModTabs;
import com.gedrite.items.ModItems;
import com.gedrite.world.effects.ModEffects;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Gedrite.MOD_ID)
public class Gedrite {

    public static final String MOD_ID = "gedrite";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Gedrite() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(ModEventBus.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ModEventBusEvents.class);
        event.enqueueWork(() -> {
            ModCauldronInteraction.registerBehavior();
            ModDispenserItemBehavior.registerBehavior();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GEDRITE_INGOT);
            event.accept(ModItems.RAW_GEDRITE);
            event.accept(ModItems.GEDRITED_COAL);
        }

        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.GEDRITE_INGOT);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.METAL_DETECTOR);
            event.accept(ModItems.GEDRITED_WATER_BUCKET);

        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.GEDRITE_BLOCK);
        }

        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.RAW_GEDRITE_BLOCK);
            event.accept(ModBlocks.GEDRITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_GEDRITE_ORE);
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_GEDRITED_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_GEDRITED_WATER.get(), RenderType.translucent());
        }
    }
}


