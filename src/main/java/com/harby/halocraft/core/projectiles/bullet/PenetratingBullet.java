package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class PenetratingBullet extends BaseBullet {
    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        bullet.level().playSound(bullet,bullet.getOnPos(), SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundSource.AMBIENT, 3F, 1F);
    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {

    }
    @Override
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.sonicBoom(owner);
    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.PENETRATING_BULLET;
    }
}
