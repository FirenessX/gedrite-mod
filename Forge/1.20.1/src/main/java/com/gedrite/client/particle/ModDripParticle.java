package com.gedrite.client.particle;

import com.gedrite.core.particles.ModParticles;
import com.gedrite.fluids.ModFluids;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModDripParticle extends TextureSheetParticle {
    private final Fluid type;

    protected ModDripParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = pType;
        this.pickSprite(spriteSet);
    }

    public static TextureSheetParticle createGedritedWaterHangParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        DripHangParticle particle = new ModDripParticle.DripHangParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_GEDRITED_WATER.get(), ModParticles.FALLING_GEDRITED_WATER.get(), spriteSet);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        return particle;
    }

    public static TextureSheetParticle createGedritedWaterFallParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        FallingParticle.FallAndLandParticle particle = new ModDripParticle.FallingParticle.FallAndLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_GEDRITED_WATER.get(), ModParticles.LANDING_GEDRITED_WATER.get(), spriteSet);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        return particle;
    }

    public static TextureSheetParticle createGedritedWaterLandParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        DripLandParticle particle = new ModDripParticle.DripLandParticle(pLevel, pX, pY, pZ, ModFluids.SOURCE_GEDRITED_WATER.get(), spriteSet);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        return particle;
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }

    }

    protected void postMoveUpdate() {
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double) this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= (double) 0.98F;
                this.yd *= (double) 0.98F;
                this.zd *= (double) 0.98F;
                if (this.type != Fluids.EMPTY) {
                    BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidstate = this.level.getFluidState(blockpos);
                    if (fluidstate.getType() == this.type && this.y < (double) ((float) blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                        this.remove();
                    }

                }
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @OnlyIn(Dist.CLIENT)
    static class DripHangParticle extends ModDripParticle {
        private final ParticleOptions fallingParticle;

        DripHangParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions pFallingParticle, SpriteSet spriteSet) {
            super(pLevel, pX, pY, pZ, pType, spriteSet);
            this.fallingParticle = pFallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }

        }

        protected void postMoveUpdate() {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallingParticle extends ModDripParticle {
        FallingParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, SpriteSet spriteSet) {
            this(pLevel, pX, pY, pZ, pType, (int) (64.0D / (Math.random() * 0.8D + 0.2D)), spriteSet);
        }

        FallingParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, int pLifetime, SpriteSet spriteSet) {
            super(pLevel, pX, pY, pZ, pType, spriteSet);
            this.lifetime = pLifetime;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
            }

        }

        @OnlyIn(Dist.CLIENT)
        static class FallAndLandParticle extends ModDripParticle.FallingParticle {
            protected final ParticleOptions landParticle;

            FallAndLandParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions pLandParticle, SpriteSet spriteSet) {
                super(pLevel, pX, pY, pZ, pType, spriteSet);
                this.landParticle = pLandParticle;
            }

            protected void postMoveUpdate() {
                if (this.onGround) {
                    this.remove();
                    this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
                }

            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class DripLandParticle extends ModDripParticle {
        DripLandParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, SpriteSet spriteSet) {
            super(pLevel, pX, pY, pZ, pType, spriteSet);
            this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        }
    }
}