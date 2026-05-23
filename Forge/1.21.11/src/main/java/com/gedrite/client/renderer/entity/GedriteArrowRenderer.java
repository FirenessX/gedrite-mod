package com.gedrite.client.renderer.entity;

import com.gedrite.Gedrite;
import com.gedrite.world.entity.projectile.GedriteArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class GedriteArrowRenderer extends ArrowRenderer<GedriteArrow, ArrowRenderState> {
    public GedriteArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected @NotNull Identifier getTextureLocation(@NotNull ArrowRenderState p_364566_) {
        return Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "textures/entity/projectiles/gedrite_arrow.png");
    }
}