package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SniperRiffle extends Gun{
    private final boolean golden;
    public SniperRiffle(Properties properties,boolean gold) {
        super(properties);
        this.golden = gold;
    }

    @Override
    public int getShootingDelay() {
        return 20;
    }

    @Override
    public void shotProjectile(Level level, LivingEntity livingEntity, ItemStack stack) {
        if (!level.isClientSide) {
            BaseBulletEntity bulletEntity = new BaseBulletEntity(level,livingEntity);
            bulletEntity.setProjectileType(getAmmoType(stack));
            bulletEntity.setDamage(golden ? 35f : 20f);
            bulletEntity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0F, 6.0F, 1.0F);
            level.addFreshEntity(bulletEntity);
        }
    }

    @Override
    public int getMaxAmmo() {
        return 15;
    }

    @Override
    public int getWeaponReloadCooldown() {
        return 50;
    }
}
