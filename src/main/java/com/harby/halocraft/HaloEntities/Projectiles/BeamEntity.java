package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.HaloParticles;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BeamEntity extends Projectile {
    public BeamEntity(Level level) {
        super(HaloEntities.BEAM.get(), level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= 400) {
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.setPos(d0, d1, d2);
        double r = (float) (this.getColor() >> 16 & 255) / 255.0F;
        double g = (float) (this.getColor() >> 8 & 255) / 255.0F;
        double b = (float) (this.getColor() & 255) / 255.0F;
        double x = d0+ this.random.nextDouble() - this.random.nextDouble();
        double y = d1+ this.random.nextDouble() - this.random.nextDouble();
        double z = d2+ this.random.nextDouble() - this.random.nextDouble();
        for (int e = 0; e<14;e++){
            level().addParticle(HaloParticles.PLASMA_TRAIL.get(),x, y, z,r,g,b);
        }
        level().addParticle(HaloParticles.PLASMA_TRAIL.get(),d0, y-1, d2,r,g,b);
    }

    private int getColor() {
        return 6719955;
    }
}
