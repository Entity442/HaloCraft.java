package com.harby.halocraft.core.projectiles.ammoType;

import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.HaloEntities.Projectiles.NeedlerEntity;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class NeedlerType extends BaseAmmoType {
    @Override
    public BaseProjectileEntity createBullet(Level level, LivingEntity livingEntity, AmmoList ammo, float speed) {
        return new NeedlerEntity(level, livingEntity, ammo, HaloEntities.NEEDLER_SHARD.get(), speed);
    }
}
