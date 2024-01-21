package com.harby.halocraft.core.projectiles.ammoType;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public abstract class BaseAmmoType {
    public abstract BaseProjectileEntity createBullet(Level level, LivingEntity livingEntity, AmmoList ammo);
}
