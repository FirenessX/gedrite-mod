package com.gedrite.fluids;

import com.gedrite.Gedrite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation GEDRITED_WATER_STILL_RL = new ResourceLocation(Gedrite.MOD_ID, "block/gedrited_water_still");
    public static final ResourceLocation GEDRITED_WATER_FLOWING_RL = new ResourceLocation(Gedrite.MOD_ID, "block/gedrited_water_flow");
    public static final ResourceLocation GEDRITED_WATER_OVERLAY_RL = new ResourceLocation(Gedrite.MOD_ID, "misc/in_gedrited_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Gedrite.MOD_ID);

    public static final RegistryObject<FluidType> GEDRITED_WATER_FLUID_TYPE = register("gedrited_water",
            FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(false)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    );

    public static final RegistryObject<FluidType> TEST_FLUID_TYPE = registertest("test_water",
            FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(false)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
    );

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

    public static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BasicFluidType(GEDRITED_WATER_STILL_RL, GEDRITED_WATER_FLOWING_RL, GEDRITED_WATER_OVERLAY_RL,
                0xfff722d7, new Vector3f(135f / 255f, 34f / 255f, 102f / 255f), properties));
    }
    public static RegistryObject<FluidType> registertest(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BasicFluidType(GEDRITED_WATER_STILL_RL, GEDRITED_WATER_FLOWING_RL, GEDRITED_WATER_OVERLAY_RL,
                0xff00cc00, new Vector3f(0f, 34f / 255f, 0f), properties));
    }
}