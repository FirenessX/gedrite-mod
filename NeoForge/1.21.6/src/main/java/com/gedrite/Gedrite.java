package com.gedrite;

import com.gedrite.blocks.ModBlocks;
import com.gedrite.core.cauldron.ModCauldronInteraction;
import com.gedrite.core.dispenser.ModDispenserItemBehavior;
import com.gedrite.core.particles.ModParticles;
import com.gedrite.event.ModEventBus;
import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import com.gedrite.items.ModCreativeModTabs;
import com.gedrite.items.ModItems;
import com.gedrite.world.effects.ModEffects;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Gedrite.MODID)
public class Gedrite {

    public static final String MODID = "gedrite";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Gedrite(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        modEventBus.addListener(ModEventBus::registerParticleFactories);
        modEventBus.addListener(ModFluidTypes::registerClientItemExtensions);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
//        NeoForge.EVENT_BUS.register(ModEventBusEvents.class);


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

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("aperik");
        LOGGER.info(ModFluidTypes.GEDRITED_WATER_STILL_RL.toString());
        LOGGER.info(ModFluidTypes.GEDRITED_WATER_FLOWING_RL.toString());
        LOGGER.info(ModFluidTypes.GEDRITED_WATER_OVERLAY_RL.toString());
    }
}
