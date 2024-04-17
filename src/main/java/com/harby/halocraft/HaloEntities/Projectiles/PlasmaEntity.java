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
import net.minecraft.world.phys.Vec3;

public class PlasmaEntity extends BaseProjectileEntity {
    private static final EntityDataAccessor<Integer> TEMPERATURE =
            SynchedEntityData.defineId(PlasmaEntity.class, EntityDataSerializers.INT);

    public PlasmaEntity(Level level, Entity livingEntity, AmmoList ammo, float speed, int temperature) {
        super(level, livingEntity, ammo, HaloEntities.PLASMA_BALL.get(), speed);
        if (ammo.getType() != AmmoTypes.PLASMA) {
            throw new IllegalArgumentException("PlasmaEntity can only be created with Plasma ammo.");
        }
        this.setTemperature(temperature);
    }
    public PlasmaEntity(Level level, Entity livingEntity, AmmoList ammo, double velocity, int temperature, Vec3 shoutedPos) {
        super(level, livingEntity, ammo, HaloEntities.PLASMA_BALL.get(), velocity, shoutedPos);
        if (ammo.getType() != AmmoTypes.PLASMA) {
            throw new IllegalArgumentException("PlasmaEntity can only be created with Plasma ammo.");
        }
        this.setTemperature(temperature);
    }

    public PlasmaEntity(Level level, EntityType<PlasmaEntity> entityType) {
        super(level, entityType);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TEMPERATURE, 0);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setTemperature(tag.getInt("temperature"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("temperature", this.getTemperature());
    }

    public int[] getRGBColor() {
        int r = (int) Math.min(Math.max(0.6d*Math.pow(this.getTemperature(),0.8d)+100*Math.pow(2,-this.getTemperature()*0.005),0),254);
        int g = (int) Math.min(Math.max(-0.000015d*Math.pow(this.getTemperature(),2)+0.111d*this.getTemperature()-7,0),254);
        int b = (int) Math.min(Math.max(0.3d*Math.pow(this.getTemperature(),0.9d)+100*Math.pow(4,-this.getTemperature()*0.0005),0),254);
        return new int[]{r, g, b};
    }

    public void setTemperature(int temperature) {
        this.entityData.set(TEMPERATURE, temperature);
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
