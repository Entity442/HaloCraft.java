package com.harby.halocraft.core.projectiles.plasma;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaEntity;
import com.harby.halocraft.HaloItems.Gun;
import com.harby.halocraft.HaloItems.PlasmaGun;
import com.harby.halocraft.core.HaloParticles;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        if (bullet.tickCount == 0) return;
        if (bullet instanceof PlasmaEntity plasmaBall) {
            //this code allow more particules, so between the position at tick
            double pTicks = 0d;
            while (pTicks < 1d) {
                Vec3 vec = (this.movement(plasmaBall.getPosition(0), plasmaBall.getShoutedPos(), plasmaBall.getShoutedDirection(), plasmaBall.tickCount - 1 + pTicks));
                int[] color = plasmaBall.getRGBColor();
                plasmaBall.level().addAlwaysVisibleParticle(HaloParticles.PLASMA_TRAIL.get(), vec.x, vec.y, vec.z, color[0]/255f, color[1]/255f, color[2]/255f);
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
        if (bullet instanceof PlasmaEntity ball) {
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                ball.level().playLocalSound(entityHitResult.getLocation().x, entityHitResult.getLocation().y, entityHitResult.getLocation().z, SoundEvents.FIRECHARGE_USE, SoundSource.AMBIENT, 0.5F, 2.6F, false);
                if (ball.getTemperature() >= 200) {
                    livingEntity.setSecondsOnFire(ball.getTemperature() / 200);
                }
            }
        }
    }

    public void onHitBlock(BaseProjectileEntity bullet, BlockHitResult result) {
        if (bullet instanceof PlasmaEntity ball) {
        BlockState state = ball.level().getBlockState(result.getBlockPos());
        ball.level().playLocalSound(result.getLocation().x, result.getLocation().y, result.getLocation().z, SoundEvents.FIRECHARGE_USE, SoundSource.AMBIENT, 0.5F, 2.6F, false);
        if (ball.getTemperature() >= 500 && state.isFlammable(ball.level(), result.getBlockPos(), result.getDirection())) {
            BlockPos pos = result.getBlockPos().relative(result.getDirection());
            BlockState fire = Blocks.FIRE.defaultBlockState();
            ball.level().setBlock(pos, fire, 2);
        }}
    }

    public Vec3 movement(Vec3 pos, Vec3 shoutedPos, Vec3 shoutedDirection, double tickCount) {
        double x = shoutedPos.x;
        double y = shoutedPos.y;
        double z = shoutedPos.z;
        x += shoutedDirection.x * tickCount;
        y += shoutedDirection.y * tickCount;
        z += shoutedDirection.z * tickCount;
        //gravity: (here = -0.4 block / ticks)
        y -= 0.2 * tickCount * tickCount;
        return new Vec3(x, y, z);
    }

    @Override
    public void onShoot(BaseProjectileEntity bullet, Gun gun) {
        if (bullet instanceof PlasmaEntity plasmaBall && gun instanceof PlasmaGun pgun) {
            plasmaBall.setTemperature(200);//pgun.getPlasma_temperature());
            bullet.playSound(SoundEvents.FIRE_EXTINGUISH, 0.6F, 0.8F);
        }
    }
}
