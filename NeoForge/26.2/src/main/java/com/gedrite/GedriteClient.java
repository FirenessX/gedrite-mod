package com.gedrite;

import com.gedrite.fluids.ModFluidTypes;
import com.gedrite.fluids.ModFluids;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static com.gedrite.fluids.ModFluidTypes.gedritedWaterModel;

@Mod(value = Gedrite.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Gedrite.MOD_ID, value = Dist.CLIENT)
public class GedriteClient {
    public GedriteClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onRegisterFluidModels(RegisterFluidModelsEvent event) {
        ModFluidTypes.GEDRITED_WATER_FLUID_TYPE.asOptional().ifPresent(_ -> {
            Fluid stillFluid = ModFluids.SOURCE_GEDRITED_WATER.value();
            Fluid flowingFluid = ModFluids.FLOWING_GEDRITED_WATER.value();
            event.register(gedritedWaterModel, stillFluid, flowingFluid);
        });
    }
}
