package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AssaultRifle extends Gun{
    public AssaultRifle(Properties properties) {
        super(properties, ammoType);
    }

    @Override
    public int getMaxAmmo() {
        return 60;
    }

    @Override
    public int getShootingDelay() {
        return 2;
    }

    @Override
    public int getWeaponReloadCooldown() {
        return 40;
    }

    @Override
    public void shotProjectile(Level level, LivingEntity livingEntity,ItemStack stack){
        if (!level.isClientSide) {
            BaseBulletEntity bulletEntity = new BaseBulletEntity(level,livingEntity);
            bulletEntity.setProjectileType(getAmmoType(stack));
            bulletEntity.setDamage(7.0f);
            bulletEntity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0F, 6.0F, 1.0F);
            level.addFreshEntity(bulletEntity);
        }
    }

    @Override
    public boolean twoHands() {
        return false;
    }
}
