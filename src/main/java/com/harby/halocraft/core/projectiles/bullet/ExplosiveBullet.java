package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ExplosiveBullet extends BaseBullet{
    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        bullet.level().explode(bullet, bullet.getX(), bullet.getY(), bullet.getZ(), 0.5F, Level.ExplosionInteraction.NONE);
    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {
        bullet.level().explode(bullet, bullet.getX(), bullet.getY(), bullet.getZ(), 0.5F, Level.ExplosionInteraction.BLOCK);
    }
    @Override
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.explosion(bullet, owner);
    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.EXPLOSIVE_BULLET;
    }
}
