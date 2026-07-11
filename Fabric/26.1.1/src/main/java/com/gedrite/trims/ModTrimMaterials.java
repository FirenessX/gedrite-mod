package com.gedrite.trims;

import com.gedrite.Gedrite;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> GEDRITE =
            ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite"));

    public static void bootstrap(BootstrapContext<TrimMaterial> registerable) {
        register(registerable, GEDRITE, Style.EMPTY.withColor(0xF734DA), MaterialAssetGroup.create("gedrite"));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> resourceKey, Style hoverTextStyle, MaterialAssetGroup assets) {
        MutableComponent description = Component.translatable(Util.makeDescriptionId("trim_material", resourceKey.identifier())).withStyle(hoverTextStyle);
        context.register(resourceKey, new TrimMaterial(assets, description));
    }
}