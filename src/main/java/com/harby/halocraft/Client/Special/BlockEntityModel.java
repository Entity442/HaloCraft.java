package com.harby.halocraft.Client.Special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.function.Function;

public abstract class BlockEntityModel<T extends BlockEntity> extends Model {
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
    public BlockEntityModel(Function<ResourceLocation, RenderType> p_103110_) {
        super(p_103110_);
    }
    protected BlockEntityModel() {
        this(RenderType::entityCutout);
    }

    public abstract void setupAnim(T entity, float ageInTicks);


    protected void animate(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
        pAnimationState.updateTime(pAgeInTicks, pSpeed);
        pAnimationState.ifStarted((p_233392_) -> {
            KeyframeAnimationForBlocks.animate(this, pAnimationDefinition, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
        });
    }

    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    public abstract ModelPart root();

    public Optional<ModelPart> getAnyDescendantWithName(String pName) {
        return pName.equals("root") ? Optional.of(this.root()) : this.root().getAllParts().filter((p_233400_) -> {
            return p_233400_.hasChild(pName);
        }).findFirst().map((p_233397_) -> {
            return p_233397_.getChild(pName);
        });
    }
}
