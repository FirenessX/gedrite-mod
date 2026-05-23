package com.gedrite.client.render.entity;

import com.gedrite.Gedrite;
import com.gedrite.entity.custom.projectile.GedriteArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

public class GedriteArrowEntityRenderer extends ProjectileEntityRenderer<GedriteArrowEntity, ProjectileEntityRenderState> {
    public GedriteArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
        return Identifier.of(Gedrite.MOD_ID, "textures/entity/projectiles/gedrite_arrow.png");
    }


    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }
}
