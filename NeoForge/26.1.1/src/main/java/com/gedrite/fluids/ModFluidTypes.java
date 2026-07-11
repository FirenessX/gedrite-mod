package com.gedrite.fluids;

import com.gedrite.Gedrite;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;
import org.jspecify.annotations.NonNull;

public class ModFluidTypes {
    public static final Identifier GEDRITED_WATER_STILL_RL = Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "block/gedrited_water_still");
    public static final Identifier GEDRITED_WATER_FLOWING_RL = Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "block/gedrited_water_flow");
    public static final Identifier GEDRITED_WATER_OVERLAY_RL = Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "misc/in_gedrited_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Gedrite.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> GEDRITED_WATER_FLUID_TYPE = FLUID_TYPES.register("gedrited_water", () -> new FluidType(FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(false)
                    .motionScale(0.003)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    ));

    public static final DeferredHolder<FluidType, FluidType> TEST_FLUID_TYPE = FLUID_TYPES.register("test_water", () -> new FluidType(FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(false)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    ));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

    public static final IClientFluidTypeExtensions GEDRITED_WATER_FLUID = new IClientFluidTypeExtensions() {
//        @Override
//        public @NotNull Identifier getStillTexture() {
//            return GEDRITED_WATER_STILL_RL;
//        }
//
//        @Override
//        public @NotNull Identifier getFlowingTexture() {
//            return GEDRITED_WATER_FLOWING_RL;
//        }
//
//        @Override
//        public Identifier getOverlayTexture() {
//            return GEDRITED_WATER_OVERLAY_RL;
//        }
//
//        @Override
//        public int getTintColor() {
//            return 0xfff722d7;
//        }

        @Override
        public @NotNull void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
            fluidFogColor.set(135f / 255f, 34f / 255f, 102f / 255f, 1.0F);
        }

        @Override
        public void modifyFogRender(@NonNull Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
            fogData.renderDistanceStart = -5.0F;
            fogData.renderDistanceEnd = 10.0F;
            fogData.environmentalStart = -5.0F;
            fogData.environmentalEnd = 10.0F;
            fogData.skyEnd = 10.0F;
            fogData.cloudEnd = 10.0F;
        }
    };

    public static void registerClientItemExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(GEDRITED_WATER_FLUID, ModFluidTypes.GEDRITED_WATER_FLUID_TYPE);
    }

    public static final FluidModel.Unbaked gedritedWaterModel = new FluidModel.Unbaked(
            new Material(GEDRITED_WATER_STILL_RL, true),
            new Material(GEDRITED_WATER_FLOWING_RL, true),
            new Material(GEDRITED_WATER_OVERLAY_RL, true),
            _ -> 0xfff722d7
    );
}