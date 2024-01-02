package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.WarthogModel;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.HaloEntities.Vehicles.CarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WarthogRenderer <T extends CarEntity> extends VehicleRenderer<T> {

    public WarthogRenderer(EntityRendererProvider.Context context) {
        super(context, new WarthogModel<>());
        this.shadowRadius = 0.7F;
    }

    @Override
    public ResourceLocation getBaseTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/car.png");
    }

    @Override
    public ResourceLocation getTintedTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/blank.png");
    }

    @Override
    public ResourceLocation getDetailedTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/car_details.png");
    }
}
