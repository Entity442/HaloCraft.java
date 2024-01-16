package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import com.harby.halocraft.core.projectiles.AmmoList;

public abstract class BaseBullet extends BaseAmmo {
    public AmmoTypes getAmmoType() {
        return AmmoTypes.BULLET;
    }

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoList getBulletType();
}
