package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloItems.Gun;
import com.harby.halocraft.core.HaloParticles;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class BaseBullet extends BaseAmmo {
    public AmmoTypes getAmmoType() {
        return AmmoTypes.BULLET;
    }

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
        if (bullet.tickCount == 0) return;
        double pTicks = 0d;
        while (pTicks < 1d) {
            Vec3 vec = (this.movement(bullet.getPosition(0), bullet.getShoutedPos(), bullet.getShoutedDirection(), bullet.tickCount - 1 + pTicks));
            bullet.level().addAlwaysVisibleParticle(HaloParticles.PLASMA_TRAIL.get(), vec.x, vec.y, vec.z, 1, 1, 1);
            pTicks += 0.2d;
        }
    }

    @Override
    public void onShoot(BaseProjectileEntity bullet, Gun gun) {
        bullet.level().playSound(bullet, bullet.getOnPos(), SoundEvents.FIREWORK_ROCKET_BLAST_FAR, SoundSource.AMBIENT, 4F, 2F);
    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult blockHitResult) {
        bullet.level().playSound(bullet, blockHitResult.getBlockPos(),SoundEvents.FIREWORK_ROCKET_BLAST_FAR,SoundSource.AMBIENT,1.0F, 1.0F);
    }

    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        bullet.level().playSound(bullet,entityHitResult.getEntity().getOnPos(), SoundEvents.FIREWORK_ROCKET_BLAST_FAR,SoundSource.AMBIENT, 0.5F, 2.0F);
    }
}
