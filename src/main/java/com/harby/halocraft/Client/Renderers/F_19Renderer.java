package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.F_19Model;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.F_19;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class F_19Renderer<T extends F_19> extends VehicleRenderer<T> {
    public F_19Renderer(EntityRendererProvider.Context context) {
        super(context, new F_19Model<>());
        this.shadowRadius = 0.7F;
    }

    @Override
    public ResourceLocation getBaseTexture() {
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/f_29.png");
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
