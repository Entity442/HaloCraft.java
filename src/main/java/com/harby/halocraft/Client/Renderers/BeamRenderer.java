package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.PlasmaRing;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import com.harby.halocraft.HaloEntities.Projectiles.BeamEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BeamRenderer<T extends BeamEntity> extends EntityRenderer<T> {
    private final PlasmaRing<BeamEntity> model;
    public BeamRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new PlasmaRing<>();
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return new ResourceLocation(HaloCraft.MODID,"textures/entity/beam.png");
    }


    @Override
    public void render(T pEntity, float value, float pPartialTick, PoseStack stack, MultiBufferSource source, int pPackedLight) {
        stack.pushPose();
        stack.translate(0,1.5,0);
        stack.mulPose(Axis.YP.rotationDegrees(180.0F - value));
        stack.mulPose(Axis.ZP.rotationDegrees(-180F));
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(getTextureLocation(pEntity)));
        this.model.renderToBuffer(stack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1.0F);
        this.model.setupAnim(pEntity,0,0,pPartialTick,0,0);
        renderLight(this.model,this.getTextureLocation(pEntity),stack,source,pPackedLight,pEntity,1,1,1);
        stack.popPose();
        super.render(pEntity, value, pPartialTick, stack, source, pPackedLight);
    }

    public static <T extends Entity> void renderLight(EntityModel<T> pModel, ResourceLocation pTextureLocation, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pEntity, float pRed, float pGreen, float pBlue) {
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.eyes(pTextureLocation));
        pModel.renderToBuffer(pMatrixStack, vertexconsumer, 15728640,OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
    }
}
