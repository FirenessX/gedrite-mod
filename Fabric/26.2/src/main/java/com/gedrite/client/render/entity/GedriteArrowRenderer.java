package com.gedrite.client.render.entity;

import com.gedrite.Gedrite;
import com.gedrite.entity.custom.projectile.GedriteArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class GedriteArrowRenderer extends ArrowRenderer<GedriteArrow, ArrowRenderState> {
    public GedriteArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "textures/entity/projectiles/gedrite_arrow.png");
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }
}
