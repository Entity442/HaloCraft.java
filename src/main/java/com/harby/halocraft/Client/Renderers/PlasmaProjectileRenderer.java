package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.PlasmaProjectileModel;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PlasmaProjectileRenderer<T extends PlasmaProjectileEntity> extends EntityRenderer<T> {
    private final PlasmaProjectileModel model;
    private static final ResourceLocation TEXTURE = new ResourceLocation(HaloCraft.MODID,
            "textures/entity/blank.png");
    public PlasmaProjectileRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        this.model = new PlasmaProjectileModel();
    }


    @Override
    public void render(T type, float value, float value2, PoseStack stack, MultiBufferSource source, int value3) {
        stack.pushPose();
        stack.translate(0,1.5,0);
        stack.mulPose(Axis.YP.rotationDegrees(180.0F - value));
        stack.mulPose(Axis.ZP.rotationDegrees(-180F));
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(getTextureLocation(type)));
        int i = type.getColor();
        float r = (float) (i >> 16 & 255) / 255.0F;
        float g = (float) (i >> 8 & 255) / 255.0F;
        float b = (float) (i & 255) / 255.0F;
        this.model.renderToBuffer(stack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);

        stack.popPose();
        super.render(type, value, value2, stack, source, value3);
    }

    @Override
    protected int getBlockLightLevel(T type, BlockPos pos) {
        return Mth.clamp(super.getBlockLightLevel(type, pos) + 7, 0, 15);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return TEXTURE;
    }
}
