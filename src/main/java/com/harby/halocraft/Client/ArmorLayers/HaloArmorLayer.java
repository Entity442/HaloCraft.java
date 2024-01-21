package com.harby.halocraft.Client.ArmorLayers;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.HaloBaseArmor;
import com.harby.halocraft.HaloItems.interfaces.IHaloArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import org.antlr.v4.runtime.misc.Triple;

import java.util.ArrayList;
import java.util.List;

public class HaloArmorLayer<E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {
    protected final List<Triple<EquipmentSlot,Item,EntityModel<E>>> objects = new ArrayList<>();
    public HaloArmorLayer(RenderLayerParent<E, M> pRenderer) {
        super(pRenderer);
        this.addModelToItem();
    }

    public void addModelToItem(){
        /*this.objects.add(new Triple<>(EquipmentSlot.HEAD, HaloItems.TEST.get(),new unsc_helmetModel<>()));
        this.objects.add(new Triple<>(EquipmentSlot.CHEST, HaloItems.TEST2.get(),new GhostModelH<>()));
        this.objects.add(new Triple<>(EquipmentSlot.LEGS, HaloItems.TEST3.get(),new PlasmaRingH<>()));
        this.objects.add(new Triple<>(EquipmentSlot.FEET, HaloItems.TEST4.get(),new PlasmaRingH<>()));*/
    }

    private ResourceLocation getTextureLocation(EquipmentSlot slot,IHaloArmorItem item){
        if (slot == EquipmentSlot.HEAD){
            return item.getHelmetTexture();
        }else if (slot == EquipmentSlot.CHEST){
            return item.getChestplateTexture();
        }else if (slot == EquipmentSlot.LEGS){
            return item.getLegTexture();
        }else if (slot == EquipmentSlot.FEET){
            return item.getBootTexture();
        }
        return new ResourceLocation(HaloCraft.MODID,
                "textures/entity/blank.png");
    }


    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        for (var triple : objects){
            EquipmentSlot slot = triple.a;
            Item item = triple.b;
            EntityModel<E> model = triple.c;

            if (pLivingEntity.getItemBySlot(slot).getItem() == item && item instanceof IHaloArmorItem armorItem){
                this.renderArmorParts(pPoseStack,pBuffer,pPackedLight,pLivingEntity,model,1,1,1,getTextureLocation(slot,armorItem), pLimbSwing,  pLimbSwingAmount, pAgeInTicks,  pNetHeadYaw,  pHeadPitch);
            }
        }
        if (pLivingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof HaloBaseArmor armor && armor.isShielded() && armor.getShieldTicks() >0){
            ResourceLocation Shield = new ResourceLocation("minecraft:textures/entity/creeper/creeper_armor.png");
            this.renderEnergyShield(pPoseStack,pBuffer,pPackedLight,pLivingEntity,Shield,pLimbSwing,pLimbSwingAmount,pAgeInTicks,pNetHeadYaw,pHeadPitch,pPartialTick);
        }
    }

    private void renderArmorParts(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, EntityModel<E> pModel, float pRed, float pGreen, float pBlue, ResourceLocation armorResource
            , float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch){
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.armorCutoutNoCull(armorResource));
        pModel.setupAnim(pLivingEntity,pLimbSwing,pLimbSwingAmount,pAgeInTicks,pNetHeadYaw,pHeadPitch);
        pModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
    }

    private void renderEnergyShield(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, ResourceLocation armorResource
            , float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, float pPartialTicks){
        float f = (float)pLivingEntity.tickCount + pPartialTicks;
        HumanoidModel<E> entitymodel = this.getParentModel();
        entitymodel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
        this.getParentModel().copyPropertiesTo(entitymodel);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.energySwirl(armorResource, 0.01F % 1.0F, f * 0.01F % 1.0F));
        entitymodel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        entitymodel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }
}
