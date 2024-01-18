package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.core.projectiles.bullet.*;
import com.harby.halocraft.core.projectiles.plasma.PlasmaBall;

public enum AmmoList {
    NONE(null, null),

    NORMAL_BULLET(new Bullet(), AmmoTypes.BULLET),
    PENETRATING_BULLET(new PenetratingBullet(), AmmoTypes.BULLET),
    FIRE_BULLET(new FireBullet(), AmmoTypes.BULLET),
    EXPLOSIVE_BULLET(new ExplosiveBullet(), AmmoTypes.BULLET),
    FROZEN_BULLET(new FrozenBullet(), AmmoTypes.BULLET),
    PlASMA_BALL(new PlasmaBall(), AmmoTypes.PLASMA);

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
