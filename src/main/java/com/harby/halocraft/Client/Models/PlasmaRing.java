package com.harby.halocraft.Client.Models;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BeamEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PlasmaRing<T extends BeamEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HaloCraft.MODID, "plasmaring"), "main");
	private final ModelPart circle;
	private final ModelPart circle2;
	private final ModelPart circle3;
	private final ModelPart middle;

	public PlasmaRing() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.circle = root.getChild("circle");
		this.circle2 = root.getChild("circle2");
		this.circle3 = root.getChild("circle3");
		this.middle = root.getChild("middle");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition circle = partdefinition.addOrReplaceChild("circle", CubeListBuilder.create().texOffs(36, 24).addBox(-9.0F, 0.0F, -9.0F, 18.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition circle2 = partdefinition.addOrReplaceChild("circle2", CubeListBuilder.create().texOffs(0, 24).addBox(-9.0F, -5.0F, -9.0F, 18.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition circle3 = partdefinition.addOrReplaceChild("circle3", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -5.0F, -12.0F, 24.0F, 0.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition middle = partdefinition.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(96, 0).addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.middle.xScale = 1.5f;
		this.middle.yScale = 1.5f;

		this.circle.xScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;
		this.circle.zScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;

		this.circle2.xScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;
		this.circle2.zScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;

		this.circle3.xScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;
		this.circle3.zScale =1.5f + (Mth.cos(ageInTicks)/4) * 2;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		circle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		circle2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		circle3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		middle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}