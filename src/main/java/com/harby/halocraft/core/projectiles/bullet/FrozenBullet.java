package com.harby.halocraft.core.projectiles.bullet;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FrozenBullet extends BaseBullet{
    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        entityHitResult.getEntity().setTicksFrozen(20);
    }

    @Override
    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult blockHitResult) {
        BlockState state = bullet.level().getBlockState(blockHitResult.getBlockPos());
        if (state.is(Blocks.WATER)) bullet.level().setBlock(blockHitResult.getBlockPos(), Blocks.ICE.defaultBlockState(), 2);
    }
    @Override
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.freeze();
    }
    @Override
    public AmmoList getBulletType() {
        return AmmoList.FROZEN_BULLET;
    }
}
