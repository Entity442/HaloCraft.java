package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class BulletEntity extends BaseProjectileEntity {
    public BulletEntity(Level level, Entity livingEntity, AmmoList ammo, float speed) {
        super(level, livingEntity, ammo, HaloEntities.BULLET.get(), speed);
        if (ammo.getType() != AmmoTypes.BULLET) {
            throw new IllegalArgumentException("BulletEntity can only be created with Bullet ammo.");
        }
    }

    public BulletEntity(Level level, EntityType<BulletEntity> entityType) {
        super(level, entityType);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        this.playSound(SoundEvents.FIREWORK_ROCKET_BLAST_FAR, 1.0F, 1.0F);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        this.playSound(SoundEvents.FIREWORK_ROCKET_BLAST_FAR, 0.5F, 2.0F);
    }
}
