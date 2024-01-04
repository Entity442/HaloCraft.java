package com.harby.halocraft.Message;

import com.harby.halocraft.HaloItems.EnergieSword;
import com.harby.halocraft.HaloItems.Gun;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class HandleReloadingModels {
    public static void addReloading() {
        for (Gun gun : HaloItems.GUNS_ITEMS) {
            makeGun(gun);
        }
        makeEnergySword(HaloItems.ENERGIE_SWORD.get());
    }

    private static void makeGun(Gun item) {
        ItemProperties.register(item, new ResourceLocation("shooting"), (pStack, p_174606_, property, p_174608_) -> {
            return property != null && item.isShooting() ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("reloading"), (pStack, p_174606_, property, p_174608_) -> {
            return property != null && item.isReloading() ? 1.0F : 0.0F;
        });
    }

    private static void makeEnergySword(EnergieSword item) {
        ItemProperties.register(item, new ResourceLocation("is_off"), (pStack, p_174606_, property, p_174608_) -> {
            return property != null && item.isOff(pStack) ? 1.0F : 0.0F;
        });
    }
}
