package com.harby.halocraft.HaloItems;

import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

public class AmmoItem extends ItemBase {
    private final AmmoList ammo;
    private final Gun[] guns;

    /**
     *Guns is the gun list that are allowed to use this ammo
     */
    public AmmoItem(Properties properties, AmmoList ammo, Gun... guns) {
        super(properties);
        this.ammo = ammo;
        for (Gun gun : guns) {
            if (gun.getAmmoType() != ammo.getType()) {
                throw new IllegalArgumentException("The gun "+gun.getClass().getName()+" has not a valid ammo type ("+ammo.getType().name()+") !");
            }
        }
        this.guns = guns;
    }

    public AmmoList getBullet() {
        return ammo;
    }

    public boolean isValidGun(ItemStack pItemStack) {
        return pItemStack.getItem() instanceof Gun gunClass && Arrays.asList(guns).contains(gunClass);
    }
}
