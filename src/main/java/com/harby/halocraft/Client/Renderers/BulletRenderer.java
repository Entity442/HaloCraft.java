package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BulletEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BulletRenderer <T extends BulletEntity> extends EntityRenderer<T> {
    public BulletRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return new ResourceLocation(HaloCraft.MODID,"");
    }
}
