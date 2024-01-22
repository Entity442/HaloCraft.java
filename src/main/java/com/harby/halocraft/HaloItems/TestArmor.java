package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.interfaces.IHaloMaterials;
import net.minecraft.resources.ResourceLocation;

public class TestArmor extends HaloBaseArmor{
    public TestArmor(Type type) {
        super(new IHaloMaterials() {

            @Override
            public int getDefense(Type type) {
                return new int[]{3,6,9,12}[type.getSlot().getIndex()];
            }

            @Override
            public int getDurability(Type type) {return new int[]{100,100,100,100}[type.getSlot().getIndex()];}

            @Override
            public double getHealthModifier(Type type) {
                return new int[]{1,3,6,7}[type.getSlot().getIndex()];
            }

            @Override
            public double getSpeedModifier(Type type) {
                return new int[]{0,0,0,0}[type.getSlot().getIndex()];
            }

            @Override
            public double getSwimModifier(Type type) {
                return new int[]{1,2,3,4}[type.getSlot().getIndex()];
            }

            @Override
            public double getDamageModifier(Type type) {
                return new int[]{2,2,2,2}[type.getSlot().getIndex()];
            }

            @Override
            public double getKnockResistance(Type type) {
                return new int[]{1,1,1,1}[type.getSlot().getIndex()];
            }

            @Override
            public double getToughnessModifier(Type type) {
                return new int[]{2,2,2,2}[type.getSlot().getIndex()];
            }
        }, type,true);
    }


    @Override
    public ResourceLocation getHelmetTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/armor/unsc_helmet_tex.png");
    }

    @Override
    public ResourceLocation getChestplateTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/blank.png");
    }

    @Override
    public ResourceLocation getLegTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/blank.png");
    }

    @Override
    public ResourceLocation getBootTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/blank.png");
    }

}
