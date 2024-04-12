package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

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
}
