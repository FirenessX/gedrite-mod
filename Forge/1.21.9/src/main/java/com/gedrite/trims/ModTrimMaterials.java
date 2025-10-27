package com.gedrite.trims;

import com.gedrite.Gedrite;
import com.gedrite.items.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> GEDRITE =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(Gedrite.MOD_ID, "gedrite"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, GEDRITE, ModItems.GEDRITE_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#f734da").getOrThrow()));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style) {
        TrimMaterial trimmaterial = new TrimMaterial(MaterialAssetGroup.create("gedrite"),
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style));
        context.register(trimKey, trimmaterial);
    }
}
