package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NeedlerEntity extends BaseProjectileEntity{
    public NeedlerEntity(Level level, Entity livingEntity, AmmoList ammo, EntityType<? extends BaseProjectileEntity> entityType, double speed) {
        super(level, livingEntity, ammo, entityType, speed);
        if (ammo.getType() != AmmoTypes.NEEDLER) {
            throw new IllegalArgumentException("NeedlerEntity can only be created with Needler ammo.");
        }
    }

    public NeedlerEntity(Level level, EntityType<? extends BaseProjectileEntity> entityType) {
        super(level, entityType);
    }
}
