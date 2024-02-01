package com.harby.halocraft.core.projectiles.ammoType;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.BulletEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BulletType extends BaseAmmoType {
    @Override
    public BaseProjectileEntity createBullet(Level level, LivingEntity livingEntity, AmmoList ammo, float speed) {
        return new BulletEntity(level, livingEntity, ammo, speed);
    }
}
