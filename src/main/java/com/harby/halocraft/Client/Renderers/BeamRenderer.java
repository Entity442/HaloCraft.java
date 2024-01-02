package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import com.harby.halocraft.HaloEntities.Projectiles.BeamEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BeamRenderer<T extends BeamEntity> extends EntityRenderer<T> {
    public BeamRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return new ResourceLocation(HaloCraft.MODID,"");
    }
}
