package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SniperRiffle extends Gun{
    public static final int USE_DURATION = 1200;
    public static final float ZOOM_FOV_MODIFIER = 0.1F;
    private final boolean golden;
    public SniperRiffle(Properties properties,boolean gold) {
        super(properties);
        this.golden = gold;
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPYGLASS;
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

    @Override
    public boolean twoHands() {
        return true;
    }

    public boolean isScopeing(){
        return true;
    }
}
