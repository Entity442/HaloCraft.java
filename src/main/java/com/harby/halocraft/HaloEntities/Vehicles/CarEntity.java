package com.harby.halocraft.HaloEntities.Vehicles;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.HaloEntities.BaseClasses.VehiculeTypes;
import com.harby.halocraft.core.HaloConfig;
import com.harby.halocraft.core.HaloEntities;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CarEntity extends BasicVehicleEntity {
    private final CarPartEntity[] subEntities;
    public final CarPartEntity engine;
    public final CarPartEntity gun;

    public CarEntity(Level level) {
        super(HaloEntities.CAR.get(), level, VehiculeTypes.TIRED);
        this.setMaxUpStep(1.0F);
        this.engine = new CarPartEntity(this, "engine", 2.0F, 1.2F);
        this.gun = new CarPartEntity(this, "gun", 2.0F, 1.2F);
        this.subEntities = new CarPartEntity[]{this.engine,this.gun};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }

    @Override
    protected int getMaxPassengers() {
        return 2;
    }

    @Override
    public float getTopSpeed() {
        return 2.0F;
    }

    @Override
    public float speed() {
        return 0.8F;
    }


    @Override
    protected void positionRider(Entity entity, MoveFunction p_19958_) {
        super.positionRider(entity, p_19958_);
        Vec3 vec3;
        if (entity == this.getFirstPassenger()){
            vec3 = (new Vec3(0.1D, 0.0D, -0.6D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        }else{
            vec3 = (new Vec3(0.1D, 0.0D, +0.6D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));

        }
        entity.setPos(this.getX() + vec3.x, this.getY() + 0.2,this.getZ()+ vec3.z);
    }


    @Override
    public void tick() {
        float offset = 0;
        float f14 = this.getYRot() * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }
        if (this.getAccelerationLevel() > 0){
            offset = (1.85F/this.getTopSpeed()) * this.getAccelerationLevel();
        }
        this.tickPart(this.engine, (double)(f2 * (-2.0F - offset)), 0.0D, (double)(-f15 * (-2.0F - offset)));
        this.tickPart(this.gun, (double)(f2 * (2.0F - offset)), 0.0D, (double)(-f15 * (2.0F - offset)));
        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.tick();
    }

    private void tickPart(CarPartEntity part, double e, double i, double o) {
        part.setPos(this.getX() + e, this.getY() + i, this.getZ() + o);
    }
    public CarPartEntity[] getSubEntities() {
        return this.subEntities;
    }


    @Override
    public float getMaxVehicleHealth() {
        return (float) (HaloConfig.SERVER.car_hp.get() * 1.0F);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

        @Override
    public net.minecraftforge.entity.PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_218825_) {
        super.recreateFromPacket(p_218825_);
        if (true) return;
        CarPartEntity[] vehiclepart = this.getSubEntities();

        for(int i = 0; i < vehiclepart.length; ++i) {
            vehiclepart[i].setId(i + p_218825_.getId());
        }

    }

    public CarPartEntity getGun() {
        return gun;
    }


    @Override
    public boolean canCollideWith(Entity entity) {
        if (entity == this.engine){
            return false;
        }
        if (entity == this.gun){
            return false;
        }
        return super.canCollideWith(entity);
    }
}
