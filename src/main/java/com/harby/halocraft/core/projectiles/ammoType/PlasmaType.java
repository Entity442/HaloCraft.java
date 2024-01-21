package com.harby.halocraft.core.projectiles.ammoType;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class PlasmaType extends BaseAmmoType {
    @Override
    public BaseProjectileEntity createBullet(Level level, LivingEntity livingEntity, AmmoList ammo) {
        return new PlasmaProjectileEntity(level, livingEntity, ammo);
    }
}
