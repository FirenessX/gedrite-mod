package com.gedrite.client.particle;

import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;

@Environment(value= EnvType.CLIENT)
public abstract class ModBlockLeakParticle extends SingleQuadParticle {
    private final Fluid fluid;
    protected ModBlockLeakParticle(ClientLevel level, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.setSize(0.01f, 0.01f);
        this.gravity = 0.06f;
        this.setSprite(sprite);
        this.fluid = fluid;
    }

    public static SingleQuadParticle createDrippingGedritedWater(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd, TextureAtlasSprite sprite) {
//        BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
//        if (!level.getFluidState(pos.down()).isIn(ModTags.Fluids.GEDRITED_WATER)) {
            Dripping particle = new Dripping(level, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.FALLING_GEDRITED_WATER, sprite);
            particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
            particle.setSprite(sprite);
            return particle;
//        }
//        return null;
    }

    public static SingleQuadParticle createFallingGedritedWater(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd, TextureAtlasSprite sprite) {
        ContinuousFalling particle = new ContinuousFalling(level, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.LANDING_GEDRITED_WATER, sprite);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        particle.setSprite(sprite);
        return particle;
    }

    public static SingleQuadParticle createLandingGedritedWater(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd, TextureAtlasSprite sprite) {
        Landing particle = new Landing(level, x, y, z, ModFluids.GEDRITED_WATER, sprite);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        particle.setSprite(sprite);
        return particle;
    }

//    public static BillboardParticle createDrippingDripstoneGedritedWater(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd, Sprite sprite) {
//        BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
//        if (level.getBlockState(pos.down()).getFluidState().getFluid() != ModFluids.GEDRITED_WATER) {
//            Dripping particle = new Dripping(level, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.FALLING_DRIPSTONE_GEDRITED_WATER, sprite);
//            particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
//            particle.setSprite(sprite);
//            return particle;
//        }
//        return null;
//    }
//
//    public static BillboardParticle createFallingDripstoneGedritedWater(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd, Sprite sprite) {
//        FallingGedritedWater particle = new FallingGedritedWater(level, x, y, z, ModFluids.GEDRITED_WATER, sprite);
//        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
//        particle.setSprite(sprite);
//        return particle;
//    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.updateAge();
        if (this.removed) {
            return;
        }
        this.yd -= (double)this.gravity;
        this.move(this.xd, this.yd, this.zd);
        this.updateVelocity();
        if (this.removed) {
            return;
        }
        this.xd *= (double)0.98f;
        this.yd *= (double)0.98f;
        this.zd *= (double)0.98f;
        if (this.fluid == Fluids.EMPTY) {
            return;
        }
        BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
        FluidState fluidState = this.level.getFluidState(blockPos);
        if (fluidState.getType() == this.fluid && this.y < (double)((float)blockPos.getY() + fluidState.getHeight(this.level, blockPos))) {
            this.remove();
        }
    }

    protected void updateVelocity() {
    }

    protected void updateAge() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected Fluid getFluid() {
        return this.fluid;
    }


//    @Environment(EnvType.CLIENT)
//    static class Dripping extends GedritedWaterParticle {
//        private final ParticleEffect nextParticle;
//        protected Dripping(ClientLevel level, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
//            super(level, x, y, z, fluid, sprite);
//            this.nextParticle = nextParticle;
//            this.gravityStrength *= 0.02f;
//            this.lifetime = 20;  // Уменьшим lifetime, чтобы частица быстрее начинала падать
//        }
//
//        @Override
//        protected void updateAge() {
//            if (this.lifetime-- <= 0) {
//                this.remove();
//                this.level.addParticleClient(this.nextParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
//            }
//        }
//
//        @Override
//        protected void updateVelocity() {
//            this.xd *= 0.02;
//            this.yd *= 0.02;
//            this.zd *= 0.02;
//        }
//    }
//
//    @Environment(EnvType.CLIENT)
//    static class FallingGedritedWater extends GedritedWaterParticle {
//        protected FallingGedritedWater(ClientLevel level, double x, double y, double z, Fluid fluid, Sprite sprite) {
//            super(level, x, y, z, fluid, sprite);
//            this.lifetime = 40;
//        }
//
//        @Override
//        protected void updateVelocity() {
//            this.xd *= 0.98;
//            this.yd *= 0.98;
//            this.zd *= 0.98;
//        }
//
//        @Override
//        public void tick() {
//            super.tick();
//            if (this.onGround) {
//                this.remove();
//                this.level.addParticleClient(ModParticleTypes.SPLASH_GEDRITED_WATER, this.x, this.y, this.z, 0.0, 0.0, 0.0);
//            }
//        }
//    }

    @Environment(value=EnvType.CLIENT)
    static class Dripping
            extends ModBlockLeakParticle {
        private final SimpleParticleType nextParticle;

        Dripping(ClientLevel level, double x, double y, double z, Fluid fluid, SimpleParticleType nextParticle, TextureAtlasSprite sprite) {
            super(level, x, y, z, fluid, sprite);
            this.nextParticle = nextParticle;
            this.gravity *= 0.02f;
            this.lifetime = 40;
        }

        @Override
        protected void updateAge() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.nextParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        @Override
        protected void updateVelocity() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }

        @Override
        protected Layer getLayer() {
            return Layer.OPAQUE;
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class ContinuousFalling
            extends Falling {
        protected final SimpleParticleType nextParticle;

        ContinuousFalling(ClientLevel level, double x, double y, double z, Fluid fluid, SimpleParticleType nextParticle, TextureAtlasSprite sprite) {
            super(level, x, y, z, fluid, sprite);
            this.nextParticle = nextParticle;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class Landing
            extends ModBlockLeakParticle {
        Landing(ClientLevel ClientLevel, double d, double e, double f, Fluid fluid, TextureAtlasSprite sprite) {
            super(ClientLevel, d, e, f, fluid, sprite);
            this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        protected Layer getLayer() {
            return Layer.OPAQUE;
        }
    }


//    @Environment(value=EnvType.CLIENT)
//    static class DripstoneDrip
//            extends ContinuousFalling {
//        DripstoneDrip(ClientLevel ClientLevel, double d, double e, double f, Fluid fluid, SimpleParticleType nextParticle, TextureAtlasSprite sprite) {
//            super(ClientLevel, d, e, f, fluid, nextParticle, sprite);
//        }
//
//        @Override
//        protected void updateVelocity() {
//            if (this.onGround) {
//                this.remove();
//                this.level.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
//                SoundEvent soundEvent = this.getFluid() == Fluids.LAVA ? SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA : SoundEvents.POINTED_DRIPSTONE_DRIP_WATER;
//                float f = Mth.randomBetween(this.random, 0.3f, 1.0f);
//                this.level.playLocalSound(this.x, this.y, this.z, soundEvent, SoundSource.BLOCKS, f, 1.0f, false);
//            }
//        }
//    }

    @Environment(value=EnvType.CLIENT)
    static class Falling
            extends ModBlockLeakParticle {
        Falling(ClientLevel ClientLevel, double d, double e, double f, Fluid fluid, TextureAtlasSprite sprite) {
            this(ClientLevel, d, e, f, fluid, (int)(64.0 / (Math.random() * 0.8 + 0.2)), sprite);
        }

        Falling(ClientLevel level, double x, double y, double z, Fluid fluid, int lifetime, TextureAtlasSprite sprite) {
            super(level, x, y, z, fluid, sprite);
            this.lifetime = lifetime;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.remove();
            }
        }

        @Override
        protected Layer getLayer() {
            return Layer.OPAQUE;
        }
    }
}
