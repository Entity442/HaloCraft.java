package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class PenetratingBullet extends BaseBullet {
    @Override
    public void onHitEntity(BaseBulletEntity bullet, EntityHitResult entityHitResult) {

    }

    @Override
    public void onHitBlock(BaseBulletEntity bullet, BlockHitResult result) {

    }

    @Override
    public double moveX(double posX, double movementX) {
        return 0;
    }

    @Override
    public double moveY(double posY, double movementY) {
        return 0;
    }

    @Override
    public double moveZ(double posZ, double movementZ) {
        return 0;
    }

    @Override
    public void onMove(BaseBulletEntity bullet) {

    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.PENETRATING_BULLET;
    }
}
