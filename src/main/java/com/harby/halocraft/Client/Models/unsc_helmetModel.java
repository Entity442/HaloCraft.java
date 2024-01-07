package com.harby.halocraft.Client.Models;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.harby.halocraft.HaloCraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class unsc_helmetModel<T extends LivingEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HaloCraft.MODID, "unsc_helmet"), "main");
	private final ModelPart UNSC_Helmet1;

	public unsc_helmetModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.UNSC_Helmet1 = root.getChild("UNSC_Helmet1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition UNSC_Helmet1 = partdefinition.addOrReplaceChild("UNSC_Helmet1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -31.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-4.0F, -30.0F, 4.0F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 3).addBox(-5.0F, -30.0F, -3.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 3).mirror().addBox(4.0F, -30.0F, -3.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(23, 18).addBox(-4.5F, -31.8F, -4.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.01F))
		.texOffs(0, 28).addBox(-4.0F, -32.3F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = UNSC_Helmet1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1554F, -26.0F, -3.7325F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r2 = UNSC_Helmet1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1554F, -26.0F, -3.7325F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.UNSC_Helmet1.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.UNSC_Helmet1.xRot = headPitch /  ( 90F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		UNSC_Helmet1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}