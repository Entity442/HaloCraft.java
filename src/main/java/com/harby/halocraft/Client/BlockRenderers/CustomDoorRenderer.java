package com.harby.halocraft.Client.BlockRenderers;

import com.harby.halocraft.Client.BlockModels.forrunner_door_model;
import com.harby.halocraft.Client.Special.BaseBlockEntityRenderer;
import com.harby.halocraft.HaloBlockEntities.CustomDoorEntity;
import com.harby.halocraft.HaloCraft;
import net.minecraft.resources.ResourceLocation;

public class CustomDoorRenderer extends BaseBlockEntityRenderer<CustomDoorEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HaloCraft.MODID,"textures/entity/blank.png");
    public CustomDoorRenderer() {
        super(new forrunner_door_model<>());
    }

    @Override
    public int getTicks(CustomDoorEntity entity) {
        return entity.tick;
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
