package com.harby.halocraft.HaloEntities.Vehicles;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.HaloEntities.BaseClasses.VehiculeTypes;
import com.harby.halocraft.core.HaloConfig;
import com.harby.halocraft.core.HaloEntities;
import net.minecraft.world.level.Level;

public class F_19 extends BasicVehicleEntity {
    public F_19(Level level) {
        super(HaloEntities.F29.get(), level, VehiculeTypes.FLYING);
    }

    @Override
    public float getTopSpeed() {
        return 4f;
    }

    @Override
    public float flyingSpeed() {
        return 0.9F;
    }

    @Override
    public float setMinFlyingSpeed() {
        return 1f;
    }

    @Override
    public float getMaxVehicleHealth() {
        return (float) (HaloConfig.SERVER.ghost_hp.get() * 1.0F);
    }
}
