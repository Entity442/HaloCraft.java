package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;

public abstract class BaseBullet extends BaseAmmo {
    public AmmoTypes getAmmoType() {
        return AmmoTypes.BULLET;
    }

    @Override
    public double moveX(double posX, double movementX) {
        return posX+movementX;
    }
    @Override
    public double moveY(double posY, double movementY) {
        return posY+movementY-0.1; //gravity
    }
    @Override
    public double moveZ(double posZ, double movementZ) {
        return posZ+movementZ;
    }

    @Override
    public void onMove(BaseProjectileEntity bullet) {

    }
}
