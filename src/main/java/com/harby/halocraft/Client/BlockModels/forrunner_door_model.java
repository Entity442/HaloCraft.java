package com.harby.halocraft.Client.BlockModels;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.harby.halocraft.Client.Animations.ForrunnerDoorAnimations;
import com.harby.halocraft.Client.Special.BlockEntityModel;
import com.harby.halocraft.HaloBlockEntities.CustomDoorEntity;
import com.harby.halocraft.HaloCraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class forrunner_door_model<T extends CustomDoorEntity> extends BlockEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HaloCraft.MODID, "forerunner_door_variant_3"), "main");
	private final ModelPart frame;
	private final ModelPart right;
	private final ModelPart left;
	private final ModelPart top;

	public forrunner_door_model() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.frame = root.getChild("frame");
		this.right = root.getChild("right");
		this.left = root.getChild("left");
		this.top = root.getChild("top");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition frame = partdefinition.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(-8, -8).addBox(-24.0F, -53.0F, -5.0F, 6.0F, 48.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(-8, -8).addBox(18.0F, -53.0F, -5.0F, 6.0F, 48.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 29.0F, 0.0F));

		PartDefinition cube_r1 = frame.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-8, -8).mirror().addBox(-36.0F, -48.0F, -5.0F, 13.0F, 38.0F, 10.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r2 = frame.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-8, -8).addBox(23.0F, -48.0F, -5.0F, 13.0F, 38.0F, 10.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r3 = frame.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-8, -8).mirror().addBox(-41.0F, -12.0F, -5.0F, 4.0F, 13.0F, 10.0F, new CubeDeformation(-0.03F)).mirror(false), PartPose.offsetAndRotation(-5.4443F, -12.0875F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(-4, -4).addBox(0.0F, -22.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(-4, -4).addBox(6.0F, -48.0F, -3.0F, 12.0F, 48.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(-4, -4).addBox(-6.0F, -22.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(-4, -4).mirror().addBox(-18.0F, -48.0F, -3.0F, 12.0F, 48.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(-4, -4).mirror().addBox(-6.0F, -45.0F, -3.0F, 12.0F, 23.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		frame.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entity, float ageInTicks) {
		if (entity.getOpen()){
			top.yRot = ageInTicks;
		}
		animate(entity.openAnimationState,ForrunnerDoorAnimations.OPEN,ageInTicks,1f);
		animate(entity.closedAnimationState,ForrunnerDoorAnimations.CLOSE,ageInTicks,1f);
	}

	@Override
	public ModelPart root() {
		return this.top;
	}
}