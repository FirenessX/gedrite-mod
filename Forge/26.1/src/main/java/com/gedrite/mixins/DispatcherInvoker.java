package com.gedrite.mixins;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CauldronInteraction.Dispatcher.class)
public interface DispatcherInvoker {
    @Invoker(value = "put", remap = false)
    void gedrite$put(Item item, CauldronInteraction interaction);
}
