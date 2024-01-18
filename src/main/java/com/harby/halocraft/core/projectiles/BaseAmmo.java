package com.harby.halocraft.core.projectiles;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public abstract class BaseAmmo{
    public BaseAmmo() {
    }

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoTypes getAmmoType();
    public abstract void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult);
    public abstract void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result);

    public abstract double moveX(double posX, double movementX);
    public abstract double moveY(double posY, double movementY);
    public abstract double moveZ(double posZ, double movementZ);
    public abstract void onMove(BaseProjectileEntity bullet);

    /**
     * needed to be filled in by subclasses
     */
    public abstract AmmoList getBulletType();

}
