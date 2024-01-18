package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class PenetratingBullet extends BaseBullet {
    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {

    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {

    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.PENETRATING_BULLET;
    }
}
