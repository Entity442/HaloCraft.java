package com.harby.halocraft.Client.Models;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.CarEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class WarthogModel<T extends CarEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HaloCraft.MODID, "warthog_basic_slim_mg"), "main");
	private final ModelPart trunk;
	private final ModelPart frame;
	private final ModelPart seats;
	private final ModelPart hexadecagon;
	private final ModelPart suspension;
	private final ModelPart wheel_detail;
	private final ModelPart turret;
	private final ModelPart bumper;
	private final ModelPart bb_main;

	public WarthogModel() {
		ModelPart root = createBodyLayer().bakeRoot();
		this.trunk = root.getChild("trunk");
		this.frame = root.getChild("frame");
		this.seats = root.getChild("seats");
		this.hexadecagon = root.getChild("hexadecagon");
		this.suspension = root.getChild("suspension");
		this.wheel_detail = root.getChild("wheel_detail");
		this.turret = root.getChild("turret");
		this.bumper = root.getChild("bumper");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition trunk = partdefinition.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(145, 113).addBox(1.876F, -15.554F, 20.5218F, 14.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.876F, 17.7F, 22.5623F));

		PartDefinition cube_r1 = trunk.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(14.0F, 3.2048F, -15.9411F, 30.0F, 0.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.876F, -14.119F, -22.8532F, 0.0F, -1.5708F, 0.0F));

		PartDefinition frame = partdefinition.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(59, 151).addBox(-6.4474F, -11.1983F, 33.6303F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(106, 123).addBox(-11.9984F, -18.9088F, -21.9584F, 25.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(143, 42).addBox(-10.7456F, -18.9088F, 0.744F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5526F, 11.8159F, 9.6271F));

		PartDefinition cube_r2 = frame.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(143, 46).addBox(-10.5F, -1.0F, -1.0F, 21.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5296F, -17.9088F, -9.7112F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = frame.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(73, 2).addBox(15.002F, 1.3205F, -16.4593F, 0.0F, 13.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, 0.7854F, -1.5708F));

		PartDefinition cube_r4 = frame.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(70, 106).addBox(18.825F, -0.8153F, -16.4593F, 2.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = frame.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(73, 0).addBox(-7.8077F, 10.6874F, -16.4593F, 12.0F, 1.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 1.5708F, 0.8727F, 1.5708F));

		PartDefinition cube_r6 = frame.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(59, 72).mirror().addBox(-13.8233F, 13.5153F, -16.4593F, 13.0F, 1.0F, 33.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -11.1016F, -19.4409F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r7 = frame.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(59, 72).addBox(-0.8233F, 13.5153F, -16.4593F, 13.0F, 1.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, 0.8727F, -1.5708F));

		PartDefinition cube_r8 = frame.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(106, 34).addBox(16.4038F, 9.0437F, -16.4593F, 2.0F, 2.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(83, 77).addBox(17.9005F, 4.6891F, -17.0093F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, 1.2217F, -1.5708F));

		PartDefinition cube_r9 = frame.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(143, 50).mirror().addBox(-16.5692F, 13.1617F, -17.0857F, 18.0F, 5.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, -0.8727F, 1.5708F));

		PartDefinition cube_r10 = frame.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(145, 106).mirror().addBox(-10.3085F, 9.6575F, -17.0857F, 17.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r11 = frame.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.1229F, 9.6875F, 15.0857F, 13.0F, 5.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 1.5708F, 0.6981F, 1.5708F));

		PartDefinition cube_r12 = frame.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(143, 50).addBox(-1.4308F, 13.1617F, -17.0857F, 18.0F, 5.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, 0.8727F, -1.5708F));

		PartDefinition cube_r13 = frame.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(145, 106).addBox(-6.6915F, 9.6575F, -17.0857F, 17.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r14 = frame.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-6.8771F, 9.6875F, 15.0857F, 13.0F, 5.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 1.5708F, -0.6981F, -1.5708F));

		PartDefinition cube_r15 = frame.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(1.0219F, 16.5861F, -5.5175F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 2.6922F, -0.3146F, 0.9639F));

		PartDefinition cube_r16 = frame.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(83, 77).mirror().addBox(-19.9005F, 4.6891F, -17.0093F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -1.5708F, -1.2217F, 1.5708F));

		PartDefinition cube_r17 = frame.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(24, 11).mirror().addBox(-18.3212F, 5.2858F, -4.9164F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -2.2166F, -0.5904F, 1.968F));

		PartDefinition cube_r18 = frame.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 7).addBox(-13.0219F, 16.5861F, -5.5175F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 2.6922F, 0.3146F, -0.9639F));

		PartDefinition cube_r19 = frame.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(24, 11).addBox(16.3212F, 5.2858F, -4.9164F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, -2.2166F, 0.5904F, -1.968F));

		PartDefinition cube_r20 = frame.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(106, 131).mirror().addBox(-0.1466F, -2.9634F, 43.2331F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 110).mirror().addBox(-0.1466F, -1.4634F, 42.9831F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r21 = frame.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(106, 131).mirror().addBox(-4.5F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.0169F, -10.1983F, 27.6968F, 0.0F, -1.3963F, 0.0F));

		PartDefinition cube_r22 = frame.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(143, 38).mirror().addBox(11.3568F, -2.9634F, 15.3357F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(59, 142).mirror().addBox(11.3568F, -1.4634F, 15.0857F, 22.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r23 = frame.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(143, 38).addBox(-33.3568F, -2.9634F, 15.3357F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(59, 142).addBox(-33.3568F, -1.4634F, 15.0857F, 22.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r24 = frame.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 110).mirror().addBox(-4.5F, -3.0F, -1.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-14.7669F, -6.6983F, 27.6968F, 0.0F, -1.3963F, 0.0F));

		PartDefinition cube_r25 = frame.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 110).addBox(-4.5F, -3.0F, -1.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.8721F, -6.6983F, 27.6968F, 0.0F, 1.3963F, 0.0F));

		PartDefinition cube_r26 = frame.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 110).addBox(-8.8534F, -1.4634F, 42.9831F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(106, 131).addBox(-8.8534F, -2.9634F, 43.2331F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r27 = frame.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 105).addBox(10.4207F, 6.8661F, -16.2411F, 2.0F, 2.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5526F, -8.2349F, -9.918F, 1.5708F, -1.2217F, -1.5708F));

		PartDefinition cube_r28 = frame.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(106, 131).addBox(-4.5F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.1221F, -10.1983F, 27.6968F, 0.0F, 1.3963F, 0.0F));

		PartDefinition seats = partdefinition.addOrReplaceChild("seats", CubeListBuilder.create(), PartPose.offset(10.136F, 18.8906F, 11.5334F));

		PartDefinition cube_r29 = seats.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(106, 106).mirror().addBox(-5.0169F, 3.371F, 1.3807F, 13.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.136F, -15.3095F, -11.8244F, 1.5708F, 0.2618F, 1.5708F));

		PartDefinition cube_r30 = seats.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(118, 69).mirror().addBox(-2.4485F, 6.1975F, 1.3807F, 13.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.136F, -15.3095F, -11.8244F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r31 = seats.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(118, 69).addBox(-10.5515F, 6.1975F, 1.3807F, 13.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.136F, -15.3095F, -11.8244F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r32 = seats.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(106, 106).addBox(-7.9831F, 3.371F, 1.3807F, 13.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.136F, -15.3095F, -11.8244F, 1.5708F, -0.2618F, -1.5708F));

		PartDefinition hexadecagon = partdefinition.addOrReplaceChild("hexadecagon", CubeListBuilder.create(), PartPose.offset(19.25F, 132.25F, -142.0F));

		PartDefinition suspension = partdefinition.addOrReplaceChild("suspension", CubeListBuilder.create(), PartPose.offset(7.9677F, 19.195F, 27.5543F));

		PartDefinition wheel_detail = partdefinition.addOrReplaceChild("wheel_detail", CubeListBuilder.create(), PartPose.offset(104.055F, 18.195F, 27.805F));

		PartDefinition bone = wheel_detail.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-201.11F, -2.0F, -1.0F));

		PartDefinition bone2 = wheel_detail.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-201.11F, -2.0F, -54.61F));

		PartDefinition bone4 = bone2.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 0.0F));

		PartDefinition bone3 = bone4.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r1 = bone3.addOrReplaceChild("hexadecagon_r1", CubeListBuilder.create().texOffs(0, 78).addBox(-5.0F, -4.8576F, 17.7626F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(96.055F, -14.614F, 22.096F, -0.48F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r2 = bone3.addOrReplaceChild("hexadecagon_r2", CubeListBuilder.create().texOffs(0, 78).addBox(-5.0F, -4.8576F, -20.7626F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(96.055F, -13.614F, 26.5141F, 0.48F, 0.0F, 0.0F));

		PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 232).addBox(-112.055F, -5.7341F, -6.2945F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 232).addBox(-105.055F, -5.9341F, -6.0945F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, -7.0659F, -1.4055F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, 5.6528F, -1.4055F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(237, 251).addBox(-113.055F, -1.4055F, -7.0659F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, -1.4055F, 5.6528F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(0, 232).mirror().addBox(-80.055F, -5.7341F, -6.2945F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 232).mirror().addBox(-87.055F, -5.9341F, -6.0945F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, -7.0659F, -1.4055F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, 5.6528F, -1.4055F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(237, 251).mirror().addBox(-87.1195F, -1.4055F, -7.0659F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, -1.4055F, 5.6528F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 232).addBox(-112.055F, -5.7341F, 44.4045F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 232).addBox(-105.055F, -5.9341F, 44.7045F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, -7.0659F, 49.2045F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, 5.6528F, 49.2045F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, -1.4055F, 56.2628F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-113.055F, -1.4055F, 43.5441F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).mirror().addBox(-87.1195F, -1.4055F, 43.5441F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, -1.4055F, 56.2628F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, 5.6528F, 49.2045F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(-87.1195F, -7.0659F, 49.2045F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 232).mirror().addBox(-87.055F, -5.9341F, 44.7045F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 232).mirror().addBox(-80.055F, -5.7341F, 44.4045F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(192.11F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r3 = bone5.addOrReplaceChild("hexadecagon_r3", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, -4.4748F, 27.9139F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 8.2439F, 27.9139F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 1.1857F, 34.9721F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 1.1857F, 22.2534F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 1.1857F, 22.2534F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 1.1857F, 34.9721F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 8.2439F, 27.9139F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, -4.4748F, 27.9139F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, -0.3927F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r4 = bone5.addOrReplaceChild("hexadecagon_r4", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, 15.4982F, 17.4942F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 28.2169F, 17.4942F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 21.1587F, 24.5524F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 21.1587F, 11.8337F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 21.1587F, 11.8337F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 21.1587F, 24.5524F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 28.2169F, 17.4942F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 15.4982F, 17.4942F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, 0.3927F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r5 = bone5.addOrReplaceChild("hexadecagon_r5", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, -10.2316F, 33.7319F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, -10.2316F, 21.0132F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, -10.2316F, 21.0132F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, -10.2316F, 33.7319F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, -0.7854F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r6 = bone5.addOrReplaceChild("hexadecagon_r6", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, 26.6737F, 14.4789F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 26.6737F, 1.7602F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 26.6737F, 1.7602F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 26.6737F, 14.4789F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, 0.7854F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r7 = bone5.addOrReplaceChild("hexadecagon_r7", CubeListBuilder.create().texOffs(21, 140).mirror().addBox(-6.1535F, -7.2322F, 15.3613F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, -0.6545F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r8 = bone5.addOrReplaceChild("hexadecagon_r8", CubeListBuilder.create().texOffs(23, 81).mirror().addBox(7.0F, -1.9145F, 28.3917F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(23, 81).addBox(-9.0F, -1.9145F, 28.3917F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, -0.48F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r9 = bone5.addOrReplaceChild("hexadecagon_r9", CubeListBuilder.create().texOffs(21, 140).addBox(3.1535F, -7.2322F, 15.3613F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 24.5141F, -0.6545F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r10 = bone5.addOrReplaceChild("hexadecagon_r10", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, 26.9693F, -3.469F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 26.9693F, -16.1877F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 26.9693F, -3.469F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 26.9693F, -16.1877F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, -0.7854F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r11 = bone5.addOrReplaceChild("hexadecagon_r11", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, 21.3187F, -13.6332F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(237, 251).mirror().addBox(8.9355F, 21.3187F, -26.3519F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 28.3769F, -20.6915F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 15.6582F, -20.6915F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 21.3187F, -13.6332F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(237, 251).addBox(-17.0F, 21.3187F, -26.3519F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 28.3769F, -20.6915F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 15.6582F, -20.6915F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, -0.3927F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r12 = bone5.addOrReplaceChild("hexadecagon_r12", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, 1.0257F, -24.0529F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(237, 251).mirror().addBox(8.9355F, 1.0257F, -36.7716F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, 8.0839F, -31.1112F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).mirror().addBox(8.9355F, -4.6348F, -31.1112F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, 1.0257F, -24.0529F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(237, 251).addBox(-17.0F, 1.0257F, -36.7716F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, 8.0839F, -31.1112F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(-17.0F, -4.6348F, -31.1112F, 8.0645F, 1.4132F, 2.811F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, 0.3927F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r13 = bone5.addOrReplaceChild("hexadecagon_r13", CubeListBuilder.create().texOffs(234, 251).mirror().addBox(8.9355F, -10.5273F, -22.7221F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(237, 251).mirror().addBox(8.9355F, -10.5273F, -35.4408F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(234, 251).addBox(-17.0F, -10.5273F, -22.7221F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F))
		.texOffs(237, 251).addBox(-17.0F, -10.5273F, -35.4408F, 8.0645F, 2.811F, 1.4132F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, 0.7854F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r14 = bone5.addOrReplaceChild("hexadecagon_r14", CubeListBuilder.create().texOffs(92, 0).mirror().addBox(7.0F, -2.1076F, -31.7626F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 0).addBox(-9.0F, -2.1076F, -31.7626F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, 0.48F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r15 = bone5.addOrReplaceChild("hexadecagon_r15", CubeListBuilder.create().texOffs(93, 142).mirror().addBox(-6.3135F, -7.4673F, -29.6677F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, 0.6545F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r16 = bone5.addOrReplaceChild("hexadecagon_r16", CubeListBuilder.create().texOffs(93, 142).addBox(3.3135F, -7.4673F, -29.6677F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-96.055F, -13.614F, 26.5141F, 0.6545F, 0.3927F, 0.0F));

		PartDefinition turret = partdefinition.addOrReplaceChild("turret", CubeListBuilder.create().texOffs(59, 71).addBox(-2.998F, 6.1878F, -0.2539F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(63, 106).addBox(-1.748F, -5.4545F, -4.017F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(130, 0).addBox(-3.748F, -10.4545F, -12.767F, 7.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(63, 106).addBox(-1.048F, -10.2045F, -29.767F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(63, 106).addBox(-2.348F, -7.8045F, -29.767F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(63, 106).addBox(0.252F, -7.8045F, -29.767F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(73, 47).addBox(-7.998F, 12.1878F, -5.2539F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.002F, -7.1878F, 22.2539F));

		PartDefinition cube_r33 = turret.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(27, 89).mirror().addBox(-16.3591F, -17.9733F, 18.8561F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(91, 31).mirror().addBox(-16.3591F, -20.1233F, 18.8561F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 22).mirror().addBox(-17.3591F, -20.9733F, 19.1061F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.002F, 10.7688F, -22.5448F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r34 = turret.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(27, 89).addBox(14.3591F, -17.9733F, 18.8561F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(91, 31).addBox(14.3591F, -20.1233F, 18.8561F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 22).addBox(16.3591F, -20.9733F, 19.1061F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.002F, 10.7688F, -22.5448F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r35 = turret.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(77, 71).addBox(-2.5F, -19.6616F, 3.9813F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 41).mirror().addBox(7.25F, -27.6616F, 3.7313F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 41).addBox(-8.25F, -27.6616F, 3.7313F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(73, 47).addBox(2.25F, -29.6616F, 3.7313F, 5.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(79, 83).addBox(-7.25F, -29.6616F, 3.7313F, 5.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.002F, 10.7688F, -22.5448F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r36 = turret.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(37, 105).addBox(-2.0F, -1.7718F, 22.0412F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(59, 83).addBox(-2.5F, 4.0782F, 21.5412F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.002F, 10.7688F, -22.5448F, 0.5236F, 0.0F, 0.0F));

		PartDefinition bumper = partdefinition.addOrReplaceChild("bumper", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 45.0F));

		PartDefinition cube_r37 = bumper.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(130, 30).addBox(-10.0F, -2.5F, 0.75F, 20.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.9F, -0.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r38 = bumper.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(92, 16).mirror().addBox(-2.0F, -2.25F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.6564F, 2.7446F, 0.0582F, -0.1886F, 0.3864F, -0.0718F));

		PartDefinition cube_r39 = bumper.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(92, 16).mirror().addBox(-2.0F, -2.25F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.6564F, -0.1554F, 0.5582F, -0.1886F, 0.3864F, -0.0718F));

		PartDefinition cube_r40 = bumper.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(92, 16).addBox(-2.0F, -2.25F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6564F, 2.7446F, 0.0582F, -0.1886F, -0.3864F, 0.0718F));

		PartDefinition cube_r41 = bumper.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(130, 30).addBox(-10.0F, -2.5F, 0.75F, 20.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r42 = bumper.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(92, 16).addBox(-2.0F, -2.25F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6564F, -0.1554F, 0.5582F, -0.1886F, -0.3864F, 0.0718F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r43 = bb_main.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 84).addBox(-6.4888F, -5.25F, -1.3736F, 6.0F, 7.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(15.0881F, -15.864F, -39.5548F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r44 = bb_main.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 84).addBox(0.4888F, -5.25F, -1.3736F, 6.0F, 7.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-15.0881F, -15.864F, -39.5548F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r45 = bb_main.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(92, 11).mirror().addBox(-8.0F, -1.0F, -2.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 11).addBox(6.0F, -1.0F, -2.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.0F, 45.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r46 = bb_main.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-10.0F, -3.0F, -16.5F, 20.0F, 6.0F, 33.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2093F, -17.6304F, -30.9976F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r47 = bb_main.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(37, 106).addBox(42.4888F, -4.0F, 10.8764F, 3.0F, 6.0F, 20.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-20.8357F, -16.6304F, 2.4912F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r48 = bb_main.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(85, 47).addBox(1.468F, 5.5F, -10.0827F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(85, 55).addBox(7.468F, 5.5F, -10.0827F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.419F, -0.2909F, 0.0F, 0.5672F, -1.5708F));

		PartDefinition cube_r49 = bb_main.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(59, 83).addBox(-0.5F, -1.15F, -0.5F, 1.0F, 2.3F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.85F, -19.3527F, -11.2109F, 0.0F, 0.5672F, -1.5708F));

		PartDefinition cube_r50 = bb_main.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(29, 58).addBox(4.7F, -7.468F, -10.0827F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(59, 71).addBox(12.3F, -7.468F, -10.0827F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 95).addBox(8.0F, -6.468F, -15.2827F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.419F, -0.2909F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r51 = bb_main.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(74, 83).addBox(-5.468F, -12.3F, -10.2827F, 1.0F, 2.3F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.419F, -0.2909F, 0.0F, -0.5672F, 1.5708F));

		PartDefinition cube_r52 = bb_main.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(98, 22).addBox(8.0F, -13.6664F, -11.4141F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.419F, -0.2909F, 1.1345F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		trunk.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frame.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		seats.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hexadecagon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		suspension.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wheel_detail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		turret.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bumper.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}