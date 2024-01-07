package com.harby.halocraft.HaloItems.interfaces;

import com.harby.halocraft.HaloItems.HaloBaseArmor;

public interface IHaloMaterials {
    int getDurability(HaloBaseArmor.Type type);
    int getDefense(HaloBaseArmor.Type type);

    double getHealthModifier(HaloBaseArmor.Type type);
    double getSpeedModifier(HaloBaseArmor.Type type);
    double getSwimModifier(HaloBaseArmor.Type type);
    double getDamageModifier(HaloBaseArmor.Type type);
    double getKnockResistance(HaloBaseArmor.Type type);
    double getToughnessModifier(HaloBaseArmor.Type type);

}
