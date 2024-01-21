package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class PlasmaProjectileEntity extends BaseProjectileEntity {
    private static final EntityDataAccessor<Integer> COLOR =
            SynchedEntityData.defineId(PlasmaProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TEMPERATURE =
            SynchedEntityData.defineId(PlasmaProjectileEntity.class, EntityDataSerializers.INT);

    public PlasmaProjectileEntity(Level level, Entity livingEntity, AmmoList ammo) {
        super(level, livingEntity, ammo, HaloEntities.PLASMA_BALL.get());
        if (ammo.getType() != AmmoTypes.PLASMA) {
            throw new IllegalArgumentException("PlasmaProjectileEntity can only be created with Plasma ammo.");
        }
    }

    public PlasmaProjectileEntity(Level level, EntityType<PlasmaProjectileEntity> entityType) {
        super(level, entityType);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, 0);
        this.entityData.define(TEMPERATURE, 0);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setColor(tag.getInt("color"));
        this.setTemperature(tag.getInt("temperature"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("color", this.getColor());
        tag.putInt("temperature", this.getTemperature());
    }

    public void setColor(int i) {
        this.entityData.set(COLOR, i);
    }

    public int getColor() {
        return this.entityData.get(COLOR);
    }

    public void setTemperature(int i) {
        this.entityData.set(TEMPERATURE, i);
    }

    public int getTemperature() {
        return this.entityData.get(TEMPERATURE);
    }

    /*@Override
    public void tick() {
        super.tick();
        double r = (float) (this.getColor() >> 16 & 255) / 255.0F;
        double g = (float) (this.getColor() >> 8 & 255) / 255.0F;
        double b = (float) (this.getColor() & 255) / 255.0F;
    }*/
}
