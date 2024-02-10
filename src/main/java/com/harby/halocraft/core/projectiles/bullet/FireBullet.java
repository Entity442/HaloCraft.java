package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireBullet extends BaseBullet {
    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        if (!bullet.level().isClientSide()) {
            entityHitResult.getEntity().setSecondsOnFire(1);
        }
    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {
        if (!bullet.level().isClientSide()) {
            BlockState state = bullet.level().getBlockState(result.getBlockPos());
            if (state.isFlammable(bullet.level(), result.getBlockPos(), result.getDirection())) {
                BlockPos pos = result.getBlockPos().relative(result.getDirection());
                BlockState fire = Blocks.FIRE.defaultBlockState();
                bullet.level().setBlock(pos, fire, 2);
            }
        }
    }
    @Override
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.onFire();
    }

    @Override
    public AmmoList getBulletType() {
        return AmmoList.FIRE_BULLET;
    }
}
