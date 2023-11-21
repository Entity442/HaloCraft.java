package com.harby.halocraft.Client.Renderers;

import com.harby.halocraft.Client.Models.GhostModel;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.Ghost;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GhostRenderer<T extends Ghost> extends EntityRenderer<T> {
    private final GhostModel model;
    private static final ResourceLocation TEXTURE = new ResourceLocation(HaloCraft.MODID,
            "textures/entity/blank.png");

    public GhostRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.7F;
        this.model = new GhostModel();
    }


    @Override
    public void render(T type, float value, float value2, PoseStack stack, MultiBufferSource source, int value3) {
        stack.pushPose();
        stack.translate(0,1.5,0);
        stack.mulPose(Axis.YP.rotationDegrees(180.0F - value));
        stack.mulPose(Axis.ZP.rotationDegrees(-180F));
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(getTextureLocation(type)));
        float r;
        float g;
        float b;
        if  (type.getTint() != 0){
            int i = type.getTint();
            r = (float) (i >> 16 & 255) / 255.0F;
            g = (float) (i >> 8 & 255) / 255.0F;
            b = (float) (i & 255) / 255.0F;
        }else{
             r = 1.0f;
             g = 1.0f;
             b = 1.0f;
        }

        this.model.renderToBuffer(stack, vertexConsumer, value3, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);

        stack.popPose();
        super.render(type, value, value2, stack, source, value3);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return TEXTURE;
    }
}
