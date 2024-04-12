package com.harby.halocraft.core.projectiles.needler;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class NeedlerShard extends BaseAmmo {
    @Override
    public AmmoTypes getAmmoType() {
        return null;
    }

    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {

    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {

    }

    @Override
    public Vec3 movement(Vec3 pos, Vec3 shoutedPos, Vec3 shoutedDirection, double tickCount) {
        double x = shoutedPos.x;
        double y = shoutedPos.y;
        double z = shoutedPos.z;
        x += shoutedDirection.x * tickCount;
        y += shoutedDirection.y * tickCount;
        z += shoutedDirection.z * tickCount;
        //gravity: (here = -0.2 block / ticks)
        y -= 0.2 * tickCount * tickCount;
        return new Vec3(x, y, z);
    }

    @Override
    public void onMove(BaseProjectileEntity bullet) {

    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.NEEDLER_SHARD;
    }

    @Override
    public void onShoot(BaseProjectileEntity bullet) {

    }
}
