package com.harby.halocraft.HaloItems;

import com.harby.halocraft.core.projectiles.AmmoTypes;

public class PlasmaGun extends Gun{
    private final int plasma_temperature;
    public PlasmaGun(Properties properties, boolean twoHand, int maxAmmo, int shootingDelay, int reloadCooldown, float damage, float speed, int plasma_temperature) {
        super(properties, twoHand, AmmoTypes.PLASMA, maxAmmo, shootingDelay, reloadCooldown, damage, speed);
        this.plasma_temperature = plasma_temperature;
    }
    public int getPlasma_temperature() {
        return this.plasma_temperature;
    }

}
