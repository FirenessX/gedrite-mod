package com.gedrite.client.particle;

import com.gedrite.fluids.ModFluids;
import com.gedrite.particles.ModParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

@Environment(value= EnvType.CLIENT)
public abstract class ModBlockLeakParticle extends BillboardParticle {
    private final Fluid fluid;
    protected ModBlockLeakParticle(ClientWorld world, double x, double y, double z, Fluid fluid, Sprite sprite) {
        super(world, x, y, z, sprite);
        this.setBoundingBoxSpacing(0.01f, 0.01f);
        this.gravityStrength = 0.06f;
        this.setSprite(sprite);
        this.fluid = fluid;
    }

    public static BillboardParticle createDrippingGedritedWater(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
//        BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
//        if (!world.getFluidState(pos.down()).isIn(ModTags.Fluids.GEDRITED_WATER)) {
            Dripping particle = new Dripping(world, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.FALLING_GEDRITED_WATER, sprite);
            particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
            particle.setSprite(sprite);
            return particle;
//        }
//        return null;
    }

    public static BillboardParticle createFallingGedritedWater(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
        ContinuousFalling particle = new ContinuousFalling(world, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.LANDING_GEDRITED_WATER, sprite);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        particle.setSprite(sprite);
        return particle;
    }

    public static BillboardParticle createLandingGedritedWater(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
        Landing particle = new Landing(world, x, y, z, ModFluids.GEDRITED_WATER, sprite);
        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
        particle.setSprite(sprite);
        return particle;
    }

//    public static BillboardParticle createDrippingDripstoneGedritedWater(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
//        BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
//        if (world.getBlockState(pos.down()).getFluidState().getFluid() != ModFluids.GEDRITED_WATER) {
//            Dripping particle = new Dripping(world, x, y, z, ModFluids.GEDRITED_WATER, ModParticleTypes.FALLING_DRIPSTONE_GEDRITED_WATER, sprite);
//            particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
//            particle.setSprite(sprite);
//            return particle;
//        }
//        return null;
//    }
//
//    public static BillboardParticle createFallingDripstoneGedritedWater(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
//        FallingGedritedWater particle = new FallingGedritedWater(world, x, y, z, ModFluids.GEDRITED_WATER, sprite);
//        particle.setColor((float) 135/255, (float) 34/255, (float) 102/255);
//        particle.setSprite(sprite);
//        return particle;
//    }

    @Override
    public BillboardParticle.RenderType getRenderType() {
        return BillboardParticle.RenderType.PARTICLE_ATLAS_OPAQUE;
    }

    @Override
    public void tick() {
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;
        this.updateAge();
        if (this.dead) {
            return;
        }
        this.velocityY -= (double)this.gravityStrength;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.updateVelocity();
        if (this.dead) {
            return;
        }
        this.velocityX *= (double)0.98f;
        this.velocityY *= (double)0.98f;
        this.velocityZ *= (double)0.98f;
        if (this.fluid == Fluids.EMPTY) {
            return;
        }
        BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
        FluidState fluidState = this.world.getFluidState(blockPos);
        if (fluidState.getFluid() == this.fluid && this.y < (double)((float)blockPos.getY() + fluidState.getHeight(this.world, blockPos))) {
            this.markDead();
        }
    }

    protected void updateVelocity() {
    }

    protected void updateAge() {
        if (this.maxAge-- <= 0) {
            this.markDead();
        }
    }

    protected Fluid getFluid() {
        return this.fluid;
    }


//    @Environment(EnvType.CLIENT)
//    static class Dripping extends GedritedWaterParticle {
//        private final ParticleEffect nextParticle;
//        protected Dripping(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
//            super(world, x, y, z, fluid, sprite);
//            this.nextParticle = nextParticle;
//            this.gravityStrength *= 0.02f;
//            this.maxAge = 20;  // Уменьшим maxAge, чтобы частица быстрее начинала падать
//        }
//
//        @Override
//        protected void updateAge() {
//            if (this.maxAge-- <= 0) {
//                this.markDead();
//                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
//            }
//        }
//
//        @Override
//        protected void updateVelocity() {
//            this.velocityX *= 0.02;
//            this.velocityY *= 0.02;
//            this.velocityZ *= 0.02;
//        }
//    }
//
//    @Environment(EnvType.CLIENT)
//    static class FallingGedritedWater extends GedritedWaterParticle {
//        protected FallingGedritedWater(ClientWorld world, double x, double y, double z, Fluid fluid, Sprite sprite) {
//            super(world, x, y, z, fluid, sprite);
//            this.maxAge = 40;
//        }
//
//        @Override
//        protected void updateVelocity() {
//            this.velocityX *= 0.98;
//            this.velocityY *= 0.98;
//            this.velocityZ *= 0.98;
//        }
//
//        @Override
//        public void tick() {
//            super.tick();
//            if (this.onGround) {
//                this.markDead();
//                this.world.addParticleClient(ModParticleTypes.SPLASH_GEDRITED_WATER, this.x, this.y, this.z, 0.0, 0.0, 0.0);
//            }
//        }
//    }

    @Environment(value=EnvType.CLIENT)
    static class Dripping
            extends ModBlockLeakParticle {
        private final ParticleEffect nextParticle;

        Dripping(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
            super(world, x, y, z, fluid, sprite);
            this.nextParticle = nextParticle;
            this.gravityStrength *= 0.02f;
            this.maxAge = 40;
        }

        @Override
        protected void updateAge() {
            if (this.maxAge-- <= 0) {
                this.markDead();
                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
            }
        }

        @Override
        protected void updateVelocity() {
            this.velocityX *= 0.02;
            this.velocityY *= 0.02;
            this.velocityZ *= 0.02;
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class ContinuousFalling
            extends Falling {
        protected final ParticleEffect nextParticle;

        ContinuousFalling(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
            super(world, x, y, z, fluid, sprite);
            this.nextParticle = nextParticle;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class Landing
            extends ModBlockLeakParticle {
        Landing(ClientWorld clientWorld, double d, double e, double f, Fluid fluid, Sprite sprite) {
            super(clientWorld, d, e, f, fluid, sprite);
            this.maxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }
    }


    @Environment(value=EnvType.CLIENT)
    static class DripstoneDrip
            extends ContinuousFalling {
        DripstoneDrip(ClientWorld clientWorld, double d, double e, double f, Fluid fluid, ParticleEffect particleEffect, Sprite sprite) {
            super(clientWorld, d, e, f, fluid, particleEffect, sprite);
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
                SoundEvent soundEvent = this.getFluid() == Fluids.LAVA ? SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA : SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER;
                float f = MathHelper.nextBetween(this.random, 0.3f, 1.0f);
                Random random = new Random();
                this.world.playSoundClient(this.x, this.y, this.z, soundEvent, SoundCategory.BLOCKS, f, 1.0f, false);
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class Falling
            extends ModBlockLeakParticle {
        Falling(ClientWorld clientWorld, double d, double e, double f, Fluid fluid, Sprite sprite) {
            this(clientWorld, d, e, f, fluid, (int)(64.0 / (Math.random() * 0.8 + 0.2)), sprite);
        }

        Falling(ClientWorld world, double x, double y, double z, Fluid fluid, int maxAge, Sprite sprite) {
            super(world, x, y, z, fluid, sprite);
            this.maxAge = maxAge;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
            }
        }
    }
}
