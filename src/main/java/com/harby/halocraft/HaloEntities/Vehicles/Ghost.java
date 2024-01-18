package com.harby.halocraft.HaloEntities.Vehicles;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.HaloConfig;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.projectiles.AmmoList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Ghost extends BasicVehicleEntity {
    private int shooting_ticks = 0;
    public Ghost(Level level) {
        super(HaloEntities.GHOST.get(), level);
        this.setMaxUpStep(1.3F);
    }

    @Override
    public boolean isHoveringVehicle() {
        return true;
    }

    @Override
    public float getMaxVehicleHealth() {
        return (float) (HaloConfig.SERVER.ghost_hp.get() * 1.0F);
    }
    @Override
    public float getTopSpeed() {
        return 2.1F;
    }

    @Override
    public float flyingSpeed() {
        return 0.9F;
    }

    @Override
    protected void positionRider(Entity entity, MoveFunction p_19958_) {
        super.positionRider(entity, p_19958_);
        Vec3 vec3 = (new Vec3(-0.5D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        entity.setPos(this.getX() + vec3.x, this.getY() ,this.getZ()+ vec3.z);
    }

    @Override
    public void tick() {
        super.tick();
        Player player = (Player) this.getFirstPassenger();
        if (player == null) return;
        if (HaloKeys.getKey(3) && shooting_ticks < 2){
            shooting_ticks = 10;
            HaloCraft.sendMSGToServer(new HaloKeys(this.getId(), player.getId(), 3));
        }
        if (this.shooting_ticks > 0){
            --shooting_ticks;
            if (this.tickCount % 5 != 0){
                PlasmaProjectileEntity bulletEntity = new PlasmaProjectileEntity(this.level(),this, AmmoList.PlASMA_BALL);
                bulletEntity.setOwner(player);
                bulletEntity.setDamage(10.0F);
                bulletEntity.setTemperature(500);
                bulletEntity.setColor(3113940);
                bulletEntity.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 3.0F, 1.0F);
                Vec3 vec3;
                if (random.nextFloat() < 0.5f){
                    vec3 = (new Vec3(1.5D, 0.0D, 0.42D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
                }else{
                    vec3 = (new Vec3(1.5D, 0.0D, -0.42D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
                }
                bulletEntity.moveTo(this.getX() + vec3.x, this.getY()+0.32D ,this.getZ()+ vec3.z);
                this.level().addFreshEntity(bulletEntity);
            }
        }
    }

    public void onKeyPacket(Entity keyPresser, int type) {
        if (keyPresser.isPassengerOfSameVehicle(this)) {
            if (type == 3) {
                shooting_ticks = 10;
            }
        }
    }

}
