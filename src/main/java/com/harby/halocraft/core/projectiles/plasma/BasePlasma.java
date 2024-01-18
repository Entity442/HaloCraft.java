package com.harby.halocraft.core.projectiles.plasma;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.core.HaloParticles;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public abstract class BasePlasma extends BaseAmmo {
    @Override
    public AmmoTypes getAmmoType() {
        return AmmoTypes.PLASMA;
    }

    @Override
    public void onMove(BaseProjectileEntity bullet) {
        bullet.level().addParticle(HaloParticles.PLASMA_TRAIL.get(), bullet.getX() - 0.2, bullet.getY() - 0.2, bullet.getZ() - 0.2, 1, 1, 1);
    }

    @Override
    public void onHitEntity(BaseProjectileEntity bullet, EntityHitResult entityHitResult) {
        if (!bullet.level().isClientSide() && bullet instanceof PlasmaProjectileEntity e) {
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                if (e.getTemperature() >= 200) {
                    livingEntity.setSecondsOnFire(10);
                }
            }
        }
    }

    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {
        if (!bullet.level().isClientSide() && bullet instanceof PlasmaProjectileEntity e) {
            BlockState state = e.level().getBlockState(result.getBlockPos());
            if (e.getTemperature() >= 500 && state.isFlammable(e.level(), result.getBlockPos(), result.getDirection())) {
                BlockPos pos = result.getBlockPos().relative(result.getDirection());
                BlockState fire = Blocks.FIRE.defaultBlockState();
                e.level().setBlock(pos, fire, 2);
            }
        }
    }

    @Override
    public double moveX(double posX, double movementX) {
        return posX+movementX;
    }
    @Override
    public double moveY(double posY, double movementY) {
        return posY+movementY-0.1; //gravity
    }
    @Override
    public double moveZ(double posZ, double movementZ) {
        return posZ+movementZ;
    }
}
