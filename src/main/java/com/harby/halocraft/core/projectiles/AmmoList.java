package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.core.projectiles.bullet.Bullet;
import com.harby.halocraft.core.projectiles.bullet.PenetratingBullet;

public enum AmmoList {
    BULLET(new Bullet(), AmmoTypes.BULLET),
    PENETRATING_BULLET(new PenetratingBullet(), AmmoTypes.BULLET);

    private final BaseAmmo ba;
    private final AmmoTypes at;
    AmmoList(BaseAmmo ba, AmmoTypes at) {
        this.ba = ba;
        this.at = at;
    }
    public BaseAmmo get() {
        return ba;
    }
    public AmmoTypes getType() {
        return at;
    }
}
