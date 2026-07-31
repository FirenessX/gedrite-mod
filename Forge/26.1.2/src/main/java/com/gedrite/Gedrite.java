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
import com.gedrite.sounds.ModSoundEvents;
import com.gedrite.world.effects.ModEffects;
import com.gedrite.world.entity.ModEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.gedrite.fluids.ModFluidTypes.gedritedWaterModel;

@Mod(Gedrite.MOD_ID)
public class Gedrite {

    public static final String MOD_ID = "gedrite";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Gedrite(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);
        BuildCreativeModeTabContentsEvent.BUS.addListener(this::addCreative);
        RegisterParticleProvidersEvent.BUS.addListener(ModEventBus::registerParticleFactories);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


        ModItems.register(modBusGroup);
        ModBlocks.register(modBusGroup);
        ModCreativeModTabs.register(modBusGroup);
        ModFluids.register(modBusGroup);
        ModFluidTypes.register(modBusGroup);
        ModEffects.register(modBusGroup);
        ModParticles.register(modBusGroup);
        ModSoundEvents.register(modBusGroup);
        ModEntities.register(modBusGroup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
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

        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.GEDRITE_ARROW);
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
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        static void onRegisterFluidModels(ModelEvent.BakeFluidModels event) {
            ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.getHolder().ifPresent(_ -> {
                Fluid stillFluid = ModFluids.SOURCE_GEDRITED_WATER.get();
                Fluid flowingFluid = ModFluids.FLOWING_GEDRITED_WATER.get();
                event.register(stillFluid, gedritedWaterModel.bake(event.materials(), () -> "Gedrited_Water"));
                event.register(flowingFluid, gedritedWaterModel.bake(event.materials(), () -> "Gedrited_Water"));
            });
        }
    }
}


