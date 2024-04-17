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

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoList getBulletType();

    /**
     * needed to be filled in by subclasses
     * function that compute the movement of the bullet
     *
     * @return the new position
     */
    public abstract Vec3 movement(Vec3 pos, Vec3 shoutedPos, Vec3 shoutedDirection, double tickCount);

    /*
     * called when the projectile hit an entity
     */
    public abstract void onHitEntity(BaseProjectileEntity projectile, EntityHitResult entityHitResult);

    /*
     * called when the projectile hit a block
     */
    public abstract void onHitBlock(BaseProjectileEntity projectile, BlockHitResult result);

    /*
     * called when the projectile move, called after the new position is set
     */
    public abstract void onMove(BaseProjectileEntity projectile);

    /*
     * called when the projectile is shot
     */
    public abstract void onShoot(BaseProjectileEntity projectile, Gun gun);

    /**
     * Retrieves the damage source for a given projectile and owner.
     */
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity projectile, LivingEntity owner) {
        return damageSources.mobProjectile(projectile, owner);
    }

}
