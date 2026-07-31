package com.gedrite.world.tree;

import com.gedrite.Gedrite;
import com.gedrite.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower BLACK_SAPOTE = new TreeGrower(Gedrite.MOD_ID + ":black_sapote",
            Optional.of(ModConfiguredFeatures.BLACK_SAPOTE_KEY), Optional.empty(), Optional.empty());
}
