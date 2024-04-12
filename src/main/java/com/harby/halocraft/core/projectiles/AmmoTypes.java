package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.ammoType.BaseAmmoType;
import com.harby.halocraft.core.projectiles.ammoType.BulletType;
import com.harby.halocraft.core.projectiles.ammoType.NeedlerType;
import com.harby.halocraft.core.projectiles.ammoType.PlasmaType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public enum AmmoTypes {
    PLASMA(new PlasmaType()),
    BULLET(new BulletType()),
    NEEDLER(new NeedlerType());
    //BEAM(),//=> laser
    //ROCKET();
    private final BaseAmmoType classBullet;

    AmmoTypes(BaseAmmoType classBullet) {
        this.classBullet = classBullet;
    }

    public BaseProjectileEntity createBullet(Level level, LivingEntity livingEntity, AmmoList ammo, float speed) {
        return classBullet.createBullet(level, livingEntity, ammo, speed);
    }
}