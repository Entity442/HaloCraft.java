package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloItems.Gun;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class BaseAmmo {
    public BaseAmmo() {
    }

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoTypes getAmmoType();

    public abstract void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult);

    public abstract void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result);

    public abstract Vec3 movement(Vec3 pos, Vec3 shoutedPos, Vec3 shoutedDirection, double tickCount);

    public abstract void onMove(BaseProjectileEntity bullet);

    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.mobProjectile(bullet, owner);
    }

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoList getBulletType();

    public abstract void onShoot(BaseProjectileEntity bullet, Gun gun);

}
