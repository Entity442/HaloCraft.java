package com.harby.halocraft.Client.Models;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.Ghost;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class GhostModelH<T extends LivingEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HaloCraft.MODID, "ghost"), "main");
	private final ModelPart bone3;
	private final ModelPart bone2;

	public GhostModelH() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.bone3 = root.getChild("bone3");
		this.bone2 = root.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(-9, -9).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(-18, -18).addBox(-9.0F, -9.0F, -15.0F, 3.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(-18, -18).mirror().addBox(6.0F, -9.0F, -15.0F, 3.0F, 3.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-24, -24).addBox(-8.0F, -9.0F, 4.0F, 16.0F, 5.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -16.0F));

		PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-9, -9).addBox(-7.0F, -7.0F, -3.0F, 14.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.6191F, -4.7495F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-9, -9).addBox(-7.5F, -13.0F, -3.25F, 15.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.6226F, -8.8391F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-11, -11).addBox(-7.0F, -26.0F, -3.0F, 16.0F, 10.0F, 13.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, -20.4896F, -10.6792F, -1.7017F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-9, -9).addBox(-8.0F, -14.0F, -3.0F, 16.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.8536F, -7.1816F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-10, -10).addBox(-8.0F, -37.7F, 2.5F, 16.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.4633F, -1.7633F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-8, -8).addBox(-8.0F, -3.0F, -4.0F, 16.0F, 6.0F, 10.0F, new CubeDeformation(0.03F)), PartPose.offsetAndRotation(-0.01F, -9.9733F, 38.9467F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-4, -4).addBox(-8.0F, -3.0F, -6.0F, 16.0F, 6.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -10.8956F, 40.3319F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = bone3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-7, -7).addBox(-7.0F, -31.7F, 2.5F, 13.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.0904F, 7.7011F, -2.0071F, 0.0F, 0.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(-4, -4).mirror().addBox(-0.7783F, -7.8085F, -4.7463F, 12.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-4, -4).addBox(25.2217F, -7.8085F, -4.7463F, 12.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.2217F, 17.7986F, -4.819F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-14, -14).addBox(21.9117F, -7.7375F, -41.5459F, 5.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.9851F, -12.4645F, 20.3727F, 0.1642F, -0.0594F, 0.3442F));

		PartDefinition cube_r10 = bone2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-14, -14).addBox(8.0F, -8.1058F, -41.5459F, 12.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(-14, -14).mirror().addBox(-18.0F, -8.1058F, -41.5459F, 12.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.2217F, -4.2619F, 21.819F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bone2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-14, -14).mirror().addBox(-26.9117F, -7.7375F, -41.5459F, 5.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(22.4584F, -12.4645F, 20.3727F, 0.1642F, 0.0594F, -0.3442F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}