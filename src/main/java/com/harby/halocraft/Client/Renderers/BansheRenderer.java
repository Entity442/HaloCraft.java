package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.BansheModel;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.Banshe;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BansheRenderer<T extends Banshe> extends VehicleRenderer<T> {

    public BansheRenderer(EntityRendererProvider.Context context) {
        super(context,new BansheModel<>());
        this.shadowRadius = 0.7F;
    }
    @Override
    public ResourceLocation getBaseTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/banshee.png");
    }

    @Override
    public ResourceLocation getTintedTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/blank.png");
    }

    @Override
    public ResourceLocation getDetailedTexture() {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/blank1.png");
    }
}
