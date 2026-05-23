package com.gedrite.trims;

import com.gedrite.Gedrite;
import com.gedrite.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> GEDRITE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(Gedrite.MOD_ID, "gedrite"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, GEDRITE, Registries.ITEM.getEntry(ModItems.GEDRITE_INGOT), Style.EMPTY.withColor(TextColor.parse("#f734da").getOrThrow()));
    }

    public static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey, RegistryEntry<Item> item, Style style)
    {
        ArmorTrimMaterial armorTrimMaterial = new ArmorTrimMaterial(ArmorTrimAssets.of("gedrite"),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));
        registerable.register(armorTrimKey, armorTrimMaterial);

    }
}
