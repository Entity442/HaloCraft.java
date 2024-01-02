package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.GhostModel;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.Ghost;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GhostRenderer<T extends Ghost> extends VehicleRenderer<T> {
    public GhostRenderer(EntityRendererProvider.Context context) {
        super(context,new GhostModel<T>());
        this.shadowRadius = 0.7F;
    }

    @Override
    public ResourceLocation getBaseTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/blank.png");
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
