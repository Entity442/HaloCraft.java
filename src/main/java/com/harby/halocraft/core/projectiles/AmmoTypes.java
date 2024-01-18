package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.BulletEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.core.HaloEntities;
import net.minecraft.client.Minecraft;

public enum AmmoTypes {
    PLASMA(new PlasmaProjectileEntity(Minecraft.getInstance().level, HaloEntities.PLASMA_BALL.get())),
    BULLET(new BulletEntity(Minecraft.getInstance().level, HaloEntities.BULLET.get()));
    //BEAM(),//=> laser
    //ROCKET(),
    //NIDLER();
    private final BaseProjectileEntity classBullet;

    AmmoTypes(BaseProjectileEntity classBullet) {
        this.classBullet = classBullet;
    }

    public BaseProjectileEntity getClassBullet() {
        return classBullet;
    }
}