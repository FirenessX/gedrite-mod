package com.gedrite;

import com.gedrite.client.renderer.entity.GedriteArrowRenderer;
import com.gedrite.fluids.ModFluids;
import com.gedrite.world.entity.ModEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Gedrite.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Gedrite.MOD_ID, value = Dist.CLIENT)
public class GedriteClient {
    public GedriteClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_GEDRITED_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_GEDRITED_WATER.get(), ChunkSectionLayer.TRANSLUCENT);

            EntityRenderers.register(ModEntities.GEDRITE_ARROW.get(), GedriteArrowRenderer::new);

        });

    }
}
