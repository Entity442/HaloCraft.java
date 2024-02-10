package com.harby.halocraft.core.projectiles.plasma;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.core.HaloParticles;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class BasePlasma extends BaseAmmo {
    @Override
    public AmmoTypes getAmmoType() {
        return AmmoTypes.PLASMA;
    }

    @Override
    public void onMove(BaseProjectileEntity bullet) {
        if (bullet instanceof PlasmaProjectileEntity plasmaBall) {
            //this code allow more particules, so between the position at tick
            double pTicks = 0d;
            while (pTicks < 1d) {
                Vec3 vec = (this.movement(plasmaBall.getPosition(0), plasmaBall.getShoutedPos(), plasmaBall.getShoutedDirection(), plasmaBall.tickCount - 1 + pTicks));
                plasmaBall.level().addAlwaysVisibleParticle(HaloParticles.PLASMA_TRAIL.get(), vec.x, vec.y, vec.z, plasmaBall.getColor(), plasmaBall.getColor(), plasmaBall.getColor());
                pTicks += 0.05d;
            }
        }
    }

    @Override
    public DamageSource getDamageSource(DamageSources damageSources, BaseProjectileEntity bullet, LivingEntity owner) {
        return damageSources.indirectMagic(bullet, owner);
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

    public Vec3 movement(Vec3 pos, Vec3 shoutedPos, Vec3 shoutedDirection, double tickCount) {
        double x = shoutedPos.x;
        double y = shoutedPos.y;
        double z = shoutedPos.z;
        x += shoutedDirection.x * tickCount;
        y += shoutedDirection.y * tickCount;
        z += shoutedDirection.z * tickCount;
        //gravity: (here = -0.2 block / ticks)
        y -= 0.1 * tickCount * tickCount;
        return new Vec3(x, y, z);
    }

    @Override
    public void onShoot(BaseProjectileEntity bullet) {

    }
}
